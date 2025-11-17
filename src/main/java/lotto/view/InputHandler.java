package lotto.view;

import java.util.List;
import java.util.function.Supplier;
import lotto.domain.Lotto;
import lotto.domain.LottoNumber;
import lotto.domain.Money;

public class InputHandler {
    private final InputView inputView;
    private final OutputView outputView;

    public InputHandler(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public Money readPurchaseAmount() {
        return retry(() -> {
            int amount = inputView.readPurchaseAmount();
            return Money.from(amount);
        });
    }

    public Lotto readWinningNumbers() {
        return retry(() -> {
            List<Integer> numbers = inputView.readWinningNumbers();
            return new Lotto(numbers);
        });
    }

    public LottoNumber readBonusNumber(Lotto winningNumbers) {
        return retry(() -> {
            int number = inputView.readBonusNumber();
            LottoNumber bonusNumber = new LottoNumber(number);

            if (winningNumbers.containsBonus(bonusNumber.getNumber())) {
                throw new IllegalArgumentException(
                        "[ERROR] 보너스 번호는 당첨 번호와 중복될 수 없습니다."
                );
            }

            return bonusNumber;
        });
    }

    private <T> T retry(Supplier<T> supplier) {
        while (true) {
            try {
                return supplier.get();
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e.getMessage());
            }
        }
    }
}
