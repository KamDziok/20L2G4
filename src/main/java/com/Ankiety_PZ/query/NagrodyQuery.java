/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Ankiety_PZ.query;

import com.Ankiety_PZ.generowaniePDF.NagrodyGenerowaniePDF;
import com.Ankiety_PZ.hibernate.Nagrody;
import com.Ankiety_PZ.hibernate.Uzytkownicy;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.util.List;

/**
 * Klasa zawiera metody do przesyłu danych z bazą danych dla tabeli Nagrody.
 */
public class NagrodyQuery extends OperationInSession {

    private OperationsOnDataInEntity<Nagrody> modifyNagrody;

    public NagrodyQuery() {
        this.modifyNagrody = new OperationsOnDataInEntity<>();
    }

    /**
     * Wypisuje wszystkie {@link Nagrody nagrody} z bazy
     *
     * @return Lista {@link Nagrody nagród}, jeśli w bazie nie ma {@link Nagrody nagród} lista będzie pusta
     * @throws HibernateException wyjątek Hibernate
     */
    public List<Nagrody> selectAll() throws HibernateException {
        return modifyNagrody.selectListHQL(("from Nagrody"));
    }

    /**
     * Wypisuje obiekt {@link Nagrody nagrody} o podanym id.
     *
     * @param id {@link Nagrody nagrody}, którą chcemy uzyskać.
     * @return Obietk {@link Nagrody nagroda}, jeśli brak to wartość null.
     */
    public Nagrody selectByID(int id) {
        return modifyNagrody.selectObjectHQL(("from Nagrody where ID = " + id));
    }

    /**
     * Wypisuje wszystkie {@link Nagrody nagrody} z bazy, które nie uległy przedawnieniu.
     *
     * @return Lista {@link Nagrody nagród}, jeśli w bazie nie ma {@link Nagrody nagród} lista będzie pusta
     */
    public List<Nagrody> selectAllActive() {
        return modifyNagrody.selectListHQL(("from Nagrody as n where n.liczbaPunktow>=0"));
    }

    /**
     * Dodanie {@link Nagrody nagrody} do bazy danych.
     *
     * @param nagrody {@link Nagrody nagroda}, którą chcemy dodać
     * @return true jeśli się powiodło, w przeciwnym wypadku false
     */
    public Boolean addNagrody(Nagrody nagrody) {
        return modifyNagrody.add(nagrody);
    }

    /**
     * Dodanie {@link Nagrody nagrody} do bazy danych w ramach zewnętrznej sesji.
     *
     * @param nagrody {@link Nagrody nagroda}, którą chcemy dodać
     * @param session sesja z bazą w ramach, której ma zostać dodana nagroda do bazy.
     * @return true jeśli się powiodło, w przeciwnym wypadku false
     */
    Boolean addNagrodyWithOutTransaction(Nagrody nagrody, Session session) {
        return modifyNagrody.addWithOutTransaction(nagrody, session);
    }

    /**
     * Modyfikacja {@link Nagrody nagrody} w bazie danych.
     *
     * @param nagrody {@link Nagrody nagroda}, którą chcemy zmodyfikować
     * @return true jeśli się powiodło, w przeciwnym wypadku false
     */
    public Boolean updateNagrody(Nagrody nagrody) {
        return modifyNagrody.update(nagrody);
    }

    /**
     * Modyfikacja {@link Nagrody nagrody} w bazie danych w ramach zewnętrznej sesji.
     *
     * @param nagrody {@link Nagrody nagroda}, którą chcemy zmodyfikować
     * @param session sesja z bazą w ramach, której ma zostać dodana nagroda do bazy.
     * @return true jeśli się powiodło, w przeciwnym wypadku false
     */
    Boolean updateNagrodyWithOutTransaction(Nagrody nagrody, Session session) {
        return modifyNagrody.updateWithOutTransaction(nagrody, session);
    }

