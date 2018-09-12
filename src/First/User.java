package First;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class User {
	private Scanner scanner;
	public User() {
		scanner = new Scanner(System.in);
	}
	
	private List<MessageListener> listeners = new ArrayList<MessageListener>();
	
	public void addListener(MessageListener listenerToAdd) {
		listeners.add(listenerToAdd);
	}
	
	public void removeListener(MessageListener listenerToRemove) {
		listeners.remove(listenerToRemove);
	}
	
	public void sendMessage() {
		if(scanner.hasNext()) {
			String message = scanner.next();
			int listenersCount = listeners.size();
			for (int i = 0; i < listenersCount; i++) {
				listeners.get(i).onMessage(message, this);
			}
		}
	}
}
