package com.Ankiety_PZ.test;

import com.Ankiety_PZ.hibernate.Ankiety;
import com.Ankiety_PZ.hibernate.Odpowiedzi;
import com.Ankiety_PZ.hibernate.Pytania;
import com.Ankiety_PZ.hibernate.Uzytkownicy;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

import java.util.List;


public class OdpowiedziTabelka extends BulidStage{
    public String treść;
    public Button buttonUsun;


    OdpowiedziTabelka(Odpowiedzi odpowiedzi, Ankiety ankieta,Uzytkownicy user, Pytania pytania, List<Odpowiedzi> odp , List<Pytania> pyt, List<Odpowiedzi> usu,Boolean edycja2) {
        treść = odpowiedzi.getOdpowiedz();
        buttonUsun = new Button("Usuń");
        buttonUsun.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                loadingFXML(event, SceneFXML.DODAJ_PYTANIE);
                DodawaniepytaniaController dodawaniepytaniaController  = load.getController();
                pytania.getOdpowiedzis().remove(odpowiedzi);
               // OdpowiedziQuery query = new OdpowiedziQuery();
                //query.deleteOdpowiedzi(odpowiedzi);
                dodawaniepytaniaController.Inicjajca();
                usu.add(odpowiedzi);
                dodawaniepytaniaController.SetAnuluj(usu);
                dodawaniepytaniaController.DaneUsniecia(pyt, odp);
                dodawaniepytaniaController.Edycja(true);
                dodawaniepytaniaController.SetEdycja(edycja2);
                //dodawaniepytaniaController.DaneUsniecia(pyt);
                dodawaniepytaniaController.setStartValues(user);
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

