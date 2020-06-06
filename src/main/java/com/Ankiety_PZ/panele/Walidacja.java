package com.Ankiety_PZ.panele;

import com.Ankiety_PZ.hibernate.Uzytkownicy;
import org.apache.commons.codec.digest.DigestUtils;

/**
 * Klasa służy do walidacji edycji użytkownika.
 */

public class Walidacja {

    /**
     * Minimalna długośc hasła.
     */

    private static final int dlugoscHasla = 3;

    /**
     * Informacja przy błędnie wprowadzonym kodzie pocztowym.
     */

    private String blad_kod_pocztowy;

    /**
     * Informacja przy błędnie wprowadzonych hasłach.
     */

    private String blad_haslo;

    /**
     * Metoda sprawdzenie czy obowiązkowe pola nie są puste.
     *
     * @param mail             adres mail użytkownika.
     * @param kod_pocztowy_cz1 dwie pierwsze cyfry kodu pocztowego.
     * @param kod_pocztowy_cz2 trzy ostatnie cyfry kodu pocztowego.
     * @param imie             imię użytkownika.
     * @param miasto           miasto użytkownika.
     * @param nazwisko         nazwisko użytkownika.
     * @param numer_domu       numer domu użytkownika.
     * @param ulica            ulica użytkownika.
     * @return true jeśli wszystkie pola obowiązkowe są uzupełnione, w przeciwnym wypadku false
     */

    public boolean czyUzupelnionePola(String mail, String nazwisko, String imie, String miasto, String ulica,
                                      String numer_domu, String kod_pocztowy_cz1, String kod_pocztowy_cz2) {
        return (!mail.isEmpty() && !nazwisko.isEmpty() && !imie.isEmpty() &&
                !miasto.isEmpty() && !ulica.isEmpty() && !numer_domu.isEmpty() &&
                !kod_pocztowy_cz1.isEmpty() && !kod_pocztowy_cz2.isEmpty());
    }

    /**
     * Metoda sprawdza czy kod pocztowy składa się z liczb i czy ma odpowiedznią długość.
     *
     * @param kod_pocztowy_cz1 dwie pierwsze cyfry kodu pocztowego.
     * @param kod_pocztowy_cz2 trzy ostatnie cyfry kodu pocztowego.
     * @return true jeśli kod pocztowy jest poprawny, w przeciwnym razie false
     */

    public boolean czyPoprawnyKodPocztowy(String kod_pocztowy_cz1, String kod_pocztowy_cz2) {
        try {
            if (kod_pocztowy_cz1.length() == 2 && kod_pocztowy_cz2.length() == 3) {
                int postCodeFirstInt = Integer.parseInt(kod_pocztowy_cz1);
                int postCodeSecondInt = Integer.parseInt(kod_pocztowy_cz2);
                return true;
            } else {
                blad_kod_pocztowy = ("Kod pocztowy ma nieodpowiednią długość lub jest niepoprawny!");
            }
        } catch (IllegalArgumentException argumentException) {
            blad_kod_pocztowy = ("Kod pocztowy jest niepoprawny, podaj cyfry!");
            System.out.println(argumentException.getMessage());
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
        return false;
    }

    /**
     * Metoda sprawdza, czy hasło ma odpowiednią ilość znaków i czy nowe hasło jest takie samo jak powtórz hasło.
     * Sprawdza również czy dotychczasowe hasło zostało podane poprawnie, albo nie jest zmieniane i zostało puste.
     *
     * @param haslo         aktualne hasło użytkownika.
     * @param nowe_haslo    nowe hasło.
     * @param powtorz_haslo nowe hasło powtórnie wprowadzone przez użytkownika.
     * @param uzytkownik    aktualnie zalogowany użytkownik.
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
                nowe_haslo = DigestUtils.shaHex(nowe_haslo);
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
     * @param mail adres mail użytkownika.
     * @return true jeśli sdres e-mail posiada @, w przeciwnym wypadku false.
     */

    public boolean czyPoprawnyMail(String mail) {
        if (mail.indexOf('@') != -1) {
            return true;
        }
        return false;
    }

    public static int getDlugoscHasla() {
        return dlugoscHasla;
    }

    public String getBlad_kod_pocztowy() {
        return blad_kod_pocztowy;
    }

    public void setBlad_kod_pocztowy(String blad_kod_pocztowy) {
        this.blad_kod_pocztowy = blad_kod_pocztowy;
    }

    public String getBlad_haslo() {
        return blad_haslo;
    }

    public void setBlad_haslo(String blad_haslo) {
        this.blad_haslo = blad_haslo;
    }
}