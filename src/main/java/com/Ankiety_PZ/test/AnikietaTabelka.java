package com.Ankiety_PZ.test;

import com.Ankiety_PZ.hibernate.Ankiety;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

import java.util.Date;

public class AnikietaTabelka extends BulidStage {

    private String tytul;
    private int liczbaPunktow;
    private Date dataZakonczenia;
    private Button button;

    AnikietaTabelka(Ankiety ankieta) {
        tytul = ankieta.getTytul();
        liczbaPunktow = ankieta.getLiczbaPunktow();
        dataZakonczenia = ankieta.getDataZakonczenia();
        button = new Button("Wypełnij");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                loadingFXML(event, SceneFXML.OKNO_ANKIETA_RADIO);
                activeScene(event, false, true);
            }
        });
    }

    public String getTytul() {
        return tytul;
    }

    public int getLiczbaPunktow() {
        return liczbaPunktow;
    }

    public Date getDataZakonczenia() {
        return dataZakonczenia;
    }

    public Button getButton() {
        return button;
    }
}
