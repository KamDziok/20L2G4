package com.Ankiety_PZ.test;

import com.Ankiety_PZ.hibernate.Uzytkownicy;

public class Walidacja {

    /**
     * Minimalna długośc hasłą.
     */
    private static final int dlugoscHasla = 3;

    private String blad_kod_pocztowy;

    public String getBlad_haslo() {
        return blad_haslo;
    }

    public void setBlad_haslo(String blad_haslo) {
        this.blad_haslo = blad_haslo;
    }

    private String blad_haslo;

    public static int getDlugoscHasla() {
        return dlugoscHasla;
    }

    public String getBlad_kod_pocztowy() {
        return blad_kod_pocztowy;
    }

    public void setBlad_kod_pocztowy(String blad_kod_pocztowy) {
        this.blad_kod_pocztowy = blad_kod_pocztowy;
    }

    /**
     * Metoda sprawdzenie czy obowiązkowe pola nie są puste.
     *
     * @return true jeśli wszystkie pola obowiązkowe są uzupełnione, w przeciwnym wypadku false
     */
    public boolean czyUzupelnionePola(String mail, String nazwisko, String imie, String miasto, String ulica, String numer_domu, String kod_pocztkowy_cz1, String kod_pocztowy_cz2) {
        return (!mail.isEmpty() && !nazwisko.isEmpty() &&
                !imie.isEmpty() && !miasto.isEmpty() && !ulica.isEmpty() && !numer_domu.isEmpty() &&
                !kod_pocztkowy_cz1.isEmpty() && !kod_pocztowy_cz2.isEmpty());
    }

    /**
     * Metoda sprawdza czy kod pocztowy składa się z liczb i czy ma odpowiedznią długość.
     *
     * @return true jeśli kod pocztowy jest poprawny, w przeciwnym razie false
     * @author KamDziok
     */
    public boolean czyPoprawnyKodPocztowy(String kod_pocztowy_cz1, String kod_pocztowy_cz2) {
        try {
            if (kod_pocztowy_cz1.length() == 2 && kod_pocztowy_cz2.length() == 3) {
                return true;
            } else {
                blad_kod_pocztowy = ("Kod pocztowy ma niepoprawną długość lub jest niepoprawny!");
            }
        } catch (IllegalArgumentException argumentException) {
            blad_kod_pocztowy = ("Kod pocztowy jest niepoprawny!");
            System.out.println(argumentException.getMessage());
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
        return false;
    }


    /**
     * Metoda sprawdza, czy hasło ma odpowiednia ilośc znaków i czy nowe hasło jest takie samo jak powtórz hasło.
     * Sprawdza również czy dotychczasowe hasło zostało podane poprawnie, albo nie jest zmieniane i zostało puste.
     *
     * @return true jeśli hasło ma odpowiednią długość i jest takie samo jak powtórz hasło i dotychczasowe hasło
     * zostało podane poprawnie, w przeciwnym wypadku false.
     */
    public boolean sprawdzHaslo(String haslo, String powtorz_haslo, String nowe_haslo, Uzytkownicy uzytkownik) {
        if (((powtorz_haslo.length() < dlugoscHasla) || (nowe_haslo.length() < dlugoscHasla)) && (!haslo.isEmpty())) {

            blad_haslo = ("Hasło jest za krótkie lub nie wypełniłeś wszystkich pól!");
        } else {
            if (!nowe_haslo.equals(powtorz_haslo)) {
                blad_haslo = ("Hasła nie są takie same!");
            } else {
                if (haslo.equals(uzytkownik.getHaslo())) {
                    return true;
                } else if (haslo.isEmpty() && nowe_haslo.isEmpty() && powtorz_haslo.isEmpty()) {
                    return true;
                } else {
                    blad_haslo = ("Podałeś niepoprawne hasło do konta!");
                }
            }
        }
        return false;
    }


    /**
     * Metoda sprawdza, czy w podanym adresie e-mail znajduje się @.
     *
     * @return true jeśli sdres e-mail posiada @, w przeciwnym wypadku false.
     */
    public boolean czyPoprawnyMail(String mail) {
        if (mail.indexOf('@') != -1) {
            return true;
        }
        return false;
    }
}