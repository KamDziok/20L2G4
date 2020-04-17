/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Ankiety_PZ.query;

import com.Ankiety_PZ.hibernate.Nagrody;
import org.hibernate.*;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author KamDziok
 */
public class NagrodyQuery extends OperationInSession{
    
    public List<Nagrody> selesctAll() throws HibernateException {
        List<Nagrody> nagrody = new ArrayList<>();
        try {
            session = openSession();
            criteria = session.createCriteria(Nagrody.class);
            nagrody = criteria.list();
        } catch(Exception e){
            logException(e);
        }finally{
            sessionClose(session);
        }
        return nagrody;
    }

    public Nagrody selectByID(int id){
        Nagrody nagroda = new Nagrody();
        try{
            session = openSession();
            String hgl = "from Nagrody where ID = " + id;
            query = session.createQuery(hgl);
            nagroda = (Nagrody) query.uniqueResult();
        }catch(Exception e){
            logException(e);
        }finally {
            sessionClose(session);
        }
        return nagroda;
    }

    public Boolean addNagrody(Nagrody nagroda){
        Boolean result = false;
        try{
            session = openSession();
            transaction = beginTransaction(session);
            session.save(nagroda);
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

    public Boolean updateNagrody(Nagrody nagroda){
        Boolean result = false;
        try{
            session = openSession();
            transaction = beginTransaction(session);;
            session.update(nagroda);
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

    public Boolean delNagrody(Nagrody nagroda){
        Boolean result = false;
        try{
            session = openSession();
            transaction = beginTransaction(session);;
            session.delete(nagroda);
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
