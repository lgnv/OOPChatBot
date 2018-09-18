package First;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class BotTests {

	@Test
	void testOpenHangman() {
		var bot = new Bot();
		var user = new User(0);
		bot.onMessage("виселица", user);
		assertTrue(user.getListenersCount() == 1);
	}
	
	@Test
	void testBotIsBusy() {
		var bot = new Bot();
		bot.startOfGame();
		var result = bot.onMessage("some_message", null);
		assertTrue(result == null);
	}
	
	@Test 
	void testBotIsNotBusy() {
		var bot = new Bot();
		bot.startOfGame();
		bot.endOfGame();
		var result = bot.onMessage("some_message", null);
		assertTrue(result != null);
	}
	
	 @Test
	 void testGetGames() {
		 var bot = new Bot();
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
		 var bot = new Bot();
		 var result = bot.onMessage("help", null);
		 assertTrue(result.contains("напиши"));
	 }

}
