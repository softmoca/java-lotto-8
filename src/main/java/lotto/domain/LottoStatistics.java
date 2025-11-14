package lotto.domain;


import java.util.HashMap;
import java.util.Map;

public class LottoStatistics {
    private final int purchaseAmount;
    private final Map<Rank, Integer> statistics;

    public LottoStatistics(int purchaseAmount) {
        this.purchaseAmount = purchaseAmount;
        this.statistics = new HashMap<>();
    }

    public void add(Rank rank) {
        statistics.put(rank, statistics.getOrDefault(rank, 0) + 1);
    }

    public int getCount(Rank rank) {
        return statistics.getOrDefault(rank, 0);
    }
}
