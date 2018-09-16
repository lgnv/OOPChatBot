package First;

import java.util.Scanner;

public class EntryPoint {
	private static Scanner scanner = new Scanner(System.in);
	
	public static void main(String[] args) {
		var currentUser = new User();
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