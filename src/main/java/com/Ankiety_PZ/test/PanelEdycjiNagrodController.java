package com.Ankiety_PZ.test;

import com.Ankiety_PZ.hibernate.Ankiety;
import com.Ankiety_PZ.hibernate.Pytania;
import com.Ankiety_PZ.hibernate.Uzytkownicy;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Sample Skeleton for 'PanelEdycjiNagrod.fxml' Controller Class
 */

public class PanelEdycjiNagrodController extends BulidStage implements Initializable, SetStartValues {

    private Uzytkownicy curentUser;

    File file = new File("C:\\Users\\Banan\\Pictures\\a.jpg");
    @FXML
    private Button wyloguj;

    @FXML
    private Button panelEdycjiNagrodButtonUsun;

    @FXML
    private Button panelEdycjiNagrodButtonAnuluj;

    @FXML
    private TextField nag;

    @FXML
    private TextField pkt;

    @FXML
    private ImageView imageview;

    @FXML
    private Button PanelEdycjiNagrod;

    @FXML
    private Label imie_nazwisko_rola;

    @FXML
    void wyloguj(ActionEvent event) {
        loadingFXML(event, SceneFXML.PANEL_LOGIN);
        activeScene(event, false, false);
    }


    @FXML
    void panelEdycjiNagrodButtonDodajZdjecie(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Wybierz zdjęcie");
        Stage stage = new Stage();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Obrazy", "*.jpg","*.png","*.jpeg")
                ,new FileChooser.ExtensionFilter("Inne", "*")
        );
        file = fileChooser.showOpenDialog(stage);
        try {
            Image image = new Image(file.toURI().toString());

            imageview.setImage(image);
        }catch(IllegalArgumentException argumentException){
            System.out.println("Nie wybrałeś zdjęcia lub rozszerzenie nie jest obsługiwane. " + argumentException.getMessage());
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void panelEdycjiNagrodButtonAnuluj(ActionEvent event) {
        loadingFXML(event, SceneFXML.PANEL_NAGROD);
        activeScene(event, false, false);
    }

    @FXML
    void panelEdycjiNagrodButtonUsun(ActionEvent event) {
        loadingFXML(event, SceneFXML.PANEL_NAGROD);
        activeScene(event, false, false);
    }

    @Override
    public void setStartValues(Uzytkownicy user) {
        this.curentUser = user;
       String imie_nazwisko_rola_tmp = curentUser.getImie() + " " + curentUser.getNazwisko()+ " - konto zarządzania nagrodami";
        System.out.print(imie_nazwisko_rola_tmp);
        imie_nazwisko_rola.setText(imie_nazwisko_rola_tmp);
    }

    @Override
    public void setStartValuesAnkiety(Ankiety ankieta) {

    }

    @Override
    public void setStartValuesPytanie(Pytania pytania) {

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert PanelEdycjiNagrod != null : "fx:id=\"panelEdycjiNagrodButtonDodajZdjecie\" was not injected: check your FXML file 'PanelEdycjiNagrod.fxml'.";
        assert panelEdycjiNagrodButtonUsun != null : "fx:id=\"panelEdycjiNagrodButtonUsun\" was not injected: check your FXML file 'PanelEdycjiNagrod.fxml'.";
        assert panelEdycjiNagrodButtonAnuluj != null : "fx:id=\"panelEdycjiNagrodButtonAnuluj\" was not injected: check your FXML file 'PanelEdycjiNagrod.fxml'.";
        assert wyloguj != null : "fx:id=\"wyloguj\" was not injected: check your FXML file 'PanelEdycjiNagrod.fxml'.";
        assert imie_nazwisko_rola != null : "fx:id=\"imie_nazwisko_rola\" was not injected: check your FXML file 'PanelEdycjiNagrod.fxml'.";

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {


        Image image = new Image(file.toURI().toString());
        imageview.setImage(image);


    }


}
