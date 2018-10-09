import static org.junit.jupiter.api.Assertions.*;
import java.util.HashMap;
import java.util.function.Function;

import First.*;
import org.junit.jupiter.api.Test;

class BotTests {
	private JokeDownloader jokeDownloader = new JokeFromFile("top100.txt");
	
	private Bot getBot() {
		return GeneratorBot.getBot(jokeDownloader);
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
		 var result = bot.onMessage("помощь", new User(0));
		 assertTrue(result.contains("Получить"));
	 }

}
