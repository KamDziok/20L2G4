package com.Ankiety_PZ.query;

import com.Ankiety_PZ.hibernate.Odpowiedzi;
import org.hibernate.HibernateException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class OdpowiedziQuery extends OperationInSession {

    public List<Odpowiedzi> selestAll() throws HibernateException {
        List<Odpowiedzi> odpowiedzi = new ArrayList<>();
        try {
            session = openSession();
            criteria = session.createCriteria(Odpowiedzi.class);
            odpowiedzi = criteria.list();
        } catch(Exception e){
            logException(e);
        }finally{
            sessionClose(session);
        }
        return odpowiedzi;
    }
    public Boolean addOdpoweidz(Odpowiedzi odpowiedzi){
        return modifyOdpowiedzi(odpowiedzi, true, false, false);
    }

    public Boolean updateOdpowiedzi(Odpowiedzi odpowiedzi){
        return modifyOdpowiedzi(odpowiedzi, false, true, false);
    }

    public Boolean delOdpowiedzi(Odpowiedzi odpowiedzi){
        return modifyOdpowiedzi(odpowiedzi, false, false, true);
    }

    private Boolean modifyOdpowiedzi(Odpowiedzi odpowiedzi, boolean add, boolean update, boolean delete){
        Boolean result = false;
        try{
            session = openSession();
            transaction = beginTransaction(session);;
            if(add){
                session.save(odpowiedzi);
            }
            if(update){
                session.update(odpowiedzi);
            }
            if(delete) {
                session.delete(odpowiedzi);
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
     * Metoda zwraca liste mozliwych idOdpowiedzi dla konkretnej pytania.
     *
     * @author KamDziok
     * @param idPytania Identyfikator pytania, których możliwych odpowiedzi szukamy.
     * @return List idOdpowiedzia dla konkretnej Pytania, w przeciwnym wypadku null.
     */
    public List<Integer> selectSetOdpowiedziByIdPytania(Integer idPytania){
        List<Integer> odpowiedzi = new ArrayList<>();
        try{
            session = openSession();
            odpowiedzi = session
                    .createQuery("select o.idOdpowiedzi from Odpowiedzi as o " +
                            "inner join o.pytania as p " +
                            "where p.idPytania=:id")
                    .setParameter("id", idPytania)
                    .list();
        }catch(Exception e){
            logException(e);
        }finally {
            sessionClose(session);
        }
        return odpowiedzi;
    }
}
