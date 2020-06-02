package com.Ankiety_PZ.panele;

import com.Ankiety_PZ.hibernate.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.util.List;
import java.util.*;

/**
 * Klasa odpowiedzialna za dodawanie odpowiedzi do pytani oraz gotowych już pytań do ankiety
 */

public class DodawaniePytaniaController extends BulidStage implements SetStartValues {
    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;

    /**
     * Plik przechowujący zdjecie.
     */

    File file;

    /**
     * obiekt przechowujący grupe przycisków radio box.
     */

    private final ToggleGroup radioButtonGroup = new ToggleGroup();
    /**
     * obiekt aktualną liczbe wypełnień ankiety.
     */

    private int aktualnaliczbaodpowiedzi = 0;

    /**
     * widok zdjecia dla pytania
     */

    @FXML
    private ImageView imageview;

    /**
     * pole na treść odpowiedzi.
     */

    @FXML
    private TextField odpowiedzi;

    /**
     * przycisk służacy do dadawania zdjęcia.
     */

    @FXML
    private Button dodajzdjecie;

    /**
     * przycisk służacy do wylogowywania.
     */

    @FXML
    private Button wyloguj1;

    /**
     * przycisk służacy do anulowania zmian.
     */

    @FXML
    private Button anuluj;

    /**
     * przycisk Radio box służący do zaznaczenia pytania Otwartego.
     */

    @FXML
    private RadioButton dodawaniePytaniaRBQuestionOpen;

    /**
     * przycisk Radio box służący do zaznaczenia pytania Otwartego.
     */

    @FXML
    private RadioButton dodawaniePytaniaRBQuestionCloseMoreThenOne;

    /**
     * przycisk Radio box służący do zaznaczenia pytania zmkniętego wielokrotnego wyboru.
     */

    @FXML
    private RadioButton dodawaniePytaniaRBQuestionCloseOnlyOne;

    /**
     * przycisk Radio box służący do zaznaczenia pytania zamkniętego jednokrotnego wyboru.
     */

    @FXML
    private RadioButton dodawaniePytaniaRBQuestionPercentages;

    /**
     * przycisk Radio box służący do zaznaczenia pytania procentowego.
     */

    @FXML
    private RadioButton dodawaniePytaniaRBQuestionPoints;

    /**
     * przycisk Radio box służący do zaznaczenia pytania punktowego.
     */

    /**
     * pole do wpisania ilości punktów w pytaniu punktowym.
     */
    @FXML
    private TextField punkty;

    /**
     * liczba punktów w formacie String.
     */

    private String punktyS;

    /**
     * liczba punktów w formacie int.
     */

    private int punktyi;

    /**
     * pole na treść pytania.
     */

    @FXML
    private TextField trescPytania;

    /**
     * widok treść błedu przy tworzeniu pytania.
     */

    @FXML
    private Label panelTworzeniaPytanLabelError;

    /**
     * widok tableki z odopwiedziami.
     */

    @FXML
    private TableView odpowiedziTabelka;

    /**
     * widok column tableki z treścią odpowiedzi.
     */

    @FXML
    private TableColumn tresc;

    /**
     * widok column tableki z przyciskiem usuń.
     */

    @FXML
    private TableColumn przyciskUsun;

    /**
     * obiek przechowujący bajty zdjecia.
     */

    private byte[] zdjecie;

    /**
     * obiek przechowujący stan ankiety.
     */

    private Boolean edycja2;

    /**
     * treść odpowiedzi.
     */
    private String odp;

    /**
     * obiek przechowujący aktualnego uzytkownika.
     */

    private Uzytkownicy curetUser;

    /**
     * obiek przechowujący ilość punktów wartych za pytanie.
     */

    private Integer punktowe;

    /**
     * obiek przechowujący rodzaj pytania.
     */

    private int rodzajPytania;

    /**
     * obiek przechowujący aktualną ankiete.
     */

    private Ankiety ankiety2;

    /**
     * obiek przechowujący aktualne pytanie.
     */
    private Pytania pytania;

    /**
     * lista przechowująca pytania.
     */

    private List<Pytania> listaPytaU;

    /**
     * lista przechowująca odpowiedzi.
     */
    private List<Odpowiedzi> listaOdpU;

    /**
     * lista przechowująca odpowiedzi aktualne dodawane.
     */

    private Set listaOdpTego;

