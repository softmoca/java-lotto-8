package lotto.controller;

import java.util.Map;
import lotto.domain.Lotto;
import lotto.domain.LottoShop;
import lotto.domain.Lottos;
import lotto.domain.Money;
import lotto.view.InputView;
import lotto.view.OutputView;

public class Controller {

    public void run() {

        int tiketCount = getTiketCount();
        Lottos lottos = new Lottos(tiketCount);
        OutputView.printLottos(lottos);

        Lotto winnerLotto = getWinnerLotto();

        System.out.println();
        int bounsNum = getBonusNum(winnerLotto);
        Map<Integer, Integer> map = LottoShop.find(lottos, winnerLotto, bounsNum);
        OutputView.printResult(map);
        double temp = LottoShop.find2(map, tiketCount);

        String PROFIT_RATE_FORMAT = "총 수익률은 %.1f%%입니다.";
        System.out.println(String.format(PROFIT_RATE_FORMAT, temp));


    }

    private int getBonusNum(Lotto winnerLotto) {

        while (true) {

            try {
                int input = InputView.readInputBonusNum();

                if (input < 1 || input > 45) {
                    throw new IllegalArgumentException("[ERROR] 보너스 번호 범위 ");
                }

                for (int x : winnerLotto.getNumbers()) {
                    if (x == input) {
                        throw new IllegalArgumentException("[ERROR] 보너스 중복 ");

                    }
                }
                return input;

            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

    }

    private Lotto getWinnerLotto() {

        while (true) {

            try {
                String input = InputView.readInputWinnerLotto();
                Lotto lotto = new Lotto(input);
                return lotto;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }


    }

    private int getTiketCount() {
        while (true) {

            try {
                String input = InputView.readInputMoney();
                Money m = new Money(input);
                return m.getTiketCount();

            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }


}
