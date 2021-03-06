package com.Ankiety_PZ.panele;

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
import org.apache.commons.codec.digest.DigestUtils;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Klasa odpowiada za panel administratora.
 */

public class PanelAdminaController extends BulidStage implements SetStartValues {

    /**
     * Obiekt nasłuchujący zmiany w tabeli aktywnych użytkowników.
     */

    public ObservableList<UzytkownicyTabelka> dane;

    /**
     * Obiekt nasłuchujący zmiany w tabeli zablokowanych użytkowników.
     */

    public ObservableList<UzytkownicyZablokowaniTabelka> dane2;

    /**
     * String z aktualnym imieniem, nazwiskiem i rolą użytkownika
     */

    private String imie_nazwisko_rola_tmp;

    /**
     * Aktualnie zalogowany użytkownik
     */

    private Uzytkownicy curentUser;

    /**
     * Etykieta z aktualnym imieniem, nazwiskiem i rolą użytkownika
     */

    @FXML
    private Label imie_nazwisko_rola;

    /**
     * Etykieta z aktualnym imieniem, nazwiskiem i rolą użytkownika
     */

    @FXML
    private Label imie_nazwisko_rola2;

    /**
     * Etykieta z aktualnym imieniem, nazwiskiem i rolą użytkownika
     */

    @FXML
    private Label imie_nazwisko_rola3;

    /**
     * Etykieta informująca o rodzaju błędu przy edycji własnego profilu przez administratora.
     */

    @FXML
    private Label panelAdminaLabelError;
    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;

    /**
     * Przycisk odpowiedzialny za wylogowywanie.
     */

    @FXML
    private Button wyloguj;

    /**
     * Tabela aktywnych użytkowników.
     */

    @FXML
    TableView tableUzytkownicy;

    /**
     * Tabela zablokowanych użytkowników.
     */

    @FXML
    TableView tableUzytkownicyZablokowani;

    /**
     * Kolumna w tabeli użytkowników aktywnych z imieniem i nazwiskiem.
     */

    @FXML
    private TableColumn imie_i_nazwisko;

    /**
     * Kolumna w tabeli użytkowników aktywnych z mailem użytkownika.
     */

    @FXML
    private TableColumn mail;

    /**
     * Kolumna w tabeli użytkowników aktywnych z punktami użytkownika.
     */

    @FXML
    private TableColumn pkt;

    /**
     * Kolumna w tabeli użytkowników aktywnych z przyciskiem do blokowania użytkownika.
     */

    @FXML
    private TableColumn przycisk;

    /**
     * Kolumna w tabeli użytkowników zablokowanych z imieniem i nazwiskiem.
     */

    @FXML
    private TableColumn imie_i_nazwisko_z;

    /**
     * Kolumna w tabeli użytkowników zablokowanych z mailem użytkownika.
     */

    @FXML
    private TableColumn mail_z;

    /**
     * Kolumna w tabeli zablokowanych użytkowników z punktami użytkownika.
     */

    @FXML
    private TableColumn pkt_z;

    /**
     * Kolumna w tabeli użytkowników zablokowanych z przyciskiem do odblokowywania użytkownika.
     */

    @FXML
    private TableColumn przycisk3;

    /**
     * Kolumna w tabeli użytkowników aktywnych z przyciskiem do edycji użytkownika.
     */

    @FXML
    private TableColumn przycisk2;

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
     * Metoda wykonująca akcję edycji profilu własnego profilu przez administratora wraz ze sprawdzeniem prawidłowości
     * wprowadzonych danch. Metoda wykona się po wybraniu przycisku <code>zapisz zmiany</code>.
     *
     * @param event zdarzenie, po którym funkcja ma się wywołać.
     */

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
        if (walidacja.czyUzupelnionePola(mailAdmin, surnameAdmin, nameAdmin, cityAdmin, streetAdmin, numberHouseStringAdmin, postCodeFirstStringAdmin, postCodeSecondStringAdmin)) {
            if (walidacja.czyPoprawnyKodPocztowy(postCodeFirstStringAdmin, postCodeSecondStringAdmin)) {
                if (walidacja.sprawdzHaslo(passwordAdmin, passwordRepeatAdmin, passwordNewAdmin, curentUser)) {
                    if (walidacja.czyPoprawnyMail(mailAdmin)) {
                        UzytkownicyQuery update = new UzytkownicyQuery();
                        String postCode = postCodeFirstStringAdmin + "-" + postCodeSecondStringAdmin;
                        curentUser.setMail(mailAdmin);
                        curentUser.setImie(nameAdmin);
                        if (!passwordAdmin.isEmpty()) {
                            curentUser.setHaslo(DigestUtils.shaHex(passwordRepeatAdmin));
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

    /**
     * Metoda odpowiada pobranie aktualnych aktywnych użytkowników do tabeli.
     */

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

    /**
     * Metoda odpowiada pobranie aktualnych zablokowanych użytkowników do tabeli.
     */

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

    /**
     * Metoda odpowiada za ustwienie aktualnych danych przy własnego edycji profilu przez administratora.
     */

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

    /**
     * Metoda wykonuje inicjalizację aktualnych danych użytkownika, użytkowników aktywnych i zablokowanych.
     */

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
    void initialize() {
        assert wyloguj != null : "fx:id=\"wyloguj\" was not injected: check your FXML file 'PanelAdmina.fxml'.";
        assert imie_nazwisko_rola != null : "fx:id=\"imie_nazwisko_rola\" was not injected: check your FXML file 'PanelAdmina.fxml'.";
        assert imie_nazwisko_rola2 != null : "fx:id=\"imie_nazwisko_rola2\" was not injected: check your FXML file 'PanelAdmina.fxml'.";
        assert imie_nazwisko_rola3 != null : "fx:id=\"imie_nazwisko_rola3\" was not injected: check your FXML file 'PanelAdmina.fxml'.";

    }

}
