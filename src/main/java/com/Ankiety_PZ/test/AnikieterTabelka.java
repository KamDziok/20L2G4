package com.Ankiety_PZ.test;

import com.Ankiety_PZ.hibernate.Ankiety;
import com.Ankiety_PZ.hibernate.Nagrody;
import com.Ankiety_PZ.hibernate.Pytania;
import com.Ankiety_PZ.hibernate.Uzytkownicy;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

import java.util.Date;
import java.util.Iterator;

public class AnikieterTabelka extends BulidStage implements SetStartValues{
    private Uzytkownicy curentUser;
    public String tytul;
    public int liczbaPunktow;
    public Date dataZakonczenia;
    public Button buttonUsun;
    public Button buttonEdycja;

    AnikieterTabelka(Ankiety ankieta) {
        tytul = ankieta.getTytul();
        liczbaPunktow = ankieta.getLiczbaPunktow();
        dataZakonczenia = ankieta.getDataZakonczenia();
        buttonUsun = new Button("Usun");
        buttonUsun.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                loadingFXML(event, SceneFXML.TWORZENIE_ANKIETY);
                PanelTworzeniaankietyController panelTworzeniaankietyController = load.getController();
                activeScene(event, false, false);
            }
        });
        buttonEdycja = new Button("Edycja");
        buttonEdycja.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                loadingFXML(event, SceneFXML.TWORZENIE_ANKIETY);
                PanelTworzeniaankietyController panelTworzeniaankietyController = load.getController();
                panelTworzeniaankietyController.setStartValuesEdytujAnkiety(ankieta);
                activeScene(event, false, false);
                System.out.println("przekazanie ankiety do edycji");
                System.out.println(ankieta);
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

    @Override
    public void setStartValues(Uzytkownicy user) {
        this.curentUser = user;


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
