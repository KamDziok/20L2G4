package com.Ankiety_PZ.query;
import com.Ankiety_PZ.hibernate.Pytania;
import org.hibernate.*;

import java.util.ArrayList;
import java.util.List;


public class PytaniaQuery extends OperationInSession {

    private OperationsOnDataInEntity<Pytania> modifyPytania;

    public PytaniaQuery(){
        this.modifyPytania = new OperationsOnDataInEntity<>();
    }

    public List<Pytania> selectAll() throws HibernateException {
        List<Pytania> pytania = new ArrayList<>();
        try {
            session = openSession();
            criteria = session.createCriteria(Pytania.class);
            pytania = criteria.list();
        } catch(Exception e){
            logException(e);
        }finally{
            closeSession(session);
        }
        return pytania;
    }

    public Pytania selectByID(int id){
        Pytania pytania = new Pytania();
        try{
            session = openSession();
            String hgl = "from Pytania where ID = " + id;
            query = session.createQuery(hgl);
            pytania = (Pytania) query.uniqueResult();
        }catch(Exception e){
            logException(e);
        }finally {
            closeSession(session);
        }
        return pytania;
    }


    public Boolean addPytania(Pytania pytanie){
        return modifyPytania.modifyDataInEntity(pytanie, true, false,false, true);
    }

    Boolean addPytaniaWithOutTransaction(Pytania pytanie){
        return modifyPytania.modifyDataInEntity(pytanie, true, false,false, false);
    }

    public Boolean updatePytania(Pytania pytanie){
        return modifyPytania.modifyDataInEntity(pytanie, false, true, false, true);
    }

    Boolean updatePytaniaWithOutTransaction(Pytania pytanie){
        return modifyPytania.modifyDataInEntity(pytanie, false, true, false, false);
    }

    public Boolean delPytania(Pytania pytania){
        return modifyPytania.modifyDataInEntity(pytania, false, false, true, true);
    }

    Boolean delPytaniaWithOutTransaction(Pytania pytania){
        return modifyPytania.modifyDataInEntity(pytania, false, false, true, false);
    }

    /**
     * Metoda zwraca liste idPytan dla konkretnej ankiety.
     *
     * @author KamDziok
     * @param idAnkiety Identyfikator ankiety, której pytań szukamy.
     * @return List idPytania dla konkretnej Ankiety, w przeciwnym wypadku null.
     */
    public List<Integer> selectListIdPytaniaByIdAnkiety(Integer idAnkiety){
        List<Integer> pytania = new ArrayList<>();
        try{
            session = openSession();
            pytania =  session
                    .createQuery("select p.idPytania from Pytania as p " +
                            "inner join p.ankiety as a " +
                    "where a.idAnkiety=:id")
                    .setParameter("id", idAnkiety)
                    .list();
        }catch(Exception e){
            logException(e);
        }finally {
            closeSession(session);
        }
        return pytania;
    }

}
