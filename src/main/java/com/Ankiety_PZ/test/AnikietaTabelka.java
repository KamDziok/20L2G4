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
//                Pytania pytanie = iterator.next();
//                switch (pytanie.getRodzajPytania()) {
//                    case 0: //  jednokrotnego wyboru
                if (iterator.hasNext()) {
                    loadingFXML(event, SceneFXML.OKNO_ANKIETA_RADIO);
                    OknoAnkietyRadioController radioController = load.getController();
                    radioController.setStartValuesIerator(iterator);
                    activeScene(event, false, true);
                }
//                        break;
//                    case 1: //  wielokrotnego wyboru
//                        loadingFXML(event, SceneFXML.OKNO_ANKIETA_CHECK);
//                        OknoAnkietyCheckController checkController = load.getController();
//                        checkController.setStartValuesIerator(iterator);
//                        activeScene(event, false, true);
//                        break;
//                    case 2: //  otwarte
//                        loadingFXML(event, SceneFXML.OKNO_ANKIETA_OPEN);
//                        OknoAnkietyOpenController openController = load.getController();
//                        openController.setStartValuesIerator(iterator);
//                        activeScene(event, false, true);
//                        break;
//                    case 3: //  punktowe
//                        loadingFXML(event, SceneFXML.PANEL_UZYTKOWNIKA);
//                        PanelUzytkownikaController panelUzytkownikaController = load.getController();
//                        radioController.setStartValuesPytanie(pytanie);
//                        activeScene(event, false, true);
//                        break;
//                    case 4: //  procentowe
//                        loadingFXML(event, SceneFXML.PANEL_UZYTKOWNIKA);
//                        PanelUzytkownikaController panelUzytkownikaController = load.getController();
//                        radioController.setStartValuesPytanie(pytanie);
//                        activeScene(event, false, true);
//                        break;
//                }
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
