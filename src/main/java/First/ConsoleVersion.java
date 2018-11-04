package First;

import First.BotLogic.Bot;
import First.BotLogic.GeneratorBot;
import First.BotLogic.User;
import First.BotLogic.UserManager;
import First.Jokes.JokeFromFile;
import First.TypoCorrect.SynonymStrategy;
import First.TypoCorrect.TypoCorrecter;
import First.TypoCorrect.YandexSynonymDownloader;

import java.util.Scanner;

public class ConsoleVersion {
	private static Scanner scanner = new Scanner(System.in);
	private static User currentUser = UserManager.getDefaultUser();
	
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
