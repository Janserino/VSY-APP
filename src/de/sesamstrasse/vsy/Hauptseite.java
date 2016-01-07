package de.sesamstrasse.vsy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Hauptseite extends Activity {
    /**
     * Called when the activity is first created.
     */

    JSONObject test;
    JSONArray myArray;

    ArrayList<Restaurant> myListFromJSON = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        try
        {
            jsonArrayToArrayList(myJson());
            fillMyList();
        }
        catch (JSONException ex)
        {
            Toast toast = Toast.makeText(getApplicationContext(),"Fehler " + ex.toString(),Toast.LENGTH_LONG );
            toast.show();
            Log.e("1",ex.getLocalizedMessage());
        }
    }

    public void fillMyList(){
        ListView myListview = (ListView)findViewById(R.id.listView);

        ArrayAdapter<Restaurant> arrayAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_list_item_1, myListFromJSON);
        //TODO: Adapter so bearbeiten das die eigentlichen Namen angezeigt werden...
        myListview.setAdapter(arrayAdapter);
    }

    public JSONArray myJson() throws JSONException
    {
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

    public void jsonArrayToArrayList(JSONArray jsonArray)
    {
        try {
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Restaurant resultRow = new Restaurant();
                resultRow.id = jsonObject.getInt("id");
                resultRow.name = jsonObject.getString("name");
                resultRow.bewertung = jsonObject.getDouble("bewertung");
                myListFromJSON.add(resultRow);
            }
        }
        catch (JSONException ex)
        {
            Toast toast = Toast.makeText(getApplicationContext(),"Fehler " + ex.toString(),Toast.LENGTH_LONG );
            toast.show();
        }
    }

    public void showNewRestaurantActivity(View view){
        Intent intent = new Intent(this, restaurantManager.class);
        startActivity(intent);
    }
}
