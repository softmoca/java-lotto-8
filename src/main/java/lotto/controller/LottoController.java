package lotto.controller;

import java.util.List;
import lotto.domain.Lotto;
import lotto.domain.LottoMachine;
import lotto.domain.LottoNumber;
import lotto.domain.LottoStatistics;
import lotto.domain.Money;
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
        Money purchaseAmount = readPurchaseAmountWithRetry();
        List<Lotto> lottos = issueLottos(purchaseAmount);

        // 2. 당첨 번호 입력
        WinningLotto winningLotto = createWinningLottoWithRetry();

        // 3. 당첨 확인 및 출력
        checkAndPrintResult(lottos, winningLotto, 1000);
    }

    private Money readPurchaseAmountWithRetry() {
        while (true) {
            try {
                int amount = inputView.readPurchaseAmount();
                return Money.from(amount);
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e.getMessage());
            }
        }
    }

    private List<Lotto> issueLottos(Money purchaseAmount) {

        List<Lotto> lottos = lottoMachine.issue(purchaseAmount);
        outputView.printPurchaseCount(lottos.size());
        outputView.printLottos(lottos);
        return lottos;

    }


    private WinningLotto createWinningLottoWithRetry() {
        Lotto winningNumbers = readWinningNumbersWithRetry();
        LottoNumber bonusNumber = readBonusNumberWithRetry(winningNumbers);
        return WinningLotto.of(winningNumbers, bonusNumber);
    }

    private Lotto readWinningNumbersWithRetry() {
        while (true) {
            try {
                List<Integer> numbers = inputView.readWinningNumbers();
                return new Lotto(numbers);
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e.getMessage());
            }
        }
    }

    private LottoNumber readBonusNumberWithRetry(Lotto winningNumbers) {
        while (true) {
            try {
                int number = inputView.readBonusNumber();
                LottoNumber bonusNumber = new LottoNumber(number);

                if (winningNumbers.containsBonus(bonusNumber.getNumber())) {
                    throw new IllegalArgumentException(
                            "[ERROR] 보너스 번호는 당첨 번호와 중복될 수 없습니다."
                    );
                }

                return bonusNumber;
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
