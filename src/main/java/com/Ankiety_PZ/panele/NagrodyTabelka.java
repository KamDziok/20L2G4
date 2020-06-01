package com.Ankiety_PZ.panele;

import com.Ankiety_PZ.hibernate.Nagrody;
import com.Ankiety_PZ.hibernate.Uzytkownicy;
import com.Ankiety_PZ.query.NagrodyQuery;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

/**
 * Klasa obsługuje tabelę nagród w panelu osoby odpowiedzialnej za nagrody.
 */

public class NagrodyTabelka extends BulidStage {

    /**
     * Nazwa nagrody
     */

    public String tytul;

    /**
     * Liczba punktów dla nagrody
     */

    public int liczbaPunktow;

    /**
     * Ilustracja dla nagrody
     */

    private ImageView zdjecie;

    /**
     * Przycisk do usunięcia nagrody
     */

    public Button usun;

    /**
     * Przycisk do edycji nagrody
     */

    public Button edytuj;

    /**
     * Obiekt nagrody
     */

    private Nagrody nagroda;

    /**
     * Obiekt uzytkownika
     */

    private Uzytkownicy user;

    /**
     * Metoda ustawia pojedynczą nagrodę w tabeli nagród.
     * Metoda obsługuje również akcje usuwania nagród przyciskiem <code>usuń</code>
     * oraz edycji nagród przyciskiem <code>edytuj</code>.
     *
     * @param nagroda    obiekt nagroda do wypisania w tabeli.
     * @param uzytkownik obiekt użytkownika.
     * @param panel      PanelOsobyOdNagrodController.
     */

    NagrodyTabelka(Nagrody nagroda, Uzytkownicy uzytkownik, PanelOsobyOdNagrodController panel) {
        this.nagroda = nagroda;
        this.user = uzytkownik;
        tytul = nagroda.getNazwa();
        liczbaPunktow = nagroda.getLiczbaPunktow();

        if (null != nagroda.getZdjecie()) {
            try {
                conversjaNaZ(nagroda.getZdjecie(), nagroda);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        usun = new Button("Usuń");
        edytuj = new Button("Edytuj");
        usun.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                NagrodyQuery usun = new NagrodyQuery();
                usun.deactivateNagrody(nagroda);
                panel.setNagrody();
            }
        });
        edytuj.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                loadingFXML(event, SceneFXML.PANEL_EDIT_NAGROD);
                PanelEdycjiNagrodController panelEdycjiNagrodController = load.getController();
                panelEdycjiNagrodController.setStartValues(uzytkownik);
                panelEdycjiNagrodController.setStartValuesNagroda(nagroda);
                activeScene(event, false, false);
            }
        });
    }


    public String getTytul() {
        return tytul;
    }

    public int getLiczbaPunktow() {
        return liczbaPunktow;
    }

    public ImageView getZdjecie() {
        return zdjecie;
    }

    public Button getUsun() {
        return usun;
    }

    public Button getEdytuj() {
        return edytuj;
    }

    /**
     * Metoda konwertuje tablicę byte na Image
     *
     * @param bytes   tablica byte ze zdjęciem w formacie jpg.
     * @param nagrody obiekt nagrody.
     */

    public void conversjaNaZ(byte[] bytes, Nagrody nagrody) throws IOException {
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
        zdjecie = new ImageView();
        zdjecie.setImage(image2);
        zdjecie.setFitWidth(320);
        zdjecie.setFitHeight(180);
        System.out.println(bytes);
        System.out.println(zdjecie);
    }
}

