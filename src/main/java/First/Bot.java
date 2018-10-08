package First;


public class Bot implements MessageListener {
	private static final String help = "Я развлекательный бот. Чтобы получить список игр, в которые я умею играть, напиши \"игры\".\n"
			+ "Для начала игры напиши её название.\n Чтобы получить свежую шутку напиши \"кек\"";
	private Menu menu;
	
	public Bot(Menu menu) {
		this.menu = menu;
	}
	
	public static String start() {
		return "Привет, пользователь!\n" + help;
	}

	public String onMessage(String message, User currentUser) {
		var lowerMessage = message.toLowerCase();
		if (currentUser.getIsPlaying() || !menu.commandAvailable(lowerMessage)){
			return null;
		}
		return menu.useCommandFromMenu(lowerMessage, currentUser);
	}
}
