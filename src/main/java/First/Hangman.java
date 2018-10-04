package First;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Hangman implements MessageListener, Game, Feature {
	private final String rules = "Правила игры \"Виселица\":\n Я загадал для тебя слово. У тебя будет 6 попыток "
			+ "отгадать его. Да прибудет с тобой эрудиция. Игра началась.\n В любой момент ты можешь выйти из игры по команде \"выйти\"";
	private String word;
	private final String offerToPlayAgain = "Хочешь сыграть снова? Да\\Нет?";
	private ArrayList<Integer> positionsOfGuessed = new ArrayList<>();
	private ArrayList<Character> usedLetters = new ArrayList<>();
	private ArrayList<String> words;
	private int hp = 6;
	private Random random = new Random();
	
	public Hangman() {
		words = getWordsFromFile("words.txt");
		word = getRandomWord();
	}
	
	public String getCommand(){
		return "виселица";
	}

	public String getNameFeature(){
		return "Игра 'Виселица'";
	}
	
	public String use(User user) {
		restartGame();
		user.changeIsPlaying();
		user.addListener(this);
		return start();
	}
	
	public int getHP() {
		return hp;
	}
	
	public ArrayList<Integer> getPositionsOfGuessed(){
		return positionsOfGuessed;
	}
	
	public ArrayList<Character> getUsedLetters(){
		return usedLetters;
	}
	
	public String start() {
		return  rules + '\n' + getGameStatus();
	}
	
	public boolean getGameIsOver() {
		return hp == 0 || positionsOfGuessed.size() == word.length();
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
			statusWord += positionsOfGuessed.contains(position)
					? word.charAt(position) + " "
					: "_ ";
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
			return "Увы, ты проиграл. " + offerToPlayAgain;
		}
		else if (positionsOfGuessed.size() == word.length()) {
			var answer = new StringBuilder("Загаданное слово: ");
			answer.append(word);
			answer.append("\n");
			answer.append("Урааа, ты отгадал слово!!! ");
			answer.append(offerToPlayAgain);
			return answer.toString();
		}
		else {
			var answer = new StringBuilder("Слово: ");
			answer.append(getStatusWord());
			answer.append("\nОсталось попыток: ");
			answer.append(hp);
			answer.append("\n");
			answer.append(getStringUsedLetters());
			return answer.toString();
		}
	}
	
	private String restartGame() {
		positionsOfGuessed.clear();
		usedLetters.clear();
		hp = 6;
		word = getRandomWord();
		return rules + '\n' + getGameStatus();
	}

	private String finishGame(User user){
		user.changeIsPlaying();
		user.removeListener(this);
		return "Хорошо. Спасибо за игру!";
	}
	
	private String getRandomWord() {
		var index = random.nextInt(words.size());
		return words.get(index);
	}

	public String onMessage(String message, User currentUser) {
		var firstSymbol = message.length() > 0 ? message.toLowerCase().charAt(0) : ' ';
		if (message.equalsIgnoreCase("выйти")){
			return finishGame(currentUser);
		}
		if (message.equalsIgnoreCase("правила")) {
			return rules;
		}
		if (getGameIsOver()) {
			if (message.equalsIgnoreCase("да")) {
				return restartGame();
			}
			if (message.equalsIgnoreCase("нет")) {
				return finishGame(currentUser);
			}
			return "Извини, не понял тебя. Напиши да\\нет";
		}
		if (message.length() != 1 || !Character.isLetter(firstSymbol)) {
			return "Упс, нужно ввести всего лишь одну букву!";
		}
		return acceptTheOption(firstSymbol);
	}
	
	public ArrayList<String> getWordsFromFile(String filename) {
		var words = new ArrayList<String>();
		try(var br = new BufferedReader(new FileReader(filename))){
			String line;
		    while((line=br.readLine())!=null){
		        words.add(line);
		    }
		} catch (IOException e) {
			return new ArrayList<>();
		}
		return words;
	}
}
