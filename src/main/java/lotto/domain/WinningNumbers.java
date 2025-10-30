package lotto.domain;

import java.util.List;
import lotto.Lotto;

public class WinningNumbers {

    private final Lotto winningLotto;


    public WinningNumbers(List<Integer> numbers) {
        this.winningLotto = new Lotto(numbers);
    }

    public boolean hasNumber(int number) {
        return winningLotto.hasNumber(number);
    }

    public List<Integer> getNumbers() {
        return List.of(); //TDOO
    }

}
