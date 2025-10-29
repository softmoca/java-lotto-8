package lotto.util;

public class InputValidator {

    private static final int LOTTO_PRICE = 1000;

    public static int validatePurchaseAmount(String input) {
        int amount = validateAndParseInteger(input);

        if (amount <= 0) {
            throw new IllegalArgumentException("구입 금액은 0보다 커야 합니다.");
        }

        if (amount % LOTTO_PRICE != 0) {
            throw new IllegalArgumentException("구입 금액은 1,000원 단위여야 합니다.");
        }

        return amount;
    }


    private static int validateAndParseInteger(String input) {
        try {
            return Integer.parseInt(input.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("구입 금액은 숫자여야 합니다.");
        }
    }


}
