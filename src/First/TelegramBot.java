package First;

import java.util.HashMap;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
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
		replyToUser(userId, currentUser);
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