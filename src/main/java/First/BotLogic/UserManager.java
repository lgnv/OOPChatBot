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

	public UserManager(TypoCorrecterFactory defaultCorrecterFactory, JokeDownloader jokeDownloader,
					   StrategyManager strategyManager){
		this.defaultCorrecterFactory = defaultCorrecterFactory;
		this.jokeDownloader = jokeDownloader;
		this.strategyManager = strategyManager;
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
