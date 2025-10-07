package com.example.projekt_uge41_majken_og_emma;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.Random;

public class VendeSpil extends Application
{
    private Brik[][] brikker; //opretter et 2D array. Alle brikker holdes i arrayet.
    private Brik vendtBrik1 = null; //variabel der bruges til at holde styr på hvilke brikker der er vendt
    private Brik vendtBrik2 = null;
    private int antalStik = 0;
    private Text antalStikTekst;

    private String[] brikListe = { //indeholder navnene på hvert brik/billede
            "brik3.png", "brik16.png", "brik9.png", "brik18.png", "brik15.png", "brik8.png",
            "brik14.png", "brik11.png", "brik1.png", "brik4.png", "brik7.png", "brik12.png",
            "brik1.png", "brik12.png", "brik2.png", "brik9.png", "brik10.png", "brik5.png",
            "brik2.png", "brik10.png", "brik7.png", "brik6.png", "brik14.png", "brik11.png",
            "brik18.png", "brik6.png", "brik4.png", "brik5.png", "brik3.png", "brik17.png",
            "brik15.png", "brik17.png", "brik13.png", "brik16.png", "brik8.png", "brik13.png",
    };

    public void start(Stage stage) throws IOException {
        Pane scenegraf = new Pane(); //opretter en scene

        java.util.Collections.shuffle(java.util.Arrays.asList(brikListe)); //bland brikkerne

        //Her sættes banen op i et 6x6 gitter
        brikker = new Brik[6][6];
        int t = 0; //vi laver en tæller til at lave en loop, så alle 36 brikker får et unikt billed.
        for (int i = 0; i < 6; i++)
            for (int j = 0; j < 6; j++) {
                brikker[i][j] = new Brik(i, j, brikListe[t]); //Lav hver brik som et objekt "Brik" med position og billede.
                scenegraf.getChildren().add(brikker[i][j]); //Tilføj brikken til scenegrafen
                brikker[i][j].setOnMouseClicked(e -> klik(e)); //Tilføj musse-klik til brikken
                ++t; //tælleren stiger med 1 for hver brik, der bliver lavet.
            }

        //opretter rektangel -> bruger vi senere som knap
        Rectangle rect = new Rectangle(245, 600, 100, 40);
        rect.setFill(Color.GREY);
        //opretter tekst oven på vores rektangel / knap
        Text knapTekst = new Text("Reset");
        knapTekst.setFill(Color.WHITE);
        knapTekst.setFont(Font.font("Cambria", 20)); //teksttype og tekst størrelse
        knapTekst.setX(271);
        knapTekst.setY(627);

        //opretter antal stik som tekst
        antalStikTekst = new Text("Antal Stik: " + antalStik);
        antalStikTekst.setFill(Color.BLACK);
        antalStikTekst.setFont(Font.font("Cambria", 20)); //teksttype og tekst størrelse
        antalStikTekst.setX(25);
        antalStikTekst.setY(625);

        rect.setOnMouseClicked(event -> restart()); //ved klik på rectangel starter restart-metoden
        knapTekst.setOnMouseClicked(event -> restart()); //ved klik på knapTekst starter restart-metoden

        scenegraf.getChildren().addAll(rect, knapTekst, antalStikTekst); //rectangel og tekst på knappen vises på scenen

        // Sæt scenen op
        Scene scene = new Scene(scenegraf, 590, 650);
        stage.setTitle("Vende Spil :)");
        stage.setScene(scene);
        stage.show();
    }

    // blander et array af int i tilfældig rækkeflge
    public void blandBrikker(int[] brikliste) {
        Random rand = new Random();
        for (int i = 0; i < brikListe.length; i++) {
            int randomIndexToSwap = rand.nextInt(brikListe.length);
            String temp = String.valueOf(Integer.parseInt(brikListe[randomIndexToSwap]));
            brikListe[randomIndexToSwap] = brikListe[i];
            brikListe[i] = temp;
        }
    }

