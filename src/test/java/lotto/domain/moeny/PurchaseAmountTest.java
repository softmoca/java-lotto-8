package lotto.domain.moeny;

import static lotto.exception.ErrorMessage.INVALID_PURCHASE_AMOUNT_FORMAT;
import static lotto.exception.ErrorMessage.INVALID_PURCHASE_AMOUNT_INSUFFICIENT;
import static lotto.exception.ErrorMessage.INVALID_PURCHASE_AMOUNT_UNIT;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class PurchaseAmountTest {

    @DisplayName("유효한 구입 금액으로 객체가 정상 생성된다")
    @Test
    void 유효한_구입_금액으로_객체가_정상_생성된다() {
        // given
        String input = "5000";

        // when & then
        assertThatCode(() -> PurchaseAmount.from(input))
                .doesNotThrowAnyException();
    }

    @DisplayName("구입 금액이 1000원 미만이면 예외가 발생한다")
    @ParameterizedTest
    @ValueSource(strings = {"-100", "0", "999"})
    void 구입_금액이_1000원_미만이면_예외가_발생한다(String input) {
        // when & then
        assertThatThrownBy(() -> PurchaseAmount.from(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(INVALID_PURCHASE_AMOUNT_INSUFFICIENT.getMessage());
    }

    @DisplayName("구입 금액이 1000원 단위가 아니면 예외가 발생한다")
    @ParameterizedTest
    @ValueSource(strings = {"1500", "2300", "10001"})
    void 구입_금액이_1000원_단위가_아니면_예외가_발생한다(String input) {
        // when & then
        assertThatThrownBy(() -> PurchaseAmount.from(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(INVALID_PURCHASE_AMOUNT_UNIT.getMessage());
    }

    @DisplayName("구입 금액이 숫자가 아니면 예외가 발생한다")
    @ParameterizedTest
    @ValueSource(strings = {"abc", "1000원", "천원", ""})
    void 구입_금액이_숫자가_아니면_예외가_발생한다(String input) {
        // when & then
        assertThatThrownBy(() -> PurchaseAmount.from(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(INVALID_PURCHASE_AMOUNT_FORMAT.getMessage());
    }

    @DisplayName("구입 금액으로 로또 개수를 계산한다")
    @Test
    void 구입_금액으로_로또_개수를_계산한다() {
        // given
        PurchaseAmount purchaseAmount = PurchaseAmount.from("5000");

        // when
        int quantity = purchaseAmount.getLottoQuantity();

        // then
        assertThat(quantity).isEqualTo(5);
    }

}
