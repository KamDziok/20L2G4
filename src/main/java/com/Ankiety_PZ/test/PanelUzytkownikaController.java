/**
 * Sample Skeleton for 'panelUzytkownika.fxml' Controller Class
 */

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

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class PanelUzytkownikaController extends BulidStage implements SetStartValues {

    private Uzytkownicy curentUser;
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;
    @FXML // URL location of the FXML file that was given to the FXMLLoader
     private String textPkt;
    private URL location;
    private String sprawdzhaslo;
    private String mailUser;
    private String passwordUser;
    private String passwordRepeatUser;
    private String passwordNewUser;
    private String nameUser;
    private String surnameUser;
    private String cityUser;
    private String streetUser;
    /** Numer domu wczytany z pola tekstowego jako String. */
    private String numberHouseStringUser;
    /** Numer lokalu wczytany z pola tekstowego jako String. */
    private String numberFlatStringUser;
    /** Pierwsza część kodu pocztowego wczytany z pola tekstowego jako String. */
    private String postCodeFirstStringUser;
    /** Druga część kodu pocztowego wczytany z pola tekstowego jako String. */
    private String postCodeSecondStringUser;
    /** Pierwsza część kodu pocztowego przekształconego na int. */
    private int postCodeFirstIntUser;
    /** Druga część kodu pocztowego przekształconego na int. */
    private int postCodeSecondIntUser;
    /** Minimalna długośc hasłą. */
    private final int minSizePasswordUser = 3;
    @FXML
    private Label panelUzytkownikaLabelError;
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

    /**
     * Metoda sprawdzenie czy obowiązkowe pola nie są puste.
     *
     * @return true jeśli wszystkie pola obowiązkowe są uzupełnione, w przeciwnym wypadku false
     */
    private boolean compulsoryFildNotNullUser(){
        return (!mailUser.isEmpty() && !nameUser.isEmpty() &&
                !surnameUser.isEmpty() && !cityUser.isEmpty() &&
                !streetUser.isEmpty() && !numberHouseStringUser.isEmpty() &&
                !postCodeFirstStringUser.isEmpty() && !postCodeSecondStringUser.isEmpty());
    }

    /**
     * Metoda sprawdza czy kod pocztowy składa się z liczb i czy ma odpowiedznią długość.
     *
     * @author KamDziok
     * @return true jeśli kod pocztowy jest poprawny, w przeciwnym razie false
     */
    private boolean postCodeIsNumberUser(){
        try{
            if(postCodeFirstStringUser.length() == 2 && postCodeSecondStringUser.length() == 3) {
                postCodeFirstIntUser = Integer.parseInt(postCodeFirstStringUser);
                postCodeSecondIntUser = Integer.parseInt(postCodeSecondStringUser);
                return true;
            }else{
                panelUzytkownikaLabelError.setText("Kod pocztowy ma niepoprawną długość!");
            }
        }catch(IllegalArgumentException argumentException){
            panelUzytkownikaLabelError.setText("Kod pocztowy jest niepoprawny!");
            System.out.println(argumentException.getMessage());
        }catch(Exception exception){
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
    private boolean checkPasswordUser(){
        if(((passwordRepeatUser.length() < minSizePasswordUser) ||
                (passwordNewUser.length() < minSizePasswordUser)) &&
                (!passwordUser.isEmpty())){

            sprawdzhaslo ="Hasło jest za krótkie lub nie wypełniłeś wszystkich pól!";
        }else {
            if(!passwordNewUser.equals(passwordRepeatUser)){
                sprawdzhaslo ="Hasła nie są takie same!";
            }else{
                if(passwordUser.equals(curentUser.getHaslo())){
                    return true;
                }
                else if(passwordUser.isEmpty() && passwordNewUser.isEmpty() && passwordRepeatUser.isEmpty()){
                    return true;
                }
                else{
                    sprawdzhaslo ="Podałeś niepoprawne hasło do konta!";}
            }
        }
        return false;
    }

    /**
     * Metoda sprawdza, czy w podanym adresie e-mail znajduje się @.
     *
     * @return true jeśli sdres e-mail posiada @, w przeciwnym wypadku false.
     */
    private boolean chechEmailUser(){
        if(mailUser.indexOf('@') != -1){
            return true;
        }
        return false;
    }

    @FXML
    void panelUzytkownikaButtonZmienUstawienia(ActionEvent event) {
        mailUser = email.getText();
        passwordUser = haslo.getText();
        passwordNewUser = nowehaslo.getText();
        passwordRepeatUser = hasloznowu.getText();
        nameUser = imie.getText();
        surnameUser = nazwisko.getText();
        cityUser = miejscowosc.getText();
        streetUser = ulica.getText();
        numberHouseStringUser = budynek.getText();
        numberFlatStringUser = lokal.getText();
        postCodeFirstStringUser = kod1.getText();
        postCodeSecondStringUser = kod2.getText();
        if(compulsoryFildNotNullUser()){
            if(postCodeIsNumberUser()){
                if(checkPasswordUser()){
                    if(chechEmailUser()) {
                        UzytkownicyQuery update = new UzytkownicyQuery();
                        String postCode = postCodeFirstIntUser + "-" + postCodeSecondIntUser;
                        curentUser.setMail(mailUser);
                        curentUser.setImie(nameUser);
                        if(!passwordUser.isEmpty()){
                            curentUser.setHaslo(passwordRepeatUser);
                        }
                        curentUser.setNazwisko(surnameUser);
                        curentUser.setMiejscowosc(cityUser);
                        curentUser.setUlica(streetUser);
                        curentUser.setNumerBudynku(numberHouseStringUser);
                        curentUser.setNumerLokalu(numberFlatStringUser);
                        curentUser.setKodPocztowy(postCode);
                        update.updateUzytkownicy(curentUser);
                        textPkt = curentUser.getImie() + " " + curentUser.getNazwisko()+ " posiadasz ";
                        labelPunkty.setText(textPkt);
                        labelPunktyUstawienia.setText(textPkt);
                        labelPunktyNagrody.setText(textPkt);
                        panelUzytkownikaLabelError.setText("Profil został pomyślnie zaktualizowany.");
                    }else{
                        panelUzytkownikaLabelError.setText("Podany adres e-mail jest nieprawidłowy!");
                    }
                }else{
                    panelUzytkownikaLabelError.setText(sprawdzhaslo);
                }
            }else{
                panelUzytkownikaLabelError.setText("Nieprawidłowy kod pocztowy!");
            }
        }else{
            panelUzytkownikaLabelError.setText("Wymagane pola są puste!");
        }
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
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
        for (Ankiety ankieta:ankiety
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
        for (Nagrody nagroda:nagrody
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
}
