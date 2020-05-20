package com.Ankiety_PZ.test;

import com.Ankiety_PZ.hibernate.*;
import com.Ankiety_PZ.query.OdpowiedziQuery;
import com.Ankiety_PZ.query.PytaniaQuery;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

import java.util.List;


public class OdpowiedziTabelka extends BulidStage{
    public String treść;
    public Button buttonUsun;


    OdpowiedziTabelka(Odpowiedzi odpowiedzi, Ankiety ankieta, Pytania pytania) {
        treść = odpowiedzi.getOdpowiedz();
        buttonUsun = new Button("Usuń");
        buttonUsun.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                loadingFXML(event, SceneFXML.DODAJ_PYTANIE);
                DodawaniepytaniaController dodawaniepytaniaController  = load.getController();
                pytania.getOdpowiedzis().remove(odpowiedzi);
                OdpowiedziQuery query = new OdpowiedziQuery();
                query.deleteOdpowiedzi(odpowiedzi);
                dodawaniepytaniaController.Edycja(true);
                dodawaniepytaniaController.setStartValuesAnkiety(ankieta);
                dodawaniepytaniaController.setStartValuesPytanie(pytania);


                activeScene(event, false, false);

            }
        });

    }

    public String getTreść() {
        return treść;
    }

    public Button getButtonUsun() {
        return buttonUsun;
    }

}

