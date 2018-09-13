package First;

public class EntryPoint {
	public static void main(String[] args) {
		User currentUser = new User();
		Bot bot = new Bot();
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