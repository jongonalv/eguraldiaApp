package eguraldiaApp;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

/**
 * httpAPIKonexioa gauzatzeko klasea.
 */
public class httpAPIKonexioa {
	
    /**
     * Konexioa egiteko API-arekin.
     *
     * @param urlString --> API-aren URL-a
     * @return konexioa
     * @throws IOException Signals that an I/O exception has occurred.
     */
    protected static HttpURLConnection fetchConnection(String urlString) throws IOException {
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.connect();
        return conn;
    }
    
    /**
     * Json fitxategia lortu, konexioaren emaitzak irakurtzen dira
     * eta json-aren emaitzak bueltatzen ditu Stringbuilder moduan.
     *
     * @param conn --> API konexioa
     * @return json emaitzak
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public static StringBuilder jsonLortu(HttpURLConnection conn) throws IOException {
    	
        StringBuilder resultJson = new StringBuilder();
        Scanner scanner = new Scanner(conn.getInputStream());

        while (scanner.hasNext()) {
            resultJson.append(scanner.nextLine());
        }

        scanner.close();
        conn.disconnect();

        return resultJson;
    }
}
