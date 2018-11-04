package First.TypoCorrect;

import java.util.HashSet;

public interface ISynonymDownloader {
    HashSet<String> getSynonyms(String word);
}
