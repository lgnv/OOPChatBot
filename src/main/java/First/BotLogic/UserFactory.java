package First.BotLogic;

import First.Jokes.JokeFromSite;
import First.TypoCorrect.DamerauLevensteinStrategy;
import First.TypoCorrect.StrategyManager;
import First.TypoCorrect.TypoCorrecter;
import First.TypoCorrect.TypoCorrecterFactory;

public class UserFactory {
    public static User getDefaultUser() {
        return new User(0,
                new TypoCorrecter(new DamerauLevensteinStrategy()),
                new StrategyManager());
    }

    public static UserManager getDefaultUserManager() {
        return new UserManager(new TypoCorrecterFactory(),
                new JokeFromSite("https://www.anekdot.ru/last/good/"),
                new StrategyManager());
    }
}
