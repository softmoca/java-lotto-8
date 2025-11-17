package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;

class LottoStatisticsTest {

    @Test
    void 등수별_개수를_정확히_집계한다() {
        // given
        List<Lotto> lottos = List.of(
                new Lotto(List.of(1, 2, 3, 4, 5, 6)),   // 1등
                new Lotto(List.of(1, 2, 3, 4, 5, 7)),   // 2등
                new Lotto(List.of(1, 2, 3, 4, 5, 8)),   // 3등
                new Lotto(List.of(1, 2, 3, 4, 10, 11)), // 4등
                new Lotto(List.of(1, 2, 3, 10, 11, 12)) // 5등
        );
        Lotto winningNumbers = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        WinningLotto winningLotto = WinningLotto.of(winningNumbers, new LottoNumber(7));
        LottoStatistics statistics = LottoStatistics.from(lottos, winningLotto);

        // when
        int firstCount = statistics.getCount(Rank.FIRST);
        int secondCount = statistics.getCount(Rank.SECOND);
        int thirdCount = statistics.getCount(Rank.THIRD);
        int fourthCount = statistics.getCount(Rank.FOURTH);
        int fifthCount = statistics.getCount(Rank.FIFTH);

        // then
        assertThat(firstCount).isEqualTo(1);
        assertThat(secondCount).isEqualTo(1);
        assertThat(thirdCount).isEqualTo(1);
        assertThat(fourthCount).isEqualTo(1);
        assertThat(fifthCount).isEqualTo(1);
    }

    @Test
    void 총_상금을_계산한다() {
        // given
        List<Lotto> lottos = List.of(
                new Lotto(List.of(1, 2, 3, 10, 11, 12)),
                new Lotto(List.of(1, 2, 3, 4, 10, 11))
        );
        Lotto winningNumbers = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        WinningLotto winningLotto = WinningLotto.of(winningNumbers, new LottoNumber(7));
        LottoStatistics statistics = LottoStatistics.from(lottos, winningLotto);

        // when
        int totalPrize = statistics.getTotalPrize();

        // then
        assertThat(totalPrize).isEqualTo(55_000);
    }

}
