package com.Ankiety_PZ.query;

import com.Ankiety_PZ.hibernate.OdpowiedziUzytkownicy;
import com.Ankiety_PZ.hibernate.Pytania;
import com.Ankiety_PZ.hibernate.Uzytkownicy;
import com.Ankiety_PZ.test.Permissions;
import com.Ankiety_PZ.test.TypeOfQuestion;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class UzytkownicyQuery extends OperationInSession {

    private OperationsOnDataInEntity<Uzytkownicy> modifyUzytkownik;

    public UzytkownicyQuery(){
        this.modifyUzytkownik = new OperationsOnDataInEntity<>();
    }

    public List<Uzytkownicy> selectAll() throws HibernateException {
        return modifyUzytkownik.selectListHQL(("from Uzytkownicy"));
//        List<Uzytkownicy> uzytkowink = new ArrayList<>();
//        try {
//            session = openSession();
//            criteria = session.createCriteria(Uzytkownicy.class);
//            uzytkowink = criteria.list();
//        } catch(Exception e){
//            logException(e);
//        }finally{
//            closeSession(session);
//        }
//        return uzytkowink;
    }

    /**
     * Odczyt pojedynczego użytkownika z bazy.
     *
     * @author KamDziok
     * @param id identyfikarot użytkownika.
     * @return obiekt Uzytkownicy jeśli istnieje w zazie użytkownika o podanym id, w przeciwnym wypadku null.
     */
    public Uzytkownicy selectById(int id){
        return modifyUzytkownik.selectObjectHQL(("from Uzytkownicy as u where u.idUzytkownika=" + id));
    }

    public Boolean addUzytkownicy(Uzytkownicy uzytkownicy){
        return modifyUzytkownik.add(uzytkownicy);
    }

    Boolean addUzytkownicyWithOutTransaction(Uzytkownicy uzytkownicy, Session session){
        if(session == null){
            session = openSession();
        }
        return modifyUzytkownik.addWithOutTransaction(uzytkownicy, session);
    }

    public Boolean updateUzytkownicy(Uzytkownicy uzytkownicy){
        return modifyUzytkownik.update(uzytkownicy);
    }

    Boolean updateUzytkownicyWithOutTransaction(Uzytkownicy uzytkownicy, Session session){
        if(session == null){
            session = openSession();
        }
        return modifyUzytkownik.updateWithOutTransaction(uzytkownicy, session);
    }

    public Boolean deleteUzytkownicy(Uzytkownicy uzytkownicy){
        return modifyUzytkownik.delete(uzytkownicy);
    }

    Boolean deleteUzytkownicyWithOutTransaction(Uzytkownicy uzytkownicy, Session session){
        if(session == null){
            session = openSession();
        }
        return modifyUzytkownik.deleteWithOutTransaction(uzytkownicy, session);
    }

    public Uzytkownicy selectByMail(String mail){
        return modifyUzytkownik.selectObjectHQL(("from Uzytkownicy where mail=\"" + mail + "\""));
//        Uzytkownicy user = null;
//        try{
//            session = openSession();
//            String hgl = "from Uzytkownicy where mail=\"" + mail + "\"";
//            System.out.println(hgl);
//            query = session.createQuery(hgl);
//            user = (Uzytkownicy) query.uniqueResult();
//        }catch(Exception e){
//            logException(e);
//        }finally {
//            closeSession(session);
//        }
//        return user;
    }

    public Uzytkownicy selectByMailAndPassword(String mail, String password){
        return modifyUzytkownik.selectObjectHQL(("from Uzytkownicy where mail = '" + mail + "' and haslo ='" + password +"'"));
//        Uzytkownicy user = null;
//        try{
//            session = openSession();
//            String hgl = "from Uzytkownicy where mail = '" + mail + "' and haslo ='" + password +"'";
//            query = session.createQuery(hgl);
//            user = (Uzytkownicy) query.uniqueResult();
//        }catch(Exception e){
//            logException(e);
//        }finally {
//            closeSession(session);
//        }
//        return user;
    }

    /**
     * Zablokowanie konta użytkownika.
     *
     * @author KamDziok
     * @param user obiet Uzytkownicy, który chcemy zablokować.
     * @return true jeśli operacja się udała, w przeciwnym wypadku false.
     */

    public boolean ban(Uzytkownicy user){
        user.setUprawnienia(Permissions.BAN);
        return updateUzytkownicy(user);
    }

    public boolean unban(Uzytkownicy user){
        boolean result = false;
        user.setUprawnienia(Permissions.KLIENT);
        try {
            updateUzytkownik(user);
            result = true;
        }catch (Exception e){
            logException(e);
        }
        return result;
    }

    /**
     * Wyszukanie użytkowników pod względem uprawnień.
     *
     * @author KamDziok
     * @param ban true jeśli chcemy, żeby wyszukało nam użytkowników zablokowanych,
     *            false jeśli chcemu listę użytkowników niezblokowanych.
     * @return listę użytkowników spełniajaćych kryterium, jeśli lista jest pusta nie znaleziono określonych użytkowników
     */

    public List<Uzytkownicy> selectBy(boolean ban){
        if(ban){
            return new OperationsOnDataInEntity<Uzytkownicy>().selectListHQL(
                    ("from Uzytkownicy as u where u.uprawnienia<=" + Permissions.BAN));
        }else{
            return new OperationsOnDataInEntity<Uzytkownicy>().selectListHQL(
                    ("from Uzytkownicy as u where u.uprawnienia>" + Permissions.BAN));
        }
    }

    public Boolean addOdpowiedziUzytkownika(List<OdpowiedziUzytkownicy> usersAnswers){
        boolean result = false;
        try{
            session = openSession();
            transaction = beginTransaction(session);
            usersAnswers.forEach(userAnswers ->
            {
                Integer points = null;
                if(userAnswers.getPunktowe() != TypeOfQuestion.USER_ANSWER_NULL){
                    points = userAnswers.getPunktowe();
                }
                session.createSQLQuery("INSERT INTO `odpowiedzi_uzytkownicy`(`ID_odpowiedzi`, `ID_uzytkownika`, `punktowe`) " +
                    "VALUES (:idOdpowiedzi,:idUzytkownika,:punkty)")
                    .setParameter("idOdpowiedzi", userAnswers.getOdpowiedz().getIdOdpowiedzi())
                    .setParameter("idUzytkownika", userAnswers.getUzytkownik().getIdUzytkownika())
                    .setParameter("punkty", points).executeUpdate();
            });
            commitTransaction(transaction);
            result = true;
        }catch(Exception e){
            rollbackTransaction(transaction);
            logException(e);
        }finally {
            closeSession(session);
        }
        return result;
    }
}
