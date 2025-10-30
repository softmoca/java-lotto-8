package lotto.domain;

import java.util.List;
import lotto.Lotto;
import lotto.util.InputParser;

public class WinningNumbers {

    private final Lotto winningLotto;


    public WinningNumbers(List<String> numberStrings) {
        List<Integer> numbers = convertToIntegers(numberStrings);
        this.winningLotto = new Lotto(numbers);
    }

    private List<Integer> convertToIntegers(List<String> numberStrings) {
        return numberStrings.stream()
                .map(InputParser::parseToInteger)
                .toList();
    }


    public boolean hasNumber(int number) {
        return winningLotto.hasNumber(number);
    }

    public List<Integer> getNumbers() {
        return List.of(); //TDOO
    }

}
