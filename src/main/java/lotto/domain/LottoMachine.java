package lotto.domain;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.List;

public class LottoMachine {
    private static final int LOTTO_PRICE = 1000;

    public LottoMachine() {
    }

    public List<Lotto> issue(Money purchaseAmount) {

        int count = calculateLottoCount(purchaseAmount.getAmount());
        return createLottos(count);
    }

    private int calculateLottoCount(int purchaseAmount) {
        return purchaseAmount / LOTTO_PRICE;
    }

    private List<Lotto> createLottos(int count) {
        return java.util.stream.Stream.generate(this::createLotto)
                .limit(count)
                .toList();
    }


    private Lotto createLotto() {
        List<Integer> numbers = Randoms.pickUniqueNumbersInRange(1, 45, 6);
        return new Lotto(numbers);
    }


}
