package lotto.view;

import java.util.ArrayList;
import java.util.List;
import lotto.Lotto;
import lotto.domain.Rank;
import lotto.domain.WinningStatistics;

public class OutputView {
    private static final String ERROR_PREFIX = "[ERROR] ";
    private static final String PROFIT_RATE_FORMAT = "총 수익률은 %.1f%%입니다.";


    public void printErrorMessage(String message) {
        if (message.startsWith(ERROR_PREFIX)) {
            System.out.println(message);
            return;
        }
        System.out.println(ERROR_PREFIX + message);
    }

    public void printPurchaseCount(int count) {
        System.out.println();
        System.out.println(count + "개를 구매했습니다.");
    }

    public void printLottos(List<Lotto> lottos) {
        for (Lotto lotto : lottos) {
            printLotto(lotto);
        }
    }

    public void printStatisticsHeader() {
        System.out.println();
        System.out.println("당첨 통계");
        System.out.println("---");
    }

    public void printStatistics(WinningStatistics statistics) {
        printRankStatistics(Rank.FIFTH, statistics);
        printRankStatistics(Rank.FOURTH, statistics);
        printRankStatistics(Rank.THIRD, statistics);
        printRankStatistics(Rank.SECOND, statistics);
        printRankStatistics(Rank.FIRST, statistics);
    }

    public void printProfitRate(double profitRate) {
        System.out.println(String.format(PROFIT_RATE_FORMAT, profitRate));
    }

    private void printRankStatistics(Rank rank, WinningStatistics statistics) {
        int count = statistics.getCountByRank(rank);
        int prizeAmount = rank.getPrizeAmount();
        System.out.println(rank.getDescription() + " (" + prizeAmount + "원) - " + count + "개");
    }

    private void printLotto(Lotto lotto) {
        List<Integer> numbers = new ArrayList<>(lotto.getNumbers());
        System.out.println(numbers);
    }


}
