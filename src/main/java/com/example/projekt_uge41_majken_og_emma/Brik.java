package com.example.projekt_uge41_majken_og_emma;

import javafx.animation.ScaleTransition;
import javafx.animation.SequentialTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class Brik extends ImageView
{
    private Image forside, bagside; //variabel der indeholder vores billeder
    private String brikNavn; //variabel der indeholder navnet på brikken/billedet

    public Brik(int x, int y, String brikListe) //konstruktør der opretter en brik med et billede
    {
        brikNavn = brikListe; //gemmer navnet på brikken, så vi kan finde ud af om brikkerne matcher
        forside = new Image(getClass().getResource(brikListe).toString()); //indlæser billedet til forsiden
        bagside = new Image(getClass().getResource("bagside.png").toString()); //indlæser billedet til bagsiden
        setImage(bagside); //viser bagsiden som udgangspunkt
        setX(x * 100); //placering af brikken på x-aksen.
        setY(y * 100); //placering af brikken på y-aksen.
    }

    public void vend() //bruges i vores klik-metode
    {
        setImage(forside); // Her vendes brikken.
    }

    public String getBriknavn() //bruges i vores klik-metode til at tjekke match
    {
         return brikNavn; //her henter vi navnet på den enkelte brik
    }

}