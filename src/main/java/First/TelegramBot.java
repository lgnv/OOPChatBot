package First;

import com.vdurmont.emoji.EmojiParser;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.Arrays;
import java.util.List;
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
		else if (textFromUser.equalsIgnoreCase("кусь")) {
			var link = new StringBuilder("https://psv4.userapi.com/c848024/u5057566/");
			link.append("docs/d13/214f83b16c21/catism.gif?extra=JvM8DUxE1LtdaSP7hMcnE");
			link.append("CEr90TKqb1BSB2yKmaHgXi3Ir365CV02PSeK4C1Nhcnb5U6OsNd5XS9gOnwaS");
			link.append("PubVwYUuuMSqkBcTjJDluekqxS5oLnNRvneU2zPbOVN9GaxJKU11A8CT6oWXNbnwBSMVMS");
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

	private void setStandardReplyKeyboard(SendMessage sendMessage) {
		ReplyKeyboardMarkup replyKeyboardMarkup = getReplyKeyboardMarkup(sendMessage);
		var keyboard = new ArrayList<KeyboardRow>();
		var keyboardFirstRow = getKeyboardRow(Arrays.asList("кек", "игры"));
		var keyboardSecondRow = getKeyboardRow(Arrays.asList("помощь"));
		keyboard.add(keyboardFirstRow);
		keyboard.add(keyboardSecondRow);
		replyKeyboardMarkup.setKeyboard(keyboard);
	}

	private KeyboardRow getKeyboardRow(List<String> buttonTexts) {
		var keyboardRow = new KeyboardRow();
		for (var text : buttonTexts) {
			keyboardRow.add(text);
		}
		return keyboardRow;
	}

	private ReplyKeyboardMarkup getReplyKeyboardMarkup(SendMessage sendMessage) {
		var replyKeyboardMarkup = new ReplyKeyboardMarkup();
		sendMessage.setReplyMarkup(replyKeyboardMarkup);
		replyKeyboardMarkup.setSelective(true);
		replyKeyboardMarkup.setResizeKeyboard(true);
		replyKeyboardMarkup.setOneTimeKeyboard(false);
		return replyKeyboardMarkup;
	}

	private void setGameReplyKeyboard(SendMessage sendMessage) {
		var replyKeyboardMarkup = getReplyKeyboardMarkup(sendMessage);
		var keyboard = new ArrayList<KeyboardRow>();
		var keyboardFirstRow = getKeyboardRow(Arrays.asList("правила", "выйти"));
		keyboard.add(keyboardFirstRow);
		replyKeyboardMarkup.setKeyboard(keyboard);
	}

	private String fixText(String text) {
		var pattern = Pattern.compile("(&quot;)|(&laquo;)|(&raquo;)");
		var matcher = pattern.matcher(text);
		return matcher.replaceAll("\"");
	}

	private void replyToUser(long userId, User currentUser) {
		for (var textFromBot : currentUser.getReceivedFromBotMessages()) {
			var botMessage = new SendMessage();
			botMessage.setChatId(userId);
			botMessage.setText(fixText(textFromBot));
			if (currentUser.getIsPlaying()) {
				setGameReplyKeyboard(botMessage);
			}
			else {
				setStandardReplyKeyboard(botMessage);
			}
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