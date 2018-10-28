package First.TypoCorrect;

import java.util.Set;

public class DamerauLevensteinStrategy implements CorrectStrategy {
    private int[] currentRow;
    private int[] previousRow;
    private int[] transpositionRow;

    public DamerauLevensteinStrategy(int maxLength) {
        currentRow = new int[maxLength + 1];
        previousRow = new int[maxLength + 1];
        transpositionRow = new int[maxLength + 1];
    }

    private int getDistance(CharSequence first, CharSequence second, int max) {
        int firstLength = first.length();
        int secondLength = second.length();

        if (firstLength == 0) {
            return secondLength;
        }
        else if (secondLength == 0) {
            return firstLength;
        }

        if (firstLength > secondLength) {
            CharSequence tmp = first;
            first = second;
            second = tmp;
            firstLength = secondLength;
            secondLength = second.length();
        }

        if (max < 0) {
            max = secondLength;
        }
        if (secondLength - firstLength > max) {
            return max + 1;
        }

        if (firstLength > currentRow.length) {
            currentRow = new int[firstLength + 1];
            previousRow = new int[firstLength + 1];
            transpositionRow = new int[firstLength + 1];
        }

        for (int i = 0; i <= firstLength; i++) {
            previousRow[i] = i;
        }

        char lastSecondCh = 0;
        for (int i = 1; i <= secondLength; i++) {
            char secondCh = second.charAt(i - 1);
            currentRow[0] = i;

            // Вычисляем только диагональную полосу шириной 2 * (max + 1)
            int from = Math.max(i - max - 1, 1);
            int to = Math.min(i + max + 1, firstLength);

            char lastFirstCh = 0;
            for (int j = from; j <= to; j++) {
                char firstCh = first.charAt(j - 1);

                // Вычисляем минимальную цену перехода в текущее состояние из предыдущих среди удаления, вставки и
                // замены соответственно.
                int cost = firstCh == secondCh ? 0 : 1;
                int value = Math.min(Math.min(currentRow[j - 1] + 1, previousRow[j] + 1),
                                     previousRow[j - 1] + cost);

                // Если вдруг была транспозиция, надо также учесть и её стоимость.
                if (firstCh == lastSecondCh && secondCh == lastFirstCh) {
                    value = Math.min(value, transpositionRow[j - 2] + cost);
                }

                currentRow[j] = value;
                lastFirstCh = firstCh;
            }
            lastSecondCh = secondCh;

            int tempRow[] = transpositionRow;
            transpositionRow = previousRow;
            previousRow = currentRow;
            currentRow = tempRow;
        }
        return previousRow[firstLength];
    }

    public String correctTypo(String word, Set<String> commands) {
        for (var command : commands) {
            if (getDistance(word, command, -1) <= Math.min(word.length(), 2)) {
                return command;
            }
        }
        return word;
    }
}
