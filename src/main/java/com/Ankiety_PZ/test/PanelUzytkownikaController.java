/**
 * Sample Skeleton for 'panelUzytkownika.fxml' Controller Class
 */

package com.Ankiety_PZ.test;

import com.Ankiety_PZ.hibernate.Uzytkownicy;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
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
    private Label punkty;
    @FXML
    private Label labelPunkty;
    @FXML
    private Label punktyNagrody;
    @FXML
    private Label labelPunktyNagrody;
    @FXML
    private Label punktyUstawienia;
    @FXML
    private Label labelPunktyUstawienia;
    @FXML
    private TextField email;
    @FXML
    private TextField haslo;
    @FXML
    private TextField nowehaslo;
    @FXML
    private TextField hasloznowu;
    @FXML
    private TextField imie;
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
    private TextField kod1;
    @FXML
    private TextField kod2;
    @FXML
    private TableView tableAnkiety;

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

    private void setUstawienia() {
        String imie = curentUser.getImie();
        String nazwisko = curentUser.getNazwisko();
        String textPkt = imie + " " + nazwisko + " posiadasz ";
        String pkt = curentUser.getLiczbaPunktow() + "pkt";
        String[] kod = curentUser.getKodPocztowy().split("-");
        System.out.println(kod[0]);
        labelPunkty.setText(textPkt);
        punkty.setText(pkt);
        labelPunktyUstawienia.setText(textPkt);
        punktyUstawienia.setText(pkt);
        labelPunktyNagrody.setText(textPkt);
        punktyNagrody.setText(pkt);
        email.setText(curentUser.getMail());
        this.imie.setText(imie);
        this.nazwisko.setText(nazwisko);
        miejscowosc.setText(curentUser.getMiejscowosc());
        ulica.setText(curentUser.getUlica());
        budynek.setText(curentUser.getNumerBudynku());
        lokal.setText(curentUser.getNumerLokalu());
        kod1.setText(kod[0]);
        kod2.setText(kod[1]);
    }

    private void setAnkiety() {
//        String[] kolumny = {"Tytuł", "Wygasa", "Punkty", ""};
//        String[][] dane = {{"Tytuł", "Wygasa", "Punkty", ""}};

    }

    private void setNagrody() {

    }

    @Override
    public void setStartValues(Uzytkownicy user) {
        curentUser = user;
        setUstawienia();
        setAnkiety();
        setNagrody();
    }
}
