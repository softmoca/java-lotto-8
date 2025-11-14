package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.Test;

class WinningLottoTest {
    
    @Test
    void 로또와_비교하여_등수를_판단한다_1등() {
        Lotto winningNumbers = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        LottoNumber bonusNumber = new LottoNumber(7);

        WinningLotto winning = WinningLotto.of(winningNumbers, bonusNumber);
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));

        Rank rank = winning.match(lotto);

        assertThat(rank).isEqualTo(Rank.FIRST);
    }

    @Test
    void 로또와_비교하여_등수를_판단한다_2등() {
        Lotto winningNumbers = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        LottoNumber bonusNumber = new LottoNumber(7);

        WinningLotto winning = WinningLotto.of(winningNumbers, bonusNumber);
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 7));

        Rank rank = winning.match(lotto);

        assertThat(rank).isEqualTo(Rank.SECOND);
    }

    @Test
    void 로또와_비교하여_등수를_판단한다_3등() {
        Lotto winningNumbers = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        LottoNumber bonusNumber = new LottoNumber(7);

        WinningLotto winning = WinningLotto.of(winningNumbers, bonusNumber);
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 8));

        Rank rank = winning.match(lotto);

        assertThat(rank).isEqualTo(Rank.THIRD);
    }

    @Test
    void 로또와_비교하여_등수를_판단한다_낙첨() {
        Lotto winningNumbers = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        LottoNumber bonusNumber = new LottoNumber(7);

        WinningLotto winning = WinningLotto.of(winningNumbers, bonusNumber);

        Lotto lotto = new Lotto(List.of(1, 2, 8, 9, 10, 11));

        Rank rank = winning.match(lotto);

        assertThat(rank).isEqualTo(Rank.NONE);
    }


    @Test
    void 정적_팩토리로_당첨_번호를_생성한다() {
        Lotto winningNumbers = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        LottoNumber bonusNumber = new LottoNumber(7);

        assertThatCode(() -> WinningLotto.of(winningNumbers, bonusNumber))
                .doesNotThrowAnyException();
    }

    @Test
    void 정적_팩토리에서_보너스가_당첨_번호와_중복되면_예외() {
        Lotto winningNumbers = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        LottoNumber bonusNumber = new LottoNumber(6);

        assertThatThrownBy(() -> WinningLotto.of(winningNumbers, bonusNumber))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("중복");
    }

}
