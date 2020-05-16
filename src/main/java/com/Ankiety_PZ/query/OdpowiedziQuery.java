package com.Ankiety_PZ.query;

import com.Ankiety_PZ.hibernate.Ankiety;
import com.Ankiety_PZ.hibernate.Odpowiedzi;
import com.Ankiety_PZ.hibernate.OdpowiedziUzytkownicy;
import com.Ankiety_PZ.hibernate.Pytania;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class OdpowiedziQuery extends OperationInSession {

    private OperationsOnDataInEntity<Odpowiedzi> modifyOdpowiedzi;

    public OdpowiedziQuery(){
        modifyOdpowiedzi = new OperationsOnDataInEntity<>();
    }

    public List<Odpowiedzi> selestAll() throws HibernateException {
        return modifyOdpowiedzi.selectListHQL(("from Odpowiedzi"));
    }

    public Odpowiedzi selectByID(int id){
        return modifyOdpowiedzi.selectObjectHQL(("from Odpowiedzi where id=" + id));
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
        return modifyOdpowiedzi.selectListHQL(
                            ("select o from Odpowiedzi as o " +
                            "inner join o.pytania as p " +
                            "where p.idPytania=" + pytania.getIdPytania()));
    }

    public List<OdpowiedziUzytkownicy> selectOdpowiedziPointsAndPercent(Odpowiedzi odpowiedzi){
        List<OdpowiedziUzytkownicy> odpowiedziUzytkownicyList = new ArrayList<>();
        List<Integer> points = (ArrayList<Integer>) new OperationsOnDataInEntity<Integer>().selectListSQL(
                "SELECT `punktowe` FROM `odpowiedzi_uzytkownicy` WHERE `ID_odpowiedzi`=" + odpowiedzi.getIdOdpowiedzi());
        points.forEach(point -> {
            OdpowiedziUzytkownicy odpowiedziUzytkownicy = new OdpowiedziUzytkownicy();
            odpowiedziUzytkownicy.setOdpowiedz(odpowiedzi);
            odpowiedziUzytkownicy.setPunktowe(point);
            odpowiedziUzytkownicyList.add(odpowiedziUzytkownicy);
        });
        return odpowiedziUzytkownicyList;
    }

    public BigInteger selectCountOdpowiedzi(Odpowiedzi odpowiedzi){
        return (BigInteger) new OperationsOnDataInEntity<BigInteger>().selectObjectSQL(
                "SELECT count(*) FROM `odpowiedzi_uzytkownicy` WHERE ID_odpowiedzi=" + odpowiedzi.getIdOdpowiedzi());
    }
}
