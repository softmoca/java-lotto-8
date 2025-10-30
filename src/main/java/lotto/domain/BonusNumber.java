package lotto.domain;

import lotto.util.InputParser;

public class BonusNumber {
    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 45;

    private final int value;

    private BonusNumber(int value) {
        this.value = value;
    }

    public static BonusNumber of(String input, WinningNumbers winningNumbers) {
        int value = InputParser.parseToInteger(input);
        validateRange(value);
        validateDuplication(value, winningNumbers);
        return new BonusNumber(value);
    }

    private static void validateRange(int value) {
        if (value < MIN_NUMBER || value > MAX_NUMBER) {
            throw new IllegalArgumentException(
                    String.format("[ERROR] 보너스 번호는 %d부터 %d 사이의 숫자여야 합니다.",
                            MIN_NUMBER, MAX_NUMBER)
            );
        }
    }

    private static void validateDuplication(int value, WinningNumbers winningNumbers) {
        if (winningNumbers.hasNumber(value)) {
            throw new IllegalArgumentException(
                    "[ERROR] 보너스 번호는 당첨 번호와 중복될 수 없습니다."
            );
        }
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BonusNumber that = (BonusNumber) o;
        return value == that.value;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(value);
    }
}