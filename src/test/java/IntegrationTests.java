import First.BotLogic.GeneratorBot;
import First.BotLogic.UserFactory;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class IntegrationTests {
    private

    @Test
    void testFirstError() {
        var bot = GeneratorBot.getConsoleBot();
        var currentUser = UserFactory.getDefaultUser();
        currentUser.addListener(bot);
        var result = bot.onMessage("шутка", currentUser);
        assertNull(null, result); // первая ошибка
    }

    @Test
    void testFixedTypo() {
        var bot = GeneratorBot.getConsoleBot();
        var currentUser = UserFactory.getDefaultUser();
        currentUser.addListener(bot);
        var result = bot.onMessage("аникдот", currentUser);
        assertTrue(result.contains("а"));
    }

    @Test
    void testGameStrategy() {
        var bot = GeneratorBot.getConsoleBot();
        var currentUser = UserFactory.getDefaultUser();
        currentUser.addListener(bot);
        currentUser.sendMessage("виселица");
        currentUser.sendMessage("a");
        var receivedMessages = currentUser.getReceivedFromBotMessages();
        assertNotEquals(0, receivedMessages.size());
    }

    @Test
    void testChangeStrategy() {
        var bot = GeneratorBot.getConsoleBot();
        var currentUser = UserFactory.getDefaultUser();
        currentUser.addListener(bot);
        for (var i = 0; i < 3; i++) {
            var result = bot.onMessage("забава", currentUser);
            assertNull(null, result);
        }
        var result = bot.onMessage("забава", currentUser);
        assertTrue(result.contains("а")); // уже не ошибка
    }
}
