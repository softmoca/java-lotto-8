package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.Test;

class WinningLottoTest {

    @Test
    void 로또와_비교하여_1등을_판단한다() {
        // given
        Lotto winningNumbers = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        LottoNumber bonusNumber = new LottoNumber(7);
        WinningLotto winning = WinningLotto.of(winningNumbers, bonusNumber);
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));

        // when
        Rank rank = winning.match(lotto);

        // then
        assertThat(rank).isEqualTo(Rank.FIRST);
    }

    @Test
    void 로또와_비교하여_2등을_판단한다() {
        // given
        Lotto winningNumbers = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        LottoNumber bonusNumber = new LottoNumber(7);
        WinningLotto winning = WinningLotto.of(winningNumbers, bonusNumber);
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 7));

        // when
        Rank rank = winning.match(lotto);

        // then
        assertThat(rank).isEqualTo(Rank.SECOND);
    }

    @Test
    void 로또와_비교하여_3등을_판단한다() {
        // given
        Lotto winningNumbers = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        LottoNumber bonusNumber = new LottoNumber(7);
        WinningLotto winning = WinningLotto.of(winningNumbers, bonusNumber);
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 8));

        // when
        Rank rank = winning.match(lotto);

        // then
        assertThat(rank).isEqualTo(Rank.THIRD);
    }

    @Test
    void 로또와_비교하여_낙첨을_판단한다() {
        // given
        Lotto winningNumbers = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        LottoNumber bonusNumber = new LottoNumber(7);
        WinningLotto winning = WinningLotto.of(winningNumbers, bonusNumber);
        Lotto lotto = new Lotto(List.of(1, 2, 8, 9, 10, 11));

        // when
        Rank rank = winning.match(lotto);

        // then
        assertThat(rank).isEqualTo(Rank.NONE);
    }

    @Test
    void 보너스_번호가_당첨_번호와_중복되면_예외가_발생한다() {
        // given
        Lotto winningNumbers = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        LottoNumber bonusNumber = new LottoNumber(6);

        // when & then
        assertThatThrownBy(() -> WinningLotto.of(winningNumbers, bonusNumber))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("중복");
    }
}
