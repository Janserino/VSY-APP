package de.sesamstrasse.vsy;

import android.widget.Toast;
import org.apache.http.protocol.HTTP;
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
import java.util.concurrent.ExecutionException;

/**
 * Created by jroerthmans on 07.01.2016.
 */
public class ConnectionHandler {


    private static final String ENDPOINT_1 = "192.168.2.111:8081/",
            ENDPOINT_2 = "192.168.2.111:8082/",
            CREATE_RESTAURANT = "createRestaurant",
            GET_RESTAURANT = "getRestaurant",
            RATE_RESTAURANT = "postRestaurant";


    public static boolean endpoint1Up = true;
    public static boolean secondTry = false;


    public static JSONArray getRestaurants() throws ExecutionException {
        secondTry = false;
        String result = httpRequestor("", GET_RESTAURANT);
        if(result == "01" || result == "02")
            throw new ExecutionException(new Throwable(result));
        try {
            return new JSONArray(result);
        } catch (JSONException e) {
        }
        return new JSONArray();
    }

    public static JSONArray addRestaurant(String name) throws ExecutionException {
        JSONObject restaurant = new JSONObject();
        try {
            restaurant.put("name", name);
            secondTry = false;
            String result = httpRequestor(restaurant.toString(), CREATE_RESTAURANT);
            if(result == "01" || result == "02")
                throw new ExecutionException(new Throwable(result));
            return new JSONArray(result);
        } catch (JSONException e) {
            throw new ExecutionException(new Throwable(e.getMessage()));
        }
    }

    public static JSONArray rateRestaurant(short rating, long id) throws ExecutionException{
        JSONObject restaurant = new JSONObject();
        try {
            restaurant.put("id", id);
            restaurant.put("bewertung", rating);
            String payload = restaurant.toString();
            String result = httpRequestor(payload, RATE_RESTAURANT);
            if(result == "01" || result == "02")
                throw new ExecutionException(new Throwable(result));
            return new JSONArray(result);
        } catch (JSONException e) {
            throw new ExecutionException(new Throwable(e.getMessage()));
        }
    }

    private static String httpRequestor(String payload, String transaction) {
        try {
            if (payload == null)
                payload = "";

            String endpoint = "";
            if (endpoint1Up)
                endpoint = ENDPOINT_1;
            else
                endpoint = ENDPOINT_2;

            URL url = new URL("http://" + endpoint + transaction);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            switch (transaction) {
                case CREATE_RESTAURANT:
                case RATE_RESTAURANT:
                    connection.setRequestMethod("POST");
                    break;
                case GET_RESTAURANT:
                    connection.setRequestMethod("GET");
                    break;
            }

            connection.setRequestProperty("Content-Type", "application/json");

            connection.setConnectTimeout(1000);

            //Falls POST/PUT
            if (connection.getRequestMethod() == "POST") {
                OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
                writer.write(payload);
                writer.flush();
            }

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));

            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
                response.append('\n');
            }
            in.close();

            secondTry = false;
            return response.toString();
        } catch (MalformedURLException e) {
            return "01";
        } catch (IOException e) {
            System.out.println(e.getMessage());
            if (secondTry)
                return "02";
            else {
                endpoint1Up = !endpoint1Up;
                secondTry = true;
                return httpRequestor(payload, transaction);
            }
        }
    }
}




    /*


    public static String httpRequestTest(String payload, String transaction){
        HttpURLConnection connection = null;
        try {
            if (payload == null)
                payload = "";

            URL url = new URL("http://" + ENDPOINT_1 + transaction);
            connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");
            connection.setDoOutput(true);

            connection.setRequestProperty("Content-Type", "text/html");
            connection.setUseCaches(false);
            connection.setDoInput(true);

            InputStream inputStream = connection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            StringBuilder response = new StringBuilder();
            while ((line = bufferedReader.readLine()) != null) {
                response.append(line);
                response.append('\n');
            }

            bufferedReader.close();
            return response.toString();
        }
        catch (MalformedURLException e){
            System.out.println(e);
        }
        catch (IOException e){
            System.out.println(e);
        }

        return "";
    }

     */