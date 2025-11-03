package lotto.domain;

import static lotto.exception.ErrorMessage.INVALID_LOTTO_DUPLICATION;
import static lotto.exception.ErrorMessage.INVALID_LOTTO_NUMBER_FORMAT;
import static lotto.exception.ErrorMessage.INVALID_LOTTO_NUMBER_RANGE;
import static lotto.exception.ErrorMessage.INVALID_LOTTO_SIZE;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class LottoTest {
    @Test
    void 로또_번호의_개수가_6개가_넘어가면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 6, 7)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 번호에 중복된 숫자가 있으면 예외가 발생한다.")
    @Test
    void 로또_번호에_중복된_숫자가_있으면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 5)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 번호 6개로 로또가 정상 생성된다")
    @Test
    void 로또가_정상_생성된다() {
        // given
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);

        // when & then
        assertThatCode(() -> new Lotto(numbers))
                .doesNotThrowAnyException();
    }

    @DisplayName("로또 번호의 개수가 6개 미만이면 예외가 발생한다")
    @Test
    void 로또_번호의_개수가_6개_미만이면_예외가_발생한다() {
        // given
        List<Integer> numbers = List.of(1, 2, 3, 4, 5);

        // when & then
        assertThatThrownBy(() -> new Lotto(numbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(INVALID_LOTTO_SIZE.getMessage());
    }

    @DisplayName("로또 번호에 중복된 숫자가 있으면 예외가 발생한다2")
    @Test
    void 로또_번호에_중복된_숫자가_있으면_예외가_발생한다2() {
        // given
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 5);

        // when & then
        assertThatThrownBy(() -> new Lotto(numbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(INVALID_LOTTO_DUPLICATION.getMessage());
    }

    @DisplayName("로또 번호가 1~45 범위를 벗어나면 예외가 발생한다")
    @ParameterizedTest
    @ValueSource(ints = {0, -1, -10, 46, 50, 100})
    void 로또_번호가_범위를_벗어나면_예외가_발생한다(int invalidNumber) {
        // given
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, invalidNumber);

        // when & then
        assertThatThrownBy(() -> new Lotto(numbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(INVALID_LOTTO_NUMBER_RANGE.getMessage());
    }

    @DisplayName("문자열 리스트로 로또를 생성한다")
    @Test
    void 문자열_리스트로_로또를_생성한다() {
        // given
        List<String> numberStrings = List.of("1", "2", "3", "4", "5", "6");

        // when
        Lotto lotto = Lotto.from(numberStrings);

        // then
        assertThat(lotto.getNumbers()).containsExactly(1, 2, 3, 4, 5, 6);
    }

    @DisplayName("문자열이 숫자가 아니면 예외가 발생한다")
    @Test
    void 문자열이_숫자가_아니면_예외가_발생한다() {
        // given
        List<String> numberStrings = List.of("1", "2", "3", "4", "5", "a");

        // when & then
        assertThatThrownBy(() -> Lotto.from(numberStrings))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(INVALID_LOTTO_NUMBER_FORMAT.getMessage());
    }

    @DisplayName("로또가 특정 번호를 포함하는지 확인한다")
    @Test
    void 로또가_특정_번호를_포함하는지_확인한다() {
        // given
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));

        // when & then
        assertThat(lotto.contains(1)).isTrue();
        assertThat(lotto.contains(7)).isFalse();
    }

}
