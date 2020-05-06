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

    public Boolean addNagrody(Nagrody nagroda){
        return modifyNagrody.modifyDataInEntity(nagroda, true, false, false, true);
    }

    Boolean addNagrodyWithOutTransaction(Nagrody nagroda){
        return modifyNagrody.modifyDataInEntity(nagroda, true, false, false, false);
    }

    public Boolean updateNagrody(Nagrody nagroda){
        return modifyNagrody.modifyDataInEntity(nagroda, false, true, false, true);
    }

    Boolean updateNagrodyWithOutTransaction(Nagrody nagroda){
        return modifyNagrody.modifyDataInEntity(nagroda, false, true, false, false);
    }

    public Boolean delNagrody(Nagrody nagroda){
        return modifyNagrody.modifyDataInEntity(nagroda, false, false, true, true);
    }

    Boolean delNagrodyWithOutTransaction(Nagrody nagroda){
        return modifyNagrody.modifyDataInEntity(nagroda, false, false, true, false);
    }

    public Boolean deactivateNagrody(Nagrody nagrody){
        nagrody.setLiczbaPunktow(-1);
        //sprawdzic czy istnieje w uzytkownicy_nagrody
        return updateNagrody(nagrody);
    }
}
