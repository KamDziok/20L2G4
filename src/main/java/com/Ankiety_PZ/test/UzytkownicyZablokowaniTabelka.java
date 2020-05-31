package com.Ankiety_PZ.test;

import com.Ankiety_PZ.hibernate.Uzytkownicy;
import com.Ankiety_PZ.query.UzytkownicyQuery;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

/**
 * Klasa obsługuje tabelę zablokowanych użytkowników w panelu administratora.
 */

public class UzytkownicyZablokowaniTabelka extends BulidStage {

    /**
     * id użytownika zablokowanego
     */

    private int id;

    /**
     * Imie i nazwisko użytkownika zablokowanego
     */

    private String imie_i_nazwisko_z;

    /**
     * Mail użytkownika zablokowanego
     */

    private String mail_z;

    /**
     * Liczba punków użytkownika zablokowanego
     */

    private int liczbaPunktow_z;

    /**
     * Przycisk do odblokowowania użytkownika
     */

    private Button odblokuj;

    /**
     * Obiekt użytkownika
     */

    private Uzytkownicy user;

    /**
     * Metoda ustawia pojedynczego użytkownika w tabeli zablokowanych użytkowników.
     * Metoda obsługuje również akcje odblokowania użytkownika przyciskiem <code>odblokuj</code>.
     * @param uzytkownik obiekt użytkownika do wypisania w tabeli.
     * @param panel PanelAdminaController.
     */

    UzytkownicyZablokowaniTabelka(Uzytkownicy uzytkownik, PanelAdminaController panel) {
        this.user = uzytkownik;
        imie_i_nazwisko_z = uzytkownik.getImie() + " " + uzytkownik.getNazwisko();
        liczbaPunktow_z = uzytkownik.getLiczbaPunktow();
        id = uzytkownik.getIdUzytkownika();
        mail_z = uzytkownik.getMail();
        odblokuj = new Button("Odblokuj");

        odblokuj.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                UzytkownicyQuery unban = new UzytkownicyQuery();
                unban.unban(uzytkownik);
                panel.setUzytkownicy();
                panel.setUzytkownicyZablokowani();
            }
        });

    }

    public String getImie_i_nazwisko_z() {
        return imie_i_nazwisko_z;
    }

    public String getMail_z() {
        return mail_z;
    }

    public int getLiczbaPunktow_z() {
        return liczbaPunktow_z;
    }

    public Button getOdblokuj() {
        return odblokuj;
    }
}
