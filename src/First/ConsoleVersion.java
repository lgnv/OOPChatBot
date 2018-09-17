package First;

import java.util.Scanner;

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
					if (messageFromBot != null) {
						System.out.println(messageFromBot);
					}
				}
			}
		}
	}
}
