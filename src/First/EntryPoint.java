package First;

public class EntryPoint {
	public static void main(String[] args) {
		User currentUser = new User();
		Bot bot = new Bot();
		currentUser.addListener(bot);
		while (true) {
			currentUser.sendMessage();
		}
	}
}