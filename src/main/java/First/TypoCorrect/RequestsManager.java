package First.TypoCorrect;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class RequestsManager {
    private static String sendRequest(String word) throws IOException {
        var builder = new StringBuilder();
        builder.append("https://dictionary.yandex.net/api/v1/dicservice.json/");
        builder.append("lookup?key=dict.1.1.20181024T140651Z.8edd968a695a4c26.");
        builder.append("86dc72f73eeaf2cbfc3c898e7243b75156ece2b5&lang=ru-ru&text=");
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

    public static String getResponse(String word) {
        String response = "";
        try {
            response = RequestsManager.sendRequest(word);
        }
        finally {
            return response;
        }
    }
}
