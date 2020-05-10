package com.Ankiety_PZ.test;

import com.Ankiety_PZ.hibernate.Uzytkownicy;
import com.Ankiety_PZ.query.UzytkownicyQuery;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class UzytkownicyTabelka extends PanelAdminaController {

    private int id;
    private String imie_i_nazwisko;
    private String mail;
    private int liczbaPunktow;
    private Button button;


    UzytkownicyTabelka(Uzytkownicy uzytkownik, PanelAdminaController panel) {
        imie_i_nazwisko = uzytkownik.getImie()+" "+uzytkownik.getNazwisko();
        liczbaPunktow = uzytkownik.getLiczbaPunktow();
        id = uzytkownik.getIdUzytkownika();
        mail = uzytkownik.getMail();
        button = new Button("Usuń");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                UzytkownicyQuery ban = new UzytkownicyQuery();
                ban.ban(uzytkownik);
                panel.dane.forEach(s -> {
                    if(s.id == uzytkownik.getIdUzytkownika())
                        panel.dane.remove(s);
                });
//                Iterator iterator = panel.dane.iterator();
//                while (iterator.hasNext()){
//                    if (iterator. == uzytkownik.getIdUzytkownika()){
//                        panel.dane.iterator().remove();
//                    }
//                }
                panel.tableUzytkownicy.setItems(panel.dane);
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

    public Button getButton() {
        return button;
    }
}