    /**
     * Usunięcie {@link Nagrody nagrody} z bazy danych.
     *
     * @param nagrody {@link Nagrody nagroda}, którą chcemy usunąć
     * @return true jeśli się powiodło, w przeciwnym wypadku false
     */
    public Boolean deleteNagrody(Nagrody nagrody) {
        return modifyNagrody.delete(nagrody);
    }

    /**
     * Usunięcie {@link Nagrody nagrody} z bazy danych w ramach zewnętrznej sesji.
     *
     * @param nagrody {@link Nagrody nagroda}, którą chcemy usunąć
     * @param session sesja z bazą w ramach, której ma zostać dodana nagroda do bazy.
     * @return true jeśli się powiodło, w przeciwnym wypadku false
     */
    Boolean deleteNagrodyWithOutTransaction(Nagrody nagrody, Session session) {
        return modifyNagrody.deleteWithOutTransaction(nagrody, session);
    }

    /**
     * Zablokowanie {@link Nagrody nagrody} przez ustawienie jej liczby punktów na -1.
     *
     * @param nagrody {@link Nagrody nagroda}, którą chcemy dezaktywować
     * @return true jeśli się powiodło, w przeciwnym wypadku false
     */
    public Boolean deactivateNagrody(Nagrody nagrody) {
        nagrody.setLiczbaPunktow(-1);
        return updateNagrody(nagrody);
    }

    /**
     * Sprawdzenie czy {@link Uzytkownicy uzytkownik} ma odpowiednią liczbę punktów aby odebrać {@link Nagrody nagrodę}
     *
     * @param nagrody {@link Nagrody nagroda}, którą chcemy otrzymać {@link Uzytkownicy uzytkownik}
     * @param uzytkownicy {@link Uzytkownicy uzytkownik} wykonujący akcję.
     * @return true jeśli {@link Uzytkownicy uzytkownik} ma wystarczającą liczbę punktów, w przeciwnym wypadku false
     */
    Boolean checkUzytkownikCanGetNagrody(Nagrody nagrody, Uzytkownicy uzytkownicy){
        boolean result = false;
        if (uzytkownicy.getLiczbaPunktow() >= nagrody.getLiczbaPunktow()){
            result = true;
        }
        return result;
    }

    /**
     * Odnotowanie w bazie przekazania faktu, że {@link Uzytkownicy uzytkownikownik} otzrymał {@link Nagrody nagrodę}
     *
     * @param nagrody {@link Nagrody nagroda}, którą chcemy otrzymać {@link Uzytkownicy uzytkownik}
     * @param uzytkownicy {@link Uzytkownicy uzytkownik} wykonujący akcję.
     * @return true jeśli się powiodło, w przeciwnym wypadku false
     */
    public Boolean getNagrodyToUzytkownicy(Nagrody nagrody, Uzytkownicy uzytkownicy) {
        boolean result = false;
        if (checkUzytkownikCanGetNagrody(nagrody, uzytkownicy)) {
            try {
                session = openSession();
                transaction = beginTransaction(session);
                new NagrodyGenerowaniePDF(nagrody, uzytkownicy);
                session.createSQLQuery("INSERT INTO `uzytkownicy_nagrody`(`ID_uzytkownika`, `ID_nagrody`) " +
                        "VALUES (:idUzytkownicy,:idNagrody)")
                        .setParameter("idUzytkownicy", uzytkownicy.getIdUzytkownika())
                        .setParameter("idNagrody", nagrody.getIdNagrody())
                        .executeUpdate();
                uzytkownicy.updatePunkty(nagrody.getLiczbaPunktow(), false);
                new UzytkownicyQuery().updateUzytkownicyWithOutTransaction(uzytkownicy, session);
                commitTransaction(transaction);
                result = true;
            } catch (Exception e) {
                rollbackTransaction(transaction);
                logException(e);
            } finally {
                closeSession(session);
            }
        }
        return result;
    }
}
