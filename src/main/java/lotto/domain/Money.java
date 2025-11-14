package lotto.domain;

public class Money {
    private static final int LOTTO_PRICE = 1000;
    private static final int MIN_AMOUNT = 1;

    private final int amount;


    private Money(int amount) {
        validate(amount);
        this.amount = amount;
    }

    public static Money from(int amount) {
        return new Money(amount);
    }

    private void validate(int amount) {
        if (amount < MIN_AMOUNT) {
            throw new IllegalArgumentException(
                    "[ERROR] 구입 금액은 양수여야 합니다."
            );
        }

        if (amount % LOTTO_PRICE != 0) {
            throw new IllegalArgumentException(
                    "[ERROR] 구입 금액은 1,000원 단위여야 합니다."
            );
        }
    }


    public int getAmount() {
        return amount;
    }
}
