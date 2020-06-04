package com.Ankiety_PZ.panele;

import com.Ankiety_PZ.hibernate.Uzytkownicy;
import com.Ankiety_PZ.query.UzytkownicyQuery;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Klasa odpowiwada ze rejestrację użytkownika.
 * Klasa kontroler do ramki 'PanelRegi.fxml', jest potomkiem klasy {@link BulidStage}.
 */

public class PanelRegiController extends BulidStage {

    /**
     * Mail wczytany z pola tekstowego jako String.
     */

    private String email;

    /**
     * Hasło wczytane z pola tekstowego jako String.
     */

    private String password;

    /**
     * Powtórnie wprowadzone hasło wczytane z pola tekstowego jako String.
     */

    private String passwordRepeat;

    /**
     * Imię wczytane z pola tekstowego jako String.
     */

    private String name;

    /**
     * Nazwisko wczytane z pola tekstowego jako String.
     */

    private String surname;

    /**
     * Miasto wczytane z pola tekstowego jako String.
     */

    private String city;

    /**
     * Ulica wczytana z pola tekstowego jako String.
     */

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
     * Numer lokalu przekształcony na int, jeśli wartość tej zmiennej jest -1 to pole jest puste.
     */

    private int numberFlatInt = -1;

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

    /**
     * Metoda sprawdzenie czy obowiązkowe pola nie są puste.
     *
     * @return true jeśli wszystkie pola obowiązkowe są uzupełnione, w przeciwnym wypadku false
     */

    private boolean compulsoryFildNotNull() {
        return (!email.isEmpty() && !password.isEmpty() && !passwordRepeat.isEmpty() && !name.isEmpty() &&
                !surname.isEmpty() && !city.isEmpty() && !street.isEmpty() && !numberHouseString.isEmpty() &&
                !postCodeFirstString.isEmpty() && !postCodeSecondString.isEmpty());
    }

    /**
     * Metoda sprawdza czy kod pocztowy składa się z liczb i czy ma odpowiedznią długość.
     *
     * @return true jeśli kod pocztowy jest poprawny, w przeciwnym razie false
     */

