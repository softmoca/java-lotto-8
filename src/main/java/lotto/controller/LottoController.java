package lotto.controller;

import java.util.List;
import lotto.Lotto;
import lotto.domain.PurchaseAmount;
import lotto.domain.RandomLottoNumberGenerator;
import lotto.domain.WinningNumbers;
import lotto.domain.WinningStatistics;
import lotto.service.LottoMatcher;
import lotto.service.LottoShop;
import lotto.util.InputValidator;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {
    private final InputView inputView;
    private final OutputView outputView;
    private final LottoShop lottoShop;
    private final LottoMatcher lottoMatcher;

    public LottoController() {
        this.inputView = new InputView();
        this.outputView = new OutputView();
        this.lottoShop = new LottoShop(new RandomLottoNumberGenerator());
        this.lottoMatcher = new LottoMatcher();
    }

    public void run() {
        PurchaseAmount purchaseAmount = inputPurchaseAmount();
        List<Lotto> lottos = purchaseLottos(1000); //TDODO
        WinningNumbers winningNumbers = inputWinningNumbers();
        checkAndPrintResult(lottos, winningNumbers, 1000);// TODO

    }


    private PurchaseAmount inputPurchaseAmount() {
        while (true) {
            try {
                String input = inputView.readPurchaseAmount();
                return PurchaseAmount.from(input);
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

    private void checkAndPrintResult(List<Lotto> lottos, WinningNumbers winningNumbers, int purchaseAmount) {
        WinningStatistics statistics = lottoMatcher.match(lottos, winningNumbers);
        outputView.printStatisticsHeader();
        outputView.printStatistics(statistics);

        double profitRate = statistics.calculateProfitRate(purchaseAmount);
        outputView.printProfitRate(profitRate);

    }


}