package lotto.service;

import java.util.List;
import lotto.Lotto;
import lotto.domain.Rank;
import lotto.domain.WinningNumbers;
import lotto.domain.WinningStatistics;

public class LottoMatcher {

    public WinningStatistics match(List<Lotto> lottos, WinningNumbers winningNumbers) {
        WinningStatistics statistics = new WinningStatistics();

        for (Lotto lotto : lottos) {
            Rank rank = determineRank(lotto, winningNumbers);
            statistics.addResult(rank);
        }

        return statistics;
    }

    private Rank determineRank(Lotto lotto, WinningNumbers winningNumbers) {
        int matchCount = lotto.countMatchingNumbers(winningNumbers.getNumbers());
        boolean hasBonus = lotto.hasNumber(winningNumbers.getBonusNumber());

        return Rank.valueOf(matchCount, hasBonus);
    }
}
