package lotto.view;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import lotto.domain.Lotto;
import lotto.domain.Lottos;

public class OutputView {

    public static void printLottos(Lottos lottos) {
        System.out.println();
        System.out.println(lottos.getCnt() + "개를 구매했습니다.");

        for (Lotto lotto : lottos.getLottos()) {
            List<Integer> numbers = new ArrayList<>(lotto.getNumbers());
            numbers.sort(Comparator.naturalOrder());
            System.out.println(numbers);
        }


    }

    public static void printResult(Map<Integer, Integer> map) {
        System.out.println();

        System.out.println("당첨 통계");
        System.out.println("---");

        for (int x : map.keySet()) {

            if (x == 1) {
                System.out.println("6개 일치 (2,000,000,000원) - " + map.get(x) + "개");

            } else if (x == 2) {
                System.out.println("5개 일치, 보너스 볼 일치 (30,000,000원) - " + map.get(x) + "개");

            } else if (x == 3) {
                System.out.println("5개 일치 (1,500,000원) - " + map.get(x) + "개");

            } else if (x == 4) {
                System.out.println("4개 일치 (50,000원) - " + map.get(x) + "개");

            } else if (x == 5) {
                System.out.println("3개 일치 (5,000원) - " + map.get(x) + "개");

            }


        }


    }


}
