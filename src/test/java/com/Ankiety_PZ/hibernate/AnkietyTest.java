package com.Ankiety_PZ.hibernate;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Klasa testująca dla Ankiety
 */

public class AnkietyTest {

    private static Ankiety anietaA;
    private static Ankiety anietaACopy;
    private static Ankiety anietaB;

    static void setAnkietyA(Date date) {
        anietaA = new Ankiety("AnkietaA", 10, date, date, 0);
        anietaA.setIdAnkiety(1);
    }

    static void setAnkietyACopy(Date date) {
        anietaACopy = new Ankiety("AnkietaA", 10, date, date, 0);
        anietaACopy.setIdAnkiety(1);
    }

    @BeforeAll
    static void setValuesInObcejt() {
        Date date = new Date();
        setAnkietyA(date);
        setAnkietyACopy(date);
        anietaB = new Ankiety("AnkietaB", 149, new Date(), new Date(), 8);
        anietaB.setIdAnkiety(33);
    }

    @BeforeEach
    void clearAnkietyACopy() {
        setAnkietyACopy(anietaA.getDataZakonczenia());
    }

    /**
     * Sprawdzenie czy funkcja isTheSame zwraca true, jeśli obie ankiety mają te mase wartości pól
     */
    @Test
    void isTheSameTrue() {
        assertTrue(anietaA.isTheSame(anietaACopy));
    }

    /**
     * Sprawdzenie czy funkcja isTheSame zwraca false, jeśli obie ankiety mają wszystkie wartości pól różne
     */
    @Test
    void isTheSameDifferentAll() {
        assertFalse(anietaA.isTheSame(anietaB));
    }

    /**
     * Sprawdzenie czy funkcja isTheSame zwraca false, jeśli ankiety mają różne id
     */
    @Test
    void isTheSameDifferentId() {
        anietaACopy.setIdAnkiety(2);
        assertFalse(anietaA.isTheSame(anietaACopy));
    }

    /**
     * Sprawdzenie czy funkcja isTheSame zwraca false, jeśli ankiety mają różne tytuły
     */
    @Test
    void isTheSameDifferentTytul() {
        anietaACopy.setTytul("AnkietaB");
        assertFalse(anietaA.isTheSame(anietaACopy));
    }

    /**
     * Sprawdzenie czy funkcja isTheSame zwraca false, jeśli ankiety mają różne liczbę punktów
     */
    @Test
    void isTheSameDifferentLiczbaPunktow() {
        anietaACopy.setLiczbaPunktow(12);
        assertFalse(anietaA.isTheSame(anietaACopy));
    }

    /**
     * Sprawdzenie czy funkcja isTheSame zwraca false, jeśli ankiety mają różną liczbę wypełnień
     */
    @Test
    void isTheSameDifferentLiczbaWypelnien() {
        anietaACopy.setLiczbaWypelnien(12);
        assertFalse(anietaA.isTheSame(anietaACopy));
    }
}
