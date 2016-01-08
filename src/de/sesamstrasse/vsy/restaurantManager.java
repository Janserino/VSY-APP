package de.sesamstrasse.vsy;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import org.w3c.dom.Text;

/**
 * Created by jroerthmans on 05.01.2016.
 */
public class restaurantManager extends Activity {

    private String name;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.restaurant_manager);
    }

    public void saveRestaurantButtonClicked(View view){
        try{
            final Text myText=(Text)findViewById(R.id.editText3);
            name = myText.getWholeText();
            //TODO: Schreiben des Namens an den Webservice
            //TODO: Zurückkommende ArrayList an Hauptseite weitergeben
            Toast.makeText(restaurantManager.this, "Restaurant " + name + " erfolgreich angelegt", Toast.LENGTH_SHORT).show();
        }
        catch (Exception e){
            Toast.makeText(restaurantManager.this, "Fehler", Toast.LENGTH_SHORT).show();
        }
    }
}