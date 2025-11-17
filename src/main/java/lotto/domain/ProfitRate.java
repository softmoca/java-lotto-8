package lotto.domain;

public class ProfitRate {
    private final double value;

    private ProfitRate(double value) {
        this.value = value;
    }

    public static ProfitRate calculate(int totalPrize, int purchaseAmount) {
        double rate = (double) totalPrize / purchaseAmount * 100;
        double rounded = Math.round(rate * 10) / 10.0;
        return new ProfitRate(rounded);
    }

    public double getValue() {
        return value;
    }
}
