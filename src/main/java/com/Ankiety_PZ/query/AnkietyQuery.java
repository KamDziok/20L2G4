
package com.Ankiety_PZ.query;

import com.Ankiety_PZ.hibernate.Ankiety;
import com.Ankiety_PZ.hibernate.Odpowiedzi;
import com.Ankiety_PZ.hibernate.Pytania;
import com.Ankiety_PZ.hibernate.Uzytkownicy;
import com.Ankiety_PZ.test.TypeOfQuestion;
import com.Ankiety_PZ.query.OperationsOnDataInEntity;
import org.hibernate.Session;

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
        return modifyAnkiety.selectListHQL("from Ankiety");
    }

    public Ankiety selectById(Integer id){
        return modifyAnkiety.selectObjectHQL(("from Ankiety where ID=" + id) );
    }

    public Boolean addAnkiety(Ankiety ankiety){
        return modifyAnkiety.add(ankiety);
    }

    Boolean addAnkietyWithOutTransaction(Ankiety ankiety, Session session){
        return modifyAnkiety.addWithOutTransaction(ankiety, session);
    }

    public Boolean updateAnkiety(Ankiety ankiety){
        return modifyAnkiety.update(ankiety);
    }

    Boolean updateAnkietyWithOutTransaction(Ankiety ankiety, Session session){
        return modifyAnkiety.updateWithOutTransaction(ankiety, session);
    }

    public Boolean deleteAnkiety(Ankiety ankiety){
        return modifyAnkiety.delete(ankiety);
    }

    Boolean deleteAnkietyWithOutTransaction(Ankiety ankiety, Session session){
        return modifyAnkiety.deleteWithOutTransaction(ankiety, session);
    }

    public Boolean addAnkietyWithPytaniaAndOdpowiedzi(Ankiety ankiety){
        Boolean result = false;
        try{
            PytaniaQuery pytaniaQuery = new PytaniaQuery();
            OdpowiedziQuery odpowiedziQuery = new OdpowiedziQuery();
            session = openSession();
            transaction = beginTransaction(session);;
            addAnkietyWithOutTransaction(ankiety, session);
            ankiety.getPytanias().forEach(pytaniaObj -> {
                Pytania pytania = (Pytania) pytaniaObj;
                pytaniaQuery.addPytaniaWithOutTransaction(pytania, session);
                if(pytania.getRodzajPytania() != TypeOfQuestion.OPEN) {
                    pytania.getOdpowiedzis().forEach(odpowiedziObj -> {
                        Odpowiedzi odpowiedzi = (Odpowiedzi) odpowiedziObj;
                        odpowiedziQuery.addOdpowiedziWithOutTransaction(odpowiedzi, session);
                    });
                }
            });
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

    public Boolean updateAnkietyWithPytaniaAndOdpowiedzi(Ankiety ankiety){
        Boolean result = false;
        if(ankiety.getIdAnkiety() != null) {
            try {
                PytaniaQuery pytaniaQuery = new PytaniaQuery();
                OdpowiedziQuery odpowiedziQuery = new OdpowiedziQuery();
                session = openSession();
                transaction = beginTransaction(session);
                Ankiety oldAnkieta = selectById(ankiety.getIdAnkiety());
                if (!ankiety.isTheSame(oldAnkieta)) {
                    updateAnkietyWithOutTransaction(ankiety, session);
                }
                ankiety.getPytanias().forEach(pytaniaObj -> {
                    Pytania pytania = (Pytania) pytaniaObj;
                    if(pytania.getIdPytania().intValue() != -1){
                        Pytania oldPytania = pytaniaQuery.selectByID(pytania.getIdPytania());
                        if(!pytania.isTheSame(oldPytania)) {
                            pytaniaQuery.updatePytaniaWithOutTransaction(pytania, session);
                        }
                        if(pytania.getRodzajPytania() != TypeOfQuestion.OPEN) {
                            pytania.getOdpowiedzis().forEach(odpowiedziObj -> {
                                Odpowiedzi odpowiedzi = (Odpowiedzi) odpowiedziObj;
                                if (odpowiedzi.getIdOdpowiedzi().intValue() != -1) {
                                    Odpowiedzi oldOdpowiedzi = odpowiedziQuery.selectByID(odpowiedzi.getIdOdpowiedzi());
                                    if (!odpowiedzi.isTheSame(oldOdpowiedzi)) {
                                        odpowiedziQuery.updateOdpowiedziWithOutTransaction(odpowiedzi, session);
                                    }
                                } else {
                                    odpowiedziQuery.addOdpowiedziWithOutTransaction(odpowiedzi, session);
                                }
                            });
                        }
                    }else {
                        Pytania newPytanie = new Pytania(ankiety, pytania.getTresc(), pytania.getZdjecie(), pytania.getRodzajPytania(), pytania.getPunktowe());
                        pytaniaQuery.addPytaniaWithOutTransaction(newPytanie, session);
                        if(pytania.getRodzajPytania() != TypeOfQuestion.OPEN) {
                            pytania.getOdpowiedzis().forEach(odpowiedziObj -> {
                                Odpowiedzi odpowiedzi = (Odpowiedzi) odpowiedziObj;
                                odpowiedzi.setPytania(newPytanie);
                                odpowiedziQuery.addOdpowiedziWithOutTransaction(odpowiedzi, session);
                            });
                        }
                    }
                });
                commitTransaction(transaction);
                result = true;
            } catch (Exception e) {
                rollbackTransaction(transaction);
                logException(e);
            } finally {
                closeSession(session);
            }
        }
        return result;
    }

    public Boolean deletePytaniaAndOdpowiedziInAnkiety(List<Pytania> pytaniaList, List<Odpowiedzi> odpowiedziList){
        Boolean result = false;
        try{
            session = openSession();
            transaction = beginTransaction(session);
            new OdpowiedziQuery().deleteListOdpowiedzi(odpowiedziList, session);
            new PytaniaQuery().deleteListPytania(pytaniaList, session);
            commitTransaction(transaction);
            result = true;
        }catch (Exception e){
            rollbackTransaction(transaction);
            logException(e);
        }finally {
            closeSession(session);
        }
        return result;
    }

    public List<Ankiety> selectAllUzytkownik(Uzytkownicy user){
        return modifyAnkiety.selectListHQL(("from Ankiety AS a where a.uzytkownicy.idUzytkownika=" + user.getIdUzytkownika()));
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
            List<Pytania> pytaniaList = pq.selectListPytaniaByIdAnkiety(ankiety);
            OdpowiedziQuery oq = new OdpowiedziQuery();
            ankiety.initHashSetPytania();
            pytaniaList.forEach(pytanie -> {
                if (pytanie.getRodzajPytania() != TypeOfQuestion.OPEN) {
                    List<Odpowiedzi> odpowiedziList = oq.selectSetOdpowiedziByIdPytania(pytanie);
                    pytanie.initHashSetOdpowiedzi();
                    odpowiedziList.forEach(odpowiedzi -> {
                        pytanie.getOdpowiedzis().add(odpowiedzi);
                    });
                }
                ankiety.getPytanias().add(pytanie);
            });
        }catch(Exception e){
            logException(e);
        }
        return ankiety;
    }

    //dobre
    public List<Ankiety> selectAllActiveAndNotDoAnkiety(Uzytkownicy user){
        List<Ankiety> ankiety = new ArrayList<>();
        List<Integer> idAnkietyList = new ArrayList<>();
        try{
            session = openSession();
            Date date = new Date();
            idAnkietyList = (ArrayList<Integer>) session
                    .createSQLQuery("SELECT ao.ID FROM ankiety AS ao WHERE ao.id not in " +
                            "( select distinct a.ID from ankiety AS a " +
                            "inner join pytania as p ON a.ID=p.ID_ankiety " +
                            "inner join odpowiedzi as o ON p.ID=o.ID_pytania " +
                            "inner join odpowiedzi_uzytkownicy as ou ON o.ID=ou.ID_odpowiedzi " +
                            "WHERE ou.ID_uzytkownika=:id) " +
                            "AND :date between ao.data_rozpoczecia and ao.data_zakonczenia")
                    .setParameter("date", date)
                    .setParameter("id", user.getIdUzytkownika())
                    .list();
            idAnkietyList.forEach(idAnkiety -> {
                ankiety.add(selectById(idAnkiety));
            });
        }catch(Exception e){
            logException(e);
        }finally {
            closeSession(session);
        }
        return ankiety;
    }

    public Ankiety selectToAnalysis(Ankiety ankiety){
        try{
            PytaniaQuery pytaniaQuery = new PytaniaQuery();
            List<Pytania> pytaniaList = pytaniaQuery.selectListPytaniaByIdAnkiety(ankiety);
            OdpowiedziQuery odpowiedziQuery = new OdpowiedziQuery();
            ankiety.initHashSetPytania();
            pytaniaList.forEach(pytanie -> {
                if (pytanie.getRodzajPytania() != TypeOfQuestion.OPEN) {
                    List<Odpowiedzi> odpowiedziList = odpowiedziQuery.selectSetOdpowiedziByIdPytania(pytanie);
                    pytanie.initHashSetOdpowiedzi();
                    odpowiedziList.forEach(odpowiedzi -> {
                        if(pytanie.getRodzajPytania() == TypeOfQuestion.PERCENT || pytanie.getRodzajPytania() == TypeOfQuestion.POINTS) {
                            odpowiedzi.initOdpowiedziUzytkownicy();
                            odpowiedzi.setOdpowiedziUzytkownicy(odpowiedziQuery.selectOdpowiedziPointsAndPercent(odpowiedzi));
                        }else {
                            odpowiedzi.setCount(odpowiedziQuery.selectCountOdpowiedzi(odpowiedzi).intValue());
                        }
                        pytanie.getOdpowiedzis().add(odpowiedzi);
                    });
                }else{
                    pytanie.setPytaniaUzytkownicy(pytaniaQuery.selectPytaniaUzytkownicy(pytanie));
                }
                ankiety.getPytanias().add(pytanie);
            });
        }catch(Exception e){
            logException(e);
        }
        return ankiety;
    }

}