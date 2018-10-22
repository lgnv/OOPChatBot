package First.Jokes;

import First.Jokes.JokeDownloader;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.LinkedList;
import java.util.regex.Pattern;

public class JokeFromSite implements JokeDownloader {
	private final String source;

	public JokeFromSite(String source) {
		this.source = source;
	}

	public LinkedList<String> downloadJokesList() {
		try {
			var content = getContentOfHTTPPage(source);
			return getJokesFromHTML(content);
		} catch (Exception e) {
			return new LinkedList<>();
		}
	}

	private LinkedList<String> getJokesFromHTML(String content) {
		var jokes = new LinkedList<String>();
		var pattern = Pattern.compile("\"text\">([^href]*?)</div");
	    var matcher = pattern.matcher(content);
	    if(matcher.find()) {
	        do {
	            jokes.add(matcher.group(1));
	        } while(matcher.find(matcher.start()+1));
	    }
	    return jokes;
	}
	
	private static String getContentOfHTTPPage(String pageAddress) throws Exception {
        var builder = new StringBuilder();
        var pageURL = new URL(pageAddress);
        var connection = pageURL.openConnection();
        try(var reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
			String inputLine;
			while ((inputLine = reader.readLine()) != null) {
				builder.append(inputLine);
			}
		}
        return builder.toString();
    }
}
