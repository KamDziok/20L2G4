package com.Ankiety_PZ.query;

import com.Ankiety_PZ.hibernate.OdpowiedziUzytkownicy;
import org.hibernate.HibernateException;

import java.util.ArrayList;
import java.util.List;

public class OdpowiedziUzytkownicyQuery extends OperationInSession {

    public List<OdpowiedziUzytkownicy> selectAll() throws HibernateException {
        List<OdpowiedziUzytkownicy> odpU = new ArrayList<>();
        try {
            session = openSession();
            criteria = session.createCriteria(OdpowiedziUzytkownicy.class);
            odpU = criteria.list();
        } catch(Exception e){
            logException(e);
        }finally{
            sessionClose(session);
        }
        return odpU;
    }

    public Boolean addOdpowiedzUzytkownicy(OdpowiedziUzytkownicy odpU){
        Boolean result = false;
        try{
            session = openSession();
            transaction = beginTransaction(session);
            session.save(odpU);
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
