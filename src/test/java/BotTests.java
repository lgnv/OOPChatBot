import static org.junit.jupiter.api.Assertions.*;
import java.util.HashMap;
import org.junit.jupiter.api.Test;

import First.Bot;
import First.Game;
import First.Hangman;
import First.JokeDownloader;
import First.JokeFromFile;
import First.User;

class BotTests {
	private HashMap<String, Game> games = new HashMap<String, Game>() {{ put("Виселица", new Hangman()); }};
	private JokeDownloader jokerFile = new JokeFromFile("top100.txt");
	
	private Bot getBot() {
		return new Bot(games, jokerFile);
	}
	
	@Test
	void testUndefinedCommand() {
		var bot = getBot();
		var result = bot.onMessage("some_message", new User(0));
		assertNull(result);
	}
	
	 @Test
	 void testGetGames() {
		 var bot = getBot();
		 var result = bot.onMessage("игры", new User(0));
		 assertTrue(result.contains("Виселица"));
	 }
	 
	 @Test
	 void testStart() {
		 var result = Bot.start();
		 assertTrue(result.contains("Привет"));
	 }
	 
	 @Test
	 void testHelp() {
		 var bot = getBot();
		 var result = bot.onMessage("help", new User(0));
		 assertTrue(result.contains("напиши"));
	 }

}
