package com.Ankiety_PZ.query;
import com.Ankiety_PZ.hibernate.Pytania;
import org.hibernate.*;

import java.util.ArrayList;
import java.util.List;


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
        Boolean result = false;
        try{
            session = openSession();
            transaction = beginTransaction(session);
            session.save(pytanie);
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

    public Boolean updatePytania(Pytania pytanie){
        Boolean result = false;
        try{
            session = openSession();
            transaction = beginTransaction(session);;
            session.update(pytanie);
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

    public Boolean delPytania(Pytania pytania){
        Boolean result = false;
        try{
            session = openSession();
            transaction = beginTransaction(session);;
            session.delete(pytania);
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
