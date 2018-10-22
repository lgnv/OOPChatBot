import static org.junit.jupiter.api.Assertions.*;

import First.BotLogic.Bot;
import First.BotLogic.GeneratorBot;
import First.BotLogic.User;
import First.Jokes.JokeDownloader;
import First.Jokes.JokeFromFile;
import First.TypoCorrect.LevensteinMetric;
import First.TypoCorrect.TypoCorrecter;
import org.junit.jupiter.api.Test;

class BotTests {
	private JokeDownloader jokeDownloader = new JokeFromFile("top100.txt");
	private TypoCorrecter correcter = new TypoCorrecter(new LevensteinMetric(255));
	
	private Bot getBot() {
		return GeneratorBot.getBot(jokeDownloader);
	}
	
	@Test
	void testUndefinedCommand() {
		var bot = getBot();
		var result = bot.onMessage("some_message", new User(0, correcter), correcter);
		assertNull(result);
	}
	
	 @Test
	 void testGetGames() {
		 var bot = getBot();
		 var result = bot.onMessage("игры", new User(0, correcter), correcter);
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
		 var result = bot.onMessage("помощь", new User(0, correcter), correcter);
		 assertTrue(result.contains("Получить"));
	 }

}