package com.Ankiety_PZ.panele;

/**
 * Klasa odpowiada za pytania z rozdzielaną liczbą punków.
 */

public class KlasaPomocnicza {

    private int count;
    private int punktowe;

    KlasaPomocnicza(int punktowe) {
        this.punktowe = punktowe;
        count = 0;
    }

    public void update() {
        count += 1;
    }

    public int getCount() {
        return count;
    }

    public int getPunktowe() {
        return punktowe;
    }
}
