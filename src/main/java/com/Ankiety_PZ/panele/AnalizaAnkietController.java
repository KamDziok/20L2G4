package com.Ankiety_PZ.panele;

import com.Ankiety_PZ.hibernate.*;
import com.Ankiety_PZ.query.AnkietyQuery;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.chart.*;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

/**
 * Klasa służy do tworzenia analizy ankiet.
 */

public class AnalizaAnkietController implements SetStartValues {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane panel;

    @FXML
    private Label tytulAnkiety;

    /**
     * Obiekt aktualnie zalogowanego użytkownika.
     */

    private Uzytkownicy curentUser;

    /**
     * Obiekt ankiety do analizy.
     */

    private Ankiety ankieta;

    /**
     * Metoda rysuje wykres dla pytań jednokrotnego wyboru i wielokrotnego wyboru.
     * @param pytanie obiekt pytania.
     * @param y paramter odpowiadający za położenie ukłaldu.
     */

    void analizaPytaniaRadioCheck(Pytania pytanie, int y) {
        Set<Odpowiedzi> odpowiedzi = pytanie.getOdpowiedzis();
        XYChart.Series series1 = new XYChart.Series();
        for (Odpowiedzi odpowiedz : odpowiedzi
        ) {
            series1.getData().add(new XYChart.Data(odpowiedz.getOdpowiedz(), odpowiedz.getCount()));
        }
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        BarChart<String, Number> wykres = new BarChart(xAxis, yAxis);
        xAxis.setLabel("odpowiedzi");
        yAxis.setLabel("ilosc odpowiedzi");
        AnchorPane border = new AnchorPane();
        border.prefWidth(1200);
        Label label = new Label(pytanie.getTresc());
        label.setPrefWidth(1180);
        label.setAlignment(Pos.CENTER);
        label.setLayoutY(30);
        wykres.setLayoutY(140);
        wykres.setLayoutX(300);
        wykres.setScaleX(1.3);
        wykres.setScaleY(1.3);
        border.getChildren().addAll(label, wykres);
        border.setLayoutY(y);
        wykres.getData().add(series1);
        panel.getChildren().add(border);
    }

    /**
     * Metoda wypisuje wszystkie odpowiedzi otwarte w tabeli.
     * @param pytanie obiekt pytania.
     * @param y paramter odpowiadający za położenie ukłaldu.
     */

    void analizaPytaniaOtwarte(Pytania pytanie, int y) {
        TableView<DoAnalizy> tabelka = new TableView();
        TableColumn odpowiedzi = new TableColumn();
        tabelka.getColumns().add(odpowiedzi);
        ObservableList<DoAnalizy> dane = FXCollections.observableArrayList();
        for (PytaniaUzytkownicy odpowiedz : pytanie.getPytaniaUzytkownicy()
        ) {
            dane.add(new DoAnalizy(odpowiedz.getOdpowiedz()));
        }
        tabelka.itemsProperty().setValue(dane);
        odpowiedzi.setCellValueFactory(new PropertyValueFactory("odpowiedz"));
        AnchorPane border = new AnchorPane();
        border.prefWidth(1200);
        Label label = new Label(pytanie.getTresc());
        label.setPrefWidth(1180);
        label.setAlignment(Pos.CENTER);
        label.setLayoutY(30);
        tabelka.setLayoutY(140);
        tabelka.setLayoutX(450);
        tabelka.setScaleX(1.3);
        tabelka.setScaleY(1.3);
        border.getChildren().addAll(label, tabelka);
        border.setLayoutY(y);
        panel.getChildren().add(border);
    }

    private KlasaPomocnicza funkcjaPomocnicza(OdpowiedziUzytkownicy odpowiedzi, List<OdpowiedziUzytkownicy> lista) {
        KlasaPomocnicza klasa = new KlasaPomocnicza(odpowiedzi.getPunktowe());
        for (OdpowiedziUzytkownicy odpowiedz : lista
        ) {
            if (klasa.getPunktowe() == odpowiedz.getPunktowe())
                klasa.update();
        }
        return klasa;
    }

    /**
     * Metoda rysuje wykres dla pytań procentowych i punktowych.
     * @param pytanie obiekt pytania.
     * @param y paramter odpowiadający za położenie ukłaldu.
     */

    void analizaPytaniaProcentowePkt(Pytania pytanie, int y) {
        Set<Odpowiedzi> odpowiedzi = pytanie.getOdpowiedzis();
        LinkedList<XYChart.Series> lista = new LinkedList();
        NumberAxis xAxis = new NumberAxis();
        NumberAxis yAxis = new NumberAxis();
        LineChart<Number, Number> wykres = new LineChart(xAxis, yAxis);
        xAxis.setLabel("odpowiedzi");
        yAxis.setLabel("ilosc odpowiedzi");
        for (Odpowiedzi odpowiedz : odpowiedzi
        ) {
            lista.add(new XYChart.Series());
            XYChart.Series series = lista.getLast();
            series.setName(odpowiedz.getOdpowiedz());
            for (OdpowiedziUzytkownicy odpowiedzUzytkownika : odpowiedz.getOdpowiedziUzytkownicy()
            ) {
                series.getData().add(new XYChart.Data(odpowiedzUzytkownika.getPunktowe(), funkcjaPomocnicza(odpowiedzUzytkownika, odpowiedz.getOdpowiedziUzytkownicy()).getCount()));
            }
            wykres.getData().add(series);
        }

        AnchorPane border = new AnchorPane();
        border.prefWidth(1200);
        Label label = new Label(pytanie.getTresc());
        label.setPrefWidth(1180);
        label.setAlignment(Pos.CENTER);
        label.setLayoutY(30);
        wykres.setLayoutY(140);
        wykres.setLayoutX(300);
        wykres.setScaleX(1.3);
        wykres.setScaleY(1.3);
        border.getChildren().addAll(label, wykres);
        border.setLayoutY(y);
        panel.getChildren().add(border);
    }

    @FXML
    void initialize() {}

    @Override
    public void setStartValues(Uzytkownicy user) {
        curentUser = user;
    }

    @Override
    public void setStartValuesAnkiety(Ankiety ankieta) {
        AnkietyQuery query = new AnkietyQuery();
        this.ankieta = query.selectToAnalysis(ankieta);
        Set<Pytania> pytania = this.ankieta.getPytanias();
        tytulAnkiety.setText(this.ankieta.getTytul());
        int y = 0;
        for (Pytania pytanie : pytania
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
            y += 750;
        }
    }

    @Override
    public void setStartValuesPytanie(Pytania pytania) {

    }

    @Override
    public void setStartValuesNagroda(Nagrody nagroda) {
    }
}
