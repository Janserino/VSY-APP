package de.sesamstrasse.vsy;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;

/**
 * Created by jroerthmans on 05.01.2016.
 */
public class RestaurantManager extends Activity {

    private String name;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.restaurant_manager);
    }

    public void saveRestaurantButtonClicked(View view) {
        try {
            final EditText myText = (EditText) findViewById(R.id.editText3);
            name = myText.getText().toString();

            ConnectionHandler.addRestaurant(name);

            Toast.makeText(RestaurantManager.this, "Restaurant " + name + " erfolgreich angelegt", Toast.LENGTH_SHORT).show();
        } catch (ExecutionException e) {
            Toast.makeText(RestaurantManager.this, "Fehler bei der Verbindung", Toast.LENGTH_LONG).show();
        }
    }
}