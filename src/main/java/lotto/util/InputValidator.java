package lotto.util;

public class InputValidator {
    private static final String DELIMITER = ",";
    private static final String EMPTY_INPUT_ERROR = "입력값이 비어있습니다.";
    private static final String NO_DELIMITER_ERROR = "쉼표(,)로 구분된 입력이 필요합니다.";
    private static final String CONSECUTIVE_DELIMITER_ERROR = "연속된 쉼표는 허용되지 않습니다.";
    private static final String START_WITH_DELIMITER_ERROR = "쉼표로 시작할 수 없습니다.";
    private static final String END_WITH_DELIMITER_ERROR = "쉼표로 끝날 수 없습니다.";

    public static void validateInput(String input) {
        validateNotEmpty(input);
        validateContainsDelimiter(input);
        validateNoConsecutiveDelimiters(input);
        validateNotStartWithDelimiter(input);
        validateNotEndWithDelimiter(input);
    }

    private static void validateNotEmpty(String input) {
        if (input == null || input.trim().isEmpty()) {
            throw new IllegalArgumentException(EMPTY_INPUT_ERROR);
        }
    }

    private static void validateContainsDelimiter(String input) {
        if (!input.contains(DELIMITER)) {
            throw new IllegalArgumentException(NO_DELIMITER_ERROR);
        }
    }

    private static void validateNoConsecutiveDelimiters(String input) {
        if (input.contains(DELIMITER + DELIMITER)) {
            throw new IllegalArgumentException(CONSECUTIVE_DELIMITER_ERROR);
        }
    }

    private static void validateNotStartWithDelimiter(String input) {
        if (input.startsWith(DELIMITER)) {
            throw new IllegalArgumentException(START_WITH_DELIMITER_ERROR);
        }
    }

    private static void validateNotEndWithDelimiter(String input) {
        if (input.endsWith(DELIMITER)) {
            throw new IllegalArgumentException(END_WITH_DELIMITER_ERROR);
        }
    }
}
