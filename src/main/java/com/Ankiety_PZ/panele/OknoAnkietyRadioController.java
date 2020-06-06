package com.Ankiety_PZ.panele;

import com.Ankiety_PZ.hibernate.*;
import com.Ankiety_PZ.query.UzytkownicyQuery;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ResourceBundle;
import java.util.Set;

/**
 * Klasa odpowiada za panel wypełniania ankiety.
 */

public class OknoAnkietyRadioController extends BulidStage implements SetStartValues {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    /**
     * Grupa przycisków typu RadioButton.
     */

    @FXML
    private ToggleGroup radioButtonGroup = new ToggleGroup();

    /**
     * Obiekt typu AnchorPane ("tło okna") do, którego są dodawane kolejne elementy JavaFX.
     */

    @FXML
    private AnchorPane panel;

    /**
     * Etykieta z tytułem ankiety
     */

    @FXML
    private Label ankietaTytul;

    /**
     * Etykieta z treścią pytania.
     */

    @FXML
    private Label trescPytania;

    /**
     * Pole do, którego dodawany jest obrazek.
     */

    @FXML
    private ImageView obrazek;

    /**
     * Etykieta z treścią odpowiedniego błędu.
     * Wyświetlana jeśli odpowiedz nie jest zaznaczona lub odpowiedz nie spełnia kryteriów.
     */

    @FXML
    private Label oknoAnkietyLabelError;

    /**
     * Lista odpowiedzi na pytanie jednokrotnego wyboru.
     */

    private LinkedList<RadioButton> radioButtons;

    /**
     * Lista odpowiedzi na pytanie wielokrotnego wyboru.
     */

    private LinkedList<CheckBox> checkBox;

    /**
     * Lista pól do przypisania punktów w pytaniu punktowym.
     */

    private LinkedList<TextField> punktowePola;

    /**
     * Lista odpowiedzi na pytanie punktowe.
     */

    private LinkedList<Label> punktoweOdpowiedzi;

    /**
     * Pole do wpisania odpowiedzi otwartej.
     */

    private TextArea odpowiedzOtwarta;

    /**
     * Pole do wpisania odpowiedzi procentowej.
     */

    private TextField odpowiedzProcentowa;

    /**
     * Przycisk odpowiadający za przejście do następnego pytania, lub zakończenia ankiety.
     */

    private Button dalej;

    /**
     * Lista odpowiedzi użytkownika (każdego rodzaju poza otwartymi)
     * przeznaczona do wysłania do bazy danych.
     */

    private LinkedList<OdpowiedziUzytkownicy> odpowiedziDoWyslania = new LinkedList();

    /**
     * Lista odpowiedzi użytkownika na pytania otwarte przeznaczona do wysłania do bazy danych.
     */

    private LinkedList<PytaniaUzytkownicy> odpowiedziDoWyslaniaOtwarte = new LinkedList();

    /**
     * Aktualnie zalogowany użytkownik.
     */

    private Uzytkownicy curentUser;

    /**
     * Pole przechowywujące rodzaj pytania
     */

    private int rodzajPytania;

    /**
     * Ilość punktów do rozdzielenia przy pytaniu punktowym.
     */

    private Integer punkty;

    /**
     * Ilość punktów do zdobycią za wypełnienie całej ankiety.
     */

    private int punktyZaAnkiete;

    /**
     * Obiekt kontrolera panelu użytkownika, wykorzystywany np. do aktualizacji ilości punktów po wypełnieniu ankiety.
     */

    private PanelUzytkownikaController controller;

    /**
     * Metoda tworzy i wyświetla pola odpowiedzi dla pytania typu jednokrotnego wyboru.
     *
     * @param odpowiedzi lista odpowiedzi na pytanie.
     */

    private void setRadioOdpowiedzi(Set<Odpowiedzi> odpowiedzi) {
        radioButtons = new LinkedList();
        int y = 172;
        for (Odpowiedzi odpowiedz : odpowiedzi) {
            radioButtons.add(new RadioButton(odpowiedz.getOdpowiedz()));
        }
        for (RadioButton radio : radioButtons
        ) {
            radio.setLayoutX(37);
            radio.setLayoutY(y);
            radio.setToggleGroup(radioButtonGroup);
            panel.getChildren().add(radio);
            radio.setVisible(true);
            y += 50;
        }
    }

