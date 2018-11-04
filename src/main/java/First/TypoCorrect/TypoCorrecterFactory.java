package First.TypoCorrect;

public class TypoCorrecterFactory {
    public TypoCorrecter create() {
        return new TypoCorrecter(new DamerauLevensteinStrategy());
    }
}
