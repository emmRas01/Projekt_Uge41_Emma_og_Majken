package com.example.projekt_uge41_majken_og_emma;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class Brik extends ImageView {

    private Image forside, bagside;

    public Brik(int x, int y, String filnavn) {
        // Sæt ny brik op med forside- og bagsidebillede (alle brikker har samme bagside).
        // Vis forsiden som udgangspunkt
        forside = new Image(getClass().getResource(filnavn).toString());
        bagside = new Image(getClass().getResource("brik1.png").toString());
        setImage(forside);
        // Placér brikken: lav selv koordinaterne, så brikkerne spredes
        setX(x * 90);
        setY(y * 90);
    }

    public void vend() {
        // Her skal brikken vendes.
        // Lige nu udskrives lidt oplysninger om brikken og bagsiden vises
        System.out.println("brik " + getX() + "," + getY());
        setImage(bagside);
    }
}