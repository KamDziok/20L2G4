package com.Ankiety_PZ.query;

import com.Ankiety_PZ.hibernate.Ankiety;
import com.Ankiety_PZ.hibernate.Odpowiedzi;
import com.Ankiety_PZ.hibernate.Pytania;
import com.Ankiety_PZ.hibernate.Uzytkownicy;
import com.Ankiety_PZ.test.TypeOfQuestion;
import com.Ankiety_PZ.query.OperationsOnDataInEntity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Klasa zawiera metody do przesyłu danych z bazą danych dla tabeli Ankiety.
 */

public class AnkietyQuery extends OperationInSession {

    private OperationsOnDataInEntity<Ankiety> modifyAnkiety;

    public AnkietyQuery(){
        this.modifyAnkiety = new OperationsOnDataInEntity<>();
    }

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
            closeSession(session);
        }
        return ankiety;
    }

    public Ankiety selectById(Integer id){
            return modifyAnkiety.selectObject(("from Ankiety where ID=" + id) );
    }

    public Boolean addAnkiety(Ankiety ankiety){
        return modifyAnkiety.modifyDataInEntity(ankiety, true, false, false, true);
    }

    Boolean addAnkietyWithOutTransaction(Ankiety ankiety){
        return modifyAnkiety.modifyDataInEntity(ankiety, true, false, false, false);
    }

    public Boolean updateSnkiety(Ankiety ankiety){
        return modifyAnkiety.modifyDataInEntity(ankiety, false, true, false, true);
    }

    Boolean updateSnkietyWithOutTransaction(Ankiety ankiety){
        return modifyAnkiety.modifyDataInEntity(ankiety, false, true, false, false);
    }

    public Boolean deleteAnkiety(Ankiety ankiety){
        return modifyAnkiety.modifyDataInEntity(ankiety, false, false, true, true);
    }

    Boolean deleteAnkietyWithOutTransaction(Ankiety ankiety){
        return modifyAnkiety.modifyDataInEntity(ankiety, false, false, true, false);
    }

    public Boolean addAnkietyWithPytaniaAndOdpowiedzi(Ankiety ankiety){
        Boolean result = false;
        try{
            PytaniaQuery pytaniaQuery = new PytaniaQuery();
            OdpowiedziQuery odpowiedziQuery = new OdpowiedziQuery();
            session = openSession();
            transaction = beginTransaction(session);;
            addAnkietyWithOutTransaction(ankiety);
            for(Object pytaniaObj : ankiety.getPytanias()){
                Pytania pytania = (Pytania) pytaniaObj;
                pytaniaQuery.addPytaniaWithOutTransaction(pytania);
                for(Object odpowiedziObj : pytania.getOdpowiedzis()){
                    Odpowiedzi odpowiedzi = (Odpowiedzi) odpowiedziObj;
                    odpowiedziQuery.addOdpoweidzWithOutTransaction(odpowiedzi);
                }
            }
            commitTransaction(transaction);
            result = true;
        }catch(Exception e){
            rollbackTransaction(transaction);
            logException(e);
        }finally {
            closeSession(session);
        }
        return result;
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
            closeSession(session);
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

    public List<Ankiety> selectAllActiveAndNotDoAnkiety(Uzytkownicy user){
        List<Ankiety> ankiety = new ArrayList<>();
        List<Integer> idAnkietyList = new ArrayList<>();
        try{
            session = openSession();
            Date date = new Date();
            idAnkietyList = session
                    .createSQLQuery("select distinct a.ID from ankiety AS a " +
                            "inner join pytania as p ON a.ID=p.ID_ankiety " +
                            "inner join odpowiedzi as o ON p.ID=o.ID_pytania " +
                            "inner join odpowiedzi_uzytkownicy as ou ON o.ID=ou.ID_odpowiedzi " +
                            "WHERE ou.ID_uzytkownika!=:id and :date BETWEEN a.data_rozpoczecia and a.data_zakonczenia")
                    .setParameter("date", date)
                    .setParameter("id", user.getIdUzytkownika())
                    .list();
            for(int idAnkiety : idAnkietyList){
                ankiety.add(selectById(idAnkiety));
            }
        }catch(Exception e){
            logException(e);
        }finally {
            closeSession(session);
        }
        return ankiety;
    }

}
