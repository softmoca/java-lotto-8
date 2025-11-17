package lotto.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LottoStatistics {

    public static LottoResult calculate(List<Lotto> lottos,
                                        WinningLotto winningLotto,
                                        int purchaseAmount) {
        // 1. 통계 수집
        Map<Rank, Integer> statistics = collectStatistics(lottos, winningLotto);

        // 2. DTO 생성
        List<RankStatistic> rankStats = createRankStatistics(statistics);
        double profitRate = calculateProfitRate(statistics, purchaseAmount);

        return LottoResult.of(rankStats, profitRate);
    }

    private static Map<Rank, Integer> collectStatistics(List<Lotto> lottos,
                                                        WinningLotto winningLotto) {
        Map<Rank, Integer> statistics = new HashMap<>();

        for (Lotto lotto : lottos) {
            Rank rank = winningLotto.match(lotto);
            statistics.put(rank, statistics.getOrDefault(rank, 0) + 1);
        }

        return statistics;
    }

    private static List<RankStatistic> createRankStatistics(Map<Rank, Integer> statistics) {
        return Rank.getWinningRanksInDisplayOrder().stream()
                .map(rank -> RankStatistic.of(rank, statistics.getOrDefault(rank, 0)))
                .toList();
    }

    private static double calculateProfitRate(Map<Rank, Integer> statistics,
                                              int purchaseAmount) {
        int totalPrize = statistics.entrySet().stream()
                .mapToInt(entry -> entry.getKey().getPrizeMoney() * entry.getValue())
                .sum();

        double rate = (double) totalPrize / purchaseAmount * 100;
        return Math.round(rate * 10) / 10.0;
    }
}
