package First;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Date;

public class JokeFromSite implements JokeDownloader{
	private long timer;
	private LinkedList<String> jokes;
	private String source;

	public JokeFromSite(String source) {
		jokes = getJokesList(source);
		this.source = source;
	}

	public String getJoke() {
		if (jokes.size() == 0 && getMillisecondToHours(timer, new Date().getTime()) >= 24){
			jokes = getJokesList(source);
		}
		var joke = jokes.pollLast();
		return joke == null ? "На сегодня шутки закончились, прости" : joke;
	}

	public LinkedList<String> getJokesList(String source) {
		timer = new Date().getTime();
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

    public static int getMillisecondToHours(long oldTime, long newTime){
		return (int)(Math.abs(newTime - oldTime)) / (1000 * 60 * 60);
	}
}
