package lotto.service;

import java.util.List;
import lotto.domain.BonusNumber;
import lotto.domain.Lotto;
import lotto.domain.Rank;
import lotto.domain.WinningStatistics;

public class LottoMatcher {

    public WinningStatistics match(List<Lotto> lottos, Lotto winningNumbers, BonusNumber bonusNumber) {
        WinningStatistics statistics = new WinningStatistics();

        for (Lotto lotto : lottos) {
            Rank rank = determineRank(lotto, winningNumbers, bonusNumber);
            statistics.addResult(rank);
        }

        return statistics;
    }

    private Rank determineRank(Lotto purchasedLotto, Lotto winningNumbers, BonusNumber bonusNumber) {
        int matchCount = countMatches(purchasedLotto, winningNumbers);
        boolean hasBonus = purchasedLotto.contains(bonusNumber.getValue());

        return Rank.valueOf(matchCount, hasBonus);
    }

    private int countMatches(Lotto purchasedLotto, Lotto winningNumbers) {
        return (int) purchasedLotto.getNumbers().stream()
                .filter(winningNumbers::contains)
                .count();
    }
}
