package First;

import First.BotLogic.*;
import First.Jokes.JokeFromFile;

import java.util.Scanner;

public class ConsoleVersion {
	private static Scanner scanner = new Scanner(System.in);
	private static User currentUser = UserFactory.getDefaultUser();
	
	public static void main(String[] args) {
		var bot = GeneratorBot.getBot(new JokeFromFile("top100.txt"));
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
