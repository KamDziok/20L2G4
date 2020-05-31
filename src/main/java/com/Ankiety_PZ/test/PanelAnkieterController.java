package com.Ankiety_PZ.test;
/**
 * Sample Skeleton for 'panelAnkieter.fxml' Controller Class
 */


import com.Ankiety_PZ.hibernate.Ankiety;
import com.Ankiety_PZ.hibernate.Nagrody;
import com.Ankiety_PZ.hibernate.Pytania;
import com.Ankiety_PZ.hibernate.Uzytkownicy;
import com.Ankiety_PZ.query.AnkietyQuery;
import com.Ankiety_PZ.query.UzytkownicyQuery;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.*;

public class PanelAnkieterController extends BulidStage implements SetStartValues {
    private Uzytkownicy curentUser2;
    private Uzytkownicy curentUser;
    public int id_ankiety;
    private String imie_nazwisko_rola_tmp;
    private String sprawdzhaslo;
    private String mailAnkieter;
    private String passwordAnkieter;
    private String passwordRepeatAnkieter;
    private String passwordNewAnkieter;
    private String nameAnkieter;
    private String surnameAnkieter;
    private String cityAnkieter;
    private String streetAnkieter;
    @FXML
    private Label panelAnkieterLabelError;
    /**
     * Numer domu wczytany z pola tekstowego jako String.
     */
    private String numberHouseStringAnkieter;
    /**
     * Numer lokalu wczytany z pola tekstowego jako String.
     */
    private String numberFlatStringAnkieter;
    /**
     * Pierwsza część kodu pocztowego wczytany z pola tekstowego jako String.
     */
    private String postCodeFirstStringAnkieter;
    /**
     * Druga część kodu pocztowego wczytany z pola tekstowego jako String.
     */
    private String postCodeSecondStringAnkieter;

    /**
     * Pierwsza część kodu pocztowego przekształconego na int.
     */
    private int postCodeFirstIntAnkieter;
    /**
     * Druga część kodu pocztowego przekształconego na int.
     */
    private int postCodeSecondIntAnkieter;
    /**
     * Minimalna długośc hasłą.
     */
    private final int minSizePasswordAnkieter = 3;

    @FXML
    private Label imie_nazwisko_rola;
    @FXML
    private Label imie_nazwisko_rola2;
    @FXML
    private Label panelAnkieteraLabelError;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML
    private Button wyloguj2;

    @FXML
    private Button wyloguj1;

    @FXML // fx:id="panelAnkietButtonDodaj"
    private Button panelAnkietButtonDodaj; // Value injected by FXMLLoader

    private Date a;
    @FXML
    private TableView tableAnkiety;
    @FXML
    private TableColumn tytul;
    @FXML
    private TableColumn wygasa;
    @FXML
    private TableColumn pkt;
    @FXML
    private TableColumn przyciskEdycja;
    @FXML
    private TableColumn przyciskUsun;
    @FXML
    private TableColumn przyciskAnaliza;


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
    private Ankiety ankiety;

    /**
     * Metoda obsługująca przyciśk Dodaj
     *
     * @param event zdarzenie, po którym funkcja ma się wywołać
     * @author HubertJakobsze
     */

    @FXML
    void panelAnkietButtonDodajAction(ActionEvent event) {
        Date data = new Date();
        Ankiety ankieta = new Ankiety();
        ankieta.setUzytkownicy(curentUser2);
        ankieta.setLiczbaWypelnien(0);
        Calendar ca = Calendar.getInstance();
        ca.setTime(data);
        ca.add(Calendar.DATE, 1);
        data = ca.getTime();
        ankieta.setDataRozpoczecia(data);
        Date dt = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(dt);
        c.add(Calendar.DATE, 31);
        dt = c.getTime();
        ankieta.setDataZakonczenia(dt);
        loadingFXML(event, SceneFXML.TWORZENIE_ANKIETY);
        PanelTworzeniaankietyController panelTworzeniaankietyController = load.getController();
        panelTworzeniaankietyController.SetEdycja(false);
        panelTworzeniaankietyController.SetStart();
        panelTworzeniaankietyController.StartListaPytan();
        panelTworzeniaankietyController.setPytdoUsuniecia();
        panelTworzeniaankietyController.setStartValuesAnkiety(ankieta);
        panelTworzeniaankietyController.setStartValues(curentUser2);
        activeScene(event, false, false);

    }

    /**
     * Metoda sprawdzenie czy obowiązkowe pola nie są puste.
     *
     * @return true jeśli wszystkie pola obowiązkowe są uzupełnione, w przeciwnym wypadku false
     */
    private boolean compulsoryFildNotNullAnkieter() {
        return (!mailAnkieter.isEmpty() && !nameAnkieter.isEmpty() &&
                !surnameAnkieter.isEmpty() && !cityAnkieter.isEmpty() && !streetAnkieter.isEmpty() && !numberHouseStringAnkieter.isEmpty() &&
                !postCodeFirstStringAnkieter.isEmpty() && !postCodeSecondStringAnkieter.isEmpty());
    }

