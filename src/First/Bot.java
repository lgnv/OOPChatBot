package First;

public class Bot implements MessageListener {
	private final String help = "Я игровой бот. Чтобы получить список игр, в которые я умею играть, напиши \"игры\".\n"
			+ "Для начала игры напиши её название.";
	
	public Bot() {
		start();
	}
	
	public void start() {
		System.out.println("Привет, пользователь!");
		System.out.println(help);
	}
	
	public void getGames() {
		
	}

	@Override
	public void onMessage(String message) {
		System.out.println("Сообщение получил! " + message);
	}
}
