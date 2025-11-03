package lotto.util;

import static lotto.exception.ErrorMessage.CONSECUTIVE_DELIMITER;
import static lotto.exception.ErrorMessage.EMPTY_INPUT;
import static lotto.exception.ErrorMessage.ENDS_WITH_DELIMITER;
import static lotto.exception.ErrorMessage.NO_DELIMITER;
import static lotto.exception.ErrorMessage.STARTS_WITH_DELIMITER;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class InputValidatorTest {

    @DisplayName("유효한 입력은 예외가 발생하지 않는다")
    @Test
    void 유효한_입력은_예외가_발생하지_않는다() {
        // given
        String input = "1,2,3,4,5,6";

        // when & then
        assertThatCode(() -> InputValidator.validateInput(input))
                .doesNotThrowAnyException();
    }

    @DisplayName("입력이 비어있으면 예외가 발생한다")
    @ParameterizedTest
    @ValueSource(strings = {"", "   "})
    void 입력이_비어있으면_예외가_발생한다(String input) {
        // when & then
        assertThatThrownBy(() -> InputValidator.validateInput(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(EMPTY_INPUT.getMessage());
    }


    @DisplayName("쉼표가 없으면 예외가 발생한다")
    @Test
    void 쉼표가_없으면_예외가_발생한다() {
        // given
        String input = "123456";

        // when & then
        assertThatThrownBy(() -> InputValidator.validateInput(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(NO_DELIMITER.getMessage());
    }

    @DisplayName("연속된 쉼표가 있으면 예외가 발생한다")
    @Test
    void 연속된_쉼표가_있으면_예외가_발생한다() {
        // given
        String input = "1,,2,3,4,5,6";

        // when & then
        assertThatThrownBy(() -> InputValidator.validateInput(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(CONSECUTIVE_DELIMITER.getMessage());
    }

    @DisplayName("쉼표로 시작하면 예외가 발생한다")
    @Test
    void 쉼표로_시작하면_예외가_발생한다() {
        // given
        String input = ",1,2,3,4,5,6";

        // when & then
        assertThatThrownBy(() -> InputValidator.validateInput(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(STARTS_WITH_DELIMITER.getMessage());
    }

    @DisplayName("쉼표로 끝나면 예외가 발생한다")
    @Test
    void 쉼표로_끝나면_예외가_발생한다() {
        // given
        String input = "1,2,3,4,5,6,";

        // when & then
        assertThatThrownBy(() -> InputValidator.validateInput(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ENDS_WITH_DELIMITER.getMessage());
    }
}
