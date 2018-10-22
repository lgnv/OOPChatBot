package First.Features;

import First.BotLogic.User;

public interface Feature {
    String getCommand();
    String getDescription();
    String use(User user, String command);
}
