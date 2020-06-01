package com.Ankiety_PZ.query;

import com.Ankiety_PZ.hibernate.Odpowiedzi;
import com.Ankiety_PZ.hibernate.OdpowiedziUzytkownicy;
import com.Ankiety_PZ.hibernate.Pytania;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * Klasa zawiera metody do przesyłu danych z bazą danych dla tabeli Odpowiedzi.
 */

public class OdpowiedziQuery extends OperationInSession {

    private OperationsOnDataInEntity<Odpowiedzi> modifyOdpowiedzi;

    public OdpowiedziQuery() {
        modifyOdpowiedzi = new OperationsOnDataInEntity<>();
    }

    /**
     * Wypisuje wszystkie {@link Odpowiedzi odpowiedzi} z bazy
     *
     * @return Lista {@link Odpowiedzi odpowiedzi}, jeśli w bazie nie ma {@link Odpowiedzi odpowiedzi} lista będzie pusta
     * @throws HibernateException
     */
    public List<Odpowiedzi> selestAll() throws HibernateException {
        return modifyOdpowiedzi.selectListHQL(("from Odpowiedzi"));
    }

    /**
     * Wypisuje obiekt {@link Odpowiedzi odpowiedzi} o podanym id.
     *
     * @param id {@link Odpowiedzi odpowiedzi}, którą chcemy uzyskać.
     * @return Obietk {@link Odpowiedzi odpowiedzi}, jeśli brak to wartość null.
     */
    public Odpowiedzi selectByID(int id) {
        return modifyOdpowiedzi.selectObjectHQL(("from Odpowiedzi where id=" + id));
    }

    /**
     * Dodanie {@link Odpowiedzi odpowiedzi} do bazy danych.
     *
     * @param odpowiedzi {@link Odpowiedzi odpowiedzi}, którą chcemy dodać
     * @return true jeśli się powiodło, w przeciwnym wypadku false
     */
    public Boolean addOdpowiedzi(Odpowiedzi odpowiedzi) {
        return modifyOdpowiedzi.add(odpowiedzi);
    }

    /**
     * Dodanie {@link Odpowiedzi odpowiedzi} do bazy danych w ramach zewnętrznej sesji.
     *
     * @param odpowiedzi {@link Odpowiedzi odpowiedzi}, którą chcemy dodać
     * @param session sesja z bazą w ramach, której ma zostać dodana nagroda do bazy.
     * @return true jeśli się powiodło, w przeciwnym wypadku false
     */
    Boolean addOdpowiedziWithOutTransaction(Odpowiedzi odpowiedzi, Session session) {
        return modifyOdpowiedzi.addWithOutTransaction(odpowiedzi, session);
    }

    /**
     * Modyfikacja {@link Odpowiedzi odpowiedzi} w bazie danych.
     *
     * @param odpowiedzi  {@link Odpowiedzi odpowiedzi}, którą chcemy zmodyfikować
     * @return true jeśli się powiodło, w przeciwnym wypadku false
     */
    public Boolean updateOdpowiedzi(Odpowiedzi odpowiedzi) {
        return modifyOdpowiedzi.update(odpowiedzi);
    }

    /**
     * Modyfikacja {@link Odpowiedzi odpowiedzi} do bazy danych w ramach zewnętrznej sesji.
     *
     * @param odpowiedzi {@link Odpowiedzi odpowiedzi}, którą chcemy zmodyfikować
     * @param session sesja z bazą w ramach, której ma zostać dodana nagroda do bazy.
     * @return true jeśli się powiodło, w przeciwnym wypadku false
     */
    Boolean updateOdpowiedziWithOutTransaction(Odpowiedzi odpowiedzi, Session session) {
        return modifyOdpowiedzi.updateWithOutTransaction(odpowiedzi, session);
    }

    /**
     * Usunięcie {@link Odpowiedzi odpowiedzi} z bazy danych.
     *
     * @param odpowiedzi {@link Odpowiedzi odpowiedzi}, którą chcemy usunąć
     * @return true jeśli się powiodło, w przeciwnym wypadku false
     */
    public Boolean deleteOdpowiedzi(Odpowiedzi odpowiedzi) {
        return modifyOdpowiedzi.delete(odpowiedzi);
    }

