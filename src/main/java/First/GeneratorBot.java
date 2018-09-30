package First;

import java.util.HashMap;
import java.util.function.Function;

public class GeneratorBot {
	public static Bot getBot() {
		var games = new HashMap<String, Function<User, String>>() {{ put("виселица", (User user)-> Hangman.play(user)); }};
		var jokerSite = new JokeFromSite("https://www.anekdot.ru/last/good/");
		return new Bot(games, jokerSite);
	}
}
