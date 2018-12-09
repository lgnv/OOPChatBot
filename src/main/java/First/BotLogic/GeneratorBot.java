package First.BotLogic;

import First.Features.*;
import First.Games.Hangman;
import First.Jokes.JokeDownloader;
import First.Jokes.JokeFilter;
import First.Jokes.JokeFromFile;

import java.util.ArrayList;

public class GeneratorBot {

	public static Bot getBot(JokeDownloader jokeDownloader) {
		var games = new ArrayList<Feature>();
		games.add(new HangmanFeature(new Hangman()));
		var gamesMenu = new Menu(games, new HelpFeature(), "игра", "Получить список игр");
		var mainFeatures = new ArrayList<Feature>();
		mainFeatures.add(new JokeFeature(new JokeFilter(jokeDownloader)));
		mainFeatures.add(gamesMenu);
		var mainMenu = new Menu(mainFeatures, new HelpFeature());
		return new Bot(mainMenu);
	}

	public static Bot getConsoleBot()
	{
		return getBot(new JokeFromFile("top100.txt"));
	}
}
