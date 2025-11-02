package lotto.domain.winning;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WinningStatistics {

    private final Map<Rank, Integer> rankCounts;

    private WinningStatistics(Map<Rank, Integer> rankCounts) {
        this.rankCounts = rankCounts;
    }

    public static WinningStatistics from(List<Rank> ranks) {
        Map<Rank, Integer> rankCounts = new HashMap<>();

        for (Rank rank : ranks) {
            if (rank.isWinning()) {
                rankCounts.put(rank, rankCounts.getOrDefault(rank, 0) + 1);
            }
        }

        return new WinningStatistics(rankCounts);
    }


    public int getCountByRank(Rank rank) {
        return rankCounts.getOrDefault(rank, 0);
    }

    public long calculateTotalPrize() {
        long totalPrize = 0;

        for (Rank rank : rankCounts.keySet()) {
            int count = rankCounts.get(rank);
            totalPrize += (long) rank.getPrizeAmount() * count;
        }

        return totalPrize;
    }

}
