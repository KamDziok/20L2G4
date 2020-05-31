package com.Ankiety_PZ.panele;

import com.Ankiety_PZ.hibernate.Ankiety;
import com.Ankiety_PZ.hibernate.Nagrody;
import com.Ankiety_PZ.hibernate.Pytania;
import com.Ankiety_PZ.hibernate.Uzytkownicy;
import com.Ankiety_PZ.query.AnkietyQuery;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

import java.util.Date;
import java.util.Iterator;

public class AnkieterTabelka extends BulidStage implements SetStartValues {
    public String tytul;
    public int liczbaPunktow;
    public Date dataZakonczenia;
    public Button buttonUsun;
    public Button buttonEdycja;
    public Button buttonAnaliza;

    AnkieterTabelka(Ankiety ankieta, Uzytkownicy curentUser) {
        tytul = ankieta.getTytul();
        liczbaPunktow = ankieta.getLiczbaPunktow();
        dataZakonczenia = ankieta.getDataZakonczenia();
        buttonUsun = new Button("Usuń");
        buttonUsun.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                loadingFXML(event, SceneFXML.PANEL_ANKIETERA);
                PanelAnkieterController panelAnkieterController = load.getController();
                AnkietyQuery query = new AnkietyQuery();
                query.deleteAnkiety(ankieta);

                panelAnkieterController.setStartValues(curentUser);
                activeScene(event, false, false);
            }
        });
        buttonEdycja = new Button("Edytuj");
        buttonEdycja.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Date data = new Date();
                if (data.compareTo(ankieta.getDataRozpoczecia()) == -1) {
                    loadingFXML(event, SceneFXML.TWORZENIE_ANKIETY);
                    PanelTworzeniaAnkietyController panelTworzeniaankietyController = load.getController();
                    panelTworzeniaankietyController.SetEdycja(true);
                    panelTworzeniaankietyController.setPytdoUsuniecia();
                    panelTworzeniaankietyController.setStartValues(curentUser);
                    panelTworzeniaankietyController.setStartValuesEdytujAnkiety(ankieta);
                    activeScene(event, false, false);
                    System.out.println("przekazanie ankiety do edycji");
                    System.out.println(ankieta);
                } else {
                    loadingFXML(event, SceneFXML.PANEL_ANKIETERA);
                    PanelAnkieterController panelAnkieterController = load.getController();
                    panelAnkieterController.ankietawtoku();
                    panelAnkieterController.setStartValuesAnkiety(ankieta);
                    panelAnkieterController.setStartValues(curentUser);
                    activeScene(event, false, false);
                }
            }
        });
        buttonAnaliza = new Button("Analiza");
        buttonAnaliza.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                loadingFXML(event, SceneFXML.OKNO_ANKIETA_ANALIZA);
                AnalizaAnkietController analizaAnkietController = load.getController();
                analizaAnkietController.setStartValuesAnkiety(ankieta);
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

    public Button getButtonEdycja() {
        return buttonEdycja;
    }

    public Button getButtonUsun() {
        return buttonUsun;
    }

    public Button getButtonAnaliza() {
        return buttonAnaliza;
    }

    @Override
    public void setStartValues(Uzytkownicy user) {


    }

    @Override
    public void setStartValuesAnkiety(Ankiety ankieta2) {


    }

    @Override
    public void setStartValuesPytanie(Pytania pytania) {

    }

    @Override
    public void setStartValuesNagroda(Nagrody nagroda) {

    }

    @Override
    public void setStartValuesIerator(Iterator<Pytania> iterator) {

    }
}
