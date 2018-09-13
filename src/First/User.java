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
			var listenersCount = listeners.size();
			for (var numberOfListener = 0; numberOfListener < listenersCount; numberOfListener++) {
				receivedFromBotMessages.add(listeners.get(numberOfListener).onMessage(message, this));
			}
		}
	}
}
