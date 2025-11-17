package lotto.util;

import java.util.List;
import lotto.domain.LottoStatistics;
import lotto.domain.ProfitRate;
import lotto.dto.LottoResult;
import lotto.dto.RankStatistic;

public class LottoResultMapper {

    public static LottoResult createResult(LottoStatistics statistics, int purchaseAmount) {
        List<RankStatistic> rankStats = statistics.getRankStatistics();
        ProfitRate profitRate = ProfitRate.calculate(
                statistics.getTotalPrize(),
                purchaseAmount
        );

        return LottoResult.of(rankStats, profitRate.getValue());
    }
}
