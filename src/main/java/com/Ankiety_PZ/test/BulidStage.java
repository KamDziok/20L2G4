package com.Ankiety_PZ.test;

import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.Node;

import javax.swing.text.StyledEditorKit;

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

    public void activeScene(ActionEvent event, Boolean maximized) {

        try {
            Scene homeScene = new Scene(home);
            app = (Stage) ((Node) event.getSource()).getScene().getWindow();
            app.setMaximized(maximized);
            app.hide();
            app.setScene(homeScene);
            app.show();

        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }

    }

}
