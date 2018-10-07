import static org.junit.jupiter.api.Assertions.*;

import First.JokeFilter;
import First.JokeFromFile;
import First.User;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;


public class JokeFromFileTests {

    @Test
    void testGetOneJoke(){
        assertNotEquals("На сегодня шутки закончились, прости", getLastJoke(1));
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

    private String getLastJoke(int count) {
        var user = new User(0);
        var joker = new JokeFromFile();
        var jokeFilter = new JokeFilter(joker);
        IntStream.range(0, count - 1).forEach(i -> jokeFilter.getJoke(user));
        return jokeFilter.getJoke(user);
    }
}
