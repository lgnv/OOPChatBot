import static org.junit.jupiter.api.Assertions.*;
import java.util.HashMap;
import java.util.function.Function;

import org.junit.jupiter.api.Test;

import First.Bot;
import First.Game;
import First.Hangman;
import First.JokeDownloader;
import First.JokeFromFile;
import First.User;

class BotTests {
	private HashMap<String, Function<User, String>> games = new HashMap<>() {{ put("виселица", (User user)-> Hangman.play(user)); }};
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
		 assertTrue(result.contains("виселица"));
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