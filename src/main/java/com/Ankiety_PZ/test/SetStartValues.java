package com.Ankiety_PZ.test;

import com.Ankiety_PZ.hibernate.Ankiety;
import com.Ankiety_PZ.hibernate.Nagrody;
import com.Ankiety_PZ.hibernate.Pytania;
import com.Ankiety_PZ.hibernate.Uzytkownicy;

import java.util.Iterator;

public interface SetStartValues {
    public abstract void setStartValues(Uzytkownicy user);
    public abstract void setStartValuesAnkiety(Ankiety ankieta);
    public abstract void setStartValuesPytanie(Pytania pytania);
    public abstract void setStartValuesNagroda(Nagrody nagroda);
    public abstract void setStartValuesIerator(Iterator<Pytania> iterator);
}
