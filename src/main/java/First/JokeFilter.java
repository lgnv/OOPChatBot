package First;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

public class JokeFilter implements Feature {
    private LinkedList<String> jokes;
    private JokeDownloader downloader;
    private TimeChecker timeChecker;
    private HashMap<Long, HashSet<Integer>> userHashes = new HashMap<>();

    public JokeFilter(JokeDownloader downloader) {
        this.downloader = downloader;
        jokes = downloader.downloadJokesList();
        timeChecker = new TimeChecker();
    }

    public String getCommand(){
        return "кек";
    }

    public String getDescription(){
        return "Получить анекдот";
    }

    public String use(User user, String command){
        return getJoke();
    }

    private boolean isNewJoke(String joke) {
        return true;
    }

    public String getJoke() {
        if (downloader instanceof JokeFromSite && timeChecker.needToUpdate()) {
            var newJokes = downloader.downloadJokesList();
            jokes.removeAll(newJokes);
            jokes.addAll(newJokes);
        }
        String joke = null;
        do {
            joke = jokes.pollLast();
        }
        while (!isNewJoke(joke));
        return joke == null ? "На сегодня шутки закончились, прости" : joke;
    }
}
