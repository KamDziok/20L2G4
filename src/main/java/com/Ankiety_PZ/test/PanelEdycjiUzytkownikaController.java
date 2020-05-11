package com.Ankiety_PZ.test;

import com.Ankiety_PZ.hibernate.Ankiety;
import com.Ankiety_PZ.hibernate.Nagrody;
import com.Ankiety_PZ.hibernate.Pytania;
import com.Ankiety_PZ.hibernate.Uzytkownicy;
import com.Ankiety_PZ.query.UzytkownicyQuery;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class PanelEdycjiUzytkownikaController extends BulidStage implements SetStartValues,SetStartValuesEdycjaUzytkownika {

    Uzytkownicy curentUser;
    Uzytkownicy edycja;

    private String imie_nazwisko_rola_tmp;
    private String mail;
    private int uprawnienia_i;
    private String password;
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

    @FXML
    private Button wyloguj;

    @FXML
    private Button panelEdycjiUzytkownikowButtonZapisz;

    @FXML
    private Button panelEdycjiUzytkownikowButtonAnuluj;

    @FXML
    private Label panelEdycjiUzytkownikaLabelError;

    @FXML
    private Label imie_nazwisko_rola;

    @FXML
    private TextField email;

    @FXML
    private TextField haslo;

    @FXML
    private TextField uprawnienia;

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
    private boolean compulsoryFildNotNull(){
        return (!mail.isEmpty() && !name.isEmpty() && !surname.isEmpty() && !city.isEmpty() && !street.isEmpty()
                && !numberHouseString.isEmpty() && !postCodeFirstString.isEmpty() && !postCodeSecondString.isEmpty());
    }

    /**
     * Metoda sprawdza czy kod pocztowy składa się z liczb i czy ma odpowiedznią długość.
     *
     * @return true jeśli kod pocztowy jest poprawny, w przeciwnym razie false
     */
    private boolean postCodeIsNumber(){
        try{
            if(postCodeFirstString.length() == 2 && postCodeSecondString.length() == 3) {
                postCodeFirstInt = Integer.parseInt(postCodeFirstString);
                postCodeSecondInt = Integer.parseInt(postCodeSecondString);
                return true;
            }else{
                panelEdycjiUzytkownikaLabelError.setText("Kod pocztowy ma niepoprawną długość!");
            }
        }catch(IllegalArgumentException argumentException){
            panelEdycjiUzytkownikaLabelError.setText("Kod pocztowy jest niepoprawny!");
            System.out.println(argumentException.getMessage());
        }catch(Exception exception){
            System.out.println(exception.getMessage());
        }
        return false;
    }

    /**
     * Metoda sprawdza czy numer uprawniania zawiera liczbę i czy jest ona z zakresu dostepnych uprawnień.
     *
     * @return true jeśli uprawnienie jest poprawne, w przeciwnym razie false
     */
    private boolean checkUprawnienia(){
        try{
            if(uprawnienia_i >= -1 && uprawnienia_i <= 3) {
                return true;
            }else{
                panelEdycjiUzytkownikaLabelError.setText("Niepoprawny kod uprawnień!");
            }
        }catch(IllegalArgumentException argumentException){
            panelEdycjiUzytkownikaLabelError.setText("Niepoprawny kod uprawnień!");
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
        if((password.length() < minSizePassword) && (password.length() != 0)){
            return false;
        }else {
            return true;
        }
    }

    /**
     * Metoda sprawdza, czy w podanym adresie e-mail znajduje się @.
     *
     * @return true jeśli sdres e-mail posiada @, w przeciwnym wypadku false.
     */
    private boolean chechEmail(){
        if(mail.indexOf('@') != -1){
            return true;
        }
        return false;
    }

    @FXML
    void panelEdycjiUzytkownikowButtonZapisz(ActionEvent event) {
        mail = email.getText();
        password = haslo.getText();
        uprawnienia_i =Integer.parseInt(uprawnienia.getText());
        name = imie.getText();
        surname = nazwisko.getText();
        city = miejscowosc.getText();
        street = ulica.getText();
        numberHouseString = budynek.getText();
        numberFlatString = lokal.getText();
        postCodeFirstString = kod1.getText();
        postCodeSecondString = kod2.getText();

        if(compulsoryFildNotNull()){
            if(postCodeIsNumber()){
                if(checkPassword()){
                    if(chechEmail()) {
                        if(checkUprawnienia()) {
                            UzytkownicyQuery update = new UzytkownicyQuery();
                            String postCode = postCodeFirstInt + "-" + postCodeSecondInt;
                            edycja.setMail(mail);
                            if(!password.isEmpty()){
                            edycja.setHaslo(password);}
                            edycja.setUprawnienia(uprawnienia_i);
                            edycja.setImie(name);
                            edycja.setNazwisko(surname);
                            edycja.setMiejscowosc(city);
                            edycja.setUlica(street);
                            edycja.setNumerBudynku(numberHouseString);
                            edycja.setNumerLokalu(numberFlatString);
                            edycja.setKodPocztowy(postCode);
                            update.updateUzytkownik(edycja);
                            loadingFXML(event, SceneFXML.PANEL_ADMINA);
                            PanelAdminaController panelAdminaController = load.getController();
                            panelAdminaController.setStartValues(curentUser);
                            activeScene(event, false, false); }
                                else{
                            panelEdycjiUzytkownikaLabelError.setText("Niepoprawny kod uprawnień!");
                        }
                    }else{
                        panelEdycjiUzytkownikaLabelError.setText("Podany adres e-mailu jest nieprawidłowy!");
                    }
                }else{
                    panelEdycjiUzytkownikaLabelError.setText("Hasło jest za krótkie!");
                }
            }else{
                panelEdycjiUzytkownikaLabelError.setText("Nieprawidłowy kod pocztowy!");
            }
        }else{
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

        imie_nazwisko_rola_tmp = curentUser.getImie() + " " + curentUser.getNazwisko()+ " - konto administratora";
        System.out.print(imie_nazwisko_rola_tmp);
        imie_nazwisko_rola.setText(imie_nazwisko_rola_tmp);

    }

    @Override
    public void SetStartValuesEdycjaUzytkownika(Uzytkownicy user){
        this.edycja = user;
        setUstawienia();
    }

    private void setUstawienia() {
        String imie = edycja.getImie();
        String uprawnienia = edycja.getUprawnienia()+"";
        String nazwisko = edycja.getNazwisko();
        String[] kod = edycja.getKodPocztowy().split("-");
        System.out.println(kod[0]);
        email.setText(edycja.getMail());
        this.imie.setText(imie);
        this.nazwisko.setText(nazwisko);
        this.uprawnienia.setText(uprawnienia);
        miejscowosc.setText(edycja.getMiejscowosc());
        ulica.setText(edycja.getUlica());
        budynek.setText(edycja.getNumerBudynku());
        lokal.setText(edycja.getNumerLokalu());
        kod1.setText(kod[0]);
        kod2.setText(kod[1]);
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

}
