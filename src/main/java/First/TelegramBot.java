package First;

import com.vdurmont.emoji.EmojiParser;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import java.util.regex.Pattern;
import java.util.ArrayList;

public class TelegramBot extends TelegramLongPollingBot {
	private UserManager userManager = new UserManager();
	private String smile_cat_emoji = EmojiParser.parseToUnicode(":smile_cat:");
	
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

	private void setButtons(SendMessage sendMessage) {
		var replyKeyboardMarkup = new ReplyKeyboardMarkup();
		sendMessage.setReplyMarkup(replyKeyboardMarkup);
		replyKeyboardMarkup.setSelective(true);
		replyKeyboardMarkup.setResizeKeyboard(true);
		replyKeyboardMarkup.setOneTimeKeyboard(false);
		var keyboard = new ArrayList<KeyboardRow>();
		var keyboardFirstRow = new KeyboardRow();
		keyboardFirstRow.add("кек");
		keyboardFirstRow.add("игры");
		var keyboardSecondRow = new KeyboardRow();
		keyboardSecondRow.add("help");
		keyboard.add(keyboardFirstRow);
		keyboard.add(keyboardSecondRow);
		replyKeyboardMarkup.setKeyboard(keyboard);
	}

	private String fixText(String text) {
		var pattern = Pattern.compile("(&quot;)|(&laquo;)|(&raquo;)");
		var matcher = pattern.matcher(text);
		return matcher.replaceAll("\"");
	}

	private void replyToUser(long userId, User currentUser) {
		for (var textFromBot : currentUser.getReceivedFromBotMessages()) {
			if (textFromBot == null) {
				continue;
			}
			var botMessage = new SendMessage();
			botMessage.setChatId(userId);
			botMessage.setText(fixText(textFromBot));
			setButtons(botMessage);
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
		var message = new SendMessage();
		message.setChatId(userId);
		message.setText(smile_cat_emoji);
		try {
			execute(gif);
			execute(message);
		} catch (TelegramApiException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String getBotToken() {
		return "687342413:AAHQ_-8rh0ObFMlQSSsl13RQbHvkjNW1ju0";
	}
}