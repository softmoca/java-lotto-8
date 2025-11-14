package lotto.domain;

public enum Rank {
    FIRST,
    SECOND,
    THIRD,
    FOURTH,
    FIFTH;

    public static Rank of(int matchCount, boolean hasBonus) {
        if (matchCount == 6) {
            return FIRST;
        }
        if (matchCount == 5 && hasBonus) {
            return SECOND;
        }
        if (matchCount == 5) {
            return THIRD;
        }
        return FIRST;
    }
}
