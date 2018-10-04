package First;

import java.util.ArrayList;

public class GeneratorBot {

	public static Bot getBot(JokeFilter jokeFilter) {
		var a = new ArrayList<Feature>();
		a.add(jokeFilter);
		a.add(new Hangman());
		var menu = new Menu(a);
		return new Bot(menu);
	}
}
