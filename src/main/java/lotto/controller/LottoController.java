package lotto.controller;

import java.util.List;
import lotto.Lotto;
import lotto.domain.RandomLottoNumberGenerator;
import lotto.domain.WinningNumbers;
import lotto.service.LottoShop;
import lotto.util.InputValidator;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {
    private final InputView inputView;
    private final OutputView outputView;
    private final LottoShop lottoShop;

    public LottoController() {
        this.inputView = new InputView();
        this.outputView = new OutputView();
        this.lottoShop = new LottoShop(new RandomLottoNumberGenerator());
    }

    public void run() {
        int purchaseAmount = inputPurchaseAmount();
        List<Lotto> lottos = purchaseLottos(purchaseAmount);
        WinningNumbers winningNumbers = inputWinningNumbers();

    }


    private int inputPurchaseAmount() {
        while (true) {
            try {
                String input = inputView.readPurchaseAmount();
                return InputValidator.validatePurchaseAmount(input);
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e.getMessage());
            }
        }
    }

    private List<Lotto> purchaseLottos(int purchaseAmount) {
        List<Lotto> lottos = lottoShop.buyLottos(purchaseAmount);

        int count = lottos.size();
        outputView.printPurchaseCount(count);
        outputView.printLottos(lottos);
        return lottos;
    }

    private WinningNumbers inputWinningNumbers() {
        List<Integer> numbers = inputWinningNumbersList();
        int bonusNumber = inputBonusNumber();
        return new WinningNumbers(numbers, bonusNumber);
    }

    private List<Integer> inputWinningNumbersList() {
        while (true) {
            try {
                String input = inputView.readWinningNumbers();
                return InputValidator.validateWinningNumbers(input);
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e.getMessage());
            }
        }
    }

    private int inputBonusNumber() {
        while (true) {
            try {
                String input = inputView.readBonusNumber();
                return InputValidator.validateBonusNumber(input);
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e.getMessage());
            }
        }


    }

}