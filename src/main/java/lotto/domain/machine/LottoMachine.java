package lotto.domain.machine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;
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
        return IntStream.range(0, quantity)
                .mapToObj(i -> new Lotto(numberGenerator.generate()))
                .toList();
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
