package com.Ankiety_PZ.panele;

import com.Ankiety_PZ.hibernate.Odpowiedzi;
import com.Ankiety_PZ.hibernate.Pytania;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

import java.util.List;
import java.util.Set;

/**
 * Klasa obsługuje tabelę odpowiedzi w panelu tworzenia ankiety.
 */

public class OdpowiedziTabelka extends BulidStage {

    /**
     * Treść odpowiedzi
     */

    public String tresc;

    /**
     * Przycisk do usunięcia odpowiedzi
     */

    public Button buttonUsun;

    /**
     * Metoda tworząca elemety tabeliu z odpoweidziami.
     * @param odpowiedzi obiekt pojedynczej odpowiedzi.
     * @param odp lista odpowiedzi utworzonych przez użytkownika.
     * @param pyt lista pytań utworzonych przez użytkownika.
     * @param usu  odpowiedzi do usunięcia.
     * @param dod DodawaniePytaniaController.
     */

    OdpowiedziTabelka(Odpowiedzi odpowiedzi, List<Odpowiedzi> odp, List<Pytania> pyt, Set usu, DodawaniePytaniaController dod) {
        tresc = odpowiedzi.getOdpowiedz();
        buttonUsun = new Button("Usuń");
        buttonUsun.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                usu.add(odpowiedzi);
                dod.SetAnuluj(usu);
                dod.getListaOdpTego().remove(odpowiedzi);
                dod.DaneUsniecia(pyt, odp);
                dod.Edycja(true);
                dod.setOdpowiedziSS((dod.getListaOdpTego()));
            }
        });

    }

    public String getTresc() {
        return tresc;
    }

    public Button getButtonUsun() {
        return buttonUsun;
    }

}

