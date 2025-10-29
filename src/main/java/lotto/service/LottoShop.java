package lotto.service;

import java.util.ArrayList;
import java.util.List;
import lotto.Lotto;
import lotto.domain.LottoNumberGenerator;

public class LottoShop {

    private static final int LOTTO_PRICE = 1000;

    private final LottoNumberGenerator numberGenerator;

    public LottoShop(LottoNumberGenerator numberGenerator) {
        this.numberGenerator = numberGenerator;
    }

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
            List<Integer> numbers = numberGenerator.generate();
            lottos.add(new Lotto(numbers));
        }

        return lottos;
    }
}
