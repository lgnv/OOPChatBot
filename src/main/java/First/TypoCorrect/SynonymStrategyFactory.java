package First.TypoCorrect;

public class SynonymStrategyFactory implements IStrategyFactory {
    @Override
    public CorrectStrategy create() {
        return new SynonymStrategy(new YandexSynonymDownloader());
    }
}
