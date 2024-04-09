package eguraldiaApp;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import javax.swing.JOptionPane;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class geokokapenakKudeatu {

    public static ArrayList<Geokokapena> getKokapenaData(String kokapenaIzena) {
        kokapenaIzena = kokapenaIzena.replaceAll(" ", "+");
        String urlString = "https://geocoding-api.open-meteo.com/v1/search?name=" + kokapenaIzena
                + "&count=10&language=en&format=json";

        try {
            HttpURLConnection conn = httpAPIKonexioa.fetchConnection(urlString);
            if (conn != null && conn.getResponseCode() == 200) {
            	
                StringBuilder resultJson = httpAPIKonexioa.jsonLortu(conn);

                JSONParser parser 			= new JSONParser();
                JSONObject resultsJsonObj 	= (JSONObject) parser.parse(resultJson.toString());
                JSONArray locationData 		= (JSONArray) resultsJsonObj.get("results");

                if (locationData != null && !locationData.isEmpty()) {
                    ArrayList<Geokokapena> kokapenak = new ArrayList<>();
                    for (Object obj : locationData) {
                        JSONObject jsonObject = (JSONObject) obj;
                        Geokokapena kokapena = createGeokokapenaFromJson(jsonObject);
                        kokapenak.add(kokapena);
                    }
                    return kokapenak;
                }
            } else {
                JOptionPane.showMessageDialog(null, "Ezin izan da API-ra konektatu", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static Geokokapena createGeokokapenaFromJson(JSONObject jsonObject) {
        float id 					= nullBalorea(jsonObject.get("id"));
        String name 				= (String) jsonObject.get("name");
        float latitude 				= nullBalorea(jsonObject.get("latitude"));
        float longitude 			= nullBalorea(jsonObject.get("longitude"));
        float elevation 			= nullBalorea(jsonObject.get("elevation"));
        String country_code 		= (String) jsonObject.get("country_code");
        String timezone 			= (String) jsonObject.get("timezone");
        float population 			= nullBalorea(jsonObject.get("population"));
        JSONArray postcodesArray 	= (JSONArray) jsonObject.get("postcodes");
        ArrayList<Object> postcodes = new ArrayList<>();
        if (postcodesArray != null) {
            for (Object postcode : postcodesArray) {
                postcodes.add(postcode);
            }
        }
        float country_id 			= nullBalorea(jsonObject.get("country_id"));
        String country 				= (String) jsonObject.get("country");
        String admin1 				= (String) jsonObject.get("admin1");
        String admin2 				= (String) jsonObject.get("admin2");

        return new Geokokapena(id, name, latitude, longitude, elevation, country_code,
                timezone, population, postcodes, country_id, country, admin1, admin2);
    }

    private static float nullBalorea(Object valor) {
        return (valor instanceof Number) ? ((Number) valor).floatValue() : 0.0f;
    }
}
