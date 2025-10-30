package lotto.domain;

import java.util.List;
import lotto.Lotto;
import lotto.util.InputParser;

public class WinningNumbers {

    private final Lotto winningLotto;
    private final int bonusNumber;


    public WinningNumbers(List<String> numberStrings, int bonusNumber) {
        List<Integer> numbers = convertToIntegers(numberStrings);
        this.winningLotto = new Lotto(numbers);
        this.bonusNumber = bonusNumber;
        validateBonusNumber(numbers, bonusNumber);
    }

    private List<Integer> convertToIntegers(List<String> numberStrings) {
        return numberStrings.stream()
                .map(InputParser::parseToInteger)
                .toList();
    }


    private void validateBonusNumber(List<Integer> numbers, int bonusNumber) {
        if (numbers.contains(bonusNumber)) {
            throw new IllegalArgumentException("보너스 번호는 당첨 번호와 중복될 수 없습니다.");
        }
    }

    public int getBonusNumber() {
        return bonusNumber;
    }

    public List<Integer> getNumbers() {
        return List.of(); //TDOO
    }

}
