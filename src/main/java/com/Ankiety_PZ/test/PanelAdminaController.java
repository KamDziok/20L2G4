package com.Ankiety_PZ.test;

/**
 * Sample Skeleton for 'PanelAdmina.fxml' Controller Class
 */


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import java.net.URL;
import java.util.ResourceBundle;

public class PanelAdminaController extends BulidStage {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML
    private Button wyloguj;

    @FXML
    void wyloguj(ActionEvent event) {
        loadingFXML(event, SceneFXML.PANEL_LOGIN);
        activeScene(event, false, false);
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert wyloguj != null : "fx:id=\"wyloguj\" was not injected: check your FXML file 'PanelAdmina.fxml'.";
    }
}
