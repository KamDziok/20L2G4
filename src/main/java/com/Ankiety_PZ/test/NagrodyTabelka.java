package com.Ankiety_PZ.test;

import com.Ankiety_PZ.hibernate.Nagrody;
import com.Ankiety_PZ.hibernate.Uzytkownicy;
import com.Ankiety_PZ.query.NagrodyQuery;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class NagrodyTabelka extends BulidStage {

    public String tytul;
    public int liczbaPunktow;
    public Button usun;
    public Button edytuj;
    private Nagrody nagroda;
    private Uzytkownicy user;

    NagrodyTabelka(Nagrody nagroda, Uzytkownicy uzytkownik, PanelOsobyOdNagrodController panel) {
        this.nagroda = nagroda;
        this.user = uzytkownik;
        tytul = nagroda.getNazwa();
        liczbaPunktow = nagroda.getLiczbaPunktow();
        usun = new Button("Usuń");
        edytuj = new Button ("Edytuj");
        usun.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                NagrodyQuery usun = new NagrodyQuery();
                usun.deactivateNagrody(nagroda);
                panel.setNagrody();
            }
        });
        edytuj.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                loadingFXML(event, SceneFXML.PANEL_EDIT_NAGROD);
                PanelEdycjiNagrodController panelEdycjiNagrodController = load.getController();
                panelEdycjiNagrodController.setStartValues(uzytkownik);
                panelEdycjiNagrodController.setStartValuesNagroda(nagroda);
                activeScene(event, false, false);
            }
        });
    }



    public String getTytul() {
        return tytul;
    }

    public int getLiczbaPunktow() {
        return liczbaPunktow;
    }

    public Button getUsun() {
        return usun;
    }

    public Button getEdytuj() {return edytuj;}
}
