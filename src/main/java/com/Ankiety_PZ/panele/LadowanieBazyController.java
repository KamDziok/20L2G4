package com.Ankiety_PZ.panele;

import com.Ankiety_PZ.query.RunningScripts;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Klasa odpowiada za panel odpowiedzialny za wczytanie bazy danych
 */

public class LadowanieBazyController extends BulidStage {

    String blad;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    /**
     * Przycisk odpowiadający za zamknięcie aplikacji
     */

    @FXML
    private Button zamknij;

    /**
     * Przycisk odpowiadający za wykonanie operacji dodania bazzy
     */

    @FXML
    private Button dalej;

    /**
     * Etykieta do pola nazwy użytkownika
     */

    @FXML
    private Label loginLabel;

    /**
     * Etykieta do pola hasło
     */

    @FXML
    private Label hasloLabel;

    /**
     * Etykieta do pola link
     */

    @FXML
    private Label linkLabel;

    /**
     * Etykieta do wyswietlania wykonywanych procesów
     */

    @FXML
    private Label info;

    /**
     * Etykieta do wyswietlania komunikatów
     */

    @FXML
    private Label error;

    /**
     * Pole tekstowe do wprowadzenia nazwy użytkownika
     */

    @FXML
    private TextField login;

    /**
     * Pole tekstowe do wprowadzenia hasła
     */

    @FXML
    private TextField haslo;

    /**
     * Pole tekstowe do wprowadzenia adresu servera MySql
     */

    @FXML
    private TextField link;

    /**
     * Grupa przycisków typu RadioButton.
     */

    @FXML
    private ToggleGroup radioButtonsGroup = new ToggleGroup();

    /**
     * Radio button z bazą
     */

    @FXML
    private RadioButton yes;

    /**
     * Radio button bez bazy
     */

    @FXML
    private RadioButton no;

    @FXML
    void initialize() {
        info.setAlignment(Pos.CENTER);
        error.setAlignment(Pos.CENTER);
        link.setText("localhost/");
        info.setVisible(false);
        zamknij.setVisible(false);
        error.setText("Brak połączenia z bazą danych!");
    }

    /**
     * Metoda do wyłączania/włączania niektórych elementów okna
     *
     * @param akcja true    jesli obiekty mają sie pojawić (info schować)
     *              false   jesli mają sie schować (info pojawić)
     */

    private void setAllVisible(boolean akcja) {
        dalej.setVisible(akcja);
        linkLabel.setVisible(akcja);
        link.setVisible(akcja);
        loginLabel.setVisible(akcja);
        login.setVisible(akcja);
        hasloLabel.setVisible(akcja);
        haslo.setVisible(akcja);
        error.setVisible(akcja);
        yes.setVisible(akcja);
        no.setVisible(akcja);
        zamknij.setVisible(!akcja);
        info.setVisible(!akcja);
    }

    /**
     * Metoda służy do zamknięcia aplikacji po kliknięciu przycisku <code>zamknij</code>.
     */

    @FXML
    void zamknijButtonClick(ActionEvent event) {
        deleteStage(event);
    }

    /**
     * Metoda uruchamia pliki .sql ze skryptami dodającymi bazę danych,
     * i rekordy jeśli zaznaczony jest odpowiedni radioBox
     */

    @FXML
    void dalejButtonClick(ActionEvent event) {
        try {
            setAllVisible(false);
            info.setText("Ładowanie bazy, proszę czekać!");
            RunningScripts.exeSqlFile(link.getText(), login.getText(), haslo.getText(), "baza_danych/ankiety.sql");
            info.setText("Dodawanie użytkownika.");
            RunningScripts.exeSqlFile(link.getText(), login.getText(), haslo.getText(), "baza_danych/ankiety_uzytkownik.sql");
            if (yes.isSelected()){
                info.setText("Zapełnianie bazy danymi.");
                setAllVisible(true);
                setAllVisible(false);
                RunningScripts.exeSqlFile(link.getText(),login.getText(),haslo.getText(),"baza_danych/bazadanychtest/ankiety.sql");
            }
            info.setText("Instalacja przebiegła pomyślnie, zrestartuj aplikację!");
            } catch (Exception e) {
            error.setText("Podane dane są nieprawidłowe lub serwer jest wyłączony!");
            setAllVisible(true);
        }
    }
}