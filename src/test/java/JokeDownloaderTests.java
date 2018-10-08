import static org.junit.jupiter.api.Assertions.*;
import First.JokeFromSite;
import First.JokeFromFile;
import org.junit.jupiter.api.Test;

class JokeDownloaderTests {
    @Test
    void testDownloadJokesFromSite(){
        var jokeDownloader = new JokeFromSite();
        var jokesList = jokeDownloader.downloadJokesList();
        assertTrue(jokesList.size() > 0);
    }

    @Test
    void testDownloadJokesFromFile() {
        var jokeDownloader = new JokeFromFile();
        var jokesList = jokeDownloader.downloadJokesList();
        assertTrue(jokesList.size() > 0);
    }
}
