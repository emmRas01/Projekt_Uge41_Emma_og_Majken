package com.example.projekt_uge41_majken_og_emma;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Brik extends ImageView {

    private Image forside, bagside;
    private String brikNavn;

    public Brik(int x, int y, String brikListe)
    {
        brikNavn = brikListe;
        forside = new Image(getClass().getResource(brikListe).toString()); //Vis forsiden som udgangspunkt
        bagside = new Image(getClass().getResource("bagside.png").toString());
        setImage(bagside);
        setX(x * 100); //placering af brikken på x-aksen.
        setY(y * 100); //placering af brikken på y-aksen.
    }

    public void vend()
    {
        setImage(forside); // Her vendes brikken.
    }

    public String getBriknavn()
    {
         return brikNavn; //her henter vi navnet på den enkelte brik
    }

}