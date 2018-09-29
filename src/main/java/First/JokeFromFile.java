package First;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

public class JokeFromFile implements JokeDownloader{
	private LinkedList<String> jokes;

	public JokeFromFile(String source) {
		jokes = getJokesList(source);
	}

	public LinkedList<String> getJokesList(String filename) {
		var jokes = new LinkedList<String>();
		try(var br = new BufferedReader(new FileReader(filename))){
			var builder = new StringBuilder();
			var countEmptyLines = 0;
			String line;
		    while((line=br.readLine())!=null){
		    	if (line.length() != 0)
		    	{
		    		countEmptyLines = 0;
		    		builder.append(line);
		    		builder.append("\n");
		    	}
		    	else {
		    		countEmptyLines++;
		    	}
		    	if (countEmptyLines == 4) {
		    		jokes.add(builder.toString());
		    		builder = new StringBuilder();
		    		countEmptyLines = 0;
		    	}
		    }
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return jokes;
	}

	public String getJoke() {
		var joke = jokes.pollLast();
		return joke == null ? "На сегодня шутки закончились, прости" : joke;
	}
}
