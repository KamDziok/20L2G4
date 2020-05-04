package com.Ankiety_PZ.query;

import com.Ankiety_PZ.hibernate.Ankiety;
import com.Ankiety_PZ.hibernate.Odpowiedzi;
import com.Ankiety_PZ.hibernate.Pytania;

import java.util.ArrayList;
import java.util.Date;
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

    public Ankiety selectById(Integer id){
        Ankiety ankiety = null;
        try{
            session = openSession();
            String hgl = "from Ankiety where ID = " + id;
            query = session.createQuery(hgl);
            ankiety = (Ankiety) query.uniqueResult();
        }catch(Exception e){
            logException(e);
        }finally {
            sessionClose(session);
        }
        return ankiety;
    }

    public Boolean addAnkiety(Ankiety ankiety){
        return modifyAnkiety(ankiety, true, false, false);
    }

    public Boolean updateSnkiety(Ankiety ankiety){
        return modifyAnkiety(ankiety, false, true, false);
    }

    public Boolean deleteAnkiety(Ankiety ankiety){
        return modifyAnkiety(ankiety, false, false, true);
    }

    private Boolean modifyAnkiety(Ankiety ankiety, boolean add, boolean update, boolean delete){
        Boolean result = false;
        try{
            session = openSession();
            transaction = beginTransaction(session);;
            if(add){
                session.save(ankiety);
            }
            if(update){
                session.update(ankiety);
            }
            if(delete) {
                session.delete(ankiety);
            }
            commitTransaction(transaction);
            result = true;
        }catch(Exception e){
            transactionRollback(transaction);
            logException(e);
        }finally {
            sessionClose(session);
        }
        return result;
    }

    public Boolean addAnkietyWithPytaniaAndOdpowiedzi(Ankiety ankiety){
        Boolean result = false;
        try{
            PytaniaQuery pytaniaQuery = new PytaniaQuery();
            OdpowiedziQuery odpowiedziQuery = new OdpowiedziQuery();
            session = openSession();
            transaction = beginTransaction(session);;
            addAnkiety(ankiety);
            for(Object pytaniaObj : ankiety.getPytanias()){
                Pytania pytania = (Pytania) pytaniaObj;
                pytaniaQuery.addPytania(pytania);
                for(Object odpowiedziObj : pytania.getOdpowiedzis()){
                    Odpowiedzi odpowiedzi = (Odpowiedzi) odpowiedziObj;
                    odpowiedziQuery.addOdpoweidz(odpowiedzi);
                }
            }
        }catch(Exception e){
            transactionRollback(transaction);
            logException(e);
        }finally {
            sessionClose(session);
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
            sessionClose(session);
        }
        return ankiety;
    }



}
