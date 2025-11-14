package lotto.domain;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.List;

public class LottoMachine {
    private static final int LOTTO_PRICE = 1000;

    public LottoMachine() {
    }

    public List<Lotto> issue(int purchaseAmount) {
        validatePurchaseAmount(purchaseAmount);
        int count = calculateLottoCount(purchaseAmount);
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

    private void validatePurchaseAmount(int purchaseAmount) {
        if (purchaseAmount <= 0) {
            throw new IllegalArgumentException(
                    "[ERROR] 구입 금액은 양수여야 합니다."
            );
        }

        if (purchaseAmount % LOTTO_PRICE != 0) {
            throw new IllegalArgumentException(
                    "[ERROR] 구입 금액은 1,000원 단위여야 합니다."
            );
        }
    }

    private Lotto createLotto() {
        List<Integer> numbers = Randoms.pickUniqueNumbersInRange(1, 45, 6);
        return new Lotto(numbers);
    }


}
