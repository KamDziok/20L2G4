/**
 * Sample Skeleton for 'PanelOsobyOdNagrod.fxml' Controller Class
 */

package com.Ankiety_PZ.test;

import com.Ankiety_PZ.hibernate.Nagrody;
import com.Ankiety_PZ.hibernate.Uzytkownicy;
import com.Ankiety_PZ.query.NagrodyQuery;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class PanelOsobyOdNagrodController extends BulidStage implements SetStartValues {


    private Uzytkownicy curentUser;

    private String imie_nazwisko_rola_tmp;

    @FXML
    private Label imie_nazwisko_rola;

    @FXML
    private Button wyloguj;

    @FXML
    private Button panelnagroddodajnagrode;

    @FXML
    private TableView tableNagrody;

    @FXML
    private TableColumn nagrody;

    @FXML
    private TableColumn zdjecie;

    @FXML
    private TableColumn pkt;

    @FXML
    private TableColumn usun;

    @FXML
    private TableColumn edytuj;

    @FXML
    private Label imie_nazwisko_rola2;


    @FXML
    void panelnagroddodajnagrode(ActionEvent event) {
        loadingFXML(event, SceneFXML.PANEL_EDIT_NAGROD);
        activeScene(event, false, false);
    }

    @FXML
    void wyloguj(ActionEvent event) {
        loadingFXML(event, SceneFXML.PANEL_LOGIN);
        activeScene(event, false, false);
    }

    void setNagrody() {
        NagrodyQuery query = new NagrodyQuery();
        List<Nagrody> nagrodies = query.selectAllActive();
        ObservableList<NagrodyTabelka> dane = FXCollections.observableArrayList();
        for (Nagrody nagroda:nagrodies
        ) {
            dane.add(new NagrodyTabelka (nagroda));

        }
        System.out.println();
        tableNagrody.itemsProperty().setValue(dane);
        nagrody.setCellValueFactory(new PropertyValueFactory("tytul"));
        pkt.setCellValueFactory(new PropertyValueFactory("liczbaPunktow"));
        usun.setCellValueFactory(new PropertyValueFactory("usun"));
        edytuj.setCellValueFactory(new PropertyValueFactory("edytuj"));
    }



    @Override
    public void setStartValues(Uzytkownicy user) {
        this.curentUser = user;
        imie_nazwisko_rola_tmp = curentUser.getImie() + " " + curentUser.getNazwisko()+ " - konto zarządzania nagrodami";
        System.out.print(imie_nazwisko_rola_tmp);
        imie_nazwisko_rola.setText(imie_nazwisko_rola_tmp);
        imie_nazwisko_rola2.setText(imie_nazwisko_rola_tmp);
        setNagrody();
    }

    @FXML
    void initialize() {
        assert imie_nazwisko_rola != null : "fx:id=\"imie_nazwisko_rola\" was not injected: check your FXML file 'PanelOsobyOdNagrod.fxml'.";
        assert wyloguj != null : "fx:id=\"wyloguj\" was not injected: check your FXML file 'PanelOsobyOdNagrod.fxml'.";
        assert panelnagroddodajnagrode != null : "fx:id=\"panelnagroddodajnagrode\" was not injected: check your FXML file 'PanelOsobyOdNagrod.fxml'.";
        assert imie_nazwisko_rola2 != null : "fx:id=\"imie_nazwisko_rola2\" was not injected: check your FXML file 'PanelOsobyOdNagrod.fxml'.";
    }
}