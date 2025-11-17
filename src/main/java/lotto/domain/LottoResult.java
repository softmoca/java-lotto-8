package lotto.domain;

import java.util.List;

public class LottoResult {
    private final List<RankStatistic> rankStatistics;
    private final double profitRate;

    private LottoResult(List<RankStatistic> rankStatistics, double profitRate) {
        this.rankStatistics = rankStatistics;
        this.profitRate = profitRate;
    }

    public static LottoResult of(List<RankStatistic> rankStatistics, double profitRate) {
        return new LottoResult(rankStatistics, profitRate);
    }

    public List<RankStatistic> getRankStatistics() {
        return rankStatistics;
    }

    public double getProfitRate() {
        return profitRate;
    }
}
