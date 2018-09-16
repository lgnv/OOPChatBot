package First;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

public class TelegramBot extends TelegramLongPollingBot {
	private User currentUser;
	
	public TelegramBot(User currentUser) {
		this.currentUser = currentUser;
	}
	
	@Override
	public String getBotUsername() {
		return "LP-Chat-bot";
	}

	@Override
	public void onUpdateReceived(Update e) {
		Message messageFromUser = e.getMessage();
		String text = messageFromUser.getText();
		currentUser.sendMessage(text);
		for (var textFromBot : currentUser.getReceivedFromBotMessages()) {
			var botMessage = new SendMessage();
			botMessage.setChatId(messageFromUser.getChatId());
			botMessage.setText(textFromBot);
		}
		System.out.println(text);
	}

	@Override
	public String getBotToken() {
		return "687342413:AAHQ_-8rh0ObFMlQSSsl13RQbHvkjNW1ju0";
	}
}