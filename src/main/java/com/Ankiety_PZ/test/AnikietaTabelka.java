package com.Ankiety_PZ.test;

import com.Ankiety_PZ.hibernate.Ankiety;
import com.Ankiety_PZ.hibernate.Pytania;
import com.Ankiety_PZ.query.AnkietyQuery;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

import java.util.Date;
import java.util.Iterator;
import java.util.Set;

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
                AnkietyQuery query = new AnkietyQuery();
                Ankiety ankietyWithPytania = query.selectAnkietaWithPytaniaAndOdpowiedziByAnkiety(ankieta);
                Set<Pytania> zbior = ankietyWithPytania.getPytanias();
                Iterator<Pytania> iterator = zbior.iterator();
                if (iterator.hasNext()) {
                    loadingFXML(event, SceneFXML.OKNO_ANKIETA_RADIO);
                    OknoAnkietyRadioController radioController = load.getController();
                    radioController.setStartValuesIerator(iterator);
                    activeScene(event, false, true);
                }
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
