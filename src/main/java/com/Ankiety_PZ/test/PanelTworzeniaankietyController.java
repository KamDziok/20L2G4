/**
 * Sample Skeleton for 'Dodawaniepytania.fxml' Controller Class
 */
package com.Ankiety_PZ.test;


import java.awt.*;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

import com.Ankiety_PZ.hibernate.Ankiety;
import com.Ankiety_PZ.hibernate.Odpowiedzi;
import com.Ankiety_PZ.hibernate.Pytania;
import com.Ankiety_PZ.hibernate.Uzytkownicy;
import com.Ankiety_PZ.query.AnkietyQuery;
import com.Ankiety_PZ.query.OdpowiedziQuery;
import com.Ankiety_PZ.query.PytaniaQuery;
import com.Ankiety_PZ.query.UzytkownicyQuery;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;


import javax.xml.crypto.Data;

public class PanelTworzeniaankietyController extends BulidStage implements SetStartValues{
    private Uzytkownicy curentUser;
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;



    @FXML
    private javafx.scene.control.TextField punkty;

    @FXML
    private javafx.scene.control.TextField dataOD;

    @FXML
    private javafx.scene.control.TextField trescTytulu; // Value injected by FXMLLoader

    @FXML
    private javafx.scene.control.TextField dataDo;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;
    private Pytania pytanie;

    private String tytul;
    private int liczbaPunktow;
    private Date dataRozpoczecia;
    private Date dataZakonczenia;
    private Ankiety ankiety;


    /**
     * Metoda obsługująca przyciśk wyloguj.
     *
     * @author HubertJakobsze
     * @param event zdarzenie, po którym funkcja ma się wywołać
     */

    @FXML
    void wylogujAction(ActionEvent event) {
        loadingFXML(event, SceneFXML.PANEL_LOGIN);
        PanelLoginController panelLoginController = load.getController();
        activeScene(event, false, false);
    }

    /**
     * Metoda obsługująca przyciśk anuluj.
     *
     * @author HubertJakobsze
     * @param event zdarzenie, po którym funkcja ma się wywołać
     */

    @FXML
    void anulujAction(ActionEvent event) {
        loadingFXML(event, SceneFXML.PANEL_ANKIETERA);
        PanelAnkieterController panelAnkieterController = load.getController();
        activeScene(event, false, false);
    }

    /**
     * Metoda obsługująca przyciśk dodaj pytanie.
     *
     * @author HubertJakobsze
     * @param event zdarzenie, po którym funkcja ma się wywołać
     */

    @FXML
    void dodajpytanieAction(ActionEvent event) {
        loadingFXML(event, SceneFXML.DODAJ_PYTANIE);
        DodawaniepytaniaController dodawaniepytaniaController  = load.getController();
        dodawaniepytaniaController.setStartValuesAnkiety(ankiety);
        dodawaniepytaniaController.setStartValues(curentUser);
        activeScene(event, false, false);
    }

    @Override
    public void setStartValues(Uzytkownicy user) {
        this.curentUser = user;
    }

    @Override
    public void setStartValuesAnkiety(Ankiety ankieta)
    {
        this.ankiety = ankieta;
        trescTytulu.setText(ankieta.getTytul());

    }

    @Override
    public void setStartValuesPytanie(Pytania pytania) {
        this.pytanie = pytania;

    }

    @FXML
    void ZapiszAction(ActionEvent event) {
        DateFormat dateFrm = new SimpleDateFormat("yyyy-MM-dd");
        tytul = trescTytulu.getText();
        liczbaPunktow = Integer.parseInt(punkty.getText());
        try {
            dataRozpoczecia = dateFrm.parse(dataOD.getText());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        try {
            dataZakonczenia = dateFrm.parse(dataDo.getText());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        ankiety.setTytul(tytul);
        ankiety.setLiczbaPunktow(liczbaPunktow);
        ankiety.setDataRozpoczecia(dataRozpoczecia);
        ankiety.setDataZakonczenia(dataZakonczenia);
        ankiety.setLiczbaWypelnien(0);
        ankiety.setUzytkownicy(curentUser);
        AnkietyQuery query = new AnkietyQuery();
        query.addAnkietyWithPytaniaAndOdpowiedzi(ankiety);



    }
}
