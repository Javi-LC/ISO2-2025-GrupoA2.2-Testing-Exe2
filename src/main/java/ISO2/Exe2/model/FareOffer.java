package ISO2.Exe2.model;

/**
 * Represents a fare offer with a name and discount percentage.
 */
public class FareOffer {
    private final String name;
    private final double discountPercentage;

    /**
     * Creates a new FareOffer.
     * @param name the name of the fare offer
     * @param discountPercentage the discount percentage
     */
    public FareOffer(String name, double discountPercentage) {
        this.name = name;
        this.discountPercentage = discountPercentage;
    }

    /**
     * Gets the name of the fare offer.
     * @return the fare offer name
     */
    public String getName() { return name; }

    /**
     * Gets the discount percentage.
     * @return the discount percentage
     */
    public double getDiscountPercentage() { return discountPercentage; }
}
