import First.BotLogic.GeneratorBot;
import First.BotLogic.User;
import First.BotLogic.UserManager;
import First.Jokes.JokeFromFile;
import First.TypoCorrect.DamerauLevensteinStrategy;
import First.TypoCorrect.GameStrategy;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class IntegrationTests {
    @Test
    void testAllProgram() {
        var bot = GeneratorBot.getBot(new JokeFromFile("top100.txt"));
        var currentUser = UserManager.getDefaultUser();
        currentUser.addListener(bot);
        assertTrue(currentUser.getCorrecter().getStrategy() instanceof DamerauLevensteinStrategy);
        var result = bot.onMessage("шутка", currentUser);
        assertEquals(null, result);
        result = bot.onMessage("аникдот", currentUser);
        assertNotEquals(null, result);
        result = bot.onMessage("виселица", currentUser);
        result = bot.onMessage("а", currentUser);
        assertTrue(currentUser.getCorrecter().getStrategy() instanceof GameStrategy);
        assertTrue(currentUser.getIsPlaying());
        currentUser.sendMessage("выйти");
        result = bot.onMessage("кек", currentUser);
        assertFalse(currentUser.getCorrecter().getStrategy() instanceof GameStrategy);
        for (var i = 0; i < 3; i++)
            bot.onMessage("шутка", currentUser);
        assertNotEquals(null, bot.onMessage("шутка", currentUser));
    }
}
