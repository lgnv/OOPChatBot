import static org.junit.jupiter.api.Assertions.*;

import First.TypoCorrect.LevensteinMetric;
import First.TypoCorrect.TypoCorrecter;
import org.junit.jupiter.api.Test;

import First.Games.Hangman;
import First.BotLogic.User;

class HangmanTests {
    private TypoCorrecter correcter = new TypoCorrecter(new LevensteinMetric(255));

	@Test
	void testLetterInWord() {
		var hangman = getHangman();
		var user = new User(0, correcter);
		hangman.onMessage("л", user, correcter);
		assertEquals(hangman.getHP(), 6);
		assertEquals(hangman.getPositionsOfGuessed().size(), 1);
		assertEquals((int)hangman.getPositionsOfGuessed().get(0), 1);
		assertEquals(hangman.getUsedLetters().size(), 1);
		assertEquals((char)hangman.getUsedLetters().get(0), 'л');
	}
	
	@Test
	void testLetterNotInWord() {
		var hangman = getHangman();
		var user = new User(0, correcter);
		hangman.onMessage("а", user, correcter);
		assertEquals(hangman.getHP(), 5);
		assertEquals(hangman.getPositionsOfGuessed().size(), 0);
		assertEquals(hangman.getUsedLetters().size(), 1);
		assertEquals((char)hangman.getUsedLetters().get(0),'а');
	}
	
	@Test
	void testLetterInWordTwice() {
		var hangman = getHangman();
		hangman.onMessage("о", null, correcter);
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
		var user = new User(0, correcter);
		hangman.onMessage("а", user, correcter);
		assertEquals(hangman.getHP(), 5);
		assertEquals(hangman.getPositionsOfGuessed().size(), 0);
		assertEquals(hangman.getUsedLetters().size(), 1);
		assertEquals((char)hangman.getUsedLetters().get(0), 'а');
		hangman.onMessage("а", user, correcter);
		assertEquals(hangman.getHP(), 5);
	}
	
	@Test
	void testGameOver() {
		var hangman = getHangman();
		var user = new User(0, correcter);
		hangman.onMessage("а", user, correcter);
		hangman.onMessage("q", user, correcter);
		hangman.onMessage("t", user, correcter);
		hangman.onMessage("w", user, correcter);
		hangman.onMessage("e", user, correcter);
		hangman.onMessage("r", user, correcter);
		assertTrue(hangman.getGameIsOver());
	}
	
	@Test
	void testRestartGame() {
		var hangman = getHangman();
		var user = new User(0, correcter);
		hangman.onMessage("о", user, correcter);
		assertEquals(hangman.getPositionsOfGuessed().size(), 2);
		assertEquals(hangman.getUsedLetters().size(), 1);
		hangman.onMessage("q", user, correcter);
		hangman.onMessage("t", user, correcter);
		hangman.onMessage("w", user, correcter);
		hangman.onMessage("e", user, correcter);
		hangman.onMessage("r", user, correcter);
		hangman.onMessage("f", user, correcter);
		assertTrue(hangman.getGameIsOver());
		hangman.onMessage("да", user, correcter);
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
		var user = new User(0, correcter);
		var result = h.onMessage("f", user, correcter);
		assertTrue(result.contains("Осталось попыток: 5"));
		assertTrue(result.contains("Слово: _ _ _ _ _"));
		assertTrue(result.contains("Использованные буквы: f, "));
	}

	@Test
	void testWin(){
		var h = getHangman();
		var user = new User(0, correcter);
		h.onMessage("с", user, correcter);
		h.onMessage("л", user, correcter);
		h.onMessage("о", user, correcter);
		var result = h.onMessage("в", user, correcter);
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
