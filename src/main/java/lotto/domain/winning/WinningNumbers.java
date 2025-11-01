package lotto.domain.winning;


import lotto.domain.Lotto;

public class WinningNumbers {
    private final Lotto winningLotto;
    private final BonusNumber bonusNumber;

    public WinningNumbers(Lotto winningLotto, BonusNumber bonusNumber) {
        this.winningLotto = winningLotto;
        this.bonusNumber = bonusNumber;
    }

    public Rank match(Lotto purchasedLotto) {
        int matchCount = countMatches(purchasedLotto);
        boolean matchesBonus = purchasedLotto.contains(bonusNumber.getValue());
        return Rank.valueOf(matchCount, matchesBonus);
    }


    private int countMatches(Lotto purchasedLotto) {
        return (int) purchasedLotto.getNumbers().stream()
                .filter(winningLotto::contains)
                .count();
    }
}
