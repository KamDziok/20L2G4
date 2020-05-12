/**
 * Sample Skeleton for 'panelUzytkownika.fxml' Controller Class
 */

package com.Ankiety_PZ.test;

import com.Ankiety_PZ.hibernate.Ankiety;
import com.Ankiety_PZ.hibernate.Pytania;
import com.Ankiety_PZ.hibernate.Nagrody;
import com.Ankiety_PZ.hibernate.Uzytkownicy;
import com.Ankiety_PZ.query.AnkietyQuery;
import com.Ankiety_PZ.query.NagrodyQuery;
import com.Ankiety_PZ.query.UzytkownicyQuery;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

public class PanelUzytkownikaController extends BulidStage implements SetStartValues {

    private Uzytkownicy curentUser;
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;
    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;
    @FXML private Button wyloguj;
    @FXML private Label punkty;
    @FXML private Label labelPunkty;
    @FXML private Label punktyNagrody;
    @FXML private Label labelPunktyNagrody;
    @FXML private Label punktyUstawienia;
    @FXML private Label labelPunktyUstawienia;
    @FXML private TextField email;
    @FXML private TextField haslo;
    @FXML private TextField nowehaslo;
    @FXML private TextField hasloznowu;
    @FXML private TextField imie;
    @FXML private TextField nazwisko;
    @FXML private TextField miejscowosc;
    @FXML private TextField ulica;
    @FXML private TextField budynek;
    @FXML private TextField lokal;
    @FXML private TextField kod1;
    @FXML private TextField kod2;
    @FXML private TableView tableAnkiety;
    @FXML private TableColumn tytul;
    @FXML private TableColumn wygasa;
    @FXML private TableColumn pkt;
    @FXML private TableColumn przycisk;
    @FXML private TableView tableNagrody;
    @FXML private TableColumn nazwa;
    @FXML private TableColumn cena;
    @FXML private TableColumn obrazek;
    @FXML private TableColumn kup;

    @FXML
    void wyloguj(ActionEvent event) {
        loadingFXML(event, SceneFXML.PANEL_LOGIN);
        activeScene(event, false, false);
    }

    @FXML
    void panelUzytkownikaButtonMakeAnkiet(ActionEvent event) {
        loadingFXML(event, SceneFXML.OKNO_ANKIETA_RADIO);
        activeScene(event, false, true);
    }

    @FXML
    void panelUzytkownikaButtonZmienUstawienia(ActionEvent event) {
        curentUser.setMail(email.getText());
        if(haslo.getText().equals(curentUser.getHaslo()) && nowehaslo.getText().equals(hasloznowu.getText()))
            curentUser.setHaslo(nowehaslo.getText());
        curentUser.setImie(imie.getText());
        curentUser.setNazwisko(nazwisko.getText());
        curentUser.setMiejscowosc(miejscowosc.getText());
        curentUser.setUlica(ulica.getText());
        curentUser.setNumerBudynku(budynek.getText());
        curentUser.setNumerLokalu(lokal.getText());
        curentUser.setKodPocztowy(kod1.getText() + "-" + kod2.getText());
        UzytkownicyQuery query = new UzytkownicyQuery();
        query.updateUzytkownicy(curentUser);
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
        AnkietyQuery query = new AnkietyQuery();
        List<Ankiety> ankiety = query.selectAllActiveAnkiety();
        ObservableList<AnikietaTabelka> dane = FXCollections.observableArrayList();
        for (Ankiety ankieta:ankiety
             ) {
            dane.add(new AnikietaTabelka(ankieta, curentUser));
        }
        System.out.println();
        tableAnkiety.itemsProperty().setValue(dane);
        tytul.setCellValueFactory(new PropertyValueFactory("tytul"));
        wygasa.setCellValueFactory(new PropertyValueFactory("dataZakonczenia"));
        pkt.setCellValueFactory(new PropertyValueFactory("liczbaPunktow"));
        przycisk.setCellValueFactory(new PropertyValueFactory("button"));
    }

    private void setNagrody() {
        NagrodyQuery query = new NagrodyQuery();
        List<Nagrody> nagrody = query.selectAll();
        ObservableList<NagrodaTabelka> dane = FXCollections.observableArrayList();
        for (Nagrody nagroda:nagrody
        ) {
            dane.add(new NagrodaTabelka(nagroda));
        }
        tableNagrody.itemsProperty().setValue(dane);
        nazwa.setCellValueFactory(new PropertyValueFactory("nazwa"));
        cena.setCellValueFactory(new PropertyValueFactory("cena"));
        obrazek.setCellValueFactory(new PropertyValueFactory("obrazek"));
        kup.setCellValueFactory(new PropertyValueFactory("button"));
    }

    @Override
    public void setStartValues(Uzytkownicy user) {
        curentUser = user;
        setUstawienia();
        setAnkiety();
        setNagrody();
    }

    @Override
    public void setStartValuesAnkiety(Ankiety ankieta) {

    }

    @Override
    public void setStartValuesPytanie(Pytania pytania) {

    }

    @Override
    public void setStartValuesNagroda(Nagrody nagroda) {

    }

    @Override
    public void setStartValuesIerator(Iterator iterator) {

    }
}
