package First.TypoCorrect;

public class GameStrategyFactory implements IStrategyFactory {
    @Override
    public CorrectStrategy create() {
        return new GameStrategy();
    }
}
