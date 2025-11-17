package lotto.util;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import lotto.domain.Lotto;
import lotto.domain.LottoNumber;
import lotto.domain.LottoStatistics;
import lotto.domain.WinningLotto;
import lotto.dto.LottoResult;
import org.junit.jupiter.api.Test;

class LottoResultMapperTest {


    @Test
    void 통계로부터_결과를_생성한다() {
        // given
        List<Lotto> lottos = List.of(
                new Lotto(List.of(1, 2, 3, 4, 5, 6))
        );
        Lotto winningNumbers = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        WinningLotto winningLotto = WinningLotto.of(winningNumbers, new LottoNumber(7));
        LottoStatistics statistics = LottoStatistics.from(lottos, winningLotto);

        // when
        LottoResult result = LottoResultMapper.createResult(statistics, 1000);

        // then
        assertThat(result).isNotNull();
        assertThat(result.getRankStatistics()).hasSize(5);
        assertThat(result.getProfitRate()).isPositive();
    }
}
