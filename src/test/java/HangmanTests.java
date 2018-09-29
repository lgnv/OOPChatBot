import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import First.Hangman;
import First.User;

class HangmanTests {
	
	@Test
	void testLetterInWord() {
		var hangman = getHangman();
		var user = new User(0);
		hangman.onMessage("л", user);
		assertTrue(hangman.getHP() == 6);
		assertTrue(hangman.getPositionsOfGuessed().size() == 1);
		assertTrue(hangman.getPositionsOfGuessed().get(0) == 1);
		assertTrue(hangman.getUsedLetters().size() == 1);
		assertTrue(hangman.getUsedLetters().get(0) == 'л');
	}
	
	@Test
	void testLetterNotInWord() {
		var hangman = getHangman();
		var user = new User(0);
		hangman.onMessage("а", user);
		assertTrue(hangman.getHP() == 5);
		assertTrue(hangman.getPositionsOfGuessed().size() == 0);
		assertTrue(hangman.getUsedLetters().size() == 1);
		assertTrue(hangman.getUsedLetters().get(0) == 'а');
	}
	
	@Test
	void testLetterInWordTwice() {
		var hangman = getHangman();
		hangman.onMessage("о", null);
		assertTrue(hangman.getHP() == 6);
		assertTrue(hangman.getPositionsOfGuessed().size() == 2);
		assertTrue(hangman.getPositionsOfGuessed().get(0) == 2);
		assertTrue(hangman.getPositionsOfGuessed().get(1) == 4);
		assertTrue(hangman.getUsedLetters().size() == 1);
		assertTrue(hangman.getUsedLetters().get(0) == 'о');
	}
	
	@Test
	void testLetterWasUsedBefore() {
		var hangman = getHangman();
		var user = new User(0);
		hangman.onMessage("а", user);
		assertTrue(hangman.getHP() == 5);
		assertTrue(hangman.getPositionsOfGuessed().size() == 0);
		assertTrue(hangman.getUsedLetters().size() == 1);
		assertTrue(hangman.getUsedLetters().get(0) == 'а');
		hangman.onMessage("а", user);
		assertTrue(hangman.getHP() == 5);
	}
	
	@Test
	void testGameOver() {
		var hangman = getHangman();
		var user = new User(0);
		hangman.onMessage("а", user);
		hangman.onMessage("q", user);
		hangman.onMessage("t", user);
		hangman.onMessage("w", user);
		hangman.onMessage("e", user);
		hangman.onMessage("r", user);
		assertTrue(hangman.getGameIsOver());
	}
	
	@Test
	void testRestartGame() {
		var hangman = getHangman();
		var user = new User(0);
		hangman.onMessage("о", user);
		assertTrue(hangman.getPositionsOfGuessed().size() == 2);
		assertTrue(hangman.getUsedLetters().size() == 1);
		hangman.onMessage("q", user);
		hangman.onMessage("t", user);
		hangman.onMessage("w", user);
		hangman.onMessage("e", user);
		hangman.onMessage("r", user);
		hangman.onMessage("f", user);
		assertTrue(hangman.getGameIsOver());
		hangman.onMessage("да", user);
		assertTrue(hangman.getPositionsOfGuessed().size() == 0);
		assertTrue(hangman.getUsedLetters().size() == 0);
	}
	
	private Hangman getHangman() {
		var h = new Hangman();
		try {
		       var field = h.getClass().getDeclaredField("word");
		       field.setAccessible(true);
		       field.set(h, (String)"слово");
		   } catch (NoSuchFieldException | IllegalAccessException e) {
		       e.printStackTrace();
		   }
		return h;
	}

}
