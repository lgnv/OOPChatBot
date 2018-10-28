package First.BotLogic;


import First.Features.Menu;
import java.util.Arrays;
import java.util.HashSet;

public class Bot implements MessageListener {
	private static final String help = "Я развлекательный бот. Чтобы получить список игр, в которые я умею играть, напиши \"игра\".\n"
			+ "Для начала игры напиши её название.\n Чтобы получить свежую шутку напиши \"анекдот\"";
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

	public String onMessage(String message, User currentUser) {
		var lowerMessage = message.toLowerCase();
		var correctedMessage = currentUser.getCorrecter().execute(lowerMessage, commands);
		if (currentUser.getIsPlaying() || !menu.commandAvailable(correctedMessage)){
			return null;
		}
		return menu.useCommandFromMenu(correctedMessage, currentUser);
	}
}
