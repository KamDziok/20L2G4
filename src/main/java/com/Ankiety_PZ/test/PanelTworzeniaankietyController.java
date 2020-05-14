/**
 * Sample Skeleton for 'Dodawaniepytania.fxml' Controller Class
 */
package com.Ankiety_PZ.test;


import com.Ankiety_PZ.hibernate.*;
import com.Ankiety_PZ.query.AnkietyQuery;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

public class PanelTworzeniaankietyController extends BulidStage implements SetStartValues{
    private Uzytkownicy curentUser;
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;



    @FXML
    private javafx.scene.control.TextField punkty;

    @FXML
    private javafx.scene.control.TextField dataOD;

    @FXML
    private javafx.scene.control.TextField trescTytulu; // Value injected by FXMLLoader

    @FXML
    private javafx.scene.control.TextField dataDo;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;
    private Pytania pytanie;

    private String tytul;
    private int liczbaPunktow;
    private Date dataRozpoczecia;
    private Date dataZakonczenia;
    private Ankiety ankiety;
    @FXML private TableView pytanieTabele;
    @FXML private TableColumn treść;
    @FXML private TableColumn Rpytanie;
    @FXML private TableColumn przyciskEdycja;
    @FXML private TableColumn przyciskUsun;


    /**
     * Metoda obsługująca przyciśk wyloguj.
     *
     * @author HubertJakobsze
     * @param event zdarzenie, po którym funkcja ma się wywołać
     */

    @FXML
    void wylogujAction(ActionEvent event) {
        loadingFXML(event, SceneFXML.PANEL_LOGIN);
        PanelLoginController panelLoginController = load.getController();
        activeScene(event, false, false);
    }

    /**
     * Metoda obsługująca przyciśk anuluj.
     *
     * @author HubertJakobsze
     * @param event zdarzenie, po którym funkcja ma się wywołać
     */

    @FXML
    void anulujAction(ActionEvent event) {
        loadingFXML(event, SceneFXML.PANEL_ANKIETERA);
        PanelAnkieterController panelAnkieterController = load.getController();
        panelAnkieterController.setStartValues(curentUser);
        activeScene(event, false, false);
    }

    /**
     * Metoda obsługująca przyciśk dodaj pytanie.
     *
     * @author HubertJakobsze
     * @param event zdarzenie, po którym funkcja ma się wywołać
     */

    @FXML
    void dodajpytanieAction(ActionEvent event) {
        loadingFXML(event, SceneFXML.DODAJ_PYTANIE);
        DodawaniepytaniaController dodawaniepytaniaController  = load.getController();
        dodawaniepytaniaController.setStartValuesAnkiety(ankiety);
        dodawaniepytaniaController.setStartValues(curentUser);
        activeScene(event, false, false);
    }

    @Override
    public void setStartValues(Uzytkownicy user) {
        this.curentUser = user;
        setPytanie();
    }

    @Override
    public void setStartValuesAnkiety(Ankiety ankieta)
    {

        this.ankiety = ankieta;
        trescTytulu.setText(ankiety.getTytul());
        punkty.setText(String.valueOf(ankiety.getLiczbaPunktow()));
        dataOD.setText(String.valueOf((ankiety.getDataRozpoczecia())));
        dataDo.setText(String.valueOf((ankiety.getDataZakonczenia())));


    }

    @Override
    public void setStartValuesPytanie(Pytania pytania) {
        this.pytanie = pytania;
        setPytanieB();

    }

    @Override
    public void setStartValuesNagroda(Nagrody nagroda) {

    }


    @Override
    public void setStartValuesIerator(Iterator iterator) {

    }

    @FXML
    void ZapiszAction(ActionEvent event) {
        DateFormat dateFrm = new SimpleDateFormat("yyyy-MM-dd");
        tytul = trescTytulu.getText();
        liczbaPunktow = Integer.parseInt(punkty.getText());
        try {
            dataRozpoczecia = dateFrm.parse(dataOD.getText());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        try {
            dataZakonczenia = dateFrm.parse(dataDo.getText());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        ankiety.setTytul(tytul);
        ankiety.setLiczbaPunktow(liczbaPunktow);
        ankiety.setDataRozpoczecia(dataRozpoczecia);
        ankiety.setDataZakonczenia(dataZakonczenia);
        ankiety.setLiczbaWypelnien(0);
        ankiety.setUzytkownicy(curentUser);
        AnkietyQuery query = new AnkietyQuery();
        query.addAnkietyWithPytaniaAndOdpowiedzi(ankiety);



    }

    private void setPytanie() {
        AnkietyQuery query = new AnkietyQuery();
        ObservableList<PytanieTabelka> dane = FXCollections.observableArrayList();
        ankiety = query.selectAnkietaWithPytaniaAndOdpowiedziByAnkiety(ankiety);
        ankiety.getPytanias().forEach(pytanie -> {
            Pytania pytania2 = (Pytania) pytanie;
            dane.add(new PytanieTabelka(pytania2)); });
        treść.setCellValueFactory(new PropertyValueFactory("treść"));
        Rpytanie.setCellValueFactory(new PropertyValueFactory("Rpytanie"));
        przyciskEdycja.setCellValueFactory(new PropertyValueFactory("buttonEdycja"));
        przyciskUsun.setCellValueFactory(new PropertyValueFactory("buttonUsun"));
        pytanieTabele.itemsProperty().setValue(dane);
    }
    private void setPytanieB() {
        AnkietyQuery query = new AnkietyQuery();
        ObservableList<PytanieTabelka> dane = FXCollections.observableArrayList();
        //ankiety = query.selectAnkietaWithPytaniaAndOdpowiedziByAnkiety(ankiety);
        ankiety.getPytanias().forEach(pytanie -> {
            Pytania pytania2 = (Pytania) pytanie;
            dane.add(new PytanieTabelka(pytania2)); });
        treść.setCellValueFactory(new PropertyValueFactory("treść"));
        Rpytanie.setCellValueFactory(new PropertyValueFactory("Rpytanie"));
        przyciskEdycja.setCellValueFactory(new PropertyValueFactory("buttonEdycja"));
        przyciskUsun.setCellValueFactory(new PropertyValueFactory("buttonUsun"));
        pytanieTabele.itemsProperty().setValue(dane);
    }
}
