/**
 * Sample Skeleton for 'oknoAnkietyRadio.fxml' Controller Class
 */

package com.Ankiety_PZ.test;

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

public class OknoAnkietyRadioController extends BulidStage implements SetStartValues{

    private Uzytkownicy curentUser;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="radioButtonGroup"
    private ToggleGroup radioButtonGroup = new ToggleGroup(); // Value injected by FXMLLoader

    @FXML
    private AnchorPane panel;

    @FXML
    private Label ankietaTytul;

    @FXML
    private Label trescPytania;

    @FXML
    private ImageView obrazek;

    @FXML
    private Label oknoAnkietyLabelError;

    private LinkedList<RadioButton> radioButtons;
    private LinkedList<CheckBox> checkBox;
    private LinkedList<TextField> punktowePola;
    private LinkedList<Label> punktoweOdpowiedzi;
    private TextArea odpowiedzOtwarta;
    private TextField odpowiedzProcentowa;
    private Button dalej;
    private LinkedList<OdpowiedziUzytkownicy> odpowiedziDoWyslania = new LinkedList();
    private LinkedList<PytaniaUzytkownicy> odpowiedziDoWyslaniaOtwarte = new LinkedList();
    private int rodzajPytania;
    private Integer punkty;
    private int punktyZaAnkiete;
    private PanelUzytkownikaController controller;

    private void setRadioOdpowiedzi(Set<Odpowiedzi> odpowiedzi) {
        radioButtons = new LinkedList();
        int y = 172;
        for (Odpowiedzi odpowiedz : odpowiedzi) {
            radioButtons.add(new RadioButton(odpowiedz.getOdpowiedz()));
        }
        for (RadioButton radio:radioButtons
             ) {
            radio.setLayoutX(37);
            radio.setLayoutY(y);
            radio.setToggleGroup(radioButtonGroup);
            panel.getChildren().add(radio);
            radio.setVisible(true);
            y += 50;
        }
    }

    private void setCheckOdpowiedzi(Set<Odpowiedzi> odpowiedzi) {
        checkBox = new LinkedList();
        int y = 172;
        for (Odpowiedzi odpowiedz : odpowiedzi) {
            checkBox.add(new CheckBox(odpowiedz.getOdpowiedz()));
        }
        for (CheckBox check:checkBox
        ) {
            check.setLayoutX(37);
            check.setLayoutY(y);
            panel.getChildren().add(check);
            check.setVisible(true);
            y += 50;
        }
    }

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

    private void setPktOdpowiedzi(Set<Odpowiedzi> odpowiedzi,int pkt) {
        punktoweOdpowiedzi = new LinkedList();
        punktowePola = new LinkedList();
        int y = 172;
        for (Odpowiedzi odpowiedz : odpowiedzi) {
            punktowePola.add(new TextField());
            punktoweOdpowiedzi.add(new Label(odpowiedz.getOdpowiedz()));
        }
        for (TextField field:punktowePola
        ) {
            field.setLayoutX(37);
            field.setLayoutY(y);
            field.setMaxWidth(100);
            panel.getChildren().add(field);
            field.setVisible(true);
            y += 50;
        }
        y = 172;
        for (Label label:punktoweOdpowiedzi
        ) {
            label.setLayoutX(200);
            label.setLayoutY(y);
            panel.getChildren().add(label);
            label.setVisible(true);
            y += 50;
        }
    }

    private void setProcentOdpowiedzi() {
        int y = 172;
        odpowiedzProcentowa = new TextField();
        odpowiedzProcentowa.setLayoutX(37);
        odpowiedzProcentowa.setLayoutY(y);
        panel.getChildren().add(odpowiedzProcentowa);
        odpowiedzProcentowa.setVisible(true);
    }

    private boolean isQuestionComplete() {
        switch (rodzajPytania) {
            case 0:
                return isRadioComplete();
            case 1:
                return isCheckComplete();
            case 2:
                return isOpenComplete();
            case 3:
                return isPktComplete();
            case 4:
                return isPercentComplete();
            default:
                return true;
        }
    }

    private boolean isRadioComplete() {
        for (RadioButton button:radioButtons
        ) {
            if (button.isSelected())
                return true;
        }
        oknoAnkietyLabelError.setText("Wybierz odpowiedz");
        return false;
    }

    private boolean isCheckComplete() {
        for (CheckBox box:checkBox
             ) {
            if (box.isSelected())
                return true;
        }
        oknoAnkietyLabelError.setText("Wybierz co najmniej jedną odpowiedz");
        return false;
    }

    private boolean isOpenComplete() {
        if (odpowiedzOtwarta.getText().equals("")) {
            oknoAnkietyLabelError.setText("Wpisz odpowiedz");
            return false;
        } else return true;
    }

