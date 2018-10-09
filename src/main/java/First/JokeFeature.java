package First;

public class JokeFeature implements Feature {
    private JokeFilter jokeFilter;

    public JokeFeature(JokeFilter jokeFilter) {
        this.jokeFilter = jokeFilter;
    }

    public String getCommand(){
        return "кек";
    }

    public String getDescription(){
        return "Получить анекдот";
    }

    public String use(User user, String command){
        return jokeFilter.getJoke(user);
    }
}
