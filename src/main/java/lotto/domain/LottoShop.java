package lotto.domain;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class LottoShop {
    //private final List<Lotto> lottos;

    public static Map<Integer, Integer> find(Lottos lottos, Lotto winnerLotto, int bonusNum) {
        Map<Integer, Integer> map = new LinkedHashMap<>();

        map.put(5, 0);
        map.put(4, 0);
        map.put(3, 0);
        map.put(2, 0);
        map.put(1, 0);

        for (Lotto lotto : lottos.getLottos()) {
            Set<Integer> set = new HashSet<>(lotto.getNumbers());
            int res = 0;
            boolean flag = false;

            for (int x : winnerLotto.getNumbers()) {
                if (set.contains(x)) {
                    res++;
                }
            }
            if (set.contains(bonusNum)) {
                flag = true;
            }

            if (res == 6) {
                map.put(1, map.getOrDefault(1, 0) + 1);
            } else if (res == 5 && flag) {
                map.put(2, map.getOrDefault(2, 0) + 1);
            } else if (res == 5) {
                map.put(3, map.getOrDefault(3, 0) + 1);
            } else if (res == 4) {
                map.put(4, map.getOrDefault(4, 0) + 1);
            } else if (res == 3) {
                map.put(5, map.getOrDefault(3, 0) + 1);
            }
            System.out.println();


        }

        return map;
    }


    public static double find2(Map<Integer, Integer> map, int ti) {
        long sum = 0;
        ti = ti * 1000;

        for (int x : map.keySet()) {

            if (x == 1) {
                sum = sum + (long) map.get(x) * 2000000000;
                //System.out.println("6개 일치 (2,000,000,000원) - " + map.get(x) + "개");

            } else if (x == 2) {
                sum = sum + (long) map.get(x) * 30000000;
                //System.out.println("5개 일치, 보너스 볼 일치 (30,000,000원) - " + map.get(x) + "개");

            } else if (x == 3) {
                //System.out.println("5개 일치 (1,500,000원) - " + map.get(x) + "개");
                sum = sum + (long) map.get(x) * 1500000;
            } else if (x == 4) {
                //System.out.println("4개 일치 (50,000원) - " + map.get(x) + "개");

                sum = sum + (long) map.get(x) * 50000;
            } else if (x == 5) {
                //System.out.println("3개 일치 (5,000원) - " + map.get(x) + "개");
                sum = sum + (long) map.get(x) * 5000;
            }


        }

        double temp = (double) sum / ti * 100;

        return Math.round(temp * 10.0) / 10.0;
    }


}
