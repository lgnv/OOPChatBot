package First;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;

import org.junit.jupiter.api.Test;

class UserTests {
	private HashMap<String, Game> games = new HashMap<String, Game>() {{ put("Виселица", new Hangman()); }};
	
	private Bot getBot() {
		return new Bot(games);
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
