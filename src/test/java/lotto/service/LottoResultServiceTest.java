package lotto.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import lotto.domain.Lotto;
import lotto.domain.LottoNumber;
import lotto.domain.LottoResult;
import lotto.domain.LottoStatistics;
import lotto.domain.Rank;
import lotto.domain.RankStatistic;
import lotto.domain.WinningLotto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LottoResultServiceTest {

    private LottoResultService service;

    @BeforeEach
    void setUp() {
        service = new LottoResultService();
    }

    @Test
    void 통계로부터_결과를_생성한다() {
        // given
        List<Lotto> lottos = List.of(
                new Lotto(List.of(1, 2, 3, 4, 5, 6))
        );
        Lotto winningNumbers = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        WinningLotto winningLotto = WinningLotto.of(winningNumbers, new LottoNumber(7));
        LottoStatistics statistics = LottoStatistics.from(lottos, winningLotto);

        // when
        LottoResult result = service.createResult(statistics, 1000);

        // then
        assertThat(result).isNotNull();
        assertThat(result.getRankStatistics()).hasSize(5);
        assertThat(result.getProfitRate()).isPositive();
    }

    @Test
    void 등수별_통계를_DTO로_변환한다() {
        // given: 5등 1개
        List<Lotto> lottos = List.of(
                new Lotto(List.of(1, 2, 3, 10, 11, 12))
        );
        Lotto winningNumbers = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        WinningLotto winningLotto = WinningLotto.of(winningNumbers, new LottoNumber(7));
        LottoStatistics statistics = LottoStatistics.from(lottos, winningLotto);

        // when
        LottoResult result = service.createResult(statistics, 1000);

        // then
        List<RankStatistic> rankStats = result.getRankStatistics();
        RankStatistic fifthStat = findStat(rankStats, Rank.FIFTH);
        assertThat(fifthStat.getCount()).isEqualTo(1);
        assertThat(fifthStat.getDescription()).isEqualTo("3개 일치");
        assertThat(fifthStat.getPrizeMoney()).isEqualTo(5_000);
    }


    private RankStatistic findStat(List<RankStatistic> rankStats, Rank rank) {
        return rankStats.stream()
                .filter(stat -> stat.getRank() == rank)
                .findFirst()
                .orElseThrow();
    }


}
