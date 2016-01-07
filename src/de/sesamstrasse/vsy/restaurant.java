package de.sesamstrasse.vsy;

/**
 * Created by jroerthmans on 07.01.2016.
 */
public class Restaurant {
        long id;
        String name;
        double bewertung;
        int anzahl;


        public String toString() {
            return name + "\nBewertung: " + bewertung + "/5";
        }
}
