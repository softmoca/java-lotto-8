package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class LottoMachineTest {

    @Test
    void 로또를_발행한다() {
        // given
        Money money = Money.from(3000);

        // when
        List<Lotto> lottos = LottoMachine.issue(money);

        // then
        assertThat(lottos).hasSize(3);
        assertThat(lottos.get(0).getNumbers()).hasSize(6);
    }

    @ParameterizedTest(name = "{0}원 -> {1}장")
    @CsvSource({
            "1000, 1",
            "2000, 2",
            "5000, 5",
            "10000, 10"
    })
    void 다양한_구입_금액으로_로또를_발행한다(int amount, int expectedCount) {
        // given
        Money money = Money.from(amount);

        // when
        List<Lotto> lottos = LottoMachine.issue(money);

        // then
        assertThat(lottos).hasSize(expectedCount);
    }
}
