package com.Ankiety_PZ.query;

import com.Ankiety_PZ.hibernate.Odpowiedzi;
import org.hibernate.HibernateException;

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

    public Boolean addOdpoweidz(Odpowiedzi odpowiedzi){
        return modifyOdpowiedzi.modifyDataInEntity(odpowiedzi, true, false, false, true);
    }

    Boolean addOdpoweidzWithOutTransaction(Odpowiedzi odpowiedzi){
        return modifyOdpowiedzi.modifyDataInEntity(odpowiedzi, true, false, false, false);
    }

    public Boolean updateOdpowiedzi(Odpowiedzi odpowiedzi){
        return modifyOdpowiedzi.modifyDataInEntity(odpowiedzi, false, true, false, true);
    }

    Boolean updateOdpowiedziWithOutTransaction(Odpowiedzi odpowiedzi){
        return modifyOdpowiedzi.modifyDataInEntity(odpowiedzi, false, true, false, false);
    }

    public Boolean delOdpowiedzi(Odpowiedzi odpowiedzi){
        return modifyOdpowiedzi.modifyDataInEntity(odpowiedzi, false, false, true, true);
    }

    Boolean delOdpowiedziWithOutTransaction(Odpowiedzi odpowiedzi){
        return modifyOdpowiedzi.modifyDataInEntity(odpowiedzi, false, false, true, false);
    }

    /**
     * Metoda zwraca liste mozliwych idOdpowiedzi dla konkretnej pytania.
     *
     * @author KamDziok
     * @param idPytania Identyfikator pytania, których możliwych odpowiedzi szukamy.
     * @return List idOdpowiedzia dla konkretnej Pytania, w przeciwnym wypadku null.
     */
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
}
