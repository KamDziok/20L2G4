package com.Ankiety_PZ.test;

import com.Ankiety_PZ.hibernate.*;
import com.Ankiety_PZ.query.OdpowiedziQuery;
import com.Ankiety_PZ.query.PytaniaQuery;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

import java.util.Iterator;

public class PytanieTabelka extends BulidStage implements SetStartValues{

    public String treść;
    public String Rpytanie;
    public Button buttonUsun;
    public Button buttonEdycja;

    PytanieTabelka(Ankiety ankieta, Pytania pytanie, Uzytkownicy user) {
        treść = pytanie.getTresc();
        Rpytanie = String.valueOf(pytanie.getRodzajPytania());
        buttonUsun = new Button("Usuń");
        buttonUsun.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                loadingFXML(event, SceneFXML.TWORZENIE_ANKIETY);
                PanelTworzeniaankietyController panelTworzeniaankietyController = load.getController();
                PytaniaQuery query1 =new PytaniaQuery();
                System.out.println(pytanie);
                ankieta.getPytanias().remove(pytanie);
                query1.deletePytania(pytanie);
                System.out.println(user);
                panelTworzeniaankietyController.setStartValuesAnkiety(ankieta);
                panelTworzeniaankietyController.setStartValues(user);

                panelTworzeniaankietyController.SetEdycja(true);

                activeScene(event, false, false);
            }
        });
        buttonEdycja = new Button("Edytuj");
        buttonEdycja.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                loadingFXML(event, SceneFXML.DODAJ_PYTANIE);
                DodawaniepytaniaController dodawaniepytaniaController  = load.getController();
                dodawaniepytaniaController.Edycja(false);
                dodawaniepytaniaController.SetEdycja(true);
                dodawaniepytaniaController.setStartValues(user);
                dodawaniepytaniaController.setStartValuesAnkiety(ankieta);
                dodawaniepytaniaController.setStartValuesPytanie(pytanie);
                if(pytanie.getRodzajPytania()== TypeOfQuestion.OPEN) pytanie.initHashSetOdpowiedzi();
                    dodawaniepytaniaController.setOdpowiedziSS(pytanie);
                activeScene(event, false, false);

            }
        });
    }

    public String getTreść() {
        return treść;
    }

    public String getrodzajPytania() {
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

    @Override
    public void setStartValuesIerator(Iterator<Pytania> iterator) {

    }
}
