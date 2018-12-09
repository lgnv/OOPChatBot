package First;

import First.BotLogic.TelegramBot;
import First.BotLogic.UserManagerFactory;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

public class TelegramVersion {	
	public static void main(String[] args) {
		ApiContextInitializer.init();
		TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
		try {
			telegramBotsApi.registerBot(new TelegramBot(UserManagerFactory.getDefaultUserManager()));
		} catch (TelegramApiRequestException e) {
			e.printStackTrace();
		}
	}
}