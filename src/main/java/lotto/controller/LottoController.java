package lotto.controller;

import java.util.List;
import lotto.domain.Lotto;
import lotto.domain.LottoResult;
import lotto.domain.Lottos;
import lotto.domain.ProfitRate;
import lotto.domain.PurchaseAmount;
import lotto.domain.RandomLottoNumberGenerator;
import lotto.domain.WinningNumbers;
import lotto.domain.WinningStatistics;
import lotto.service.LottoShop;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {
    private final InputHandler inputHandler;
    private final OutputView outputView;
    private final LottoShop lottoShop;

    public LottoController() {
        InputView inputView = new InputView();
        this.outputView = new OutputView();
        this.inputHandler = new InputHandler(inputView, outputView);
        this.lottoShop = new LottoShop(new RandomLottoNumberGenerator());
    }

    public void run() {
        PurchaseAmount purchaseAmount = inputHandler.inputPurchaseAmount();
        Lottos lottos = purchaseLottos(purchaseAmount.getLottoQuantity());

        printPurchaseInfo(lottos);

        WinningNumbers winningNumbers = inputHandler.inputWinningNumbers();
        printResult(lottos, winningNumbers, purchaseAmount);
    }

    private Lottos purchaseLottos(int quantity) {
        List<Lotto> lottoList = lottoShop.buyLottos(quantity);
        return new Lottos(lottoList);
    }


    private void printPurchaseInfo(Lottos lottos) {
        outputView.printPurchaseCount(lottos.size());
        outputView.printLottos(lottos.getLottos());
    }

    private void printResult(Lottos lottos, WinningNumbers winningNumbers,
                             PurchaseAmount purchaseAmount) {
        WinningStatistics statistics = lottos.match(winningNumbers);
        LottoResult result = new LottoResult(statistics, purchaseAmount);

        outputView.printStatisticsHeader();
        outputView.printStatistics(result.getStatistics());

        ProfitRate profitRate = result.calculateProfitRate();
        outputView.printProfitRate(profitRate.getValue());
    }
}
