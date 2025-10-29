package lotto.controller;

import lotto.util.InputValidator;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {
    private final InputView inputView;
    private final OutputView outputView;

    public LottoController() {
        this.inputView = new InputView();
        this.outputView = new OutputView();
    }

    public void run() {
        int purchaseAmount = inputPurchaseAmount();

    }


    private int inputPurchaseAmount() {
        String input = inputView.readPurchaseAmount();
        return InputValidator.validatePurchaseAmount(input);
    }


}