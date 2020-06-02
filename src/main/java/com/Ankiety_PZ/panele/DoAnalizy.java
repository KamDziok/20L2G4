package com.Ankiety_PZ.panele;

/**
 * Klasa jest szablonem danych dla tabelki z odpowiedziami otwartymi w analizie ankiet.
 */

public class DoAnalizy {

    private String odpowiedz;

    public DoAnalizy(String odpowiedz) {
        this.odpowiedz = odpowiedz;
    }

    public String getOdpowiedz() {
        return odpowiedz;
    }
}
