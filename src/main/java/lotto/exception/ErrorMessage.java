package lotto.exception;


public enum ErrorMessage {
    // 구입 금액 관련
    INVALID_PURCHASE_AMOUNT_FORMAT("구입 금액은 숫자여야 합니다."),
    INVALID_PURCHASE_AMOUNT_INSUFFICIENT("구입 금액은 1,000원 보다 커야 합니다."),
    INVALID_PURCHASE_AMOUNT_UNIT("구입 금액은 1,000원 단위여야 합니다.");


    private static final String ERROR_PREFIX = "[ERROR] ";
    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return ERROR_PREFIX + message;
    }


}

