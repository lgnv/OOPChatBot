package First.TypoCorrect;

import java.util.HashSet;

public class FakeSynonymDownloader implements ISynonymDownloader {
    @Override
    public HashSet<String> getSynonyms(String word) {
        return null;
    }
}
