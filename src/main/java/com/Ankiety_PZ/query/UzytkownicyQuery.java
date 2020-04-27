package com.Ankiety_PZ.query;

import com.Ankiety_PZ.hibernate.Uzytkownicy;
import org.hibernate.HibernateException;

import java.util.ArrayList;
import java.util.List;

public class UzytkownicyQuery extends OperationInSession {

    public List<Uzytkownicy> selectAll() throws HibernateException {
        List<Uzytkownicy> uzytkowink = new ArrayList<>();
        try {
            session = openSession();
            criteria = session.createCriteria(Uzytkownicy.class);
            uzytkowink = criteria.list();
        } catch(Exception e){
            logException(e);
        }finally{
            sessionClose(session);
        }
        return uzytkowink;
    }

    public Boolean addUzytkownik(Uzytkownicy uzytkownik){
        Boolean result = false;
        try{
            session = openSession();
            transaction = beginTransaction(session);
            session.save(uzytkownik);
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

    public Boolean updateUzytkownik(Uzytkownicy uzytkownik){
        Boolean result = false;
        try{
            session = openSession();
            transaction = beginTransaction(session);;
            session.update(uzytkownik);
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

    public Boolean delUzytkownik(Uzytkownicy uzytkownik){
        Boolean result = false;
        try{
            session = openSession();
            transaction = beginTransaction(session);;
            session.delete(uzytkownik);
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

    public Uzytkownicy selectByMail(String mail){
        Uzytkownicy user = null;
        try{
            session = openSession();
            String hgl = "from Uzytkownicy where mail=\"" + mail + "\"";
            System.out.println(hgl);
            query = session.createQuery(hgl);
            user = (Uzytkownicy) query.uniqueResult();
        }catch(Exception e){
            logException(e);
        }finally {
            sessionClose(session);
        }
        return user;
    }

    public Uzytkownicy selectByMailAndPassword(String mail, String password){
        Uzytkownicy user = null;
        try{
            session = openSession();
            String hgl = "from Uzytkownicy where mail = '" + mail + "' and haslo ='" + password +"'";
            query = session.createQuery(hgl);
            user = (Uzytkownicy) query.uniqueResult();
        }catch(Exception e){
            logException(e);
        }finally {
            sessionClose(session);
        }
        return user;
    }
}
