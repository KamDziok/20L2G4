package com.Ankiety_PZ.generowaniePDF;

import com.Ankiety_PZ.hibernate.Nagrody;
import com.Ankiety_PZ.hibernate.Uzytkownicy;
import pl.generowaniePDF.Generowanie;

/**
 * Klasa odpowiedzialna za połączenie z zewnętrznym pakietem odpowiedzialnym
 * za generowanie PDF z kuponem za wymianę nagrody.
 */

public class NagrodyGenerowaniePDF {

    /**
     * Konstruktor przekazuje odpowiednie parametry potrzebne do generowania PDF z nagrodą.
     * @param nagrody obiekt kupionej nagorody za punkty.
     * @param user obiekt użytkownika wymieniającego punkty na nagrodę.
     */

    public NagrodyGenerowaniePDF(Nagrody nagrody, Uzytkownicy user) {
        new Generowanie(nagrody.getIdNagrody(), nagrody.getLiczbaPunktow(), nagrody.getNazwa(),
                        user.getIdUzytkownika(), user.getImie(), user.getNazwisko());
    }
}
