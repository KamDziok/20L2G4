package com.Ankiety_PZ.test;

/**
 * Sample Skeleton for 'PanelAdmina.fxml' Controller Class
 */


import com.Ankiety_PZ.hibernate.Uzytkownicy;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class PanelAdminaController extends BulidStage implements SetStartValues {

    private Uzytkownicy curentUser;

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
    private Label imie_nazwisko_rola;

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
        assert imie_nazwisko_rola != null : "fx:id=\"imie_nazwisko_rola\" was not injected: check your FXML file 'PanelAdmina.fxml'.";
    }
    @Override
    public void setStartValues(Uzytkownicy user) {
        System.out.println(user.getImie());
        curentUser = user;
        imie_nazwisko_rola.setText(user.getImie()+" "+user.getNazwisko()+" - konto administratora");
       // email.setText(user.getMail());
      //  imie.setText(user.getImie());
      //  nazwisko.setText(user.getNazwisko());
     //   miejscowosc.setText(user.getMiejscowosc());
     //   ulica.setText(user.getUlica());
      //  budynek.setText(user.getNumerBudynku());
      //  kod.setText(user.getKodPocztowy());
    }

}
