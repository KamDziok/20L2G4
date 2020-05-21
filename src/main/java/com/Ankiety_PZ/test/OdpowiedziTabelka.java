package com.Ankiety_PZ.test;

import com.Ankiety_PZ.hibernate.Ankiety;
import com.Ankiety_PZ.hibernate.Odpowiedzi;
import com.Ankiety_PZ.hibernate.Pytania;
import com.Ankiety_PZ.hibernate.Uzytkownicy;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

import java.util.List;
import java.util.Set;


public class OdpowiedziTabelka extends BulidStage{
    public String treść;
    public Button buttonUsun;


    OdpowiedziTabelka(Odpowiedzi odpowiedzi, Ankiety ankieta, Uzytkownicy user, List<Odpowiedzi> odp , List<Pytania> pyt, Set usu, Boolean edycja2, DodawaniepytaniaController dod) {
        treść = odpowiedzi.getOdpowiedz();
        buttonUsun = new Button("Usuń");
        buttonUsun.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               // usu.remove(odpowiedzi);
              //  if(pytania.getRodzajPytania() == TypeOfQuestion.OPEN )
               // {dod.Inicjajca();}
              //  else dod.InicjajcaZ(pytania);
                usu.add(odpowiedzi);
                dod.SetAnuluj(usu);
                dod.getListaOdpTego().remove(odpowiedzi);
                //dod.SetAnuluj(odpowiedzi);
                dod.DaneUsniecia(pyt, odp);
                dod.Edycja(true);
                //dod.SetEdycja(edycja2);
                dod.setOdpowiedziSS((dod.getListaOdpTego()));
               // dod.setStartValuesPytanie(pytania);

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

