package lotto.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LottoStatistics {
    private final Map<Rank, Integer> statistics;

    private LottoStatistics() {
        this.statistics = new HashMap<>();
    }

    public static LottoStatistics from(List<Lotto> lottos, WinningLotto winningLotto) {
        LottoStatistics stats = new LottoStatistics();

        for (Lotto lotto : lottos) {
            Rank rank = winningLotto.match(lotto);
            stats.add(rank);
        }

        return stats;
    }

    private void add(Rank rank) {
        statistics.put(rank, statistics.getOrDefault(rank, 0) + 1);
    }


    public List<RankStatistic> getRankStatistics() {
        return Rank.getWinningRanksInDisplayOrder().stream()
                .map(rank -> RankStatistic.of(rank, getCount(rank)))
                .toList();
    }

    public LottoResult createResult(int purchaseAmount) {
        List<RankStatistic> rankStats = Rank.getWinningRanksInDisplayOrder().stream()
                .map(rank -> RankStatistic.of(rank, getCount(rank)))
                .toList();

        double profitRate = calculateProfitRate(purchaseAmount);

        return LottoResult.of(rankStats, profitRate);
    }

    private int getCount(Rank rank) {
        return statistics.getOrDefault(rank, 0);
    }

    private int getTotalPrize() {
        return statistics.entrySet().stream()
                .mapToInt(entry -> entry.getKey().getPrizeMoney() * entry.getValue())
                .sum();
    }

    private double calculateProfitRate(int purchaseAmount) {
        double rate = (double) getTotalPrize() / purchaseAmount * 100;
        return Math.round(rate * 10) / 10.0;
    }

}
