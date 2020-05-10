package com.Ankiety_PZ.test;

import com.Ankiety_PZ.hibernate.Uzytkownicy;
import com.Ankiety_PZ.query.UzytkownicyQuery;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class UzytkownicyZablokowaniTabelka extends BulidStage {

    private int id;
    private String imie_i_nazwisko_z;
    private String mail_z;
    private int liczbaPunktow_z;
    private Button odblokuj;

    private Uzytkownicy user;

    UzytkownicyZablokowaniTabelka(Uzytkownicy uzytkownik, PanelAdminaController panel) {
        this.user = uzytkownik;
        imie_i_nazwisko_z = uzytkownik.getImie()+" "+uzytkownik.getNazwisko();
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
