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
    void testGameStrategy() {
        var typoCorrecter = new TypoCorrecter(new GameStrategy());
        var commands = new HashSet<String>();
        commands.add("команда");
        assertEquals("команда", typoCorrecter.execute("команда", commands));
        assertNotEquals("указание", typoCorrecter.execute("команда", commands));
    }
}
