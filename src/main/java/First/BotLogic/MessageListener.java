package First.BotLogic;

import First.TypoCorrect.TypoCorrecter;

public interface MessageListener {
	String onMessage(String message, User currentUser, TypoCorrecter correcter);
}
