
/**
 * Sample Skeleton for 'Dodawaniepytania.fxml' Controller Class
 */

package com.Ankiety_PZ.test;
import com.Ankiety_PZ.hibernate.*;
import com.Ankiety_PZ.query.AnkietyQuery;
import com.Ankiety_PZ.query.OdpowiedziQuery;
import com.Ankiety_PZ.query.PytaniaQuery;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.*;


public class DodawaniepytaniaController extends BulidStage implements SetStartValues {
    File file = new File("C:\\Users\\wlasciciel\\Pictures\\a.jpg");

    private final ToggleGroup radioButtonGroup = new ToggleGroup();

    @FXML
    private ImageView imageview;

    @FXML
    private TextField odpowiedzi;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="dodajzdjecie"
    private Button dodajzdjecie; // Value injected by FXMLLoader

    @FXML // fx:id="wyloguj1"
    private Button wyloguj1; // Value injected by FXMLLoader

    @FXML // fx:id="anuluj"
    private Button anuluj; // Value injected by FXMLLoader

    @FXML // fx:id="dodawaniePytaniaRBQuestionOpen"
    private RadioButton dodawaniePytaniaRBQuestionOpen; // Value injected by FXMLLoader

    @FXML // fx:id="dodawaniePytaniaRBQuestionCloseMoreThenOne"
    private RadioButton dodawaniePytaniaRBQuestionCloseMoreThenOne; // Value injected by FXMLLoader

    @FXML // fx:id="dodawaniePytaniaRBQuestionCloseOnlyOne"
    private RadioButton dodawaniePytaniaRBQuestionCloseOnlyOne; // Value injected by FXMLLoader

    @FXML // fx:id="dodawaniePytaniaRBQuestionPercentages"
    private RadioButton dodawaniePytaniaRBQuestionPercentages; // Value injected by FXMLLoader

    @FXML // fx:id="dodawaniePytaniaRBQuestionPoints"
    private RadioButton dodawaniePytaniaRBQuestionPoints; // Value injected by FXMLLoader
    @FXML
    private TextField punkty;
    @FXML
    private TextField trescPytania;

    @FXML private TableView odpowiedziTabelka;
    @FXML private TableColumn treść;
    @FXML private TableColumn przyciskUsun;
    private Boolean edycja2;
    private String odp;
    private String tresc;
    private Integer punktowe;
    private int rodzajPytania;
    private Ankiety ankiety2;
    private Ankiety AnkietaStanPoczatkowy;
    private List<String> listaOdp = new ArrayList<String>();
    private Pytania pytania;
    private  ObservableList<OdpowiedziTabelka> dane = FXCollections.observableArrayList();
    private Boolean edycja = false;

    /**
     * Metoda obsługująca przyciśk anuluj.
     *
     * @author HubertJakobsze
     * @param event zdarzenie, po którym funkcja ma się wywołać
     */


    @Override
    public void setStartValues(Uzytkownicy user) {

    }

    @Override
    public void setStartValuesAnkiety(Ankiety ankieta) {
        this.ankiety2 = ankieta;
        this.AnkietaStanPoczatkowy = ankieta;
        System.out.println("ankiety setStartValuesAnkiety dpc");
        System.out.println(ankiety2);

}

    @Override
    public void setStartValuesPytanie(Pytania pytanie) {
        this.pytania = pytanie;
        System.out.println("ankiety setStartValuesAnkiety dpc");
        System.out.println("ankiety setStartValuesAnkiety dpc");
        System.out.println("ankiety setStartValuesAnkiety dpc");
        System.out.println("ankiety setStartValuesAnkiety dpc");
        System.out.println(ankiety2);
        setOdpowiedziSS(pytanie);
    }

    @Override
    public void setStartValuesNagroda(Nagrody nagroda) {

    }


