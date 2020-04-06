/**
 * Sample Skeleton for 'panelUzytkownika.fxml' Controller Class
 */

package com.Ankiety_PZ.test;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class PanelUzytkownikaController extends BulidStage{

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML
    private Button wyloguj;

    @FXML
    void wyloguj(ActionEvent event) {
        loadingFXML(event, SceneFXML.PANEL_LOGIN);
        activeScene(event, false, false);}

    @FXML
    void panelUzytkownikaButtonMakeAnkiet(ActionEvent event) {
        loadingFXML(event, SceneFXML.OKNO_ANKIETA_RADIO);
        activeScene(event, false, true);
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
            assert wyloguj != null : "fx:id=\"wyloguj\" was not injected: check your FXML file 'panelUzytkownika.fxml'.";
    }
}
