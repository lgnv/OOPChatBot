package First;

import java.util.Map;

public class Bot implements MessageListener {
	private static final String help = "Я игровой бот. Чтобы получить список игр, в которые я умею играть, напиши \"игры\".\n"
			+ "Для начала игры напиши её название.";
	private Map<String, Game> games;
	
	public Bot(Map<String, Game> games) {
		this.games = games;
	}
	
	public static String start() {
		return "Привет, пользователь!\n" + help;
	}
	
	private String getGames() {
		var listGames = "Список игр:\n";
		for (var game : games.keySet()) {
			listGames += game + '\n';
		}
		return listGames;
	}

	@Override
	public String onMessage(String message, User currentUser) {
		if (message.equalsIgnoreCase("игры")) {
			return getGames();
		}
		else if (games.keySet().contains(message)) {
			return games.get(message).play(currentUser);
			
		}
		else if (message.equalsIgnoreCase("help")) {
			return help;
		}
		else {
			return null;
		}
	}
}
