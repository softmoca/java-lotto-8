package lotto.domain;

import static lotto.exception.ErrorMessage.BONUS_NUMBER_DUPLICATED;
import static lotto.exception.ErrorMessage.BONUS_NUMBER_OUT_OF_RANGE;

public class BonusNumber {
    private static final int MIN_LOTTO_NUMBER = 1;
    private static final int MAX_LOTTO_NUMBER = 45;

    private final int value;

    private BonusNumber(int value) {
        this.value = value;
    }

    public static BonusNumber of(int value, Lotto winningNumbers) {
        validateRange(value);
        validateDuplication(value, winningNumbers);
        return new BonusNumber(value);
    }

    private static void validateRange(int value) {
        if (value < MIN_LOTTO_NUMBER || value > MAX_LOTTO_NUMBER) {
            throw new IllegalArgumentException(
                    BONUS_NUMBER_OUT_OF_RANGE.getMessage()
            );
        }
    }

    private static void validateDuplication(int value, Lotto winningNumbers) {
        if (winningNumbers.hasNumber(value)) {
            throw new IllegalArgumentException(
                    BONUS_NUMBER_DUPLICATED.getMessage()
            );
        }
    }

    public int getValue() {
        return value;
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