    /**
     * Metoda tworzy i wyświetla pola odpowiedzi dla pytania typu wielokrotnego wyboru.
     *
     * @param odpowiedzi lista odpowiedzi na pytanie.
     */

    private void setCheckOdpowiedzi(Set<Odpowiedzi> odpowiedzi) {
        checkBox = new LinkedList();
        int y = 172;
        for (Odpowiedzi odpowiedz : odpowiedzi) {
            checkBox.add(new CheckBox(odpowiedz.getOdpowiedz()));
        }
        for (CheckBox check : checkBox
        ) {
            check.setLayoutX(37);
            check.setLayoutY(y);
            panel.getChildren().add(check);
            check.setVisible(true);
            y += 50;
        }
    }

    /**
     * Metoda tworzy i wyświetla pole przeznaczone na odpowiedz do pytania otwartego.
     */

    private void setOpenOdpowiedzi() {
        int y = 172;
        odpowiedzOtwarta = new TextArea();
        odpowiedzOtwarta.setLayoutX(37);
        odpowiedzOtwarta.setLayoutY(y);
        odpowiedzOtwarta.setMaxWidth(500);
        odpowiedzOtwarta.setWrapText(true);
        panel.getChildren().add(odpowiedzOtwarta);
        odpowiedzOtwarta.setVisible(true);
    }

    /**
     * Metoda tworzy i wyświetla pola odpowiedzi dla pytania typu punktowego.
     *
     * @param odpowiedzi lista odpowiedzi na pytanie.
     */

    private void setPktOdpowiedzi(Set<Odpowiedzi> odpowiedzi) {
        punktoweOdpowiedzi = new LinkedList();
        punktowePola = new LinkedList();
        int y = 172;
        for (Odpowiedzi odpowiedz : odpowiedzi) {
            punktowePola.add(new TextField());
            punktoweOdpowiedzi.add(new Label(odpowiedz.getOdpowiedz()));
        }
        for (TextField field : punktowePola
        ) {
            field.setLayoutX(37);
            field.setLayoutY(y);
            field.setMaxWidth(100);
            panel.getChildren().add(field);
            field.setVisible(true);
            y += 50;
        }
        y = 172;
        for (Label label : punktoweOdpowiedzi
        ) {
            label.setLayoutX(200);
            label.setLayoutY(y);
            panel.getChildren().add(label);
            label.setVisible(true);
            y += 50;
        }
    }

    /**
     * Metoda tworzy i wyświetla pole przeznaczone na odpowiedz do pytania procentowego.
     */

    private void setProcentOdpowiedzi(Set<Odpowiedzi> odpowiedzi) {
        int y = 172;
        for (Odpowiedzi odpowiedz : odpowiedzi) {
            Label procentowe = new Label(odpowiedz.getOdpowiedz());
            procentowe.setLayoutX(350);
            procentowe.setLayoutY(y);
            panel.getChildren().add(procentowe);
        }
        odpowiedzProcentowa = new TextField();
        odpowiedzProcentowa.setLayoutX(37);
        odpowiedzProcentowa.setLayoutY(y);
        panel.getChildren().add(odpowiedzProcentowa);
        odpowiedzProcentowa.setVisible(true);
    }

    /**
     * Metoda sprawdza rodzaj pytania i wywołuje odpowiednią funkcje sprawdzającą poprawność.
     *
     * @return zwraca true jesli odpowiedz jest poprawna, w przeciwnym wypadku false.
     */

    private boolean isQuestionComplete() {
        switch (rodzajPytania) {
            case TypeOfQuestion.ONE_CHOICE:
                return isRadioComplete();
            case TypeOfQuestion.MANY_CHOICE:
                return isCheckComplete();
            case TypeOfQuestion.OPEN:
                return isOpenComplete();
            case TypeOfQuestion.POINTS:
                return isPktComplete();
            case TypeOfQuestion.PERCENT:
                return isPercentComplete();
            default:
                return false;
        }
    }

    /**
     * Metoda sprawdza czy jest zaznaczona odpowiedz.
     *
     * @return zwraca true jesli odpowiedz jest zaznaczona, w przeciwnym wypadku false.
     */

    private boolean isRadioComplete() {
        for (RadioButton button : radioButtons
        ) {
            if (button.isSelected())
                return true;
        }
        oknoAnkietyLabelError.setText("Wybierz odpowiedź!");
        return false;
    }

