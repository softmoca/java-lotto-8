package lotto.controller;

import java.util.List;
import lotto.domain.Lotto;
import lotto.domain.LottoMachine;
import lotto.domain.LottoStatistics;
import lotto.domain.Rank;
import lotto.domain.WinningLotto;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {
    private final InputView inputView;
    private final OutputView outputView;
    private final LottoMachine lottoMachine;

    public LottoController() {
        this.inputView = new InputView();
        this.outputView = new OutputView();
        this.lottoMachine = new LottoMachine();
    }

    public void run() {
        // 1. 로또 구입
        int purchaseAmount = readPurchaseAmountWithRetry();
        List<Lotto> lottos = issueLottos(purchaseAmount);

        // 2. 당첨 번호 입력
        WinningLotto winningLotto = createWinningLottoWithRetry();

        // 3. 당첨 확인 및 출력
        checkAndPrintResult(lottos, winningLotto, purchaseAmount);
    }

    private int readPurchaseAmountWithRetry() {
        while (true) {
            try {
                return inputView.readPurchaseAmount();
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e.getMessage());
            }
        }
    }

    private List<Lotto> issueLottos(int purchaseAmount) {
        try {
            List<Lotto> lottos = lottoMachine.issue(purchaseAmount);
            outputView.printPurchaseCount(lottos.size());
            outputView.printLottos(lottos);
            return lottos;
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e.getMessage());
            int retryAmount = readPurchaseAmountWithRetry();
            return issueLottos(retryAmount);
        }
    }

    private WinningLotto createWinningLottoWithRetry() {
        while (true) {
            try {
                List<Integer> winningNumbers = readWinningNumbersWithRetry();
                int bonusNumber = readBonusNumberWithRetry();
                return new WinningLotto(winningNumbers, bonusNumber);
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e.getMessage());
            }
        }
    }

    private List<Integer> readWinningNumbersWithRetry() {
        while (true) {
            try {
                List<Integer> numbers = inputView.readWinningNumbers();
                new Lotto(numbers);
                return numbers;
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e.getMessage());
            }
        }
    }

    private int readBonusNumberWithRetry() {
        while (true) {
            try {
                return inputView.readBonusNumber();
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e.getMessage());
            }
        }
    }

    private void checkAndPrintResult(List<Lotto> lottos,
                                     WinningLotto winningLotto,
                                     int purchaseAmount) {
        // "통계 생성"
        LottoStatistics statistics = new LottoStatistics(purchaseAmount);

        // "각 로또 당첨 확인"
        for (Lotto lotto : lottos) {
            Rank rank = winningLotto.match(lotto);
            statistics.add(rank);
        }

        printStatistics(statistics);
    }

    private void printStatistics(LottoStatistics statistics) {
        outputView.printStatisticsHeader();

        //  "5등부터 1등까지 출력"
        for (Rank rank : Rank.values()) {
            if (rank.isWinning()) {
                outputView.printStatistics(rank, statistics.getCount(rank));
            }
        }

        outputView.printProfitRate(statistics.getProfitRate());
    }
}
