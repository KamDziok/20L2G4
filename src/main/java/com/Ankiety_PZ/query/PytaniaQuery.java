package com.Ankiety_PZ.query;
import com.Ankiety_PZ.hibernate.Pytania;
import org.hibernate.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class PytaniaQuery extends OperationInSession {

    public List<Pytania> selectAll() throws HibernateException {
        List<Pytania> pytania = new ArrayList<>();
        try {
            session = openSession();
            criteria = session.createCriteria(Pytania.class);
            pytania = criteria.list();
        } catch(Exception e){
            logException(e);
        }finally{
            sessionClose(session);
        }
        return pytania;
    }

    public Boolean addPytania(Pytania pytanie){
        return modifyPytania(pytanie, true, false,false);
    }

    public Boolean updatePytania(Pytania pytanie){
        return modifyPytania(pytanie, false, true, false);
    }

    public Boolean delPytania(Pytania pytania){
        return modifyPytania(pytania, false, false, true);
    }

    private Boolean modifyPytania(Pytania pytania, boolean add, boolean update, boolean delete){
        Boolean result = false;
        try{
            session = openSession();
            transaction = beginTransaction(session);;
            if(add){
                session.save(pytania);
            }
            if(update){
                session.update(pytania);
            }
            if(delete) {
                session.delete(pytania);
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

    /**
     * Metoda zwraca liste idPytan dla konkretnej ankiety.
     *
     * @author KamDziok
     * @param idAnkiety Identyfikator ankiety, której pytań szukamy.
     * @return List idPytania dla konkretnej Ankiety, w przeciwnym wypadku null.
     */
    public List<Integer> selectListIdPytaniaByIdAnkiety(Integer idAnkiety){
        List<Integer> pytania = new ArrayList<>();
        try{
            session = openSession();
            pytania =  session
                    .createQuery("select p.idPytania from Pytania as p " +
                            "inner join p.ankiety as a " +
                    "where a.idAnkiety=:id")
                    .setParameter("id", idAnkiety)
                    .list();
        }catch(Exception e){
            logException(e);
        }finally {
            sessionClose(session);
        }
        return pytania;
    }

}
