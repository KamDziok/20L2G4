package com.Ankiety_PZ.test;

import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.Node;

public abstract class BulidStage {

    protected Parent home;
    protected FXMLLoader load;
    protected Stage app;

    public void loadingFXML(ActionEvent event, String sceneFXML) {

        try {
            load = new FXMLLoader(getClass().getResource(sceneFXML + ".fxml"));
            home = load.load();

        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }

    }

    public void activeScene(ActionEvent event) {

        try {
            Scene homeScene = new Scene(home);
            app = (Stage) ((Node) event.getSource()).getScene().getWindow();
            app.hide();
            app.setScene(homeScene);
            app.show();

        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }

    }

}
