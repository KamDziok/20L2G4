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
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.net.URL;
import java.util.ResourceBundle;

public class PanelEdycjiUzytkownikaController extends BulidStage implements SetStartValues, SetStartValuesEdycjaUzytkownika, Initializable {

    Uzytkownicy curentUser;
    Uzytkownicy edycja;
    ObservableList list = FXCollections.observableArrayList();
    private String imie_nazwisko_rola_tmp;
    private String mail;
    private int uprawnienia_i;
    private String password;
    private String name;
    private String surname;
    private String city;
    private String street;
    /**
     * Numer domu wczytany z pola tekstowego jako String.
     */

    private String numberHouseString;

    /**
     * Numer lokalu wczytany z pola tekstowego jako String.
     */

    private String numberFlatString;

    /**
     * Pierwsza część kodu pocztowego wczytany z pola tekstowego jako String.
     */

    private String postCodeFirstString;

    /**
     * Druga część kodu pocztowego wczytany z pola tekstowego jako String.
     */

    private String postCodeSecondString;

    /**
     * Pierwsza część kodu pocztowego przekształconego na int.
     */

    private int postCodeFirstInt;

    /**
     * Druga część kodu pocztowego przekształconego na int.
     */

    private int postCodeSecondInt;

    /**
     * Minimalna długośc hasłą.
     */

    private final int minSizePassword = 3;

    @FXML
    private Label panelEdycjiUzytkownikaLabelError;

    @FXML
    private Label imie_nazwisko_rola;

    @FXML
    private TextField email;

    @FXML
    private TextField haslo;

    @FXML
    private ChoiceBox<String> uprawnienia;

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
    void panelEdycjiUzytkownikowButtonAnuluj(ActionEvent event) {
        loadingFXML(event, SceneFXML.PANEL_ADMINA);
        PanelAdminaController panelAdminaController = load.getController();
        panelAdminaController.setStartValues(curentUser);
        activeScene(event, false, false);
    }

    /**
     * Metoda sprawdzenie czy obowiązkowe pola nie są puste.
     *
     * @return true jeśli wszystkie pola obowiązkowe są uzupełnione, w przeciwnym wypadku false
     */
    private boolean compulsoryFildNotNull() {
        return (!mail.isEmpty() && !name.isEmpty() && !surname.isEmpty() && !city.isEmpty() && !street.isEmpty()
                && !numberHouseString.isEmpty() && !postCodeFirstString.isEmpty() && !postCodeSecondString.isEmpty());
    }


    /**
     * Metoda pobiera uprawnienia z ChoiceBoxa, jeśli nie zostały zmienione ustawia uprawnienia_i na dotychczasową wartość.
     *
     * @author Krzysztof Banaś
     */
    private void checkUprawnienia() {
        String ustaw = uprawnienia.getValue();
        try {
            if (ustaw.equals("Użytkownik")) {
                uprawnienia_i = 0;
            } else if (ustaw.equals("Ankieter")) {
                uprawnienia_i = 1;
            } else if (ustaw.equals("Osoba odpowiedzialna za nagrody")) {
                uprawnienia_i = 2;
            } else if (ustaw.equals("Administrator")) {
                uprawnienia_i = 3;
            }
        } catch (RuntimeException pusteuprawnienia) {
            uprawnienia_i = edycja.getUprawnienia();
        }
    }

    /**
     * Metoda sprawdza, czy hasło ma odpowiednia ilośc znaków i czy potwórz hasło jest takie samo jak hasło.
     *
     * @return true jeśli hasło ma odpowiednią długość i jest takie samo jak powtórz hasło, w przeciwnym wypadku false.
     */
    private boolean checkPassword() {
        if ((password.length() < minSizePassword) && (password.length() != 0)) {
            return false;
        } else {
            return true;
        }
    }


