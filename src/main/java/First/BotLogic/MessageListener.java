package First.BotLogic;

import First.BotLogic.User;
import First.TypoCorrect.TypoCorrecter;

public interface MessageListener {
	String onMessage(String message, User currentUser, TypoCorrecter correcter);
}
