package First;

public interface Feature {
    String getCommand();
    String getDescription();
    String use(User user, String command);
}
