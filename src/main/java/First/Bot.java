package First;

import java.util.HashMap;
import java.util.function.Function;

public class Bot implements MessageListener {
	private static final String help = "Я развлекательный бот. Чтобы получить список игр, в которые я умею играть, напиши \"игры\".\n"
			+ "Для начала игры напиши её название.\n Чтобы получить свежую шутку напиши \"кек\"";
	private HashMap<String, Function<User, String>> games;
	private JokeDownloader joker;
	
	public Bot(HashMap<String, Function<User, String>> games, JokeDownloader joker) {
		this.games = games;
		this.joker = joker;
	}
	
	public static String start() {
		return "Привет, пользователь!\n" + help;
	}
	
	private String getGames() {
		var builder = new StringBuilder("Список игр:\n");
		for (var game : games.keySet()) {
			builder.append(game);
			builder.append('\n');
		}
		return builder.toString();
	}

	@Override
	public String onMessage(String message, User currentUser) {
	    var lowerMessage = message.toLowerCase();
		if (currentUser.getIsPlaying()){
			return null;
		}
		if (lowerMessage.equals("игры")) {
			return getGames();
		}
		if (games.keySet().contains(lowerMessage)) {
			currentUser.changeIsPlaying();
			return games.get(lowerMessage).apply(currentUser);
			
		}
		if (lowerMessage.equals("помощь")) {
			return help;
		}
		if (lowerMessage.equals("кек")) {
			return joker.getJoke();
		}
		return null;
	}
}
