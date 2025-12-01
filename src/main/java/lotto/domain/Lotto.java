package lotto.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    public Lotto(String input) {
        this(validateParseNum(input));
    }

    private static List<Integer> validateParseNum(String input) {
        if (input.trim().isEmpty()) {
            throw new IllegalArgumentException("[ERROR] 당첨 입력 빈 ");
        }

        String[] numbers = input.split(",");

        List<Integer> temp = new ArrayList<>();

        for (String number : numbers) {
            try {
                temp.add(Integer.parseInt(number));
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("[ERROR] 당첨 입력 숫자 ");
            }
        }
        return temp;

    }


    private void validate(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 6개여야 합니다.");
        }

        for (int num : numbers) {
            if (1 <= num && num <= 45) {
                continue;
            }
            throw new IllegalArgumentException("[ERROR] 번호는 1-45사이 ");
        }

        Set<Integer> set = new HashSet<>(numbers);

        if (set.size() != 6) {
            throw new IllegalArgumentException("[ERROR] 번호 중복 ");
        }


    }

    public List<Integer> getNumbers() {
        return numbers;
    }

// TODO: 추가 기능 구현
}
