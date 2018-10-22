package First.BotLogic;


import First.Features.Menu;
import First.TypoCorrect.TypoCorrecter;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Bot implements MessageListener {
	private static final String help = "Я развлекательный бот. Чтобы получить список игр, в которые я умею играть, напиши \"игры\".\n"
			+ "Для начала игры напиши её название.\n Чтобы получить свежую шутку напиши \"кек\"";
	private Menu menu;
	private HashSet<String> commands;
	
	public Bot(Menu menu) {
		this.menu = menu;
		commands = menu.getCommands();
		commands.addAll(Arrays.asList("выйти", "правила", "да", "нет", "помощь"));
	}
	
	public static String start() {
		return "Привет, пользователь!\n" + help;
	}

	public String onMessage(String message, User currentUser, TypoCorrecter correcter) {
		var lowerMessage = message.toLowerCase();
		var correctedMessage = correcter.CorrectTypo(lowerMessage, commands);
		if (currentUser.getIsPlaying() || !menu.commandAvailable(correctedMessage)){
			return null;
		}
		return menu.useCommandFromMenu(correctedMessage, currentUser);
	}
}
