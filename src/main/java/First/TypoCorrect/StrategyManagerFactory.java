package First.TypoCorrect;

import First.TypoCorrect.Synonyms.SynonymStrategyFactory;

public class StrategyManagerFactory {
    public static StrategyManager create() {
        return new StrategyManager(new SynonymStrategyFactory(),
                new GameStrategyFactory());
    }
}
