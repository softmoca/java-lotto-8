package lotto.view;


import java.text.DecimalFormat;
import java.util.List;
import lotto.domain.Lotto;
import lotto.domain.Rank;

public class OutputView {
    private static final DecimalFormat MONEY_FORMAT = new DecimalFormat("#,###");

    public void printPurchaseCount(int count) {
        System.out.println();
        System.out.println(count + "개를 구매했습니다.");
    }

    public void printLottos(List<Lotto> lottos) {
        for (Lotto lotto : lottos) {
            System.out.println(lotto.getNumbers());
        }
    }

    public void printStatisticsHeader() {
        System.out.println();
        System.out.println("당첨 통계");
        System.out.println("---");
    }

    public void printStatistics(Rank rank, int count) {
        if (rank == Rank.NONE) {
            return;
        }

        String message = String.format("%s - %s개",
                getRankMessage(rank),
                count
        );
        System.out.println(message);
    }

    private String getRankMessage(Rank rank) {
        if (rank == Rank.SECOND) {
            return String.format("5개 일치, 보너스 볼 일치 (%s원)",
                    MONEY_FORMAT.format(rank.getPrizeMoney()));
        }

        int matchCount = getMatchCount(rank);
        return String.format("%d개 일치 (%s원)",
                matchCount,
                MONEY_FORMAT.format(rank.getPrizeMoney()));
    }

    private int getMatchCount(Rank rank) {
        return switch (rank) {
            case FIRST -> 6;
            case SECOND, THIRD -> 5;
            case FOURTH -> 4;
            case FIFTH -> 3;
            default -> 0;
        };
    }

    public void printProfitRate(double profitRate) {
        System.out.printf("총 수익률은 %.1f%%입니다.%n", profitRate);
    }


}
