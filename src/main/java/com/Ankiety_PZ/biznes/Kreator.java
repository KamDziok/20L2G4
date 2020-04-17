package com.Ankiety_PZ.biznes;

import java.util.LinkedList;

public class Kreator extends Uzytkownik{

    private LinkedList listaAnkiet;


    public Kreator(int id, String imie, String nazwisko, String mail, String haslo) {
        super(id, imie, nazwisko, mail, haslo);
        listaAnkiet = new LinkedList();   //zastapic lista ankiet z Hibernate
    }


//    public boolean dodajAnkiete() {
//        cos
//        return true;
//    }

    public LinkedList getListaAnkiet() {
        return listaAnkiet;
    }

    public void setListaAnkiet(LinkedList listaAnkiet) {
        this.listaAnkiet = listaAnkiet;
    }
}
