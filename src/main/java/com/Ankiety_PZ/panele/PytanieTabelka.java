package com.Ankiety_PZ.panele;

import com.Ankiety_PZ.hibernate.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Klasa osługująca tworzenie tabelki z pytaniami w panelu tworzenia ankiety
 */

public class PytanieTabelka extends BulidStage implements SetStartValues {

    /**
     * Treść pytania.
     */

    public String tresc;

    /**
     * rodzja pytania.
     */
    public String Rpytanie;

    /**
     * przycisk usuń.
     */

    public Button buttonUsun;

    /**
     * przycisk edytuj.
     */

    public Button buttonEdycja;

    /**
     * metoda tworząca elemety tableki z pytaniami.
     */

    PytanieTabelka(Ankiety ankieta, Pytania pytanie, Uzytkownicy user, List<Odpowiedzi> odp,
                   Set<Pytania> pyt, List<Pytania> listaPytaU, PanelTworzeniaAnkietyController two) {
        tresc = pytanie.getTresc();
        Rpytanie = pytanie.getRodzajPytania() + " ";
        buttonUsun = new Button("Usuń");
        buttonUsun.setOnAction(new EventHandler<ActionEvent>() {
            /**
             * metoda obsługująca przycisk usuń.
             */
            @Override
            public void handle(ActionEvent event) {
                pyt.add(pytanie);
                two.getListaPytan().remove(pytanie);
                two.DaneUsniecia(listaPytaU, odp);
                two.setPytdoUsunieciaUs(pyt);
                two.setPytanieB(two.getListaPytan());
            }
        });
        buttonEdycja = new Button("Edytuj");
        buttonEdycja.setOnAction(new EventHandler<ActionEvent>() {
            /**
             * metoda obsługująca przycisk edytuj.
             */
            @Override
            public void handle(ActionEvent event) {
                loadingFXML(event, SceneFXML.DODAJ_PYTANIE);
                DodawaniePytaniaController dodawaniepytaniaController = load.getController();
                if (pytanie.getRodzajPytania() == TypeOfQuestion.OPEN) {
                    dodawaniepytaniaController.Inicjajca();
                } else {
                    dodawaniepytaniaController.InicjajcaZ(pytanie);
                }
                dodawaniepytaniaController.DaneUsniecia(listaPytaU, odp);
                dodawaniepytaniaController.Edycja(false);
                dodawaniepytaniaController.SetEdycja(two.edycja2);
                dodawaniepytaniaController.setStartValues(user);
                dodawaniepytaniaController.setLisaPytanPrzekazana(two.getListaPytan());
                dodawaniepytaniaController.setStartValuesAnkiety(ankieta);
                dodawaniepytaniaController.setStartValuesPytanie(pytanie);
                if (pytanie.getRodzajPytania() == TypeOfQuestion.OPEN) pytanie.initHashSetOdpowiedzi();
                dodawaniepytaniaController.setOdpowiedziSS(dodawaniepytaniaController.getListaOdpTego());
                activeScene(event, false, false);

            }
        });
    }

    public String getTresc() {
        return tresc;
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
    public void setStartValues(Uzytkownicy user) {
    }

    @Override
    public void setStartValuesAnkiety(Ankiety ankieta) {
    }

    @Override
    public void setStartValuesPytanie(Pytania pytania) {
    }

    @Override
    public void setStartValuesNagroda(Nagrody nagroda) {
    }

    @Override
    public void setStartValuesIerator(Iterator<Pytania> iterator) {

    }
}
