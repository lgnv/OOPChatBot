import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import First.TypoCorrect.DamerauLevensteinStrategy;
import java.util.HashSet;

public class DamerauLevensteinTests {

    @Test
    void testCorrectWord(){
        assertEquals("привет", getResultDamerauLevenstein("привет"));
    }

    @Test
    void testWithTypo(){
        assertEquals("пока", getResultDamerauLevenstein("пка"));
    }

    @Test
    void testTooManyTypos(){
        assertEquals("Превед", getResultDamerauLevenstein("Превед"));
    }

    @Test
    void testTypoPermutation(){
        assertEquals("привет", getResultDamerauLevenstein("рпивте"));
    }

    @Test
    void testIncorrectWord(){
        assertEquals("foobar", getResultDamerauLevenstein("foobar"));
    }

    private String getResultDamerauLevenstein(String word){
        var commands = new HashSet<String>();
        commands.add("привет");
        commands.add("хорошо");
        commands.add("пока");
        var dl = new DamerauLevensteinStrategy();
        return dl.correctTypo(word, commands);
    }
}
