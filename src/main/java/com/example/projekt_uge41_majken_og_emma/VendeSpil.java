package com.example.projekt_uge41_majken_og_emma;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class VendeSpil extends Application
{
    private Brik[][] brikker; //opretter et 2D array. Alle brikker holdes i arrayet.

    private Brik vendtBrik1 = null; //variabel der bruges til at holde styr på hvilke brikker der er vendt
    private Brik vendtBrik2 = null; //variabel der bruges til at holde styr på hvilke brikker der er vendt

    private int antalStik = 0; //tæller der holder styr på antal stik
    private Text antalStikTekst; //variabel der indeholder teksten til antal stik
    private int antalTraek = 0; //tæller der holder styr på antal træk
    private Text antalTraekTekst; //variabel der indeholder teksten til antal træk
    private Text vinderTekst; //variabel der indeholder teksten til vinderen

    private Pane scenegraf; //variabel der indeholder vores grafiske elementer.
                            // Placeres her så metoden baneOpsaetning kan bruge den.

    private String[] brikListe = { //indeholder navnene på hvert brik/billede
            "brik1.png", "brik1.png", "brik2.png", "brik2.png", "brik3.png", "brik3.png",
            "brik4.png", "brik4.png", "brik5.png", "brik5.png", "brik6.png", "brik6.png",
            "brik7.png", "brik7.png", "brik8.png", "brik8.png", "brik9.png", "brik9.png",
            "brik10.png", "brik10.png", "brik11.png", "brik11.png", "brik12.png", "brik12.png",
            "brik13.png", "brik13.png", "brik14.png", "brik14.png", "brik15.png", "brik15.png",
            "brik16.png", "brik16.png", "brik17.png", "brik17.png", "brik18.png", "brik18.png",
    };

    public void start(Stage stage) throws IOException
    {
        scenegraf = new Pane(); //opretter en scene

        baneOpsaetning(); //sætter banen op

        //opretter rektangel -> bruger vi senere som knap
        Rectangle rect = new Rectangle(245, 600, 100, 40);
        rect.setFill(Color.GREY);

        //opretter tekst oven på vores rektangel / knap
        Text knapTekst = new Text("Reset");
        knapTekst.setFill(Color.WHITE);
        knapTekst.setFont(Font.font("Cambria", FontWeight.BOLD,20)); //teksttype og tekst størrelse
        knapTekst.setX(271);
        knapTekst.setY(627);

        //opretter antal stik som tekst
        antalStikTekst = new Text("Antal Stik: " + antalStik);
        antalStikTekst.setFill(Color.BLACK);
        antalStikTekst.setFont(Font.font("Constantia", FontWeight.BOLD, 20)); //teksttype og tekst størrelse
        antalStikTekst.setX(445);
        antalStikTekst.setY(625);

        //opretter antal træk som tekst
        antalTraekTekst = new Text("Antal Træk: " + antalTraek);
        antalTraekTekst.setFill(Color.BLACK);
        antalTraekTekst.setFont(Font.font("Constantia", FontWeight.BOLD, 20)); //teksttype og tekst størrelse
        antalTraekTekst.setX(25);
        antalTraekTekst.setY(625);

        //opretter vinder som tekst
        vinderTekst = new Text("DU HAR VUNDET!");
        vinderTekst.setFill(Color.GOLD);
        vinderTekst.setFont(Font.font("Jokerman", FontWeight.BOLD, 50)); //teksttype og tekst størrelse
        vinderTekst.setX(80);
        vinderTekst.setY(305);

        rect.setOnMouseClicked(event -> restart()); //ved klik på rectangel starter restart-metoden
        knapTekst.setOnMouseClicked(event -> restart()); //ved klik på knapTekst starter restart-metoden

        scenegraf.getChildren().addAll(rect, knapTekst, antalStikTekst, antalTraekTekst); //rectangel og tekst vises på scenen

        // Sæt scenen op
        Scene scene = new Scene(scenegraf, 590, 650); //opretter en ny scene
        stage.setTitle("Vende Spil :)"); //titlen på vores vindue/stage sættes
        stage.setScene(scene); //scenen placeres i vinduet/stage
        stage.show(); //viser vinduet/stage
    }

    public void baneOpsaetning() //metode der placere vores brikker
    {
        java.util.Collections.shuffle(java.util.Arrays.asList(brikListe)); //blander brikkerne tilfældigt
        brikker = new Brik[6][6]; //Her sættes banen op i et 6x6 gitter
        int t = 0; //vi laver en tæller til at lave en loop, så alle 36 brikker får et unikt billed.
        for (int i = 0; i < 6; i++) //løber igennem rækkerne
            for (int j = 0; j < 6; j++) //løber igennem kolonnerne
            {
                brikker[i][j] = new Brik(i, j, brikListe[t]); //Lav hver brik som et objekt "Brik" med position og billede.
                scenegraf.getChildren().add(brikker[i][j]); //Tilføj brikken til scenegrafen
                brikker[i][j].setOnMouseClicked(e -> klik(e)); //Tilføj musse-klik til brikken
                ++t; //tælleren stiger med 1 for hver brik, der bliver lavet.
            }
    }

    public void klik(MouseEvent e) //metode der styrer vores spil-logik
    {
        Brik b = (Brik) e.getSource(); //finder ud af hvilken brik der blev klikket på

        if (b == vendtBrik1 || b == vendtBrik2) return; //tjek om brikken allerede er blevet vendt.
        //man kan ikke vende brikker der allerede er vendt.
        //Første brik
        if (vendtBrik1 == null) //er der ikke vendt en brik endnu? -> Så er det første brik.
        {
            vendtBrik1 = b;     //ved 1. klik gemmes brikken som "vendtBrik1"
            vendtBrik1.vendTransition();  //vender brikken, så billedet kan ses.
        } else if(vendtBrik2 == null) { //der er allerede vendt en brik -> så er det anden brik.
            vendtBrik2 = b;    //ved 2. klik gemmes brikken som "vendtBrik2"
            vendtBrik2.vendTransition(); //vender brikken, så billedet kan ses.

            antalTraek++; //når spilleren har vendt 2 brikker giver det 1 træk
            antalTraekTekst.setText("Antal Træk: " + antalTraek); //opdatere tekst

            if (vendtBrik1.getBriknavn().equals(vendtBrik2.getBriknavn())) //det er et match
            // den henter navnet på begge billeder og tjekker om de er equals.
            {
                antalStik++; //tilføjer 1 point når kortene er et match
                antalStikTekst.setText("Antal Stik: " + antalStik); //opdatere tekst
                if (antalStik == 18) //18 stik -> du har vundet.
                {
                    vinderTekst.setText("DU HAR VUNDET!"); //opdatere tekst
                    scenegraf.getChildren().addAll(vinderTekst); //tilføjer teksten til scenen
                }

                PauseTransition pause = new PauseTransition(Duration.seconds(1));
                //vi bruger PauseTransition til at give spilleren 1 sec til at se brikkerne inden de forsvinder.
                pause.setOnFinished(event -> //efter de 1 sek er gået skal følgende ske
                {
                    vendtBrik1.visBagside();
                    vendtBrik2.visBagside();
                    vendtBrik1 = null; //nulstiller, så vi kan klikke på flere brikker
                    vendtBrik2 = null; //nulstiller, så vi kan klikke på flere brikker
                });
                pause.play(); //spillet pauses, så setOnFinished starter 0,5 sek efter.
            } else { //det er ikke et match
                PauseTransition pause = new PauseTransition(Duration.seconds(1));
                //vi bruger PauseTransition til at give spilleren 1 sec til at se brikkerne inden de vendes til bagsiden.
                pause.setOnFinished(event -> //efter de 1 sek er gået skal følgende ske
                {
                    vendtBrik1.vendTilBagsiden(); //brikkerne vendes til bagsiden
                    vendtBrik2.vendTilBagsiden(); //brikkerne vendes til bagsiden
                    vendtBrik1 = null; //nulstiller, så vi kan klikke på 2 nye brikker
                    vendtBrik2 = null; //nulstiller, så vi kan klikke på 2 nye brikker
                });
                pause.play();
            }
        }
    }

    public void restart() //ved klik på rectangel restarter spillet
    {
        scenegraf.getChildren().removeIf(node -> node instanceof Brik); //fjerner alle gamle brikker fra scenegraf

        baneOpsaetning(); //kalder bane opsætningen, så brikkerne sættes op igen

        antalStik = 0; //nulstiller tælleren
        antalStikTekst.setText("Antal Stik: " + antalStik); //opdatere tekst

        antalTraek = 0; //nulstiller tælleren
        antalTraekTekst.setText("Antal Træk: " + antalTraek); //opdatere tekst

        vendtBrik1 = null; //nulstiller variablen der holder styr på første vendt brik
        vendtBrik2 = null; //nulstiller variablen der holder styr på anden vendt brik

        scenegraf.getChildren().remove(vinderTekst); //hvis vinder teksten er der, så fjernes den ved restart
    }

    public static void main(String[] args)
    {
        launch();
    }
}