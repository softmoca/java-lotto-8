package lotto.domain;

import java.util.Arrays;
import java.util.List;

public enum Rank {
    FIRST(6, 2_000_000_000, false, "6개 일치"),
    SECOND(5, 30_000_000, true, "5개 일치, 보너스 볼 일치"),
    THIRD(5, 1_500_000, false, "5개 일치"),
    FOURTH(4, 50_000, false, "4개 일치"),
    FIFTH(3, 5_000, false, "3개 일치"),
    NONE(0, 0, false, "");

    private final int matchCount;
    private final int prizeMoney;
    private final boolean requireBonus;
    private final String description;

    Rank(int matchCount, int prizeMoney, boolean requireBonus, String description) {
        this.matchCount = matchCount;
        this.prizeMoney = prizeMoney;
        this.requireBonus = requireBonus;
        this.description = description;
    }

    public int getPrizeMoney() {
        return prizeMoney;
    }

    public String getDescription() {
        return description;
    }

    public int getMatchCount() {
        return matchCount;
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

    public static List<Rank> getWinningRanksInDisplayOrder() {
        return List.of(FIFTH, FOURTH, THIRD, SECOND, FIRST);
    }

}
