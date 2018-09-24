package First;

import java.util.HashMap;

public class UserManager {
	private HashMap<Long, User> users = new HashMap<Long, User>();
	private HashMap<String, Game> games = new HashMap<String, Game>() {{ put("Виселица", new Hangman()); }};
	
	private void updateUsers(Long userId) {
		if (!users.containsKey(userId)){
			var user = new User(userId);
			user.addListener(new Bot(games));
			users.put(userId, user);
		}
	}
	
	public User getUser(Long userId) {
		updateUsers(userId);
		return users.get(userId);
	}
}
