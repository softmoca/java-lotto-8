package lotto.domain;

public class PurchaseAmount {

    private static final int LOTTO_PRICE = 1000;
    private static final int MINIMUM_AMOUNT = LOTTO_PRICE;

    private final int amount;

    private PurchaseAmount(int amount) {
        validate(amount);
        this.amount = amount;
    }

    public static PurchaseAmount from(int amount) {
        return new PurchaseAmount(amount);
    }

    private void validate(int amount) {
        validatePositive(amount);
        validateUnit(amount);
    }


    private void validatePositive(int amount) {
        if (amount < MINIMUM_AMOUNT) {
            throw new IllegalArgumentException(
                    "구입 금액은 " + MINIMUM_AMOUNT + "원 이상이어야 합니다."
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