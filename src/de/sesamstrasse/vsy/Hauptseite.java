package de.sesamstrasse.vsy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Hauptseite extends Activity {
    /**
     * Called when the activity is first created.
     */

    private ArrayList<Restaurant> myListFromJSON = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        jsonArrayToArrayList(ConnectionHandler.getRestaurants());
        fillMyList();
    }

    public void fillMyList() {
        ListView myListview = (ListView) findViewById(R.id.listView);

        ArrayAdapter<Restaurant> arrayAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_list_item_1, myListFromJSON);
        myListview.setAdapter(arrayAdapter);
    }

    public void jsonArrayToArrayList(JSONArray jsonArray) {
        try {
            for (int i = 0; i < jsonArray.length(); i++) {
                myListFromJSON.add(Restaurant.parseJSON(jsonArray.getJSONObject(i)));
            }
        } catch (JSONException e) {
        }
    }

    public void showNewRestaurantActivity(View view) {
        Intent intent = new Intent(this, restaurantManager.class);
        startActivity(intent);
    }

    //region testOhneWebService
    /*
    public JSONArray myJson() throws JSONException {
        String myJSONTest = "{\n" +
                "  \"restaurantListe\": [\n" +
                "    {\n" +
                "      \"id\": \"1\",\n" +
                "      \"name\": \"McDonalds\",\n" +
                "      \"bewertung\": \"1.5\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": \"2\",\n" +
                "      \"name\": \"BurgerKing\",\n" +
                "      \"bewertung\": \"2.5\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": \"3\",\n" +
                "      \"name\": \"Mensa\",\n" +
                "      \"bewertung\": \"3.5\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": \"4\",\n" +
                "      \"name\": \"Dönermann\",\n" +
                "      \"bewertung\": \"4\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": \"5\",\n" +
                "      \"name\": \"Chinese\",\n" +
                "      \"bewertung\": \"5\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": \"6\",\n" +
                "      \"name\": \"Kantine\",\n" +
                "      \"bewertung\": \"0.5\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": \"2\",\n" +
                "      \"name\": \"BurgerKing\",\n" +
                "      \"bewertung\": \"2.5\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": \"3\",\n" +
                "      \"name\": \"Mensa\",\n" +
                "      \"bewertung\": \"3.5\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": \"4\",\n" +
                "      \"name\": \"Dönermann\",\n" +
                "      \"bewertung\": \"4\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": \"5\",\n" +
                "      \"name\": \"Chinese\",\n" +
                "      \"bewertung\": \"5\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": \"6\",\n" +
                "      \"name\": \"Kantine\",\n" +
                "      \"bewertung\": \"0.5\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": \"2\",\n" +
                "      \"name\": \"BurgerKing\",\n" +
                "      \"bewertung\": \"2.5\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": \"3\",\n" +
                "      \"name\": \"Mensa\",\n" +
                "      \"bewertung\": \"3.5\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": \"4\",\n" +
                "      \"name\": \"Dönermann\",\n" +
                "      \"bewertung\": \"4\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": \"5\",\n" +
                "      \"name\": \"Chinese\",\n" +
                "      \"bewertung\": \"5\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": \"6\",\n" +
                "      \"name\": \"Kantine\",\n" +
                "      \"bewertung\": \"0.5\"\n" +
                "    }\n" +
                "  ]\n" +
                "}";
        test = new JSONObject(myJSONTest);
        myArray = test.getJSONArray("restaurantListe");
        return myArray;
    }
    */
    //endregion
}
