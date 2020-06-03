package com.Ankiety_PZ.panele;

import com.Ankiety_PZ.hibernate.Ankiety;
import com.Ankiety_PZ.hibernate.Nagrody;
import com.Ankiety_PZ.hibernate.Pytania;
import com.Ankiety_PZ.hibernate.Uzytkownicy;
import com.Ankiety_PZ.query.NagrodyQuery;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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
import java.util.Iterator;

/**
 * Klasa odpowiedzialna za dodawanie i edycję nagród.
 */

public class PanelEdycjiNagrodController extends BulidStage implements SetStartValues {


    /**
     * Nagroda do edycji
     */

    private Nagrody nagrody;

    /**
     * Aktualnie zalogowany użytkownik
     */

    private Uzytkownicy curentUser;

    /**
     * Liczba punktów dla nagrody
     */

    int liczba_punktow;

    /**
     * Liczba punktów dla nagrody, napis
     */

    String liczba_punktowS;

    /**
     * Nazwa nagrody
     */

    String nazwa_nagrody;

    /**
     * Plik dla zdjęcia
     */

    File file;

    /**
     * Przycisk wylogowywania
     */

    @FXML
    private Button wyloguj;

    /**
     * Przycisk zapisywania zmian lub dodwania nagrody
     */

    @FXML
    private Button panelEdycjiNagrodButtonEdytuj;

    /**
     * Przycisk anulowania zmian lub dodwania nagrody
     */

    @FXML
    private Button panelEdycjiNagrodButtonAnuluj;

    /**
     * Pole tekstowe z nazwą nagrody
     */

    @FXML
    private TextField nag;

    /**
     * Pole tekstowe z liczbą punktów za nagrodę
     */

    @FXML
    private TextField pkt;

    /**
     * Widok zdjęcia dla nagrody
     */

    @FXML
    private ImageView imageview;

    /**
     * Etykieta z aktualnym imieniem, nazwiskiem i rolą użytkownika
     */

    @FXML
    private Label imie_nazwisko_rola;

    /**
     * Etykieta informująca o rodzaju błędu przy edycji nagrody.
     */

    @FXML
    private Label panelEdycjiNagrodLabelError;

    /**
     * Tablica byte przechowująca zdjęcie z bazy.
     */

    private byte[] zdjecie;

    @FXML
    private Button PanelEdycjiNagrod;

    /**
     * Metoda wykonująca akcję wylogowowania użytkownika po naciśnięciu przycisku <code>wyloguj</code>.
     *
     * @param event zdarzenie, po którym funkcja ma się wywołać.
     */

    @FXML
    void wyloguj(ActionEvent event) {
        loadingFXML(event, SceneFXML.PANEL_LOGIN);
        activeScene(event, false, false);
    }

    /**
     * Metoda zmienia nazwę przycisku w przypadku dodawania a nie edycji nagrody.
     */

    void ustawZapisz() {
        panelEdycjiNagrodButtonEdytuj.setText("Dodaj");
    }

    /**
     * Metoda wykonująca akcję uruchomienia okna wyboru zdjęcia po naciśnięciu przycisku <code>dodaj zdjęcie</code>.
     *
     * @param event zdarzenie, po którym funkcja ma się wywołać.
     */

