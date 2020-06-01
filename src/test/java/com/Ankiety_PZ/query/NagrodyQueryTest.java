package com.Ankiety_PZ.query;

import com.Ankiety_PZ.hibernate.Nagrody;
import com.Ankiety_PZ.hibernate.Uzytkownicy;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NagrodyQueryTest {

    private NagrodyQuery nq = new NagrodyQuery();

    private Nagrody nagrody = new Nagrody(100, "Nagroda test");
    private Uzytkownicy uzytkownikTrue = new Uzytkownicy("User", "A", "a", "a", 0,
            "a", "a", "1a", "aa-aaa", 110);
    private Uzytkownicy uzytkownikFalse = new Uzytkownicy("User", "B", "b", "b", 0,
            "b", "b", "1b", "bb-bbb", 90);

    @Test
    void checkUzytkownikCanGetNagrodyTestTrue(){
        assertTrue(nq.checkUzytkownikCanGetNagrody(nagrody, uzytkownikTrue));
    }

    @Test
    void checkUzytkownikCanGetNagrodyTestFalse(){
        assertFalse(nq.checkUzytkownikCanGetNagrody(nagrody, uzytkownikFalse));
    }

}
