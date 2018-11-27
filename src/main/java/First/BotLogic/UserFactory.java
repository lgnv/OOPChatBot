package First.BotLogic;

import First.TypoCorrect.*;

public class UserFactory {
    public static User getDefaultUser() {
        return new User(0,
                new TypoCorrecter(new DamerauLevensteinStrategy()),
                StrategyManagerFactory.create());
    }

    public static User getPlayingUser() {
        return new User(0,
                new TypoCorrecter(new GameStrategy()),
                StrategyManagerFactory.create());
    }
}
