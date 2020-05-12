/**
 * Sample Skeleton for 'oknoAnkietyRadio.fxml' Controller Class
 */

package com.Ankiety_PZ.test;

import com.Ankiety_PZ.hibernate.*;
import com.Ankiety_PZ.query.OdpowiedziUzytkownicyQuery;
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

import static javax.swing.JOptionPane.showMessageDialog;

public class OknoAnkietyRadioController extends BulidStage implements SetStartValues{

    private Uzytkownicy curentUser;

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
    private LinkedList<TextField> punktowePola;
    private LinkedList<Label> punktoweOdpowiedzi;
    private TextArea odpowiedzOtwarta;
    private TextField odpowiedzProcentowa;
    private Button dalej;


    private LinkedList<OdpowiedziUzytkownicy> odpowiedziDoWyslania = new LinkedList();
    private int rodzajPytania;
    private Integer punkty;

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
            punktowePola.add(new TextField());
            punktoweOdpowiedzi.add(new Label(odpowiedz.getOdpowiedz()));
        }
        for (TextField field:punktowePola
        ) {
            field.setLayoutX(37);
            field.setLayoutY(y);
            field.setMaxWidth(100);
            panel.getChildren().add(field);
            field.setVisible(true);
            y += 50;
        }
        y = 172;
        for (Label label:punktoweOdpowiedzi
        ) {
            label.setLayoutX(200);
            label.setLayoutY(y);
            panel.getChildren().add(label);
            label.setVisible(true);
            y += 50;
        }
    }

    private void setProcentOdpowiedzi() {
        int y = 172;
        odpowiedzProcentowa = new TextField();
        odpowiedzProcentowa.setLayoutX(37);
        odpowiedzProcentowa.setLayoutY(y);
        panel.getChildren().add(odpowiedzProcentowa);
        odpowiedzProcentowa.setVisible(true);
    }

    private boolean isQuestionComplete() {
        switch (rodzajPytania) {
            case 0:
                return isRadioComplete();
            case 1:
                return isCheckComplete();
            case 2:
                return isOpenComplete();
            case 3:
                return isPktComplete();
            case 4:
                return isPercentComplete();
            default:
                return true;
        }
    }

    private boolean isRadioComplete() {
        for (RadioButton button:radioButtons
        ) {
            if (button.isSelected())
                return true;
        }
        return false;
    }

    private boolean isCheckComplete() {
        for (CheckBox box:checkBox
             ) {
            if (box.isSelected())
                return true;
        }
        return false;
    }

    private boolean isOpenComplete() {
        return !odpowiedzOtwarta.getText().equals("");
    }

    private boolean isPktComplete() {
        int suma = 0;
        try {
            for (TextField field:punktowePola
            ) {
                suma += Integer.parseInt(field.getText());
            }
            return suma == punkty;
        } catch (Exception e) {
            return false;
        }
    }

    private boolean isPercentComplete() {
        try {
            return Integer.parseInt(odpowiedzProcentowa.getText()) >= 0 &&
                    Integer.parseInt(odpowiedzProcentowa.getText()) <= 100;
        } catch (Exception e) {
            return false;
        }
    }

    private void addAnswer(Set<Odpowiedzi> odpowiedzi) {
        switch (rodzajPytania) {
            case 0:
                for (RadioButton button:radioButtons
                     ) {
                    if (button.isSelected())
                        odpowiedziDoWyslania.add(new OdpowiedziUzytkownicy(odpowiedzi.iterator().next(), curentUser));
                }
                break;
            case 1:
                for (CheckBox box:checkBox
                ) {
                    if (box.isSelected())
                        odpowiedziDoWyslania.add(new OdpowiedziUzytkownicy(odpowiedzi.iterator().next(), curentUser));
                }
                break;
            case 2:

                break;
            case 3:
                for (TextField field:punktowePola
                ) {
                    odpowiedziDoWyslania.add(new OdpowiedziUzytkownicy(odpowiedzi.iterator().next(), curentUser, Integer.parseInt(field.getText())));
                }
                break;
            case 4:
                odpowiedziDoWyslania.add(new OdpowiedziUzytkownicy(odpowiedzi.iterator().next(), curentUser, Integer.parseInt(odpowiedzProcentowa.getText())));
                break;
        }
    }

    private void setButton(Iterator iterator, Set<Odpowiedzi> odpowiedzi) {
        if (iterator.hasNext()) {
            dalej = new Button("Dalej");
            dalej.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    if (isQuestionComplete()) {
                        addAnswer(odpowiedzi);
                        loadingFXML(event, SceneFXML.OKNO_ANKIETA_RADIO);
                        OknoAnkietyRadioController radioController = load.getController();
                        radioController.setStartValuesIerator(iterator);
                        radioController.setStartValues(curentUser);
                        activeScene(event, false, false);
                    } else {
                        showMessageDialog(null, "Proszę poprawnie wypełnić odpowiedź");
                    }
                }
            });
        } else {
            dalej = new Button("Zakończ");
            dalej.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    showMessageDialog(null, "Gratuluję ukonczenia ankiety.");
                    OdpowiedziUzytkownicyQuery query = new OdpowiedziUzytkownicyQuery();
//                    wyslanie do bazy
//                    zaznaczenie ankiety jako wypelnionej zeby sie wiecej nie pokazywala
//                    wywalenie jej z tabelki
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
        curentUser = user;
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
//        ankietaTytul.setText(pytanie.getAnkiety().getTytul());
        punkty = pytanie.getPunktowe();
        rodzajPytania = pytanie.getRodzajPytania();
        switch (rodzajPytania) {
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
                setPktOdpowiedzi(pytanie.getOdpowiedzis(), punkty);
                break;
            case 4:
                setProcentOdpowiedzi();
                break;
        }
        setButton(iterator, pytanie.getOdpowiedzis());
    }
}
