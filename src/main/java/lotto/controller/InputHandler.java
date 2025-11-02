package lotto.controller;

import java.util.List;
import java.util.function.Supplier;
import lotto.domain.Lotto;
import lotto.domain.moeny.PurchaseAmount;
import lotto.domain.winning.BonusNumber;
import lotto.domain.winning.WinningNumbers;
import lotto.view.InputView;
import lotto.view.OutputView;

public class InputHandler {
    private final InputView inputView;
    private final OutputView outputView;

    public InputHandler(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public PurchaseAmount inputPurchaseAmount() {
        return retryOnException(() -> {
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
        return retryOnException(() -> {
            List<String> numberStrings = inputView.readWinningNumbers();
            return Lotto.from(numberStrings);
        });
    }

    private BonusNumber inputBonusNumber(Lotto winningNumbers) {
        return retryOnException(() -> {
            String input = inputView.readBonusNumber();
            return BonusNumber.of(input, winningNumbers);
        });
    }

    private <T> T retryOnException(Supplier<T> inputSupplier) {
        while (true) {
            try {
                return inputSupplier.get();
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e.getMessage());
            }
        }
    }
}
