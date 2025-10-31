package lotto.domain;


public class LottoResult {
    private final WinningStatistics statistics;
    private final PurchaseAmount purchaseAmount;

    public LottoResult(WinningStatistics statistics, PurchaseAmount purchaseAmount) {
        this.statistics = statistics;
        this.purchaseAmount = purchaseAmount;
    }


    public ProfitRate calculateProfitRate() {
        long totalPrize = statistics.calculateTotalPrize();
        return ProfitRate.of(totalPrize, purchaseAmount);
    }


    public WinningStatistics getStatistics() {
        return statistics;
    }

    public PurchaseAmount getPurchaseAmount() {
        return purchaseAmount;
    }
}