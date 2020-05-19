package com.Ankiety_PZ.test;

import com.Ankiety_PZ.hibernate.Nagrody;
import com.Ankiety_PZ.hibernate.Uzytkownicy;
import com.Ankiety_PZ.query.NagrodyQuery;
import com.Ankiety_PZ.query.UzytkownicyQuery;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

import static javax.swing.JOptionPane.showMessageDialog;

public class NagrodaTabelka {
    private String nazwa;
    private int cena;
    private byte[] obrazek;
    private Button button;

    NagrodaTabelka(Nagrody nagroda, PanelUzytkownikaController controller) {
        nazwa = nagroda.getNazwa();
        cena = nagroda.getLiczbaPunktow();
        obrazek = nagroda.getZdjecie();
        button = new Button("Wymień");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Uzytkownicy curentUser = controller.getCurentUser();
                if (curentUser.updatePunkty(cena, false)) {
                    controller.updatePkt(String.valueOf(curentUser.getLiczbaPunktow()));
                    UzytkownicyQuery query = new UzytkownicyQuery();
                    NagrodyQuery queryNagrody = new NagrodyQuery();
                    query.updateUzytkownicy(curentUser);
                    queryNagrody.getNagrodyToUzytkownicy(nagroda, curentUser);
                } else {
                    showMessageDialog(null, "Biedaku ... nawet złota nie masz.");
                }

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
