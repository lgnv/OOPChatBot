package First;

import java.util.ArrayList;
import java.util.Arrays;

public class GeneratorBot {

	public static Bot getBot(JokeFilter jokeFilter) {
		var games = new ArrayList<Feature>();
		games.add(new Hangman());
		var gamesMenu = new Menu(games, "игры", "Получить список игр");
		var mainFeatures = new ArrayList<Feature>();
		mainFeatures.add(jokeFilter);
		mainFeatures.add(gamesMenu);
		var mainMenu = new Menu(mainFeatures);
		return new Bot(mainMenu);
	}
}
