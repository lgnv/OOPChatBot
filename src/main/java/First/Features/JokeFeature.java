package First.Features;

import First.BotLogic.User;
import First.Jokes.JokeFilter;

public class JokeFeature implements Feature {
    private JokeFilter jokeFilter;

    public JokeFeature(JokeFilter jokeFilter) {
        this.jokeFilter = jokeFilter;
    }

    public String getCommand(){
        return "анекдот";
    }

    public String getDescription(){
        return "Получить анекдот";
    }

    public String use(User user, String command){
        return jokeFilter.getJoke(user);
    }
}
