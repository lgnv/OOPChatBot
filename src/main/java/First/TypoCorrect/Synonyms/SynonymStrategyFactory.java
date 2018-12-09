package First.TypoCorrect.Synonyms;

import First.TypoCorrect.CorrectStrategy;
import First.TypoCorrect.IStrategyFactory;

public class SynonymStrategyFactory implements IStrategyFactory {
    @Override
    public CorrectStrategy create() {
        return new SynonymStrategy(new YandexSynonymDownloader());
    }
}
