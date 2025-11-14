package lotto.domain;

import java.util.Optional;

public enum Rank {
    FIRST,
    SECOND,
    THIRD,
    FOURTH,
    FIFTH;

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
