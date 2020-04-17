package com.Ankiety_PZ.biznes;

import java.util.LinkedList;
import java.time.LocalDate;

public class Klient extends Uzytkownik{

    private LinkedList listaAnkiet;
    private LinkedList listaNagrod;
    private int liczbaPunktow;
    private String miejscowosc;
    private String ulica;
    private String numerBloku;
    private String numerLokalu;
    private String kodPocztowy;
    private String plec;
    private LocalDate wiek;


    public Klient(int id, String imie, String nazwisko, String mail, String haslo,
           int liczbaPunktow, String miejscowosc, String ulica, String numerBloku,
           String numerLokalu, String kodPocztowy, String plec, LocalDate wiek) {
        super(id, imie, nazwisko, mail, haslo);
        this.listaAnkiet = new LinkedList();    //zastapic jakas funkcja szukajaca aktualnych ankiet
        this.listaNagrod = new LinkedList();    //zastapic lista z Hibernate
        this.liczbaPunktow = liczbaPunktow;
        this.miejscowosc = miejscowosc;
        this.ulica = ulica;
        this.numerBloku = numerBloku;
        this.numerLokalu = numerLokalu;
        this.kodPocztowy = kodPocztowy;
        this.plec = plec;
        this.wiek = wiek;
    }


    boolean wydaj(int cena) {
        if (liczbaPunktow - cena < 0) {
            return false;
        }
        liczbaPunktow = liczbaPunktow - cena;
        return true;
    }

    boolean dodaj(int punkty) {
        liczbaPunktow = liczbaPunktow + punkty;
        return true;
    }

    public boolean zmienMiejscowosc(String nowaMiejscowosc) {
        if (!miejscowosc.equals(nowaMiejscowosc)) {
            miejscowosc = nowaMiejscowosc;
            // update na baze
            return true;
        }
        return false;
    }

    public boolean zmienUlice(String nowaUlica) {
        if (!ulica.equals(nowaUlica)) {
            ulica = nowaUlica;
            // update na baze
            return true;
        }
        return false;
    }

    public boolean zmienBlok(String nowyBlok) {
        if (!numerBloku.equals(nowyBlok)) {
            numerBloku = nowyBlok;
            // update na baze
            return true;
        }
        return false;
    }
    public boolean zmienLokal(String nowyLokal) {
        if (!numerLokalu.equals(nowyLokal)) {
            numerLokalu = nowyLokal;
            // update na baze
            return true;
        }
        return false;
    }

    public boolean zmienKodPocztowy(String nowyKod) {
        if (!kodPocztowy.equals(nowyKod)) {
            kodPocztowy = nowyKod;
            // update na baze
            return true;
        }
        return false;
    }

//    public boolean aktualizacjaWieku(String nowaUlica) {
//        if (!ulica.equals(nowaUlica)) {
//            ulica = nowaUlica;
//            // update na baze
//            return true;
//        }
//        return false;
//    }

    public LinkedList getListaAnkiet() {
        return listaAnkiet;
    }

    public LinkedList getListaNagrod() {
        return listaNagrod;
    }

    public int getLiczbaPunktow() {
        return liczbaPunktow;
    }

    public String getMiejscowosc() {
        return miejscowosc;
    }

    public String getUlica() {
        return ulica;
    }

    public String getNumerBloku() {
        return numerBloku;
    }

    public String getNumerLokalu() {
        return numerLokalu;
    }

    public String getKodPocztowy() {
        return kodPocztowy;
    }

    public String getPlec() {
        return plec;
    }

    public LocalDate getWiek() {
        return wiek;
    }

    private void setListaAnkiet(LinkedList listaAnkiet) {
        this.listaAnkiet = listaAnkiet;
    }

    private void setListaNagrod(LinkedList listaNagrod) {
        this.listaNagrod = listaNagrod;
    }

    private void setLiczbaPunktow(int liczbaPunktow) {
        this.liczbaPunktow = liczbaPunktow;
    }

    private void setMiejscowosc(String miejscowosc) {
        this.miejscowosc = miejscowosc;
    }

    private void setUlica(String ulica) {
        this.ulica = ulica;
    }

    private void setNumerBloku(String numerBloku) {
        this.numerBloku = numerBloku;
    }

    private void setNumerLokalu(String numerLokalu) {
        this.numerLokalu = numerLokalu;
    }

    private void setKodPocztowy(String kodPocztowy) {
        this.kodPocztowy = kodPocztowy;
    }

    private void setPlec(String plec) {
        this.plec = plec;
    }

    private void setWiek(LocalDate wiek) {
        this.wiek = wiek;
    }

}
