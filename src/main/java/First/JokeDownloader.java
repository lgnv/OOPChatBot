package First;

import java.util.LinkedList;

public abstract class JokeDownloader {
	private LinkedList<String> jokes;
	
	protected abstract LinkedList<String> getJokesList(String source); 
	
	public JokeDownloader(String source) {
		jokes = getJokesList(source);
	}
	
	public String getJoke() {
		var joke = jokes.pollLast();
		return joke == null ? "На сегодня шутки закончились, прости" : joke;
	};
}
