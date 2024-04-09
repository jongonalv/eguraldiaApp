package eguraldiaApp;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class httpAPIKonexioa {
	
    protected static HttpURLConnection fetchConnection(String urlString) throws IOException {
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.connect();
        return conn;
    }
    
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
