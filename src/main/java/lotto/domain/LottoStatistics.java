package lotto.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LottoStatistics {
    private final Map<Rank, Integer> statistics;

    private LottoStatistics(Map<Rank, Integer> statistics) {
        this.statistics = statistics;
    }

    public static LottoStatistics from(List<Lotto> lottos, WinningLotto winningLotto) {
        Map<Rank, Integer> stats = new HashMap<>();

        for (Lotto lotto : lottos) {
            Rank rank = winningLotto.match(lotto);
            stats.merge(rank, 1, Integer::sum);
        }

        return new LottoStatistics(stats);
    }

    public List<RankStatistic> getRankStatistics() {
        return Rank.getWinningRanksInDisplayOrder().stream()
                .map(rank -> RankStatistic.of(rank, getCount(rank)))
                .toList();
    }

    public int getTotalPrize() {
        return statistics.entrySet().stream()
                .mapToInt(entry -> entry.getKey().getPrizeMoney() * entry.getValue())
                .sum();
    }

    private int getCount(Rank rank) {
        return statistics.getOrDefault(rank, 0);
    }
}
