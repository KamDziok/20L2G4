/**
 * Sample Skeleton for 'panelUzytkownika.fxml' Controller Class
 */

package com.Ankiety_PZ.test;

import com.Ankiety_PZ.hibernate.Uzytkownicy;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class PanelUzytkownikaController extends BulidStage implements SetStartValues{

    private Uzytkownicy curentUser;
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;
    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;
    @FXML
    private Button wyloguj;
    @FXML
    private TextField email;
    @FXML
    private TextField haslo;
    @FXML
    private TextField nowehaslo;
    @FXML
    private TextField hasloznowu;
    @FXML
    private Button imie;
    @FXML
    private TextField nazwisko;
    @FXML
    private TextField miejscowosc;
    @FXML
    private TextField ulica;
    @FXML
    private TextField budynek;
    @FXML
    private TextField lokal;
    @FXML
    private TextField kod;

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

    @Override
    public void setStartValues(Uzytkownicy user) {
        System.out.println(user.getImie());
        curentUser = user;
        email.setText(user.getMail());
        imie.setText(user.getImie());
        nazwisko.setText(user.getNazwisko());
        miejscowosc.setText(user.getMiejscowosc());
        ulica.setText(user.getUlica());
        budynek.setText(user.getNumerBudynku());
        lokal.setText(user.getNumerLokalu());
        kod.setText(user.getKodPocztowy());
    }
}
