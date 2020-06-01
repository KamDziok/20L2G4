/**
 * Sample Skeleton for 'DodawaniePytania.fxml' Controller Class
 */
package com.Ankiety_PZ.panele;


import com.Ankiety_PZ.hibernate.*;
import com.Ankiety_PZ.query.AnkietyQuery;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
/**
 * Klasa odpowiedzialna za edycje wybranej ankiety
 */

public class PanelTworzeniaAnkietyController extends BulidStage implements SetStartValues, Initializable {

    ObservableList listDD = FXCollections.observableArrayList();
    ObservableList listMM = FXCollections.observableArrayList();
    ObservableList listRRRR = FXCollections.observableArrayList();
    private String dataoddd;
    private String dataodmm;
    private String dataodrrrr;
    private String datadodd;
    private String datadomm;
    private String datadorrrr;
    private int liczpytania = 0;
    private int liczpytaniaB = 0;
    private int liczbapytan = 0;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;
    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;
    private String tytul;
    private int liczbaPunktow;
    private String liczbaPunktowS;
    private Uzytkownicy curetUser;
    private Date dataRozpoczecia;
    private Date dataZakonczenia;
    private Ankiety ankiety;
    public Boolean edycja2 = true;
    private String dataod;
    private String datado;
    public List<Pytania> listaPytaU;
    private Set<Pytania> pytaniaDoUsuniecia;
    public List<Odpowiedzi> listaOdpU;
    @FXML
    private TableView pytanieTabele;
    @FXML
    private TableColumn tresc;
    @FXML
    private TableColumn Rpytanie;
    @FXML
    private TableColumn przyciskEdycja;
    @FXML
    private TableColumn przyciskUsun;
    @FXML
    private Label panelTworzeniaAnkietyLabelError;

    @FXML
    private TextField trescTytulu;

    @FXML
    private TextField punkty;
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
    private Set<Pytania> ListaPytan;
    private Set<Pytania> pytdoUsuniecia;

    public void SetEdycja(Boolean wyb) {
        edycja2 = wyb;
    }

    /**
     * Metoda obsługująca przyciśk wyloguj.
     *
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
     * @param event zdarzenie, po którym funkcja ma się wywołać
     */

    @FXML
    void anulujAction(ActionEvent event) {
        loadingFXML(event, SceneFXML.PANEL_ANKIETERA);
        PanelAnkieterController panelAnkieterController = load.getController();
        panelAnkieterController.setStartValues(curetUser);
        activeScene(event, false, false);
    }

    /**
     * Metoda obsługująca przyciśk dodaj pytanie.
     *
     * @param event zdarzenie, po którym funkcja ma się wywołać
     */

