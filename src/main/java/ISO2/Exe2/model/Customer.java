package ISO2.Exe2.model;

/**
 * Simple customer data class.
 * Stores all customer information needed for fare calculation.
 */
public class Customer {
    private final int age;
    private final int flightsPerYear;
    private final boolean isStudent;
    private final boolean livesWithParents;
    private final double income;
    private final CabinClass preferredClass;
    private final DestinationRegion preferredRegion;
    private final boolean travelsWithChildren;

    public Customer(int age, int flightsPerYear, boolean isStudent, boolean livesWithParents,
                   double income, CabinClass preferredClass, DestinationRegion preferredRegion,
                   boolean travelsWithChildren) {
        this.age = age;
        this.flightsPerYear = flightsPerYear;
        this.isStudent = isStudent;
        this.livesWithParents = livesWithParents;
        this.income = income;
        this.preferredClass = preferredClass;
        this.preferredRegion = preferredRegion;
        this.travelsWithChildren = travelsWithChildren;
    }

    public int getAge() { return age; }
    public int getFlightsPerYear() { return flightsPerYear; }
    public boolean isStudent() { return isStudent; }
    public boolean isLivesWithParents() { return livesWithParents; }
    public double getIncome() { return income; }
    public CabinClass getPreferredClass() { return preferredClass; }
    public DestinationRegion getPreferredRegion() { return preferredRegion; }
    public boolean isTravelsWithChildren() { return travelsWithChildren; }
}
