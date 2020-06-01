package com.Ankiety_PZ.panele;

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

/**
 * Klasa odpowiada za panel użytkownika.
 */

public class PanelUzytkownikaController extends BulidStage implements SetStartValues {

    /**
     * Aktualnie zalogowany użytkownik
     */

    private Uzytkownicy curentUser;
    @FXML
    private ResourceBundle resources;

    /**
     * Aktualna liczba punktów zalogowanego użytkownika.
     */

    @FXML
    private String textPkt;

    /**
     * Etykieta informująca o rodzaju błędu przy edycji profilu użytkownika.
     */

    @FXML
    private Label panelUzytkownikaLabelError;

    /**
     * Etykieta informująca o rodzaju błędu przy wymianie punktów na nagrody.
     */

    @FXML
    private Label panelUzytkownikaLabelErrorNagrody;


    /**
     * Przycisk odpowiedzialny za wylogowywanie.
     */

    @FXML
    private Button wyloguj;

    /**
     * Etykieta z liczbą punktów użytkownika.
     */

    @FXML
    private Label punkty;

    /**
     * Etykieta z liczbą punktów użytkownika.
     */

    @FXML
    private Label labelPunkty;

    /**
     * Etykieta z liczbą punktów użytkownika.
     */

    @FXML
    private Label punktyNagrody;

    /**
     * Etykieta z liczbą punktów użytkownika przy nagrodach.
     */

    @FXML
    private Label labelPunktyNagrody;

    /**
     * Etykieta z liczbą punktów użytkownika.
     */

    @FXML
    private Label punktyUstawienia;

    /**
     * Etykieta z liczbą punktów użytkownika przy edycji profilu.
     */

    @FXML
    private Label labelPunktyUstawienia;

    /**
     * Mail przy edycji profilu.
     */

    @FXML
    private TextField email;

    /**
     * Aktualne hasło przy edycji profilu.
     */

    @FXML
    private TextField haslo;

    /**
     * Nowe hasło przy edycji profilu.
     */

    @FXML
    private TextField nowehaslo;

    /**
     * Powtórnie wprowadzone hasło przy edycji profilu.
     */

    @FXML
    private TextField hasloznowu;

    /**
     * Imie przy edycji profilu.
     */

    @FXML
    private TextField imie;

    /**
     * Nazwisko przy edycji profilu.
     */

    @FXML
    private TextField nazwisko;

    /**
     * Miejscowość przy edycji profilu.
     */

    @FXML
    private TextField miejscowosc;

    /**
     * Ulica przy edycji profilu.
     */

    @FXML
    private TextField ulica;

    /**
     * Numer budynku przy edycji profilu.
     */

    @FXML
    private TextField budynek;

    /**
     * Numer lokalu przy edycji profilu.
     */

    @FXML
    private TextField lokal;

    /**
     * Dwie pierwsze cyfry kodu pocztowego przy edycji profilu.
     */

    @FXML
    private TextField kod1;

    /**
     * Trzy ostatnie cyfry kodu pocztowego przy edycji profilu.
     */

    @FXML
    private TextField kod2;

    /**
     * Tabela ankiet.
     */

    @FXML
    private TableView tableAnkiety;

    /**
     * Kolumna w tabeli ankiet z tytułem ankiety.
     */

    @FXML
    private TableColumn tytul;

    /**
     * Kolumna w tabeli ankiet z datą ważności ankiety.
     */

    @FXML
    private TableColumn wygasa;

    /**
     * Kolumna w tabeli ankiet, w której pojawia się liczba punktów doliczonych do konta po wypełnieniu ankiety.
     */

    @FXML
    private TableColumn pkt;

    /**
     * Kolumna w tabeli ankiet, w której pojawia się przycisk <code>wypełnij</code>.
     */

    @FXML
    private TableColumn przycisk;

    /**
     * Tabela nagród.
     */

    @FXML
    private TableView tableNagrody;

    /**
     * Kolumna w tabeli nagród, w której pojawia się nazwa nagrody.
     */

    @FXML
    private TableColumn nazwa;

    /**
     * Kolumna w tabeli nagród, w której pojawia się liczba punktów potrzebna do wymiany nagrody.
     */

    @FXML
    private TableColumn cena;

    /**
     * Kolumna w tabeli nagród, w której pojawia się ilustracja nagrody.
     */

    @FXML
    private TableColumn obrazek;

    /**
     * Kolumna w tabeli nagród, w której pojawia się przycisk <code>wymień</code>.
     */

    @FXML
    private TableColumn kup;

    /**
     * Etykieta informująca o rodzaju błędu przy wypełnianiu ankiety przez użytkownika.
     */

    @FXML
    private Label panelUzytkownikaLabelErrorAnkiety;

    /**
     * Metoda wykonująca akcję wylogowowania użytkownika po naciśnięciu przycisku <code>wyloguj</code>.
     * @param event
     */

    @FXML
    void wyloguj(ActionEvent event) {
        loadingFXML(event, SceneFXML.PANEL_LOGIN);
        activeScene(event, false, false);
    }

    /**
     * Metoda wykonująca akcję edycji profilu użytkownika wraz ze sprawdzeniem prawidłowości wprowadzonych danch.
     * Metoda wykona się po wybraniu przycisku <code>zapisz zmiany</code>.
     *
     * @param event
     */

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
    void initialize() {
        assert wyloguj != null : "fx:id=\"wyloguj\" was not injected: check your FXML file 'PanelUzytkownika.fxml'.";
    }

    /**
     * Metoda odpowiada za akutalizację liczby punktów użytkownika po wypełnieniu ankiety lub wymianie punktów na
     * nagrody.
     *
     * @param punkty aktualna liczba punktów użytkownika zalogowanego.
     */

    public void updatePkt(String punkty) {
        this.punkty.setText(punkty + "pkt");
        punktyUstawienia.setText(punkty + "pkt");
        punktyNagrody.setText(punkty + "pkt");
    }

    /**
     * Metoda odpowiada za ustwienie aktualnych danych przy edycji profilu.
     */

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

    /**
     * Metoda odpowiada pobranie aktualnych i nie wypełnionych przez użytkownika ankiet do tabeli.
     */

    void setAnkiety() {
        AnkietyQuery query = new AnkietyQuery();
        List<Ankiety> ankiety = query.selectAllActiveAndNotDoAnkiety(curentUser);
        ObservableList<AnkietaTabelka> dane = FXCollections.observableArrayList();
        for (Ankiety ankieta : ankiety
        ) {
            dane.add(new AnkietaTabelka(ankieta, this));
        }
        tableAnkiety.itemsProperty().setValue(dane);
        tytul.setCellValueFactory(new PropertyValueFactory("tytul"));
        wygasa.setCellValueFactory(new PropertyValueFactory("dataZakonczenia"));
        pkt.setCellValueFactory(new PropertyValueFactory("liczbaPunktow"));
        przycisk.setCellValueFactory(new PropertyValueFactory("button"));
    }

    /**
     * Metoda odpowiada za pobranie aktualnych nagród do tabeli.
     */

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

    /**
     * Metoda wykonuje inicjalizację aktualnych danych użytkownika, ankiet, nagród i ustawień w edycji profilu.
     */

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
