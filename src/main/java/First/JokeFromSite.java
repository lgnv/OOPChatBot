package First;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JokeFromSite extends JokeDownloader{	
	public JokeFromSite(String source) {
		super(source);
	}

	protected LinkedList<String> getJokesList(String source) {
		String content = "";
		try {
			content = getContentOfHTTPPage(source);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return getJokesFromHTML(content);
	}

	private LinkedList<String> getJokesFromHTML(String content) {
		var jokes = new LinkedList<String>();
		Pattern pattern = Pattern.compile("\"text\">([^href]*?)</div");
	    Matcher matcher = pattern.matcher(content);
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
        var reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        try {
            String inputLine;
            while ((inputLine = reader.readLine()) != null) {
                builder.append(inputLine);
            }
        } finally {
            reader.close();
        }
        return builder.toString();
    }
}
