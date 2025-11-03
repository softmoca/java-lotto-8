package lotto.domain.machine;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.List;
import lotto.domain.Lotto;
import lotto.domain.moeny.PurchaseAmount;
import lotto.domain.winning.BonusNumber;
import lotto.domain.winning.Rank;
import lotto.domain.winning.WinningNumbers;
import lotto.domain.winning.WinningStatistics;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoMachineTest {

    static class FixedLottoNumberGenerator implements LottoNumberGenerator {
        private final List<List<Integer>> numbers;
        private int index = 0;

        public FixedLottoNumberGenerator(List<Integer>... numbers) {
            this.numbers = List.of(numbers);
        }

        @Override
        public List<Integer> generate() {
            return numbers.get(index++);
        }
    }

    @DisplayName("구입 금액만큼 로또를 발급한다")
    @Test
    void 구입_금액만큼_로또를_발급한다() {
        // given
        LottoMachine machine = new LottoMachine(new RandomLottoNumberGenerator());
        PurchaseAmount purchaseAmount = PurchaseAmount.from("3000");

        // when
        machine.purchase(purchaseAmount);

        // then
        assertThat(machine.getPurchasedCount()).isEqualTo(3);
    }

    @DisplayName("구매한 로또 리스트를 수정할 수 없다")
    @Test
    void 구매한_로또_리스트를_수정할_수_없다() {
        // given
        LottoMachine machine = new LottoMachine(new RandomLottoNumberGenerator());
        machine.purchase(PurchaseAmount.from("1000"));

        // when
        List<Lotto> lottos = machine.getPurchasedLottos();

        // then
        assertThatThrownBy(() -> lottos.add(new Lotto(List.of(1, 2, 3, 4, 5, 6))))
                .isInstanceOf(UnsupportedOperationException.class);
    }

    @DisplayName("당첨 통계를 정확히 계산한다")
    @Test
    void 당첨_통계를_정확히_계산한다() {
        // given
        LottoMachine machine = new LottoMachine(new FixedLottoNumberGenerator(
                List.of(1, 2, 3, 4, 5, 6),    // 1등
                List.of(1, 2, 3, 4, 5, 7),    // 2등 (보너스 일치)
                List.of(1, 2, 3, 4, 5, 8),    // 3등
                List.of(1, 2, 3, 4, 10, 11),  // 4등
                List.of(1, 2, 3, 10, 11, 12)  // 5등
        ));
        machine.purchase(PurchaseAmount.from("5000"));

        Lotto winningLotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        BonusNumber bonusNumber = BonusNumber.of("7", winningLotto);
        WinningNumbers winningNumbers = new WinningNumbers(winningLotto, bonusNumber);

        // when
        WinningStatistics statistics = machine.calculateStatistics(winningNumbers);

        // then
        assertAll(
                () -> assertThat(statistics.getCountByRank(Rank.FIRST)).isEqualTo(1),
                () -> assertThat(statistics.getCountByRank(Rank.SECOND)).isEqualTo(1),
                () -> assertThat(statistics.getCountByRank(Rank.THIRD)).isEqualTo(1),
                () -> assertThat(statistics.getCountByRank(Rank.FOURTH)).isEqualTo(1),
                () -> assertThat(statistics.getCountByRank(Rank.FIFTH)).isEqualTo(1)
        );
    }
    
}
