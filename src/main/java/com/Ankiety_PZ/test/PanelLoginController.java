/**
 * Sample Skeleton for 'PanelLogin.fxml' Controller Class
 */

package com.Ankiety_PZ.test;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class PanelLoginController extends BulidStage {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="panelLoginTFEmail"
    private TextField panelLoginTFEmail; // Value injected by FXMLLoader

    @FXML // fx:id="panelLoginPFPassword"
    private TextField panelLoginPFPassword; // Value injected by FXMLLoader

    @FXML // fx:id="panelLoginButtonLogin"
    private Button panelLoginButtonLogin; // Value injected by FXMLLoader

    @FXML // fx:id="panelLoginButtonRegi"
    private Button panelLoginButtonRegi; // Value injected by FXMLLoader

    @FXML
    void panelLoginButtonLoginAcept(ActionEvent event) {

    }

    @FXML
    void panelLoginButtonRegiAcept(ActionEvent event) {
        loadingFXML(event, SceneFXML.PANEL_REGI);
        activeScene(event);
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert panelLoginTFEmail != null : "fx:id=\"panelLoginTFEmail\" was not injected: check your FXML file 'PanelLogin.fxml'.";
        assert panelLoginPFPassword != null : "fx:id=\"panelLoginPFPassword\" was not injected: check your FXML file 'PanelLogin.fxml'.";
        assert panelLoginButtonLogin != null : "fx:id=\"panelLoginButtonLogin\" was not injected: check your FXML file 'PanelLogin.fxml'.";
        assert panelLoginButtonRegi != null : "fx:id=\"panelLoginButtonRegi\" was not injected: check your FXML file 'PanelLogin.fxml'.";

    }
}
