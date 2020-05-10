package com.Ankiety_PZ.hibernate;


import com.Ankiety_PZ.test.TypeOfQuestion;

import javax.persistence.*;

@Entity(name = "OdpowiedziUzytkownicy")
@Table(name = "odpowiedzi_uzytkownicy")
public class OdpowiedziUzytkownicy {

    @EmbeddedId
    private OdpowiedziUzytkownicyId id;

    @ManyToOne
    @MapsId("idOdpowiedzi")
    private Odpowiedzi odpowiedz;

    @ManyToOne
    @MapsId("idUzytkownika")
    private Uzytkownicy uzytkownik;

    @Column(name = "punktowe")
    private Integer punktowe;

    public OdpowiedziUzytkownicy() {
    }

    public OdpowiedziUzytkownicy(Odpowiedzi odpowiedz, Uzytkownicy uzytkownik) {
        this.odpowiedz = odpowiedz;
        this.uzytkownik = uzytkownik;
        this.punktowe = TypeOfQuestion.USER_ANSWER_NULL;
        this.id = new OdpowiedziUzytkownicyId(odpowiedz.getIdOdpowiedzi(), uzytkownik.getIdUzytkownika());
    }

    public OdpowiedziUzytkownicy(Odpowiedzi odpowiedz, Uzytkownicy uzytkownik, Integer punktowe) {
        this.odpowiedz = odpowiedz;
        this.uzytkownik = uzytkownik;
        this.punktowe = punktowe;
        this.id = new OdpowiedziUzytkownicyId(odpowiedz.getIdOdpowiedzi(), uzytkownik.getIdUzytkownika());
    }

    public Odpowiedzi getOdpowiedz() {
        return odpowiedz;
    }

    public void setOdpowiedz(Odpowiedzi odpowiedz) {
        this.odpowiedz = odpowiedz;
    }

    public Uzytkownicy getUzytkownik() {
        return uzytkownik;
    }

    public void setUzytkownik(Uzytkownicy uzytkownik) {
        this.uzytkownik = uzytkownik;
    }

    public int getPunktowe() {
        return punktowe;
    }

    public void setPunktowe(Integer punktowe) {
        this.punktowe = punktowe;
    }
}
