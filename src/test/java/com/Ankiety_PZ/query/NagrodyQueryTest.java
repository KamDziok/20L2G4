package com.Ankiety_PZ.query;

import com.Ankiety_PZ.hibernate.Nagrody;
import com.Ankiety_PZ.hibernate.Uzytkownicy;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Klasa testująca dla NagrodyQuery
 */

public class NagrodyQueryTest {

    private static NagrodyQuery nq;

    private static Nagrody nagrody;
    private static Uzytkownicy uzytkownikTrue;
    private static Uzytkownicy uzytkownikFalse;

    static void setUzytkownikTrue() {
        uzytkownikTrue = new Uzytkownicy("User", "A", "a", "a", 0,
                "a", "a", "1a", "aa-aaa", 110);
    }

    static void setUzytkownikFalse() {
        uzytkownikFalse = new Uzytkownicy("User", "B", "b", "b", 0,
                "b", "b", "1b", "bb-bbb", 90);
    }

    @BeforeAll
    static void setValuesInObcejt() {
        nq = new NagrodyQuery();
        nagrody = new Nagrody(100, "Nagroda test");
        setUzytkownikTrue();
        setUzytkownikFalse();
    }

    @BeforeEach
    void clearUzytkownicy() {
        setUzytkownikTrue();
        setUzytkownikFalse();
    }

    @Test
    void checkUzytkownikCanGetNagrodyTestTrue() {
        assertTrue(nq.checkUzytkownikCanGetNagrody(nagrody, uzytkownikTrue));
    }

    @Test
    void checkUzytkownikCanGetNagrodyTestFalse() {
        assertFalse(nq.checkUzytkownikCanGetNagrody(nagrody, uzytkownikFalse));
    }

}
