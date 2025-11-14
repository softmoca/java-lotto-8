package lotto.domain;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.Test;

class WinningLottoTest {

    @Test
    void 당첨_번호를_생성한다() {
        List<Integer> winningNumbers = List.of(1, 2, 3, 4, 5, 6);
        int bonusNumber = 7;

        assertThatCode(() -> new WinningLotto(winningNumbers, bonusNumber))
                .doesNotThrowAnyException();
    }

    @Test
    void 보너스_번호가_당첨_번호와_중복되면_예외() {
        List<Integer> winningNumbers = List.of(1, 2, 3, 4, 5, 6);
        int bonusNumber = 6;

        assertThatThrownBy(() -> new WinningLotto(winningNumbers, bonusNumber))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("중복");
    }

    @Test
    void 보너스_번호는_1부터_45_사이여야_한다() {
        List<Integer> winningNumbers = List.of(1, 2, 3, 4, 5, 6);

        assertThatThrownBy(() -> new WinningLotto(winningNumbers, 0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("1부터 45");

        assertThatThrownBy(() -> new WinningLotto(winningNumbers, 46))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("1부터 45");
    }
}
