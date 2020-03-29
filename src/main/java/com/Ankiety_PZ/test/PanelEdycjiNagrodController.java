package com.Ankiety_PZ.test;

        import javafx.event.ActionEvent;
        import javafx.fxml.FXML;
        import javafx.fxml.Initializable;
        import javafx.scene.control.Button;

        import javafx.scene.layout.AnchorPane;
        import javafx.stage.FileChooser;
        import javafx.stage.Stage;

        import java.net.URL;
        import java.util.ResourceBundle;

public class PanelEdycjiNagrodController implements Initializable {


    @FXML
    private AnchorPane anchorpane;

    @FXML
    private Button PanelEdycjiNagrod;

    @FXML
    void panelEdycjiNagrodButtonDodajZdjecie(ActionEvent event) {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Wybierz zdjęcie");
        Stage stage = (Stage)anchorpane.getScene().getWindow();
        fileChooser.showOpenDialog(null);
    }


    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert PanelEdycjiNagrod != null : "fx:id=\"panelEdycjiNagrodButtonDodajZdjecie\" was not injected: check your FXML file 'PanelEdycjiNagrod.fxml'.";


    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
