package First;

import java.util.HashMap;
import java.util.function.Function;

public class GeneratorBot {
	public static Bot getBot(JokeFilter jokeFilter) {
		var games = new HashMap<String, Function<User, String>>() {{ put("виселица", (User user)-> Hangman.play(user)); }};
		return new Bot(games, jokeFilter);
	}
}
