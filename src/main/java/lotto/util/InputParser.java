package lotto.util;

import java.util.Arrays;
import java.util.List;

public class InputParser {

    private static final String DELIMITER = ",";

    public static List<String> parseToStringList(String input) {
        return Arrays.stream(input.split(DELIMITER))
                .map(String::trim)
                .toList();
    }


    public static int parseToInteger(String input) {
        try {
            return Integer.parseInt(input.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자 형식이 올바르지 않습니다.");
        }
    }

}