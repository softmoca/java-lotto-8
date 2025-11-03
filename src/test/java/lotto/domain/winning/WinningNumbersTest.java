package lotto.domain.winning;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import lotto.domain.Lotto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WinningNumbersTest {

    @DisplayName("6개 일치하면 1등으로 판정한다")
    @Test
    void 여섯개_일치하면_1등으로_판정한다() {
        // given
        Lotto winningLotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        BonusNumber bonusNumber = BonusNumber.of("7", winningLotto);
        WinningNumbers winningNumbers = new WinningNumbers(winningLotto, bonusNumber);

        Lotto purchasedLotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));

        // when
        Rank rank = winningNumbers.match(purchasedLotto);

        // then
        assertThat(rank).isEqualTo(Rank.FIRST);
    }

    @DisplayName("5개 일치하고 보너스 볼이 일치하면 2등으로 판정한다")
    @Test
    void 다섯개_일치하고_보너스_볼이_일치하면_2등으로_판정한다() {
        // given
        Lotto winningLotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        BonusNumber bonusNumber = BonusNumber.of("7", winningLotto);
        WinningNumbers winningNumbers = new WinningNumbers(winningLotto, bonusNumber);

        Lotto purchasedLotto = new Lotto(List.of(1, 2, 3, 4, 5, 7));

        // when
        Rank rank = winningNumbers.match(purchasedLotto);

        // then
        assertThat(rank).isEqualTo(Rank.SECOND);
    }

    @DisplayName("5개 일치하고 보너스 볼이 불일치하면 3등으로 판정한다")
    @Test
    void 다섯개_일치하고_보너스_볼이_불일치하면_3등으로_판정한다() {
        // given
        Lotto winningLotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        BonusNumber bonusNumber = BonusNumber.of("7", winningLotto);
        WinningNumbers winningNumbers = new WinningNumbers(winningLotto, bonusNumber);

        Lotto purchasedLotto = new Lotto(List.of(1, 2, 3, 4, 5, 8));

        // when
        Rank rank = winningNumbers.match(purchasedLotto);

        // then
        assertThat(rank).isEqualTo(Rank.THIRD);
    }

    @DisplayName("3개 일치하면 5등으로 판정한다")
    @Test
    void 세개_일치하면_5등으로_판정한다() {
        // given
        Lotto winningLotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        BonusNumber bonusNumber = BonusNumber.of("7", winningLotto);
        WinningNumbers winningNumbers = new WinningNumbers(winningLotto, bonusNumber);

        Lotto purchasedLotto = new Lotto(List.of(1, 2, 3, 10, 11, 12));

        // when
        Rank rank = winningNumbers.match(purchasedLotto);

        // then
        assertThat(rank).isEqualTo(Rank.FIFTH);
    }

    @DisplayName("2개 이하 일치하면 낙첨으로 판정한다")
    @Test
    void 두개_이하_일치하면_낙첨으로_판정한다() {
        // given
        Lotto winningLotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        BonusNumber bonusNumber = BonusNumber.of("7", winningLotto);
        WinningNumbers winningNumbers = new WinningNumbers(winningLotto, bonusNumber);

        Lotto purchasedLotto = new Lotto(List.of(1, 2, 10, 11, 12, 13));

        // when
        Rank rank = winningNumbers.match(purchasedLotto);

        // then
        assertThat(rank).isEqualTo(Rank.NONE);
    }
}
