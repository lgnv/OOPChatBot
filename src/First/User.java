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
	
	private List<String> receivedFromBotMessages = new ArrayList<String>();
	
	public List<String> getReceivedFromBotMessages() {
		return receivedFromBotMessages;
	}
	
	public void addListener(MessageListener listenerToAdd) {
		listeners.add(listenerToAdd);
	}
	
	public void removeListener(MessageListener listenerToRemove) {
		listeners.remove(listenerToRemove);
	}
	
	public void sendMessage() {
		if(scanner.hasNextLine()) {
			receivedFromBotMessages.clear();
			String message = scanner.nextLine();
			int listenersCount = listeners.size();
			for (int i = 0; i < listenersCount; i++) {
				receivedFromBotMessages.add(listeners.get(i).onMessage(message, this));
			}
		}
	}
}
