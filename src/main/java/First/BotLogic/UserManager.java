package First.BotLogic;

import First.Jokes.JokeDownloader;
import First.Jokes.JokeFromSite;
import First.TypoCorrect.*;

import java.util.HashMap;

public class UserManager {
	private HashMap<Long, User> users = new HashMap<>();
	private JokeDownloader jokeDownloader;
	private TypoCorrecterFactory defaultCorrecterFactory;
	private StrategyManager strategyManager;

	public UserManager() {
		this(new TypoCorrecterFactory(),
				new JokeFromSite("https://www.anekdot.ru/last/good/"),
				new StrategyManager());
	}

	public UserManager(TypoCorrecterFactory defaultCorrecterFactory, JokeDownloader jokeDownloader,
					   StrategyManager strategyManager){
		this.defaultCorrecterFactory = defaultCorrecterFactory;
		this.jokeDownloader = jokeDownloader;
		this.strategyManager = strategyManager;
	}

	public static User getDefaultUser() {
		return new User(0,
				new TypoCorrecter(new DamerauLevensteinStrategy()),
				new StrategyManager());
	}

	private void updateUsers(Long userId) {
		if (!users.containsKey(userId)){
			var user = new User(userId, defaultCorrecterFactory.create(), strategyManager);
			user.addListener(GeneratorBot.getBot(jokeDownloader));
			users.put(userId, user);
		}
	}
	
	public User getUser(Long userId) {
		updateUsers(userId);
		return users.get(userId);
	}
}
