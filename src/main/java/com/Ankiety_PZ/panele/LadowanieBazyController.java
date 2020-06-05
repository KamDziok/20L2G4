/**
 * Sample Skeleton for 'LadowanieBazy.fxml' Controller Class
 */

package com.Ankiety_PZ.panele;

import com.Ankiety_PZ.query.RunningScripts;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class LadowanieBazyController extends BulidStage{

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;



    @FXML
    private Button dalej;



    @FXML
    private Label loginLabel;



    @FXML
    private Label hasloLabel;



    @FXML
    private Label linkLabel;



    @FXML
    private Label info;



    @FXML
    private Label error;



    @FXML
    private TextField login;



    @FXML
    private TextField haslo;



    @FXML
    private TextField link;

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        info.setAlignment(Pos.CENTER);
        error.setAlignment(Pos.CENTER);
        link.setText("jdbc:mysql://localhost/");
        info.setVisible(false);
    }

    private void setAllVisible(boolean akcja) {
        dalej.setVisible(akcja);
        linkLabel.setVisible(akcja);
        link.setVisible(akcja);
        loginLabel.setVisible(akcja);
        login.setVisible(akcja);
        hasloLabel.setVisible(akcja);
        haslo.setVisible(akcja);
        error.setVisible(akcja);
        info.setVisible(!akcja);
    }

    @FXML
    void dalejButtonClick(ActionEvent event) {
        try {
            setAllVisible(false);
            info.setText("Ladowanie bazy danych, to może zająć ok minuty.");
            RunningScripts.exeSqlFile(link.getText(),login.getText(),haslo.getText(),"baza_danych/ankiety.sql");
            info.setText("Dodawanie użytkownika.");
            RunningScripts.exeSqlFile(link.getText(),login.getText(),haslo.getText(),"baza_danych/ankiety_uzytkownik.sql");
            info.setText("Zapełnianie bazy danymi.");
            try {
                RunningScripts.exeSqlFile(link.getText(),login.getText(),haslo.getText(),"baza_danych/bazadanychtest/ankiety.sql");
                loadingFXML(event, SceneFXML.PANEL_LOGIN);
                activeScene(event, false, false);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                error.setText("Ladowanie danych nie powiodło się. Zresetuj aplikacje");
                setAllVisible(true);
            }
        } catch (Exception e) {
            error.setText("Podany użytkownik nie istnieje lub nie ma uprawnień," +
                    " podany adres jest błędny, lub server mySQL jest wyłączony.");
            setAllVisible(true);
        }
    }
}