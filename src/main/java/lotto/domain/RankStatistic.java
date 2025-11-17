package lotto.domain;

public class RankStatistic {
    private final Rank rank;
    private final int count;

    private RankStatistic(Rank rank, int count) {
        this.rank = rank;
        this.count = count;
    }

    public static RankStatistic of(Rank rank, int count) {
        return new RankStatistic(rank, count);
    }

    public String getDescription() {
        return rank.getDescription();
    }

    public int getPrizeMoney() {
        return rank.getPrizeMoney();
    }

    public int getCount() {
        return count;
    }

    public Rank getRank() {
        return rank;
    }
}
