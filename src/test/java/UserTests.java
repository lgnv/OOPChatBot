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

class UserTests {
	private HashMap<String, Function<User, String>> games = new HashMap<>() {{ put("виселица", (User user)-> Hangman.play(user)); }};
	private JokeDownloader jokerFile = new JokeFromFile("top100.txt");
	
	private Bot getBot() {
		return new Bot(games, jokerFile);
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
