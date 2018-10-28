package First.Features;

import First.Games.Hangman;
import First.BotLogic.User;
import First.TypoCorrect.GameStrategy;

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
        user.getCorrecter().setStrategy(new GameStrategy());
        return hangman.start();
    }
}
