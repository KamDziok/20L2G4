package com.Ankiety_PZ.query;

import com.Ankiety_PZ.hibernate.OdpowiedziUzytkownicy;
import com.Ankiety_PZ.hibernate.Uzytkownicy;
import com.Ankiety_PZ.test.Permissions;
import com.Ankiety_PZ.test.TypeOfQuestion;
import org.hibernate.HibernateException;

import java.util.ArrayList;
import java.util.List;

public class UzytkownicyQuery extends OperationInSession {

    private OperationsOnDataInEntity<Uzytkownicy> modifyUzytkownik;

    public UzytkownicyQuery(){
        this.modifyUzytkownik = new OperationsOnDataInEntity<>();
    }

    public List<Uzytkownicy> selectAll() throws HibernateException {
        List<Uzytkownicy> uzytkowink = new ArrayList<>();
        try {
            session = openSession();
            criteria = session.createCriteria(Uzytkownicy.class);
            uzytkowink = criteria.list();
        } catch(Exception e){
            logException(e);
        }finally{
            closeSession(session);
        }
        return uzytkowink;
    }

    /**
     * Odczyt pojedynczego użytkownika z bazy.
     *
     * @author KamDziok
     * @param id identyfikarot użytkownika.
     * @return obiekt Uzytkownicy jeśli istnieje w zazie użytkownika o podanym id, w przeciwnym wypadku null.
     */
    public Uzytkownicy selectById(int id){
        Uzytkownicy user = null;
        try{
            session = openSession();
            user = (Uzytkownicy) session
                    .createQuery("from Uzytkownicy as u where u.idUzytkownika=:id")
                    .setParameter("id",id)
                    .uniqueResult();
        }catch(Exception e){
            logException(e);
        }finally{
            closeSession(session);
        }
        return user;
    }

    public Boolean addUzytkownik(Uzytkownicy uzytkownik){
        return modifyUzytkownik.modifyDataInEntity(uzytkownik, true, false, false, true);
    }

    Boolean addUzytkownikWithOutTransaction(Uzytkownicy uzytkownik){
        return modifyUzytkownik.modifyDataInEntity(uzytkownik, true, false, false, false);
    }

    public Boolean updateUzytkownik(Uzytkownicy uzytkownik){
        return modifyUzytkownik.modifyDataInEntity(uzytkownik, false, true, false, true);
    }

    Boolean updateUzytkownikWithOutTransaction(Uzytkownicy uzytkownik){
        return modifyUzytkownik.modifyDataInEntity(uzytkownik, false, true, false, false);
    }

    public Boolean delUzytkownik(Uzytkownicy uzytkownik){
        return modifyUzytkownik.modifyDataInEntity(uzytkownik, false, false, true, true);
    }

    Boolean delUzytkownikWithOutTransaction(Uzytkownicy uzytkownik){
        return modifyUzytkownik.modifyDataInEntity(uzytkownik, false, false, true, false);
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
            closeSession(session);
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
            closeSession(session);
        }
        return user;
    }

    /**
     * Zablokowanie konta użytkownika.
     *
     * @author KamDziok
     * @param user obiet Uzytkownicy, który chcemy zablokować.
     * @return true jeśli operacja się udała, w przeciwnym wypadku false.
     */

    public boolean ban(Uzytkownicy user){
        boolean result = false;
        user.setUprawnienia(Permissions.BAN);
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
        List<Uzytkownicy> users = new ArrayList<>();
        String hql;
        if(ban){
            hql = "from Uzytkownicy as u where u.uprawnienia<=" + Permissions.BAN;
        }else{
            hql = "from Uzytkownicy as u where u.uprawnienia>" + Permissions.BAN;
        }
        try{
            session = openSession();
            users = session.createQuery(hql).list();
        }catch(Exception e){
            logException(e);
        }finally {
            closeSession(session);
        }
        return users;
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
