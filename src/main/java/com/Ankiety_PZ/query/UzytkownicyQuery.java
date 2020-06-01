package com.Ankiety_PZ.query;

import com.Ankiety_PZ.hibernate.Ankiety;
import com.Ankiety_PZ.hibernate.OdpowiedziUzytkownicy;
import com.Ankiety_PZ.hibernate.PytaniaUzytkownicy;
import com.Ankiety_PZ.hibernate.Uzytkownicy;
import com.Ankiety_PZ.panele.Permissions;
import com.Ankiety_PZ.panele.TypeOfQuestion;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.util.List;

/**
 * Klasa zawiera metody do przesyłu danych z bazą danych dla tabeli Uzytkownicy.
 */

public class UzytkownicyQuery extends OperationInSession {

    private OperationsOnDataInEntity<Uzytkownicy> modifyUzytkownik;

    public UzytkownicyQuery() {
        this.modifyUzytkownik = new OperationsOnDataInEntity<>();
    }

    public List<Uzytkownicy> selectAll() throws HibernateException {
        return modifyUzytkownik.selectListHQL(("from Uzytkownicy"));
    }

    /**
     * Odczyt pojedynczego użytkownika z bazy.
     *
     * @param id identyfikarot użytkownika.
     * @return obiekt Uzytkownicy jeśli istnieje w zazie użytkownika o podanym id, w przeciwnym wypadku null.
     */

    public Uzytkownicy selectById(int id) {
        return modifyUzytkownik.selectObjectHQL(("from Uzytkownicy as u where u.idUzytkownika=" + id));
    }

    public Boolean addUzytkownicy(Uzytkownicy uzytkownicy) {
        return modifyUzytkownik.add(uzytkownicy);
    }

    Boolean addUzytkownicyWithOutTransaction(Uzytkownicy uzytkownicy, Session session) {
        return modifyUzytkownik.addWithOutTransaction(uzytkownicy, session);
    }

    public Boolean updateUzytkownicy(Uzytkownicy uzytkownicy) {
        return modifyUzytkownik.update(uzytkownicy);
    }

    Boolean updateUzytkownicyWithOutTransaction(Uzytkownicy uzytkownicy, Session session) {
        return modifyUzytkownik.updateWithOutTransaction(uzytkownicy, session);
    }

    public Boolean deleteUzytkownicy(Uzytkownicy uzytkownicy) {
        return modifyUzytkownik.delete(uzytkownicy);
    }

    Boolean deleteUzytkownicyWithOutTransaction(Uzytkownicy uzytkownicy, Session session) {
        return modifyUzytkownik.deleteWithOutTransaction(uzytkownicy, session);
    }

    public Uzytkownicy selectByMail(String mail) {
        return modifyUzytkownik.selectObjectHQL(("from Uzytkownicy where mail=\"" + mail + "\""));
    }

    public Uzytkownicy selectByMailAndPassword(String mail, String password) {
        return modifyUzytkownik.selectObjectHQL(("from Uzytkownicy where mail = '" + mail + "' and haslo ='" + password + "'"));
    }

    /**
     * Zablokowanie konta użytkownika.
     *
     * @param user obiet Uzytkownicy, który chcemy zablokować.
     * @return true jeśli operacja się udała, w przeciwnym wypadku false.
     */

    public boolean ban(Uzytkownicy user) {
        user.setUprawnienia(Permissions.BAN);
        return updateUzytkownicy(user);
    }

    public boolean unban(Uzytkownicy user) {
        user.setUprawnienia(Permissions.KLIENT);
        return updateUzytkownicy(user);
    }

    /**
     * Wyszukanie użytkowników pod względem uprawnień.
     *
     * @param ban true jeśli chcemy, żeby wyszukało nam użytkowników zablokowanych,
     *            false jeśli chcemu listę użytkowników niezblokowanych.
     * @param user obiekt użytkownika.
     * @return listę użytkowników spełniajaćych kryterium, jeśli lista jest pusta nie znaleziono określonych użytkowników
     */

    public List<Uzytkownicy> selectBy(boolean ban, Uzytkownicy user) {
        if (ban) {
            return new OperationsOnDataInEntity<Uzytkownicy>().selectListHQL(
                    ("from Uzytkownicy as u where u.uprawnienia<=" + Permissions.BAN + " and u.idUzytkownika<>" + user.getIdUzytkownika()));
        } else {
            return new OperationsOnDataInEntity<Uzytkownicy>().selectListHQL(
                    ("from Uzytkownicy as u where u.uprawnienia>" + Permissions.BAN + " and u.idUzytkownika<>" + user.getIdUzytkownika()));
        }
    }

    public Boolean addOdpowiedziUzytkownika(List<OdpowiedziUzytkownicy> usersAnswers, List<PytaniaUzytkownicy> userOpenAnswers, Ankiety ankiety, Uzytkownicy user) {
        boolean result = false;
        try {
            session = openSession();
            transaction = beginTransaction(session);
            usersAnswers.forEach(userAnswers ->
            {
                Integer points = null;
                if (userAnswers.getPunktowe() != TypeOfQuestion.USER_ANSWER_NULL) {
                    points = userAnswers.getPunktowe();
                }
                session.createSQLQuery("INSERT INTO `odpowiedzi_uzytkownicy`(`ID_odpowiedzi`, `ID_uzytkownika`, `punktowe`) " +
                        "VALUES (:idOdpowiedzi,:idUzytkownika,:punkty)")
                        .setParameter("idOdpowiedzi", userAnswers.getOdpowiedz().getIdOdpowiedzi())
                        .setParameter("idUzytkownika", userAnswers.getUzytkownik().getIdUzytkownika())
                        .setParameter("punkty", points)
                        .executeUpdate();
            });
            userOpenAnswers.forEach(userOpenAnswer -> {
                session.createSQLQuery("INSERT INTO `pytania_uzytkownicy`(`ID_uzytkownika`, `ID_pytania`, `odpowiedz`) " +
                        "VALUES (:idUzytkownika,:idPytania,:odpowiedz)")
                        .setParameter("idUzytkownika", userOpenAnswer.getUzytkownik().getIdUzytkownika())
                        .setParameter("idPytania", userOpenAnswer.getPytanie().getIdPytania())
                        .setParameter("odpowiedz", userOpenAnswer.getOdpowiedz())
                        .executeUpdate();
            });
            ankiety.setLiczbaWypelnien(new Integer(ankiety.getLiczbaWypelnien().intValue() + 1));
            new AnkietyQuery().updateAnkietyWithOutTransaction(ankiety, session);
            commitTransaction(transaction);
            result = true;
        } catch (Exception e) {
            rollbackTransaction(transaction);
            logException(e);
        } finally {
            closeSession(session);
        }
        return result;
    }
}
