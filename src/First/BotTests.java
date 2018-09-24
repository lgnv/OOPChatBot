package First;

import static org.junit.jupiter.api.Assertions.*;
import java.util.HashMap;
import org.junit.jupiter.api.Test;

class BotTests {
	private HashMap<String, Game> games = new HashMap<String, Game>() {{ put("Виселица", new Hangman()); }};
	
	private Bot getBot() {
		return new Bot(games);
	}
	
	@Test
	void testUndefinedCommand() {
		var bot = getBot();
		var result = bot.onMessage("some_message", null);
		assertTrue(result == null);
	}
	
	 @Test
	 void testGetGames() {
		 var bot = getBot();
		 var result = bot.onMessage("игры", null);
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
		 var result = bot.onMessage("help", null);
		 assertTrue(result.contains("напиши"));
	 }

}
