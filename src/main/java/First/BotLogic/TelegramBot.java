package First.BotLogic;

import First.utility.ConfigManager;
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
	private UserManager userManager;

	public TelegramBot(UserManager userManager) {
		this.userManager = userManager;
	}
	
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
			var link = ConfigManager.getProperty("CAT_GIF_URL");
			sendGif(userId, link);
		}
		else if (textFromUser.equalsIgnoreCase("кусь")) {
			var link = ConfigManager.getProperty("UCUS_GIF_URL");
			sendGif(userId, link);
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
		keyboard.add(getKeyboardRow(Arrays.asList("анекдот", "игра")));
		keyboard.add(getKeyboardRow(Arrays.asList("помощь")));
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
		keyboard.add(getKeyboardRow(Arrays.asList("правила", "выйти")));
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
		try {
			execute(gif);
			execute(message);
		} catch (TelegramApiException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String getBotToken() {
		return ConfigManager.getProperty("BOT_TOKEN");
	}
}