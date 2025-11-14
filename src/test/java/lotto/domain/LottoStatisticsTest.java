package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import org.junit.jupiter.api.Test;

class LottoStatisticsTest {

    @Test
    void 통계를_생성한다() {
        int purchaseAmount = 8000;

        assertThatCode(() -> new LottoStatistics(purchaseAmount))
                .doesNotThrowAnyException();
    }

    @Test
    void 당첨_결과를_추가한다() {
        LottoStatistics statistics = new LottoStatistics(8000);

        statistics.add(Rank.FIFTH);

        assertThat(statistics.getCount(Rank.FIFTH)).isEqualTo(1);
    }

    @Test
    void 여러_당첨_결과를_추가한다() {
        LottoStatistics statistics = new LottoStatistics(8000);

        statistics.add(Rank.FIFTH);
        statistics.add(Rank.FIFTH);
        statistics.add(Rank.NONE);
        statistics.add(Rank.NONE);
        statistics.add(Rank.NONE);
        statistics.add(Rank.NONE);
        statistics.add(Rank.NONE);
        statistics.add(Rank.NONE);

        assertThat(statistics.getCount(Rank.FIFTH)).isEqualTo(2);
        assertThat(statistics.getCount(Rank.NONE)).isEqualTo(6);
    }
}
