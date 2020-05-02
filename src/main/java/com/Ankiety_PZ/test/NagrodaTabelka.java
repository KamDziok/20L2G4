package com.Ankiety_PZ.test;

import com.Ankiety_PZ.hibernate.Nagrody;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class NagrodaTabelka {
    private String nazwa;
    private int cena;
    private byte[] obrazek;
    private Button button;

    NagrodaTabelka(Nagrody nagroda) {
        nazwa = nagroda.getNazwa();
        cena = nagroda.getLiczbaPunktow();
        obrazek = nagroda.getZdjecie();
        button = new Button("Wymień");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

            }
        });
    }

    public String getNazwa() {
        return nazwa;
    }

    public int getCena() {
        return cena;
    }

    public byte[] getObrazek() {
        return obrazek;
    }

    public Button getButton() {
        return button;
    }
}
