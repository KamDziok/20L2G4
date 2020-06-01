package com.Ankiety_PZ.panele;

import com.Ankiety_PZ.hibernate.Nagrody;

public class NagrodaPrzekazwanie extends BulidStage {

    public String tytul;
    public int liczbaPunktow;

    NagrodaPrzekazwanie(Nagrody nagroda) {
        tytul = nagroda.getNazwa();
        liczbaPunktow = nagroda.getLiczbaPunktow();
    }

    public String getTytul() {
        return tytul;
    }

    public int getLiczbaPunktow() {
        return liczbaPunktow;
    }

}
