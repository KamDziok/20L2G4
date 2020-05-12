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
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

public class PanelAnkieterController extends BulidStage implements SetStartValues{
    private Uzytkownicy curentUser2;
    public int id_ankiety;
    @FXML
    private Label imie_nazwisko_rola2;

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
    @FXML private TableView tableAnkiety;
    @FXML private TableColumn tytul;
    @FXML private TableColumn wygasa;
    @FXML private TableColumn pkt;
    @FXML private TableColumn przyciskEdycja;
    @FXML private TableColumn przyciskUsun;


    public PanelAnkieterController() {
    }



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

    /**
     * Metoda obsługująca przyciśk Dodaj
     *
     * @author HubertJakobsze
     * @param event zdarzenie, po którym funkcja ma się wywołać
     */

    @FXML
    void panelAnkietButtonDodajAction(ActionEvent event) {
        Ankiety ankieta = new Ankiety();
        loadingFXML(event, SceneFXML.TWORZENIE_ANKIETY);
        PanelTworzeniaankietyController panelTworzeniaankietyController = load.getController();
        panelTworzeniaankietyController.setStartValuesAnkiety(ankieta);
        panelTworzeniaankietyController.setStartValues(curentUser2);
        activeScene(event, false, false);



    }

    @FXML
    void panelAnkieteraButtonZmienUstawienia(ActionEvent event) {
        curentUser2.setMail(email.getText());
        if(haslo.getText().equals(curentUser2.getHaslo()) && nowehaslo.getText().equals(hasloznowu.getText()))
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
     * @author HubertJakobsze
     * @param event zdarzenie, po którym funkcja ma się wywołać
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
     * @author HubertJakobsze
     * @param event zdarzenie, po którym funkcja ma się wywołać
     */
    @FXML
    void wyloguj2Action(ActionEvent event) {
        loadingFXML(event, SceneFXML.PANEL_LOGIN);
        PanelLoginController panelLoginController = load.getController();
        activeScene(event, false, false);
    }


    private void setAnkiety() {
        AnkietyQuery query = new AnkietyQuery();
        List<Ankiety> ankiety = query.selectAll();
        ObservableList<AnikieterTabelka> dane = FXCollections.observableArrayList();
        for (Ankiety ankieta2:ankiety
        ) {
                dane.add(new AnikieterTabelka(ankieta2));
        }
        tableAnkiety.itemsProperty().setValue(dane);
        tytul.setCellValueFactory(new PropertyValueFactory("tytul"));
        wygasa.setCellValueFactory(new PropertyValueFactory("dataZakonczenia"));
        pkt.setCellValueFactory(new PropertyValueFactory("liczbaPunktow"));
        przyciskEdycja.setCellValueFactory(new PropertyValueFactory("buttonEdycja"));
        przyciskUsun.setCellValueFactory(new PropertyValueFactory("buttonUsun"));

    }

    @Override
    public void setStartValues(Uzytkownicy user) {
        this.curentUser2 = user;
///        imie_nazwisko_rola_tmp = curentUser.getImie() + " " + curentUser.getNazwisko()+ " - Ankieter";
        setAnkiety();
    }

    @Override
    public void setStartValuesAnkiety(Ankiety ankiety) {


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


    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert wyloguj2 != null : "fx:id=\"wyloguj2\" was not injected: check your FXML file 'PanelAnkieter.fxml'.";
        assert panelAnkietButtonDodaj != null : "fx:id=\"panelAnkietButtonDodaj\" was not injected: check your FXML file 'PanelAnkieter.fxml'.";
        assert wyloguj1 != null : "fx:id=\"wyloguj1\" was not injected: check your FXML file 'PanelAnkieter.fxml'.";
        assert imie_nazwisko_rola2 != null : "fx:id=\"imie_nazwisko_rola2\" was not injected: check your FXML file 'PanelAnkieter.fxml'.";


    }

//    @Override
//    public void setStartValues(Uzytkownicy user) {
//        this.curentUser = user;
//        setUstawienia();
//    }

}
