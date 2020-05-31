package com.Ankiety_PZ.test;

import com.Ankiety_PZ.hibernate.Ankiety;
import com.Ankiety_PZ.hibernate.Nagrody;
import com.Ankiety_PZ.hibernate.Pytania;
import com.Ankiety_PZ.hibernate.Uzytkownicy;
import com.Ankiety_PZ.query.NagrodyQuery;
import com.Ankiety_PZ.query.UzytkownicyQuery;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.util.List;

public class PanelOsobyOdNagrodController extends BulidStage implements SetStartValues {


    private Uzytkownicy curentUser;
    public ObservableList<NagrodyTabelka> dane;
    private String imie_nazwisko_rola_tmp;

    @FXML
    private Label imie_nazwisko_rola;

    @FXML
    private Label panelNagrodLabelError;

    @FXML
    private Button wyloguj;

    @FXML
    private Button panelnagroddodajnagrode;

    @FXML
    private TableView tableNagrody;

    @FXML
    private TableColumn nagrody;

    @FXML
    private TableColumn zdjecie;

    @FXML
    private TableColumn pkt;

    @FXML
    private TableColumn usun;

    @FXML
    private TableColumn edytuj;

    public TableColumn getNagrody() {
        return nagrody;
    }

    public TableColumn getZdjecie() {
        return zdjecie;
    }

    public TableColumn getPkt() {
        return pkt;
    }

    @FXML
    private Label imie_nazwisko_rola2;

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
    void panelnagroddodajnagrode(ActionEvent event) {

        loadingFXML(event, SceneFXML.PANEL_EDIT_NAGROD);
        PanelEdycjiNagrodController panelEdycjiNagrodController = load.getController();
        panelEdycjiNagrodController.ustawZapisz();
        panelEdycjiNagrodController.setStartValues(curentUser);
        activeScene(event, false, false);
    }

    @FXML
    void wyloguj(ActionEvent event) {
        loadingFXML(event, SceneFXML.PANEL_LOGIN);
        activeScene(event, false, false);
    }

    @FXML
    void panelOsobyOdNagrodButtonZmienUstawienia(ActionEvent event) {
        String mailN = email.getText();
        String passwordN = haslo.getText();
        String passwordNewN = nowehaslo.getText();
        String passwordRepeatN = hasloznowu.getText();
        String nameN = imie.getText();
        String surnameN = nazwisko.getText();
        String cityN = miejscowosc.getText();
        String streetN = ulica.getText();
        String numberHouseStringN = budynek.getText();
        String numberFlatStringN = lokal.getText();
        String postCodeFirstStringN = kod1.getText();
        String postCodeSecondStringN = kod2.getText();
        Walidacja walidacja = new Walidacja();
        if (walidacja.czyUzupelnionePola(mailN, surnameN, nameN, cityN, streetN, numberFlatStringN, postCodeFirstStringN, postCodeSecondStringN)) {
            if (walidacja.czyPoprawnyKodPocztowy(postCodeFirstStringN, postCodeSecondStringN)) {
                if (walidacja.sprawdzHaslo(passwordN, passwordRepeatN, passwordNewN, curentUser)) {
                    if (walidacja.czyPoprawnyMail(mailN)) {
                        UzytkownicyQuery update = new UzytkownicyQuery();
                        int postCodeFirstIntN = Integer.parseInt(postCodeFirstStringN);
                        int postCodeSecondIntN = Integer.parseInt(postCodeSecondStringN);
                        String postCode = postCodeFirstIntN + "-" + postCodeSecondIntN;
                        curentUser.setMail(mailN);
                        curentUser.setImie(nameN);
                        if (!passwordN.isEmpty()) {
                            curentUser.setHaslo(passwordRepeatN);
                        }
                        curentUser.setNazwisko(surnameN);
                        curentUser.setMiejscowosc(cityN);
                        curentUser.setUlica(streetN);
                        curentUser.setNumerBudynku(numberHouseStringN);
                        curentUser.setNumerLokalu(numberFlatStringN);
                        curentUser.setKodPocztowy(postCode);
                        update.updateUzytkownicy(curentUser);
                        imie_nazwisko_rola_tmp = curentUser.getImie() + " " + curentUser.getNazwisko() + " - konto zarządzania nagrodami";
                        imie_nazwisko_rola.setText(imie_nazwisko_rola_tmp);
                        imie_nazwisko_rola2.setText(imie_nazwisko_rola_tmp);
                        panelNagrodLabelError.setText("Profil został pomyślnie zaktualizowany.");
                    } else {
                        panelNagrodLabelError.setText("Podany adres e-mail jest nieprawidłowy!");
                    }
                } else {
                    panelNagrodLabelError.setText(walidacja.getBlad_haslo());
                }
            } else {
                panelNagrodLabelError.setText(walidacja.getBlad_kod_pocztowy());
            }
        } else {
            panelNagrodLabelError.setText("Wymagane pola są puste!");
        }
    }

    void setNagrody() {
        NagrodyQuery query = new NagrodyQuery();
        List<Nagrody> nagrodies = query.selectAllActive();
        dane = FXCollections.observableArrayList();
        for (Nagrody nagrod : nagrodies
        ) {
            dane.add(new NagrodyTabelka(nagrod, curentUser, this));
        }
        tableNagrody.itemsProperty().setValue(dane);
        nagrody.setCellValueFactory(new PropertyValueFactory("tytul"));
        zdjecie.setCellValueFactory(new PropertyValueFactory("zdjecie"));
        pkt.setCellValueFactory(new PropertyValueFactory("liczbaPunktow"));
        usun.setCellValueFactory(new PropertyValueFactory("usun"));
        edytuj.setCellValueFactory(new PropertyValueFactory("edytuj"));
        tableNagrody.setItems(dane);
    }

    private void setUstawienia() {
        String imie = curentUser.getImie();
        String nazwisko = curentUser.getNazwisko();
        String[] kod = curentUser.getKodPocztowy().split("-");
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
        imie_nazwisko_rola_tmp = curentUser.getImie() + " " + curentUser.getNazwisko() + " - konto zarządzania nagrodami";
        imie_nazwisko_rola.setText(imie_nazwisko_rola_tmp);
        imie_nazwisko_rola2.setText(imie_nazwisko_rola_tmp);
        setNagrody();
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
    void initialize() {
        assert imie_nazwisko_rola != null : "fx:id=\"imie_nazwisko_rola\" was not injected: check your FXML file 'PanelOsobyOdNagrod.fxml'.";
        assert wyloguj != null : "fx:id=\"wyloguj\" was not injected: check your FXML file 'PanelOsobyOdNagrod.fxml'.";
        assert panelnagroddodajnagrode != null : "fx:id=\"panelnagroddodajnagrode\" was not injected: check your FXML file 'PanelOsobyOdNagrod.fxml'.";
        assert imie_nazwisko_rola2 != null : "fx:id=\"imie_nazwisko_rola2\" was not injected: check your FXML file 'PanelOsobyOdNagrod.fxml'.";
    }
}