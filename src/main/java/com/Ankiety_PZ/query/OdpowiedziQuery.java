package com.Ankiety_PZ.query;

import com.Ankiety_PZ.hibernate.Odpowiedzi;
import org.hibernate.HibernateException;

import java.util.ArrayList;
import java.util.List;

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
        Boolean result = false;
        try{
            session = openSession();
            transaction = beginTransaction(session);
            session.save(odpowiedzi);
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

    public Boolean updateOdpowiedzi(Odpowiedzi odpowiedzi){
        Boolean result = false;
        try{
            session = openSession();
            transaction = beginTransaction(session);;
            session.update(odpowiedzi);
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

    public Boolean delOdpowiedzi(Odpowiedzi odpowiedzi){
        Boolean result = false;
        try{
            session = openSession();
            transaction = beginTransaction(session);;
            session.delete(odpowiedzi);
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
}
