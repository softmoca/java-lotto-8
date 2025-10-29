package lotto.controller;

import java.util.List;
import lotto.Lotto;
import lotto.domain.RandomLottoNumberGenerator;
import lotto.service.LottoShop;
import lotto.util.InputValidator;
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
        int purchaseAmount = inputPurchaseAmount();
        List<Lotto> lottos = purchaseLottos(purchaseAmount);

    }


    private int inputPurchaseAmount() {
        while (true) {
            try {
                String input = inputView.readPurchaseAmount();
                return InputValidator.validatePurchaseAmount(input);
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e.getMessage());
            }
        }
    }

    private List<Lotto> purchaseLottos(int purchaseAmount) {
        List<Lotto> lottos = lottoShop.buyLottos(purchaseAmount);

        int count = lottos.size();
        outputView.printPurchaseCount(count);
        return lottos;
    }


}