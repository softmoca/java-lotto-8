package lotto.domain.winning;

import static lotto.exception.ErrorMessage.BONUS_NUMBER_DUPLICATED;
import static lotto.exception.ErrorMessage.BONUS_NUMBER_OUT_OF_RANGE;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import lotto.domain.Lotto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class BonusNumberTest {

    @DisplayName("유효한 보너스 번호로 객체가 정상 생성된다")
    @Test
    void 유효한_보너스_번호로_객체가_정상_생성된다() {
        // given
        Lotto winningNumbers = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        String input = "7";

        // when & then
        assertThatCode(() -> BonusNumber.of(input, winningNumbers))
                .doesNotThrowAnyException();
    }

    @DisplayName("보너스 번호가 1~45 범위를 벗어나면 예외가 발생한다")
    @ParameterizedTest
    @ValueSource(strings = {"0", "-1", "-10", "46", "50", "100"})
    void 보너스_번호가_범위를_벗어나면_예외가_발생한다(String input) {
        // given
        Lotto winningNumbers = new Lotto(List.of(1, 2, 3, 4, 5, 6));

        // when & then
        assertThatThrownBy(() -> BonusNumber.of(input, winningNumbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(BONUS_NUMBER_OUT_OF_RANGE.getMessage());
    }

    @DisplayName("보너스 번호가 당첨 번호와 중복되면 예외가 발생한다")
    @Test
    void 보너스_번호가_당첨_번호와_중복되면_예외가_발생한다() {
        // given
        Lotto winningNumbers = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        String input = "6";

        // when & then
        assertThatThrownBy(() -> BonusNumber.of(input, winningNumbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(BONUS_NUMBER_DUPLICATED.getMessage());
    }

    @DisplayName("보너스 번호가 숫자가 아니면 예외가 발생한다")
    @ParameterizedTest
    @ValueSource(strings = {"a", "보너스", ""})
    void 보너스_번호가_숫자가_아니면_예외가_발생한다(String input) {
        // given
        Lotto winningNumbers = new Lotto(List.of(1, 2, 3, 4, 5, 6));

        // when & then
        assertThatThrownBy(() -> BonusNumber.of(input, winningNumbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(BONUS_NUMBER_OUT_OF_RANGE.getMessage());
    }

}
