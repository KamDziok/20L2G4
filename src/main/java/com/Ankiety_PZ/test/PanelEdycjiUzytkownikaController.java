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


    @FXML
    private Button wyloguj;

    @FXML
    private Button panelEdycjiUzytkownikowButtonZapisz;

    @FXML
    private Button panelEdycjiUzytkownikowButtonAnuluj;

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

    @FXML
    void panelEdycjiUzytkownikowButtonZapisz(ActionEvent event) {
        edycja.setMail(email.getText());
        if(haslo.getText().equals("")){}
        else {
            edycja.setHaslo(haslo.getText());
        }
        edycja.setUprawnienia(Integer.parseInt(uprawnienia.getText()));
        edycja.setImie(imie.getText());
        edycja.setNazwisko(nazwisko.getText());
        edycja.setMiejscowosc(miejscowosc.getText());
        edycja.setUlica(ulica.getText());
        edycja.setNumerBudynku(budynek.getText());
        edycja.setNumerLokalu(lokal.getText());
        edycja.setKodPocztowy(kod1.getText() + "-" + kod2.getText());
        UzytkownicyQuery query = new UzytkownicyQuery();
        query.updateUzytkownik(edycja);
        loadingFXML(event, SceneFXML.PANEL_ADMINA);
        PanelAdminaController panelAdminaController = load.getController();
        panelAdminaController.setStartValues(curentUser);
        activeScene(event, false, false);
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
