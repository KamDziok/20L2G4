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
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

public class PanelAdminaController extends BulidStage implements SetStartValues {

    private Uzytkownicy curentUser;
    public ObservableList<UzytkownicyTabelka> dane;
    public ObservableList<UzytkownicyZablokowaniTabelka> dane2;
    private String imie_nazwisko_rola_tmp;

    @FXML
    private Label imie_nazwisko_rola;
    @FXML
    private Label imie_nazwisko_rola2;
    @FXML
    private Label imie_nazwisko_rola3;
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;
    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;
    @FXML
    private Button wyloguj;
    @FXML
    TableView tableUzytkownicy;
    @FXML
    TableView  tableUzytkownicyZablokowani;;
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

    @FXML private TextField email;
    @FXML private TextField haslo;
    @FXML private TextField nowehaslo;
    @FXML private TextField hasloznowu;
    @FXML private TextField imie;

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

    @FXML private TextField nazwisko;
    @FXML private TextField miejscowosc;
    @FXML private TextField ulica;
    @FXML private TextField budynek;
    @FXML private TextField lokal;
    @FXML private TextField kod1;
    @FXML private TextField kod2;

    @FXML
    void wyloguj(ActionEvent event) {
        loadingFXML(event, SceneFXML.PANEL_LOGIN);
        activeScene(event, false, false);
    }

    @FXML
    void panelAdminaButtonZmienUstawienia(ActionEvent event) {
        curentUser.setMail(email.getText());
        if(haslo.getText().equals(curentUser.getHaslo()) && nowehaslo.getText().equals(hasloznowu.getText()))
            curentUser.setHaslo(nowehaslo.getText());
        curentUser.setImie(imie.getText());
        curentUser.setNazwisko(nazwisko.getText());
        curentUser.setMiejscowosc(miejscowosc.getText());
        curentUser.setUlica(ulica.getText());
        curentUser.setNumerBudynku(budynek.getText());
        curentUser.setNumerLokalu(lokal.getText());
        curentUser.setKodPocztowy(kod1.getText() + "-" + kod2.getText());
        UzytkownicyQuery query = new UzytkownicyQuery();
        query.updateUzytkownicy(curentUser);
    }

    void setUzytkownicy() {
        UzytkownicyQuery query = new UzytkownicyQuery();
        List<Uzytkownicy> uzytkownicy = query.selectBy(false);
        dane = FXCollections.observableArrayList();
        for (Uzytkownicy uzytkownik:uzytkownicy
        ) {
            dane.add(new UzytkownicyTabelka (uzytkownik, curentUser, this));
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
        List<Uzytkownicy> uzytkownicy = query.selectBy(true);
        dane2 = FXCollections.observableArrayList();
        for (Uzytkownicy uzytkownik:uzytkownicy
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

        imie_nazwisko_rola_tmp = curentUser.getImie() + " " + curentUser.getNazwisko()+ " - konto administratora";
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

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert wyloguj != null : "fx:id=\"wyloguj\" was not injected: check your FXML file 'PanelAdmina.fxml'.";
        assert imie_nazwisko_rola != null : "fx:id=\"imie_nazwisko_rola\" was not injected: check your FXML file 'PanelAdmina.fxml'.";
        assert imie_nazwisko_rola2 != null : "fx:id=\"imie_nazwisko_rola2\" was not injected: check your FXML file 'PanelAdmina.fxml'.";
        assert imie_nazwisko_rola3 != null : "fx:id=\"imie_nazwisko_rola3\" was not injected: check your FXML file 'PanelAdmina.fxml'.";

    }

}
