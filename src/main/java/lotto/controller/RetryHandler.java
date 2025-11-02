package lotto.controller;

import java.util.function.Supplier;
import lotto.view.OutputView;

public record RetryHandler(OutputView outputView) {

    public <T> T retryUntilValid(Supplier<T> supplier) {
        while (true) {
            try {
                return supplier.get();
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e.getMessage());
            }
        }
    }
}
