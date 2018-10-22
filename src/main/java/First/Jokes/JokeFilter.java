package First.Jokes;

import First.BotLogic.TimeChecker;
import First.BotLogic.User;

import java.util.HashMap;
import java.util.HashSet;

public class JokeFilter {
    private HashMap<Integer, String> jokes = new HashMap<>();
    private JokeDownloader downloader;
    private TimeChecker timeChecker;

    public JokeFilter(JokeDownloader downloader) {
        this.downloader = downloader;
        updateJokes();
        timeChecker = new TimeChecker();
    }

    public String getJoke(User user) {
        if (downloader instanceof JokeFromSite && timeChecker.needToUpdate()) {
            updateJokes();
        }
        var hashesJokesUserDontKnow = new HashSet<>(jokes.keySet());
        hashesJokesUserDontKnow.removeAll(user.getHashesReceivedJokes());
        Integer newHash = null;
        for (var h: hashesJokesUserDontKnow) {
            newHash = h;
            break;
        }
        if (newHash == null){
            return "На сегодня шутки закончились, прости";
        }
        user.learnJoke(jokes.get(newHash));
        return jokes.get(newHash);
    }

    private void updateJokes(){
        var newJokes = downloader.downloadJokesList();
        for (var joke: newJokes) {
            jokes.put(joke.hashCode(), joke);
        }
    }
}
