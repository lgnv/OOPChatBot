package First;

import java.util.Arrays;
import java.util.List;

public class Bot implements MessageListener {
	private final String help = "Я игровой бот. Чтобы получить список игр, в которые я умею играть, напиши \"игры\".\n"
			+ "Для начала игры напиши её название.";
	private final List<String> games = Arrays.asList("Виселица");
	private boolean isPlayingNow = false;
	
	
	public String start() {
		return "Привет, пользователь!\n" + help;
	}
	
	public void endOfGame() {
		isPlayingNow = false;
	}
	
	private String getGames() {
		var listGames = "Список игр:\n";
		for (String game : games) {
			listGames += game + '\n';
		}
		return listGames;
	}

	@Override
	public String onMessage(String message, User currentUser) {
		if (isPlayingNow)
			return "";
		if (message.equalsIgnoreCase("игры")) {
			return getGames();
		}
		else if (message.equalsIgnoreCase("Виселица")) {
			isPlayingNow = true;
			Hangman hangman = new Hangman(this);
			currentUser.addListener(hangman);
			return hangman.start();
		}
		else {
			return "Извини, не понял тебя.\n" + help;
		}
		//System.out.println("Сообщение получил! " + message);
	}
}
