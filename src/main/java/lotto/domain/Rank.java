package lotto.domain;

public enum Rank {
    FIRST;

    public static Rank of(int matchCount, boolean hasBonus) {
        return FIRST;
    }
}
