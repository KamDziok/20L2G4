package com.Ankiety_PZ.panele;

/**
 * Klasa jest wykorzystywana do liczenia ilości wystąpień takich samych odpowiedzi typu punktowego i procentowego.
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
