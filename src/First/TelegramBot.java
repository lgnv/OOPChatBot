package First;

import java.util.HashMap;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
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
		var id = messageFromUser.getChatId();
		if (!users.containsKey(id)){
			var user = new User();
			user.addListener(new Bot());
			users.put(id, user);
		}
		var text = messageFromUser.getText();
		users.get(id).sendMessage(text);
		for (var textFromBot : users.get(id).getReceivedFromBotMessages()) {
			var botMessage = new SendMessage();
			if (textFromBot == null) {
				continue;
			}
			botMessage.setChatId(messageFromUser.getChatId());
			botMessage.setText(textFromBot);
			try {
				execute(botMessage);
			} catch (TelegramApiException e1) {
				e1.printStackTrace();
			}
		}
	}

	@Override
	public String getBotToken() {
		return "687342413:AAHQ_-8rh0ObFMlQSSsl13RQbHvkjNW1ju0";
	}
}