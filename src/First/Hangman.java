package First;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Hangman implements MessageListener{
	private final String rules = "Правила игры \"Виселица\": ..."; // TODO
	
	private final String beginning = "Я загадал для тебя слово. У тебя будет 6 попыток "
			+ "отгадать его. Да прибудет с тобой эрудиция. Игра началась.";
	
	private String word;
	private final String offerToPlayAgain = "Хочешь сыграть снова? Да\\Нет?";
	private ArrayList<Integer> positionsOfGuessed = new ArrayList<Integer>();
	private ArrayList<Character> usedLetters = new ArrayList<Character>();
	private ArrayList<String> words = new ArrayList<String>();
	private int hp = 6;
	private boolean gameIsOver = false;
	private Bot parent;
	private Random random = new Random();
	
	public Hangman(Bot parent) {
		this.parent = parent;
		words = getWordsFromFile("words.txt");
		word = getRandomWord();
	}
	
	public String start() {
		return rules + '\n' + beginning + '\n' + getGameStatus();
	}
	
	private String acceptTheOption(char letter) {
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
		for (int position = 0; position < word.length(); position++) {
			if (word.charAt(position) == letter) {
				positionsOfGuessed.add(position);
				found = true;
			}
		}
		return found;
	}
	
	private String getStatusWord() {
		var statusWord = "";
		for (var position = 0; position < word.length(); position++) {
			if(positionsOfGuessed.contains(position)) {
				statusWord += word.charAt(position) + " ";
			}
			else {
				statusWord += "_ ";
			}
		}
		return statusWord;
	}
	
	private String getStringUsedLetters() {
		var result = "Использованные буквы: ";
		for(var letter : usedLetters) {
			result += letter + ", ";
		}
		return result;
	}
	
	private String getGameStatus() {
		if (hp == 0) {
			gameIsOver = true;
			return "Увы, ты проиграл. " + offerToPlayAgain;
		}
		else if (positionsOfGuessed.size() == word.length()) {
			gameIsOver = true;
			return "Загаданное слово: " + word + "\n" + "Урааа, ты отгадал слово!!! " + offerToPlayAgain;
		}
		else {
			return "Слово: " + getStatusWord() + "\nОсталось попыток: " + hp + "\n" + getStringUsedLetters();
		}
	}
	
	private String restartGame() {
		positionsOfGuessed.clear();
		usedLetters.clear();
		hp = 6;
		gameIsOver = false;
		word = getRandomWord();
		return beginning + '\n' + getGameStatus();
	}
	
	private String getRandomWord() {
		var index = random.nextInt(words.size());
		return words.get(index);
	}

	@Override
	public String onMessage(String message, User currentUser) {
		var firstSymbol = message.length() > 0 ? message.toLowerCase().charAt(0) : ' ';
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
	
	private ArrayList<String> getWordsFromFile(String filename) {
		var words = new ArrayList<String>();
		try(var br = new BufferedReader(new FileReader(filename))){
			String line;
		    while((line=br.readLine())!=null){
		        words.add(line);
		    }
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return words;
	}
}
