package com.Ankiety_PZ.biznes;

public class Uzytkownik {

    private int id;
    private String imie;
    private String nazwisko;
    private String mail;
    private String haslo;

    public Uzytkownik(int id, String imie, String nazwisko, String mail, String haslo) {
        this.id = id;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.mail = mail;
        this.haslo = haslo;
    }

    public boolean zmienHaslo(String stare, String nowe) {
        if (stare.equals(nowe)) {
            haslo = nowe;
            // update na baze
            return true;
        }
        return false;
    }

    public boolean zmienMail(String nowyMail) {
        if (!mail.equals(nowyMail)) {
            mail = nowyMail;
            // update na baze
            return true;
        }
        return false;
    }

    public boolean zmienImie(String noweImie) {
        if (!imie.equals(noweImie)) {
            imie = noweImie;
            // update na baze
            return true;
        }
        return false;
    }

    public boolean zmienNazwisko(String noweNazwisko) {
        if (!imie.equals(noweNazwisko)) {
            nazwisko = noweNazwisko;
            // update na baze
            return true;
        }
        return false;
    }

    public int getId() {
        return id;
    }

    public String getImie() {
        return imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public String getMail() {
        return mail;
    }

    public String getHaslo() {
        return haslo;
    }

    private void setImie(String imie) {
        this.imie = imie;
    }

    private void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    private void setMail(String mail) {
        this.mail = mail;
    }

    private void setHaslo(String haslo) {
        this.haslo = haslo;
    }

}
