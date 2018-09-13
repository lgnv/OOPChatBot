package First;

import java.util.ArrayList;

public class Hangman implements MessageListener{
	private final String rules = "Правила игры \"Виселица\": ..."; // TODO
	private final String beginning = "Я загадал для тебя слово. У тебя будет 6 попыток "
			+ "отгадать его. Да прибудет с тобой эрудиция. Игра началась.";
	
	private String word = "слово";
	private final String offerToPlayAgain = "Хочешь сыграть снова? Да\\нет?";
	private ArrayList<Integer> positionsOfGuessed = new ArrayList<Integer>();
	private ArrayList<Character> usedLetters = new ArrayList<Character>();
	private int hp = 6;
	private boolean gameIsOver = false;
	private Bot parent;
	
	public Hangman(Bot parent) {
		this.parent = parent;
	}
	
	public String start() {
		return rules + '\n' + beginning + '\n' + getGameStatus();
	}
	
	public String acceptTheOption(char letter) {
		if (usedLetters.contains(letter)) {
			return "Вы уже вводили эту букву. Попробуйте другую.";
		}
		usedLetters.add(letter);
		var answer = "";
		if (checkTheLetter(letter)) {
			answer += "Угадал!!!\n";
		}
		else {
			answer += "Увы, ты не угадал\n";
			hp--;
		}
		return answer + getGameStatus();
	}
	
	
	private boolean checkTheLetter(char letter) {
		var found = false;
		for (int i = 0; i < word.length(); i++) {
			if (word.charAt(i) == letter) {
				positionsOfGuessed.add(i);
				found = true;
			}
		}
		return found;
	}
	
	private String getStatusWord() {
		String statusWord = "";
		for (int i = 0; i < word.length(); i++) {
			if(positionsOfGuessed.contains(i)) {
				statusWord += word.charAt(i) + " ";
			}
			else {
				statusWord += "_ ";
			}
		}
		return statusWord;
	}
	
	private String getGameStatus() {
		if (hp == 0) {
			gameIsOver = true;
			return "Увы, ты проиграл. " + offerToPlayAgain;
		}
		else if (positionsOfGuessed.size() == word.length()) {
			gameIsOver = true;
			return "Урааа, ты отгадал слово!!! " + offerToPlayAgain;
		}
		else {
			var statusWord = getStatusWord();
			return "Слово: " + statusWord + "\nОсталось попыток: " + hp;
		}
	}
	
	private String restartGame() {
		positionsOfGuessed.clear();
		usedLetters.clear();
		hp = 6;
		gameIsOver = false;
		return beginning + '\n' + getGameStatus();
	}

	@Override
	public String onMessage(String message, User currentUser) {
		Character firstSymbol = message.length() > 0 ? message.toLowerCase().charAt(0) : ' ';
		if (gameIsOver) {
			if (message.equalsIgnoreCase("да")) {
				return restartGame();
			}
			else if (message.equalsIgnoreCase("нет")) {
				currentUser.removeListener(this);
				parent.endOfGame();
				return "Хорошо. Спасибо за игру!";
			}
			else {
				return "Извини, не понял тебя. Напиши да\\нет";
			}
		}
		else if (message.length() != 1 || !Character.isLetter(firstSymbol)) {
			return "Упс, нужно ввести всего лишь одну букву!";
		}
		else {			
			return acceptTheOption(firstSymbol);
		}
	}	
}
