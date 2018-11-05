import First.BotLogic.GeneratorBot;
import First.BotLogic.User;
import First.BotLogic.UserFactory;
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
        var currentUser = UserFactory.getDefaultUser();
        currentUser.addListener(bot);
        assertTrue(currentUser.getCorrecter().getStrategy() instanceof DamerauLevensteinStrategy);
        assertEquals(null, bot.onMessage("шутка", currentUser)); // первая ошибка

        assertNotEquals(null, bot.onMessage("аникдот", currentUser));

        bot.onMessage("виселица", currentUser);
        bot.onMessage("а", currentUser);
        assertTrue(currentUser.getCorrecter().getStrategy() instanceof GameStrategy);
        assertTrue(currentUser.getIsPlaying());
        currentUser.sendMessage("выйти");
        bot.onMessage("кек", currentUser); // вторая ошибка
        assertFalse(currentUser.getCorrecter().getStrategy() instanceof GameStrategy);
        assertFalse(currentUser.getIsPlaying());
        assertEquals(null, bot.onMessage("шутка", currentUser)); // 3 ошибка -> стратегия изменилась на синонимы
        assertNotEquals(null, bot.onMessage("шутка", currentUser)); // уже не ошибка
    }
}
