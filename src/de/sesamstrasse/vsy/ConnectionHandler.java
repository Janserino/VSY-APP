package de.sesamstrasse.vsy;

import org.json.JSONArray;

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

    private static final String ENDPOINT_1 = "127.0.0.1:8081/",
                                ENDPOINT_2 = "127.0.0.1:8082/",
                                CREATE_RESTAURANT = "createRestaurant",
                                GET_RESTAURANT = "getRestaurant",
                                RATE_RESTAURANT = "postRestaurant";


    public static JSONArray getRestaurants() {
        return null;
    }

    public static JSONArray addRestaurant() {
        return null;
    }

    public static JSONArray rateRestaurant(){
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
