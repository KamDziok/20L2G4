/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Ankiety_PZ.query;

import com.Ankiety_PZ.hibernate.Nagrody;
import com.Ankiety_PZ.hibernate.Pytania;
import org.hibernate.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Klasa zawiera metody do przesyłu danych z bazą danych dla tabeli Nagrody.
 *
 * @author KamDziok
 */
public class NagrodyQuery extends OperationInSession{

    private OperationsOnDataInEntity<Nagrody> modifyNagrody;

    public NagrodyQuery(){
        this.modifyNagrody = new OperationsOnDataInEntity<>();
    }
    
    public List<Nagrody> selectAll() throws HibernateException {
        List<Nagrody> nagrody = new ArrayList<>();
        try {
            session = openSession();
            criteria = session.createCriteria(Nagrody.class);
            nagrody = criteria.list();
        } catch(Exception e){
            logException(e);
        }finally{
            closeSession(session);
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
            closeSession(session);
        }
        return nagroda;
    }

    public List<Nagrody> selectAllActive() {
        List<Nagrody> nagrody = new ArrayList<>();
        try {
            session = openSession();
            nagrody = session.createQuery("from Nagrody as n where n.liczbaPunktow>=0").list();
        } catch(Exception e){
            logException(e);
        }finally{
            closeSession(session);
        }
        return nagrody;
    }

    public Boolean addNagrody(Nagrody nagrody){
        return modifyNagrody.add(nagrody);
    }

    Boolean addNagrodyWithOutTransaction(Nagrody nagrody, Session session){
        if(session == null){
            session = openSession();
        }
        return modifyNagrody.addWithOutTransaction(nagrody, session);
    }

    public Boolean updateNagrody(Nagrody nagrody){
        return modifyNagrody.update(nagrody);
    }

    Boolean updateNagrodyWithOutTransaction(Nagrody nagrody, Session session){
        if(session == null){
            session = openSession();
        }
        return modifyNagrody.updateWithOutTransaction(nagrody, session);
    }

    public Boolean deleteNagrody(Nagrody nagrody){
        return modifyNagrody.delete(nagrody);
    }

    Boolean deleteNagrodyWithOutTransaction(Nagrody nagrody, Session session){
        if(session == null){
            session = openSession();
        }
        return modifyNagrody.deleteWithOutTransaction(nagrody, session);
    }

    public Boolean deactivateNagrody(Nagrody nagrody){
        nagrody.setLiczbaPunktow(-1);
        //sprawdzic czy istnieje w uzytkownicy_nagrody
        return updateNagrody(nagrody);
    }
}
