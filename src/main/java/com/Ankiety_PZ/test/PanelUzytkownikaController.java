package com.Ankiety_PZ.test;

import com.Ankiety_PZ.hibernate.Ankiety;
import com.Ankiety_PZ.hibernate.Nagrody;
import com.Ankiety_PZ.hibernate.Pytania;
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
import java.util.List;
import java.util.ResourceBundle;

public class PanelUzytkownikaController extends BulidStage implements SetStartValues {

    private Uzytkownicy curentUser;
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;
    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private String textPkt;
    @FXML
    private Label panelUzytkownikaLabelError;
    @FXML
    private Label panelUzytkownikaLabelErrorNagrody;
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
    private TableColumn tytul;
    @FXML
    private TableColumn wygasa;
    @FXML
    private TableColumn pkt;
    @FXML
    private TableColumn przycisk;
    @FXML
    private TableView tableNagrody;
    @FXML
    private TableColumn nazwa;
    @FXML
    private TableColumn cena;
    @FXML
    private TableColumn obrazek;
    @FXML
    private TableColumn kup;
    @FXML
    private Label panelUzytkownikaLabelErrorAnkiety;

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
        String mailUser = email.getText();
        String passwordUser = haslo.getText();
        String passwordNewUser = nowehaslo.getText();
        String passwordRepeatUser = hasloznowu.getText();
        String nameUser = imie.getText();
        String surnameUser = nazwisko.getText();
        String cityUser = miejscowosc.getText();
        String streetUser = ulica.getText();
        String numberHouseStringUser = budynek.getText();
        String numberFlatStringUser = lokal.getText();
        String postCodeFirstStringUser = kod1.getText();
        String postCodeSecondStringUser = kod2.getText();
        Walidacja walidacja = new Walidacja();
        if (walidacja.czyUzupelnionePola(mailUser, surnameUser, nameUser, cityUser, streetUser, numberFlatStringUser, postCodeFirstStringUser, postCodeSecondStringUser)) {
            if (walidacja.czyPoprawnyKodPocztowy(postCodeFirstStringUser, postCodeSecondStringUser)) {
                if (walidacja.sprawdzHaslo(passwordUser, passwordRepeatUser, passwordNewUser, curentUser)) {
                    if (walidacja.czyPoprawnyMail(mailUser)) {
                        UzytkownicyQuery update = new UzytkownicyQuery();
                        int postCodeFirstIntUser = Integer.parseInt(postCodeFirstStringUser);
                        int postCodeSecondIntUser = Integer.parseInt(postCodeSecondStringUser);
                        String postCode = postCodeFirstIntUser + "-" + postCodeSecondIntUser;
                        curentUser.setMail(mailUser);
                        curentUser.setImie(nameUser);
                        if (!passwordUser.isEmpty()) {
                            curentUser.setHaslo(passwordRepeatUser);
                        }
                        curentUser.setNazwisko(surnameUser);
                        curentUser.setMiejscowosc(cityUser);
                        curentUser.setUlica(streetUser);
                        curentUser.setNumerBudynku(numberHouseStringUser);
                        curentUser.setNumerLokalu(numberFlatStringUser);
                        curentUser.setKodPocztowy(postCode);
                        update.updateUzytkownicy(curentUser);
                        textPkt = curentUser.getImie() + " " + curentUser.getNazwisko() + " posiadasz ";
                        labelPunkty.setText(textPkt);
                        labelPunktyUstawienia.setText(textPkt);
                        labelPunktyNagrody.setText(textPkt);
                        panelUzytkownikaLabelError.setText("Profil został pomyślnie zaktualizowany.");
                    } else {
                        panelUzytkownikaLabelError.setText("Podany adres e-mail jest nieprawidłowy!");
                    }
                } else {
                    panelUzytkownikaLabelError.setText(walidacja.getBlad_haslo());
                }
            } else {
                panelUzytkownikaLabelError.setText(walidacja.getBlad_kod_pocztowy());
            }
        } else {
            panelUzytkownikaLabelError.setText("Wymagane pola są puste!");
        }
    }

    @FXML
        // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert wyloguj != null : "fx:id=\"wyloguj\" was not injected: check your FXML file 'panelUzytkownika.fxml'.";
    }

    public void updatePkt(String punkty) {
        this.punkty.setText(punkty + "pkt");
        punktyUstawienia.setText(punkty + "pkt");
        punktyNagrody.setText(punkty + "pkt");
    }

    private void setUstawienia() {
        String imie = curentUser.getImie();
        String nazwisko = curentUser.getNazwisko();
        textPkt = imie + " " + nazwisko + " posiadasz ";
        String pkt = curentUser.getLiczbaPunktow() + " pkt";
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

    void setAnkiety() {
        AnkietyQuery query = new AnkietyQuery();
        List<Ankiety> ankiety = query.selectAllActiveAndNotDoAnkiety(curentUser);
        ObservableList<AnikietaTabelka> dane = FXCollections.observableArrayList();
        for (Ankiety ankieta : ankiety
        ) {
            dane.add(new AnikietaTabelka(ankieta, this));
        }
        tableAnkiety.itemsProperty().setValue(dane);
        tytul.setCellValueFactory(new PropertyValueFactory("tytul"));
        wygasa.setCellValueFactory(new PropertyValueFactory("dataZakonczenia"));
        pkt.setCellValueFactory(new PropertyValueFactory("liczbaPunktow"));
        przycisk.setCellValueFactory(new PropertyValueFactory("button"));
    }

    private void setNagrody() {
        NagrodyQuery query = new NagrodyQuery();
        List<Nagrody> nagrody = query.selectAllActive();
        ObservableList<NagrodaTabelka> dane = FXCollections.observableArrayList();
        for (Nagrody nagroda : nagrody
        ) {
            dane.add(new NagrodaTabelka(nagroda, this));
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

    public Uzytkownicy getCurentUser() {
        return curentUser;
    }

    Label getPanelUzytkownikaLabelErrorNagrody() {
        return panelUzytkownikaLabelErrorNagrody;
    }

    public Label getPanelUzytkownikaLabelErrorAnkiety() {
        return panelUzytkownikaLabelErrorAnkiety;
    }
}
