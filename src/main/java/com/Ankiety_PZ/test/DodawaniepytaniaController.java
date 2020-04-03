
/**
 * Sample Skeleton for 'Dodawaniepytania.fxml' Controller Class
 */

package com.Ankiety_PZ.test;
        import javafx.fxml.Initializable;
        import java.net.URL;
        import java.util.ResourceBundle;
        import javafx.event.ActionEvent;
        import javafx.fxml.FXML;
        import javafx.scene.control.Button;
        import javafx.scene.image.Image;
        import javafx.scene.image.ImageView;
        import javafx.stage.FileChooser;
        import javafx.stage.Stage;

        import java.io.File;
        import java.net.URL;
        import java.util.ResourceBundle;


public class DodawaniepytaniaController extends BulidStage implements Initializable {
    File file = new File("C:\\Users\\wlasciciel\\Pictures\\a.jpg");

    @FXML
    private ImageView imageview;

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
    /**
     * Metoda obsługująca przyciśk anuluj.
     *
     * @author HubertJakobsze
     * @param event zdarzenie, po którym funkcja ma się wywołać
     */

    @FXML
    void anulujAction(ActionEvent event) {
        loadingFXML(event, SceneFXML.TWORZENIE_ANKIETY);
        PanelTworzeniaankietyController panelTworzeniaankietyController = load.getController();
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

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert dodajzdjecie != null : "fx:id=\"dodajzdjecie\" was not injected: check your FXML file 'Dodawaniepytania.fxml'.";
        assert wyloguj1 != null : "fx:id=\"wyloguj1\" was not injected: check your FXML file 'Dodawaniepytania.fxml'.";
        assert anuluj != null : "fx:id=\"anuluj\" was not injected: check your FXML file 'Dodawaniepytania.fxml'.";

    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {


        Image image = new Image(file.toURI().toString());
        imageview.setImage(image);


    }
    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert dodajzdjecie != null : "fx:id=\"dodajzdjecie\" was not injected: check your FXML file 'Dodawaniepytania.fxml'.";
        assert wyloguj1 != null : "fx:id=\"wyloguj1\" was not injected: check your FXML file 'Dodawaniepytania.fxml'.";
        assert anuluj != null : "fx:id=\"anuluj\" was not injected: check your FXML file 'Dodawaniepytania.fxml'.";
        assert imageview != null : "fx:id=\"imageview\" was not injected: check your FXML file 'Dodawaniepytania.fxml'.";

    }
}
