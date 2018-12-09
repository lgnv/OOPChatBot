package First.BotLogic;

import First.Jokes.JokeFromSite;
import First.TypoCorrect.StrategyManagerFactory;
import First.TypoCorrect.TypoCorrecterFactory;

public class UserManagerFactory {
    public static UserManager getDefaultUserManager() {
        return new UserManager(new TypoCorrecterFactory(),
                new JokeFromSite("https://www.anekdot.ru/last/good/"),
                StrategyManagerFactory.create());
    }
}
