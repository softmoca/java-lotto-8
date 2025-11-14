package lotto.domain;


import java.util.List;

public class WinningLotto {
    private final Lotto winningNumbers;
    private final LottoNumber bonusNumber;

    public WinningLotto(List<Integer> winningNumbers, int bonusNumber) {
        this.winningNumbers = new Lotto(winningNumbers);
        this.bonusNumber = new LottoNumber(bonusNumber);
        validateBonusNotDuplicate();
    }

    private void validateBonusNotDuplicate() {
        if (winningNumbers.containsBonus(bonusNumber.getNumber())) {
            throw new IllegalArgumentException(
                    "[ERROR] 보너스 번호는 당첨 번호와 중복될 수 없습니다."
            );
        }
    }

    public Rank match(Lotto lotto) {
        int matchCount = lotto.countMatches(winningNumbers.getNumbers());
        boolean hasBonus = lotto.containsBonus(bonusNumber.getNumber());
        return Rank.of(matchCount, hasBonus);
    }

}
