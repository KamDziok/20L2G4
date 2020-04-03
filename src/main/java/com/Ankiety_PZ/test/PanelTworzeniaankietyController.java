/**
 * Sample Skeleton for 'Dodawaniepytania.fxml' Controller Class
 */
package com.Ankiety_PZ.test;


import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class PanelTworzeniaankietyController extends BulidStage {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {

    }

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
        activeScene(event, false, false);
    }





}
