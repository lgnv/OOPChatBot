package First;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class TelegramBot extends TelegramLongPollingBot {
	private UserManager userManager = new UserManager();
	
	@Override
	public String getBotUsername() {
		return "LP-Chat-bot";
	}

	@Override
	public void onUpdateReceived(Update e) {
		var messageFromUser = e.getMessage();
		var userId = messageFromUser.getChatId();
		var textFromUser = messageFromUser.getText();
		var currentUser = userManager.getUser(userId);
		currentUser.sendMessage(textFromUser);
		if (textFromUser.equalsIgnoreCase("cat")) {
			var link = new StringBuilder("https://psv4.userapi.com/c834502/u140417658/");
			link.append("docs/d13/cfae75c3f120/stereotipy.gif?extra=Ygx2aCfu_Up");
			link.append("AUTB-45umcTNrU_OEicuPTQkUaKuNgJNRS-eFhY2ET4QPoKRMu2gKbP");
			link.append("MuHXyhIaWj6k-9VYlFqozTsrEZViO01TJp6CgofXHPD1lC0bFQOZ6uit");
			link.append("BgoTNMRhKC4pMOOlV-Yg");
			sendGif(userId, link.toString());
		}
		else if (textFromUser.equalsIgnoreCase("/start")) {
			try {
				execute(new SendMessage(userId, Bot.start()));
			} catch (TelegramApiException e1) {
				e1.printStackTrace();
			}
		}
		else {
			replyToUser(userId, currentUser);
		}
	}

	private void replyToUser(long userId, User currentUser) {
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

	@Override
	public String getBotToken() {
		return "687342413:AAHQ_-8rh0ObFMlQSSsl13RQbHvkjNW1ju0";
	}
}