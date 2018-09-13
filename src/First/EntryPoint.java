package First;

public class EntryPoint {
	public static void main(String[] args) {
		var currentUser = new User();
		var bot = new Bot();
		System.out.println(bot.start());
		currentUser.addListener(bot);
		while (true) {
			currentUser.sendMessage();
			for (var message : currentUser.getReceivedFromBotMessages()) {
				System.out.println(message);
			}
		}
	}
}