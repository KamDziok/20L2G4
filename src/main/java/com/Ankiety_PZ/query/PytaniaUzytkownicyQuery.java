package com.Ankiety_PZ.query;

import com.Ankiety_PZ.hibernate.PytaniaUzytkownicy;
import org.hibernate.HibernateException;

import java.util.ArrayList;
import java.util.List;

public class PytaniaUzytkownicyQuery extends OperationInSession {


//    public List<PytaniaUzytkownicyQuery> selectAll() throws HibernateException {
//        List<PytaniaUzytkownicyQuery> pytU = new ArrayList<>();
//        try {
//            session = openSession();
//            criteria = session.createCriteria(PytaniaUzytkownicy.class);
//            pytU = criteria.list();
//        } catch(Exception e){
//            logException(e);
//        }finally{
//            closeSession(session);
//        }
//        return pytU;
//    }
//
//    public Boolean addOdpowiedzUzytkownicy(PytaniaUzytkownicyQuery pytU){
//        Boolean result = false;
//        try{
//            session = openSession();
//            transaction = beginTransaction(session);
//            session.save(pytU);
//            commitTransaction(transaction);
//            result = true;
//        }catch(Exception e){
//            rollbackTransaction(transaction);
//            logException(e);
//        }finally {
//            closeSession(session);
//        }
//        return result;
//    }
}
