package lotto.domain;

import lotto.exception.ErrorMessage;

public class PurchaseAmount {

    private static final int LOTTO_PRICE = 1000;
    private static final int MINIMUM_AMOUNT = LOTTO_PRICE;

    private final int amount;

    private PurchaseAmount(int amount) {
        validate(amount);
        this.amount = amount;
    }

    public static PurchaseAmount from(String input) {
        int amount = parseAmount(input);
        return new PurchaseAmount(amount);
    }

    private static int parseAmount(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("구입 금액은 숫자로 입력해야 합니다.");
        }
    }

    private void validate(int amount) {
        validatePositive(amount);
        validateUnit(amount);
    }


    private void validatePositive(int amount) {
        if (amount < MINIMUM_AMOUNT) {
            throw new IllegalArgumentException(
                    ErrorMessage.INVALID_PURCHASE_AMOUNT_POSITIVE.getMessage()
            );
        }
    }

    private void validateUnit(int amount) {
        if (amount % LOTTO_PRICE != 0) {
            throw new IllegalArgumentException(
                    "구입 금액은 " + LOTTO_PRICE + "원 단위여야 합니다."
            );
        }
    }

}