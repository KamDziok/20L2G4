package com.Ankiety_PZ.query;

import com.Ankiety_PZ.hibernate.OdpowiedziUzytkownicy;
import org.hibernate.HibernateException;

import java.util.ArrayList;
import java.util.List;

public class OdpowiedziUzytkownicyIdQuery extends OperationInSession {

    public List<OdpowiedziUzytkownicyId> selectAll() throws HibernateException {
        List<OdpowiedziUzytkownicyId> odpUi = new ArrayList<>();
        try {
            session = openSession();
            criteria = session.createCriteria(OdpowiedziUzytkownicyId.class);
            odpUi = criteria.list();
        } catch(Exception e){
            logException(e);
        }finally{
            sessionClose(session);
        }
        return odpUi;
    }

    public Boolean addOdpowiedzUzytkownicyId(OdpowiedziUzytkownicy odpUi){
        Boolean result = false;
        try{
            session = openSession();
            transaction = beginTransaction(session);
            session.save(odpUi);
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
