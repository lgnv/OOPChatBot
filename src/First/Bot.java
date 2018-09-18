package First;

import java.util.Arrays;
import java.util.List;

public class Bot implements MessageListener {
	private static final String help = "Я игровой бот. Чтобы получить список игр, в которые я умею играть, напиши \"игры\".\n"
			+ "Для начала игры напиши её название.";
	private final List<String> games = Arrays.asList("Виселица");
	private boolean isPlayingNow = false;
	
	
	public static String start() {
		return "Привет, пользователь!\n" + help;
	}
	
	public void startOfGame() {
		isPlayingNow = true;
		
	}
	
	public void endOfGame() {
		isPlayingNow = false;
	}
	
	private String getGames() {
		var listGames = "Список игр:\n";
		for (var game : games) {
			listGames += game + '\n';
		}
		return listGames;
	}

	@Override
	public String onMessage(String message, User currentUser) {
		if (isPlayingNow)
			return null;
		if (message.equalsIgnoreCase("игры")) {
			return getGames();
		}
		else if (message.equalsIgnoreCase("виселица")) {
			return startPlayHangman(currentUser);
		}
		else if (message.equalsIgnoreCase("help")) {
			return help;
		}
		else {
			return "Извини, не понял тебя. Для получения справки напиши \"help\"";
		}
	}

	private String startPlayHangman(User currentUser) {
		startOfGame();
		var hangman = new Hangman(this);
		currentUser.addListener(hangman);
		return hangman.start();
	}
}
