package com.Ankiety_PZ.hibernate;

import com.Ankiety_PZ.hibernate.Uzytkownicy;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UzytkownicyTest {

    private Uzytkownicy uzytkownikAdd = new Uzytkownicy("User", "A", "a", "a", 0,
            "a", "a", "1a", "aa-aaa", 110);
    private Uzytkownicy uzytkownikSub = new Uzytkownicy("User", "B", "b", "b", 0,
            "b", "b", "1b", "bb-bbb", 90);

    @Test
    void updatePunktyTestAdd(){
        boolean result = uzytkownikAdd.updatePunkty(10, true);
        assertAll("userAdd",
                () -> assertEquals(uzytkownikAdd.getLiczbaPunktow(), 120),
                () -> assertTrue(result)
        );
    }

    @Test
    void updatePunktyTestSubTrue(){
        boolean result = uzytkownikSub.updatePunkty(10, false);
        assertAll("userAdd",
                () -> assertEquals(uzytkownikSub.getLiczbaPunktow(), 80),
                () -> assertTrue(result)
        );
    }

    @Test
    void updatePunktyTestSubFalse(){
        assertFalse(uzytkownikSub.updatePunkty(130, false));
    }
}
