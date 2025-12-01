package lotto.domain;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.List;

public class Lottos {
    private final List<Lotto> lottos;

    public Lottos(int tiketCount) {
        List<Lotto> temp = new ArrayList<>();

        for (int i = 0; i < tiketCount; i++) {
            List<Integer> lotto = Randoms.pickUniqueNumbersInRange(1, 45, 6);
            temp.add(new Lotto(lotto));
        }
        this.lottos = temp;

    }

    public int getCnt() {
        return lottos.size();
    }

    public List<Lotto> getLottos() {
        return lottos;
    }
}
