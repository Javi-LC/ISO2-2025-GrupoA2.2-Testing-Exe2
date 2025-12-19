package ISO2.Exe2.model;

/**
 * Represents a fare offer with a name and discount percentage.
 */
public class FareOffer {
    /** The name of the fare offer. */
    private final String name;
    /** The discount percentage for this offer. */
    private final double discountPercentage;

    /**
     * Constructs a new FareOffer.
     *
     * @param name The name of the fare offer
     * @param discountPercentage The discount percentage
     */
    public FareOffer(final String name, final double discountPercentage) {
        this.name = name;
        this.discountPercentage = discountPercentage;
    }

    /**
     * Gets the name of the fare offer.
     *
     * @return The offer name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the discount percentage.
     *
     * @return The discount percentage
     */
    public double getDiscountPercentage() {
        return discountPercentage;
    }
}
