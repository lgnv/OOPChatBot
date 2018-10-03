package First;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

public class JokeFromFile implements JokeDownloader{
	private final String source = "top100.txt";

	public LinkedList<String> downloadJokesList() {
		var jokes = new LinkedList<String>();
		try(var br = new BufferedReader(new FileReader(source))){
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
			return jokes;
		} catch (IOException e) {
			return new LinkedList<>();
		}
	}
}
