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

    public List<Pytania> selectAll() throws HibernateException {
        return modifyPytania.selectListHQL("from Pytania");
    }

    public Pytania selectByID(int id) {
        return modifyPytania.selectObjectHQL(("from Pytania where ID = " + id));
    }


    public Boolean addPytania(Pytania pytania) {
        return modifyPytania.add(pytania);
    }

    Boolean addPytaniaWithOutTransaction(Pytania pytania, Session session) {
        return modifyPytania.addWithOutTransaction(pytania, session);
    }

    public Boolean updatePytania(Pytania pytania) {
        return modifyPytania.update(pytania);
    }

    Boolean updatePytaniaWithOutTransaction(Pytania pytania, Session session) {
        return modifyPytania.updateWithOutTransaction(pytania, session);
    }

    public Boolean deletePytania(Pytania pytania) {
        return modifyPytania.delete(pytania);
    }

    Boolean deletePytaniaWithOutTransaction(Pytania pytania, Session session) {
        return modifyPytania.deleteWithOutTransaction(pytania, session);
    }

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

    public List<Pytania> selectListPytaniaByIdAnkiety(Ankiety ankiety) {
        return modifyPytania.selectListHQL(
                ("select p from Pytania as p inner join p.ankiety as a " +
                        "where a.idAnkiety=" + ankiety.getIdAnkiety()));
    }

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