    public void klik(MouseEvent e)
    {
        Brik b = (Brik) e.getSource(); //finder ud af hvilken brik der blev klikket på

        if (b == vendtBrik1 || b == vendtBrik2) return; //tjek om brikken allerede er blevet vendt.
        //man kan ikke vende brikker der allerede er vendt.
        //Første brik
        if (vendtBrik1 == null) //er der ikke vendt en brik endnu? -> Så er det første brik.
        {
            vendtBrik1 = b;     //ved 1. klik gemmes brikken som "vendtBrik1"
            vendtBrik1.vend();  //vender brikken, så billedet kan ses.
        } else { //der er allerede vendt en brik -> så er det anden brik.
            vendtBrik2 = b;    //ved 2. klik gemmes brikken som "vendtBrik2"
            vendtBrik2.vend(); //vender brikken, så billedet kan ses.

            if (vendtBrik1.getBriknavn().equals(vendtBrik2.getBriknavn())) //det er et match
            // den henter navnet på begge billeder og tjekker om de er equals.
            {
                antalStik++; //tilføjer 1 point når kortene er et match
                antalStikTekst.setText("Antal Stik: " + antalStik);

                PauseTransition pause = new PauseTransition(Duration.seconds(0.5));
                //vi bruger PauseTransition til at give spilleren 0.5 sec til at se brikkerne inden de forsvinder.
                pause.setOnFinished(event -> //efter de 0,5 sek er gået skal følgende ske
                {
                    vendtBrik1.setVisible(false); //gør brikken usynlig
                    vendtBrik2.setVisible(false); //gør brikken usynlig
                    vendtBrik1 = null; //nulstiller, så vi kan klikke på flere brikker
                    vendtBrik2 = null; //nulstiller, så vi kan klikke på flere brikker
                });
                pause.play(); //spillet pauses, så setOnFinished starter 0,5 sek efter.
            } else { //det er ikke et match
                PauseTransition pause = new PauseTransition(Duration.seconds(0.5));
                //vi bruger PauseTransition til at give spilleren 0.5 sec til at se brikkerne inden de vendes til bagsiden.
                pause.setOnFinished(event -> //efter de 0,5 sek er gået skal følgende ske
                {
                    vendtBrik1.setImage(new Image(getClass().getResource("bagside.png").toString())); //brikkerne vendes til bagsiden
                    vendtBrik2.setImage(new Image(getClass().getResource("bagside.png").toString())); //brikkerne vendes til bagsiden
                    vendtBrik1 = null; //nulstiller, så vi kan klikke på 2 nye brikker
                    vendtBrik2 = null; //nulstiller, så vi kan klikke på 2 nye brikker
                });
                pause.play();
            }
        }
    }

    public void restart() //ved klik på rectangel starter denne metode
    {
        antalStik = 0; //nulstiller antalStik
        antalStikTekst.setText("Antal Stik: " + antalStik); //nulstiller teksten på scenen
        vendtBrik1 = null; //nulstiller brik 1
        vendtBrik2 = null; //nulstiller brik 2

        java.util.Collections.shuffle(java.util.Arrays.asList(brikListe)); //bland brikkerne igen

        //kode der gennemløber alle brikkerne og nulstiller dem
        int t = 0;
        for (int i = 0; i < 6; i++) //gennemgår rækkerne
            for (int j = 0; j < 6; j++) //gennemgår kolonnerne
            {
                brikker[i][j].setVisible(true); //forsvunde brikker bliver synlige igen
                brikker[i][j].setImage(new Image(getClass().getResource("bagside.png").toString())); //starter med bagsiden opad
                brikker[i][j].setBriknavn(brikListe[t]); //resetter briknavnet
                t++; //tæller til at køre det i et loop
            }
    }

    public static void main(String[] args) {
        launch();
    }
}