    /**
     * Metoda sprawdza czy jest zaznaczona co najmniej jedna odpowiedz.
     *
     * @return zwraca true jesli co najmniej jedna odpowiedz jest zaznaczona, w przeciwnym wypadku false.
     */

    private boolean isCheckComplete() {
        for (CheckBox box : checkBox
        ) {
            if (box.isSelected())
                return true;
        }
        oknoAnkietyLabelError.setText("Wybierz co najmniej jedną odpowiedź!");
        return false;
    }

    /**
     * Metoda sprawdza czy odpowiedz na pytanie otwarte nie jest pustym łańcuchem znaków.
     *
     * @return zwraca true jesli odpowiedz jest nie pustym łańcuchem znaków, w przeciwnym wypadku false.
     */

    private boolean isOpenComplete() {
        if (odpowiedzOtwarta.getText().equals("")) {
            oknoAnkietyLabelError.setText("Podaj odpowiedź!");
            return false;
        } else return true;
    }

    /**
     * Metoda sprawdza czy odpowiedz na pytanie procentowe jest poprawna.
     *
     * @return zwraca true jesli odpowiedz jest nie mniejsza od 0 i suma rozdzielonych punktów zgadza się z
     * liczbą ustawioną przez ankietera, w przeciwnym wypadku false.
     */

    private boolean isPktComplete() {
        int suma = 0;
        int pole = 0;
        try {
            for (TextField field : punktowePola
            ) {
                pole = Integer.parseInt(field.getText());
                if (pole < 0) {
                    oknoAnkietyLabelError.setText("Wpisana wartość musi być większa lub równa 0!");
                    return false;
                }
                suma += pole;
            }
            if (suma == punkty) return true;
            else {
                oknoAnkietyLabelError.setText("Musisz rozdzielić dokładnie " + punkty + " punktów!");
                return false;
            }
        } catch (Exception e) {
            oknoAnkietyLabelError.setText("Błąd aplikacji, coś poszło nie tak! Upewnij się, że podałeś liczbę całkowitą");
            return false;
        }
    }

    /**
     * Metoda sprawdza czy odpowiedz na pytanie procentowe jest poprawne.
     *
     * @return zwraca true jesli odpowiedz jest z przedziału od 0 do 100, w przeciwnym wypadku false.
     */

    private boolean isPercentComplete() {
        try {
            if (Integer.parseInt(odpowiedzProcentowa.getText()) >= 0 &&
                    Integer.parseInt(odpowiedzProcentowa.getText()) <= 100) return true;
            else {
                oknoAnkietyLabelError.setText("Podana wartość musi być z przedziału od 0 do 100!");
                return false;
            }
        } catch (Exception e) {
            oknoAnkietyLabelError.setText("Coś poszło nie tak, upewnij się, że podałeś liczbę całkowitą bez znaku %!");
            return false;
        }
    }

    /**
     * Metoda dodaje odpowiedzi użytkownika do odpowiedzniej listy.
     *
     * @param  odpowiedzi odpowiedzi udzielone przez użytkownika.
     * @param  pytanie    obiekt wymagany przy dodawaniu odpowiedzi do pytania otwartego.
     */

    private void addAnswer(Set<Odpowiedzi> odpowiedzi, Pytania pytanie) {
        switch (rodzajPytania) {
            case TypeOfQuestion.ONE_CHOICE:
                for (RadioButton button : radioButtons
                ) {
                    if (button.isSelected())
                        odpowiedziDoWyslania.add(new OdpowiedziUzytkownicy(odpowiedzi.iterator().next(), curentUser));
                }
                break;
            case TypeOfQuestion.MANY_CHOICE:
                Iterator iteratorek = odpowiedzi.iterator();
                for (CheckBox box : checkBox
                ) {
                    if (box.isSelected()) {
                        Odpowiedzi odp = (Odpowiedzi) iteratorek.next();
                        odpowiedziDoWyslania.add(new OdpowiedziUzytkownicy(odp, curentUser));
                    }
                }
                break;
            case TypeOfQuestion.OPEN:
                odpowiedziDoWyslaniaOtwarte.add(new PytaniaUzytkownicy(pytanie, curentUser, odpowiedzOtwarta.getText()));
                break;
            case TypeOfQuestion.POINTS:
                Iterator iteratorki = odpowiedzi.iterator();
                for (TextField field : punktowePola
                ) {
                    Odpowiedzi odp = (Odpowiedzi) iteratorki.next();
                    odpowiedziDoWyslania.add(new OdpowiedziUzytkownicy(odp, curentUser, Integer.parseInt(field.getText())));
                }
                break;
            case TypeOfQuestion.PERCENT:
                odpowiedziDoWyslania.add(new OdpowiedziUzytkownicy(odpowiedzi.iterator().next(),
                        curentUser, Integer.valueOf(odpowiedzProcentowa.getText())));
                break;
        }
    }

