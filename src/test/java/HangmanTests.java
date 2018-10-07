import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import First.Hangman;
import First.User;

import java.util.ArrayList;

class HangmanTests {
	
	@Test
	void testLetterInWord() {
		var hangman = getHangman();
		var user = new User(0);
		hangman.onMessage("л", user);
		assertEquals(hangman.getHP(), 6);
		assertEquals(hangman.getPositionsOfGuessed().size(), 1);
		assertEquals((int)hangman.getPositionsOfGuessed().get(0), 1);
		assertEquals(hangman.getUsedLetters().size(), 1);
		assertEquals((char)hangman.getUsedLetters().get(0), 'л');
	}
	
	@Test
	void testLetterNotInWord() {
		var hangman = getHangman();
		var user = new User(0);
		hangman.onMessage("а", user);
		assertEquals(hangman.getHP(), 5);
		assertEquals(hangman.getPositionsOfGuessed().size(), 0);
		assertEquals(hangman.getUsedLetters().size(), 1);
		assertEquals((char)hangman.getUsedLetters().get(0),'а');
	}
	
	@Test
	void testLetterInWordTwice() {
		var hangman = getHangman();
		hangman.onMessage("о", null);
		assertEquals(hangman.getHP(), 6);
		assertEquals(hangman.getPositionsOfGuessed().size(), 2);
		assertEquals((int)hangman.getPositionsOfGuessed().get(0), 2);
		assertEquals((int)hangman.getPositionsOfGuessed().get(1), 4);
		assertEquals(hangman.getUsedLetters().size(), 1);
		assertEquals((int)hangman.getUsedLetters().get(0), 'о');
	}
	
	@Test
	void testLetterWasUsedBefore() {
		var hangman = getHangman();
		var user = new User(0);
		hangman.onMessage("а", user);
		assertEquals(hangman.getHP(), 5);
		assertEquals(hangman.getPositionsOfGuessed().size(), 0);
		assertEquals(hangman.getUsedLetters().size(), 1);
		assertEquals((char)hangman.getUsedLetters().get(0), 'а');
		hangman.onMessage("а", user);
		assertEquals(hangman.getHP(), 5);
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
		assertEquals(hangman.getPositionsOfGuessed().size(), 2);
		assertEquals(hangman.getUsedLetters().size(), 1);
		hangman.onMessage("q", user);
		hangman.onMessage("t", user);
		hangman.onMessage("w", user);
		hangman.onMessage("e", user);
		hangman.onMessage("r", user);
		hangman.onMessage("f", user);
		assertTrue(hangman.getGameIsOver());
		hangman.onMessage("да", user);
		assertEquals(hangman.getPositionsOfGuessed().size(), 0);
		assertEquals(hangman.getUsedLetters().size(), 0);
	}

	@Test
	void testFileExist() {
		var h = new Hangman();
		assertNotEquals(0, h.getWordsFromFile("top100.txt").size());
	}

	@Test
	void testFileNotExist(){
		var h = new Hangman();
		assertEquals(0, h.getWordsFromFile("foobar").size());
	}

	@Test
	void testGetGameStatus(){
		var h = getHangman();
		var user = new User(0);
		var result = h.onMessage("f", user);
		assertTrue(result.contains("Осталось попыток: 5"));
		assertTrue(result.contains("Слово: _ _ _ _ _"));
		assertTrue(result.contains("Использованные буквы: f, "));
	}

	@Test
	void testWin(){
		var h = getHangman();
		var user = new User(0);
		h.onMessage("с", user);
		h.onMessage("л", user);
		h.onMessage("о", user);
		var result = h.onMessage("в", user);
		assertTrue(result.contains("Урааа, ты отгадал слово!!! "));
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
