package com.Ankiety_PZ.panele;

import com.Ankiety_PZ.hibernate.Ankiety;
import com.Ankiety_PZ.hibernate.Pytania;
import com.Ankiety_PZ.query.AnkietyQuery;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

import java.util.Date;
import java.util.Iterator;
import java.util.Set;

/**
 * Klasa obsługuje tabelę ankiet w panelu użytkownika.
 */

public class AnkietaTabelka extends BulidStage {

    /**
     * Tytuł ankiety
     */

    private String tytul;

    /**
     * Liczba punków za wypełnienie ankiety
     */

    private int liczbaPunktow;

    /**
     * Data zakończenia ankiety, po której przestanie być aktywna do wypełniania
     */

    private Date dataZakonczenia;

    /**
     * Przycisk do wypełnienia ankiety
     */

    private Button button;

    /**
     * Metoda ustawia pojedynczą ankietę w tabeli ankiet.
     * Metoda obsługuje również akcje wypełniania ankiet przyciskiem <code>wypełnij</code>.
     *
     * @param ankieta    obiekt ankiety do wypisania w tabeli.
     * @param controller PanelUzytkownikaController.
     */

    AnkietaTabelka(Ankiety ankieta, PanelUzytkownikaController controller) {
        tytul = ankieta.getTytul();
        liczbaPunktow = ankieta.getLiczbaPunktow();
        dataZakonczenia = ankieta.getDataZakonczenia();
        button = new Button("Wypełnij");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                AnkietyQuery query = new AnkietyQuery();
                Ankiety ankietyWithPytania = query.selectAnkietaWithPytaniaAndOdpowiedziByAnkiety(ankieta);
                Set<Pytania> zbior = ankietyWithPytania.getPytanias();
                zbior.forEach(pytanie -> {
                    pytanie.setAnkiety(ankieta);
                });
                Iterator<Pytania> iterator = zbior.iterator();
                if (iterator.hasNext()) {
                    loadingFXML(event, SceneFXML.OKNO_ANKIETA_RADIO);
                    OknoAnkietyRadioController radioController = load.getController();
                    radioController.setStartValuesIerator(iterator);
                    radioController.setStartValues(controller.getCurentUser());
                    radioController.setStartValuesPkt(liczbaPunktow);
                    radioController.setStartValuesPanelUzytkownikaController(controller);
                    activeScene(event, false, true);
                }
            }
        });
    }

    public String getTytul() {
        return tytul;
    }

    public int getLiczbaPunktow() {
        return liczbaPunktow;
    }

    public Date getDataZakonczenia() {
        return dataZakonczenia;
    }

    public Button getButton() {
        return button;
    }
}
