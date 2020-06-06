package com.Ankiety_PZ.panele;

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


/**
 * Klasa dla panelu osoby odpowiedzialnej za nagrody.
 */


public class PanelOsobyOdNagrodController extends BulidStage implements SetStartValues {

    /**
     * Aktualnie zalogowany użytkownik
     */

    private Uzytkownicy curentUser;

    /**
     * Obiekt nasłuchujący zmiany w tabeli nagród.
     */

    public ObservableList<NagrodyTabelka> dane;

    /**
     * String z aktualnym imieniem, nazwiskiem i rolą użytkownika
     */

    private String imie_nazwisko_rola_tmp;

    /**
     * Etykieta z aktualnym imieniem, nazwiskiem i rolą użytkownika
     */

    @FXML
    private Label imie_nazwisko_rola;

    /**
     * Etykieta informująca o rodzaju błędu przy edycji użytkownika.
     */

    @FXML
    private Label panelNagrodLabelError;

    /**
     * Przycisk odpowiedzialny za wylogowywanie.
     */

    @FXML
    private Button wyloguj;

    /**
     * Przycisk odpowiedzialny za dodawanie nagród.
     */

    @FXML
    private Button panelnagroddodajnagrode;

    /**
     * Tabela nagród.
     */

    @FXML
    private TableView tableNagrody;

    /**
     * Kolumna w tabeli nagród, w której pojawia się nazwa nagrody.
     */

    @FXML
    private TableColumn nagrody;

    /**
     * Kolumna w tabeli nagród, w której pojawia się ilustracja nagrody.
     */

    @FXML
    private TableColumn zdjecie;


    /**
     * Kolumna w tabeli nagród, w której pojawia się liczba punktów potrzebna do wymiany nagrody.
     */

    @FXML
    private TableColumn pkt;

    /**
     * Kolumna w tabeli nagród, w której pojawia się przycisk <code>usuń</code>.
     */

    @FXML
    private TableColumn usun;

    /**
     * Kolumna w tabeli nagród, w której pojawia się przycisk <code>edytuj</code>.
     */

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

    /**
     * Etykieta z aktualnym imieniem, nazwiskiem i rolą użytkownika
     */

    @FXML
    private Label imie_nazwisko_rola2;

    /**
     * Mail przy edycji profilu.
     */

    @FXML
    private TextField email;

    /**
     * Aktualne hasło przy edycji profilu.
     */

    @FXML
    private PasswordField haslo;

    /**
     * Nowe hasło przy edycji profilu.
     */

    @FXML
    private PasswordField nowehaslo;

    /**
     * Powtórnie wprowadzone hasło przy edycji profilu.
     */

    @FXML
    private PasswordField hasloznowu;

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
     * Metoda otwierająca okno dodawania nagrody po naciśnięciu przycisku <code>dodaj nagrodę</code>.
     *
     * @param event zdarzenie, po którym funkcja ma się wywołać.
     */

    @FXML
    void panelnagroddodajnagrode(ActionEvent event) {

        loadingFXML(event, SceneFXML.PANEL_EDIT_NAGROD);
        PanelEdycjiNagrodController panelEdycjiNagrodController = load.getController();
        panelEdycjiNagrodController.ustawZapisz();
        panelEdycjiNagrodController.setStartValues(curentUser);
        activeScene(event, false, false);
    }

    /**
     * Metoda wykonująca akcję wylogowowania użytkownika po naciśnięciu przycisku <code>wyloguj</code>.
     *
     * @param event zdarzenie, po którym funkcja ma się wywołać.
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
     * @param event zdarzenie, po którym funkcja ma się wywołać.
     */

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
        if (walidacja.czyUzupelnionePola(mailN, surnameN, nameN, cityN, streetN, numberHouseStringN, postCodeFirstStringN, postCodeSecondStringN)) {
            if (walidacja.czyPoprawnyKodPocztowy(postCodeFirstStringN, postCodeSecondStringN)) {
                if (walidacja.sprawdzHaslo(passwordN, passwordRepeatN, passwordNewN, curentUser)) {
                    if (walidacja.czyPoprawnyMail(mailN)) {
                        UzytkownicyQuery update = new UzytkownicyQuery();
                        String postCode = postCodeFirstStringN + "-" + postCodeSecondStringN;
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

    /**
     * Metoda odpowiada za pobranie aktualnych nagród do tabeli.
     */

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

    /**
     * Metoda odpowiada za ustwienie aktualnych danych przy edycji profilu.
     */

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

    /**
     * Metoda wykonuje inicjalizację aktualnych danych użytkownika, nagród i ustawień w edycji profilu.
     */

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