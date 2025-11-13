package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

class LottoNumberTest {

    @Test
    void 로또_번호는_1보다_작을_수_없다() {
        assertThatThrownBy(() -> new LottoNumber(0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("1부터 45");
    }

    @Test
    void 로또_번호는_45보다_클_수_없다() {
        assertThatThrownBy(() -> new LottoNumber(46))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("1부터 45");
    }

    @Test
    void 유효한_로또_번호를_생성한다() {

        assertThatCode(() -> new LottoNumber(1))
                .doesNotThrowAnyException();

        assertThatCode(() -> new LottoNumber(45))
                .doesNotThrowAnyException();

        assertThatCode(() -> new LottoNumber(20))
                .doesNotThrowAnyException();
    }

    @Test
    void 같은_번호는_동등하다() {
        LottoNumber number1 = new LottoNumber(1);
        LottoNumber number2 = new LottoNumber(1);

        assertThat(number1).isEqualTo(number2);
    }

    @Test
    void 다른_번호는_동등하지_않다() {
        LottoNumber number1 = new LottoNumber(1);
        LottoNumber number2 = new LottoNumber(2);

        assertThat(number1).isNotEqualTo(number2);
    }

}
