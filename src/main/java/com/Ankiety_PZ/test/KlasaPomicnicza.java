package com.Ankiety_PZ.test;

public class KlasaPomicnicza {
    private int count;
    private int punktowe;

    KlasaPomicnicza(int punktowe) {
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
