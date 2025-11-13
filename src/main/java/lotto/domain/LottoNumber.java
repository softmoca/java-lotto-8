package lotto.domain;

public class LottoNumber {

    public LottoNumber(int number) {
        if (number < 1 || number > 45) {
            throw new IllegalArgumentException(
                    "[ERROR] 로또 번호는 1부터 45 사이의 숫자여야 합니다."
            );
        }
    }
}

