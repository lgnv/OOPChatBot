package First;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class HangmanTests {
	
	@Test
	void testLetterInWord() {
		var hangman = getHangman();
		hangman.onMessage("л", null);
		assertTrue(hangman.getHP() == 6);
		assertTrue(hangman.getPositionsOfGuessed().size() == 1);
		assertTrue(hangman.getPositionsOfGuessed().get(0) == 1);
		assertTrue(hangman.getUsedLetters().size() == 1);
		assertTrue(hangman.getUsedLetters().get(0) == 'л');
	}
	
	@Test
	void testLetterNotInWord() {
		var hangman = getHangman();
		hangman.onMessage("а", null);
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
		hangman.onMessage("а", null);
		assertTrue(hangman.getHP() == 5);
		assertTrue(hangman.getPositionsOfGuessed().size() == 0);
		assertTrue(hangman.getUsedLetters().size() == 1);
		assertTrue(hangman.getUsedLetters().get(0) == 'а');
		hangman.onMessage("а", null);
		assertTrue(hangman.getHP() == 5);
	}
	
	@Test
	void testGameOver() {
		var hangman = getHangman();
		hangman.onMessage("а", null);
		hangman.onMessage("q", null);
		hangman.onMessage("t", null);
		hangman.onMessage("w", null);
		hangman.onMessage("e", null);
		hangman.onMessage("r", null);
		assertTrue(hangman.getGameIsOver());
	}
	
	@Test
	void testRestartGame() {
		var hangman = getHangman();
		hangman.onMessage("о", null);
		assertTrue(hangman.getPositionsOfGuessed().size() == 2);
		assertTrue(hangman.getUsedLetters().size() == 1);
		hangman.onMessage("q", null);
		hangman.onMessage("t", null);
		hangman.onMessage("w", null);
		hangman.onMessage("e", null);
		hangman.onMessage("r", null);
		hangman.onMessage("f", null);
		assertTrue(hangman.getGameIsOver());
		hangman.onMessage("да", null);
		assertTrue(hangman.getPositionsOfGuessed().size() == 0);
		assertTrue(hangman.getUsedLetters().size() == 0);
		
	}
	private Hangman getHangman() {
		var h = new Hangman(null);
		try {
		       var field = h.getClass().getDeclaredField("word");
		       field.setAccessible(true);
		       field.set(h, (String)"слово");;
		   } catch (NoSuchFieldException | IllegalAccessException e) {
		       e.printStackTrace();
		   }
		return h;
	}

}
