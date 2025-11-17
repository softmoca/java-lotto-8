package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class RankTest {
    @ParameterizedTest(name = "{0}개 일치, 보너스 {1} -> {2}")
    @CsvSource({
            "6, false, FIRST",
            "6, true, FIRST",
            "5, true, SECOND",
            "5, false, THIRD",
            "4, false, FOURTH",
            "4, true, FOURTH",
            "3, false, FIFTH",
            "2, false, NONE",
            "1, false, NONE",
            "0, false, NONE"
    })
    void 일치_개수와_보너스_여부로_등수를_판단한다(
            int matchCount,
            boolean hasBonus,
            Rank expected
    ) {
        assertThat(Rank.of(matchCount, hasBonus)).isEqualTo(expected);
    }

    @Test
    void 각_등수는_상금을_갖는다() {
        assertAll(
                () -> assertEquals(2_000_000_000, Rank.FIRST.getPrizeMoney()),
                () -> assertEquals(30_000_000, Rank.SECOND.getPrizeMoney()),
                () -> assertEquals(1_500_000, Rank.THIRD.getPrizeMoney()),
                () -> assertEquals(50_000, Rank.FOURTH.getPrizeMoney()),
                () -> assertEquals(5_000, Rank.FIFTH.getPrizeMoney()),
                () -> assertEquals(0, Rank.NONE.getPrizeMoney())
        );

    }

    @Test
    void _5개_일치_시_보너스가_등수를_결정한다() {
        assertThat(Rank.of(5, true)).isEqualTo(Rank.SECOND);
        assertThat(Rank.of(5, false)).isEqualTo(Rank.THIRD);
    }


}
