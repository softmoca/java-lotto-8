package lotto.view;

import java.text.DecimalFormat;
import java.util.List;
import lotto.domain.Lotto;
import lotto.domain.LottoResult;
import lotto.domain.RankStatistic;

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

    public void printResult(LottoResult result) {
        printStatisticsHeader();

        for (RankStatistic stat : result.getRankStatistics()) {
            printRankStatistic(stat);
        }

        printProfitRate(result.getProfitRate());
    }

    private void printStatisticsHeader() {
        System.out.println();
        System.out.println("당첨 통계");
        System.out.println("---");
    }

    private void printRankStatistic(RankStatistic stat) {
        String message = String.format("%s (%s원) - %d개",
                stat.getDescription(),
                MONEY_FORMAT.format(stat.getPrizeMoney()),
                stat.getCount()
        );
        System.out.println(message);
    }

    private void printProfitRate(double profitRate) {
        System.out.printf("총 수익률은 %.1f%%입니다.%n", profitRate);
    }

    public void printErrorMessage(String message) {
        System.out.println(message);
    }
}
