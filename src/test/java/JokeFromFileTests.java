import static org.junit.jupiter.api.Assertions.*;

import First.JokeFilter;
import First.JokeFromFile;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;


public class JokeFromFileTests {

    @Test
    void testGetOneJoke(){
        var joker = new JokeFromFile();
        var jokeFilter = new JokeFilter(joker);
        assertNotEquals("На сегодня шутки закончились, прости", jokeFilter.getJoke());
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

    String getLastJoke(int count) {
        var joker = new JokeFromFile();
        var jokeFilter = new JokeFilter(joker);
        IntStream.range(0, count - 1).forEach(i -> jokeFilter.getJoke());
        return jokeFilter.getJoke();
    }
}
