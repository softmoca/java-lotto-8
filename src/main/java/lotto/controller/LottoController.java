package lotto.controller;

import java.util.List;
import lotto.domain.BonusNumber;
import lotto.domain.Lotto;
import lotto.domain.LottoResult;
import lotto.domain.ProfitRate;
import lotto.domain.PurchaseAmount;
import lotto.domain.RandomLottoNumberGenerator;
import lotto.domain.WinningNumbers;
import lotto.domain.WinningStatistics;
import lotto.service.LottoMatcher;
import lotto.service.LottoShop;
import lotto.util.InputParser;
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
        WinningNumbers winningNumbers = inputWinningNumbers();

        checkAndPrintResult(lottos, winningNumbers, purchaseAmount);

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

    private WinningNumbers inputWinningNumbers() {
        Lotto winningLotto = inputWinningNumbersList();
        BonusNumber bonusNumber = inputBonusNumber(winningLotto);
        return new WinningNumbers(winningLotto, bonusNumber);
    }


    private Lotto inputWinningNumbersList() {
        while (true) {
            try {
                List<String> numberStrings = inputView.readWinningNumbers();
                List<Integer> numbers = numberStrings.stream()
                        .map(InputParser::parseToInteger)
                        .toList();

                return new Lotto(numbers);
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e.getMessage());
            }
        }
    }

    private BonusNumber inputBonusNumber(Lotto winningNumbers) {
        while (true) {
            try {
                String input = inputView.readBonusNumber();
                int value = InputParser.parseToInteger(input);
                return BonusNumber.of(value, winningNumbers);

            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e.getMessage());
            }
        }
    }

    private void checkAndPrintResult(List<Lotto> lottos, WinningNumbers winningNumbers,
                                     PurchaseAmount purchaseAmount) {
        WinningStatistics statistics = lottoMatcher.match(lottos, winningNumbers);
        LottoResult result = new LottoResult(statistics, purchaseAmount);

        outputView.printStatisticsHeader();
        outputView.printStatistics(result.getStatistics());

        ProfitRate profitRate = result.calculateProfitRate();
        outputView.printProfitRate(profitRate.getValue());
    }


}