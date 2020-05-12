package com.Ankiety_PZ.test;

import com.Ankiety_PZ.hibernate.Odpowiedzi;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;


public class OdpowiedziTabelka extends BulidStage {
    public String treść;
    public Button buttonUsun;

    OdpowiedziTabelka(String odpowiedzi) {
        treść = odpowiedzi;
        buttonUsun = new Button("Usun");
        buttonUsun.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                loadingFXML(event, SceneFXML.TWORZENIE_ANKIETY);
                PanelTworzeniaankietyController panelTworzeniaankietyController = load.getController();
                activeScene(event, false, false);
            }
        });

    }

    OdpowiedziTabelka(Odpowiedzi odpowiedzi) {
        treść = odpowiedzi.getOdpowiedz();
        buttonUsun = new Button("Usun");
        buttonUsun.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                loadingFXML(event, SceneFXML.TWORZENIE_ANKIETY);
                PanelTworzeniaankietyController panelTworzeniaankietyController = load.getController();
                activeScene(event, false, false);
            }
        });

    }

    public String getTreść() {
        return treść;
    }

    public Button getButtonUsun() {
        return buttonUsun;
    }
}

