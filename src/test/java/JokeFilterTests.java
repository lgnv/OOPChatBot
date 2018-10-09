import static org.junit.jupiter.api.Assertions.*;

import First.JokeFilter;
import First.JokeFromFile;
import First.JokeFromSite;
import First.User;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;


class JokeFilterTests {
    private static final String lackOfJoke = "На сегодня шутки закончились, прости";

    @Test
    void testGetOneJoke() {
        assertNotEquals(lackOfJoke, getLastJoke(1));
    }

    @Test
    void testGetTenJokes() {
        assertNotEquals(lackOfJoke, getLastJoke(10));
    }

    @Test
    void testGetFiveHundredJokes() {
        assertNotEquals(lackOfJoke, getLastJoke(500));
    }

    @Test
    void testGetThousandJokes() {
        assertEquals(lackOfJoke, getLastJoke(1000));
    }

    private String getLastJoke(int count) {
        var user = new User(0);
        var jokeDownloader = new JokeFromFile("top100.txt");
        var jokeFilter = new JokeFilter(jokeDownloader);
        IntStream.range(0, count - 1).forEach(i -> jokeFilter.getJoke(user));
        return jokeFilter.getJoke(user);
    }
}
