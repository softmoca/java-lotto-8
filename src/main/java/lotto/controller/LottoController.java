package lotto.controller;

import java.util.List;
import lotto.Lotto;
import lotto.domain.PurchaseAmount;
import lotto.domain.RandomLottoNumberGenerator;
import lotto.domain.WinningNumbers;
import lotto.domain.WinningStatistics;
import lotto.service.LottoMatcher;
import lotto.service.LottoShop;
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
        List<Lotto> lottos = purchaseLottos(purchaseAmount.getLottoQuantity());
        WinningNumbers winningNumbers = inputWinningNumbersList();

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

    private List<Lotto> purchaseLottos(int quantity) {
        List<Lotto> lottos = lottoShop.buyLottos(quantity);
        outputView.printPurchaseCount(quantity);
        outputView.printLottos(lottos);
        return lottos;
    }


    private WinningNumbers inputWinningNumbersList() {
        while (true) {
            try {
                List<String> temp = inputView.readWinningNumbers();

                return new WinningNumbers(temp);
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e.getMessage());
            }
        }
    }

    private int inputBonusNumber() {
        while (true) {
            try {
                String input = inputView.readBonusNumber();

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