    /**
     * Usunięcie {@link Odpowiedzi odpowiedzi} z bazy danych w ramach zewnętrznej sesji.
     *
     * @param odpowiedzi {@link Odpowiedzi odpowiedzi}, którą chcemy usunąć
     * @param session sesja z bazą w ramach, której ma zostać dodana nagroda do bazy.
     * @return true jeśli się powiodło, w przeciwnym wypadku false
     */
    Boolean deleteOdpowiedziWithOutTransaction(Odpowiedzi odpowiedzi, Session session) {
        return modifyOdpowiedzi.deleteWithOutTransaction(odpowiedzi, session);
    }

    /**
     * Usunięcie listy {@link Odpowiedzi odpowiedzi} z bazy danych w ramach zewnętrznej sesji.
     *
     * @param odpowiedziList lista {@link Odpowiedzi odpowiedzi}, które chemy usunąć
     * @param session sesja z bazą w ramach, której ma zostać dodana nagroda do bazy.
     * @return true jeśli się powiodło, w przeciwnym wypadku false
     */
    public Boolean deleteListOdpowiedzi(List<Odpowiedzi> odpowiedziList, Session session) {
        Boolean result = false;
        odpowiedziList.forEach(odpowiedzi -> {
            if (odpowiedzi.getIdOdpowiedzi().intValue() != -1) {
                deleteOdpowiedziWithOutTransaction(odpowiedzi, session);
            }
        });
        result = true;
        return result;
    }

    /**
     * Wyszukanie wszystkich {@link Odpowiedzi odpowiedzi}, które należą do określonego {@link Pytania pytania}.
     *
     * @param pytania {@link Pytania pytanie}, dla któego poszukujemy odpowiedzi
     * @return lista {@link Odpowiedzi odpowiedzi}, dla {@link Pytania pytania}, jeśli brak odpowiedzi to zwracana jest lista pusta.
     */
    public List<Odpowiedzi> selectSetOdpowiedziByIdPytania(Pytania pytania) {
        return modifyOdpowiedzi.selectListHQL(
                ("select o from Odpowiedzi as o " +
                        "inner join o.pytania as p " +
                        "where p.idPytania=" + pytania.getIdPytania()));
    }

    /**
     * Wyszukanie {@link OdpowiedziUzytkownicy odpowiedzi uzytkowników} na {@link Pytania pytania}
     * procentowe i punktowe
     *
     * @param odpowiedzi {@link Odpowiedzi odpowiedz}, do której chcemy uzyskać odpowiedzi {@link com.Ankiety_PZ.hibernate.Uzytkownicy użytkowników}.
     * @return zwraca listę {@link OdpowiedziUzytkownicy odpowiedzi użytkowników}, jeśli brak wyników to listę pustą.
     */
    public List<OdpowiedziUzytkownicy> selectOdpowiedziPointsAndPercent(Odpowiedzi odpowiedzi) {
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

    /**
     * Wyszukanie sumy {@link OdpowiedziUzytkownicy odpowiedzi użytkowników} na {@link Odpowiedzi odpowiedź}.
     *
     * @param odpowiedzi {@link Odpowiedzi odpowiedz}, do której chcemy uzyskać zliczone odpowiedzi {@link com.Ankiety_PZ.hibernate.Uzytkownicy użytkowników}.
     * @return zliczoną ilość odpowiedzi, w ptzypadku ich braku zwrócenie 0
     */
    public BigInteger selectCountOdpowiedzi(Odpowiedzi odpowiedzi) {
        return (BigInteger) new OperationsOnDataInEntity<BigInteger>().selectObjectSQL(
                "SELECT count(*) FROM `odpowiedzi_uzytkownicy` WHERE ID_odpowiedzi=" + odpowiedzi.getIdOdpowiedzi());
    }
}