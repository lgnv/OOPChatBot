package First.TypoCorrect;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Properties;

public class RequestsManager {
    private static String getUrl(String key) throws IOException {
        Properties props = new Properties();
        props.load(new FileInputStream(new File("src/main/config/keys.ini")));
        return props.getProperty(key);
    }

    private static String sendRequest(String word, String key) throws IOException {
        var builder = new StringBuilder();
        builder.append(getUrl(key));
        builder.append(word);
        var url = builder.toString();
        var obj = new URL(url);
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

    public static String getResponse(String word, String key) {
        String response = "";
        try {
            response = RequestsManager.sendRequest(word, key);
        }
        finally {
            return response;
        }
    }
}
