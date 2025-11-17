package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class ProfitRateTest {

    @Test
    void 수익률을_계산한다() {
        // given
        int totalPrize = 5_000;
        int purchaseAmount = 10_000;

        // when
        ProfitRate profitRate = ProfitRate.calculate(totalPrize, purchaseAmount);

        // then
        assertThat(profitRate.getValue()).isEqualTo(50.0);
    }

    @Test
    void 수익률은_소수점_둘째_자리에서_반올림한다() {
        // given
        int totalPrize = 2_000_000_000;
        int purchaseAmount = 14_000;

        // when
        ProfitRate profitRate = ProfitRate.calculate(totalPrize, purchaseAmount);

        // then
        assertThat(profitRate.getValue()).isEqualTo(14285714.3);
    }


}
