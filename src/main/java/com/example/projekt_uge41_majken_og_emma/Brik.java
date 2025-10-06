package com.example.projekt_uge41_majken_og_emma;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Brik extends ImageView {

    private Image forside, bagside;
    private String brikNavn;

    public Brik(int x, int y, String brikListe)
    {
        brikNavn = brikListe;
        // Sæt ny brik op med forside- og bagsidebillede (alle brikker har samme bagside).
        // Vis forsiden som udgangspunkt
        forside = new Image(getClass().getResource(brikListe).toString());
        bagside = new Image(getClass().getResource("bagside.png").toString());
        setImage(bagside);
        // Placér brikken: lav selv koordinaterne, så brikkerne spredes
        setX(x * 90);
        setY(y * 90);
    }

    public void vend()
    {
        // Her skal brikken vendes.
        setImage(forside);
    }

    public String getBriknavn()
    {
         return brikNavn;
    }
}