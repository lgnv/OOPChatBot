package First;

public class HangmanFeature implements Feature {
    private Hangman hangman;

    public HangmanFeature(Hangman hangman) {
        this.hangman = hangman;
    }

    public String getCommand(){
        return "виселица";
    }

    public String getDescription(){
        return "Игра 'Виселица'";
    }

    public String use(User user, String command) {
        hangman.restartGame();
        user.changeIsPlaying();
        user.addListener(hangman);
        return hangman.start();
    }
}