    /**
     * obiek przechowujący stan pytania (edytowany/tworzony).
     */

    private Boolean edycja = false;

    /**
     * lista przechowująca pytania do przekazania dalej.
     */

    private Set<Pytania> lisaPytanPrzekazana;

    /**
     * metoda przechwytującego aktualne zalogowaniego uzytkownika.
     */

    @Override

    public void setStartValues(Uzytkownicy user) {
        this.curetUser = user;
    }

    /**
     * Metoda przechwytująca aktualną liste pytań do ankiety.
     *
     */

    public void setLisaPytanPrzekazana(Set<Pytania> lisaPytanPrzekazana) {
        this.lisaPytanPrzekazana = lisaPytanPrzekazana;
    }

    /**
     * Metoda przechwytująca aktualną edytowaną/tworzoną ankietę.
     */

    @Override
    public void setStartValuesAnkiety(Ankiety ankieta) {
        this.ankiety2 = ankieta;
        System.out.println(ankiety2);
    }

    /**
     * Metoda wypisująca informacje danego pytania w momęcie jego edycji.
     */


    @Override
    public void setStartValuesPytanie(Pytania pytanie) {
        this.pytania = pytanie;
        if (pytania != null) {
            trescPytania.setText(pytania.getTresc());
            if (pytania.getRodzajPytania() == TypeOfQuestion.OPEN) {
                dodawaniePytaniaRBQuestionOpen.setSelected(true);
            } else {
                if (pytania.getRodzajPytania() == TypeOfQuestion.MANY_CHOICE) {
                    dodawaniePytaniaRBQuestionCloseMoreThenOne.setSelected(true);
                } else {
                    if (pytania.getRodzajPytania() == TypeOfQuestion.ONE_CHOICE) {
                        dodawaniePytaniaRBQuestionCloseOnlyOne.setSelected(true);
                    } else {
                        if (pytania.getRodzajPytania() == TypeOfQuestion.PERCENT) {
                            dodawaniePytaniaRBQuestionPercentages.setSelected(true);
                        } else {
                            if (pytania.getRodzajPytania() == TypeOfQuestion.POINTS) {
                                dodawaniePytaniaRBQuestionPoints.setSelected(true);
                                punkty.setText(String.valueOf(pytania.getPunktowe()));
                            }

                        }
                    }
                }
            }

            if (null != pytania.getZdjecie()) {
                try {
                    conversjaNaZ(pytania.getZdjecie());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (pytanie.getRodzajPytania() != TypeOfQuestion.OPEN) {

                setOdpowiedziSS(listaOdpTego);
            }
        }
    }


    @Override
    public void setStartValuesNagroda(Nagrody nagroda) {

    }

    @Override
    public void setStartValuesIerator(Iterator iterator) {

    }

    /**
     * metoda przechwytująca stan danego pytania czy jest tworzone czy edytowane.
     */

    public void SetEdycja(Boolean wyb) {
        edycja2 = wyb;
    }

    /**
     * metoda przechowująca pytania których nie należy dodawać w przypdaku kliknięcia anuluj.
     */

    public void SetAnuluj(Set odp) {
        this.listaOdpTego = odp;
    }

    /**
     * metoda inicjująca wartość początkową dla listaOdpTego dla pytań otwartych.
     */

    public void Inicjajca() {
        listaOdpTego = new HashSet();

    }

    public Set getListaOdpTego() {
        return listaOdpTego;
    }

    /**
     * metoda inicjująca wartość początkową dla listaOdpTego dla pytań innych niż otwarte.
     */

    public void InicjajcaZ(Pytania pytania) {

        listaOdpTego = new HashSet<Odpowiedzi>(pytania.getOdpowiedzis());

    }

    /**
     * metoda obsługująca przycisk anuluj.
     */

    @FXML
    void anulujAction(ActionEvent event) {
        loadingFXML(event, SceneFXML.TWORZENIE_ANKIETY);
        PanelTworzeniaAnkietyController panelTworzeniaankietyController = load.getController();
        listaOdpTego = new HashSet();
        panelTworzeniaankietyController.setListaPytan(lisaPytanPrzekazana);
        panelTworzeniaankietyController.SetStart();
        panelTworzeniaankietyController.setPytdoUsuniecia();
        panelTworzeniaankietyController.DaneUsniecia(listaPytaU, listaOdpU);
        if (rodzajPytania != TypeOfQuestion.OPEN && pytania != null) {
            listaOdpTego = new HashSet();
        }
        panelTworzeniaankietyController.setStartValuesPytanie(pytania);
        panelTworzeniaankietyController.setStartValues(curetUser);
        panelTworzeniaankietyController.setStartValuesAnkiety(ankiety2);
        panelTworzeniaankietyController.SetEdycja(edycja2);
        activeScene(event, false, false);

    }

    /**
     * Metoda obsługująca przyciśk dodaj zdjecie.
     *
     * @param event zdarzenie, po którym funkcja ma się wywołać
     */

    @FXML
    void dodajzdjecieAction(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Wybierz zdjęcie");
        Stage stage = new Stage();
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Obraz", "*.jpg"));
        file = fileChooser.showOpenDialog(stage);
        try {
            Image image = new Image(file.toURI().toString());
            imageview.setImage(image);
            conversja(file);


        } catch (IllegalArgumentException argumentException) {
            System.out.println("Nie wybrałeś zdjęcia lub rozszerzenie nie jest obsługiwane. " + argumentException.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    /**
     * Metoda służąca do conversji zdjecia .jpg na bajty.
     */

    public void conversja(File file) throws FileNotFoundException, IOException {
        FileInputStream fis = new FileInputStream(file);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buf = new byte[1024];
        try {
            for (int readNum; (readNum = fis.read(buf)) != -1; ) {
                bos.write(buf, 0, readNum);
            }
        } catch (IOException ex) {
        }

        byte[] bytes = bos.toByteArray();
        zdjecie = bytes;
        pytania.setZdjecie(zdjecie);
    }

    /**
     * Metoda służąca do conversji bajtów z bazy danych na zdjęcie.
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
        if (!(new File(directory).exists())) {
            new File(directory).mkdir();
        }
        Graphics2D g2 = bufferedImage.createGraphics();
        g2.drawImage(image, null, null);
        File imageFile = new File(directory + "\\" + pytania.getIdPytania());
        ImageIO.write(bufferedImage, "jpg", imageFile);
        Image image2 = new Image(imageFile.toURI().toString());
        imageview.setImage(image2);
    }

    /**
     * Metoda obsługująca przyciśk wyloguj.
     *
     * @param event zdarzenie, po którym funkcja ma się wywołać
     * @author HubertJakobsze
     */

    @FXML
    void wyloguj1Action(ActionEvent event) {
        loadingFXML(event, SceneFXML.PANEL_LOGIN);
        PanelLoginController panelLoginController = load.getController();
        activeScene(event, false, false);
    }

    /**
     * Metoda sprawdza czy punkty składają się z liczb i czy nie są ujemne.
     *
     * @return true jeśli punkty są poprawne, w przeciwnym razie false
     */

    private boolean punktyisnumber() {
        try {
            punktyS = punkty.getText();
            punktyi = Integer.parseInt(punktyS);

            if (punktyi > 0) {
                return true;
            } else {
                panelTworzeniaPytanLabelError.setText("Liczba punktów nie może być mniejsza od 0.");
                return false;
            }
        } catch (IllegalArgumentException argumentException) {
            panelTworzeniaPytanLabelError.setText("Punkty muszą być liczbą!");
        }

        return false;
    }

    /**
     * Metoda obsługująca dodanie obietku pytanie wraz z odpowiedziami do obiektu ankiety.
     * @param event zdarzenie, po którym funkcja ma się wywołać
     */

    @FXML
    void dodajPytanie(ActionEvent event) {

        if (!trescPytania.getText().isEmpty()) {
            if (rodzajPytania == TypeOfQuestion.OPEN) {
                if (pytania == null) {
                    Pytania pytanie = new Pytania();
                    pytania = pytanie;
                    edycja = true;
                    if (aktualnaliczbaodpowiedzi == 0) {
                        loadingFXML(event, SceneFXML.TWORZENIE_ANKIETY);
                        PanelTworzeniaAnkietyController panelTworzeniaankietyController = load.getController();
                        pytania.setTresc(trescPytania.getText());
                        pytania.setRodzajPytania(rodzajPytania);
                        pytania.setPunktowe(punktowe);
                        pytania.setAnkiety(ankiety2);
                        lisaPytanPrzekazana.add(pytania);
                        panelTworzeniaankietyController.setListaPytan(lisaPytanPrzekazana);
                        panelTworzeniaankietyController.SetStart();
                        panelTworzeniaankietyController.setPytdoUsuniecia();
                        panelTworzeniaankietyController.DaneUsniecia(listaPytaU, listaOdpU);
                        panelTworzeniaankietyController.setStartValues(curetUser);
                        panelTworzeniaankietyController.setStartValuesAnkiety(ankiety2);
                        panelTworzeniaankietyController.SetEdycja(edycja2);
                        panelTworzeniaankietyController.setPytanieB(lisaPytanPrzekazana);
                        activeScene(event, false, false);
                    } else {
                        panelTworzeniaPytanLabelError.setText("Pytanie otwarte nie może zawierać odpowiedzi!");
                    }
                } else edycja = false;

            }
            if (!edycja) {

                if ((aktualnaliczbaodpowiedzi == 0 && dodawaniePytaniaRBQuestionOpen.isSelected()) ||
                        (aktualnaliczbaodpowiedzi > 1 &&
                                (dodawaniePytaniaRBQuestionCloseMoreThenOne.isSelected() ||
                                        dodawaniePytaniaRBQuestionCloseOnlyOne.isSelected() ||
                                        (dodawaniePytaniaRBQuestionPoints.isSelected() && punktyisnumber()))) ||
                        (aktualnaliczbaodpowiedzi == 1 && (dodawaniePytaniaRBQuestionPercentages.isSelected()))) {

                    pytania.setTresc(trescPytania.getText());
                    pytania.setRodzajPytania(rodzajPytania);
                    pytania.setPunktowe(punktowe);
                    pytania.setAnkiety(ankiety2);
                    pytania.setOdpowiedzis(listaOdpTego);
                    loadingFXML(event, SceneFXML.TWORZENIE_ANKIETY);
                    PanelTworzeniaAnkietyController panelTworzeniaankietyController = load.getController();
                    lisaPytanPrzekazana.add(pytania);
                    panelTworzeniaankietyController.setListaPytan(lisaPytanPrzekazana);
                    panelTworzeniaankietyController.SetStart();
                    panelTworzeniaankietyController.setPytdoUsuniecia();
                    panelTworzeniaankietyController.DaneUsniecia(listaPytaU, listaOdpU);
                    panelTworzeniaankietyController.setStartValues(curetUser);
                    panelTworzeniaankietyController.setStartValuesAnkiety(ankiety2);
                    panelTworzeniaankietyController.SetEdycja(edycja2);
                    panelTworzeniaankietyController.setPytanieB(lisaPytanPrzekazana);
                    activeScene(event, false, false);
                    try {
                        listaOdpU.addAll(listaOdpTego);
                        listaOdpTego = new HashSet();
                    } catch (NullPointerException e) {

                        panelTworzeniaPytanLabelError.setText("Pytanie otwarte nie może zawierać odpowiedzi!");
                    }
                } else {
                    if (dodawaniePytaniaRBQuestionOpen.isSelected() || dodawaniePytaniaRBQuestionPercentages.isSelected()) {
                        panelTworzeniaPytanLabelError.setText("To pytanie nie może mieć odpowiedzi!");
                    } else {
                        panelTworzeniaPytanLabelError.setText("To pytanie musi mieć przynajmniej 2 odpowiedzi!");
                    }

                }


            } else {
                if ((aktualnaliczbaodpowiedzi == 0 &&
                        (dodawaniePytaniaRBQuestionOpen.isSelected() ||
                                dodawaniePytaniaRBQuestionPercentages.isSelected())) ||
                        (aktualnaliczbaodpowiedzi > 1 &&
                                (dodawaniePytaniaRBQuestionCloseMoreThenOne.isSelected() ||
                                        dodawaniePytaniaRBQuestionCloseOnlyOne.isSelected() ||
                                        (dodawaniePytaniaRBQuestionPoints.isSelected() && punktyisnumber())))) {

                    if (edycja2) pytania.setIdPytania(-1);
                    pytania.setTresc(trescPytania.getText());
                    pytania.setZdjecie(zdjecie);
                    pytania.setRodzajPytania(rodzajPytania);
                    pytania.setPunktowe(punktowe);
                    pytania.setAnkiety(ankiety2);
                    pytania.setOdpowiedzis(listaOdpTego);
                    loadingFXML(event, SceneFXML.TWORZENIE_ANKIETY);
                    PanelTworzeniaAnkietyController panelTworzeniaankietyController = load.getController();
                    lisaPytanPrzekazana.add(pytania);
                    panelTworzeniaankietyController.setListaPytan(lisaPytanPrzekazana);
                    panelTworzeniaankietyController.SetStart();
                    panelTworzeniaankietyController.setPytdoUsuniecia();
                    panelTworzeniaankietyController.DaneUsniecia(listaPytaU, listaOdpU);
                    panelTworzeniaankietyController.setStartValues(curetUser);
                    panelTworzeniaankietyController.setStartValuesAnkiety(ankiety2);
                    panelTworzeniaankietyController.SetEdycja(edycja2);
                    activeScene(event, false, false);
                } else {
                    if (dodawaniePytaniaRBQuestionOpen.isSelected() || dodawaniePytaniaRBQuestionPercentages.isSelected()) {
                        panelTworzeniaPytanLabelError.setText("To pytanie nie może mieć odpowiedzi!");
                    } else {
                        panelTworzeniaPytanLabelError.setText("To pytanie musi mieć przynajmniej 2 odpowiedzi!");
                    }

                }


            }

        } else {
            panelTworzeniaPytanLabelError.setText("Tytuł pytania nie może być pusty!");
        }
    }

    /**
     * Metoda obsługująca przycisk zapisz ustawiajaca wartości dla rodzaju pytania, oraz ilość punktów do rozdania w pytaniu.
     * @param event zdarzenie, po którym funkcja ma się wywołać
     */

    @FXML
    void dodajpytanieAction(ActionEvent event) {


        if (dodawaniePytaniaRBQuestionOpen.isSelected() || dodawaniePytaniaRBQuestionPercentages.isSelected()) {
            if (aktualnaliczbaodpowiedzi <= 0) {
                rodzajPytania = TypeOfQuestion.OPEN;

                punktowe = 0;
                dodajPytanie(event);
            } else {
                panelTworzeniaPytanLabelError.setText("To pytanie nie może zawierać odpowiedzi!");
            }
        } else {
            if (dodawaniePytaniaRBQuestionCloseMoreThenOne.isSelected()) {
                rodzajPytania = TypeOfQuestion.MANY_CHOICE;
                punktowe = 0;
                dodajPytanie(event);
            } else {
                if (dodawaniePytaniaRBQuestionCloseOnlyOne.isSelected()) {
                    rodzajPytania = TypeOfQuestion.ONE_CHOICE;
                    punktowe = 0;
                    dodajPytanie(event);
                } else {
                    if (dodawaniePytaniaRBQuestionPercentages.isSelected()) {
                        rodzajPytania = TypeOfQuestion.PERCENT;
                        punktowe = Integer.parseInt("100");
                        dodajPytanie(event);
                    } else {
                        if (dodawaniePytaniaRBQuestionPoints.isSelected()) {
                            rodzajPytania = TypeOfQuestion.POINTS;
                            if (!punkty.getText().isEmpty()) {
                                try {

                                    punktowe = Integer.parseInt(punkty.getText());
                                    if (punktowe >= 0) {
                                        dodajPytanie(event);
                                    } else panelTworzeniaPytanLabelError.setText("Punkty muszą być większe od 0!");
                                } catch (Exception e) {
                                    panelTworzeniaPytanLabelError.setText("Punkty muszą być liczbą!");
                                }

                            } else {
                                panelTworzeniaPytanLabelError.setText("Podaj liczbę punków!");
                            }

                        }
                    }
                }
            }
        }


    }


    @FXML
    void initialize() {
        assert dodajzdjecie != null : "fx:id=\"dodajzdjecie\" was not injected: check your FXML file 'DodawaniePytania.fxml'.";
        assert wyloguj1 != null : "fx:id=\"wyloguj1\" was not injected: check your FXML file 'DodawaniePytania.fxml'.";
        assert anuluj != null : "fx:id=\"anuluj\" was not injected: check your FXML file 'DodawaniePytania.fxml'.";
        assert imageview != null : "fx:id=\"imageview\" was not injected: check your FXML file 'DodawaniePytania.fxml'.";
        assert dodawaniePytaniaRBQuestionOpen != null : "fx:id=\"dodawaniePytaniaRBQuestionOpen\" was not injected: check your FXML file 'DodawaniePytania.fxml'.";
        assert dodawaniePytaniaRBQuestionCloseMoreThenOne != null : "fx:id=\"dodawaniePytaniaRBQuestionCloseMoreThenOne\" was not injected: check your FXML file 'DodawaniePytania.fxml'.";
        assert dodawaniePytaniaRBQuestionCloseOnlyOne != null : "fx:id=\"dodawaniePytaniaRBQuestionCloseOnlyOne\" was not injected: check your FXML file 'DodawaniePytania.fxml'.";
        assert dodawaniePytaniaRBQuestionPercentages != null : "fx:id=\"dodawaniePytaniaRBQuestionPercentages\" was not injected: check your FXML file 'DodawaniePytania.fxml'.";
        assert dodawaniePytaniaRBQuestionPoints != null : "fx:id=\"dodawaniePytaniaRBQuestionPoints\" was not injected: check your FXML file 'DodawaniePytania.fxml'.";
        dodawaniePytaniaRBQuestionOpen.setToggleGroup(radioButtonGroup);
        dodawaniePytaniaRBQuestionCloseMoreThenOne.setToggleGroup(radioButtonGroup);
        dodawaniePytaniaRBQuestionCloseOnlyOne.setToggleGroup(radioButtonGroup);
        dodawaniePytaniaRBQuestionPercentages.setToggleGroup(radioButtonGroup);
        dodawaniePytaniaRBQuestionPoints.setToggleGroup(radioButtonGroup);
        dodawaniePytaniaRBQuestionCloseOnlyOne.setSelected(true);


    }

    /**
     * Metoda obsługująca przycisk dodaj, czyli dodanie obietku odpowiedz do listy odpowiedzi.
     * @param event zdarzenie, po którym funkcja ma się wywołać
     */

    public void dodajOdpAction(ActionEvent event) {
        if (!dodawaniePytaniaRBQuestionOpen.isSelected()) {
            odp = odpowiedzi.getText();
            if (!odp.isEmpty()) {
                if (pytania == null) {

                    Pytania pytanie = new Pytania();
                    pytanie.initHashSetOdpowiedzi();
                    Odpowiedzi odpo = new Odpowiedzi(pytania, odp);
                    odpo.setIdOdpowiedzi(-1);
                    pytanie.setIdPytania(-1);
                    pytania = pytanie;
                    odpo.setPytania(pytania);
                    listaOdpTego.add(odpo);
                    edycja = true;


                } else {
                    Odpowiedzi odpo = new Odpowiedzi(pytania, odp);
                    odpo.setIdOdpowiedzi(-1);
                    pytania.setIdPytania(-1);
                    edycja = true;
                    listaOdpTego.add(odpo);
                }

                setOdpowiedziSS(listaOdpTego);
            } else {
                panelTworzeniaPytanLabelError.setText("Podaj treść odpowiedzi!");


            }

        } else {
            panelTworzeniaPytanLabelError.setText("Pytanie otwarte nie może zawierać odpowiedzi!");


        }

    }

    /**
     * Metoda ustawiająca wartość sprawdzającą czy dana ankietajest nowa czy edytowana.
     */

    public void Edycja(Boolean e) {
        this.edycja = e;
    }

    /**
     * Metoda wypisująca liste odpowiedzi w panelu dodawania pytania.
     * @param listaOdpTego lista odpowiedzi.
     */

    public void setOdpowiedziSS(Set<Odpowiedzi> listaOdpTego) {
        ObservableList<OdpowiedziTabelka> dane = FXCollections.observableArrayList();
        listaOdpTego.forEach(odpowiedz -> {
            Odpowiedzi JednaOdp = (Odpowiedzi) odpowiedz;
            dane.add(new OdpowiedziTabelka(JednaOdp, listaOdpU, listaPytaU, listaOdpTego, this));
        });
        odpowiedziTabelka.itemsProperty().setValue(dane);
        tresc.setCellValueFactory(new PropertyValueFactory("tresc"));
        przyciskUsun.setCellValueFactory(new PropertyValueFactory("buttonUsun"));
        aktualnaliczbaodpowiedzi = dane.size();

    }

    /**
     * Metoda służąca zapisania list pytań i odpowiedzi do usunięcia.
     */

    public void DaneUsniecia(List<Pytania> pyt, List<Odpowiedzi> odp) {
        this.listaPytaU = pyt;
        this.listaOdpU = odp;
    }

}

