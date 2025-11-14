package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class RankTest {

    @Test
    void _6개_일치하면_1등이다() {
        Rank rank = Rank.of(6, false);

        assertThat(rank).isEqualTo(Rank.FIRST);
    }

    @Test
    void _5개_일치하고_보너스_일치하면_2등이다() {
        Rank rank = Rank.of(5, true);

        assertThat(rank).isEqualTo(Rank.SECOND);
    }

    @Test
    void _5개_일치하고_보너스_불일치하면_3등이다() {
        Rank rank = Rank.of(5, false);

        assertThat(rank).isEqualTo(Rank.THIRD);
    }


}
