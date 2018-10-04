package First;

import java.util.Scanner;

public class ConsoleVersion {
	private static Scanner scanner = new Scanner(System.in);
	private static User currentUser = new User(0);
	
	public static void main(String[] args) {
		var jokeFilter = new JokeFilter(new JokeFromFile());
		var bot = GeneratorBot.getBot(jokeFilter);
		System.out.println(Bot.start());
		currentUser.addListener(bot);
		processInput();
	}

	private static void processInput() {
		while (true) {
			if(scanner.hasNextLine()) {			
				var messageFromUser = scanner.nextLine();
				currentUser.sendMessage(messageFromUser);
				printBotMessages();
			}
		}
	}

	private static void printBotMessages() {
		for (var messageFromBot : currentUser.getReceivedFromBotMessages()) {
			if (messageFromBot != null) {
				System.out.println(messageFromBot);
			}
		}
	}
}
