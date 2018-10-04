package First;

import java.util.ArrayList;
import java.util.List;

public class User {
	
	private long id;
	private List<MessageListener> listeners = new ArrayList<>();
	private List<String> receivedFromBotMessages = new ArrayList<>();
	private ArrayList<Integer> hashesReceivedJokes = new ArrayList<>();
	private boolean isPlaying = false;

	public boolean getIsPlaying(){
		return isPlaying;
	}

	public void changeIsPlaying(){
		isPlaying = !isPlaying;
	}
	public User(long id) {
		this.id = id;
	}
	
	public List<String> getReceivedFromBotMessages() {
		return receivedFromBotMessages;
	}
	
	public void addListener(MessageListener listenerToAdd) {
		if (!listeners.contains(listenerToAdd)) {
			listeners.add(listenerToAdd);
		}
	}
	
	public void removeListener(MessageListener listenerToRemove) {
		listeners.remove(listenerToRemove);
	}
	
	public void sendMessage(String message) {
			receivedFromBotMessages.clear();
			var listenersCount = listeners.size();
			for (var numberOfListener = 0; numberOfListener < listenersCount; numberOfListener++) {
				var messageFromBot = listeners.get(numberOfListener).onMessage(message, this);
				if (messageFromBot != null) {
					receivedFromBotMessages.add(messageFromBot);
				}
			}
	}

	public int getListenersCount() {
		return listeners.size();
	}
}
