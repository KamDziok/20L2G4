/**
 * Sample Skeleton for 'PanelOsobyOdNagrod.fxml' Controller Class
 */

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
    private String sprawdzhaslo;
    private String mailN;
    private String passwordN;
    private String passwordRepeatN;
    private String passwordNewN;
    private String nameN;
    private String surnameN;
    private String cityN;
    private String streetN;
    /**
     * Numer domu wczytany z pola tekstowego jako String.
     */
    private String numberHouseStringN;
    /**
     * Numer lokalu wczytany z pola tekstowego jako String.
     */
    private String numberFlatStringN;
    /**
     * Pierwsza część kodu pocztowego wczytany z pola tekstowego jako String.
     */
    private String postCodeFirstStringN;
    /**
     * Druga część kodu pocztowego wczytany z pola tekstowego jako String.
     */
    private String postCodeSecondStringN;

    /**
     * Pierwsza część kodu pocztowego przekształconego na int.
     */
    private int postCodeFirstIntN;
    /**
     * Druga część kodu pocztowego przekształconego na int.
     */
    private int postCodeSecondIntN;
    /**
     * Minimalna długośc hasłą.
     */
    private final int minSizePasswordN = 3;

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

    @FXML
    private Button panelOsobyOdNagrodButtonZmienUstawienia;

    public TableColumn getNagrody() {
        return nagrody;
    }

    public TableColumn getZdjecie() {
        return zdjecie;
    }

    public TableColumn getPkt() {
        return pkt;
    }

    public TableColumn getUsun() {
        return usun;
    }

    public TableColumn getEdytuj() {
        return edytuj;
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

    /**
     * Metoda sprawdzenie czy obowiązkowe pola nie są puste.
     *
     * @return true jeśli wszystkie pola obowiązkowe są uzupełnione, w przeciwnym wypadku false
     */
    private boolean compulsoryFildNotNullN() {
        return (!mailN.isEmpty() && !nameN.isEmpty() &&
                !surnameN.isEmpty() && !cityN.isEmpty() && !streetN.isEmpty() && !numberHouseStringN.isEmpty() &&
                !postCodeFirstStringN.isEmpty() && !postCodeSecondStringN.isEmpty());
    }

    /**
     * Metoda sprawdza czy kod pocztowy składa się z liczb i czy ma odpowiedznią długość.
     *
     * @return true jeśli kod pocztowy jest poprawny, w przeciwnym razie false
     * @author KamDziok
     */
    private boolean postCodeIsNumberN() {
        try {
            if (postCodeFirstStringN.length() == 2 && postCodeSecondStringN.length() == 3) {
                postCodeFirstIntN = Integer.parseInt(postCodeFirstStringN);
                postCodeSecondIntN = Integer.parseInt(postCodeSecondStringN);
                return true;
            } else {
                panelNagrodLabelError.setText("Kod pocztowy ma niepoprawną długość!");
            }
        } catch (IllegalArgumentException argumentException) {
            panelNagrodLabelError.setText("Kod pocztowy jest niepoprawny!");
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
    private boolean checkPasswordN() {
        if (((passwordRepeatN.length() < minSizePasswordN) || (passwordNewN.length() < minSizePasswordN)) && (!passwordN.isEmpty())) {

            sprawdzhaslo = "Hasło jest za krótkie lub nie wypełniłeś wszystkich pól!";
        } else {
            if (!passwordNewN.equals(passwordRepeatN)) {
                sprawdzhaslo = "Hasła nie są takie same!";
            } else {
                if (passwordN.equals(curentUser.getHaslo())) {
                    return true;
                } else if (passwordN.isEmpty() && passwordNewN.isEmpty() && passwordRepeatN.isEmpty()) {
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
    private boolean chechEmailN() {
        if (mailN.indexOf('@') != -1) {
            return true;
        }
        return false;
    }

    @FXML
    void panelOsobyOdNagrodButtonZmienUstawienia(ActionEvent event) {
        mailN = email.getText();
        passwordN = haslo.getText();
        passwordNewN = nowehaslo.getText();
        passwordRepeatN = hasloznowu.getText();
        nameN = imie.getText();
        surnameN = nazwisko.getText();
        cityN = miejscowosc.getText();
        streetN = ulica.getText();
        numberHouseStringN = budynek.getText();
        numberFlatStringN = lokal.getText();
        postCodeFirstStringN = kod1.getText();
        postCodeSecondStringN = kod2.getText();
        if (compulsoryFildNotNullN()) {
            if (postCodeIsNumberN()) {
                if (checkPasswordN()) {
                    if (chechEmailN()) {
                        UzytkownicyQuery update = new UzytkownicyQuery();
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
                    panelNagrodLabelError.setText(sprawdzhaslo);
                }
            } else {
                panelNagrodLabelError.setText("Nieprawidłowy kod pocztowy!");
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