package com.Ankiety_PZ.test;

/**
 * Sample Skeleton for 'PanelAdmina.fxml' Controller Class
 */


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

    private Uzytkownicy curentUser;
    public ObservableList<UzytkownicyTabelka> dane;
    public ObservableList<UzytkownicyZablokowaniTabelka> dane2;
    private String imie_nazwisko_rola_tmp;
    private String sprawdzhaslo;
    private String mailAdmin;
    private String passwordAdmin;
    private String passwordRepeatAdmin;
    private String passwordNewAdmin;
    private String nameAdmin;
    private String surnameAdmin;
    private String cityAdmin;
    private String streetAdmin;
    /**
     * Numer domu wczytany z pola tekstowego jako String.
     */
    private String numberHouseStringAdmin;
    /**
     * Numer lokalu wczytany z pola tekstowego jako String.
     */
    private String numberFlatStringAdmin;
    /**
     * Pierwsza część kodu pocztowego wczytany z pola tekstowego jako String.
     */
    private String postCodeFirstStringAdmin;
    /**
     * Druga część kodu pocztowego wczytany z pola tekstowego jako String.
     */
    private String postCodeSecondStringAdmin;
    /**
     * Pierwsza część kodu pocztowego przekształconego na int.
     */
    private int postCodeFirstIntAdmin;
    /**
     * Druga część kodu pocztowego przekształconego na int.
     */
    private int postCodeSecondIntAdmin;
    /**
     * Minimalna długośc hasłą.
     */
    private final int minSizePasswordAdmin = 3;
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
    ;
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

    public TableColumn getImie_i_nazwiskoPanel() {
        return imie_i_nazwisko;
    }

    public TableColumn getMailPanel() {
        return mail;
    }

    public TableColumn getPktPanel() {
        return pkt;
    }

    public TableColumn getPrzyciskPanel() {
        return przycisk;
    }

    public TableColumn getImie_i_nazwisko_zPanel() {
        return imie_i_nazwisko_z;
    }

    public TableColumn getMail_zPanel() {
        return mail_z;
    }

    public TableColumn getPkt_zPanel() {
        return pkt_z;
    }

    public TableColumn getPrzycisk_zPanel() {
        return przycisk3;
    }

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

    /**
     * Metoda sprawdzenie czy obowiązkowe pola nie są puste.
     *
     * @return true jeśli wszystkie pola obowiązkowe są uzupełnione, w przeciwnym wypadku false
     */
    private boolean compulsoryFildNotNullAdmin() {
        return (!mailAdmin.isEmpty() && !nameAdmin.isEmpty() &&
                !surnameAdmin.isEmpty() && !cityAdmin.isEmpty() && !streetAdmin.isEmpty() && !numberHouseStringAdmin.isEmpty() &&
                !postCodeFirstStringAdmin.isEmpty() && !postCodeSecondStringAdmin.isEmpty());
    }

    /**
     * Metoda sprawdza czy kod pocztowy składa się z liczb i czy ma odpowiedznią długość.
     *
     * @return true jeśli kod pocztowy jest poprawny, w przeciwnym razie false
     * @author KamDziok
     */
    private boolean postCodeIsNumberAdmin() {
        try {
            if (postCodeFirstStringAdmin.length() == 2 && postCodeSecondStringAdmin.length() == 3) {
                postCodeFirstIntAdmin = Integer.parseInt(postCodeFirstStringAdmin);
                postCodeSecondIntAdmin = Integer.parseInt(postCodeSecondStringAdmin);
                return true;
            } else {
                panelAdminaLabelError.setText("Kod pocztowy ma niepoprawną długość!");
            }
        } catch (IllegalArgumentException argumentException) {
            panelAdminaLabelError.setText("Kod pocztowy jest niepoprawny!");
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
    private boolean checkPasswordAdmin() {
        if (((passwordRepeatAdmin.length() < minSizePasswordAdmin) || (passwordNewAdmin.length() < minSizePasswordAdmin)) && (!passwordAdmin.isEmpty())) {

            sprawdzhaslo = "Hasło jest za krótkie lub nie wypełniłeś wszystkich pól!";
        } else {
            if (!passwordNewAdmin.equals(passwordRepeatAdmin)) {
                sprawdzhaslo = "Hasła nie są takie same!";
            } else {
                if (passwordAdmin.equals(curentUser.getHaslo())) {
                    return true;
                } else if (passwordAdmin.isEmpty() && passwordNewAdmin.isEmpty() && passwordRepeatAdmin.isEmpty()) {
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
    private boolean chechEmailAdmin() {
        if (mailAdmin.indexOf('@') != -1) {
            return true;
        }
        return false;
    }

    @FXML
    void panelAdminaButtonZmienUstawienia(ActionEvent event) {
        mailAdmin = email.getText();
        passwordAdmin = haslo.getText();
        passwordNewAdmin = nowehaslo.getText();
        passwordRepeatAdmin = hasloznowu.getText();
        nameAdmin = imie.getText();
        surnameAdmin = nazwisko.getText();
        cityAdmin = miejscowosc.getText();
        streetAdmin = ulica.getText();
        numberHouseStringAdmin = budynek.getText();
        numberFlatStringAdmin = lokal.getText();
        postCodeFirstStringAdmin = kod1.getText();
        postCodeSecondStringAdmin = kod2.getText();
        if (compulsoryFildNotNullAdmin()) {
            if (postCodeIsNumberAdmin()) {
                if (checkPasswordAdmin()) {
                    if (chechEmailAdmin()) {
                        UzytkownicyQuery update = new UzytkownicyQuery();
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
                    panelAdminaLabelError.setText(sprawdzhaslo);
                }
            } else {
                panelAdminaLabelError.setText("Nieprawidłowy kod pocztowy!");
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
