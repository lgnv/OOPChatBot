import static org.junit.jupiter.api.Assertions.*;
import java.util.HashMap;
import java.util.function.Function;

import First.*;
import org.junit.jupiter.api.Test;

class UserTests {
	private JokeDownloader jokerFile = new JokeFromFile();
	private JokeFilter jokeFilter = new JokeFilter(jokerFile);
	
	private Bot getBot() {
		return GeneratorBot.getBot(jokeFilter);
	}
	
	@Test
	void testSendMessage() {
		var bot = getBot();
		var user = new User(0);
		user.addListener(bot);
		user.sendMessage("игры");
		assertTrue(user.getReceivedFromBotMessages().size() > 0);
	}
	
	@Test
	void testWithoutListeners() {
		var bot = getBot();
		var user = new User(0);
		user.addListener(bot);
		user.removeListener(bot);
		user.sendMessage("message");
		assertTrue(user.getReceivedFromBotMessages().size() == 0);
	}

}
