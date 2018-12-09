package First.TypoCorrect.Synonyms;

import First.TypoCorrect.CorrectStrategy;

import java.util.Set;

public class SynonymStrategy implements CorrectStrategy {
    private ISynonymDownloader synonymDownloader;

    public SynonymStrategy(ISynonymDownloader synonymDownloader) {
        this.synonymDownloader = synonymDownloader;
    }

    @Override
    public String correctTypo(String word, Set<String> commands) {
        for (var command : commands) {
            var synonyms = synonymDownloader.getSynonyms(command);
            if (synonyms.contains(word)) {
                return command;
            }
        }
        return word;
    }
}

