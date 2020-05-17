/**
 * Sample Skeleton for 'AnalizaAnkiet.fxml' Controller Class
 */

package com.Ankiety_PZ.test;

import com.Ankiety_PZ.hibernate.*;
import com.Ankiety_PZ.query.AnkietyQuery;
import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
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

    private Ankiety ankieta;


//    metody klasy

    void setTable() {

    }

    void analizaPytania(Pytania pytanie, int y) {
        Set<Odpowiedzi> odpowiedzi = pytanie.getOdpowiedzis();
        XYChart.Series series1 = new XYChart.Series();
        series1.setName("odpowiedzi w okresie tym");
        for (Odpowiedzi odpowiedz:odpowiedzi
             ) {
            series1.getData().add(new XYChart.Data(odpowiedz.getOdpowiedz(), odpowiedz.getCount()));
        }
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        StackedBarChart<String, Number> wykres = new StackedBarChart(xAxis, yAxis);
        xAxis.setLabel("odpowiedzi");
        yAxis.setLabel("ilosc odpowiedzi");

        BorderPane border = new BorderPane();
        border.prefWidth(1200);
        border.setTop(new Label(pytanie.getTresc()));
        border.setLeft(new TableView<>());
        border.getLeft().minWidth(400);
        border.setCenter(wykres);
        border.getCenter().minWidth(800);
        border.setLayoutY(y);
        wykres.getData().add(series1);
        panel.getChildren().add(border);
    }

//    metody FXML i interfejsow

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {

    }

    @Override
    public void setStartValues(Uzytkownicy user) {

    }

    @Override
    public void setStartValuesAnkiety(Ankiety ankieta) {
        AnkietyQuery query = new AnkietyQuery();
        this.ankieta = query.selectToAnalysis(ankieta);
        Set<Pytania> pytania = this.ankieta.getPytanias();
        int y = 0;
        for (Pytania pytanie:pytania
             ) {
            analizaPytania(pytanie, y);
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
