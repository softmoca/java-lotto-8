package lotto.domain.moeny;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ProfitRateTest {

    @DisplayName("수익률을 계산한다")
    @Test
    void 수익률을_계산한다() {
        // given
        long totalPrize = 5_000L;
        PurchaseAmount purchaseAmount = PurchaseAmount.from("8000");

        // when
        ProfitRate profitRate = ProfitRate.of(totalPrize, purchaseAmount);

        // then
        assertThat(profitRate.getValue()).isEqualTo(62.5);
    }

    @DisplayName("수익률은 소수점 첫째 자리에서 반올림한다")
    @Test
    void 수익률은_소수점_첫째_자리에서_반올림한다() {
        // given
        long totalPrize = 1_000L;
        PurchaseAmount purchaseAmount = PurchaseAmount.from("3000");

        // when
        ProfitRate profitRate = ProfitRate.of(totalPrize, purchaseAmount);

        // then
        assertThat(profitRate.getValue()).isEqualTo(33.3);
    }

}
