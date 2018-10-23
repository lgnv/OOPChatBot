package First.TypoCorrect;

import java.util.Set;

public class TypoCorrecter {
    private Metric metric;

    public TypoCorrecter(Metric metric) {
        this.metric = metric;
    }

    public String CorrectTypo(String word, Set<String> commands) {
        for (var command : commands) {
            if (metric.getDistance(word, command, -1) <= 2) {
                return command;
            }
        }
        return word;
    }
}
