package lotto.controller;

import java.util.List;
import lotto.domain.Lotto;
import lotto.domain.LottoGame;
import lotto.domain.LottoResult;
import lotto.domain.ProfitRate;
import lotto.domain.PurchaseAmount;
import lotto.domain.RandomLottoNumberGenerator;
import lotto.domain.WinningNumbers;
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
        LottoGame game = prepareGame();
        printPurchaseInfo(game);

        LottoResult result = game.play();
        printResult(result);
    }

    private LottoGame prepareGame() {
        PurchaseAmount purchaseAmount = inputHandler.inputPurchaseAmount();
        List<Lotto> lottos = lottoShop.buyLottos(purchaseAmount.getLottoQuantity());
        WinningNumbers winningNumbers = inputHandler.inputWinningNumbers();

        return new LottoGame(purchaseAmount, lottos, winningNumbers);
    }

    private void printPurchaseInfo(LottoGame game) {
        outputView.printPurchaseCount(game.getLottoQuantity());
        outputView.printLottos(game.getPurchasedLottos());
    }

    private void printResult(LottoResult result) {
        outputView.printStatisticsHeader();
        outputView.printStatistics(result.getStatistics());

        ProfitRate profitRate = result.calculateProfitRate();
        outputView.printProfitRate(profitRate.getValue());
    }
}