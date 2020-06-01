package com.Ankiety_PZ.panele;

/**
 * Klasa deklaruje typy uprawnień użytkowników.
 */

public class Permissions {

    /**
     * Standardowy użytkownik, który wypełnia ankiety, zbiera punkty i wymienia na nagrody.
     */

    public static final int KLIENT = 0;

    /**
     * Ankieter odpowiedzialny za tworzenie ankiet, ich edycję i analizę.
     */

    public static final int ANKIETER = 1;

    /**
     * Osoba odpowiedzalna za nagrody dodaje nowe, edytuje i usuwa nagrody.
     */

    public static final int OSOBA_OD_NAGROD = 2;

    /**
     * Administrator odpowiedzialny jest za zarządzanie użytkownikami, blokowanie, odblokowanie i edycję.
     */

    public static final int ADMIN = 3;


    /**
     * Uprawnienie odpowiedzialne za blokadę konta użytkownika.
     */

    public static final int BAN = -1;

}
