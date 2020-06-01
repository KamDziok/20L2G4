package com.Ankiety_PZ.hibernate;

import com.Ankiety_PZ.hibernate.Uzytkownicy;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UzytkownicyTest {

    private static Uzytkownicy uzytkownikAdd;
    private static Uzytkownicy uzytkownikSub;

    static void setUzytkownikAdd(){
        uzytkownikAdd = new Uzytkownicy("User", "A", "a", "a", 0,
                "a", "a", "1a", "aa-aaa", 110);
    }

    static void setUzytkownikSub(){
        uzytkownikSub = new Uzytkownicy("User", "B", "b", "b", 0,
                "b", "b", "1b", "bb-bbb", 90);
    }

    @BeforeAll
    static void setValuesInObcejt(){
        setUzytkownikAdd();
        setUzytkownikSub();
    }

    @BeforeEach
    void clearUzytkownikSub(){
        setUzytkownikSub();
    }

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
