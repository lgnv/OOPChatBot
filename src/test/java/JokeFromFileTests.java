import static org.junit.jupiter.api.Assertions.*;
import First.JokeFromFile;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;


public class JokeFromFileTests {

    @Test
    void testGetOneJoke(){
        var joker = new JokeFromFile("top100.txt");
        assertNotEquals("На сегодня шутки закончились, прости", joker.getJoke());
    }

    @Test
    void testGetTenJokes() {
        assertNotEquals("На сегодня шутки закончились, прости", getLastJoke(10));
    }

    @Test
    void testGetFiveHundredJokes() {
        assertNotEquals("На сегодня шутки закончились, прости", getLastJoke(500));
    }

    @Test
    void testGetThousandJokes() {
        assertEquals("На сегодня шутки закончились, прости", getLastJoke(1000));
    }

    @Test
    void testGetMillionJokes() {
        assertEquals("На сегодня шутки закончились, прости", getLastJoke(1000000));
    }

    @Test
    void testWrongSource(){
        var joker = new JokeFromFile("foobar");
        assertEquals("На сегодня шутки закончились, прости", joker.getJoke());
    }

    String getLastJoke(int count) {
        var joker = new JokeFromFile("top100.txt");
        IntStream.range(0, count - 1).forEach(i -> joker.getJoke());
        return joker.getJoke();
    }
}
