package com.Ankiety_PZ.test;

import com.Ankiety_PZ.hibernate.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

import java.util.List;


public class OdpowiedziTabelka extends BulidStage{
    public String treść;
    public Button buttonUsun;

    OdpowiedziTabelka(String odpowiedzi, Ankiety ankieta, Pytania pytania, List<String> list) {
        treść = odpowiedzi;
        buttonUsun = new Button("Usuń");
        buttonUsun.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                loadingFXML(event, SceneFXML.DODAJ_PYTANIE);
                DodawaniepytaniaController dodawaniepytaniaController  = load.getController();
                dodawaniepytaniaController.SetEdycja(true);

                dodawaniepytaniaController.setStartValuesAnkiety(ankieta);
                dodawaniepytaniaController.setStartValuesPytanie(pytania);
                dodawaniepytaniaController.usun(odpowiedzi , list);

                activeScene(event, false, false);
                System.out.println("PRZEKAZYWANIE PYTANIE I ANKIETY DO EDYTOWANIA");
                System.out.println(ankieta);
              ///  System.out.println(pytanie);



            }
        });

    }

    OdpowiedziTabelka(Odpowiedzi odpowiedzi, Ankiety ankieta, Pytania pytania, List<String> list) {
        treść = odpowiedzi.getOdpowiedz();
        buttonUsun = new Button("Usun");
        buttonUsun.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                loadingFXML(event, SceneFXML.DODAJ_PYTANIE);
                DodawaniepytaniaController dodawaniepytaniaController  = load.getController();
                dodawaniepytaniaController.SetEdycja(true);

                dodawaniepytaniaController.setStartValuesAnkiety(ankieta);
                dodawaniepytaniaController.usunBAZA(odpowiedzi , list, pytania);
                dodawaniepytaniaController.setStartValuesPytanie(pytania);
                activeScene(event, false, false);
                System.out.println("PRZEKAZYWANIE PYTANIE I ANKIETY DO EDYTOWANIA");
                System.out.println(ankieta);

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

