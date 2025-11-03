package lotto.domain.winning;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RankTest {

    @DisplayName("6개 일치하면 1등이다")
    @Test
    void 여섯개_일치하면_1등이다() {
        // when
        Rank rank = Rank.valueOf(6, false);

        // then
        assertThat(rank).isEqualTo(Rank.FIRST);
        assertThat(rank.getPrizeAmount()).isEqualTo(2_000_000_000);
    }

    @DisplayName("5개 일치하고 보너스 볼이 일치하면 2등이다")
    @Test
    void 다섯개_일치하고_보너스_볼이_일치하면_2등이다() {
        // when
        Rank rank = Rank.valueOf(5, true);

        // then
        assertThat(rank).isEqualTo(Rank.SECOND);
        assertThat(rank.getPrizeAmount()).isEqualTo(30_000_000);
    }

    @DisplayName("5개 일치하고 보너스 볼이 불일치하면 3등이다")
    @Test
    void 다섯개_일치하고_보너스_볼이_불일치하면_3등이다() {
        // when
        Rank rank = Rank.valueOf(5, false);

        // then
        assertThat(rank).isEqualTo(Rank.THIRD);
        assertThat(rank.getPrizeAmount()).isEqualTo(1_500_000);
    }

    @DisplayName("4개 일치하면 4등이다")
    @Test
    void 네개_일치하면_4등이다() {
        // when
        Rank rank = Rank.valueOf(4, false);

        // then
        assertThat(rank).isEqualTo(Rank.FOURTH);
        assertThat(rank.getPrizeAmount()).isEqualTo(50_000);
    }

    @DisplayName("3개 일치하면 5등이다")
    @Test
    void 세개_일치하면_5등이다() {
        // when
        Rank rank = Rank.valueOf(3, false);

        // then
        assertThat(rank).isEqualTo(Rank.FIFTH);
        assertThat(rank.getPrizeAmount()).isEqualTo(5_000);
    }

    @DisplayName("2개 이하 일치하면 낙첨이다")
    @Test
    void 두개_이하_일치하면_낙첨이다() {
        // when
        Rank rank1 = Rank.valueOf(2, false);
        Rank rank2 = Rank.valueOf(1, false);
        Rank rank3 = Rank.valueOf(0, false);

        // then
        assertAll(
                () -> assertThat(rank1).isEqualTo(Rank.NONE),
                () -> assertThat(rank2).isEqualTo(Rank.NONE),
                () -> assertThat(rank3).isEqualTo(Rank.NONE)
        );
    }

    @DisplayName("3등 이상은 당첨이다")
    @Test
    void 삼등_이상은_당첨이다() {
        // when & then
        assertAll(
                () -> assertThat(Rank.FIRST.isWinning()).isTrue(),
                () -> assertThat(Rank.SECOND.isWinning()).isTrue(),
                () -> assertThat(Rank.THIRD.isWinning()).isTrue(),
                () -> assertThat(Rank.FOURTH.isWinning()).isTrue(),
                () -> assertThat(Rank.FIFTH.isWinning()).isTrue()
        );
    }

    @DisplayName("낙첨은 당첨이 아니다")
    @Test
    void 낙첨은_당첨이_아니다() {
        // given
        Rank none = Rank.NONE;

        // when & then
        assertThat(none.isWinning()).isFalse();
    }


}
