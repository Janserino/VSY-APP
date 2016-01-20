package de.sesamstrasse.vsy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class Hauptseite extends Activity {
    /**
     * Called when the activity is first created.
     */

    private ArrayList<Restaurant> myListFromJSON = new ArrayList<>();
    public static JSONArray jsonArray = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        ListView myListview = (ListView) findViewById(R.id.listView);
        myListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Restaurant selected = myListFromJSON.get(position);
                //TODO: Open other Activity
                //openRestaurantRatingActivity(selected.id);
                short rate = 5;
                try {
                    ConnectionHandler.rateRestaurant(rate, selected.id);
                } catch (ExecutionException e){
                    Toast.makeText(Hauptseite.this, "Fehler bei der Bewertung", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
            try {
                jsonArray = jsonArrayToArrayList(ConnectionHandler.getRestaurants());
                fillList();
            } catch (ExecutionException e) {
                Toast.makeText(Hauptseite.this, "Fehler bei der Verbindung zum Server", Toast.LENGTH_SHORT).show();
            }
    }

    public void openRestaurantRatingActivity(long restaurantId){
        Intent intent = new Intent(this, RestaurantManager.class);
        startActivity(intent);
    }

    public void showNewRestaurantActivity(View view) {
        //TODO: Activity ändern und ID übergeben
        Intent intent = new Intent(this, RestaurantManager.class);
        startActivity(intent);
    }

    public void fillList() {
        ListView myListview = (ListView) findViewById(R.id.listView);

        ArrayAdapter<Restaurant> arrayAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_list_item_1, myListFromJSON);
        myListview.setAdapter(arrayAdapter);
    }

    public JSONArray jsonArrayToArrayList(JSONArray jsonArray) {
        try {
            myListFromJSON.clear();
            for (int i = 0; i < jsonArray.length(); i++) {
                myListFromJSON.add(Restaurant.parseJSON(jsonArray.getJSONObject(i)));
            }
        } catch (JSONException e) {
        }
        return jsonArray;
    }
}
