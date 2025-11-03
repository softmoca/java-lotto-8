package lotto.domain.winning;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class RankTest {

    @DisplayName("6개 일치하면 보너스와 상관없이 1등이다")
    @ParameterizedTest
    @CsvSource({
            "false, FIRST",
            "true, FIRST"
    })
    void 여섯개_일치하면_보너스_상관없이_1등이다(boolean hasBonus, Rank expected) {
        // when
        Rank rank = Rank.valueOf(6, hasBonus);

        // then
        assertThat(rank).isEqualTo(expected);
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

    @DisplayName("4개 일치하면 보너스와 상관없이 4등이다")
    @ParameterizedTest
    @CsvSource({
            "false, FOURTH",
            "true, FOURTH"
    })
    void 네개_일치하면_보너스_상관없이_4등이다(boolean hasBonus, Rank expected) {
        // when
        Rank rank = Rank.valueOf(4, hasBonus);

        // then
        assertThat(rank).isEqualTo(expected);
        assertThat(rank.getPrizeAmount()).isEqualTo(50_000);
    }

    @DisplayName("3개 일치하면 보너스와 상관없이 5등이다")
    @ParameterizedTest
    @CsvSource({
            "false, FIFTH",
            "true, FIFTH"   // ← 이 케이스가 버그를 찾았을 것!
    })
    void 세개_일치하면_보너스_상관없이_5등이다(boolean hasBonus, Rank expected) {
        // when
        Rank rank = Rank.valueOf(3, hasBonus);

        // then
        assertThat(rank).isEqualTo(expected);
        assertThat(rank.getPrizeAmount()).isEqualTo(5_000);
    }

    @DisplayName("2개 이하 일치하면 보너스와 상관없이 낙첨이다")
    @ParameterizedTest
    @CsvSource({
            "0, false",
            "0, true",
            "1, false",
            "1, true",
            "2, false",
            "2, true"
    })
    void 두개_이하_일치하면_낙첨이다(int matchCount, boolean hasBonus) {
        // when
        Rank rank = Rank.valueOf(matchCount, hasBonus);

        // then
        assertThat(rank).isEqualTo(Rank.NONE);
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

    @DisplayName("보너스 번호는 5개 일치할 때만 등수에 영향을 준다")
    @Test
    void 보너스_번호는_5개_일치할때만_영향을_준다() {
        // 5개 일치 - 보너스에 따라 2등/3등 구분
        assertThat(Rank.valueOf(5, true)).isEqualTo(Rank.SECOND);
        assertThat(Rank.valueOf(5, false)).isEqualTo(Rank.THIRD);

        // 그 외 - 보너스 무관
        assertThat(Rank.valueOf(6, true)).isEqualTo(Rank.valueOf(6, false));
        assertThat(Rank.valueOf(4, true)).isEqualTo(Rank.valueOf(4, false));
        assertThat(Rank.valueOf(3, true)).isEqualTo(Rank.valueOf(3, false));
    }

}
