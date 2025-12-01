package lotto.domain;

public class Money {
    private final int value;

    public Money(String input) {
        validateInput(input);
        this.value = Integer.parseInt(input);
    }

    private void validateInput(String input) {
        if (input.trim().isEmpty()) {
            throw new IllegalArgumentException("[ERROR] 구매입력 빈");
        }

        validatePostiveNum(input);
        int num = Integer.parseInt(input);

        if (num % 1000 != 0) {
            throw new IllegalArgumentException("[ERROR] 구매입력 1000단위 아님 ");
        }
    }

    private void validatePostiveNum(String input) {
        try {
            int num = Integer.parseInt(input);

            if (num <= 0) {
                throw new IllegalArgumentException("[ERROR] 구매입력 음수 ");
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 구매입력 숫자가 아님");
        }
    }

    public int getTiketCount() {
        return value / 1000;
    }


}
