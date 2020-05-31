package com.Ankiety_PZ.test;

import com.Ankiety_PZ.hibernate.Ankiety;
import com.Ankiety_PZ.hibernate.Nagrody;
import com.Ankiety_PZ.hibernate.Pytania;
import com.Ankiety_PZ.hibernate.Uzytkownicy;
import com.Ankiety_PZ.query.UzytkownicyQuery;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class PanelAdminaController extends BulidStage implements SetStartValues {

    public ObservableList<UzytkownicyTabelka> dane;
    public ObservableList<UzytkownicyZablokowaniTabelka> dane2;
    private String imie_nazwisko_rola_tmp;
    private Uzytkownicy curentUser;
    @FXML
    private Label imie_nazwisko_rola;
    @FXML
    private Label imie_nazwisko_rola2;
    @FXML
    private Label imie_nazwisko_rola3;
    @FXML
    private Label panelAdminaLabelError;
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;
    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;
    @FXML
    private Button wyloguj;
    @FXML
    TableView tableUzytkownicy;
    @FXML
    TableView tableUzytkownicyZablokowani;
    @FXML
    private TableColumn imie_i_nazwisko;
    @FXML
    private TableColumn mail;
    @FXML
    private TableColumn pkt;
    @FXML
    private TableColumn przycisk;
    @FXML
    private TableColumn imie_i_nazwisko_z;
    @FXML
    private TableColumn mail_z;
    @FXML
    private TableColumn pkt_z;
    @FXML
    private TableColumn przycisk3;
    @FXML
    private TableColumn przycisk2;
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
    void wyloguj(ActionEvent event) {
        loadingFXML(event, SceneFXML.PANEL_LOGIN);
        activeScene(event, false, false);
    }

    @FXML
    void panelAdminaButtonZmienUstawienia(ActionEvent event) {
        String mailAdmin = email.getText();
        String passwordAdmin = haslo.getText();
        String passwordNewAdmin = nowehaslo.getText();
        String passwordRepeatAdmin = hasloznowu.getText();
        String nameAdmin = imie.getText();
        String surnameAdmin = nazwisko.getText();
        String cityAdmin = miejscowosc.getText();
        String streetAdmin = ulica.getText();
        String numberHouseStringAdmin = budynek.getText();
        String numberFlatStringAdmin = lokal.getText();
        String postCodeFirstStringAdmin = kod1.getText();
        String postCodeSecondStringAdmin = kod2.getText();
        Walidacja walidacja = new Walidacja();
        if (walidacja.czyUzupelnionePola(mailAdmin, surnameAdmin, nameAdmin, cityAdmin, streetAdmin, numberFlatStringAdmin, postCodeFirstStringAdmin, postCodeSecondStringAdmin)) {
            if (walidacja.czyPoprawnyKodPocztowy(postCodeFirstStringAdmin, postCodeSecondStringAdmin)) {
                if (walidacja.sprawdzHaslo(passwordAdmin, passwordRepeatAdmin, passwordNewAdmin, curentUser)) {
                    if (walidacja.czyPoprawnyMail(mailAdmin)) {
                        UzytkownicyQuery update = new UzytkownicyQuery();
                        int postCodeFirstIntAdmin = Integer.parseInt(postCodeFirstStringAdmin);
                        int postCodeSecondIntAdmin = Integer.parseInt(postCodeSecondStringAdmin);
                        String postCode = postCodeFirstIntAdmin + "-" + postCodeSecondIntAdmin;
                        curentUser.setMail(mailAdmin);
                        curentUser.setImie(nameAdmin);
                        if (!passwordAdmin.isEmpty()) {
                            curentUser.setHaslo(passwordRepeatAdmin);
                        }
                        curentUser.setNazwisko(surnameAdmin);
                        curentUser.setMiejscowosc(cityAdmin);
                        curentUser.setUlica(streetAdmin);
                        curentUser.setNumerBudynku(numberHouseStringAdmin);
                        curentUser.setNumerLokalu(numberFlatStringAdmin);
                        curentUser.setKodPocztowy(postCode);
                        update.updateUzytkownicy(curentUser);
                        imie_nazwisko_rola_tmp = curentUser.getImie() + " " + curentUser.getNazwisko() + " - konto administratora";
                        imie_nazwisko_rola.setText(imie_nazwisko_rola_tmp);
                        imie_nazwisko_rola2.setText(imie_nazwisko_rola_tmp);
                        imie_nazwisko_rola3.setText(imie_nazwisko_rola_tmp);
                        panelAdminaLabelError.setText("Profil został pomyślnie zaktualizowany.");
                    } else {
                        panelAdminaLabelError.setText("Podany adres e-mail jest nieprawidłowy!");
                    }
                } else {
                    panelAdminaLabelError.setText(walidacja.getBlad_haslo());
                }
            } else {
                panelAdminaLabelError.setText(walidacja.getBlad_kod_pocztowy());
            }
        } else {
            panelAdminaLabelError.setText("Wymagane pola są puste!");
        }
    }

    void setUzytkownicy() {
        UzytkownicyQuery query = new UzytkownicyQuery();
        List<Uzytkownicy> uzytkownicy = query.selectBy(false, curentUser);
        dane = FXCollections.observableArrayList();
        for (Uzytkownicy uzytkownik : uzytkownicy
        ) {
            dane.add(new UzytkownicyTabelka(uzytkownik, curentUser, this));
        }
        imie_i_nazwisko.setCellValueFactory(new PropertyValueFactory("imie_i_nazwisko"));
        mail.setCellValueFactory(new PropertyValueFactory("mail"));
        pkt.setCellValueFactory(new PropertyValueFactory("liczbaPunktow"));
        przycisk.setCellValueFactory(new PropertyValueFactory("usun"));
        przycisk2.setCellValueFactory(new PropertyValueFactory("edytuj"));
        tableUzytkownicy.setItems(dane);
    }

    void setUzytkownicyZablokowani() {
        UzytkownicyQuery query = new UzytkownicyQuery();
        List<Uzytkownicy> uzytkownicy = query.selectBy(true, curentUser);
        dane2 = FXCollections.observableArrayList();
        for (Uzytkownicy uzytkownik : uzytkownicy
        ) {
            dane2.add(new UzytkownicyZablokowaniTabelka(uzytkownik, this));
        }
        imie_i_nazwisko_z.setCellValueFactory(new PropertyValueFactory("imie_i_nazwisko_z"));
        mail_z.setCellValueFactory(new PropertyValueFactory("mail_z"));
        pkt_z.setCellValueFactory(new PropertyValueFactory("liczbaPunktow_z"));
        przycisk3.setCellValueFactory(new PropertyValueFactory("odblokuj"));
        tableUzytkownicyZablokowani.setItems(dane2);
    }

    private void setUstawienia() {
        String imie = curentUser.getImie();
        String nazwisko = curentUser.getNazwisko();
        String[] kod = curentUser.getKodPocztowy().split("-");
        System.out.println(kod[0]);
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


    @Override
    public void setStartValues(Uzytkownicy user) {
        this.curentUser = user;

        imie_nazwisko_rola_tmp = curentUser.getImie() + " " + curentUser.getNazwisko() + " - konto administratora";
        System.out.print(imie_nazwisko_rola_tmp);
        imie_nazwisko_rola.setText(imie_nazwisko_rola_tmp);
        imie_nazwisko_rola2.setText(imie_nazwisko_rola_tmp);
        imie_nazwisko_rola3.setText(imie_nazwisko_rola_tmp);
        setUzytkownicy();
        setUzytkownicyZablokowani();
        setUstawienia();
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

    @FXML
        // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert wyloguj != null : "fx:id=\"wyloguj\" was not injected: check your FXML file 'PanelAdmina.fxml'.";
        assert imie_nazwisko_rola != null : "fx:id=\"imie_nazwisko_rola\" was not injected: check your FXML file 'PanelAdmina.fxml'.";
        assert imie_nazwisko_rola2 != null : "fx:id=\"imie_nazwisko_rola2\" was not injected: check your FXML file 'PanelAdmina.fxml'.";
        assert imie_nazwisko_rola3 != null : "fx:id=\"imie_nazwisko_rola3\" was not injected: check your FXML file 'PanelAdmina.fxml'.";

    }

}
