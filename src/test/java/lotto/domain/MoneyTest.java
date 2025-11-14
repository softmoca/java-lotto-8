package lotto.domain;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class MoneyTest {
    @Test
    void 구입_금액을_생성한다() {
        assertThatCode(() -> Money.from(1000))
                .doesNotThrowAnyException();
    }

    @ParameterizedTest
    @ValueSource(ints = {0, -1000})
    void 구입_금액은_양수여야_한다(int amount) {
        assertThatThrownBy(() -> Money.from(amount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("양수");
    }

    @ParameterizedTest
    @ValueSource(ints = {500, 1500, 2300})
    void 구입_금액은_1000원_단위여야_한다(int amount) {
        assertThatThrownBy(() -> Money.from(amount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("1,000원 단위");
    }

}
