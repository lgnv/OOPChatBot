package First;

import java.util.HashMap;
import java.util.HashSet;

public class JokeFilter implements Feature {
    private HashMap<Integer, String> jokes = new HashMap<>();
    private JokeDownloader downloader;
    private TimeChecker timeChecker;


    public JokeFilter(JokeDownloader downloader) {
        this.downloader = downloader;
        updateJokes();
        timeChecker = new TimeChecker();
    }

    public String getCommand(){
        return "кек";
    }

    public String getDescription(){
        return "Получить анекдот";
    }

    public String use(User user, String command){
        return getJoke(user);
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
        user.learnedJoke(jokes.get(newHash));
        return jokes.get(newHash);
    }

    private void updateJokes(){
        var newJokes = downloader.downloadJokesList();
        for (var joke: newJokes) {
            jokes.put(joke.hashCode(), joke);
        }
    }
}
