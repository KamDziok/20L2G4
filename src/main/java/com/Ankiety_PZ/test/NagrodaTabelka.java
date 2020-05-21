package com.Ankiety_PZ.test;

import com.Ankiety_PZ.hibernate.Nagrody;
import com.Ankiety_PZ.hibernate.Uzytkownicy;
import com.Ankiety_PZ.query.NagrodyQuery;
import com.Ankiety_PZ.query.UzytkownicyQuery;
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

public class NagrodaTabelka {
    private String nazwa;
    private int cena;
    private ImageView obrazek;
    private Button button;

    NagrodaTabelka(Nagrody nagroda, PanelUzytkownikaController controller) {
        nazwa = nagroda.getNazwa();
        cena = nagroda.getLiczbaPunktow();
        if(null != nagroda.getZdjecie()) {
            try {
                conversjaNaZ(nagroda.getZdjecie(), nagroda);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        button = new Button("Wymień");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Uzytkownicy curentUser = controller.getCurentUser();
                NagrodyQuery queryNagrody = new NagrodyQuery();
                if (queryNagrody.getNagrodyToUzytkownicy(nagroda, curentUser)) {
                        UzytkownicyQuery query = new UzytkownicyQuery();
                        query.updateUzytkownicy(curentUser);
                        controller.updatePkt(String.valueOf(curentUser.getLiczbaPunktow()));
                        controller.getPanelUzytkownikaLabelErrorNagrody().setText("Nagroda dodana pomyslnie");
                } else {
                    controller.getPanelUzytkownikaLabelErrorNagrody().setText("Dodanie nagrody nie powiodło się");
                }
            }
        });
    }

    public String getNazwa() {
        return nazwa;
    }

    public int getCena() {
        return cena;
    }

    public ImageView getObrazek() {
        return obrazek;
    }

    public Button getButton() {
        return button;
    }
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
        String directory2 = directory + "\\pdf";
        if(!(new File(directory).exists())){
            new File(directory).mkdir();
        }
        Graphics2D g2 = bufferedImage.createGraphics();
        g2.drawImage(image, null, null);
        File imageFile = new File(directory + "\\" + nagrody.getIdNagrody());
        ImageIO.write(bufferedImage, "jpg", imageFile);
        javafx.scene.image.Image image2 = new Image(imageFile.toURI().toString());

        obrazek = new ImageView();
        obrazek.setImage(image2);
        obrazek.setFitWidth(320);
        obrazek.setFitHeight(180);
        System.out.println(bytes);
        System.out.println(obrazek);


    }
}
