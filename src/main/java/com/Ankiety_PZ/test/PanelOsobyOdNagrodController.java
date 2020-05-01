/**
 * Sample Skeleton for 'PanelOsobyOdNagrod.fxml' Controller Class
 */

package com.Ankiety_PZ.test;

import com.Ankiety_PZ.hibernate.Uzytkownicy;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class PanelOsobyOdNagrodController extends BulidStage implements SetStartValues {

    
    private Uzytkownicy curentUser;
    @FXML
    private Button panelnagroddodajnagrode;
    @FXML
    private Button wyloguj;
    @FXML
    private Label imie_nazwisko_rola;
    @FXML
    private Label imie_nazwisko_rola2;

    @FXML
    void panelnagroddodajnagrode(ActionEvent event) {
        loadingFXML(event, SceneFXML.PANEL_EDIT_NAGROD);
        activeScene(event, false, false);
    }

    @FXML
    void wyloguj(ActionEvent event) {
        loadingFXML(event, SceneFXML.PANEL_LOGIN);
        activeScene(event, false, false);
    }

    @Override
    public void setStartValues(Uzytkownicy user) {
        this.curentUser = user;
        String imie_nazwisko_rola_tmp = curentUser.getImie() + " " + curentUser.getNazwisko()+ " - konto zarządzania nagrodami";
        imie_nazwisko_rola.setText(imie_nazwisko_rola_tmp);
        imie_nazwisko_rola2.setText(imie_nazwisko_rola_tmp);
    }

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert panelnagroddodajnagrode != null : "fx:id=\"panelnagroddodajnagrode\" was not injected: check your FXML file 'PanelOsobyOdNagrod.fxml'.";
        assert wyloguj != null : "fx:id=\"wyloguj\" was not injected: check your FXML file 'PanelOsobyOdNagrod.fxml'.";
        assert imie_nazwisko_rola != null : "fx:id=\"imie_nazwisko_rola\" was not injected: check your FXML file 'PanelOsobyOdNagrod.fxml'.";
        assert imie_nazwisko_rola2 != null : "fx:id=\"imie_nazwisko_rola\" was not injected: check your FXML file 'PanelOsobyOdNagrod.fxml'.";
    }
}