    /**
     * Metoda sprawdza czy kod pocztowy składa się z liczb i czy ma odpowiedznią długość.
     *
     * @return true jeśli kod pocztowy jest poprawny, w przeciwnym razie false
     * @author KamDziok
     */
    private boolean postCodeIsNumberAnkieter() {
        try {
            if (postCodeFirstStringAnkieter.length() == 2 && postCodeSecondStringAnkieter.length() == 3) {
                postCodeFirstIntAnkieter = Integer.parseInt(postCodeFirstStringAnkieter);
                postCodeSecondIntAnkieter = Integer.parseInt(postCodeSecondStringAnkieter);
                return true;
            } else {
                panelAnkieteraLabelError.setText("Kod pocztowy ma niepoprawną długość!");
            }
        } catch (IllegalArgumentException argumentException) {
            panelAnkieteraLabelError.setText("Kod pocztowy jest niepoprawny!");
            System.out.println(argumentException.getMessage());
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
        return false;
    }

    /**
     * Metoda sprawdza, czy hasło ma odpowiednia ilośc znaków i czy nowe hasło jest takie samo jak powtórz hasło.
     * Sprawdza również czy dotychczasowe hasło zostało podane poprawnie, albo nie jest zmieniane i zostało puste.
     *
     * @return true jeśli hasło ma odpowiednią długość i jest takie samo jak powtórz hasło i dotychczasowe hasło
     * zostało podane poprawnie, w przeciwnym wypadku false.
     */
    private boolean checkPasswordAnkieter() {
        if (((passwordRepeatAnkieter.length() < minSizePasswordAnkieter) ||
                (passwordNewAnkieter.length() < minSizePasswordAnkieter)) && (!passwordAnkieter.isEmpty())) {

            sprawdzhaslo = "Hasło jest za krótkie lub nie wypełniłeś wszystkich pól!";
        } else {
            if (!passwordNewAnkieter.equals(passwordRepeatAnkieter)) {
                sprawdzhaslo = "Hasła nie są takie same!";
            } else {
                if (passwordAnkieter.equals(curentUser.getHaslo())) {
                    return true;
                } else if (passwordAnkieter.isEmpty() && passwordNewAnkieter.isEmpty() && passwordRepeatAnkieter.isEmpty()) {
                    return true;
                } else {
                    sprawdzhaslo = "Podałeś niepoprawne hasło do konta!";
                }
            }
        }
        return false;
    }

    /**
     * Metoda sprawdza, czy w podanym adresie e-mail znajduje się @.
     *
     * @return true jeśli sdres e-mail posiada @, w przeciwnym wypadku false.
     */
    private boolean chechEmailAnkieter() {
        if (mailAnkieter.indexOf('@') != -1) {
            return true;
        }
        return false;
    }

    @FXML
    void panelAnkieteraButtonZmienUstawienia(ActionEvent event) {

        curentUser2.setMail(email.getText());
        if (haslo.getText().equals(curentUser2.getHaslo()) && nowehaslo.getText().equals(hasloznowu.getText()))
            curentUser2.setHaslo(nowehaslo.getText());
        curentUser2.setImie(imie.getText());
        curentUser2.setNazwisko(nazwisko.getText());
        curentUser2.setMiejscowosc(miejscowosc.getText());
        curentUser2.setUlica(ulica.getText());
        curentUser2.setNumerBudynku(budynek.getText());
        curentUser2.setNumerLokalu(lokal.getText());
        curentUser2.setKodPocztowy(kod1.getText() + "-" + kod2.getText());
        UzytkownicyQuery query = new UzytkownicyQuery();
        query.updateUzytkownicy(curentUser2);

        mailAnkieter = email.getText();
        passwordAnkieter = haslo.getText();
        passwordNewAnkieter = nowehaslo.getText();
        passwordRepeatAnkieter = hasloznowu.getText();
        nameAnkieter = imie.getText();
        surnameAnkieter = nazwisko.getText();
        cityAnkieter = miejscowosc.getText();
        streetAnkieter = ulica.getText();
        numberHouseStringAnkieter = budynek.getText();
        numberFlatStringAnkieter = lokal.getText();
        postCodeFirstStringAnkieter = kod1.getText();
        postCodeSecondStringAnkieter = kod2.getText();
        if (compulsoryFildNotNullAnkieter()) {
            if (postCodeIsNumberAnkieter()) {
                if (checkPasswordAnkieter()) {
                    if (chechEmailAnkieter()) {
                        UzytkownicyQuery update = new UzytkownicyQuery();
                        String postCode = postCodeFirstIntAnkieter + "-" + postCodeSecondIntAnkieter;
                        curentUser.setMail(mailAnkieter);
                        curentUser.setImie(nameAnkieter);
                        if (!passwordAnkieter.isEmpty()) {
                            curentUser.setHaslo(passwordRepeatAnkieter);
                        }
                        curentUser.setNazwisko(surnameAnkieter);
                        curentUser.setMiejscowosc(cityAnkieter);
                        curentUser.setUlica(streetAnkieter);
                        curentUser.setNumerBudynku(numberHouseStringAnkieter);
                        curentUser.setNumerLokalu(numberFlatStringAnkieter);
                        curentUser.setKodPocztowy(postCode);
                        update.updateUzytkownicy(curentUser);
                        imie_nazwisko_rola_tmp = curentUser.getImie() + " " + curentUser.getNazwisko() + " - konto ankietera";
                        imie_nazwisko_rola.setText(imie_nazwisko_rola_tmp);
                        imie_nazwisko_rola2.setText(imie_nazwisko_rola_tmp);
                        panelAnkieteraLabelError.setText("Profil został pomyślnie zaktualizowany.");
                    } else {
                        panelAnkieteraLabelError.setText("Podany adres e-mail jest nieprawidłowy!");
                    }
                } else {
                    panelAnkieteraLabelError.setText(sprawdzhaslo);
                }
            } else {
                panelAnkieteraLabelError.setText("Nieprawidłowy kod pocztowy!");
            }
        } else {
            panelAnkieteraLabelError.setText("Wymagane pola są puste!");

        }
    }

    private void setUstawienia() {
        String imie = curentUser2.getImie();
        String nazwisko = curentUser2.getNazwisko();
        String[] kod = curentUser2.getKodPocztowy().split("-");
        email.setText(curentUser2.getMail());
        this.imie.setText(imie);
        this.nazwisko.setText(nazwisko);
        miejscowosc.setText(curentUser2.getMiejscowosc());
        ulica.setText(curentUser2.getUlica());
        budynek.setText(curentUser2.getNumerBudynku());
        lokal.setText(curentUser2.getNumerLokalu());
        kod1.setText(kod[0]);
        kod2.setText(kod[1]);
    }

    /**
     * Metoda obsługująca przyciśk wyloguj1.
     *
     * @param event zdarzenie, po którym funkcja ma się wywołać
     * @author HubertJakobsze
     */

    @FXML
    void wyloguj1Action(ActionEvent event) {
        loadingFXML(event, SceneFXML.PANEL_LOGIN);
        PanelLoginController panelLoginController = load.getController();
        activeScene(event, false, false);
    }

    /**
     * Metoda obsługująca przyciśk wyloguj2.
     *
     * @param event zdarzenie, po którym funkcja ma się wywołać
     * @author HubertJakobsze
     */
    @FXML
    void wyloguj2Action(ActionEvent event) {
        loadingFXML(event, SceneFXML.PANEL_LOGIN);
        PanelLoginController panelLoginController = load.getController();
        activeScene(event, false, false);
    }

    void ankietawtoku() {

        panelAnkieterLabelError.setText("Nie można edytować ankiety, która się rozpoczęła.");
    }

    private void setAnkiety() {
        AnkietyQuery query = new AnkietyQuery();
        List<Ankiety> ankiety = query.selectAllUzytkownik(curentUser2);
        ObservableList<AnikieterTabelka> dane = FXCollections.observableArrayList();
        for (Ankiety ankieta2 : ankiety
        ) {

            dane.add(new AnikieterTabelka(ankieta2, curentUser));


        }
        tableAnkiety.itemsProperty().setValue(dane);
        tytul.setCellValueFactory(new PropertyValueFactory("tytul"));
        wygasa.setCellValueFactory(new PropertyValueFactory("dataZakonczenia"));
        pkt.setCellValueFactory(new PropertyValueFactory("liczbaPunktow"));
        przyciskEdycja.setCellValueFactory(new PropertyValueFactory("buttonEdycja"));
        przyciskUsun.setCellValueFactory(new PropertyValueFactory("buttonUsun"));
        przyciskAnaliza.setCellValueFactory(new PropertyValueFactory("buttonAnaliza"));

    }

    @Override
    public void setStartValues(Uzytkownicy user) {

        this.curentUser2 = user;
        this.curentUser = user;
        imie_nazwisko_rola_tmp = curentUser.getImie() + " " + curentUser.getNazwisko() + " - konto ankietera";
        imie_nazwisko_rola.setText(imie_nazwisko_rola_tmp);
        imie_nazwisko_rola2.setText(imie_nazwisko_rola_tmp);

        setUstawienia();
        setAnkiety();
    }

    @Override
    public void setStartValuesAnkiety(Ankiety ankieta) {
        this.ankiety = ankieta;
        System.out.println("ankiety setStartValuesAnkiety pac");
        System.out.println(ankiety);


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


    @FXML
        // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert wyloguj2 != null : "fx:id=\"wyloguj2\" was not injected: check your FXML file 'PanelAnkieter.fxml'.";
        assert panelAnkietButtonDodaj != null : "fx:id=\"panelAnkietButtonDodaj\" was not injected: check your FXML file 'PanelAnkieter.fxml'.";
        assert wyloguj1 != null : "fx:id=\"wyloguj1\" was not injected: check your FXML file 'PanelAnkieter.fxml'.";
        assert imie_nazwisko_rola2 != null : "fx:id=\"imie_nazwisko_rola2\" was not injected: check your FXML file 'PanelAnkieter.fxml'.";


    }

}
