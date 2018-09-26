package First;

import java.util.HashMap;

public class GeneratorBot {
	public static Bot getBot() {
		var jokerFile = new JokeFromFile("top100.txt");
		var games = new HashMap<String, Game>() {{ put("виселица", new Hangman()); }};
		var jokerSite = new JokeFromSite("https://www.anekdot.ru/last/good/");
		return new Bot(games, jokerSite);
	}
}
