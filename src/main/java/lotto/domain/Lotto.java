package lotto.domain;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Lotto {
    private final List<LottoNumber> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        validateDuplicate(numbers);
        this.numbers = convertToLottoNumbers(numbers);
    }

    private void validate(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 6개여야 합니다.");
        }
    }

    private void validateDuplicate(List<Integer> numbers) {
        long distinctCount = numbers.stream()
                .distinct()
                .count();

        if (distinctCount != numbers.size()) {
            throw new IllegalArgumentException(
                    "[ERROR] 로또 번호는 중복될 수 없습니다."
            );
        }
    }

    private List<LottoNumber> convertToLottoNumbers(List<Integer> numbers) {
        return numbers.stream()
                .map(LottoNumber::new)
                .sorted(Comparator.comparing(LottoNumber::getNumber))
                .toList();
    }

    public int countMatches(List<Integer> winningNumbers) {
        List<LottoNumber> winningLottoNumbers = winningNumbers.stream()
                .map(LottoNumber::new)
                .toList();

        return (int) numbers.stream()
                .filter(winningLottoNumbers::contains)
                .count();
    }

    public boolean containsBonus(int bonusNumber) {
        LottoNumber bonus = new LottoNumber(bonusNumber);
        return numbers.contains(bonus);
    }

    public List<Integer> getNumbers() {
        return numbers.stream()
                .map(LottoNumber::getNumber).collect(Collectors.toUnmodifiableList());
    }

}

