package com.Ankiety_PZ.hibernate;
// Generated 2020-04-16 17:33:57 by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Ankiety generated by hbm2java
 */
public class Ankiety implements java.io.Serializable {

    private Integer idAnkiety;
    private String tytul;
    private int liczbaPunktow;
    private Date dataRozpoczecia;
    private Date dataZakonczenia;
    private Integer liczbaWypelnien;
    private Uzytkownicy uzytkownicy;
    private Set pytanias = new HashSet(0);

    public Ankiety() {
    }

    public Ankiety(String tytul, int liczbaPunktow, Date dataRozpoczecia, Date dataZakonczenia, Integer liczbaWypelnien) {
        this.idAnkiety = idAnkiety;
        this.tytul = tytul;
        this.liczbaPunktow = liczbaPunktow;
        this.dataRozpoczecia = dataRozpoczecia;
        this.dataZakonczenia = dataZakonczenia;
        this.liczbaWypelnien = liczbaWypelnien;
    }

    public Ankiety(String tytul, int liczbaPunktow, Date dataRozpoczecia, Date dataZakonczenia, Integer liczbaWypelnien, Uzytkownicy user, Set pytanias) {
        this.tytul = tytul;
        this.liczbaPunktow = liczbaPunktow;
        this.dataRozpoczecia = dataRozpoczecia;
        this.dataZakonczenia = dataZakonczenia;
        this.liczbaWypelnien = liczbaWypelnien;
        this.uzytkownicy = user;
        this.pytanias = pytanias;
    }

    public void initHashSetPytania() {
        this.pytanias = new HashSet<Pytania>();
    }

    public Integer getIdAnkiety() {
        return this.idAnkiety;
    }

    public void setIdAnkiety(Integer id) {
        this.idAnkiety = id;
    }

    public String getTytul() {
        return this.tytul;
    }

    public void setTytul(String tytul) {
        this.tytul = tytul;
    }

    public int getLiczbaPunktow() {
        return this.liczbaPunktow;
    }

    public void setLiczbaPunktow(int liczbaPunktow) {
        this.liczbaPunktow = liczbaPunktow;
    }

    public Date getDataRozpoczecia() {
        return this.dataRozpoczecia;
    }

    public void setDataRozpoczecia(Date dataRozpoczecia) {
        this.dataRozpoczecia = dataRozpoczecia;
    }

    public Date getDataZakonczenia() {
        return this.dataZakonczenia;
    }

    public void setDataZakonczenia(Date dataZakonczenia) {
        this.dataZakonczenia = dataZakonczenia;
    }

    public Set getPytanias() {
        return this.pytanias;
    }

    public void setPytanias(Set pytanias) {
        this.pytanias = pytanias;
    }

    public Integer getLiczbaWypelnien() {
        return liczbaWypelnien;
    }

    public void setLiczbaWypelnien(Integer liczbaWypelnien) {
        this.liczbaWypelnien = liczbaWypelnien;
    }

    public Uzytkownicy getUzytkownicy() {
        return uzytkownicy;
    }

    public void setUzytkownicy(Uzytkownicy uzytkownicy) {
        this.uzytkownicy = uzytkownicy;
    }


    public boolean isTheSame(Ankiety ankiety) {
        boolean result = false;
        if (idAnkiety.intValue() == ankiety.getIdAnkiety().intValue()) {
            if (tytul.equals(ankiety.getTytul())) {
                if (liczbaPunktow == ankiety.getLiczbaPunktow()) {
                    if (dataRozpoczecia.equals(ankiety.getDataRozpoczecia())) {
                        if (dataZakonczenia.equals(ankiety.getDataZakonczenia())) {
                            if (liczbaWypelnien.intValue() == ankiety.getLiczbaWypelnien().intValue()) {
                                result = true;
                            }
                        }
                    }
                }
            }
        }
        return result;
    }

}


