package com.Ankiety_PZ.query;

import com.Ankiety_PZ.hibernate.Ankiety;
import com.Ankiety_PZ.hibernate.Odpowiedzi;
import com.Ankiety_PZ.hibernate.Pytania;
import com.Ankiety_PZ.test.TypeOfQuestion;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

/**
 * Klasa zawiera metody do przesyłu danych z bazą danych dla tabeli Ankiety.
 */

public class AnkietyQuery extends OperationInSession {

    /**
     * Metoda przesyła listę wszystkich Ankiet.
     *
     * @return lista wszystkich Ankiet, jeśli rozmiar listy wynosi 0 znaczy to, że nie ma ankiet w bazie.
     */

    public List<Ankiety> selectAll() {
        List<Ankiety> ankiety = new ArrayList<>();
        try {
            session = openSession();
            criteria = session.createCriteria(Ankiety.class);
            ankiety = criteria.list();
        } catch(Exception e){
            logException(e);
        }finally{
            sessionClose(session);
        }
        return ankiety;
    }

    /**
     * Metoda przesyła listę Ankiet aktywnych.
     *
     * @author KamDziok
     * @return lista aktywnych Ankiet, jeśli rozmiar listy wynosi 0 znaczy, że nie ma dostępnych ankiet.
     */
    public List<Ankiety> selectAllActiveAnkiety(){
        List<Ankiety> ankiety = new ArrayList<>();
        try{
            session = openSession();
            Date date = new Date();
            ankiety = session
                    .createQuery("from Ankiety AS a where :date between a.dataRozpoczecia and a.dataZakonczenia")
                    .setParameter("date", date)
                    .list();
        }catch(Exception e){
            logException(e);
        }finally {
            sessionClose(session);
        }
        return ankiety;
    }

    /**
     * Metoda pobierze z bazy wszystkie {@link Pytania pytania} wraz z mozliwymi {@link Odpowiedzi odpowiedziami}.
     * {@link Pytania Pytania} i {@link Odpowiedzi odpowiedzi} sa przechowywane jako {@link Object}, aby je odczytac,
     * nalezy je zrzutowac na konkretny typ.
     *
     * @author KamDziok
     * @param ankiety Podajemy ankiete, do której chcemy otrzymac pytania i mozliwe odpowiedzi.
     * @return Obiekt {@link Ankiety ankiet} wraz ze zbiorami {@link Pytania pytan} i {@link Odpowiedzi odpowiedziami}, w przeciwnym wypadku obiekt Ankiety z pustym zbiorem Pytanias.
     */
    public Ankiety selectAnkietaWithPytaniaAndOdpowiedziByAnkiety(Ankiety ankiety){
        try{
            PytaniaQuery pq = new PytaniaQuery();
            List<Integer> idPytaniaList = pq.selectListIdPytaniaByIdAnkiety(ankiety.getIdAnkiety());
            OdpowiedziQuery oq = new OdpowiedziQuery();
            ankiety.initHashSetPytania();
            for(int idPytania : idPytaniaList) {
                Pytania pytanie = pq.selectByID(idPytania);
                if (pytanie.getRodzajPytania() != TypeOfQuestion.OPEN) {
                    List<Integer> idOdpowiedziList = oq.selectSetOdpowiedziByIdPytania(idPytania);
                    pytanie.initHashSetOdpowiedzi();
                    for (int idOdpowiedzi : idOdpowiedziList) {
                        Odpowiedzi odpowiedzi = oq.selectByID(idOdpowiedzi);
                        pytanie.getOdpowiedzis().add(odpowiedzi);
                    }
                }
                ankiety.getPytanias().add(pytanie);
            }
        }catch(Exception e){
            logException(e);
        }
        return ankiety;
    }



}
