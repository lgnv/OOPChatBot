package First.TypoCorrect;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class RequestsManager {
    private static String sendRequest(String word, String url, String key) throws IOException {
        var builder = new StringBuilder();
        builder.append(ConfigManager.getProperty(url));
        builder.append(ConfigManager.getProperty(key));
        builder.append(word);
        var full_url = builder.toString();
        var obj = new URL(full_url);
        HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
        connection.setRequestMethod("GET");
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        var response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        return response.toString();
    }

    public static String getResponse(String word, String url, String key) {
        String response = "";
        try {
            response = RequestsManager.sendRequest(word, url, key);
        }
        finally {
            return response;
        }
    }
}
