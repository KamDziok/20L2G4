package com.Ankiety_PZ.hibernate;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Klasa testująca dla Pytania
 */

public class PytaniaTest {

    private static Pytania pytaniaA;
    private static Pytania pytaniaACopy;
    private static Pytania pytaniaB;

    static void setPytaniaA() {
        pytaniaA = new Pytania("pytanieA", 50, 1);
        pytaniaA.setIdPytania(1);
    }

    static void setPytaniaACopy(Pytania pytania) {
        pytaniaACopy = new Pytania(pytania.getTresc(), pytania.getPunktowe(), pytania.getRodzajPytania());
        pytaniaACopy.setIdPytania(pytania.getIdPytania());
    }

    static void setPytaniaB() {
        pytaniaB = new Pytania("pytanieB", 60, 0);
        pytaniaB.setIdPytania(1);
    }

    @BeforeAll
    static void setValuesInObcejt() {
        setPytaniaA();
        setPytaniaACopy(pytaniaA);
        setPytaniaB();
    }

    @BeforeEach
    void clearPytaniaACopy() {
        setPytaniaACopy(pytaniaA);
    }

    /**
     * Sprawdzenie czy funkcja isTheSame zwraca true, jeśli obie Pytania mają te mase wartości pól
     */
    @Test
    void isTehSameTrue() {
        assertTrue(pytaniaA.isTheSame(pytaniaACopy));
    }

    /**
     * Sprawdzenie czy funkcja isTheSame zwraca false, jeśli obie Pytania mają wszystkie wartości pól różne
     */
    @Test
    void isTheSameFalse() {
        assertFalse(pytaniaA.isTheSame(pytaniaB));
    }

    /**
     * Sprawdzenie czy funkcja isTheSame zwraca false, jeśli obie Odpowiedzi mają różne id
     */
    @Test
    void isTheSameDifferentId() {
        pytaniaACopy.setIdPytania(23);
        assertFalse(pytaniaA.isTheSame(pytaniaACopy));
    }

    /**
     * Sprawdzenie czy funkcja isTheSame zwraca false, jeśli obie Odpowiedzi mają różne treści
     */
    @Test
    void isTheSameDifferentTresc() {
        pytaniaACopy.setTresc("pytanieACopy");
        assertFalse(pytaniaA.isTheSame(pytaniaACopy));
    }

    /**
     * Sprawdzenie czy funkcja isTheSame zwraca false, jeśli obie Odpowiedzi mają różne wartości pola punktowe
     */
    @Test
    void isTheSameDifferentPunktowe() {
        pytaniaACopy.setPunktowe(pytaniaACopy.getPunktowe() + 50);
        assertFalse(pytaniaA.isTheSame(pytaniaACopy));
    }

    /**
     * Sprawdzenie czy funkcja isTheSame zwraca false, jeśli obie Odpowiedzi mają różne rodzaje pytania
     */
    @Test
    void isTheSameDifferentRodzajPytania() {
        pytaniaACopy.setRodzajPytania(3);
        assertFalse(pytaniaA.isTheSame(pytaniaACopy));
    }
}
