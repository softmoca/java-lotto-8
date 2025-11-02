package lotto.controller;

import java.util.List;
import lotto.domain.Lotto;
import lotto.domain.moeny.PurchaseAmount;
import lotto.domain.winning.BonusNumber;
import lotto.domain.winning.WinningNumbers;
import lotto.view.InputView;

public class InputHandler {
    private final InputView inputView;
    private final RetryHandler retryHandler;

    public InputHandler(InputView inputView, RetryHandler retryHandler) {
        this.inputView = inputView;
        this.retryHandler = retryHandler;
    }

    public PurchaseAmount inputPurchaseAmount() {
        return retryHandler.retryUntilValid(() -> {
            String input = inputView.readPurchaseAmount();
            return PurchaseAmount.from(input);
        });
    }

    public WinningNumbers inputWinningNumbers() {
        Lotto winningLotto = inputWinningNumbersList();
        BonusNumber bonusNumber = inputBonusNumber(winningLotto);
        return new WinningNumbers(winningLotto, bonusNumber);
    }

    private Lotto inputWinningNumbersList() {
        return retryHandler.retryUntilValid(() -> {
            List<String> numberStrings = inputView.readWinningNumbers();
            return Lotto.from(numberStrings);
        });
    }

    private BonusNumber inputBonusNumber(Lotto winningNumbers) {
        return retryHandler.retryUntilValid(() -> {
            String input = inputView.readBonusNumber();
            return BonusNumber.of(input, winningNumbers);
        });
    }
}
