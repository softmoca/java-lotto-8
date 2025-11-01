package lotto.domain.moeny;

public class ProfitRate {
    private static final int PERCENTAGE_MULTIPLIER = 100;
    private static final double ROUNDING_MULTIPLIER = 10.0;

    private final double value;

    private ProfitRate(double value) {
        this.value = value;
    }

    public static ProfitRate of(long totalPrize, PurchaseAmount purchaseAmount) {
        double rate = calculateRate(totalPrize, purchaseAmount.getAmount());
        double roundedRate = roundToFirstDecimal(rate);
        return new ProfitRate(roundedRate);
    }

    private static double calculateRate(long totalPrize, int purchaseAmount) {
        return (double) totalPrize / purchaseAmount * PERCENTAGE_MULTIPLIER;
    }

    private static double roundToFirstDecimal(double rate) {
        return Math.round(rate * ROUNDING_MULTIPLIER) / ROUNDING_MULTIPLIER;
    }

    public double getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.format("%.1f%%", value);
    }
}
