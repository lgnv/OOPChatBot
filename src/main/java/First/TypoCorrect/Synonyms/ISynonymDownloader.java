package First.TypoCorrect.Synonyms;

import java.util.HashSet;

public interface ISynonymDownloader {
    HashSet<String> getSynonyms(String word);
}
