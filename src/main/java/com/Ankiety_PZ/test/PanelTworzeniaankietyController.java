/**
 * Sample Skeleton for 'Dodawaniepytania.fxml' Controller Class
 */
package com.Ankiety_PZ.test;


import com.Ankiety_PZ.hibernate.Ankiety;
import com.Ankiety_PZ.hibernate.Nagrody;
import com.Ankiety_PZ.hibernate.Pytania;
import com.Ankiety_PZ.hibernate.Uzytkownicy;
import com.Ankiety_PZ.query.AnkietyQuery;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.ResourceBundle;

public class PanelTworzeniaankietyController extends BulidStage implements SetStartValues, Initializable {

    ObservableList listDD = FXCollections.observableArrayList();
    ObservableList listMM = FXCollections.observableArrayList();
    ObservableList listRRRR = FXCollections.observableArrayList();

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
    @FXML
    private ChoiceBox<String> dataODDD;

    @FXML
    private ChoiceBox<String> dataODMM;

    @FXML
    private ChoiceBox<String> dataODRRRR;

    @FXML
    private ChoiceBox<String> dataDODD;

    @FXML
    private ChoiceBox<String> dataDOMM;

    @FXML
    private ChoiceBox<String> dataDORRRR;


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
        String dat = "dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dat);
        String dataDD = simpleDateFormat.format((ankiety.getDataRozpoczecia()));
        String datMM = "MM";
        SimpleDateFormat simpleDateFormatMM = new SimpleDateFormat(datMM);
        String dataMM = simpleDateFormatMM.format((ankiety.getDataRozpoczecia()));
        String datRRRR = "yyyy";
        SimpleDateFormat simpleDateFormatRRRR = new SimpleDateFormat(datRRRR);
        String dataRRRR = simpleDateFormatRRRR.format((ankiety.getDataRozpoczecia()));
//        dataOD.setText(data);
        dataODDD.setValue(dataDD);
        dataODMM.setValue(dataMM);
        dataODRRRR.setValue(dataRRRR);
      //  dataDo.setText(String.valueOf((ankiety.getDataZakonczenia())));

        setPytanieB();

    }
    public void setStartValuesEdytujAnkiety (Ankiety ankieta){
        AnkietyQuery query1 = new AnkietyQuery();
        this.ankiety = query1.selectAnkietaWithPytaniaAndOdpowiedziByAnkiety(ankieta);

        trescTytulu.setText(ankiety.getTytul());
        punkty.setText(String.valueOf(ankiety.getLiczbaPunktow()));
        String dat = "dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dat);
        String dataDD = simpleDateFormat.format((ankiety.getDataRozpoczecia()));
        dataODDD.setValue(dataDD);
        String datMM = "MM";
        SimpleDateFormat simpleDateFormatMM = new SimpleDateFormat(datMM);
        String dataMM = simpleDateFormatMM.format((ankiety.getDataRozpoczecia()));
        String datRRRR = "yyyy";
        SimpleDateFormat simpleDateFormatRRRR = new SimpleDateFormat(datRRRR);
        String dataRRRR = simpleDateFormatRRRR.format((ankiety.getDataRozpoczecia()));
        dataODMM.setValue(dataMM);
        dataODRRRR.setValue(dataRRRR);

        String datazakDD = "dd";
        SimpleDateFormat simpleDateFormatdatazakDD = new SimpleDateFormat(datazakDD);
        String datazDD = simpleDateFormatdatazakDD.format((ankiety.getDataZakonczenia()));
        dataDODD.setValue(datazDD);
        String datazakMM = "MM";
        SimpleDateFormat simpleDateFormatdatazakMM = new SimpleDateFormat(datazakMM);
        String datazMM = simpleDateFormatdatazakMM.format((ankiety.getDataZakonczenia()));
        String datzRRRR = "yyyy";
        SimpleDateFormat simpleDateFormatdatazakRRRR= new SimpleDateFormat(datzRRRR);
        String datazakRRRR = simpleDateFormatdatazakRRRR.format((ankiety.getDataZakonczenia()));
        dataDOMM.setValue(datazMM);
        dataDORRRR.setValue(datazakRRRR);
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
        String dataoddd = dataODDD.getValue();
        String dataodmm = dataODMM.getValue();
        String dataodrrrr = dataODRRRR.getValue();
        String datadodd = dataDODD.getValue();
        String datadomm = dataDOMM.getValue();
        String datadorrrr = dataDORRRR.getValue();
        String dataod = dataodrrrr+"-"+dataodmm+"-"+dataoddd;
        String datado = datadorrrr+"-"+datadomm+"-"+datadodd;
        tytul = trescTytulu.getText();
        liczbaPunktow = Integer.parseInt(punkty.getText());
        try {
            dataRozpoczecia = dateFrm.parse(dataod);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        try {
            dataZakonczenia = dateFrm.parse(datado);
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

    private void loadData(){

        listDD.removeAll(listDD);
        listDD.addAll("01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31");
        listMM.removeAll(listMM);
        listMM.addAll("01","02","03","04","05","06","07","08","09","10","11","12");
        listRRRR.removeAll(listRRRR);
        listRRRR.addAll("2020","2021","2022","2023","2024","2025","2026","2027","2028","2029","2030");
        dataODDD.getItems().addAll(listDD);
        dataODMM.getItems().addAll(listMM);
        dataODRRRR.getItems().addAll(listRRRR);
        dataDODD.getItems().addAll(listDD);
        dataDOMM.getItems().addAll(listMM);
        dataDORRRR.getItems().addAll(listRRRR);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadData();
    }
public void Usun(Pytania pytanie)
{


}


}
