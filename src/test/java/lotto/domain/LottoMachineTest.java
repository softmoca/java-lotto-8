package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class LottoMachineTest {

    @Test
    void 로또_발행기를_생성한다() {
        assertThatCode(() -> new LottoMachine())
                .doesNotThrowAnyException();
    }

    @ParameterizedTest(name = "{0}원 -> {1}장")
    @CsvSource({
            "1000, 1",
            "2000, 2",
            "5000, 5",
            "10000, 10"
    })
    void 다양한_구입_금액으로_로또를_발행한다(int amount, int expectedCount) {
        LottoMachine machine = new LottoMachine();
        Money money = Money.from(amount);

        List<Lotto> lottos = machine.issue(money);

        assertThat(lottos).hasSize(expectedCount);
    }


}
