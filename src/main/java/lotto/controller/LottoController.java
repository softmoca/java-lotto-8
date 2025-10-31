package lotto.controller;

import java.util.List;
import lotto.domain.BonusNumber;
import lotto.domain.Lotto;
import lotto.domain.LottoGame;
import lotto.domain.LottoResult;
import lotto.domain.ProfitRate;
import lotto.domain.PurchaseAmount;
import lotto.domain.RandomLottoNumberGenerator;
import lotto.domain.WinningNumbers;
import lotto.service.LottoShop;
import lotto.util.InputParser;
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
        LottoGame game = prepareGame();
        printPurchaseInfo(game);

        LottoResult result = game.play();
        printResult(result);
    }

    private LottoGame prepareGame() {
        PurchaseAmount purchaseAmount = inputPurchaseAmount();
        List<Lotto> lottos = purchaseLottos(purchaseAmount.getLottoQuantity());
        WinningNumbers winningNumbers = inputWinningNumbers();

        return new LottoGame(purchaseAmount, lottos, winningNumbers);
    }

    private void printResult(LottoResult result) {
        outputView.printStatisticsHeader();
        outputView.printStatistics(result.getStatistics());

        ProfitRate profitRate = result.calculateProfitRate();
        outputView.printProfitRate(profitRate.getValue());
    }

    private void printPurchaseInfo(LottoGame game) {
        outputView.printPurchaseCount(game.getLottoQuantity());
        outputView.printLottos(game.getPurchasedLottos());
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

}