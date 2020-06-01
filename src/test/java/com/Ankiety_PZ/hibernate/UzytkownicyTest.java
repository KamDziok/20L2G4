package com.Ankiety_PZ.hibernate;

import com.Ankiety_PZ.hibernate.Uzytkownicy;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UzytkownicyTest {

    private Uzytkownicy uzytkownikAdd = new Uzytkownicy("User", "A", "a", "a", 0,
            "a", "a", "1a", "aa-aaa", 110);
    private Uzytkownicy uzytkownikSub = new Uzytkownicy("User", "B", "b", "b", 0,
            "b", "b", "1b", "bb-bbb", 90);

    @Test
    void updatePunktyTestAdd(){
        uzytkownikAdd.updatePunkty(10, true);
        assertEquals(uzytkownikAdd.getLiczbaPunktow(), 120);
    }

    @Test
    void updatePunktyTestSub(){
        uzytkownikSub.updatePunkty(10, false);
        assertEquals(uzytkownikSub.getLiczbaPunktow(), 80);
    }
}
