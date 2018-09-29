package First;

import java.util.HashMap;

public class Bot implements MessageListener {
	private static final String help = "Я развлекательный бот. Чтобы получить список игр, в которые я умею играть, напиши \"игры\".\n"
			+ "Для начала игры напиши её название.\n Чтобы получить свежую шутку напиши \"кек\"";
	private HashMap<String, Game> games;
	private JokeDownloader joker;
	
	public Bot(HashMap<String, Game> games, JokeDownloader joker) {
		this.games = games;
		this.joker = joker;
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
		if (currentUser.getIsPlaying()){
			return null;
		}
		if (message.equalsIgnoreCase("игры")) {
			return getGames();
		}
		if (games.keySet().contains(message.toLowerCase())) {
			currentUser.changeIsPlaying();
			return games.get(message).play(currentUser);
			
		}
		if (message.equalsIgnoreCase("help")) {
			return help;
		}
		if (message.equalsIgnoreCase("кек")) {
			return joker.getJoke();
		}
		return null;
	}
}
