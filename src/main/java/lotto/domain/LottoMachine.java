package lotto.domain;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.List;

public class LottoMachine {
    private static final int LOTTO_PRICE = 1000;

    private LottoMachine() {
    }

    public static List<Lotto> issue(Money purchaseAmount) {

        int count = calculateLottoCount(purchaseAmount.getAmount());
        return createLottos(count);
    }

    private static int calculateLottoCount(int purchaseAmount) {
        return purchaseAmount / LOTTO_PRICE;
    }

    private static List<Lotto> createLottos(int count) {
        return java.util.stream.Stream.generate(LottoMachine::createLotto)
                .limit(count)
                .toList();
    }


    private static Lotto createLotto() {
        List<Integer> numbers = Randoms.pickUniqueNumbersInRange(1, 45, 6);
        return new Lotto(numbers);
    }


}
