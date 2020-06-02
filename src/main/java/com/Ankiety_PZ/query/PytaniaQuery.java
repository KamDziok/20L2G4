package com.Ankiety_PZ.query;

import com.Ankiety_PZ.hibernate.Ankiety;
import com.Ankiety_PZ.hibernate.Odpowiedzi;
import com.Ankiety_PZ.hibernate.Pytania;
import com.Ankiety_PZ.hibernate.PytaniaUzytkownicy;
import org.hibernate.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Klasa zawiera metody do przesyłu danych z bazą danych dla tabeli Pytania.
 */

public class PytaniaQuery extends OperationInSession {

    private OperationsOnDataInEntity<Pytania> modifyPytania;

    public PytaniaQuery() {
        this.modifyPytania = new OperationsOnDataInEntity<>();
    }

    /**
     * Wypisuje wszystkie {@link Pytania pytań} z bazy
     *
     * @return lista wszystkich {@link Pytania pytań} z bazyt
     * @throws HibernateException
     */
    public List<Pytania> selectAll() throws HibernateException {
        return modifyPytania.selectListHQL("from Pytania");
    }

    /**
     * Wypisuje obiekt {@link Pytania pytania} o podanym id.
     *
     * @param id {@link Pytania pytania}, którą chcemy uzyskać.
     * @return Obietk {@link Pytania pytania}, jeśli brak to wartość null.
     */
    public Pytania selectByID(int id) {
        return modifyPytania.selectObjectHQL(("from Pytania where ID = " + id));
    }

    /**
     * Dodanie {@link Pytania pytania} do bazy danych.
     *
     * @param pytania {@link Pytania pytanie}, którą chcemy dodać
     * @return true jeśli się powiodło, w przeciwnym wypadku false
     */
    public Boolean addPytania(Pytania pytania) {
        return modifyPytania.add(pytania);
    }

    /**
     * Dodanie {@link Pytania pytania} do bazy danych w ramach zewnętrznej sesji.
     *
     * @param pytania {@link Pytania pytanie}, którą chcemy dodać
     * @param session sesja z bazą w ramach, której ma zostać dodana nagroda do bazy.
     * @return true jeśli się powiodło, w przeciwnym wypadku false
     */
    Boolean addPytaniaWithOutTransaction(Pytania pytania, Session session) {
        return modifyPytania.addWithOutTransaction(pytania, session);
    }

    /**
     * Modyfikowanie {@link Pytania pytania} do bazy danych.
     *
     * @param pytania {@link Pytania pytanie}, którą chcemy zmodyfikować
     * @return true jeśli się powiodło, w przeciwnym wypadku false
     */
    public Boolean updatePytania(Pytania pytania) {
        return modifyPytania.update(pytania);
    }

    /**
     * Modyfikowanie {@link Pytania pytania} do bazy danych w ramach zewnętrznej sesji.
     *
     * @param pytania {@link Pytania pytanie}, którą chcemy zmodyfikować
     * @param session sesja z bazą w ramach, której ma zostać dodana nagroda do bazy.
     * @return true jeśli się powiodło, w przeciwnym wypadku false
     */
    Boolean updatePytaniaWithOutTransaction(Pytania pytania, Session session) {
        return modifyPytania.updateWithOutTransaction(pytania, session);
    }

    /**
     * Usuwanie {@link Pytania pytania} z bazy danych.
     *
     * @param pytania {@link Pytania pytanie}, którą chcemy usunąć
     * @return true jeśli się powiodło, w przeciwnym wypadku false
     */
    public Boolean deletePytania(Pytania pytania) {
        return modifyPytania.delete(pytania);
    }

    /**
     * Usuwanie {@link Pytania pytania} z bazy danych w ramach zewnętrznej sesji.
     *
     * @param pytania {@link Pytania pytanie}, którą chcemy usunąć
     * @param session sesja z bazą w ramach, której ma zostać dodana nagroda do bazy.
     * @return true jeśli się powiodło, w przeciwnym wypadku false
     */
    Boolean deletePytaniaWithOutTransaction(Pytania pytania, Session session) {
        return modifyPytania.deleteWithOutTransaction(pytania, session);
    }

    /**
     * Usuwanie listy {@link Pytania pytań} z bazy danych w ramach zewnętrznej sesji.
     *
     * @param pytaniaList lista {@link Pytania pytania}, które chcemy usunąć
     * @param session sesja z bazą w ramach, której ma zostać dodana nagroda do bazy.
     * @return true jeśli się powiodło, w przeciwnym wypadku false
     */
    public Boolean deleteListPytania(List<Pytania> pytaniaList, Session session) {
        Boolean result = false;
        OdpowiedziQuery odpowiedziQuery = new OdpowiedziQuery();
        pytaniaList.forEach(pytania -> {
            if (pytania.getIdPytania().intValue() != -1) {
                List<Odpowiedzi> odpowiedziList = odpowiedziQuery.selectSetOdpowiedziByIdPytania(pytania);
                odpowiedziQuery.deleteListOdpowiedzi(odpowiedziList, session);
                deletePytaniaWithOutTransaction(pytania, session);
            }
        });
        result = true;
        return result;
    }

    /**
     * Wyszukanie wszystkich {@link Pytania pytań} dla podanej {@link Ankiety ankiety}.
     *
     * @param ankiety {@link Ankiety ankieta} dla której szukamy {@link Pytania pytań}
     * @return listę {@link Pytania pytań}, w przypadku braku takich danych w bazie zostanie zwrócona lista pusta.
     */
    public List<Pytania> selectListPytaniaByIdAnkiety(Ankiety ankiety) {
        return modifyPytania.selectListHQL(
                ("select p from Pytania as p inner join p.ankiety as a " +
                        "where a.idAnkiety=" + ankiety.getIdAnkiety()));
    }

    /**
     * Wyszukanie odpowiedzi {@link com.Ankiety_PZ.hibernate.Uzytkownicy użytkowników} na {@link PytaniaUzytkownicy pytania otwarte}.
     *
     * @param pytania {@link Pytania pytanie}, na które chcemy uzyskać {@link PytaniaUzytkownicy odpowiedzi użytkowników}.
     * @return listę {@link PytaniaUzytkownicy odpowiedzi użytkowników}, w przypadku braku takich danych w bazie zostanie zwrócona lista pusta.
     */
    public List<PytaniaUzytkownicy> selectPytaniaUzytkownicy(Pytania pytania) {
        List<PytaniaUzytkownicy> pytaniaUzytkownicyList = new ArrayList<>();
        List<String> odpowiedziList = (List<String>) new OperationsOnDataInEntity<String>().selectListSQL(
                "SELECT `odpowiedz` FROM `pytania_uzytkownicy` WHERE ID_pytania=" + pytania.getIdPytania());
        odpowiedziList.forEach(odpowiedz -> {
            PytaniaUzytkownicy pytaniaUzytkownicy = new PytaniaUzytkownicy();
            pytaniaUzytkownicy.setPytanie(pytania);
            pytaniaUzytkownicy.setOdpowiedz(odpowiedz);
            pytaniaUzytkownicyList.add(pytaniaUzytkownicy);
        });
        return pytaniaUzytkownicyList;
    }
}