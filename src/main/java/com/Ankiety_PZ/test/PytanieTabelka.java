package com.Ankiety_PZ.test;

import com.Ankiety_PZ.hibernate.*;
import com.Ankiety_PZ.query.OdpowiedziQuery;
import com.Ankiety_PZ.query.PytaniaQuery;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

import java.util.Date;
import java.util.Iterator;

public class PytanieTabelka extends BulidStage implements SetStartValues{

    public String treść;
    public int Rpytanie;
    public Button buttonUsun;
    public Button buttonEdycja;

    PytanieTabelka(Ankiety ankieta, Pytania pytanie) {
        treść = pytanie.getTresc();
        Rpytanie = pytanie.getRodzajPytania();
        buttonUsun = new Button("Usun");
        buttonUsun.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                loadingFXML(event, SceneFXML.TWORZENIE_ANKIETY);
                PanelTworzeniaankietyController panelTworzeniaankietyController = load.getController();
                PytaniaQuery query1 =new PytaniaQuery();
                System.out.println(pytanie);

                pytanie.getOdpowiedzis().removeAll(pytanie.getOdpowiedzis());
                pytanie.getOdpowiedzis().forEach(odpowiedz ->{Odpowiedzi JednaOdp = (Odpowiedzi) odpowiedz;
                    OdpowiedziQuery JednaOdpq = new OdpowiedziQuery();
                    JednaOdpq.deleteOdpowiedzi(JednaOdp);});
                System.out.println("pytanie.getOdpowiedzis()pytanie.getOdpowiedzis()pytanie.getOdpowiedzis()pytanie.getOdpowiedzis()pytanie.getOdpowiedzis()pytanie.getOdpowiedzis()pytanie.getOdpowiedzis()pytanie.getOdpowiedzis()pytanie.getOdpowiedzis()pytanie.getOdpowiedzis()pytanie.getOdpowiedzis()pytanie.getOdpowiedzis()pytanie.getOdpowiedzis()pytanie.getOdpowiedzis()pytanie.getOdpowiedzis()pytanie.getOdpowiedzis()pytanie.getOdpowiedzis()pytanie.getOdpowiedzis()pytanie.getOdpowiedzis()pytanie.getOdpowiedzis()pytanie.getOdpowiedzis()pytanie.getOdpowiedzis()pytanie.getOdpowiedzis()pytanie.getOdpowiedzis()pytanie.getOdpowiedzis()pytanie.getOdpowiedzis()pytanie.getOdpowiedzis()pytanie.getOdpowiedzis()pytanie.getOdpowiedzis()pytanie.getOdpowiedzis()pytanie.getOdpowiedzis()pytanie.getOdpowiedzis()pytanie.getOdpowiedzis()pytanie.getOdpowiedzis()pytanie.getOdpowiedzis()pytanie.getOdpowiedzis()pytanie.getOdpowiedzis()pytanie.getOdpowiedzis()pytanie.getOdpowiedzis()pytanie.getOdpowiedzis()pytanie.getOdpowiedzis()pytanie.getOdpowiedzis()pytanie.getOdpowiedzis()pytanie.getOdpowiedzis()pytanie.getOdpowiedzis()pytanie.getOdpowiedzis()pytanie.getOdpowiedzis()pytanie.getOdpowiedzis()pytanie.getOdpowiedzis()pytanie.getOdpowiedzis()pytanie.getOdpowiedzis()pytanie.getOdpowiedzis()pytanie.getOdpowiedzis()pytanie.getOdpowiedzis()pytanie.getOdpowiedzis()pytanie.getOdpowiedzis()pytanie.getOdpowiedzis()pytanie.getOdpowiedzis()pytanie.getOdpowiedzis()pytanie.getOdpowiedzis()pytanie.getOdpowiedzis()pytanie.getOdpowiedzis()pytanie.getOdpowiedzis()pytanie.getOdpowiedzis()pytanie.getOdpowiedzis()pytanie.getOdpowiedzis()pytanie.getOdpowiedzis()pytanie.getOdpowiedzis()pytanie.getOdpowiedzis()pytanie.getOdpowiedzis()pytanie.getOdpowiedzis()pytanie.getOdpowiedzis()pytanie.getOdpowiedzis()");
                System.out.println(pytanie.getOdpowiedzis());
                ankieta.getPytanias().remove(pytanie);
                query1.deletePytania(pytanie);
              ///  ankieta.getPytanias().remove(pytanie);
                System.out.println(pytanie);

                panelTworzeniaankietyController.setStartValuesAnkiety(ankieta);
                panelTworzeniaankietyController.SetEdycja(true);
                   // pytanie.getOdpowiedzis().removeAll(pytanie.getOdpowiedzis());
                   // ankieta.getPytanias().remove(pytanie);

                activeScene(event, false, false);
            }
        });
        buttonEdycja = new Button("Edycja");
        buttonEdycja.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                loadingFXML(event, SceneFXML.DODAJ_PYTANIE);
                DodawaniepytaniaController dodawaniepytaniaController  = load.getController();
                dodawaniepytaniaController.SetEdycja(true);
                dodawaniepytaniaController.setStartValuesAnkiety(ankieta);
                dodawaniepytaniaController.setStartValuesPytanie(pytanie);
                activeScene(event, false, false);
                System.out.println("PRZEKAZYWANIE PYTANIE I ANKIETY DO EDYTOWANIA");
                System.out.println(ankieta);
                System.out.println(pytanie);
                System.out.println(pytanie.getZdjecie());
            }
        });
    }

    public String getTreść() {
        return treść;
    }

    public int getrodzajPytania() {
        return Rpytanie;
    }
    public Button getButtonEdycja() {
        return buttonEdycja;
    }

    public Button getButtonUsun() {
        return buttonUsun;
    }

    @Override
    public void setStartValues(Uzytkownicy user) {}
    @Override
    public void setStartValuesAnkiety(Ankiety ankieta) {
    }
    @Override
    public void setStartValuesPytanie(Pytania pytania) {}
    @Override
    public void setStartValuesNagroda(Nagrody nagroda) {}

    @Override
    public void setStartValuesIerator(Iterator<Pytania> iterator) {

    }
}
