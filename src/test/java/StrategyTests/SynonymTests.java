package StrategyTests;

import static org.junit.jupiter.api.Assertions.*;

import First.TypoCorrect.YandexSynonymDownloader;
import org.junit.jupiter.api.Test;
import First.TypoCorrect.SynonymStrategy;
import java.util.HashSet;

public class SynonymTests {
    private SynonymStrategy strategy = new SynonymStrategy(new YandexSynonymDownloader());

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

    @Test
    void testCommandNotExistInVocabulary() {
        var commands = new HashSet<String>();
        commands.add("кек");
        assertEquals("шутка", strategy.correctTypo("шутка", commands));
    }

    private String getResultSynonym(String word){
        var commands = new HashSet<String>();
        commands.add("игра");
        commands.add("привет");
        commands.add("хорошо");
        return strategy.correctTypo(word, commands);
    }
}
