package lotto.domain;

import java.util.Arrays;

public enum Rank {
    FIRST(6, 2_000_000_000, false),
    SECOND(5, 30_000_000, true),
    THIRD(5, 1_500_000, false),
    FOURTH(4, 50_000, false),
    FIFTH(3, 5_000, false),
    NONE(0, 0, false);

    private final int matchCount;
    private final int prizeMoney;
    private final boolean requireBonus;

    Rank(int matchCount, int prizeMoney, boolean requireBonus) {
        this.matchCount = matchCount;
        this.prizeMoney = prizeMoney;
        this.requireBonus = requireBonus;
    }

    public int getPrizeMoney() {
        return prizeMoney;
    }

    public static Rank of(int matchCount, boolean hasBonus) {
        return Arrays.stream(values())
                .filter(rank -> rank.matches(matchCount, hasBonus))
                .findFirst()
                .orElse(NONE);
    }

    private boolean matches(int matchCount, boolean hasBonus) {
        if (this == NONE) {
            return false;
        }

        if (this.matchCount != matchCount) {
            return false;
        }

        if (requireBonus) {
            return hasBonus;
        }

        return true;
    }

    public boolean isWinning() {
        return this != NONE;
    }


}
