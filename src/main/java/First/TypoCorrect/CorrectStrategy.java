package First.TypoCorrect;

import java.util.Set;

public interface CorrectStrategy {
    String correctTypo(String word, Set<String> commands);
}
