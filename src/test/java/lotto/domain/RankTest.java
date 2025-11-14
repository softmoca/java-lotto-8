package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;
import org.junit.jupiter.api.Test;

class RankTest {

    @Test
    void _6개_일치하면_1등이다() {
        Optional<Rank> rank = Rank.of(6, false);

        assertThat(rank).isEqualTo(Rank.FIRST);
    }

    @Test
    void _5개_일치하고_보너스_일치하면_2등이다() {
        Optional<Rank> rank = Rank.of(5, true);

        assertThat(rank).isEqualTo(Rank.SECOND);
    }

    @Test
    void _5개_일치하고_보너스_불일치하면_3등이다() {
        Optional<Rank> rank = Rank.of(5, false);

        assertThat(rank).isEqualTo(Rank.THIRD);
    }

    @Test
    void _4개_일치하면_4등이다() {
        Optional<Rank> rank = Rank.of(4, false);
        assertThat(rank).isEqualTo(Rank.FOURTH);
    }

    @Test
    void _3개_일치하면_5등이다() {
        Optional<Rank> rank = Rank.of(3, false);
        assertThat(rank).isEqualTo(Rank.FIFTH);
    }

    @Test
    void _2개_이하는_낙첨이다() {
        Optional<Rank> rank = Rank.of(2, false);
        assertThat(rank).isEmpty();

        rank = Rank.of(0, false);
        assertThat(rank).isEmpty();
    }

    @Test
    void 각_등수는_상금을_갖는다() {
        Rank first = Rank.of(6, false).get();
        assertThat(first.getPrizeMoney()).isEqualTo(2_000_000_000);

        Rank second = Rank.of(5, true).get();
        assertThat(second.getPrizeMoney()).isEqualTo(30_000_000);

        Rank fifth = Rank.of(3, false).get();
        assertThat(fifth.getPrizeMoney()).isEqualTo(5_000);
    }


}
