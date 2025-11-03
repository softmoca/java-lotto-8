package lotto.domain.winning;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.List;
import lotto.domain.moeny.ProfitRate;
import lotto.domain.moeny.PurchaseAmount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WinningStatisticsTest {

    @DisplayName("등수 리스트로 통계를 생성한다")
    @Test
    void 등수_리스트로_통계를_생성한다() {
        // given
        List<Rank> ranks = List.of(
                Rank.FIFTH, Rank.FOURTH, Rank.THIRD, Rank.NONE, Rank.NONE
        );

        // when
        WinningStatistics statistics = WinningStatistics.from(ranks);

        // then
        assertAll(
                () -> assertThat(statistics.getCountByRank(Rank.FIFTH)).isEqualTo(1),
                () -> assertThat(statistics.getCountByRank(Rank.FOURTH)).isEqualTo(1),
                () -> assertThat(statistics.getCountByRank(Rank.THIRD)).isEqualTo(1),
                () -> assertThat(statistics.getCountByRank(Rank.NONE)).isEqualTo(0)
        );
    }

    @DisplayName("낙첨은 통계에 포함되지 않는다")
    @Test
    void 낙첨은_통계에_포함되지_않는다() {
        // given
        List<Rank> ranks = List.of(Rank.NONE, Rank.NONE, Rank.NONE);

        // when
        WinningStatistics statistics = WinningStatistics.from(ranks);

        // then
        assertThat(statistics.getCountByRank(Rank.NONE)).isEqualTo(0);
    }

    @DisplayName("총 당첨 금액을 계산한다")
    @Test
    void 총_당첨_금액을_계산한다() {
        // given
        List<Rank> ranks = List.of(Rank.FIFTH, Rank.FOURTH);
        WinningStatistics statistics = WinningStatistics.from(ranks);

        // when
        long totalPrize = statistics.calculateTotalPrize();

        // then
        assertThat(totalPrize).isEqualTo(5_000 + 50_000);
    }

    @DisplayName("수익률을 계산한다")
    @Test
    void 수익률을_계산한다() {
        // given
        List<Rank> ranks = List.of(Rank.FIFTH);  // 5,000
        WinningStatistics statistics = WinningStatistics.from(ranks);
        PurchaseAmount purchaseAmount = PurchaseAmount.from("8000");

        // when
        ProfitRate profitRate = statistics.calculateProfitRate(purchaseAmount);

        // then
        assertThat(profitRate.getValue()).isEqualTo(62.5);  // 5000/8000 * 100
    }
}
