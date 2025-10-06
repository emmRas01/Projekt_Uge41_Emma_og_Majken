package com.example.projekt_uge41_majken_og_emma;

import javafx.animation.PathTransition;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;

public class VendeSpil extends Application {

    // Alle brikker holdes i arrayet brikker
    private Brik[][] brikker;
    private PathTransition pt; //så metoden startstop() kan kalde pt / kende pt
    boolean stoppede = false;

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
        for (int i = 0; i < 6; i++)
            for (int j = 0; j < 6; j++) {
                // Lav hver brik som et objekt "Brik" med billede
                brikker[i][j] = new Brik(i, j, "bagside.png");
                // Tilføj den til scenegrafen
                scenegraf.getChildren().add(brikker[i][j]);
                // Tilføj eventen til brikken
                brikker[i][j].addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler);
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

    String[]brikListe = {
            "brik1", "brik2", "brik3", "brik4", "brik5", "brik6", "brik7", "brik8", "brik9", "brik10",
            "brik11", "brik12", "brik13", "brik14", "brik15", "brik16", "brik17", "brik18",
    };

    public void restart() //ved klik på rectangel starter denne metode
    {

    }

    public static void main(String[] args) {
        launch();
    }
}