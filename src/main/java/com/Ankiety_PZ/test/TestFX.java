package com.Ankiety_PZ.test;

import com.Ankiety_PZ.generowaniePDF.NagrodyGenerowaniePDF;
import com.Ankiety_PZ.hibernate.*;
import com.Ankiety_PZ.query.AnkietyQuery;
import com.Ankiety_PZ.query.ConnectToDataBase;
import com.Ankiety_PZ.query.NagrodyQuery;
import com.Ankiety_PZ.query.UzytkownicyQuery;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class TestFX extends Application {
//    @Override
//    public void start(Stage stage) throws Exception {
//        stage.setScene(new Scene(new Pane(), 800, 600));
//        stage.show();
//    }

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {

        scene = new Scene(loadFXML(SceneFXML.PANEL_LOGIN));
        stage.setScene(scene);
        stage.show();

    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(TestFX.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        ConnectToDataBase.connectToDataBase();
//        LoadDump test = new LoadDump();
//        try {
//            test.loadDump("baza_danych/bazadanychtest/ankiety.sql");
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.out.println(e.getMessage());
//        }
//        System.out.println("Current dir using System:" + Paths.get("") .toAbsolutePath().toString());
//        PytaniaQuery pq = new PytaniaQuery();
//        System.out.println(pq.selectSetPytaniaByIdAnkiety(12).size());

        //dodawanie ankiety
//        Ankiety a = new Ankiety();
//        UzytkownicyQuery uq = new UzytkownicyQuery();
//        Uzytkownicy u = uq.selectById(1);
//        a.setUzytkownicy(u);
//        a.setLiczbaPunktow(10);
//        a.setLiczbaWypelnien(0);
//        a.setDataZakonczenia(new Date());
//        a.setDataRozpoczecia(new Date());
//        a.setTytul("fajna");
//        AnkietyQuery aq = new AnkietyQuery();
//        aq.addAnkiety(a);

        //wyszukiwanie ankiety po id
//        AnkietyQuery aq = new AnkietyQuery();
//        Ankiety a = aq.selectById(12);
//        System.out.println(a);
//        System.out.println(a.toString());

        //dodawanie odpowiedzi użytkownika
//        UzytkownicyQuery uq = new UzytkownicyQuery();
//        Uzytkownicy u = uq.selectById(1);
//        OdpowiedziQuery oq = new OdpowiedziQuery();
//        Odpowiedzi o = oq.selectByID(41);
//        OdpowiedziUzytkownicy ou2 = new OdpowiedziUzytkownicy(o, u);
//        OdpowiedziUzytkownicy ou = new OdpowiedziUzytkownicy(o, u, 12);
//        List<OdpowiedziUzytkownicy> ouList =  new ArrayList<>();
//        ouList.add(ou);
//        ouList.add(ou2);
//        List<PytaniaUzytkownicy> puList = new ArrayList<>();
//        Pytania p = new PytaniaQuery().selectByID(22);
//        PytaniaUzytkownicy pu = new PytaniaUzytkownicy(p,u,"coś");
//        puList.add(pu);
//        System.out.println(uq.addOdpowiedziUzytkownika(ouList, puList));

        //Wyświetlanie Ankiet dla użytkowników
//        UzytkownicyQuery uq = new UzytkownicyQuery();
//        Uzytkownicy u1 = uq.selectById(1);
//        Uzytkownicy u2 = uq.selectById(2);
//        Uzytkownicy u3 = uq.selectById(3);
//        AnkietyQuery aq = new AnkietyQuery();
//        System.out.println(aq.selectAllActiveAndNotDoAnkiety(u1).size());
//        System.out.println(aq.selectAllActiveAndNotDoAnkiety(u2).size());
//        System.out.println(aq.selectAllActiveAndNotDoAnkiety(u3).size());

        //wyświetlanie ankiety raze z pytaniami
//        AnkietyQuery aq = new AnkietyQuery();
//        Ankiety a = aq.selectById(12);
//        System.out.println(aq.selectAnkietaWithPytaniaAndOdpowiedziByAnkiety(a).getPytanias().size());

        //dodanie ankiety z pytaniami
//        Ankiety a = new Ankiety();
//        UzytkownicyQuery uq = new UzytkownicyQuery();
//        Uzytkownicy u = uq.selectById(1);
//        a.setUzytkownicy(u);
//        a.setLiczbaPunktow(10);
//        a.setLiczbaWypelnien(0);
//        a.setDataZakonczenia(new Date());
//        a.setDataRozpoczecia(new Date());
//        a.setTytul("fajna");
//
//        Pytania p = new Pytania();
//        p.setAnkiety(a);
//        p.setTresc("fajne");
//
//        Odpowiedzi o = new Odpowiedzi();
//        o.setPytania(p);
//        o.setOdpowiedz("fajna");
//
//        p.getOdpowiedzis().add(o);
//        a.getPytanias().add(p);
//        AnkietyQuery aq = new AnkietyQuery();
//        aq.addAnkietyWithPytaniaAndOdpowiedzi(a);

        //wyswietlenie wszytkich ankiet uzytkownika
//        System.out.println(new AnkietyQuery().selectAllUzytkownik(new UzytkownicyQuery().selectById(1)).size());

        //wyświetlanie ankiet wraz z odpowiedziami
//        AnkietyQuery aq = new AnkietyQuery();
//        Ankiety a = aq.selectById(12);
//        System.out.println(aq.selectToAnalysis(a).getPytanias().size());

        //dodawanie nagrody do uzytkownika
//        NagrodyQuery nq = new NagrodyQuery();
//        Uzytkownicy u = new UzytkownicyQuery().selectById(7);
//        Nagrody n = nq.selectByID(1);
//        System.out.println(u.getLiczbaPunktow());
//        System.out.println(n.getLiczbaPunktow());
//        System.out.println(nq.getNagrodyToUzytkownicy(n, u));

        //generowanie PDF z nagroda
//        Uzytkownicy u = new UzytkownicyQuery().selectById(7);
//        Nagrody n = new NagrodyQuery().selectByID(1);
//        new NagrodyGenerowaniePDF(n, u);

        //aktualizacja ankiety
//        AnkietyQuery aq = new AnkietyQuery();
//        Ankiety a = aq.selectById(13);
//        Pytania p = new Pytania(a, "fajna",null, TypeOfQuestion.POINTS, 23);
//        a = aq.selectAnkietaWithPytaniaAndOdpowiedziByAnkiety(a);
//        p.setIdPytania(-1);
//
//        Odpowiedzi o = new Odpowiedzi();
//        o.setIdOdpowiedzi(-1);
//        o.setPytania(p);
//        o.setOdpowiedz("fajna");
//
//        p.initHashSetOdpowiedzi();
//        p.getOdpowiedzis().add(o);
//
//        a.getPytanias().add(p);
//        System.out.println("----------------------------------");
//        aq.updateAnkietyWithPytaniaAndOdpowiedzi(a);

        launch();
    }
}
