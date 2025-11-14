package lotto.domain;

import java.util.Optional;

public enum Rank {
    FIRST(2_000_000_000),
    SECOND(30_000_000),
    THIRD(1_500_000),
    FOURTH(50_000),
    FIFTH(5_000);

    private final int prizeMoney;

    Rank(int prizeMoney) {
        this.prizeMoney = prizeMoney;
    }

    public int getPrizeMoney() {
        return prizeMoney;
    }

    public static Optional<Rank> of(int matchCount, boolean hasBonus) {
        if (matchCount == 6) {
            return Optional.of(FIRST);
        }
        if (matchCount == 5 && hasBonus) {
            return Optional.of(SECOND);
        }
        if (matchCount == 5) {
            return Optional.of(THIRD);
        }
        if (matchCount == 4) {
            return Optional.of(FOURTH);
        }
        if (matchCount == 3) {
            return Optional.of(FIFTH);
        }
        return Optional.empty();
    }
}
