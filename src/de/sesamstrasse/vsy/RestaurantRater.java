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

    private float rating;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.restaurant_rater);
    }
    public void saveRestaurantRatingButtonClicked (View view){
        try{
            final RatingBar newRating = (RatingBar)findViewById(R.id.RatingBar1);
            rating = newRating.getRating();
            ConnectionHandler.rateRestaurant(rating, selected.id);
            Toast.makeText(RestaurantRater.this, "Restaurant" + selected.name + "bewertet!");
        }
        catch (ExecutionException e) {
            Toast.makeText(Hauptseite.this, "Fehler bei der Bewertung", Toast.LENGTH_SHORT).show();
        }
    }
}