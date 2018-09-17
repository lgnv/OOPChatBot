package First;

import java.util.ArrayList;
import java.util.List;

public class User {
	
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
	
	public void sendMessage(String message) {
			receivedFromBotMessages.clear();
			var listenersCount = listeners.size();
			for (var numberOfListener = 0; numberOfListener < listenersCount; numberOfListener++) {
				receivedFromBotMessages.add(listeners.get(numberOfListener).onMessage(message, this));
			}
	}

	public int getListenersCount() {
		return listeners.size();
	}
}
