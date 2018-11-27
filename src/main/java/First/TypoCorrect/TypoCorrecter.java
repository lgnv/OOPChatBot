package First.TypoCorrect;

import java.util.Set;

public class TypoCorrecter {
    private CorrectStrategy strategy;
    private int typoCount;

    public TypoCorrecter(CorrectStrategy strategy) {
        this.strategy = strategy;
    }

    public int getTypoCount() { return typoCount; }

    public CorrectStrategy getStrategy() { return strategy; }

    public void setStrategy(CorrectStrategy newStrategy) {
        strategy = newStrategy;
    }

    public String execute(String word, Set<String> commands) {
        if (commands.contains(word)){
            return word;
        }
        var result = strategy.correctTypo(word, commands);
        if (result.equals(word)) {
            typoCount++;
        }
        return result;
    }
}


