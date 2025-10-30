package lotto.util;

import static lotto.exception.ErrorMessage.CONSECUTIVE_DELIMITER;
import static lotto.exception.ErrorMessage.EMPTY_INPUT;
import static lotto.exception.ErrorMessage.ENDS_WITH_DELIMITER;
import static lotto.exception.ErrorMessage.NO_DELIMITER;
import static lotto.exception.ErrorMessage.STARTS_WITH_DELIMITER;

public class InputValidator {
    private static final String DELIMITER = ",";

    public static void validateInput(String input) {
        validateNotEmpty(input);
        validateContainsDelimiter(input);
        validateNoConsecutiveDelimiters(input);
        validateNotStartWithDelimiter(input);
        validateNotEndWithDelimiter(input);
    }

    private static void validateNotEmpty(String input) {
        if (input == null || input.trim().isEmpty()) {
            throw new IllegalArgumentException(EMPTY_INPUT.getMessage());
        }
    }

    private static void validateContainsDelimiter(String input) {
        if (!input.contains(DELIMITER)) {
            throw new IllegalArgumentException(NO_DELIMITER.getMessage());
        }
    }

    private static void validateNoConsecutiveDelimiters(String input) {
        if (input.contains(DELIMITER + DELIMITER)) {
            throw new IllegalArgumentException(CONSECUTIVE_DELIMITER.getMessage());
        }
    }

    private static void validateNotStartWithDelimiter(String input) {
        if (input.startsWith(DELIMITER)) {
            throw new IllegalArgumentException(STARTS_WITH_DELIMITER.getMessage());
        }
    }

    private static void validateNotEndWithDelimiter(String input) {
        if (input.endsWith(DELIMITER)) {
            throw new IllegalArgumentException(ENDS_WITH_DELIMITER.getMessage());
        }
    }
}