    @FXML
    void panelEdycjiUzytkownikowButtonZapisz(ActionEvent event) {
        mail = email.getText();
        password = haslo.getText();
        name = imie.getText();
        surname = nazwisko.getText();
        city = miejscowosc.getText();
        street = ulica.getText();
        numberHouseString = budynek.getText();
        numberFlatString = lokal.getText();
        postCodeFirstString = kod1.getText();
        postCodeSecondString = kod2.getText();
        Walidacja walidacja = new Walidacja();
        if (compulsoryFildNotNull()) {
            if (walidacja.czyPoprawnyKodPocztowy(postCodeFirstString, postCodeSecondString)) {
                if (checkPassword()) {
                    if (walidacja.czyPoprawnyMail(mail)) {
                        UzytkownicyQuery update = new UzytkownicyQuery();
                        postCodeFirstInt = Integer.parseInt(postCodeFirstString);
                        postCodeSecondInt = Integer.parseInt(postCodeSecondString);
                        String postCode = postCodeFirstInt + "-" + postCodeSecondInt;
                        edycja.setMail(mail);
                        if (!password.isEmpty()) {
                            edycja.setHaslo(password);
                        }
                        checkUprawnienia();
                        edycja.setUprawnienia(uprawnienia_i);
                        edycja.setImie(name);
                        edycja.setNazwisko(surname);
                        edycja.setMiejscowosc(city);
                        edycja.setUlica(street);
                        edycja.setNumerBudynku(numberHouseString);
                        edycja.setNumerLokalu(numberFlatString);
                        edycja.setKodPocztowy(postCode);
                        update.updateUzytkownicy(edycja);
                        loadingFXML(event, SceneFXML.PANEL_ADMINA);
                        PanelAdminaController panelAdminaController = load.getController();
                        panelAdminaController.setStartValues(curentUser);
                        activeScene(event, false, false);
                    } else {
                        panelEdycjiUzytkownikaLabelError.setText("Podany adres e-mail jest nieprawidłowy!");
                    }
                } else {
                    panelEdycjiUzytkownikaLabelError.setText("Hasło jest za krótkie!");
                }
            } else {
                panelEdycjiUzytkownikaLabelError.setText(walidacja.getBlad_kod_pocztowy());
            }
        } else {
            panelEdycjiUzytkownikaLabelError.setText("Wymagane pola są puste!");
        }
    }

    @FXML
    void wyloguj(ActionEvent event) {
        loadingFXML(event, SceneFXML.PANEL_LOGIN);
        activeScene(event, false, false);
    }

    @Override
    public void setStartValues(Uzytkownicy user) {
        this.curentUser = user;

        imie_nazwisko_rola_tmp = curentUser.getImie() + " " + curentUser.getNazwisko() + " - konto administratora";
        System.out.print(imie_nazwisko_rola_tmp);
        imie_nazwisko_rola.setText(imie_nazwisko_rola_tmp);

    }

    @Override
    public void SetStartValuesEdycjaUzytkownika(Uzytkownicy user) {
        this.edycja = user;
        setUstawienia();
    }

    private void setUstawienia() {
        String imie = edycja.getImie();
        int upr = edycja.getUprawnienia();
        String nazwisko = edycja.getNazwisko();
        String[] kod = edycja.getKodPocztowy().split("-");
        System.out.println(kod[0]);
        email.setText(edycja.getMail());
        this.imie.setText(imie);
        this.nazwisko.setText(nazwisko);

        if (upr == 0) {
            uprawnienia.setValue("Użytkownik");
        } else if (upr == 1) {
            uprawnienia.setValue("Ankieter");
        } else if (upr == 2) {
            uprawnienia.setValue("Osoba odpowiedzialna za nagrody");
        } else if (upr == 3) {
            uprawnienia.setValue("Administrator");
        }

        miejscowosc.setText(edycja.getMiejscowosc());
        ulica.setText(edycja.getUlica());
        budynek.setText(edycja.getNumerBudynku());
        lokal.setText(edycja.getNumerLokalu());
        kod1.setText(kod[0]);
        kod2.setText(kod[1]);
    }

    private void loadUprawnienia() {

        list.removeAll(list);
        String klient = "Użytkownik";
        String ankieter = "Ankieter";
        String osoba_od_nagrod = "Osoba odpowiedzialna za nagrody";
        String admin = "Administrator";
        list.addAll(klient, ankieter, osoba_od_nagrod, admin);
        uprawnienia.getItems().addAll(list);
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
    public void initialize(URL location, ResourceBundle resources) {
        loadUprawnienia();
    }
}
