package com.Ankiety_PZ.query;
import com.Ankiety_PZ.hibernate.Ankiety;
import com.Ankiety_PZ.hibernate.Odpowiedzi;
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


    public Boolean addPytania(Pytania pytania){
        return modifyPytania.add(pytania);
    }

    Boolean addPytaniaWithOutTransaction(Pytania pytania, Session session){
        if(session == null){
            session = openSession();
        }
        return modifyPytania.addWithOutTransaction(pytania, session);
    }

    public Boolean updatePytania(Pytania pytania){
        return modifyPytania.update(pytania);
    }

    Boolean updatePytaniaWithOutTransaction(Pytania pytania, Session session){
        if(session == null){
            session = openSession();
        }
        return modifyPytania.updateWithOutTransaction(pytania, session);
    }

    public Boolean deletePytania(Pytania pytania){
        return modifyPytania.delete(pytania);
    }

    Boolean deletePytaniaWithOutTransaction(Pytania pytania, Session session){
        if(session == null){
            session = openSession();
        }
        return modifyPytania.deleteWithOutTransaction(pytania, session);
    }

    /**
     * Metoda zwraca liste idPytan dla konkretnej ankiety.
     *
     * @author KamDziok
     * @param idAnkiety Identyfikator ankiety, której pytań szukamy.
     * @return List idPytania dla konkretnej Ankiety, w przeciwnym wypadku null.
     */
    //do usunięcia
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

    public List<Pytania> selectListPytaniaByIdAnkiety(Ankiety ankiety){
        List<Pytania> pytania = new ArrayList<>();
        try{
            session = openSession();
            pytania =  session
                    .createQuery("select p from Pytania as p " +
                            "inner join p.ankiety as a " +
                            "where a.idAnkiety=:id")
                    .setParameter("id", ankiety.getIdAnkiety())
                    .list();
        }catch(Exception e){
            logException(e);
        }finally {
            closeSession(session);
        }
        return pytania;
    }
}
