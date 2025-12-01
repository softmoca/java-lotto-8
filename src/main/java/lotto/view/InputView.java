package lotto.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {

    public static String readInputMoney() {
        System.out.println("구입금액을 입력해 주세요.");
        return Console.readLine();
    }

    public static String readInputWinnerLotto() {
        System.out.println();
        
        System.out.println("당첨 번호를 입력해 주세요");
        return Console.readLine();
    }

    public static int readInputBonusNum() {
        System.out.println("보너스 번호를 입력해 주세요");
        String input = Console.readLine();

        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 당첨번호 정수아님 ");

        }

    }

}