    @Override
    public void setStartValuesIerator(Iterator iterator) {

    }
    public void SetEdycja(Boolean wyb)
    {
        edycja2 = wyb;
    }
    @FXML
    void anulujAction(ActionEvent event) {
        loadingFXML(event, SceneFXML.TWORZENIE_ANKIETY);
        PanelTworzeniaankietyController panelTworzeniaankietyController = load.getController();
        panelTworzeniaankietyController.setStartValuesEdytujAnkiety(ankiety2);
        panelTworzeniaankietyController.SetEdycja(edycja2);
       /// panelTworzeniaankietyController.setStartValues(curetUser);
        activeScene(event, false, false);

    }
    /**
     * Metoda obsługująca przyciśk dodaj zdjecie.
     *
     * @author HubertJakobsze
     * @param event zdarzenie, po którym funkcja ma się wywołać
     */

    @FXML
    void dodajzdjecieAction(ActionEvent event) {
        javafx.stage.FileChooser fileChooser = new javafx.stage.FileChooser();
        fileChooser.setTitle("Wybierz zdjęcie");
        Stage stage = new Stage();
        fileChooser.getExtensionFilters().addAll(
                new javafx.stage.FileChooser.ExtensionFilter("Obrazy", "*.jpg","*.png","*.jpeg")
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

    public ImageView getImageview() {
        return imageview;
    }

    /**
     * Metoda obsługująca przyciśk wyloguj.
     *
     * @author HubertJakobsze
     * @param event zdarzenie, po którym funkcja ma się wywołać
     */

    @FXML
    void wyloguj1Action(ActionEvent event) {
        loadingFXML(event, SceneFXML.PANEL_LOGIN);
        PanelLoginController panelLoginController = load.getController();
        activeScene(event, false, false);


    }
    @FXML
    void dodajpytanieAction(ActionEvent event) {


        if(dodawaniePytaniaRBQuestionOpen.isSelected()) {
            rodzajPytania=2;
            punktowe= 0;
        }
        else{if(dodawaniePytaniaRBQuestionCloseMoreThenOne.isSelected()){
            rodzajPytania=1;
            punktowe= 0;
        }
        else{if(dodawaniePytaniaRBQuestionCloseOnlyOne.isSelected()){
            rodzajPytania=0;
            punktowe= 0;
        }
        else{if(dodawaniePytaniaRBQuestionPercentages.isSelected()){
            rodzajPytania=4;
            punktowe = Integer.parseInt("100");

        }
        else{if(dodawaniePytaniaRBQuestionPoints.isSelected()){
            punktowe = Integer.parseInt(punkty.getText());
        }}}}}



        tresc = trescPytania.getText();
        if(edycja) {
            for(String odpo : listaOdp)
            {

                Odpowiedzi odp = new Odpowiedzi(pytania, odpo);
                odp.setIdOdpowiedzi(-1);
                pytania.getOdpowiedzis().add(odp);
                System.out.println(odpo);
            }

        }else {

        Pytania pytanie = new Pytania();
        if(edycja2)pytanie.setIdPytania(-1);
        pytanie.setTresc(tresc);
        //pytanie.setZdjecie(imageview);
        pytanie.setPunktowe(punktowe);
        pytanie.setRodzajPytania(rodzajPytania);
// if (punktowe!=0) pytanie.setPunktowe(punktowe);
        pytanie.setAnkiety(ankiety2);
        pytanie.initHashSetOdpowiedzi();

        for(String odpo : listaOdp)
        {

            Odpowiedzi odp = new Odpowiedzi(pytanie, odpo);
            if(edycja){odp.setIdOdpowiedzi(-1);}
            pytanie.getOdpowiedzis().add(odp);
            System.out.println(odpo);
        }


            ankiety2.getPytanias().add(pytanie);
        }
        System.out.println(ankiety2.getPytanias());
        loadingFXML(event, SceneFXML.TWORZENIE_ANKIETY);
        PanelTworzeniaankietyController panelTworzeniaankietyController = load.getController();
        panelTworzeniaankietyController.setStartValuesAnkiety(ankiety2);
        panelTworzeniaankietyController.SetEdycja(edycja2);
        activeScene(event, false, false);


    }


    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert dodajzdjecie != null : "fx:id=\"dodajzdjecie\" was not injected: check your FXML file 'Dodawaniepytania.fxml'.";
        assert wyloguj1 != null : "fx:id=\"wyloguj1\" was not injected: check your FXML file 'Dodawaniepytania.fxml'.";
        assert anuluj != null : "fx:id=\"anuluj\" was not injected: check your FXML file 'Dodawaniepytania.fxml'.";
        assert imageview != null : "fx:id=\"imageview\" was not injected: check your FXML file 'Dodawaniepytania.fxml'.";
        assert dodawaniePytaniaRBQuestionOpen != null : "fx:id=\"dodawaniePytaniaRBQuestionOpen\" was not injected: check your FXML file 'Dodawaniepytania.fxml'.";
        assert dodawaniePytaniaRBQuestionCloseMoreThenOne != null : "fx:id=\"dodawaniePytaniaRBQuestionCloseMoreThenOne\" was not injected: check your FXML file 'Dodawaniepytania.fxml'.";
        assert dodawaniePytaniaRBQuestionCloseOnlyOne != null : "fx:id=\"dodawaniePytaniaRBQuestionCloseOnlyOne\" was not injected: check your FXML file 'Dodawaniepytania.fxml'.";
        assert dodawaniePytaniaRBQuestionPercentages != null : "fx:id=\"dodawaniePytaniaRBQuestionPercentages\" was not injected: check your FXML file 'Dodawaniepytania.fxml'.";
        assert dodawaniePytaniaRBQuestionPoints != null : "fx:id=\"dodawaniePytaniaRBQuestionPoints\" was not injected: check your FXML file 'Dodawaniepytania.fxml'.";

        dodawaniePytaniaRBQuestionOpen.setToggleGroup(radioButtonGroup);
        dodawaniePytaniaRBQuestionCloseMoreThenOne.setToggleGroup(radioButtonGroup);
        dodawaniePytaniaRBQuestionCloseOnlyOne.setToggleGroup(radioButtonGroup);
        dodawaniePytaniaRBQuestionPercentages.setToggleGroup(radioButtonGroup);
        dodawaniePytaniaRBQuestionPoints.setToggleGroup(radioButtonGroup);

    }

    public void setOdpowiedzi() {
        ObservableList<OdpowiedziTabelka> dane2 = FXCollections.observableArrayList();
        dane2.addAll(dane);
        for(String odp : listaOdp)
        {
            dane2.add(new OdpowiedziTabelka(odp, ankiety2, pytania, listaOdp));
        }
        odpowiedziTabelka.itemsProperty().setValue(dane2);
        treść.setCellValueFactory(new PropertyValueFactory("treść"));
        przyciskUsun.setCellValueFactory(new PropertyValueFactory("buttonUsun"));


    }
    public void setOdpowiedziSS(Pytania pytania){

        edycja=true;
        pytania.getOdpowiedzis().forEach(odpowiedz ->{Odpowiedzi JednaOdp = (Odpowiedzi) odpowiedz;
            dane.add(new OdpowiedziTabelka(JednaOdp, ankiety2, pytania, listaOdp));});
        odpowiedziTabelka.itemsProperty().setValue(dane);
        treść.setCellValueFactory(new PropertyValueFactory("treść"));
        przyciskUsun.setCellValueFactory(new PropertyValueFactory("buttonUsun"));

    }


    public void dodajOdpAction(ActionEvent event){
        odp = odpowiedzi.getText() ;
        listaOdp.add(odp);
        setOdpowiedzi();
    }



    public void usun(String odp, List<String> list)
    {
        System.out.println(list);
        listaOdp = list;
        listaOdp.remove(odp);
       // setOdpowiedzi();
    }

    public void usunBAZA(Odpowiedzi odp, List<String> list, Pytania pytanie)
    {
        System.out.println(list);
        listaOdp = list;
        pytanie.getOdpowiedzis().remove(odp);
        //setOdpowiedziSS(pytanie);
       // setOdpowiedzi();
    }




}