    @FXML
    void panelEdycjiNagrodButtonDodajZdjecie(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Wybierz zdjęcie");
        Stage stage = new Stage();
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Obraz", "*jpg"));
        file = fileChooser.showOpenDialog(stage);
        try {
            Image image = new Image(file.toURI().toString());
            System.out.println(file.toURI().toString());
            System.out.println(file.toURI().toString());
            imageview.setImage(image);
            conversja(file);
        } catch (IllegalArgumentException argumentException) {
            System.out.println("Nie wybrałeś zdjęcia lub rozszerzenie nie jest obsługiwane. " + argumentException.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Metoda wykonująca akcję uruchomienia okna wyboru zdjęcia po naciśnięciu przycisku <code>dodaj zdjęcie</code>.
     *
     * @param file plik wczytany za pomocą {@link #panelEdycjiNagrodButtonDodajZdjecie(ActionEvent)}
     * @throws  FileNotFoundException Brak pliku
     * @throws  IllegalArgumentException Błedne dane
     * @throws  IOException Błąd odczytu
     */

    public void conversja(File file) throws FileNotFoundException, IOException {
        FileInputStream fis = new FileInputStream(file);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buf = new byte[1024];
        try {
            for (int readNum; (readNum = fis.read(buf)) != -1; ) {
                bos.write(buf, 0, readNum);
                System.out.println("read " + readNum + " bytes,");
            }
        } catch (IOException ex) {
        }

        byte[] bytes = bos.toByteArray();
        zdjecie = bytes;
        nagrody.setZdjecie(bytes);
        nagrody.setZdjecie(bytes);
        NagrodyQuery query3 = new NagrodyQuery();
        query3.updateNagrody(nagrody);

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
        if (!(new File(directory).exists())) {
            new File(directory).mkdir();
        }
        Graphics2D g2 = bufferedImage.createGraphics();
        g2.drawImage(image, null, null);
        File imageFile = new File(directory + "\\" + nagrody.getIdNagrody());
        ImageIO.write(bufferedImage, "jpg", imageFile);
        Image image2 = new Image(imageFile.toURI().toString());
        imageview.setImage(image2);
        System.out.println(imageFile);
    }

    /**
     * Metoda wykonująca akcję powrotu do panelu nagród za pomocą przycisku <code>anuluj</code>.
     *
     * @param event zdarzenie, po którym funkcja ma się wywołać.
     */

    @FXML
    void panelEdycjiNagrodButtonAnuluj(ActionEvent event) {
        loadingFXML(event, SceneFXML.PANEL_NAGROD);
        PanelOsobyOdNagrodController panelOsobyOdNagrodController = load.getController();
        panelOsobyOdNagrodController.setStartValues(curentUser);
        activeScene(event, false, false);
    }

    /**
     * Metoda sprawdzenie czy obowiązkowe pola nie są puste.
     *
     * @return true jeśli wszystkie pola obowiązkowe są uzupełnione, w przeciwnym wypadku false
     */

    private boolean compulsoryFildNotNull() {
        return (!liczba_punktowS.isEmpty() && !nazwa_nagrody.isEmpty());
    }

    /**
     * Metoda sprawdza czy liczba punktów składa się z liczb i czy jest większa lub równa 0.
     *
     * @return true jeśli liczba punków jest poprawna, w przeciwnym razie false
     */

    private boolean pktIsNumber() {
        try {
            liczba_punktow = Integer.parseInt(liczba_punktowS);
            if (liczba_punktow >= 0) {
                return true;
            } else {
                return false;
            }
        } catch (IllegalArgumentException argumentException) {
            panelEdycjiNagrodLabelError.setText("Liczba punktów zawiera niepoprawne znaki!");
            System.out.println(argumentException.getMessage());
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
        return false;
    }

    /**
     * Metoda wykonująca dodawania nagrody lub edycji po wybraniu odpowiedniego przycisku.
     * W przypadku  edycji wykona się blok try, a przy dodawaniu blok catch i utworzy nowy obiekt nagrody.
     * @param event zdarzenie, po którym funkcja ma się wywołać.
     */

    @FXML
    void panelEdycjiNagrodButtonEdytuj(ActionEvent event) {

        liczba_punktowS = pkt.getText();
        nazwa_nagrody = nag.getText();
        if (compulsoryFildNotNull()) {
            if (pktIsNumber()) {
                try {

                    NagrodyQuery zapisz = new NagrodyQuery();
                    nagrody.setLiczbaPunktow(liczba_punktow);
                    nagrody.setNazwa(nazwa_nagrody);
                    zapisz.updateNagrody(nagrody);
                } catch (RuntimeException wyjatek) {

                    Nagrody nagroda = new Nagrody(liczba_punktow, nazwa_nagrody, zdjecie);
                    NagrodyQuery dodaj = new NagrodyQuery();
                    dodaj.addNagrody(nagroda);
                } finally {
                    loadingFXML(event, SceneFXML.PANEL_NAGROD);
                    PanelOsobyOdNagrodController panelOsobyOdNagrodController = load.getController();
                    panelOsobyOdNagrodController.setStartValues(curentUser);
                    activeScene(event, false, false);
                }
            } else {

                panelEdycjiNagrodLabelError.setText("Podaj poprawną liczbę punków!");
            }
        } else {
            panelEdycjiNagrodLabelError.setText("Wymagane pola są puste!");
        }
    }

    /**
     * Metoda wykonuje inicjalizację aktualnych danych użytkownika.
     */

    @Override
    public void setStartValues(Uzytkownicy user) {
        this.curentUser = user;
        String imie_nazwisko_rola_tmp = curentUser.getImie() + " " + curentUser.getNazwisko() + " - konto zarządzania nagrodami";
        System.out.print(imie_nazwisko_rola_tmp);
        imie_nazwisko_rola.setText(imie_nazwisko_rola_tmp);
    }

    @Override
    public void setStartValuesAnkiety(Ankiety ankieta) {


    }

    @Override
    public void setStartValuesPytanie(Pytania pytania) {

    }

    /**
     * Metoda wykonuje inicjalizację aktualnie edytowanej nagrody.
     */

    @Override
    public void setStartValuesNagroda(Nagrody nagroda) {

        this.nagrody = nagroda;
        nag.setText(nagroda.getNazwa());
        pkt.setText(nagroda.getLiczbaPunktow() + "");
        if (nagroda.getZdjecie() != null) {
            try {
                conversjaNaZ(nagroda.getZdjecie());
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    @FXML
    void initialize() {
        assert PanelEdycjiNagrod != null : "fx:id=\"panelEdycjiNagrodButtonDodajZdjecie\" was not injected: check your FXML file 'PanelEdycjiNagrod.fxml'.";
        assert panelEdycjiNagrodButtonEdytuj != null : "fx:id=\"panelEdycjiNagrodButtonUsun\" was not injected: check your FXML file 'PanelEdycjiNagrod.fxml'.";
        assert panelEdycjiNagrodButtonAnuluj != null : "fx:id=\"panelEdycjiNagrodButtonAnuluj\" was not injected: check your FXML file 'PanelEdycjiNagrod.fxml'.";
        assert wyloguj != null : "fx:id=\"wyloguj\" was not injected: check your FXML file 'PanelEdycjiNagrod.fxml'.";
        assert imie_nazwisko_rola != null : "fx:id=\"imie_nazwisko_rola\" was not injected: check your FXML file 'PanelEdycjiNagrod.fxml'.";

    }

}
