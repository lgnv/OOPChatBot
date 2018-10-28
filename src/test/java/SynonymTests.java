import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import First.TypoCorrect.SynonymStrategy;
import java.util.HashSet;

public class SynonymTests {
    @Test
    void testCorrectWord(){
        assertEquals("игра", getResultSynonym("игра"));
    }

    @Test
    void testNotSynonym(){
        assertEquals("Сериал", getResultSynonym("Сериал"));
    }

    @Test
    void testSynonym(){
        assertEquals("игра", getResultSynonym("забава"));
        assertEquals("привет", getResultSynonym("добрый вечер"));
        assertEquals("хорошо", getResultSynonym("отлично"));
    }

    private String getResultSynonym(String word){
        var commands = new HashSet<String>();
        commands.add("игра");
        commands.add("привет");
        commands.add("хорошо");
        var dl = new SynonymStrategy();
        return dl.correctTypo(word, commands);
    }
}
