import static org.junit.jupiter.api.Assertions.*;
import java.util.LinkedList;
import First.JokeDownloader;
import First.User;
import First.JokeFilter;
import First.JokeFromSite;
import org.junit.jupiter.api.Test;

public class JokeFromSiteTests {

    @Test
    void testGetOneJoke(){
        var joker = new JokeFromSite();
        var jokeFilter = new JokeFilter(joker);
        var user = new User(0);
        assertNotEquals("На сегодня шутки закончились, прости", jokeFilter.getJoke(user));
    }
}
