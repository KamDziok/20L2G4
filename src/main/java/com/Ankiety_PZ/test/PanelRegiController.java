/**
 * Sample Skeleton for 'PanelRegi.fxml' Controller Class
 */

package com.Ankiety_PZ.test;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class PanelRegiController extends BulidStage {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="panelRegiButtonRegi"
    private Button panelRegiButtonRegi; // Value injected by FXMLLoader

    @FXML
    void panelRegiButtonRegiAction(ActionEvent event) {
        deleteStage(event);
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert panelRegiButtonRegi != null : "fx:id=\"panelRegiButtonRegi\" was not injected: check your FXML file 'PanelRegi.fxml'.";

    }

}
