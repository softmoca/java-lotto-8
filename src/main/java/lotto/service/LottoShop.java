package lotto.service;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.List;
import lotto.Lotto;

public class LottoShop {

    private static final int LOTTO_PRICE = 1000;


    public List<Lotto> buyLottos(int purchaseAmount) {
        int quantity = calculateQuantity(purchaseAmount);
        return generateLottos(quantity);
    }

    private int calculateQuantity(int purchaseAmount) {
        return purchaseAmount / LOTTO_PRICE;
    }

    private List<Lotto> generateLottos(int quantity) {
        List<Lotto> lottos = new ArrayList<>();

        for (int i = 0; i < quantity; i++) {
            List<Integer> numbers = Randoms.pickUniqueNumbersInRange(1, 45, 6);
            lottos.add(new Lotto(numbers));
        }

        return lottos;
    }


}
