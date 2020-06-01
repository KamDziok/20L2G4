package com.Ankiety_PZ.hibernate;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Klasa testująca dla Odpowiedzi
 */

public class OdpowiedziTest {

    private static Odpowiedzi odpowiedziA;
    private static Odpowiedzi odpowiedziACopy;
    private static Odpowiedzi odpowiedziB;

    static void setOdpowiedziACopy(Odpowiedzi odpowiedz) {
        odpowiedziACopy = new Odpowiedzi();
        odpowiedziACopy.setIdOdpowiedzi(odpowiedz.getIdOdpowiedzi());
        odpowiedziACopy.setOdpowiedz(odpowiedz.getOdpowiedz());
    }

    @BeforeAll
    static void setValuesInObcejt() {
        odpowiedziA = new Odpowiedzi();
        odpowiedziA.setIdOdpowiedzi(1);
        odpowiedziA.setOdpowiedz("odpowiedzA");
        setOdpowiedziACopy(odpowiedziA);
        odpowiedziB = new Odpowiedzi();
        odpowiedziB.setIdOdpowiedzi(33);
        odpowiedziB.setOdpowiedz("odpowiedzB");
    }

    @BeforeEach
    void clearOdpowiedziACopy() {
        setOdpowiedziACopy(odpowiedziA);
    }

    @Test
    void isTheSameTrue() {
        assertTrue(odpowiedziA.isTheSame(odpowiedziACopy));
    }

    @Test
    void isTheSameDifferentAll() {
        assertFalse(odpowiedziA.isTheSame(odpowiedziB));
    }

    @Test
    void isTheSameDifferentId() {
        odpowiedziACopy.setIdOdpowiedzi(23);
        assertFalse(odpowiedziA.isTheSame(odpowiedziACopy));
    }

    @Test
    void isTheSameDifferentOdpowiedz() {
        odpowiedziACopy.setOdpowiedz("odpowiedzACopy");
        assertFalse(odpowiedziA.isTheSame(odpowiedziACopy));
    }

}
