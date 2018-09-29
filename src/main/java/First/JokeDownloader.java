package First;

import java.util.LinkedList;

public interface JokeDownloader {
	public LinkedList<String> getJokesList(String source);
	public String getJoke();
}
