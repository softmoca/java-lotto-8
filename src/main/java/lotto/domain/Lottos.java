package lotto.domain;

import java.util.Collections;
import java.util.List;

public class Lottos {
    private final List<Lotto> lottos;

    public Lottos(List<Lotto> lottos) {
        validateNotEmpty(lottos);
        this.lottos = lottos;
    }

    private void validateNotEmpty(List<Lotto> lottos) {
        if (lottos == null || lottos.isEmpty()) {
            throw new IllegalArgumentException("로또는 최소 1장 이상이어야 합니다.");
        }
    }


    public WinningStatistics match(WinningNumbers winningNumbers) {
        WinningStatistics statistics = new WinningStatistics();

        for (Lotto lotto : lottos) {
            Rank rank = winningNumbers.match(lotto);
            statistics.addResult(rank);
        }

        return statistics;
    }


    public int size() {
        return lottos.size();
    }

    public List<Lotto> getLottos() {
        return Collections.unmodifiableList(lottos);
    }
}
