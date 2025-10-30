package lotto.domain;

import java.util.HashMap;
import java.util.Map;

public class WinningStatistics {

    private final Map<Rank, Integer> statistics;

    public WinningStatistics() {
        this.statistics = new HashMap<>();
        initializeStatistics();
    }

    private void initializeStatistics() {
        statistics.put(Rank.FIRST, 0);
        statistics.put(Rank.SECOND, 0);
        statistics.put(Rank.THIRD, 0);
        statistics.put(Rank.FOURTH, 0);
        statistics.put(Rank.FIFTH, 0);
    }

    public void addResult(Rank rank) {
        if (rank.isWinning()) {
            statistics.put(rank, statistics.get(rank) + 1);
        }
    }

    public int getCountByRank(Rank rank) {
        return statistics.getOrDefault(rank, 0);
    }

}
