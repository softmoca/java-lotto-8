package lotto.domain;

import static org.assertj.core.api.Assertions.assertThatCode;

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


}
