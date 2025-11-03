package lotto.controller;

import lotto.domain.machine.LottoMachine;
import lotto.domain.machine.RandomLottoNumberGenerator;
import lotto.domain.moeny.ProfitRate;
import lotto.domain.moeny.PurchaseAmount;
import lotto.domain.winning.WinningNumbers;
import lotto.domain.winning.WinningStatistics;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {
    private final InputHandler inputHandler;
    private final OutputView outputView;
    private final LottoMachine machine;

    public LottoController() {
        InputView inputView = new InputView();
        this.outputView = new OutputView();
        RetryHandler retryHandler = new RetryHandler(outputView);
        this.inputHandler = new InputHandler(inputView, retryHandler);
        this.machine = new LottoMachine(new RandomLottoNumberGenerator());
    }

    public void run() {
        PurchaseAmount purchaseAmount = inputHandler.inputPurchaseAmount();

        machine.purchase(purchaseAmount);
        printPurchaseInfo();

        WinningNumbers winningNumbers = inputHandler.inputWinningNumbers();
        printResult(winningNumbers, purchaseAmount);
    }

    private void printPurchaseInfo() {
        outputView.printPurchaseCount(machine.getPurchasedCount());
        outputView.printLottos(machine.getPurchasedLottos());
    }


    private void printResult(WinningNumbers winningNumbers, PurchaseAmount purchaseAmount) {
        WinningStatistics statistics = machine.calculateStatistics(winningNumbers);

        outputView.printStatisticsHeader();
        outputView.printStatistics(statistics);

        ProfitRate profitRate = statistics.calculateProfitRate(purchaseAmount);
        outputView.printProfitRate(profitRate.getValue());
    }
}
