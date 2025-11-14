package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.Test;

class LottoMachineTest {

    @Test
    void 로또_발행기를_생성한다() {
        assertThatCode(() -> new LottoMachine())
                .doesNotThrowAnyException();
    }

    @Test
    void 천원_단위가_아니면_예외() {
        LottoMachine machine = new LottoMachine();

        assertThatThrownBy(() -> machine.issue(1500))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("1,000원 단위");
    }

    @Test
    void 구입_금액만큼_로또를_발행한다() {
        LottoMachine machine = new LottoMachine();

        List<Lotto> lottos = machine.issue(8000);

        assertThat(lottos).hasSize(8);
    }

}
