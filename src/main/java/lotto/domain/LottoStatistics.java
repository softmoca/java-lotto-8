package lotto.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LottoStatistics {
    private final int purchaseAmount;
    private final Map<Rank, Integer> statistics;

    private LottoStatistics(int purchaseAmount) {
        this.purchaseAmount = purchaseAmount;
        this.statistics = new HashMap<>();
    }

    public static LottoStatistics from(List<Lotto> lottos,
                                       WinningLotto winningLotto,
                                       int purchaseAmount) {
        LottoStatistics statistics = new LottoStatistics(purchaseAmount);

        for (Lotto lotto : lottos) {
            Rank rank = winningLotto.match(lotto);
            statistics.add(rank);
        }

        return statistics;
    }

    private void add(Rank rank) {
        statistics.put(rank, statistics.getOrDefault(rank, 0) + 1);
    }

    public int getCount(Rank rank) {
        return statistics.getOrDefault(rank, 0);
    }

    public List<RankStatistic> getRankStatistics() {
        return Rank.getWinningRanksInDisplayOrder().stream()
                .map(rank -> RankStatistic.of(rank, getCount(rank)))
                .toList();
    }

    public int getTotalPrize() {
        return statistics.entrySet().stream()
                .mapToInt(entry ->
                        entry.getKey().getPrizeMoney() * entry.getValue())
                .sum();
    }

    public double getProfitRate() {
        double rate = (double) getTotalPrize() / purchaseAmount * 100;
        return Math.round(rate * 10) / 10.0;
    }
}
