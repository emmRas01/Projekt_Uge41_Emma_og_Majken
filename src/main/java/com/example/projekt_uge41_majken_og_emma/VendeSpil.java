package com.example.projekt_uge41_majken_og_emma;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class VendeSpil extends Application
{
    private Brik[][] brikker; //opretter et 2D array. Alle brikker holdes i arrayet.
    private Brik vendtBrik1 = null; //variabel der bruges til at holde styr på hvilke brikker der er vendt
    private Brik vendtBrik2 = null;

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

        //rektangel som knap
        Rectangle rect = new Rectangle(245, 600, 100, 40);
        rect.setFill(Color.BLACK);
        //tekst oven på vores rektangel / knap
        Text knapTekst = new Text("Reset");
        knapTekst.setFill(Color.WHITE);
        knapTekst.setX(280);
        knapTekst.setY(625);

        rect.setOnMouseClicked(event -> restart()); //ved klik på rectangel starter restart-metoden
        knapTekst.setOnMouseClicked(event -> restart()); //ved klik på knapTekst starter restart-metoden

        scenegraf.getChildren().addAll(rect, knapTekst); //rectangel og tekst på knappen vises på scenen

        // Sæt scenen op
        Scene scene = new Scene(scenegraf, 590, 650);
        stage.setTitle("Vende Spil :)");
        stage.setScene(scene);
        stage.show();
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

    }

    public static void main(String[] args) {
        launch();
    }
}