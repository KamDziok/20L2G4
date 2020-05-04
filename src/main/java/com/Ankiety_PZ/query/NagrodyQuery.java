/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Ankiety_PZ.query;

import com.Ankiety_PZ.hibernate.Nagrody;
import com.Ankiety_PZ.hibernate.Uzytkownicy;
import org.hibernate.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Klasa zawiera metody do przesyłu danych z bazą danych dla tabeli Nagrody.
 *
 * @author KamDziok
 */
public class NagrodyQuery extends OperationInSession{
    
    public List<Nagrody> selectAll() throws HibernateException {
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
        return modifyNagrody(nagroda, true, false, false);
    }

    public Boolean updateNagrody(Nagrody nagroda){
        return modifyNagrody(nagroda, false, true, false);
    }

    public Boolean delNagrody(Nagrody nagroda){
        return modifyNagrody(nagroda, false, false, true);
    }

    private Boolean modifyNagrody(Nagrody nagrody, boolean add, boolean update, boolean delete){
        Boolean result = false;
        try{
            session = openSession();
            transaction = beginTransaction(session);;
            if(add){
                session.save(nagrody);
            }
            if(update){
                session.update(nagrody);
            }
            if(delete) {
                session.delete(nagrody);
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

    public Boolean deactivateNagrody(Nagrody nagrody){
        nagrody.setLiczbaPunktow(-1);
        //sprawdzic czy istnieje w uzytkownicy_nagrody
        return updateNagrody(nagrody);
    }
}
