import static org.junit.jupiter.api.Assertions.*;
import java.util.LinkedList;
import First.JokeDownloader;
import First.JokeFromSite;
import org.junit.jupiter.api.Test;

public class JokeFromSiteTests {

    @Test
    void testGetMillisecondToHours(){
        assertEquals(1, JokeFromSite.getMillisecondToHours(3600000, 0));
        assertEquals(0, JokeFromSite.getMillisecondToHours(3600000, 1));
        assertEquals(3, JokeFromSite.getMillisecondToHours(3600000 * 3, 0));
        assertEquals(24, JokeFromSite.getMillisecondToHours(3600000 * 24 + 1, 0));
    }

    @Test
    void testGetOneJoke(){
        var joker = new JokeFromSite("https://www.anekdot.ru/last/good/");
        assertNotEquals("На сегодня шутки закончились, прости", joker.getJoke());
    }

    @Test
    void testUpdateByTimerAfter24(){
        var joker = getJokerForTestsTimer(1000 * 60 * 60 * 24);
        assertNotEquals("На сегодня шутки закончились, прости", joker.getJoke());
    }

    @Test
    void testUpdateByTimerAfter25(){
        var joker = getJokerForTestsTimer(1000 * 60 * 60 * 25);
        assertNotEquals("На сегодня шутки закончились, прости", joker.getJoke());
    }

    @Test
    void testNotUpdateByTimerAfter23(){
        var joker = getJokerForTestsTimer(1000 * 60 * 60 * 23);
        assertEquals("На сегодня шутки закончились, прости", joker.getJoke());
    }

    private JokeDownloader getJokerForTestsTimer(long passedTime){
        var joker = new JokeFromSite("https://www.anekdot.ru/last/good/");
        long oldTimer;
        try{
            var fieldTimer = joker.getClass().getDeclaredField("timer");
            fieldTimer.setAccessible(true);
            oldTimer = (long) fieldTimer.get(joker);
            fieldTimer.set(joker, oldTimer - passedTime);
            var fieldJokes = joker.getClass().getDeclaredField("jokes");
            fieldJokes.setAccessible(true);
            fieldJokes.set(joker, new LinkedList<String>()); // обнулили список шуток
            assertEquals(0, ((LinkedList<String>)fieldJokes.get(joker)).size());
        } catch (NoSuchFieldException | IllegalAccessException e){
            e.printStackTrace();
        }
        return joker;
    }
}
