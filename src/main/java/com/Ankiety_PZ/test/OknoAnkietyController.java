/**
 * Sample Skeleton for 'oknoAnkiety.fxml' Controller Class
 */

package com.Ankiety_PZ.test;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class OknoAnkietyController extends BulidStage{

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML
    void oknoAnkietyButtonFinish(ActionEvent event) {
        deleteStage(event);
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {

    }
}
