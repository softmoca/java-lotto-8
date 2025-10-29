package lotto.service;

import java.util.List;
import lotto.Lotto;

public class LottoShop {

    private static final int LOTTO_PRICE = 1000;


    public List<Lotto> buyLottos(int purchaseAmount) {
        int quantity = calculateQuantity(purchaseAmount);

    }

    private int calculateQuantity(int purchaseAmount) {
        return purchaseAmount / LOTTO_PRICE;
    }


}
