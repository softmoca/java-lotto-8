package lotto.domain.machine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import lotto.domain.Lotto;
import lotto.domain.moeny.PurchaseAmount;
import lotto.domain.winning.Rank;
import lotto.domain.winning.WinningNumbers;
import lotto.domain.winning.WinningStatistics;


public class LottoMachine {
    private final LottoNumberGenerator numberGenerator;
    private List<Lotto> purchasedLottos;

    public LottoMachine(LottoNumberGenerator numberGenerator) {
        this.numberGenerator = numberGenerator;
        this.purchasedLottos = new ArrayList<>();
    }

    public void purchase(PurchaseAmount purchaseAmount) {
        int quantity = purchaseAmount.getLottoQuantity();
        this.purchasedLottos = generateLottos(quantity);
    }

    private List<Lotto> generateLottos(int quantity) {
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < quantity; i++) {
            List<Integer> numbers = numberGenerator.generate();
            lottos.add(new Lotto(numbers));
        }
        return lottos;
    }

    public WinningStatistics calculateStatistics(WinningNumbers winningNumbers) {
        List<Rank> ranks = purchasedLottos.stream()
                .map(winningNumbers::match)
                .toList();

        return WinningStatistics.from(ranks);
    }

    public List<Lotto> getPurchasedLottos() {
        return Collections.unmodifiableList(purchasedLottos);
    }

    public int getPurchasedCount() {
        return purchasedLottos.size();
    }
}
