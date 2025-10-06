package com.example.projekt_uge41_majken_og_emma;

import javafx.animation.PathTransition;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class VendeSpil extends Application {

    // Alle brikker holdes i arrayet brikker
    private Brik[][] brikker;
    private Brik vendtBrik1 = null;
    private Brik vendtBrik2 = null;
    private PathTransition pt; //så metoden startstop() kan kalde pt / kende pt
    private String[] brikListe = {
            "brik3.png", "brik16.png", "brik9.png", "brik18.png", "brik15.png", "brik8.png",
            "brik14.png", "brik11.png", "brik1.png", "brik4.png", "brik7.png", "brik12.png",
            "brik1.png", "brik12.png", "brik2.png", "brik9.png", "brik10.png", "brik5.png",
            "brik2.png", "brik10.png", "brik7.png", "brik6.png", "brik14.png", "brik11.png",
            "brik18.png", "brik6.png", "brik4.png", "brik5.png", "brik3.png", "brik17.png",
            "brik15.png", "brik17.png", "brik13.png", "brik16.png", "brik8.png", "brik13.png",
    };

    public void start(Stage stage) throws IOException {
        Pane scenegraf = new Pane();

        // eventHandler som senere sættes på hver brik, så man kan klikke med musun.
        EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                Brik b = (Brik) e.getSource();
                b.vend();
            }
        };

        // Her sættes banen op. Filen brik1.png skal findes i resource
        brikker = new Brik[6][6];
        int t = 0;
        for (int i = 0; i < 6; i++)
            for (int j = 0; j < 6; j++) {
                // Lav hver brik som et objekt "Brik" med billede
                brikker[i][j] = new Brik(i, j, brikListe[t]);
                // Tilføj den til scenegrafen
                scenegraf.getChildren().add(brikker[i][j]);
                // Tilføj eventen til brikken
                brikker[i][j].setOnMouseClicked(e -> klik(e));
                ++t;
            }

        //rectangel som knap
        Rectangle rect = new Rectangle(220, 550, 100, 40);
        rect.setFill(Color.BLACK);
        rect.setOnMouseClicked(event -> restart());

        scenegraf.getChildren().addAll(rect); //rect vises på scenen

        // Sæt scenen op
        Scene scene = new Scene(scenegraf, 540, 600);
        stage.setTitle("Vende Spil :)");
        stage.setScene(scene);
        stage.show();
    }

    public void klik(MouseEvent e)
    {
       Brik b = (Brik) e.getSource();

       if (b == vendtBrik1 || b == vendtBrik2) return; //man kan ikke vende brikker der allerede er vendt.

       //Første brik
       if (vendtBrik1 == null)
           {
                vendtBrik1 = b;
                vendtBrik1.vend();
                return;
           }
       //anden brik
       if(vendtBrik2 == null)
           {
               vendtBrik2 = b;
               vendtBrik2.vend();

               if(vendtBrik1.getBriknavn().equals(vendtBrik2.getBriknavn()))  //brikkerne er et match
                   {
                       PauseTransition pause = new PauseTransition(Duration.seconds(0.5));
                       pause.setOnFinished(event-> {
                           vendtBrik1.setVisible(false);
                           vendtBrik2.setVisible(false);
                           vendtBrik1 = null;
                           vendtBrik2 = null;
                       });
                       pause.play();
                   } else {
                       PauseTransition pause = new PauseTransition(Duration.seconds(0.5));
                       pause.setOnFinished(event-> {
                           vendtBrik1.setImage(new Image(getClass().getResource("bagside.png").toString()));
                           vendtBrik2.setImage(new Image(getClass().getResource("bagside.png").toString()));
                           vendtBrik1 = null;
                           vendtBrik2 = null;
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