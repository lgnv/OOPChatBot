package First;

import java.util.Arrays;
import java.util.List;

public class Bot implements MessageListener {
	private final String help = "Я игровой бот. Чтобы получить список игр, в которые я умею играть, напиши \"игры\".\n"
			+ "Для начала игры напиши её название.";
	private final List<String> games = Arrays.asList("Виселица");
	private boolean isPlayingNow = false;
	
	public Bot() {	
		start();
	}
	
	public void start() {
		System.out.println("Привет, пользователь!");
		System.out.println(help);
	}
	
	public void getGames() {
		System.out.println("Список игр: ");
		for (String game : games) {
			System.out.println(game);
		}
	}

	@Override
	public void onMessage(String message, User currentUser) {
		if (isPlayingNow)
			return;
		if (message.equalsIgnoreCase("игры")) {
			getGames();
		}
		else if (message.equalsIgnoreCase("Виселица")) {
			isPlayingNow = true;
			Hangman hangman = new Hangman();
			currentUser.addListener(hangman);
		}
		else {
			//направить на help
		}
		//System.out.println("Сообщение получил! " + message);
	}
}
