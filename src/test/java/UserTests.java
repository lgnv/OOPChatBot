import static org.junit.jupiter.api.Assertions.*;

import First.BotLogic.Bot;
import First.BotLogic.GeneratorBot;
import First.BotLogic.User;
import First.Jokes.JokeDownloader;
import First.Jokes.JokeFromFile;
import First.TypoCorrect.LevensteinMetric;
import First.TypoCorrect.TypoCorrecter;
import org.junit.jupiter.api.Test;

class UserTests {
	private JokeDownloader jokeDownloader = new JokeFromFile("top100.txt");
    private TypoCorrecter correcter = new TypoCorrecter(new LevensteinMetric(255));

	private Bot getBot() {
		return GeneratorBot.getBot(jokeDownloader);
	}
	
	@Test
	void testSendMessage() {
		var bot = getBot();
		var user = new User(0, correcter);
		user.addListener(bot);
		user.sendMessage("игры");
		assertTrue(user.getReceivedFromBotMessages().size() > 0);
	}
	
	@Test
	void testWithoutListeners() {
		var bot = getBot();
		var user = new User(0, correcter);
		user.addListener(bot);
		user.removeListener(bot);
		user.sendMessage("message");
		assertTrue(user.getReceivedFromBotMessages().size() == 0);
	}

}
