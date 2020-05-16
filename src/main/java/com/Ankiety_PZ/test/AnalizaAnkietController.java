/**
 * Sample Skeleton for 'AnalizaAnkiet.fxml' Controller Class
 */

package com.Ankiety_PZ.test;

import com.Ankiety_PZ.hibernate.*;
import javafx.fxml.FXML;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Set;

public class AnalizaAnkietController implements SetStartValues{

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

//    pola FXML

    private Label tytulAnkiety;

//    pola klasy

    private Set<Ankiety> ankietySet;
    private Set<Pytania> pytaniaSet;
    private Set<Odpowiedzi> odpowiedziSet;

//    metody klasy

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {

    }

    @Override
    public void setStartValues(Uzytkownicy user) {

    }

    @Override
    public void setStartValuesAnkiety(Ankiety ankieta) {

    }

    @Override
    public void setStartValuesPytanie(Pytania pytania) {

    }

    @Override
    public void setStartValuesNagroda(Nagrody nagroda) {

    }
}
