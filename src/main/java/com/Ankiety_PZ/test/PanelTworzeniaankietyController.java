/**
 * Sample Skeleton for 'Dodawaniepytania.fxml' Controller Class
 */
package com.Ankiety_PZ.test;


import com.Ankiety_PZ.hibernate.*;
import com.Ankiety_PZ.query.AnkietyQuery;
import com.Ankiety_PZ.query.PytaniaQuery;
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
import java.util.*;

public class PanelTworzeniaankietyController extends BulidStage implements SetStartValues{
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
    private String tytul;
    private int liczbaPunktow;
    private Date dataRozpoczecia;
    private Date dataZakonczenia;
    private Ankiety ankiety;
    public Boolean edycja;
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
        panelAnkieterController.setStartValues(ankiety.getUzytkownicy());
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
        dodawaniepytaniaController.SetEdycja(edycja);
        activeScene(event, false, false);
    }


    @Override
    public void setStartValuesAnkiety(Ankiety ankieta)
    {


        this.ankiety = ankieta;
        System.out.println("ankiety setStartValuesAnkiety");
        System.out.println(ankiety);
        trescTytulu.setText(ankiety.getTytul());
        punkty.setText(String.valueOf(ankiety.getLiczbaPunktow()));
        String dat = "MM-dd-yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dat);
        String data = simpleDateFormat.format((ankiety.getDataRozpoczecia()));
        dataOD.setText(data);
        dataDo.setText(String.valueOf((ankiety.getDataZakonczenia())));
        System.out.println("co sie tu dzieje");
        setPytanieB();

    }
    public void setStartValuesEdytujAnkiety (Ankiety ankieta){
        AnkietyQuery query1 = new AnkietyQuery();
        this.ankiety = query1.selectAnkietaWithPytaniaAndOdpowiedziByAnkiety(ankieta);

        trescTytulu.setText(ankiety.getTytul());
        punkty.setText(String.valueOf(ankiety.getLiczbaPunktow()));
        dataOD.setText(String.valueOf((ankiety.getDataRozpoczecia())));
        dataDo.setText(String.valueOf((ankiety.getDataZakonczenia())));
        setPytanieB();

    }
    @Override
    public void setStartValues(Uzytkownicy user) {
        setPytanie();
    }


    @Override
    public void setStartValuesPytanie(Pytania pytania) { }

    @Override
    public void setStartValuesNagroda(Nagrody nagroda) { }


    @Override
    public void setStartValuesIerator(Iterator iterator) { }

    public void SetEdycja(Boolean wyb)
    {
        edycja = wyb;
        System.out.println("edycjaedycjaedycjaedycjaedycjaedycjaedycjaedycjaedycjaedycjaedycjaedycjaedycjaedycjaedycjaedycjaedycjaedycjaedycjaedycjaedycjaedycjaedycjaedycjaedycjaedycjaedycjaedycjaedycjaedycjaedycjaedycjaedycjaedycjaedycjaedycjaedycjaedycjaedycjaedycjaedycjaedycjaedycjaedycjaedycjaedycjaedycjaedycjaedycjaedycjaedycjaedycjaedycjaedycjaedycjaedycjaedycjaedycjaedycjaedycjaedycjaedycjaedycjaedycjaedycjaedycjaedycjaedycjaedycjaedycjaedycjaedycjaedycjaedycjaedycjaedycjaedycjaedycjaedycjaedycjaedycjaedycjaedycjaedycjaedycjaedycjaedycjaedycjaedycjaedycjaedycjaedycjaedycjaedycjaedycjaedycjaedycjaedycjaedycjaedycjaedycjaedycjaedycjaedycjaedycjaedycjaedycjaedycjaedycjaedycjaedycjaedycjaedycjaedycjaedycjaedycjaedycjaedycjaedycjaedycjaedycjaedycjaedycjaedycjaedycjaedycjaedycjaedycjaedycjaedycjaedycjaedycjaedycjaedycjaedycjaedycjaedycjaedycjaedycjaedycjaedycja");
        System.out.println(edycja);
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
        System.out.println(ankiety);
        //System.out.println(ankiety.getUzytkownicy());
        ankiety.setTytul(tytul);
        ankiety.setLiczbaPunktow(liczbaPunktow);
        ankiety.setDataRozpoczecia(dataRozpoczecia);
        ankiety.setDataZakonczenia(dataZakonczenia);
        //ankiety.setLiczbaWypelnien(liczbawypełnein);
       System.out.println(ankiety.getPytanias());
        AnkietyQuery query = new AnkietyQuery();
        if(edycja){query.updateAnkietyWithPytaniaAndOdpowiedzi(ankiety);}
        else{query.addAnkietyWithPytaniaAndOdpowiedzi(ankiety);}





    }

    private void setPytanie() {
        AnkietyQuery query = new AnkietyQuery();
        ObservableList<PytanieTabelka> dane = FXCollections.observableArrayList();
        ankiety = query.selectAnkietaWithPytaniaAndOdpowiedziByAnkiety(ankiety);
        ankiety.getPytanias().forEach(pytanie -> {
            Pytania pytania2 = (Pytania) pytanie;
            dane.add(new PytanieTabelka(ankiety, pytania2)); });
        treść.setCellValueFactory(new PropertyValueFactory("treść"));
        Rpytanie.setCellValueFactory(new PropertyValueFactory("Rpytanie"));
        przyciskEdycja.setCellValueFactory(new PropertyValueFactory("buttonEdycja"));
        przyciskUsun.setCellValueFactory(new PropertyValueFactory("buttonUsun" ));
        pytanieTabele.itemsProperty().setValue(dane);
    }
    private void setPytanieB() {
        ObservableList<PytanieTabelka> dane = FXCollections.observableArrayList();
        ankiety.getPytanias().forEach(pytanie -> {
            Pytania pytania2 = (Pytania) pytanie;
            dane.add(new PytanieTabelka(ankiety, pytania2)); });
        treść.setCellValueFactory(new PropertyValueFactory("treść"));
        Rpytanie.setCellValueFactory(new PropertyValueFactory("Rpytanie"));
        przyciskEdycja.setCellValueFactory(new PropertyValueFactory("buttonEdycja"));
        przyciskUsun.setCellValueFactory(new PropertyValueFactory("buttonUsun"));
        pytanieTabele.itemsProperty().setValue(dane);
    }
public void Usun(Pytania pytanie)
{


}


}
