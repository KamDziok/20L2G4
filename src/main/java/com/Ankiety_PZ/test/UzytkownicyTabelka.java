package com.Ankiety_PZ.test;

import com.Ankiety_PZ.hibernate.Uzytkownicy;
import com.Ankiety_PZ.query.UzytkownicyQuery;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

/**
 * Klasa obsługuje tabelę użytkowników w panelu administratora.
 */

public class UzytkownicyTabelka extends BulidStage {

    /**
     * id użytownika
     */

    private int id;

    /**
     * Imie i nazwisko użytkownika zablokowanego
     */

    private String imie_i_nazwisko;

    /**
     * Mail użytkownika zablokowanego
     */

    private String mail;

    /**
     * Liczba punków użytkownika zablokowanego
     */

    private int liczbaPunktow;

    /**
     * Przycisk do blokowania użytkownika
     */

    private Button usun;

    /**
     * Przycisk do edycji użytkownika
     */

    private Button edytuj;

    /**
     * Obiekt użytkownika
     */

    private Uzytkownicy user;


    /**
     * Metoda ustawia pojedynczego użytkownika w tabeli użytkowników.
     * Metoda obsługuje również akcje blokowania użytkownika przyciskiem <code>zablokuj</code>
     * oraz edycję użytkownika przyciskiem <code>edytuj</code>.
     * @param uzytkownik obiekt użytkownika do wypisania w tabeli.
     * @param uzytkownik_zalogowany obiekt użytkownika zalogowanego do przekazania do panelu edycji.
     * @param panel PanelAdminaController.
     */


    UzytkownicyTabelka(Uzytkownicy uzytkownik, Uzytkownicy uzytkownik_zalogowany, PanelAdminaController panel) {
        this.user = uzytkownik;
        imie_i_nazwisko = uzytkownik.getImie() + " " + uzytkownik.getNazwisko();
        liczbaPunktow = uzytkownik.getLiczbaPunktow();
        id = uzytkownik.getIdUzytkownika();
        mail = uzytkownik.getMail();
        usun = new Button("Zablokuj");
        edytuj = new Button("Edytuj");
        usun.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                UzytkownicyQuery ban = new UzytkownicyQuery();
                ban.ban(uzytkownik);
                panel.setUzytkownicy();
                panel.setUzytkownicyZablokowani();
            }
        });
        edytuj.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                loadingFXML(event, SceneFXML.PANEL_EDIT_USER);
                PanelEdycjiUzytkownikaController panelEdycjiUzytkownikaController = load.getController();
                panelEdycjiUzytkownikaController.setStartValues(uzytkownik_zalogowany);
                panelEdycjiUzytkownikaController.SetStartValuesEdycjaUzytkownika(uzytkownik);
                activeScene(event, false, false);
            }
        });
    }

    public String getImie_i_nazwisko() {
        return imie_i_nazwisko;
    }

    public String getMail() {
        return mail;
    }

    public int getLiczbaPunktow() {
        return liczbaPunktow;
    }

    public Button getUsun() {
        return usun;
    }

    public Button getEdytuj() {
        return edytuj;
    }
}
