import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.swing.*;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class WeatherApp {
    public static JSONObject getWeatherData(String locationName){
        JSONArray locationData = getLocationData(locationName);

        if (locationData == null) {
            JOptionPane.showMessageDialog(
                    null,
                    "Cidade não encontrada",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE
            );
        }
        //pega latitude
        JSONObject location = (JSONObject) locationData.get(0);
        double latitude = (double) location.get("latitude");
        double longitude = (double) location.get("longitude");


        String urlString = "https://api.open-meteo.com/v1/forecast?" + "latitude=" + latitude + "&longitude=" + longitude + "&hourly=temperature_2m,weather_code,relative_humidity_2m,wind_speed_10m";

        try{
            HttpURLConnection conn = fetchApiResponse(urlString);

            if (conn.getResponseCode() !=200){
                System.out.println("Não conectou com a api");
                return null;
            }

            StringBuilder resultJson = new StringBuilder();
            Scanner scanner = new Scanner(conn.getInputStream());
            while (scanner.hasNext()){
                resultJson.append(scanner.nextLine());
            }

            scanner.close();
            conn.disconnect();

            JSONParser parser = new JSONParser();
            JSONObject resultJsonObj = (JSONObject) parser.parse(String.valueOf(resultJson));

            JSONObject hourly = (JSONObject) resultJsonObj.get("hourly");

            JSONArray time = (JSONArray) hourly.get("time");
            int index = findIndexOfCurrentTime(time);

            JSONArray temperatureData = (JSONArray) hourly.get("temperature_2m");
            double temperature = (double) temperatureData.get(index);

            JSONArray weathercode = (JSONArray) hourly.get("weather_code");
            String weatherCodition = convertWeatherCode((long) weathercode.get(index));

            JSONArray relativehumidity = (JSONArray) hourly.get("weather_code");
            long humidity = (Long) relativehumidity.get(index);

            JSONArray windspeedData = (JSONArray) hourly.get("wind_speed_10m");
            double windspeed = (double) windspeedData.get(index);

            JSONObject weatherData = new JSONObject();
            weatherData.put("temperature",temperature);
            weatherData.put("weather_condition", weatherCodition);
            weatherData.put("humidity",humidity);
            weatherData.put("windspeed", windspeed);

            return weatherData;

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static JSONArray getLocationData(String locationName){
        locationName = locationName.replaceAll(" ","+");

        String urlString = "https://geocoding-api.open-meteo.com/v1/search?name=" +
        locationName + "&count=10&language=pt&format=json&countryCode=BR";

        try{
            HttpURLConnection conn = fetchApiResponse(urlString);

            if (conn.getResponseCode() !=200){
                System.out.println("Erro de conexão com a api");
            }else {
                StringBuilder resultJson = new StringBuilder();
                Scanner scanner = new Scanner(conn.getInputStream());
                while (scanner.hasNext()){
                    resultJson.append(scanner.nextLine());
                }

                scanner.close();

                conn.disconnect();

                JSONParser parser = new JSONParser();
                JSONObject resultsJsonObj = (JSONObject) parser.parse(String.valueOf(resultJson));

                JSONArray locationData = (JSONArray) resultsJsonObj.get("results");
                return locationData;
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;

    }

    private static HttpURLConnection fetchApiResponse(String urlString){
        try {
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            //set request
            conn.setRequestMethod("GET");
            conn.connect();
            return conn;
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;


    }

    private static int findIndexOfCurrentTime(JSONArray timelist){
        String currentTime = getCurrentTime();

        for (int i = 0; i < timelist.size(); i++){
            String time = (String) timelist.get(i);
            if (time.equalsIgnoreCase(currentTime)){
                return i;
            }
        }

        return 0;
    }

    public static String getCurrentTime(){
        LocalDateTime currentDateTime = LocalDateTime.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH':00'");

        String formattedDateTime = currentDateTime.format(formatter);

        return formattedDateTime;

    }

    private static  String convertWeatherCode(long weathercode){
        String weatherCondition = "";
        if (weathercode ==0L){
            weatherCondition = "Limpo";
        } else if (weathercode <= 3L && weathercode > 0L) {
            weatherCondition = "Nublado";
        } else if ((weathercode >= 51L && weathercode <= 67L)
                || (weathercode >= 80L && weathercode <= 99L))   {
            weatherCondition = "Chuva";
        } else if (weathercode >=71L && weathercode <= 77L) {
            weatherCondition = "Nevando";
        }

        return weatherCondition;
    }

}
