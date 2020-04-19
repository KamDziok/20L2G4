package com.Ankiety_PZ.hibernate;


import javax.persistence.*;

@Entity(name = "OdpowiedziUzytkownicy")
@Table(name = "odpowiedzi_uzytkownicy")
public class PytaniaUzytkownicy {

    @EmbeddedId
    private PytaniaUzytkownicyId id;

    @ManyToOne
    @MapsId("idPytania")
    private Pytania pytanie;

    @ManyToOne
    @MapsId("idUzytkownika")
    private Uzytkownicy uzytkownik;

    @Column(name = "odpowiedz")
    private String odpowiedz;


    public PytaniaUzytkownicy() {
    }

    public PytaniaUzytkownicy(Pytania pytanie, Uzytkownicy uzytkownik, String odpowiedz) {
        this.id = new PytaniaUzytkownicyId(pytanie.getIdPytania(), uzytkownik.getIdUzytkownika());
        this.pytanie = pytanie;
        this.uzytkownik = uzytkownik;
        this.odpowiedz = odpowiedz;
    }

    public Pytania getPytanie() {
        return pytanie;
    }

    public void setPytanie(Pytania pytanie) {
        this.pytanie = pytanie;
    }

    public Uzytkownicy getUzytkownik() {
        return uzytkownik;
    }

    public void setUzytkownik(Uzytkownicy uzytkownik) {
        this.uzytkownik = uzytkownik;
    }

    public String getOdpowiedz() {
        return odpowiedz;
    }

    public void setOdpowiedz(String odpowiedz) {
        this.odpowiedz = odpowiedz;
    }
}
