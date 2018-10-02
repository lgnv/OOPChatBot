package First;

import java.util.LinkedList;

public interface JokeDownloader {
	LinkedList<String> getJokesList(String source);
	String getJoke();
}
