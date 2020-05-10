package com.Ankiety_PZ.test;

import com.Ankiety_PZ.hibernate.Uzytkownicy;
import com.Ankiety_PZ.query.UzytkownicyQuery;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;


public class UzytkownicyTabelka  extends BulidStage {

    public String imie_i_nazwisko;
    public String nazwisko;
    public String mail;
    public int liczbaPunktow;
    public Button usun;
    public Button edytuj;

    UzytkownicyTabelka(Uzytkownicy uzytkownik) {
        imie_i_nazwisko = uzytkownik.getImie()+" "+uzytkownik.getNazwisko();
        liczbaPunktow = uzytkownik.getLiczbaPunktow();
        mail = uzytkownik.getMail();
        usun = new Button("Usuń");
        edytuj = new Button("Edytuj");
        usun.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                UzytkownicyQuery ban = new UzytkownicyQuery();
                ban.ban(uzytkownik);
            }
        });

        edytuj.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                loadingFXML(event, SceneFXML.PANEL_EDIT_USER);
                PanelEdycjiUzytkownikaController panelEdycjiUzytkownikaController = load.getController();
                panelEdycjiUzytkownikaController.setStartValues(uzytkownik);
                activeScene(event, false, false);
            }
        });
    }

    public String getImie_i_nazwisko() {
        return imie_i_nazwisko;
    }

    public String getMail() {
        return mail;
    }

    public int getLiczbaPunktow() {
        return liczbaPunktow;
    }

    public Button getUsun() {
        return usun;
    }

    public Button getEdytuj() {
        return edytuj;
    }
}