    private boolean postCodeIsNumber() {
        try {
            if (postCodeFirstString.length() == 2 && postCodeSecondString.length() == 3) {
                postCodeFirstInt = Integer.parseInt(postCodeFirstString);
                postCodeSecondInt = Integer.parseInt(postCodeSecondString);
                return true;
            } else {
                panelRegiLabelError.setText("Kod pocztowy ma niepoprawną długość!");
            }
        } catch (IllegalArgumentException argumentException) {
            panelRegiLabelError.setText("Kod pocztowy jest niepoprawny!");
            System.out.println(argumentException.getMessage());
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
        return false;
    }

    /**
     * Metoda sprawdza, czy hasło ma odpowiednia ilośc znaków i czy potwórz hasło jest takie samo jak hasło.
     *
     * @return true jeśli hasło ma odpowiednią długość i jest takie samo jak powtórz hasło, w przeciwnym wypadku false.
     */

    private boolean checkPassword() {
        if (password.length() < minSizePassword) {
            panelRegiLabelError.setText("Hasło jest za krótkie!");
        } else {
            if (password.equals(passwordRepeat)) {
                return true;
            } else {
                panelRegiLabelError.setText("Hasła nie są takie same!");
            }
        }
        return false;
    }

    /**
     * Metoda sprawdza, czy w podanym adresie e-mail znajduje się @.
     *
     * @return true jeśli sdres e-mail posiada @, w przeciwnym wypadku false.
     */

    private boolean chechEmail() {
        if (email.indexOf('@') != -1) {
            return true;
        }
        return false;
    }

    /**
     * Metoda sprawdza, czy użytkownik o podanym adresie e-mail istnieje w stystemie.
     *
     * @param query obiekt klasy UzytkownicyQuery.
     * @return true jeśli nie ma użytkownika o podanym adresie e-mail w bazie, w przeciwnym wypadku false.
     */

    private boolean userExist(UzytkownicyQuery query) {
        Uzytkownicy user = query.selectByMail(email);
        System.out.println(user);
        if (user == null) {
            return true;
        }
        return false;
    }

    /**
     * Załadowanie panelu logowania.
     *
     * @param event akcja po której ma wykonać się zmiana sceny
     */
    private void backToLogin(ActionEvent event){
        loadingFXML(event, SceneFXML.PANEL_LOGIN);
        activeScene(event, false, false);
    }

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField paneliRegiTFEmail;

    @FXML
    private PasswordField panelRegiPFPassword;

    @FXML
    private PasswordField panelRegiPFPasswordRepeat;

    @FXML
    private TextField panelRegiTFName;

    @FXML
    private TextField panelRegiTFSurname;

    @FXML
    private TextField panelRegiTFCity;

    @FXML
    private TextField panelRegiTFStreet;

    @FXML
    private TextField panelRegiTFNumberHouse;

    @FXML
    private TextField panelRegiTFNumberFlat;

    @FXML
    private TextField panelRegiTFPostCodeFirst;

    @FXML
    private TextField panelRegiTFPostCodeSecond;

    @FXML
    private Button panelRegiButtonRegi;

    @FXML
    private Button panelRegiButtonCanel;

    @FXML
    private Label panelRegiLabelError;

    /**
     * Metoda obsługująca wciśnięcie przycisku zarejestruj.
     *
     * @param event zdarzenie, po którym funkcja ma się wywołać
     */

    @FXML
    void panelRegiButtonRegiAction(ActionEvent event) {

        panelRegiLabelError.setText("");
        email = paneliRegiTFEmail.getText();
        password = panelRegiPFPassword.getText();
        passwordRepeat = panelRegiPFPasswordRepeat.getText();
        name = panelRegiTFName.getText();
        surname = panelRegiTFSurname.getText();
        city = panelRegiTFCity.getText();
        street = panelRegiTFStreet.getText();
        numberHouseString = panelRegiTFNumberHouse.getText();
        numberFlatString = panelRegiTFNumberFlat.getText();
        postCodeFirstString = panelRegiTFPostCodeFirst.getText();
        postCodeSecondString = panelRegiTFPostCodeSecond.getText();

        if (compulsoryFildNotNull()) {
            if (postCodeIsNumber()) {
                if (checkPassword()) {
                    if (chechEmail()) {
                        UzytkownicyQuery query = new UzytkownicyQuery();
                        if (userExist(query)) {
                            String postCode = postCodeFirstInt + "-" + postCodeSecondInt;

                            Uzytkownicy user = new Uzytkownicy(name, surname, email, password, Permissions.KLIENT,
                                    city, street, numberHouseString, numberFlatString, postCode, 0);
                            query.addUzytkownicy(user);

                            backToLogin(event);
                        } else {
                            panelRegiLabelError.setText("Istnieje użytkownik o tym e-mailu!");
                        }
                    } else {
                        panelRegiLabelError.setText("Podany adres e-mailu jest nieprawidłowy!");
                    }
                } else {
                    panelRegiLabelError.setText("Podane hasłą różnią się od siebie!");
                }
            } else {
                panelRegiLabelError.setText("Nieprawidłowy kod pocztowy!");
            }
        } else {
            panelRegiLabelError.setText("Wymagane pola są puste!");
        }
    }

    @FXML
    void panelRegiButtonCanelAction(ActionEvent event) {
        backToLogin(event);
    }

    @FXML
    void initialize() {
        assert paneliRegiTFEmail != null : "fx:id=\"paneliRegiTFEmail\" was not injected: check your FXML file 'PanelRegi.fxml'.";
        assert panelRegiPFPassword != null : "fx:id=\"panelRegiPFPassword\" was not injected: check your FXML file 'PanelRegi.fxml'.";
        assert panelRegiPFPasswordRepeat != null : "fx:id=\"panelRegiPFPasswordRepeat\" was not injected: check your FXML file 'PanelRegi.fxml'.";
        assert panelRegiTFName != null : "fx:id=\"panelRegiTFName\" was not injected: check your FXML file 'PanelRegi.fxml'.";
        assert panelRegiTFSurname != null : "fx:id=\"panelRegiTFSurname\" was not injected: check your FXML file 'PanelRegi.fxml'.";
        assert panelRegiTFCity != null : "fx:id=\"panelRegiTFCity\" was not injected: check your FXML file 'PanelRegi.fxml'.";
        assert panelRegiTFStreet != null : "fx:id=\"panelRegiTFStreet\" was not injected: check your FXML file 'PanelRegi.fxml'.";
        assert panelRegiTFNumberHouse != null : "fx:id=\"panelRegiTFNumberHouse\" was not injected: check your FXML file 'PanelRegi.fxml'.";
        assert panelRegiTFNumberFlat != null : "fx:id=\"panelRegiTFNumberFlat\" was not injected: check your FXML file 'PanelRegi.fxml'.";
        assert panelRegiTFPostCodeFirst != null : "fx:id=\"panelRegiTFPostCodeFirst\" was not injected: check your FXML file 'PanelRegi.fxml'.";
        assert panelRegiTFPostCodeSecond != null : "fx:id=\"panelRegiTFPostCodeSecond\" was not injected: check your FXML file 'PanelRegi.fxml'.";
        assert panelRegiButtonRegi != null : "fx:id=\"panelRegiButtonRegi\" was not injected: check your FXML file 'PanelRegi.fxml'.";
        assert panelRegiButtonCanel != null : "fx:id=\"panelRegiButtonCanel\" was not injected: check your FXML file 'PanelRegi.fxml'.";
        assert panelRegiLabelError != null : "fx:id=\"panelRegiLabelError\" was not injected: check your FXML file 'PanelRegi.fxml'.";
        panelRegiLabelError.setText("");
    }

}
