/**
 * Sample Skeleton for 'AnalizaAnkiet.fxml' Controller Class
 */

package com.Ankiety_PZ.test;

import com.Ankiety_PZ.hibernate.*;
import com.Ankiety_PZ.query.AnkietyQuery;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.*;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Set;

public class AnalizaAnkietController implements SetStartValues{

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

//    pola FXML

    @FXML private AnchorPane panel;
    @FXML private Label tytulAnkiety;

//    pola klasy

    private Uzytkownicy curentUser;
    private Ankiety ankieta;

//    metody klasy

    void setTable() {

    }

    void analizaPytaniaRadioCheck(Pytania pytanie, int y) {
        Set<Odpowiedzi> odpowiedzi = pytanie.getOdpowiedzis();
        XYChart.Series series1 = new XYChart.Series();
        series1.setName("odpowiedzi w okresie tym");
        for (Odpowiedzi odpowiedz:odpowiedzi
             ) {
            series1.getData().add(new XYChart.Data(odpowiedz.getOdpowiedz(), odpowiedz.getCount()));
        }
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        BarChart<String, Number> wykres = new BarChart(xAxis, yAxis);
        xAxis.setLabel("odpowiedzi");
        yAxis.setLabel("ilosc odpowiedzi");

        BorderPane border = new BorderPane();
        border.prefWidth(1200);
        border.setTop(new Label(pytanie.getTresc()));
//        border.setLeft(new TableView<>());
//        border.getLeft().minWidth(400);
        border.setCenter(wykres);
        border.getCenter().minWidth(800);
        border.setLayoutY(y);
        wykres.getData().add(series1);
        panel.getChildren().add(border);
    }

    void analizaPytaniaOtwarte(Pytania pytanie, int y) {
        TableView<PytaniaUzytkownicy> tabelka = new TableView();
        TableColumn odpowiedzi = new TableColumn();
        ObservableList<PytaniaUzytkownicy> dane = FXCollections.observableArrayList();
        for (PytaniaUzytkownicy odpowiedz:pytanie.getPytaniaUzytkownicy()
        ) {
            dane.add(new PytaniaUzytkownicy(pytanie, curentUser, odpowiedz.getOdpowiedz()));
        }
        tabelka.itemsProperty().setValue(dane);
        odpowiedzi.setCellValueFactory(new PropertyValueFactory("odpowiedz"));

        BorderPane border = new BorderPane();
        border.prefWidth(1200);
        border.setTop(new Label(pytanie.getTresc()));
        border.setLeft(tabelka);
        border.getLeft().minWidth(400);
        border.setLayoutY(y);
        panel.getChildren().add(border);
    }

    void analizaPytaniaProcentowePkt(Pytania pytanie, int y) {
        Set<Odpowiedzi> odpowiedzi = pytanie.getOdpowiedzis();
        NumberAxis xAxis = new NumberAxis();
        NumberAxis yAxis = new NumberAxis();
        LineChart<Number, Number> wykres = new LineChart(xAxis, yAxis);
        xAxis.setLabel("odpowiedzi");
        yAxis.setLabel("ilosc odpowiedzi");
        for (Odpowiedzi odpowiedz:odpowiedzi
        ) {
            XYChart.Series series = new XYChart.Series();
            series.setName(odpowiedz.getOdpowiedz());
            for (OdpowiedziUzytkownicy odpowiedzUzytkownika:odpowiedz.getOdpowiedziUzytkownicy()
                 ) {
                series.getData().add(new XYChart.Data(odpowiedzUzytkownika.getPunktowe(), odpowiedz.getCount()));
                wykres.getData().add(series);
            }
        }

        BorderPane border = new BorderPane();
        border.prefWidth(1200);
        border.setTop(new Label(pytanie.getTresc()));
//        border.setLeft(new TableView<>());
//        border.getLeft().minWidth(400);
        border.setCenter(wykres);
        border.getCenter().minWidth(800);
        border.setLayoutY(y);
        panel.getChildren().add(border);
    }

//    metody FXML i interfejsow

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {

    }

    @Override
    public void setStartValues(Uzytkownicy user) {
        curentUser = user;
    }

    @Override
    public void setStartValuesAnkiety(Ankiety ankieta) {
        AnkietyQuery query = new AnkietyQuery();
        this.ankieta = query.selectToAnalysis(ankieta);
        Set<Pytania> pytania = this.ankieta.getPytanias();
        int y = 0;
        for (Pytania pytanie:pytania
             ) {
            switch (pytanie.getRodzajPytania()) {
                case TypeOfQuestion.ONE_CHOICE:
                    analizaPytaniaRadioCheck(pytanie, y);
                    break;
                case TypeOfQuestion.MANY_CHOICE:
                    analizaPytaniaRadioCheck(pytanie, y);
                    break;
                case TypeOfQuestion.OPEN:
                    analizaPytaniaOtwarte(pytanie, y);
                    break;
                case TypeOfQuestion.POINTS:
                    analizaPytaniaProcentowePkt(pytanie, y);
                    break;
                case TypeOfQuestion.PERCENT:
                    analizaPytaniaProcentowePkt(pytanie, y);
                    break;
            }
            y += 500;
        }
    }

    @Override
    public void setStartValuesPytanie(Pytania pytania) {

    }

    @Override
    public void setStartValuesNagroda(Nagrody nagroda) {

    }
}
