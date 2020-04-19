package com.Ankiety_PZ.query;

import com.Ankiety_PZ.hibernate.PytaniaUzytkownicyId;
import org.hibernate.HibernateException;

import java.util.ArrayList;
import java.util.List;

public class PytaniaUzytkownicyIdQuery  extends OperationInSession {


    public List<PytaniaUzytkownicyId> selectAll() throws HibernateException {
        List<PytaniaUzytkownicyId> pytUi = new ArrayList<>();
        try {
            session = openSession();
            criteria = session.createCriteria(PytaniaUzytkownicyId.class);
            pytUi = criteria.list();
        } catch(Exception e){
            logException(e);
        }finally{
            sessionClose(session);
        }
        return pytUi;
    }

    public Boolean addOdpowiedzUzytkownicyid(PytaniaUzytkownicyQuery pytUi){
        Boolean result = false;
        try{
            session = openSession();
            transaction = beginTransaction(session);
            session.save(pytUi);
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
