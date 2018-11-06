package First.TypoCorrect;

import org.json.JSONArray;
import org.json.JSONObject;
import java.util.HashSet;

public class YandexSynonymDownloader implements ISynonymDownloader {
    @Override
    public HashSet<String> getSynonyms(String word) {
        var response = RequestsManager.getResponse(word, "YANDEX_URL","YANDEX_API_KEY");
        JSONArray trJson;
        var synonyms = new HashSet<String>();
        try {
            var jsonObj = new JSONObject(response);
            trJson = jsonObj
                    .getJSONArray("def")
                    .getJSONObject(0)
                    .getJSONArray("tr");
            for (var i = 0; i < trJson.length(); i++) {
                var trI = trJson.getJSONObject(i);
                synonyms.add(trI.getString("text"));
                if (!trI.keySet().contains("syn")) {
                    continue;
                }
                var synJson = trI.getJSONArray("syn");
                for (var j = 0; j < synJson.length(); j++) {
                    synonyms.add(synJson.getJSONObject(j).getString("text"));
                }
            }
        } catch (Exception e) {
            return synonyms;
        }
        return synonyms;
    }
}
