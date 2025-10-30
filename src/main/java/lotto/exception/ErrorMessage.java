package lotto.exception;


public enum ErrorMessage {
    // 구입 금액 관련
    INVALID_PURCHASE_AMOUNT_FORMAT("구입 금액은 숫자여야 합니다."),
    INVALID_PURCHASE_AMOUNT_INSUFFICIENT("구입 금액은 1,000원 보다 커야 합니다."),
    INVALID_PURCHASE_AMOUNT_UNIT("구입 금액은 1,000원 단위여야 합니다."),
    INVALID_LOTTO_SIZE("로또 번호는 6개여야 합니다."),
    INVALID_LOTTO_NUMBER_FORMAT("로또 번호는 숫자여야 합니다."),
    INVALID_LOTTO_NUMBER_RANGE("로또 번호는 1부터 45 사이의 숫자여야 합니다."),
    INVALID_LOTTO_DUPLICATION("로또 번호는 중복될 수 없습니다.");


    private static final String ERROR_PREFIX = "[ERROR] ";
    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return ERROR_PREFIX + message;
    }


}

