package com.Ankiety_PZ.test;

import com.Ankiety_PZ.hibernate.Ankiety;
import com.Ankiety_PZ.hibernate.Nagrody;
import com.Ankiety_PZ.hibernate.Pytania;
import com.Ankiety_PZ.hibernate.Uzytkownicy;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

import java.util.Date;

public class PytanieTabelka extends BulidStage implements SetStartValues{

    public String treść;
    public int Rpytanie;
    public Button buttonUsun;
    public Button buttonEdycja;

    PytanieTabelka(Ankiety ankieta, Pytania pytanie) {
        treść = pytanie.getTresc();
        Rpytanie = pytanie.getRodzajPytania();
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
                loadingFXML(event, SceneFXML.DODAJ_PYTANIE);
                DodawaniepytaniaController dodawaniepytaniaController  = load.getController();
                dodawaniepytaniaController.setStartValuesPytanie(pytanie);
                dodawaniepytaniaController.setStartValuesAnkiety(ankieta);
                activeScene(event, false, false);
                System.out.println("PRZEKAZYWANIE PYTANIE I ANKIETY DO EDYTOWANIA");
                System.out.println(ankieta);
                System.out.println(pytanie);
            }
        });
    }

    public String getTreść() {
        return treść;
    }

    public int getrodzajPytania() {
        return Rpytanie;
    }
    public Button getButtonEdycja() {
        return buttonEdycja;
    }

    public Button getButtonUsun() {
        return buttonUsun;
    }

    @Override
    public void setStartValues(Uzytkownicy user) {}
    @Override
    public void setStartValuesAnkiety(Ankiety ankieta) {
    }
    @Override
    public void setStartValuesPytanie(Pytania pytania) {}
    @Override
    public void setStartValuesNagroda(Nagrody nagroda) {}
}
