package com.Ankiety_PZ.generowaniePDF;

import com.Ankiety_PZ.hibernate.Nagrody;
import com.Ankiety_PZ.hibernate.Uzytkownicy;
import pl.generowaniePDF.Generowanie;

public class NagrodyGenerowaniePDF {

    public NagrodyGenerowaniePDF(Nagrody nagrody, Uzytkownicy user) {
        new Generowanie(nagrody.getIdNagrody(), nagrody.getLiczbaPunktow(), nagrody.getNazwa(),
                        user.getIdUzytkownika(), user.getImie(), user.getNazwisko());
    }
}
