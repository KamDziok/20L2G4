/**
 * Sample Skeleton for 'PanelRegi.fxml' Controller Class
 */

package com.Ankiety_PZ.test;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;

/**
 * Klasa kontroler do ramki 'PanelRegi.fxml', jest potomkiem klasy {@link BulidStage}.
 *
 * @author KamDziok
 */

public class PanelRegiController extends BulidStage {

    private String email;
    private String password;
    private String passwordRepeat;
    private String name;
    private String surname;
    private String city;
    private String street;
    /** Numer domu wczytany z pola tekstowego jako String. */
    private String numberHouseString;
    /** Numer lokalu wczytany z pola tekstowego jako String. */
    private String numberFlatString;
    /** Pierwsza część kodu pocztowego wczytany z pola tekstowego jako String. */
    private String postCodeFirstString;
    /** Druga część kodu pocztowego wczytany z pola tekstowego jako String. */
    private String postCodeSecondString;
    /** Numer lokalu przekształcony na int, jeśli wartość tej zmiennej jest -1 to pole jest puste. */
    private int numberFlatInt = -1;
    /** Pierwsza część kodu pocztowego przekształconego na int. */
    private int postCodeFirstInt;
    /** Druga część kodu pocztowego przekształconego na int. */
    private int postCodeSecondInt;
    /** Minimalna długośc hasłą. */
    private final int minSizePassword = 3;

    /**
     * Metoda sprawdzenie czy obowiązkowe pola nie są puste.
     *
     * @author KamDziok
     * @return true jeśli wszystkie pola obowiązkowe są uzupełnione, w przeciwnym wypadku false
     */
    private boolean compulsoryFildNotNull(){
        return (!email.isEmpty() && !password.isEmpty() && !passwordRepeat.isEmpty() && !name.isEmpty() &&
                !surname.isEmpty() && !city.isEmpty() && !street.isEmpty() && !numberHouseString.isEmpty() &&
                !postCodeFirstString.isEmpty() && !postCodeSecondString.isEmpty());
    }

    /**
     * Metoda sprawdzenie czy numer mieszkania jest liczbą, jeśli poje nie jest puste.
     *
     * @author KamDziok
     * @return true jeśli numer lokalu jest podany i jest liczbą, lub jeśli numer lokalu jest pusty, w przeciwnym wypadku false
     */
    private boolean numberFlatIsNumber(){
        try{
            if(!numberFlatString.isEmpty()) {
                numberFlatInt = Integer.parseInt(numberFlatString);
                return true;
            }
            return true;
        }catch(IllegalArgumentException argumentException){
            panelRegiLabelError.setText("Numer lokalu nie jest liczbą!");
            System.out.println(argumentException.getMessage());
        }catch(Exception exception){
            System.out.println(exception.getMessage());
        }
        return false;
    }

    /**
     * Metoda sprawdza czy kod pocztowy składa się z liczb i czy ma odpowiedznią długość.
     *
     * @author KamDziok
     * @return true jeśli kod pocztowy jest poprawny, w przeciwnym razie false
     */
    private boolean postCodeIsNumber(){
        try{
            if(postCodeFirstString.length() == 2 && postCodeSecondString.length() == 3) {
                postCodeFirstInt = Integer.parseInt(postCodeFirstString);
                postCodeSecondInt = Integer.parseInt(postCodeSecondString);
                return true;
            }else{
                panelRegiLabelError.setText("Kod pocztowy ma niepoprawną długość!");
            }
        }catch(IllegalArgumentException argumentException){
            panelRegiLabelError.setText("Kod pocztowy jest niepoprawny!");
            System.out.println(argumentException.getMessage());
        }catch(Exception exception){
            System.out.println(exception.getMessage());
        }
        return false;
    }

    /**
     * Metoda sprawdza, czy hasło ma odpowiednia ilośc znaków i czy potwórz hasło jest takie samo jak hasło.
     *
     * @return true jeśli hasło ma odpowiednią długość i jest takie samo jak powtórz hasło, w przeciwnym wypadku false.
     */
    private boolean checkPassword(){
        if(password.length() < minSizePassword){
            panelRegiLabelError.setText("Hasło jest za krótkie!");
        }else {
            if(password.equals(passwordRepeat)){
                return true;
            }else{
                panelRegiLabelError.setText("Hasła nie są takie same!");
            }
        }
        return false;
    }

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="paneliRegiTFEmail"
    private TextField paneliRegiTFEmail; // Value injected by FXMLLoader

    @FXML // fx:id="panelRegiPFPassword"
    private PasswordField panelRegiPFPassword; // Value injected by FXMLLoader

    @FXML // fx:id="panelRegiPFPasswordRepeat"
    private PasswordField panelRegiPFPasswordRepeat; // Value injected by FXMLLoader

    @FXML // fx:id="panelRegiTFName"
    private TextField panelRegiTFName; // Value injected by FXMLLoader

    @FXML // fx:id="panelRegiTFSurname"
    private TextField panelRegiTFSurname; // Value injected by FXMLLoader

    @FXML // fx:id="panelRegiTFCity"
    private TextField panelRegiTFCity; // Value injected by FXMLLoader

    @FXML // fx:id="panelRegiTFStreet"
    private TextField panelRegiTFStreet; // Value injected by FXMLLoader

    @FXML // fx:id="panelRegiTFNumberHouse"
    private TextField panelRegiTFNumberHouse; // Value injected by FXMLLoader

    @FXML // fx:id="panelRegiTFNumberFlat"
    private TextField panelRegiTFNumberFlat; // Value injected by FXMLLoader

    @FXML // fx:id="panelRegiTFPostCodeFirst"
    private TextField panelRegiTFPostCodeFirst; // Value injected by FXMLLoader

    @FXML // fx:id="panelRegiTFPostCodeSecond"
    private TextField panelRegiTFPostCodeSecond; // Value injected by FXMLLoader

    @FXML // fx:id="panelRegiButtonRegi"
    private Button panelRegiButtonRegi; // Value injected by FXMLLoader

    @FXML // fx:id="panelRegiLabelError"
    private Label panelRegiLabelError; // Value injected by FXMLLoader

    /**
     * Metoda obsługująca wciśnięcie przycisku zarejestruj.
     *
     * @param event zdarzenie, po którym funkcja ma się wywołać
     */
    @FXML
    void panelRegiButtonRegiAction(ActionEvent event) {

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

        if(compulsoryFildNotNull()){
            if(numberFlatIsNumber()){
                if(postCodeIsNumber()){
                    if(checkPassword()){
                        //User user = new User(...);
                        //addUser();
                        loadingFXML(event, SceneFXML.PANEL_LOGIN);
                        activeScene(event,false, false);
                    }
                }
            }
        }else{
            panelRegiLabelError.setText("Wymagane pola są puste!");
        }
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
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
        assert panelRegiLabelError != null : "fx:id=\"panelRegiLabelError\" was not injected: check your FXML file 'PanelRegi.fxml'.";

        panelRegiLabelError.setText("");
    }

}
