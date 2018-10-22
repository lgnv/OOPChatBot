package First.BotLogic;

import First.Jokes.JokeDownloader;
import First.Jokes.JokeFromSite;
import First.TypoCorrect.LevensteinMetric;
import First.TypoCorrect.TypoCorrecter;

import java.util.HashMap;

public class UserManager {
	private HashMap<Long, User> users = new HashMap<Long, User>();
	private JokeDownloader jokeDownloader = new JokeFromSite("https://www.anekdot.ru/last/good/");
	private TypoCorrecter correcter = new TypoCorrecter(new LevensteinMetric(255));

	private void updateUsers(Long userId) {
		if (!users.containsKey(userId)){
			var user = new User(userId, correcter);
			user.addListener(GeneratorBot.getBot(jokeDownloader));
			users.put(userId, user);
		}
	}
	
	public User getUser(Long userId) {
		updateUsers(userId);
		return users.get(userId);
	}
}
