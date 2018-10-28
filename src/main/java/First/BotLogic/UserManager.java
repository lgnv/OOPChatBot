package First.BotLogic;

import First.Jokes.JokeDownloader;
import First.Jokes.JokeFromSite;
import First.TypoCorrect.LevensteinStrategy;
import First.TypoCorrect.SynonymStrategy;
import First.TypoCorrect.TypoCorrecter;

import java.util.HashMap;

public class UserManager {
	private HashMap<Long, User> users = new HashMap<Long, User>();
	private JokeDownloader jokeDownloader = new JokeFromSite("https://www.anekdot.ru/last/good/");

	private void updateUsers(Long userId) {
		if (!users.containsKey(userId)){
			var user = new User(userId, new TypoCorrecter(new SynonymStrategy()));
			user.addListener(GeneratorBot.getBot(jokeDownloader));
			users.put(userId, user);
		}
	}
	
	public User getUser(Long userId) {
		updateUsers(userId);
		return users.get(userId);
	}
}
