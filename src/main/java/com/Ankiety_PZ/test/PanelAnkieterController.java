package com.Ankiety_PZ.test;
/**
 * Sample Skeleton for 'panelAnkieter.fxml' Controller Class
 */


import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class PanelAnkieterController extends BulidStage {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML
    private Button wyloguj2;

    @FXML
    private Button wyloguj1;

    @FXML // fx:id="panelAnkietButtonDodaj"
    private Button panelAnkietButtonDodaj; // Value injected by FXMLLoader
    /**
     * Metoda obsługująca przyciśk Dodaj
     *
     * @author HubertJakobsze
     * @param event zdarzenie, po którym funkcja ma się wywołać
     */

    @FXML
    void panelAnkietButtonDodajAction(ActionEvent event) {
        loadingFXML(event, SceneFXML.TWORZENIE_ANKIETY);
        PanelTworzeniaankietyController panelTworzeniaankietyController = load.getController();
        activeScene(event, false, false);
    }

    /**
     * Metoda obsługująca przyciśk wyloguj1.
     *
     * @author HubertJakobsze
     * @param event zdarzenie, po którym funkcja ma się wywołać
     */

    @FXML
    void wyloguj1Action(ActionEvent event) {
        loadingFXML(event, SceneFXML.PANEL_LOGIN);
       PanelLoginController panelLoginController = load.getController();
        activeScene(event, false, false);
    }

    /**
     * Metoda obsługująca przyciśk wyloguj2.
     *
     * @author HubertJakobsze
     * @param event zdarzenie, po którym funkcja ma się wywołać
     */
    @FXML
    void wyloguj2Action(ActionEvent event) {
        loadingFXML(event, SceneFXML.PANEL_LOGIN);
        PanelLoginController panelLoginController = load.getController();
        activeScene(event, false, false);
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert wyloguj2 != null : "fx:id=\"wyloguj2\" was not injected: check your FXML file 'PanelAnkieter.fxml'.";
        assert panelAnkietButtonDodaj != null : "fx:id=\"panelAnkietButtonDodaj\" was not injected: check your FXML file 'PanelAnkieter.fxml'.";
        assert wyloguj1 != null : "fx:id=\"wyloguj1\" was not injected: check your FXML file 'PanelAnkieter.fxml'.";

    }



}
