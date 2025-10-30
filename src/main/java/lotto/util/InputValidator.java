package lotto.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class InputValidator {
    private static final int MIN_LOTTO_NUMBER = 1;
    private static final int MAX_LOTTO_NUMBER = 45;
    private static final int WINNING_NUMBER_COUNT = 6;
    private static final String NUMBER_DELIMITER = ",";


    public static List<Integer> validateWinningNumbers(String input) {
        String[] tokens = input.split(NUMBER_DELIMITER);

        if (tokens.length != WINNING_NUMBER_COUNT) {
            throw new IllegalArgumentException("당첨 번호는 6개여야 합니다.");
        }

        List<Integer> numbers = parseNumbers(tokens);
        validateNumberRange(numbers);
        validateDuplication(numbers);

        return numbers;
    }

    public static int validateBonusNumber(String input) {
        int bonusNumber = validateAndParseInteger(input);
        validateSingleNumberRange(bonusNumber);
        return bonusNumber;
    }

    private static int validateAndParseInteger(String input) {
        try {
            return Integer.parseInt(input.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("구입 금액은 숫자여야 합니다.");
        }
    }

    private static List<Integer> parseNumbers(String[] tokens) {
        List<Integer> numbers = new ArrayList<>();

        for (String token : tokens) {
            try {
                numbers.add(Integer.parseInt(token.trim()));
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("로또 번호는 숫자여야 합니다.");
            }
        }

        return numbers;
    }


    private static void validateNumberRange(List<Integer> numbers) {
        for (Integer number : numbers) {
            validateSingleNumberRange(number);
        }
    }

    private static void validateSingleNumberRange(int number) {
        if (number < MIN_LOTTO_NUMBER || number > MAX_LOTTO_NUMBER) {
            throw new IllegalArgumentException("로또 번호는 1부터 45 사이의 숫자여야 합니다.");
        }
    }

    private static void validateDuplication(List<Integer> numbers) {
        HashSet<Integer> uniqueNumbers = new HashSet<>(numbers);
        if (uniqueNumbers.size() != numbers.size()) {
            throw new IllegalArgumentException("당첨 번호는 중복될 수 없습니다.");
        }
    }


}
