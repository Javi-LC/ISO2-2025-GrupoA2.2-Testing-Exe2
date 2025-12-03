package ISO2.Exe2.model;

/**
 * Value object that encapsulates financial information about a customer.
 * Simple data holder without business logic (logic is in FareService).
 */
public class FinancialProfile {
    private final double income;

    public FinancialProfile(double income) {
        this.income = income;
    }

    public double getIncome() {
        return income;
    }
}
