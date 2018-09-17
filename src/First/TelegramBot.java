package First;

import java.util.HashMap;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendAnimation;
import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class TelegramBot extends TelegramLongPollingBot {
	private HashMap<Long, User> users = new HashMap<Long, User>();
	
	@Override
	public String getBotUsername() {
		return "LP-Chat-bot";
	}

	@Override
	public void onUpdateReceived(Update e) {
		var messageFromUser = e.getMessage();
		var userId = messageFromUser.getChatId();
		var textFromUser = messageFromUser.getText();
		updateUsers(userId);
		var currentUser = users.get(userId);
		currentUser.sendMessage(textFromUser);
		if (textFromUser.equalsIgnoreCase("cat")) {
			sendGif(userId, "https://psv4.userapi.com/c834502/u140417658/"
					+ "docs/d13/cfae75c3f120/stereotipy.gif?extra=Ygx2aCfu_Up"
					+ "AUTB-45umcTNrU_OEicuPTQkUaKuNgJNRS-eFhY2ET4QPoKRMu2gKbP"
					+ "MuHXyhIaWj6k-9VYlFqozTsrEZViO01TJp6CgofXHPD1lC0bFQOZ6uit"
					+ "BgoTNMRhKC4pMOOlV-Yg");
		}
		else {
			replyToUser(userId, currentUser);
		}
	}

	private void replyToUser(Long userId, User currentUser) {
		for (var textFromBot : currentUser.getReceivedFromBotMessages()) {
			if (textFromBot == null) {
				continue;
			}
			var botMessage = new SendMessage();
			botMessage.setChatId(userId);
			botMessage.setText(textFromBot); 
			try {
				execute(botMessage);
			} catch (TelegramApiException e1) {
				e1.printStackTrace();
			}
		}
	}

	private void sendGif(Long userId, String url) {
		var gif = new SendDocument();
		gif.setDocument(url);
		gif.setChatId(userId);
		try {
			execute(gif);
		} catch (TelegramApiException e) {
			e.printStackTrace();
		}
	}

	private void updateUsers(Long userId) {
		if (!users.containsKey(userId)){
			var user = new User();
			user.addListener(new Bot());
			users.put(userId, user);
		}
	}

	@Override
	public String getBotToken() {
		return "687342413:AAHQ_-8rh0ObFMlQSSsl13RQbHvkjNW1ju0";
	}
}