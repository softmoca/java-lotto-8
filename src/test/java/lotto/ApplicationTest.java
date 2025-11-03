package lotto;

import static camp.nextstep.edu.missionutils.test.Assertions.assertRandomUniqueNumbersInRangeTest;
import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;

import camp.nextstep.edu.missionutils.test.NsTest;
import java.util.List;
import org.junit.jupiter.api.Test;

class ApplicationTest extends NsTest {
    private static final String ERROR_MESSAGE = "[ERROR]";

    @Test
    void 기능_테스트() {
        assertRandomUniqueNumbersInRangeTest(
                () -> {
                    run("8000", "1,2,3,4,5,6", "7");
                    assertThat(output()).contains(
                            "8개를 구매했습니다.",
                            "[8, 21, 23, 41, 42, 43]",
                            "[3, 5, 11, 16, 32, 38]",
                            "[7, 11, 16, 35, 36, 44]",
                            "[1, 8, 11, 31, 41, 42]",
                            "[13, 14, 16, 38, 42, 45]",
                            "[7, 11, 30, 40, 42, 43]",
                            "[2, 13, 22, 32, 38, 45]",
                            "[1, 3, 5, 14, 22, 45]",
                            "3개 일치 (5,000원) - 1개",
                            "4개 일치 (50,000원) - 0개",
                            "5개 일치 (1,500,000원) - 0개",
                            "5개 일치, 보너스 볼 일치 (30,000,000원) - 0개",
                            "6개 일치 (2,000,000,000원) - 0개",
                            "총 수익률은 62.5%입니다."
                    );
                },
                List.of(8, 21, 23, 41, 42, 43),
                List.of(3, 5, 11, 16, 32, 38),
                List.of(7, 11, 16, 35, 36, 44),
                List.of(1, 8, 11, 31, 41, 42),
                List.of(13, 14, 16, 38, 42, 45),
                List.of(7, 11, 30, 40, 42, 43),
                List.of(2, 13, 22, 32, 38, 45),
                List.of(1, 3, 5, 14, 22, 45)
        );
    }

    @Test
    void 예외_테스트() {
        assertSimpleTest(() -> {
            runException("1000j");
            assertThat(output()).contains(ERROR_MESSAGE);
        });
    }

    @Test
    void 기능_테스트2() {
        assertRandomUniqueNumbersInRangeTest(
                () -> {
                    run("8000", "2,7,16,28,39,44", "18");
                    assertThat(output()).contains(
                            "8개를 구매했습니다.",
                            "[13, 16, 23, 29, 39, 44]",
                            "[2, 16, 18, 21, 28, 44]",
                            "[2, 5, 10, 15, 17, 27]",
                            "[5, 18, 22, 25, 35, 38]",
                            "[2, 7, 15, 16, 18, 22]",
                            "[15, 24, 26, 33, 39, 45]",
                            "[16, 19, 24, 27, 29, 33]",
                            "[3, 7, 10, 28, 37, 39]",
                            "3개 일치 (5,000원) - 3개",
                            "4개 일치 (50,000원) - 1개",
                            "5개 일치 (1,500,000원) - 0개",
                            "5개 일치, 보너스 볼 일치 (30,000,000원) - 0개",
                            "6개 일치 (2,000,000,000원) - 0개",
                            "총 수익률은 812.5%입니다."
                    );
                },
                List.of(13, 16, 23, 29, 39, 44),
                List.of(2, 16, 18, 21, 28, 44),
                List.of(2, 5, 10, 15, 17, 27),
                List.of(5, 18, 22, 25, 35, 38),
                List.of(2, 7, 15, 16, 18, 22),
                List.of(15, 24, 26, 33, 39, 45),
                List.of(16, 19, 24, 27, 29, 33),
                List.of(3, 7, 10, 28, 37, 39)
        );
    }

    @Test
    void 기능_테스트_모두_낙첨() {
        assertRandomUniqueNumbersInRangeTest(
                () -> {
                    run("10000", "1,2,3,4,5,6", "7");
                    assertThat(output()).contains(
                            "10개를 구매했습니다.",
                            "[7, 13, 14, 27, 38, 41]",
                            "[1, 4, 7, 19, 20, 34]",
                            "[13, 15, 25, 27, 42, 45]",
                            "[5, 11, 14, 18, 24, 28]",
                            "[3, 15, 16, 24, 32, 36]",
                            "[5, 13, 17, 19, 32, 44]",
                            "[12, 20, 26, 31, 32, 39]",
                            "[14, 17, 30, 39, 41, 43]",
                            "[5, 12, 13, 34, 35, 42]",
                            "[1, 8, 13, 26, 38, 45]",
                            "3개 일치 (5,000원) - 0개",
                            "4개 일치 (50,000원) - 0개",
                            "5개 일치 (1,500,000원) - 0개",
                            "5개 일치, 보너스 볼 일치 (30,000,000원) - 0개",
                            "6개 일치 (2,000,000,000원) - 0개",
                            "총 수익률은 0.0%입니다."
                    );
                },
                List.of(7, 13, 14, 27, 38, 41),
                List.of(1, 4, 7, 19, 20, 34),
                List.of(13, 15, 25, 27, 42, 45),
                List.of(5, 11, 14, 18, 24, 28),
                List.of(3, 15, 16, 24, 32, 36),
                List.of(5, 13, 17, 19, 32, 44),
                List.of(12, 20, 26, 31, 32, 39),
                List.of(14, 17, 30, 39, 41, 43),
                List.of(5, 12, 13, 34, 35, 42),
                List.of(1, 8, 13, 26, 38, 45)
        );
    }


    @Override
    public void runMain() {
        Application.main(new String[]{});
    }
}
