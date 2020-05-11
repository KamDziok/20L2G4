package com.Ankiety_PZ.query;

import com.Ankiety_PZ.hibernate.Ankiety;
import com.Ankiety_PZ.hibernate.Odpowiedzi;
import com.Ankiety_PZ.hibernate.Pytania;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class OdpowiedziQuery extends OperationInSession {

    private OperationsOnDataInEntity<Odpowiedzi> modifyOdpowiedzi;

    public OdpowiedziQuery(){
        modifyOdpowiedzi = new OperationsOnDataInEntity<>();
    }

    public List<Odpowiedzi> selestAll() throws HibernateException {
        List<Odpowiedzi> odpowiedzi = new ArrayList<>();
        try {
            session = openSession();
            criteria = session.createCriteria(Odpowiedzi.class);
            odpowiedzi = criteria.list();
        } catch(Exception e){
            logException(e);
        }finally{
            closeSession(session);
        }
        return odpowiedzi;
    }

    public Odpowiedzi selectByID(int id){
        Odpowiedzi odpowiedzi = new Odpowiedzi();
        try{
            session = openSession();
            String hgl = "from Odpowiedzi where id=" + id;
            query = session.createQuery(hgl);
            odpowiedzi = (Odpowiedzi) query.uniqueResult();
        }catch(Exception e){
            logException(e);
        }finally {
            closeSession(session);
        }
        return odpowiedzi;
    }

    public Boolean addOdpowiedzi(Odpowiedzi odpowiedzi){
        return modifyOdpowiedzi.add(odpowiedzi);
    }

    Boolean addOdpowiedziWithOutTransaction(Odpowiedzi odpowiedzi, Session session){
        if(session == null){
            session = openSession();
        }
        return modifyOdpowiedzi.addWithOutTransaction(odpowiedzi, session);
    }

    public Boolean updateOdpowiedzi(Odpowiedzi odpowiedzi){
        return modifyOdpowiedzi.update(odpowiedzi);
    }

    Boolean updateOdpowiedziWithOutTransaction(Odpowiedzi odpowiedzi, Session session){
        if(session == null){
            session = openSession();
        }
        return modifyOdpowiedzi.updateWithOutTransaction(odpowiedzi, session);
    }

    public Boolean deleteOdpowiedzi(Odpowiedzi odpowiedzi){
        return modifyOdpowiedzi.delete(odpowiedzi);
    }

    Boolean deleteOdpowiedziWithOutTransaction(Odpowiedzi odpowiedzi, Session session){
        if(session == null){
            session = openSession();
        }
        return modifyOdpowiedzi.deleteWithOutTransaction(odpowiedzi, session);
    }

    /**
     * Metoda zwraca liste mozliwych idOdpowiedzi dla konkretnej pytania.
     *
     * @author KamDziok
     * @param idPytania Identyfikator pytania, których możliwych odpowiedzi szukamy.
     * @return List idOdpowiedzia dla konkretnej Pytania, w przeciwnym wypadku null.
     */
    //do usunięcia
    public List<Integer> selectSetOdpowiedziByIdPytania(Integer idPytania){
        List<Integer> odpowiedzi = new ArrayList<>();
        try{
            session = openSession();
            odpowiedzi = session
                    .createQuery("select o.idOdpowiedzi from Odpowiedzi as o " +
                            "inner join o.pytania as p " +
                            "where p.idPytania=:id")
                    .setParameter("id", idPytania)
                    .list();
        }catch(Exception e){
            logException(e);
        }finally {
            closeSession(session);
        }
        return odpowiedzi;
    }

    public List<Odpowiedzi> selectSetOdpowiedziByIdPytania(Pytania pytania){
        List<Odpowiedzi> odpowiedzi = new ArrayList<>();
        try{
            session = openSession();
            odpowiedzi = session
                    .createQuery("select o from Odpowiedzi as o " +
                            "inner join o.pytania as p " +
                            "where p.idPytania=:id")
                    .setParameter("id", pytania.getIdPytania())
                    .list();
        }catch(Exception e){
            logException(e);
        }finally {
            closeSession(session);
        }
        return odpowiedzi;
    }
}
