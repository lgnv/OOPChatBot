package First.TypoCorrect;

import java.util.Set;

public class TypoCorrecter {
    private CorrectStrategy strategy;
    private int typoCount;

    public TypoCorrecter(CorrectStrategy strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(CorrectStrategy newStrategy) {
        strategy = newStrategy;
    }

    public String execute(String word, Set<String> commands) {
        var result = strategy.correctTypo(word, commands);
        if (result.equals(word) && !commands.contains(word)) {
            typoCount++;
        }
        if (typoCount > 2 && !(strategy instanceof GameStrategy)) {
            setStrategy(new SynonymStrategy());
        }
        return result;
    }
}
