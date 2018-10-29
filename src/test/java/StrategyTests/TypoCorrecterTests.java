package StrategyTests;

import First.TypoCorrect.DamerauLevensteinStrategy;
import First.TypoCorrect.GameStrategy;
import First.TypoCorrect.TypoCorrecter;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;


public class TypoCorrecterTests {
    @Test
    void testReplaceStrategy() {
        var typoCorrecter = new TypoCorrecter(new DamerauLevensteinStrategy());
        var commands = new HashSet<String>();
        commands.add("игра");
        commands.add("привет");
        commands.add("хорошо");
        var otherWord = "добрый вечер";
        for (var i = 0; i < 3; i++) {
            assertEquals(otherWord, typoCorrecter.execute(otherWord, commands));
        }
        assertEquals("привет", typoCorrecter.execute(otherWord, commands));
    }

    @Test
    void testGameStrategy() {
        var typoCorrecter = new TypoCorrecter(new GameStrategy());
        var commands = new HashSet<String>();
        commands.add("команда");
        assertEquals("команда", typoCorrecter.execute("команда", commands));
        assertNotEquals("указание", typoCorrecter.execute("команда", commands));
    }
}
