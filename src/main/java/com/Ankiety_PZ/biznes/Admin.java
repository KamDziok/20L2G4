package com.Ankiety_PZ.biznes;

import java.util.LinkedList;

public class Admin extends Uzytkownik {

    private LinkedList<Uzytkownik> listaKont;

    public Admin(int id, String imie, String nazwisko, String mail, String haslo) {
        super(id, imie, nazwisko, mail, haslo);
        listaKont = new LinkedList();   //zastapic lista uzytkownikow z Hibernate
    }


    public LinkedList wyszukaj(String wzorzec) {
        LinkedList pofiltrowanaListaKont = new LinkedList();
        for (Uzytkownik konto : listaKont) {
            if (konto.getImie().contains(wzorzec) || konto.getNazwisko().contains(wzorzec) ||
                    konto.getMail().contains(wzorzec) || Integer.toString(konto.getId()).contains(wzorzec)) {
                pofiltrowanaListaKont.add(konto);
            }
        }
        return pofiltrowanaListaKont;
    }

    public LinkedList getListaKont() {
        return listaKont;
    }

    public void setListaKont(LinkedList listaKont) {
        this.listaKont = listaKont;
    }

}
