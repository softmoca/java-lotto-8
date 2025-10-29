package lotto.view;

public class OutputView {
    private static final String ERROR_PREFIX = "[ERROR] ";

    public void printErrorMessage(String message) {
        if (message.startsWith(ERROR_PREFIX)) {
            System.out.println(message);
            return;
        }
        System.out.println(ERROR_PREFIX + message);
    }

    public void printPurchaseCount(int count) {
        System.out.println();
        System.out.println(count + "개를 구매했습니다.");
    }


}
