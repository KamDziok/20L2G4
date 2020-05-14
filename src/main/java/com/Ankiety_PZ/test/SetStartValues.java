package com.Ankiety_PZ.test;

import com.Ankiety_PZ.hibernate.*;

import java.util.Iterator;
import java.util.LinkedList;

public interface SetStartValues {
    public abstract void setStartValues(Uzytkownicy user);
    public abstract void setStartValuesAnkiety(Ankiety ankieta);
    public abstract void setStartValuesPytanie(Pytania pytania);
    public abstract void setStartValuesNagroda(Nagrody nagroda);
    default void setStartValuesIerator(Iterator<Pytania> iterator) {};
    default void setStartValuesListOdpowiedzi(LinkedList<OdpowiedziUzytkownicy> odpowiedziDoWyslania, LinkedList<PytaniaUzytkownicy> odpowiedziDoWyslaniaOtwarte) {};
    default void setStartValuesPkt(int punkty) {};
    default void setStartValuesPanelUzytkownikaController(PanelUzytkownikaController controller) {};
}
