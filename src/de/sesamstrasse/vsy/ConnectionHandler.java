package de.sesamstrasse.vsy;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by jroerthmans on 07.01.2016.
 */
public class ConnectionHandler {

    //TODO: Überprüfen...

    private static final String ENDPOINT_1 = "127.0.0.1:8081/",
                                ENDPOINT_2 = "127.0.0.1:8082/",
                                CREATE_RESTAURANT = "createRestaurant",
                                GET_RESTAURANT = "getRestaurant",
                                RATE_RESTAURANT = "postRestaurant";


    public static JSONArray getRestaurants() {
        //TODO: Aufbau payload und Übergabe an httpRequestor
        //TODO: Rückgabe zu JSON konvertieren
        String result = httpRequestor("",GET_RESTAURANT);
        try {
            return new JSONArray(result);
        }
        catch (JSONException e){}
        return new JSONArray();
    }

    public static JSONArray addRestaurant(String name) {
        JSONObject restaurant = new JSONObject();
        try{
            restaurant.put("name",name);
            return new JSONArray(httpRequestor(restaurant.toString(),CREATE_RESTAURANT));
        }
        catch (JSONException e){}
            return getRestaurants();
    }

    public static JSONArray rateRestaurant(short rating, long id){
        JSONObject restaurant = new JSONObject();
        try{
            restaurant.put("id", id);
            restaurant.put("bewertung", rating);
            return new JSONArray(httpRequestor(restaurant.toString(),RATE_RESTAURANT));
        }
        catch (JSONException e){}
        return null;
    }

    private static String httpRequestor(String payload, String transaction){
        HttpURLConnection connection = null;
        try{
            if(payload == null)
                payload = "";

            URL url = new URL(ENDPOINT_1+transaction);
            connection = (HttpURLConnection)url.openConnection();
            switch (transaction){
                case CREATE_RESTAURANT:
                    connection.setRequestMethod("POST");
                    connection.setDoOutput(true);
                    break;
                case RATE_RESTAURANT:
                    connection.setRequestMethod("PUT");
                    connection.setDoOutput(true);
                    break;
                case GET_RESTAURANT:
                    connection.setRequestMethod("GET");
                    break;
            }

            connection.setRequestProperty("Content-Type","application/json");
            connection.setUseCaches(false);
            connection.setDoInput(true);

            //Falls POST/PUT
            if(connection.getDoOutput()) {
                OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
                writer.write(payload);
                writer.flush();
            }

            InputStream inputStream = connection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            StringBuilder response = new StringBuilder();
            while((line = bufferedReader.readLine())!= null){
                response.append(line);
                response.append('\n');
            }

            bufferedReader.close();
            return response.toString();
        }
        catch (MalformedURLException e){}
        catch (IOException e) {}
        finally{
            if(connection!=null)
                connection.disconnect();
        }
        return "";

    }

}
