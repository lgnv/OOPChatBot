package First.TypoCorrect;

import First.BotLogic.User;

public class StrategyManager {
    private CorrectStrategy previousStrategy;
    private IStrategyFactory synonymStrategyFactory;
    private IStrategyFactory gameStrategyFactory;

    public StrategyManager(IStrategyFactory synonymStrategyFactory,
                           IStrategyFactory gameStrategyFactory)
    {
        this.synonymStrategyFactory = synonymStrategyFactory;
        this.gameStrategyFactory = gameStrategyFactory;
    }

    public void checkStrategy(User user) {
        var correcter = user.getCorrecter();
        var currentStrategy = correcter.getStrategy();
        if (correcter.getTypoCount() > 2) {
            correcter.setStrategy(synonymStrategyFactory.create());
        }
        if (user.getIsPlaying()) {
            previousStrategy = currentStrategy;
            correcter.setStrategy(gameStrategyFactory.create());
        }
        else if (previousStrategy != null)
        {
            correcter.setStrategy(previousStrategy);
        }
    }
}


