import static org.junit.jupiter.api.Assertions.*;
import First.Jokes.JokeFromSite;
import First.Jokes.JokeFromFile;
import org.junit.jupiter.api.Test;

class JokeDownloaderTests {
    @Test
    void testDownloadJokesFromSite(){
        var jokeDownloader = new JokeFromSite("https://www.anekdot.ru/last/good/");
        var jokesList = jokeDownloader.downloadJokesList();
        assertTrue(jokesList.size() > 0);
    }

    @Test
    void testDownloadJokesFromFile() {
        var jokeDownloader = new JokeFromFile("top100.txt");
        var jokesList = jokeDownloader.downloadJokesList();
        assertTrue(jokesList.size() > 0);
    }
}
