package com.Ankiety_PZ.test;

import com.Ankiety_PZ.hibernate.*;
import com.Ankiety_PZ.query.OdpowiedziQuery;
import com.Ankiety_PZ.query.PytaniaQuery;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

import java.util.Iterator;
import java.util.List;

public class PytanieTabelka extends BulidStage implements SetStartValues{

    public String treść;
    public String Rpytanie;
    public Button buttonUsun;
    public Button buttonEdycja;

    PytanieTabelka(Ankiety ankieta, Pytania pytanie, Uzytkownicy user, List<Odpowiedzi> odp , List<Pytania> pyt) {
        treść = pytanie.getTresc();
        Rpytanie = pytanie.getRodzajPytania()+" ";
        buttonUsun = new Button("Usuń");
        buttonUsun.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                loadingFXML(event, SceneFXML.TWORZENIE_ANKIETY);
                PanelTworzeniaankietyController panelTworzeniaankietyController = load.getController();
                //PytaniaQuery query1 =new PytaniaQuery();
                System.out.println(pytanie);
                ankieta.getPytanias().remove(pytanie);
                //query1.deletePytania(pytanie);
                System.out.println(user);

                pyt.add(pytanie);
                System.out.println(pyt);
                panelTworzeniaankietyController.DaneUsniecia(pyt, odp);
                panelTworzeniaankietyController.SetEdycja(true);
                panelTworzeniaankietyController.setStartValues(user);
                panelTworzeniaankietyController.setStartValuesAnkiety(ankieta);

                activeScene(event, false, false);


            }
        });
        buttonEdycja = new Button("Edytuj");
        buttonEdycja.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                loadingFXML(event, SceneFXML.DODAJ_PYTANIE);
                DodawaniepytaniaController dodawaniepytaniaController  = load.getController();
                dodawaniepytaniaController.DaneUsniecia(pyt, odp);
                dodawaniepytaniaController.Edycja(false);
                dodawaniepytaniaController.SetEdycja(true);
                dodawaniepytaniaController.SetAnuluj(odp);
                dodawaniepytaniaController.setStartValues(user);
                dodawaniepytaniaController.setStartValuesAnkiety(ankieta);
                dodawaniepytaniaController.setStartValuesPytanie(pytanie);
                dodawaniepytaniaController.Inicjajca();
                if(pytanie.getRodzajPytania()== TypeOfQuestion.OPEN) pytanie.initHashSetOdpowiedzi();
                    dodawaniepytaniaController.setOdpowiedziSS(pytanie);
                activeScene(event, false, false);

            }
        });
    }

    public String getTreść() {
        return treść;
    }

    public String getRpytanie() {
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
