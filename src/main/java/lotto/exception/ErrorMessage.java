package lotto.exception;

public enum ErrorMessage {
    // 공통 숫자/형식
    INVALID_NUMBER_FORMAT("숫자 형식이 올바르지 않습니다."),

    // 구입 금액 관련
    INVALID_PURCHASE_AMOUNT_FORMAT("구입 금액은 숫자여야 합니다."),
    INVALID_PURCHASE_AMOUNT_INSUFFICIENT("구입 금액은 최소 1,000원 이상이어야 합니다."),
    INVALID_PURCHASE_AMOUNT_UNIT("구입 금액은 1,000원 단위여야 합니다."),

    // 로또 번호 관련
    INVALID_LOTTO_SIZE("로또 번호는 6개여야 합니다."),
    INVALID_LOTTO_NUMBER_FORMAT("로또 번호는 숫자여야 합니다."),
    INVALID_LOTTO_NUMBER_RANGE("로또 번호는 1부터 45 사이의 숫자여야 합니다."),
    INVALID_LOTTO_DUPLICATION("로또 번호는 중복될 수 없습니다."),

    // 입력 문자열 검증(InputValidator)
    EMPTY_INPUT("입력값이 비어있습니다."),
    NO_DELIMITER("쉼표(,)로 구분된 입력이 필요합니다."),
    CONSECUTIVE_DELIMITER("연속된 쉼표는 허용되지 않습니다."),
    STARTS_WITH_DELIMITER("쉼표로 시작할 수 없습니다."),
    ENDS_WITH_DELIMITER("쉼표로 끝날 수 없습니다.");

    private static final String ERROR_PREFIX = "[ERROR] ";
    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return ERROR_PREFIX + message;
    }
}
