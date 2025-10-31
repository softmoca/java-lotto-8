package lotto.service;

import java.util.List;
import lotto.domain.Lotto;
import lotto.domain.Rank;
import lotto.domain.WinningNumbers;
import lotto.domain.WinningStatistics;

public class LottoMatcher {

    public WinningStatistics match(List<Lotto> lottos, WinningNumbers winningNumbers) {
        WinningStatistics statistics = new WinningStatistics();

        for (Lotto lotto : lottos) {
            Rank rank = winningNumbers.match(lotto);  // 책임을 WinningNumbers에 위임
            statistics.addResult(rank);
        }

        return statistics;
    }
}
