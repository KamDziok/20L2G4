/**
 * Sample Skeleton for 'oknoAnkietyRadio.fxml' Controller Class
 */

package com.Ankiety_PZ.test;

import com.Ankiety_PZ.hibernate.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ResourceBundle;
import java.util.Set;

public class OknoAnkietyRadioController extends BulidStage implements SetStartValues{

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="radioButtonGroup"
    private ToggleGroup radioButtonGroup = new ToggleGroup(); // Value injected by FXMLLoader

    @FXML
    private AnchorPane panel;

    @FXML
    private Label ankietaTytul;

    @FXML
    private Label trescPytania;

    @FXML
    private ImageView obrazek;

    private LinkedList<RadioButton> radioButtons;
    private LinkedList<CheckBox> checkBox;
    private LinkedList<TextArea> punktowePola;
    private LinkedList<Label> punktoweOdpowiedzi;
    private TextArea odpowiedzOtwarta;
    private Button dalej;

    private Iterator<Pytania> iterator;

    @FXML
    void oknoAnkietyButtonNext(ActionEvent event) {
//        Pytania pytanie = (Pytania) iterator.next();
//        switch (pytanie.getRodzajPytania()) {
//            case 0: //  jednokrotnego wyboru
//                loadingFXML(event, SceneFXML.OKNO_ANKIETA_RADIO);
//                OknoAnkietyRadioController radioController = load.getController();
//                radioController.setStartValuesIerator(iterator, pytanie);
//                activeScene(event, false, false);
//                break;
//            case 1: //  wielokrotnego wyboru
//                loadingFXML(event, SceneFXML.OKNO_ANKIETA_CHECK);
//                OknoAnkietyCheckController checkController = load.getController();
//                checkController.setStartValuesIerator(iterator, pytanie);
//                activeScene(event, false, false);
//                break;
//            case 2: //  otwarte
//                loadingFXML(event, SceneFXML.OKNO_ANKIETA_OPEN);
//                OknoAnkietyOpenController openController = load.getController();
//                openController.setStartValuesIerator(iterator, pytanie);
//                activeScene(event, false, false);
//                break;
//            case 3: //  punktowe
//                loadingFXML(event, SceneFXML.PANEL_UZYTKOWNIKA);
//                PanelUzytkownikaController panelUzytkownikaController = load.getController();
//                radioController.setStartValuesPytanie(pytanie);
//                activeScene(event, false, false);
//                break;
//            case 4: //  procentowe
//                loadingFXML(event, SceneFXML.PANEL_UZYTKOWNIKA);
//                PanelUzytkownikaController panelUzytkownikaController = load.getController();
//                radioController.setStartValuesPytanie(pytanie);
//                activeScene(event, false, false);
//                break;
//        }
    }

    private void setRadioOdpowiedzi(Set<Odpowiedzi> odpowiedzi) {
        radioButtons = new LinkedList();
        int y = 172;
        for (Odpowiedzi odpowiedz : odpowiedzi) {
            radioButtons.add(new RadioButton(odpowiedz.getOdpowiedz()));
        }
        for (RadioButton radio:radioButtons
             ) {
            radio.setLayoutX(37);
            radio.setLayoutY(y);
            radio.setToggleGroup(radioButtonGroup);
            panel.getChildren().add(radio);
            radio.setVisible(true);
            y += 50;
        }
    }

    private void setCheckOdpowiedzi(Set<Odpowiedzi> odpowiedzi) {
        checkBox = new LinkedList();
        int y = 172;
        for (Odpowiedzi odpowiedz : odpowiedzi) {
            checkBox.add(new CheckBox(odpowiedz.getOdpowiedz()));
        }
        for (CheckBox check:checkBox
        ) {
            check.setLayoutX(37);
            check.setLayoutY(y);
            panel.getChildren().add(check);
            check.setVisible(true);
            y += 50;
        }
    }

    private void setOpenOdpowiedzi() {
        int y = 172;
        odpowiedzOtwarta = new TextArea();
        odpowiedzOtwarta.setLayoutX(37);
        odpowiedzOtwarta.setLayoutY(y);
        panel.getChildren().add(odpowiedzOtwarta);
        odpowiedzOtwarta.setVisible(true);
    }

    private void setPktOdpowiedzi(Set<Odpowiedzi> odpowiedzi,int pkt) {
        punktoweOdpowiedzi = new LinkedList();
        punktowePola = new LinkedList();
        int y = 172;
        for (Odpowiedzi odpowiedz : odpowiedzi) {
            punktowePola.add(new TextArea());
            punktoweOdpowiedzi.add(new Label(odpowiedz.getOdpowiedz()));
        }
        for (TextArea area:punktowePola
        ) {
            area.setLayoutX(37);
            area.setLayoutY(y);
            panel.getChildren().add(area);
            area.setVisible(true);
            y += 50;
        }
        for (Label label:punktoweOdpowiedzi
        ) {
            label.setLayoutX(100);
            label.setLayoutY(y);
            panel.getChildren().add(label);
            label.setVisible(true);
            y += 50;
        }
    }

    private void setButton(Iterator iterator) {
        if (iterator.hasNext()) {
            dalej = new Button("Dalej");
            dalej.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    loadingFXML(event, SceneFXML.OKNO_ANKIETA_RADIO);
                    OknoAnkietyRadioController radioController = load.getController();
                    radioController.setStartValuesIerator(iterator);
                    activeScene(event, false, false);
                }
            });
        } else {
            dalej = new Button("Zakończ");
            dalej.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    deleteStage(event);
                }
            });
        }
        dalej.setLayoutX(1090);
        dalej.setLayoutY(665);
        panel.getChildren().add(dalej);
        dalej.setVisible(true);
    }

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

    @Override
    public void setStartValuesIerator(Iterator<Pytania> iterator) {
        Pytania pytanie = iterator.next();
        trescPytania.setText(pytanie.getTresc());
//        obrazek.setImage(pytanie.getZdjecie());

        switch (pytanie.getRodzajPytania()) {
            case 0:
                setRadioOdpowiedzi(pytanie.getOdpowiedzis());
                break;
            case 1:
                setCheckOdpowiedzi(pytanie.getOdpowiedzis());
                break;
            case 2:
                setOpenOdpowiedzi();
                break;
            case 3:
                setPktOdpowiedzi(pytanie.getOdpowiedzis(), pytanie.getPunktowe());
                break;
//            case 4:
//
//                break;
        }
        setButton(iterator);
    }
}
