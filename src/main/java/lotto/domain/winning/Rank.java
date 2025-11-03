package lotto.domain.winning;

import java.util.Arrays;

public enum Rank {
    FIRST(6, 2_000_000_000, false, "6개 일치"),
    SECOND(5, 30_000_000, true, "5개 일치, 보너스 볼 일치"),
    THIRD(5, 1_500_000, false, "5개 일치"),
    FOURTH(4, 50_000, false, "4개 일치"),
    FIFTH(3, 5_000, false, "3개 일치"),
    NONE(0, 0, false, "낙첨");

    private final int matchCount;
    private final int prizeAmount;
    private final boolean requiresBonus;
    private final String description;

    Rank(int matchCount, int prizeAmount, boolean requiresBonus, String description) {
        this.matchCount = matchCount;
        this.prizeAmount = prizeAmount;
        this.requiresBonus = requiresBonus;
        this.description = description;
    }

    public static Rank valueOf(int matchCount, boolean hasBonus) {
        return Arrays.stream(values())
                .filter(rank -> rank.matches(matchCount, hasBonus))
                .findFirst()
                .orElse(NONE);
    }


    private boolean matches(int count, boolean bonus) {
        if (this.matchCount != count) {
            return false;
        }

        return !this.requiresBonus || bonus;
    }


    public int getPrizeAmount() {
        return prizeAmount;
    }

    public String getDescription() {
        return description;
    }

    public boolean isWinning() {
        return this != NONE;
    }

}
