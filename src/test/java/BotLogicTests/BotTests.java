package BotLogicTests;

import static org.junit.jupiter.api.Assertions.*;

import First.BotLogic.*;
import First.Jokes.JokeDownloader;
import First.Jokes.JokeFromFile;
import First.TypoCorrect.TypoCorrecter;
import First.TypoCorrect.DamerauLevensteinStrategy;
import org.junit.jupiter.api.Test;

class BotTests {
	private JokeDownloader jokeDownloader = new JokeFromFile("top100.txt");
	private TypoCorrecter correcter = new TypoCorrecter(new DamerauLevensteinStrategy());
	
	private Bot getBot() {
		return GeneratorBot.getBot(jokeDownloader);
	}
	
	@Test
	void testUndefinedCommand() {
		var bot = getBot();
		var result = bot.onMessage("some_message", UserFactory.getDefaultUser());
		assertNull(result);
	}
	
	 @Test
	 void testGetGames() {
		 var bot = getBot();
		 var result = bot.onMessage("игры", UserFactory.getDefaultUser());
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
		 var result = bot.onMessage("помощь", UserFactory.getDefaultUser());
		 assertTrue(result.contains("Получить"));
	 }

}