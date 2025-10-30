package lotto.domain;

import static lotto.exception.ErrorMessage.INVALID_PURCHASE_AMOUNT_FORMAT;
import static lotto.exception.ErrorMessage.INVALID_PURCHASE_AMOUNT_INSUFFICIENT;
import static lotto.exception.ErrorMessage.INVALID_PURCHASE_AMOUNT_UNIT;

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

    public int getLottoQuantity() {
        return amount / LOTTO_PRICE;
    }


    private static int parseAmount(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(INVALID_PURCHASE_AMOUNT_FORMAT.getMessage());
        }
    }

    private void validate(int amount) {
        validatePositive(amount);
        validateUnit(amount);
    }

    public int getAmount() {
        return amount;
    }

    private void validatePositive(int amount) {
        if (amount < MINIMUM_AMOUNT) {
            throw new IllegalArgumentException(INVALID_PURCHASE_AMOUNT_INSUFFICIENT.getMessage());
        }
    }

    private void validateUnit(int amount) {
        if (amount % LOTTO_PRICE != 0) {
            throw new IllegalArgumentException(INVALID_PURCHASE_AMOUNT_UNIT.getMessage());
        }
    }

}