    private boolean isPktComplete() {
        int suma = 0;
        int pole = 0;
        try {
            for (TextField field:punktowePola
            ) {
                pole = Integer.parseInt(field.getText());
                if (pole < 0) {
                    oknoAnkietyLabelError.setText("Wpisana wartość musi być większa lub równa 0");
                    return false;
                }
                suma += pole;
            }
            if (suma == punkty) return true;
            else {
                oknoAnkietyLabelError.setText("Musisz rozdzielić dokładnie " + punkty + " punktów");
                return false;
            }
        } catch (Exception e) {
            oknoAnkietyLabelError.setText("Coś poszło nie tak");
            return false;
        }
    }

    private boolean isPercentComplete() {
        try {
            if (Integer.parseInt(odpowiedzProcentowa.getText()) >= 0 &&
                    Integer.parseInt(odpowiedzProcentowa.getText()) <= 100) return true;
            else {
                oknoAnkietyLabelError.setText("Podana wartość musi być z przedziału od 0 do 100");
                return false;
            }
        } catch (Exception e) {
            oknoAnkietyLabelError.setText("Coś poszło nie tak - upewnij się, że podałeś liczbę bez znaku %");
            return false;
        }
    }

    private void addAnswer(Set<Odpowiedzi> odpowiedzi, Pytania pytanie) {
        switch (rodzajPytania) {
            case 0:
                for (RadioButton button:radioButtons
                     ) {
                    if (button.isSelected())
                        odpowiedziDoWyslania.add(new OdpowiedziUzytkownicy(odpowiedzi.iterator().next(), curentUser));
                }
                break;
            case 1:
                Iterator iteratorek = odpowiedzi.iterator();
                for (CheckBox box:checkBox
                ) {
                    if (box.isSelected()) {
                        Odpowiedzi odp = (Odpowiedzi) iteratorek.next();
                        odpowiedziDoWyslania.add(new OdpowiedziUzytkownicy(odp, curentUser));
                    }
                }
                break;
            case 2:
                odpowiedziDoWyslaniaOtwarte.add(new PytaniaUzytkownicy(pytanie, curentUser, odpowiedzOtwarta.getText()));
                break;
            case 3:
                Iterator iteratorki = odpowiedzi.iterator();
                for (TextField field:punktowePola
                ) {
                    Odpowiedzi odp = (Odpowiedzi) iteratorki.next();
                    odpowiedziDoWyslania.add(new OdpowiedziUzytkownicy(odp, curentUser, Integer.parseInt(field.getText())));
                }
                break;
            case 4:
                odpowiedziDoWyslania.add(new OdpowiedziUzytkownicy(odpowiedzi.iterator().next(), curentUser, Integer.valueOf(odpowiedzProcentowa.getText())));
                break;
        }
    }

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
                            oknoAnkietyLabelError.setText("Coś poszło nie tak, sprubój ponownie puźniej");
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
                            controller.getPanelUzytkownikaLabelErrorAnkiety().setText("Wypełnianie ostatniej ankiety nie powiodło się");
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

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {

    }

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

    @Override
    public void setStartValuesIerator(Iterator<Pytania> iterator) {
        Pytania pytanie = iterator.next();
        if(pytanie.getZdjecie()!= null){
        try {
            conversjaNaZ(pytanie.getZdjecie());
        } catch (IOException e) {
            e.printStackTrace();
        }}
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
                setPktOdpowiedzi(pytanie.getOdpowiedzis(), punkty);
                trescPytania.setText(pytanie.getTresc() + " Rozdziel " + pytanie.getPunktowe() + " punktów.");
                break;
            case TypeOfQuestion.PERCENT:
                setProcentOdpowiedzi();
                trescPytania.setText(pytanie.getTresc() + " Podaj liczbe z przedziału od 0 do 100.");
                break;
        }
        setButton(iterator, pytanie);
    }
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
        File imageFile = new File(directory + "\\" + bytes );
        ImageIO.write(bufferedImage, "jpg", imageFile);
        javafx.scene.image.Image image2 = new Image(imageFile.toURI().toString());
        obrazek.setImage(image2);


    }

    @Override
    public void setStartValuesListOdpowiedzi(LinkedList<OdpowiedziUzytkownicy> odpowiedziDoWyslania, LinkedList<PytaniaUzytkownicy> odpowiedziDoWyslaniaOtwarte) {
        this.odpowiedziDoWyslania = odpowiedziDoWyslania;
        this.odpowiedziDoWyslaniaOtwarte = odpowiedziDoWyslaniaOtwarte;
    }

    @Override
    public void setStartValuesPkt(int punkty) {
        punktyZaAnkiete = punkty;
    }

    @Override
    public void setStartValuesPanelUzytkownikaController(PanelUzytkownikaController controller) { this.controller = controller; }
}
