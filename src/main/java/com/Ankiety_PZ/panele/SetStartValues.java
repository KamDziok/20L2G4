package com.Ankiety_PZ.panele;

import com.Ankiety_PZ.hibernate.*;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * Interfejs służy do ustawienia startowych wartości.
 */

public interface SetStartValues {

    /**
     * Metoda abstrakcyjna stworzona dla ustawienia przez jej nadpisanie użytkownika.
     *
     * @param user obiekt użytkownika.
     */

    public abstract void setStartValues(Uzytkownicy user);

    /**
     * Metoda abstrakcyjna stworzona dla ustawienia przez jej nadpisanie ankiety.
     *
     * @param ankieta obiekt ankiety.
     */

    public abstract void setStartValuesAnkiety(Ankiety ankieta);

    /**
     * Metoda abstrakcyjna stworzona dla ustawienia przez jej nadpisanie pytania.
     *
     * @param pytania obiekt pytania.
     */

    public abstract void setStartValuesPytanie(Pytania pytania);

    /**
     * Metoda abstrakcyjna stworzona dla ustawienia przez jej nadpisanie nagroda.
     *
     * @param nagroda obiekt nagrody.
     */

    public abstract void setStartValuesNagroda(Nagrody nagroda);

    /**
     * @param iterator obiekt użytkownika.
     */

    default void setStartValuesIerator(Iterator<Pytania> iterator) {
    }

    /**
     * @param odpowiedziDoWyslania odpowiedzi do wysłania.
     * @param odpowiedziDoWyslaniaOtwarte odpowiedzi do wysłania z pytań otwartych.
     */

    default void setStartValuesListOdpowiedzi(LinkedList<OdpowiedziUzytkownicy> odpowiedziDoWyslania,
                                              LinkedList<PytaniaUzytkownicy> odpowiedziDoWyslaniaOtwarte) {
    }

    /**
     * @param punkty obiekt użytkownika.
     */

    default void setStartValuesPkt(int punkty) {
    }

    /**
     * @param controller PanelUzytkownikaController.
     */

    default void setStartValuesPanelUzytkownikaController(PanelUzytkownikaController controller) {
    }


}
