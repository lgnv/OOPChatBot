package First.TypoCorrect;

import java.util.Set;

public class GameStrategy implements CorrectStrategy {
    @Override
    public String correctTypo(String word, Set<String> commands) {
        return word;
    }
}
