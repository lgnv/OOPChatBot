package First.TypoCorrect;

import org.json.JSONArray;
import org.json.JSONObject;
import java.util.HashSet;
import java.util.Set;

public class SynonymStrategy implements CorrectStrategy {
    private HashSet<String> getSynonyms(String word) {
        var response = RequestsManager.getResponse(word);
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

    public String correctTypo(String word, Set<String> commands) {
        /*var synonyms = getSynonyms(word);
        for (var command : commands) {
            if (synonyms.contains(command)) {
                return command;
            }
        }*/
        for (var command : commands) {
            var synonyms = getSynonyms(command);
            if (synonyms.contains(word)) {
                return command;
            }
        }

        return word;
    }
}