    @FXML
    void dodajpytanieAction(ActionEvent event) {
        loadingFXML(event, SceneFXML.DODAJ_PYTANIE);
        DodawaniePytaniaController dodawaniepytaniaController = load.getController();
        ankiety.setTytul(trescTytulu.getText());
        DateFormat dateFrm = new SimpleDateFormat("yyyy-MM-dd");
        dataoddd = dataODDD.getValue();
        dataodmm = dataODMM.getValue();
        dataodrrrr = dataODRRRR.getValue();
        datadodd = dataDODD.getValue();
        datadomm = dataDOMM.getValue();
        datadorrrr = dataDORRRR.getValue();
        dataod = dataodrrrr + "-" + dataodmm + "-" + dataoddd;
        datado = datadorrrr + "-" + datadomm + "-" + datadodd;
        tytul = trescTytulu.getText();
        liczbaPunktowS = punkty.getText();


        try {
            dataRozpoczecia = dateFrm.parse(dataod);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        ankiety.setDataRozpoczecia(dataRozpoczecia);
        try {
            dataZakonczenia = dateFrm.parse(datado);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        ankiety.setDataZakonczenia(dataZakonczenia);
        try {
            ankiety.setLiczbaPunktow(Integer.parseInt(punkty.getText()));
        } catch (Exception e) {
            ankiety.setLiczbaPunktow(0);
        } finally {
            dodawaniepytaniaController.Inicjajca();
            dodawaniepytaniaController.setLisaPytanPrzekazana(ListaPytan);
            dodawaniepytaniaController.DaneUsniecia(listaPytaU, listaOdpU);
            dodawaniepytaniaController.setStartValuesAnkiety(ankiety);
            dodawaniepytaniaController.setStartValues(curetUser);
            dodawaniepytaniaController.SetEdycja(edycja2);

            activeScene(event, false, false);
        }


    }

    public void setListaPytan(Set<Pytania> listaPytan) {
        this.ListaPytan = listaPytan;
    }

    @Override
    public void setStartValues(Uzytkownicy user) {
        this.curetUser = user;

    }


    @Override
    public void setStartValuesAnkiety(Ankiety ankieta) {
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
        dataODDD.setValue(dataDD);
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
        SimpleDateFormat simpleDateFormatdatazakRRRR = new SimpleDateFormat(datzRRRR);
        String datazakRRRR = simpleDateFormatdatazakRRRR.format((ankiety.getDataZakonczenia()));
        dataDOMM.setValue(datazMM);
        dataDORRRR.setValue(datazakRRRR);

        setPytanieB(ListaPytan);
        liczbapytan = liczpytania + liczpytaniaB;

    }

    public void setStartValuesEdytujAnkiety(Ankiety ankieta) {
        AnkietyQuery query1 = new AnkietyQuery();
        this.ankiety = query1.selectAnkietaWithPytaniaAndOdpowiedziByAnkiety(ankieta);
        this.ankiety.setUzytkownicy(curetUser);
        ListaPytan = new HashSet<Pytania>(ankieta.getPytanias());
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
        SimpleDateFormat simpleDateFormatdatazakRRRR = new SimpleDateFormat(datzRRRR);
        String datazakRRRR = simpleDateFormatdatazakRRRR.format((ankiety.getDataZakonczenia()));
        dataDOMM.setValue(datazMM);
        dataDORRRR.setValue(datazakRRRR);
        listaPytaU = new ArrayList<>();
        listaOdpU = new ArrayList<>();
        setPytanieB(ListaPytan);
        liczbapytan = liczpytania + liczpytaniaB;
    }


    public void SetStart() {

        listaPytaU = new ArrayList<>();
        listaOdpU = new ArrayList<>();
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


    /**
     * Metoda sprawdzenie czy obowiązkowe pola nie są puste.
     *
     * @return true jeśli wszystkie pola obowiązkowe są uzupełnione, w przeciwnym wypadku false
     */
    private boolean compulsoryFildNotEmpty() {
        return (!tytul.isEmpty() && !liczbaPunktowS.isEmpty());
    }

    /**
     * Metoda sprawdzenie czy obowiązkowe pola nie są null.
     *
     * @return true jeśli wszystkie pola obowiązkowe są uzupełnione, w przeciwnym wypadku false
     */
    private boolean compulsoryFildNotNull() {
        return (tytul != null && liczbaPunktowS != null);
    }


    /**
     * Metoda sprawdzenie czy data rozpoczęcia nie jest starsza od daty zakończenia.
     *
     * @return 1 jeśli wszystkie pola obowiązkowe są uzupełnione, w przeciwnym wypadku -1
     */
    private int czyNieStarszaData() {
        return dataZakonczenia.compareTo(dataRozpoczecia);

    }

    /**
     * Metoda sprawdzenie czy wprowadzone daty nie są nieprawidłowe jak np. 31 luty.
     *
     * @return true jeśli wszystkie daty są poprawne, w przeciwnym wypadku false
     */

    private boolean czyNieistniejacaData() {
        if ((dataoddd.equals("31") && dataodmm.equals("02")) ||
                (dataoddd.equals("30") && dataodmm.equals("02")) ||
                (dataoddd.equals("29") && dataodmm.equals("02") && dataodrrrr.equals("2021")) ||
                (dataoddd.equals("29") && dataodmm.equals("02") && dataodrrrr.equals("2022")) ||
                (dataoddd.equals("29") && dataodmm.equals("02") && dataodrrrr.equals("2023")) ||
                (dataoddd.equals("29") && dataodmm.equals("02") && dataodrrrr.equals("2025")) ||
                (dataoddd.equals("29") && dataodmm.equals("02") && dataodrrrr.equals("2026")) ||
                (dataoddd.equals("29") && dataodmm.equals("02") && dataodrrrr.equals("2027")) ||
                (dataoddd.equals("29") && dataodmm.equals("02") && dataodrrrr.equals("2029")) ||
                (dataoddd.equals("29") && dataodmm.equals("02") && dataodrrrr.equals("2030")) ||
                (dataoddd.equals("31") && dataodmm.equals("04")) ||
                (dataoddd.equals("31") && dataodmm.equals("06")) ||
                (dataoddd.equals("31") && dataodmm.equals("09")) ||
                (dataoddd.equals("31") && dataodmm.equals("11")) ||

                (datadodd.equals("31") && datadomm.equals("02")) ||
                (datadodd.equals("30") && datadomm.equals("02")) ||
                (datadodd.equals("29") && datadomm.equals("02") && datadorrrr.equals("2021")) ||
                (datadodd.equals("29") && datadomm.equals("02") && datadorrrr.equals("2022")) ||
                (datadodd.equals("29") && datadomm.equals("02") && datadorrrr.equals("2023")) ||
                (datadodd.equals("29") && datadomm.equals("02") && datadorrrr.equals("2025")) ||
                (datadodd.equals("29") && datadomm.equals("02") && datadorrrr.equals("2026")) ||
                (datadodd.equals("29") && datadomm.equals("02") && datadorrrr.equals("2027")) ||
                (datadodd.equals("29") && datadomm.equals("02") && datadorrrr.equals("2029")) ||
                (datadodd.equals("29") && datadomm.equals("02") && datadorrrr.equals("2030")) ||
                (datadodd.equals("31") && datadomm.equals("04")) ||
                (datadodd.equals("31") && datadomm.equals("06")) ||
                (datadodd.equals("31") && datadomm.equals("09")) ||
                (datadodd.equals("31") && datadomm.equals("11"))

        ) {
            return false;
        } else return true;
    }

    /**
     * Metoda sprawdza czy punkty są liczbą oraz czy nie sią ujemne.
     *
     * @return true jeśli punkty są podane poprawnie, w przeciwnym razie false
     */
    private boolean punktyIsNumber() {
        try {

            liczbaPunktow = Integer.parseInt(liczbaPunktowS);
            if (liczbaPunktow >= 0) {
                return true;
            }

        } catch (IllegalArgumentException argumentException) {
            System.out.println(argumentException.getMessage());
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
        return false;
    }

    /**
     * Metoda sprawdza czy zostały wprowadzone przynajmniej 2 pytania.
     *
     * @return true jeśli są przynajmniej 2 pytania, w przeciwnym razie false
     */
    private boolean min2pytania() {
        return liczbapytan >= 2;
    }

    /**
     * Metoda służąca inicjalizacji listy pytań.
     */

    public void StartListaPytan() {
        ListaPytan = new HashSet<>();


    }

    public Set<Pytania> getListaPytan() {
        return ListaPytan;
    }

    /**
     * Metoda obsługująca przycisk zapisz, czyli zapis ankiety do bazy danych
     */

    @FXML
    void ZapiszAction(ActionEvent event) {
        DateFormat dateFrm = new SimpleDateFormat("yyyy-MM-dd");
        dataoddd = dataODDD.getValue();
        dataodmm = dataODMM.getValue();
        dataodrrrr = dataODRRRR.getValue();
        datadodd = dataDODD.getValue();
        datadomm = dataDOMM.getValue();
        datadorrrr = dataDORRRR.getValue();
        dataod = dataodrrrr + "-" + dataodmm + "-" + dataoddd;
        datado = datadorrrr + "-" + datadomm + "-" + datadodd;
        tytul = trescTytulu.getText();
        liczbaPunktowS = punkty.getText();


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
        if (compulsoryFildNotNull()) {
            if (compulsoryFildNotEmpty()) {
                if (czyNieStarszaData() == 1) {
                    if (czyNieistniejacaData()) {
                        if (punktyIsNumber()) {
                            if (min2pytania()) {
                                ankiety.setTytul(tytul);
                                ankiety.setLiczbaPunktow(liczbaPunktow);
                                ankiety.setDataRozpoczecia(dataRozpoczecia);
                                ankiety.setDataZakonczenia(dataZakonczenia);
                                AnkietyQuery query = new AnkietyQuery();

                                ankiety.setPytanias(ListaPytan);
                                if (edycja2) {
                                    listaPytaU.addAll(pytdoUsuniecia);
                                    query.deletePytaniaAndOdpowiedziInAnkiety(listaPytaU, listaOdpU);
                                    query.updateAnkietyWithPytaniaAndOdpowiedzi(ankiety);
                                } else {

                                    query.addAnkietyWithPytaniaAndOdpowiedzi(ankiety);
                                }
                                loadingFXML(event, SceneFXML.PANEL_ANKIETERA);
                                PanelAnkieterController panelAnkieterController = load.getController();
                                panelAnkieterController.setStartValues(curetUser);
                                activeScene(event, false, false);

                            } else {
                                panelTworzeniaAnkietyLabelError.setText("Ankieta musi zawierać przynajmniej 2 pytania!");
                            }
                        } else {
                            panelTworzeniaAnkietyLabelError.setText("Punktacja za ankietę jest nieprawidłowa!");
                        }
                    } else {
                        panelTworzeniaAnkietyLabelError.setText("Podana data nie istnieje!");
                    }
                } else {
                    panelTworzeniaAnkietyLabelError.setText("Data rozpoczęcia jest późniejsza niż data zakończenia!");
                }
            } else {
                panelTworzeniaAnkietyLabelError.setText("Wymagane pola są puste!");
            }
        } else {
            panelTworzeniaAnkietyLabelError.setText("Wymagane pola są puste!");
        }


    }



    public void setPytdoUsuniecia() {
        pytdoUsuniecia = new HashSet<Pytania>();

    }

    public void setPytdoUsunieciaUs(Set<Pytania> pyt) {
        pytdoUsuniecia = pyt;

    }

    /**
     * Metoda wypisująca liste pytań w panelu tworzenia ankiet
     */

    public void setPytanieB(Set lista) {
        ListaPytan = lista;
        ObservableList<PytanieTabelka> dane = FXCollections.observableArrayList();
        ListaPytan.forEach(pytanie -> {
            Pytania pytania2 = (Pytania) pytanie;
            dane.add(new PytanieTabelka(ankiety, pytanie, curetUser, listaOdpU, pytdoUsuniecia, listaPytaU, this));
            liczpytaniaB++;
        });
        tresc.setCellValueFactory(new PropertyValueFactory("tresc"));
        Rpytanie.setCellValueFactory(new PropertyValueFactory("Rpytanie"));
        przyciskEdycja.setCellValueFactory(new PropertyValueFactory("buttonEdycja"));
        przyciskUsun.setCellValueFactory(new PropertyValueFactory("buttonUsun"));
        pytanieTabele.itemsProperty().setValue(dane);
    }
    /**
     * Metoda służąca do ładowania choice box miesiecy, dni i lat.
     */

    private void loadData() {

        listDD.removeAll(listDD);
        listDD.addAll("01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31");
        listMM.removeAll(listMM);
        listMM.addAll("01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12");
        listRRRR.removeAll(listRRRR);
        listRRRR.addAll("2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030");
        dataODDD.getItems().addAll(listDD);
        dataODMM.getItems().addAll(listMM);
        dataODRRRR.getItems().addAll(listRRRR);
        dataDODD.getItems().addAll(listDD);
        dataDOMM.getItems().addAll(listMM);
        dataDORRRR.getItems().addAll(listRRRR);
    }

    /**
     * Metoda służąca zapisania list pytań i odpowiedzi do usunięcia.
     */

    public void DaneUsniecia(List<Pytania> pyt, List<Odpowiedzi> odp) {
        this.listaPytaU = pyt;
        this.listaOdpU = odp;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadData();
    }

}