    /**
     * Metoda przypisuje odpowiednie funkcje przyciskowi dalej i dodaje odpowiedz na pytanie.
     * Jeśli jest to ostatnie pytanie, wtedy przycisk zmienia swoją funkcję i nazwe na Zakończ.
     * W takim przypadku wywoływana jest metoda zapisująca odpowiedzi do bazy danych,
     * metoda aktualizująca iloś punktów użytkownika orazmetoda aktualizująca tabelę dostępnych ankiet.
     *
     * W przeciwnym wypadku jest to przycisk do następnego pytania, który przekazuje niezbędne parametry dalej.
     *
     * @param  iterator jest to obiekt dzięki, któremu poruszamy się po zbiorze pytań.
     * @param  pytanie  obiekt wymagany przy dodawaniu odpowiedzi do listy.
     */

    private void setButton(Iterator iterator, Pytania pytanie) {
        if (iterator.hasNext()) {
            dalej = new Button("Dalej");
            dalej.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    if (isQuestionComplete()) {
                        try {
                            addAnswer(pytanie.getOdpowiedzis(), pytanie);
                            loadingFXML(event, SceneFXML.OKNO_ANKIETA_RADIO);
                            OknoAnkietyRadioController radioController = load.getController();
                            radioController.setStartValuesIerator(iterator);
                            radioController.setStartValues(curentUser);
                            radioController.setStartValuesListOdpowiedzi(odpowiedziDoWyslania, odpowiedziDoWyslaniaOtwarte);
                            radioController.setStartValuesPkt(punktyZaAnkiete);
                            radioController.setStartValuesPanelUzytkownikaController(controller);
                            activeScene(event, false, false);
                        } catch (Exception e) {
                            oknoAnkietyLabelError.setText("Coś poszło nie tak, spróbuj ponownie później!");
                        }
                    }
                }
            });
        } else {
            dalej = new Button("Zakończ");
            dalej.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    if (isQuestionComplete()) {
                        try {
                            addAnswer(pytanie.getOdpowiedzis(), pytanie);
                            UzytkownicyQuery query = new UzytkownicyQuery();
                            query.addOdpowiedziUzytkownika(odpowiedziDoWyslania, odpowiedziDoWyslaniaOtwarte, pytanie.getAnkiety(), curentUser);
                            curentUser.updatePunkty(punktyZaAnkiete, true);
                            query.updateUzytkownicy(curentUser);
                            controller.updatePkt(String.valueOf(curentUser.getLiczbaPunktow()));
                            controller.setAnkiety();
                        } catch (Exception e) {
                            controller.getPanelUzytkownikaLabelErrorAnkiety().setText("Wypełnianie ostatniej ankiety nie powiodło się!");
                        } finally {
                            deleteStage(event);
                        }
                    }
                }
            });
        }
        dalej.setLayoutX(1090);
        dalej.setLayoutY(665);
        panel.getChildren().add(dalej);
        dalej.setVisible(true);
    }

    @FXML
    void initialize() {

    }

    /**
     * Metoda przekazująca obecnie zalogowanego użytkownika
     */

    @Override
    public void setStartValues(Uzytkownicy user) {
        curentUser = user;
    }

    @Override
    public void setStartValuesAnkiety(Ankiety ankieta) {

    }

    @Override
    public void setStartValuesPytanie(Pytania pytania) {

    }

    @Override
    public void setStartValuesNagroda(Nagrody nagroda) {

    }

    /**
     * Metoda przekazująca Iterator do następnego lub pierwszego pytania.
     * Zaraz po przekazaniu pytania, twożone i wyświetlane są odpowiedznie elementy JavaFX
     *
     * @param iterator jest to obiekt dzięki, któremu poruszamy się po zbiorze pytań.
     */

    @Override
    public void setStartValuesIerator(Iterator<Pytania> iterator) {
        Pytania pytanie = iterator.next();
        if (pytanie.getZdjecie() != null) {
            try {
                conversjaNaZ(pytanie.getZdjecie());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        ankietaTytul.setText(pytanie.getAnkiety().getTytul());
        punkty = pytanie.getPunktowe();
        rodzajPytania = pytanie.getRodzajPytania();
        switch (rodzajPytania) {
            case TypeOfQuestion.ONE_CHOICE:
                setRadioOdpowiedzi(pytanie.getOdpowiedzis());
                trescPytania.setText(pytanie.getTresc());
                break;
            case TypeOfQuestion.MANY_CHOICE:
                setCheckOdpowiedzi(pytanie.getOdpowiedzis());
                trescPytania.setText(pytanie.getTresc());
                break;
            case TypeOfQuestion.OPEN:
                setOpenOdpowiedzi();
                trescPytania.setText(pytanie.getTresc());
                break;
            case TypeOfQuestion.POINTS:
                setPktOdpowiedzi(pytanie.getOdpowiedzis());
                trescPytania.setText(pytanie.getTresc() + " Rozdziel " + pytanie.getPunktowe() + " punktów.");
                break;
            case TypeOfQuestion.PERCENT:
                setProcentOdpowiedzi(pytanie.getOdpowiedzis());
                trescPytania.setText(pytanie.getTresc() + " Podaj liczbe z przedziału od 0 do 100.");
                break;
        }
        setButton(iterator, pytanie);
    }

    /**
     * Metoda konwertuje tablicę byte na Image
     *
     * @param bytes   tablica byte ze zdjęciem w formacie jpg.
     * @throws  IllegalArgumentException Błedne dane
     * @throws  IOException Błąd odczytu
     */

    public void conversjaNaZ(byte[] bytes) throws IOException {


        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
        Iterator<?> readers = ImageIO.getImageReadersByFormatName("jpg");

        ImageReader reader = (ImageReader) readers.next();
        Object source = bis;
        ImageInputStream iis = ImageIO.createImageInputStream(source);
        reader.setInput(iis, true);
        ImageReadParam param = reader.getDefaultReadParam();

        BufferedImage image = reader.read(0, param);

        BufferedImage bufferedImage = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_RGB);
        String directory = System.getProperty("user.home") + "\\Documents\\Zdjęcia";
        String directory2 = directory + "\\pdf";
        if (!(new File(directory).exists())) {
            new File(directory).mkdir();
        }
        Graphics2D g2 = bufferedImage.createGraphics();
        g2.drawImage(image, null, null);
        File imageFile = new File(directory + "\\" + bytes);
        ImageIO.write(bufferedImage, "jpg", imageFile);
        javafx.scene.image.Image image2 = new Image(imageFile.toURI().toString());
        obrazek.setImage(image2);


    }

    /**
     * Metoda przekazująca listy odpowiedzi.
     *
     * @param odpowiedziDoWyslania        lista odpowiedzi użytkownika (każdego rodzaju poza otwartymi).
     * @param odpowiedziDoWyslaniaOtwarte lista odpowiedzi użytkownika na pytania otwarte.
     */

    @Override
    public void setStartValuesListOdpowiedzi(LinkedList<OdpowiedziUzytkownicy> odpowiedziDoWyslania, LinkedList<PytaniaUzytkownicy> odpowiedziDoWyslaniaOtwarte) {
        this.odpowiedziDoWyslania = odpowiedziDoWyslania;
        this.odpowiedziDoWyslaniaOtwarte = odpowiedziDoWyslaniaOtwarte;
    }

    /**
     * Metoda przekazująca ilość punktów do zdobycia za wypełnienie ankiety.
     *
     * @param punkty liczba punktów.
     */

    @Override
    public void setStartValuesPkt(int punkty) {
        punktyZaAnkiete = punkty;
    }

    /**
     * Metoda przekazująca kontroler panelu użytkownika.
     *
     * @param controller obiekt kontrolera panelu użytkownika.
     */

    @Override
    public void setStartValuesPanelUzytkownikaController(PanelUzytkownikaController controller) {
        this.controller = controller;
    }
}
