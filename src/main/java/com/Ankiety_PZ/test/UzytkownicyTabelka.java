package com.Ankiety_PZ.test;

import com.Ankiety_PZ.hibernate.Uzytkownicy;
import com.Ankiety_PZ.query.UzytkownicyQuery;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

import java.util.Iterator;

public class UzytkownicyTabelka  extends BulidStage {

    private int id;
    private String imie_i_nazwisko;
    private String mail;
    private int liczbaPunktow;
    private Button usun;
    private Button edytuj;
    private Uzytkownicy user;

    UzytkownicyTabelka(Uzytkownicy uzytkownik, PanelAdminaController panel) {
        this.user = uzytkownik;
        imie_i_nazwisko = uzytkownik.getImie()+" "+uzytkownik.getNazwisko();
        liczbaPunktow = uzytkownik.getLiczbaPunktow();
        id = uzytkownik.getIdUzytkownika();
        mail = uzytkownik.getMail();
        usun = new Button("Usuń");
        edytuj = new Button("Edytuj");
        usun.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                UzytkownicyQuery ban = new UzytkownicyQuery();

//                panel.dane.forEach(s -> {
//                    if(s.id == uzytkownik.getIdUzytkownika())
//                        panel.dane.remove(s);
//                });
//                Iterator<UzytkownicyTabelka> iterator = panel.dane.iterator();
//                while (iterator.hasNext()){
//                    Uzytkownicy userIterator = iterator.next().user;
//                    if (userIterator.getIdUzytkownika().equals(uzytkownik.getIdUzytkownika())){
//                        panel.dane.remove(userIterator);
//                    }
//                }
//                panel.tableUzytkownicy.refresh();
//                panel.tableUzytkownicy.setItems(panel.dane);
                ban.ban(uzytkownik);
                panel.setUzytkownicy();
            }
        });
        edytuj.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                loadingFXML(event, SceneFXML.PANEL_EDIT_USER);
                PanelEdycjiUzytkownikaController panelEdycjiUzytkownikaController = load.getController();
                panelEdycjiUzytkownikaController.setStartValues(uzytkownik);
                activeScene(event, false, false);
            }
        });
    }

    public String getImie_i_nazwisko() {
        return imie_i_nazwisko;
    }

    public String getMail() {
        return mail;
    }

    public int getLiczbaPunktow() {
        return liczbaPunktow;
    }

    public Button getUsun() {
        return usun;
    }

    public Button getEdytuj() {
        return edytuj;
    }
}
