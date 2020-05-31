package com.Ankiety_PZ.panele;

import com.Ankiety_PZ.hibernate.Uzytkownicy;

/**
 * Interfejs służy do ustawienia startowych wartości dla edytowanego użytkownika.
 */

public interface SetStartValuesEdycjaUzytkownika {

    /**
     * Metoda abstrakcyjna stworzona dla ustawienie przez jej nadpisanie edytowanego
     * użytkownika w panelu edycji użytkownika, edytowanego przez administratora.
     * @param user obiekt użytkownika.
     */

    public abstract void SetStartValuesEdycjaUzytkownika(Uzytkownicy user);
}
