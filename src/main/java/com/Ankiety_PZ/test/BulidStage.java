package com.Ankiety_PZ.test;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Klasa abstrakcyja z metodami do tworzenia nowych scen i okien.
 *
 * @author KamDziok
 */

public abstract class BulidStage {

    private Parent home;
    protected FXMLLoader load;
    private Stage app;

    /**
     * Metoda ładująca odpowiednia scenę fxml'ową.
     *
     * @param event     zdarzenie na rzecz, którego została wywołana funkcja
     * @param sceneFXML nazwa pliku ze sceną.
     */

    protected void loadingFXML(ActionEvent event, String sceneFXML) {

        try {
            load = new FXMLLoader(getClass().getResource(sceneFXML + ".fxml"));
            home = load.load();

        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }

    }

    /**
     * Metoda do tworzenia okna z załadowaną sceną.
     *
     * @param event     zdarzenie na rzecz, którego została wywołana funkcja.
     * @param maximized ustawienie czy okno ma być maksymalnego rozmiaru.
     * @param newStage  ustawienie czy scena zostanie załadowana w nowym oknie, czy w aktualnym.
     */
    protected void activeScene(ActionEvent event, Boolean maximized, Boolean newStage) {

        try {
            Scene homeScene = new Scene(home);
            if (newStage) {
                app = new Stage();
            } else {
                app = (Stage) ((Node) event.getSource()).getScene().getWindow();
            }
            app.setMaximized(maximized);
            app.hide();
            app.setScene(homeScene);
            app.show();

        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }

    }

    /**
     * Metoda do usuwania okna.
     *
     * @param event zdarzenie na rzecz, którego została wywołana funkcja.
     */
    protected void deleteStage(ActionEvent event) {
        try {
            app = (Stage) ((Node) event.getSource()).getScene().getWindow();
            app.close();
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
    }

}
