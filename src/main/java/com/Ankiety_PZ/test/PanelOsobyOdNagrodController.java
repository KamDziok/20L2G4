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

    private String imie_nazwisko_rola_tmp;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label imie_nazwisko_rola;

    @FXML
    private Button wyloguj;

    @FXML
    private Button panelnagroddodajnagrode;

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
        imie_nazwisko_rola_tmp = curentUser.getImie() + " " + curentUser.getNazwisko()+ " - konto zarządzania nagrodami";
        System.out.print(imie_nazwisko_rola_tmp);
        imie_nazwisko_rola.setText(imie_nazwisko_rola_tmp);
        imie_nazwisko_rola2.setText(imie_nazwisko_rola_tmp);
    }

    @FXML
    void initialize() {
        assert imie_nazwisko_rola != null : "fx:id=\"imie_nazwisko_rola\" was not injected: check your FXML file 'PanelOsobyOdNagrod.fxml'.";
        assert wyloguj != null : "fx:id=\"wyloguj\" was not injected: check your FXML file 'PanelOsobyOdNagrod.fxml'.";
        assert panelnagroddodajnagrode != null : "fx:id=\"panelnagroddodajnagrode\" was not injected: check your FXML file 'PanelOsobyOdNagrod.fxml'.";
        assert imie_nazwisko_rola2 != null : "fx:id=\"imie_nazwisko_rola2\" was not injected: check your FXML file 'PanelOsobyOdNagrod.fxml'.";
    }
}