package de.sesamstrasse.vsy;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import android.widget.RatingBar;
import de.sesamstrasse.vsy.R;

import java.util.concurrent.ExecutionException;

/**
 * Created by wagnerf on 19.01.2016.
 */

public class RestaurantRater extends Activity {
    static public long sel_id;
    public float rating;
    Restaurant restaurant = new Restaurant();

    @Override
    public void onCreate(Bundle savedInstanceState, Restaurant McBeispiel) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.restaurant_rater);
        restaurant = McBeispiel;
    }
    public void saveRestaurantRatingButtonClicked (){
        try{
            final RatingBar newRating = (RatingBar)findViewById(R.id.RatingBar1);
            rating = newRating.getRating();
            short short_rate = ((short) rating);
            ConnectionHandler.rateRestaurant(short_rate, sel_id);
            Toast.makeText(RestaurantRater.this, "Restaurant" + restaurant.name + "bewertet!", Toast.LENGTH_SHORT).show();
        }
        catch (Exception e) {
            Toast.makeText(RestaurantRater.this, "Fehler bei der Bewertung", Toast.LENGTH_SHORT).show();
        }
    }
}