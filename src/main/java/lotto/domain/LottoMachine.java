package lotto.domain;

import java.util.List;

public class LottoMachine {
    private final PurchaseAmount purchaseAmount;
    private final List<Lotto> purchasedLottos;
    private final WinningNumbers winningNumbers;

    public LottoMachine(PurchaseAmount purchaseAmount, List<Lotto> purchasedLottos,
                        WinningNumbers winningNumbers) {
        this.purchaseAmount = purchaseAmount;
        this.purchasedLottos = purchasedLottos;
        this.winningNumbers = winningNumbers;
    }

    public LottoResult check() {
        WinningStatistics statistics = checkWinning();
        return new LottoResult(statistics, purchaseAmount);
    }

    private WinningStatistics checkWinning() {
        WinningStatistics statistics = new WinningStatistics();

        for (Lotto lotto : purchasedLottos) {
            Rank rank = winningNumbers.match(lotto);
            statistics.addResult(rank);
        }

        return statistics;
    }

    public List<Lotto> getPurchasedLottos() {
        return purchasedLottos;
    }

    public int getLottoQuantity() {
        return purchasedLottos.size();
    }
}
