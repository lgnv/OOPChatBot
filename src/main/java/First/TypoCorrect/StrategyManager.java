package First.TypoCorrect;

import First.BotLogic.User;

public class StrategyManager {
    private CorrectStrategy previousStrategy;

    public void checkStrategy(User user) {
        var correcter = user.getCorrecter();
        var currentStrategy = correcter.getStrategy();
        if (correcter.getTypoCount() > 2 && !(currentStrategy instanceof GameStrategy)) {
            correcter.setStrategy(new SynonymStrategy(new YandexSynonymDownloader()));
        }
        if (user.getIsPlaying() && !(currentStrategy instanceof GameStrategy)) {
            previousStrategy = currentStrategy;
            correcter.setStrategy(new GameStrategy());
        }
        if (!user.getIsPlaying() && currentStrategy instanceof GameStrategy)
        {
            correcter.setStrategy(previousStrategy);
        }
    }
}
