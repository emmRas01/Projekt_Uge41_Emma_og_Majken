package com.example.projekt_uge41_majken_og_emma;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class VendeSpil extends Application {

    // Alle brikker holdes i arrayet brikker
    private Brik[][] brikker;

    @Override
    public void start(Stage stage) throws IOException {
        Pane scenegraf = new Pane();

        // eventHandler som senere sættes på hver brik, så man kan klikke med musun.
        EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                Brik b = (Brik)e.getSource();
                b.vend();
            }
        };

        // Her sættes banen op. Filen brik1.png skal findes i resource
        brikker = new Brik[5][5];
        for (int i=0; i<5; i++)
            for (int j=0; j<5; j++) {
                // Lav hver brik som et objekt "Brik" med billede
                brikker[i][j] = new Brik(i, j, "brik1.png");
                // Tilføj den til scenegrafen
                scenegraf.getChildren().add(brikker[i][j]);
                // Tilføj eventen til brikken
                brikker[i][j].addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler);
            }

        // Sæt scenen op
        Scene scene = new Scene(scenegraf, 600, 600);
        stage.setTitle("MemorySkelet!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}