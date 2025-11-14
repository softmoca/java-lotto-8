package lotto.domain;


public class WinningLotto {
    private final Lotto winningNumbers;
    private final LottoNumber bonusNumber;

    private WinningLotto(Lotto winningNumbers, LottoNumber bonusNumber) {
        this.winningNumbers = winningNumbers;
        this.bonusNumber = bonusNumber;
    }


    public static WinningLotto of(Lotto winningNumbers, LottoNumber bonusNumber) {
        validateBonusNotDuplicate(winningNumbers, bonusNumber);
        return new WinningLotto(winningNumbers, bonusNumber);
    }

    private static void validateBonusNotDuplicate(Lotto winningNumbers, LottoNumber bonusNumber) {
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
