package First.TypoCorrect;

import java.util.Set;

public class SynonymStrategy implements CorrectStrategy {
    public String correctTypo(String word, Set<String> commands) {
        if (commands.contains(word)){
            return word;
        }
        for (var command : commands) {
            var synonyms = SynonymDownloader.getSynonyms(command);
            if (synonyms.contains(word)) {
                return command;
            }
        }
        return word;
    }
}
