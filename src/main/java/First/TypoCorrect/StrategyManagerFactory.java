package First.TypoCorrect;

public class StrategyManagerFactory {
    public static StrategyManager create() {
        return new StrategyManager(new SynonymStrategyFactory(),
                new GameStrategyFactory());
    }
}
