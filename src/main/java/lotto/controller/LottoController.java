package lotto.controller;

import java.util.List;
import lotto.domain.Lotto;
import lotto.domain.LottoMachine;
import lotto.domain.LottoNumber;
import lotto.domain.LottoStatistics;
import lotto.domain.Money;
import lotto.domain.WinningLotto;
import lotto.dto.LottoResult;
import lotto.service.LottoResultService;
import lotto.view.InputHandler;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {
    private final InputHandler inputHandler;
    private final OutputView outputView;
    private final LottoMachine lottoMachine;
    private final LottoResultService resultService;

    public LottoController() {
        InputView inputView = new InputView();
        this.outputView = new OutputView();
        this.inputHandler = new InputHandler(inputView, outputView);
        this.lottoMachine = new LottoMachine();
        this.resultService = new LottoResultService();
    }

    public void run() {
        Money purchaseAmount = inputHandler.readPurchaseAmount();
        List<Lotto> lottos = issueLottos(purchaseAmount);
        WinningLotto winningLotto = readWinningLotto();

        LottoStatistics statistics = LottoStatistics.from(lottos, winningLotto);
        LottoResult result = resultService.createResult(statistics, purchaseAmount.getAmount());
        outputView.printResult(result);
    }

    private WinningLotto readWinningLotto() {
        Lotto winningNumbers = inputHandler.readWinningNumbers();
        LottoNumber bonusNumber = inputHandler.readBonusNumber(winningNumbers);
        return WinningLotto.of(winningNumbers, bonusNumber);
    }

    private List<Lotto> issueLottos(Money purchaseAmount) {
        List<Lotto> lottos = lottoMachine.issue(purchaseAmount);
        outputView.printPurchaseCount(lottos.size());
        outputView.printLottos(lottos);
        return lottos;
    }
}
