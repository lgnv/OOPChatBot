package First.BotLogic;

import First.BotLogic.Bot;
import First.Features.Feature;
import First.Features.HangmanFeature;
import First.Features.Menu;
import First.Games.Hangman;
import First.Jokes.JokeDownloader;
import First.Jokes.JokeFeature;
import First.Jokes.JokeFilter;
import First.TypoCorrect.LevensteinMetric;
import First.TypoCorrect.TypoCorrecter;

import java.util.ArrayList;

public class GeneratorBot {

	public static Bot getBot(JokeDownloader jokeDownloader) {
		var games = new ArrayList<Feature>();
		games.add(new HangmanFeature(new Hangman()));
		var gamesMenu = new Menu(games, "игры", "Получить список игр");
		var mainFeatures = new ArrayList<Feature>();
		mainFeatures.add(new JokeFeature(new JokeFilter(jokeDownloader)));
		mainFeatures.add(gamesMenu);
		var mainMenu = new Menu(mainFeatures);
		return new Bot(mainMenu);
	}
}
