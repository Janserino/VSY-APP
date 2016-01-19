package de.sesamstrasse.vsy;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by jroerthmans on 07.01.2016.
 */
public class Restaurant {
    long id;
    String name;
    double bewertung;
    int anzahl;

    Restaurant() {
        id = 0;
        name = "";
        bewertung = 0;
        anzahl = 0;
    }

    public String toString() {
        return name + "\nBewertung: " + bewertung + "/5";
    }

    public JSONObject toJSON() {
        try {
            JSONObject restaurant = new JSONObject();
            restaurant.put("id", this.id);
            restaurant.put("name", this.name);
            restaurant.put("bewertung", this.bewertung);
            restaurant.put("anzahl", this.anzahl);

            return restaurant;
        } catch (JSONException e) {
        }
        return new JSONObject();
    }

    public static Restaurant parseJSON(JSONObject jsonObject) {
        try {
            Restaurant restaurant = new Restaurant();
            if (jsonObject.get("id") != null)
                restaurant.id = Long.parseLong(jsonObject.get("id").toString());
            if (jsonObject.get("name") != null)
                restaurant.name = jsonObject.get("name").toString();
            if (jsonObject.get("bewertung") != null)
                restaurant.bewertung = Double.parseDouble(jsonObject.get("bewertung").toString());
            if (jsonObject.get("anzahl") != null)
                restaurant.anzahl = Integer.parseInt(jsonObject.get("anzahl").toString());
            return restaurant;
        } catch (JSONException e) {
        }
        return new Restaurant();
    }

}
