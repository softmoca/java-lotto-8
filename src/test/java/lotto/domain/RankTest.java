package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class RankTest {

    @Test
    void _6개_일치하면_1등이다() {
        Rank rank = Rank.of(6, false);

        assertThat(rank).isEqualTo(Rank.FIRST);
    }
}
