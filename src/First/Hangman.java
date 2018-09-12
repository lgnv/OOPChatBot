package First;

import java.util.ArrayList;

public class Hangman implements MessageListener{
	private final String rules = "Правила игры \"Виселица\": ..."; // TODO
	private final String beginning = "Я загадал для тебя слово. У тебя будет 6 попыток "
			+ "отгадать его. Да прибудет с тобой эрудиция. Игра началась.";
	
	private String word = "слово";
	private ArrayList<Integer> positionsOfGuessed = new ArrayList<Integer>();
	private ArrayList<Character> usedLetters = new ArrayList<Character>();
	private int hp = 6;
	private boolean isVictory = false;
	private boolean isLose = false;
	
	public Hangman() {
		System.out.println(rules);
		System.out.println(beginning);
		printGameStatus();
	}
	
	public boolean getIsVictory() {
		return isVictory;
	}
	
	public boolean getIsLose() {
		return isLose;
	}
	
	public void acceptTheOption(char letter) {
		if (usedLetters.contains(letter)) {
			System.out.println("Вы уже вводили эту букву. Попробуйте другую.");
			return;
		}
		usedLetters.add(letter);
		if (checkTheLetter(letter)) {
			System.out.println("Угадал!!!");
		}
		else {
			System.out.println("Увы, ты не угадал");
			hp--;
		}
		printGameStatus();
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
	
	private void printGameStatus() {
		if (hp == 0) {
			System.out.println("Увы, ты проиграл");
			isLose = true;
		}
		else if (positionsOfGuessed.size() == word.length()) {
			System.out.println("Урааа, ты отгадал слово!!!");
			isVictory = true;
		}
		else {
			var statusWord = getStatusWord();
			System.out.println("Слово: " + statusWord + "\nОсталось попыток: " + hp);
		}
	}

	@Override
	public void onMessage(String message, User currentUser) {
		if (message.length() != 1) {
			System.out.println("Упс, нужно ввести всего лишь одну букву!");
		}
		else {
			Character letter = message.toLowerCase().charAt(0);
			acceptTheOption(letter);
		}
	}	
}
