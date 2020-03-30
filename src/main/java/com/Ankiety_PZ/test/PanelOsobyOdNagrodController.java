/**
 * Sample Skeleton for 'PanelOsobyOdNagrod.fxml' Controller Class
 */

package com.Ankiety_PZ.test;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class PanelOsobyOdNagrodController extends BulidStage {

    @FXML
    private Button panelnagroddodajnagrode;

    @FXML
    void panelnagroddodajnagrode(ActionEvent event) {
        loadingFXML(event, SceneFXML.PANEL_EDIT_NAGROD);
        activeScene(event, false, false);
    }

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert panelnagroddodajnagrode != null : "fx:id=\"panelnagroddodajnagrode\" was not injected: check your FXML file 'PanelEdycjiNagrod.fxml'.";
    }
}