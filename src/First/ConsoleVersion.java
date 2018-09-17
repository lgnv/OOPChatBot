package First;

import java.util.Scanner;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

public class ConsoleVersion {
	private static Scanner scanner = new Scanner(System.in);
	private static User currentUser = new User();
	
	public static void main(String[] args) {
		var bot = new Bot();
		System.out.println(bot.start());
		currentUser.addListener(bot);
		while (true) {
			if(scanner.hasNextLine()) {			
				var messageFromUser = scanner.nextLine();
				currentUser.sendMessage(messageFromUser);
				for (var messageFromBot : currentUser.getReceivedFromBotMessages()) {
					System.out.println(messageFromBot);
				}
			}
		}
	}
}
