package ISO2.Exe2.model;

/**
 * Value object that encapsulates travel preferences and behavior.
 * Simple data holder without business logic (logic is in FareService).
 */
public class TravelProfile {
    private final int flightsPerYear;
    private final CabinClass preferredClass;
    private final DestinationRegion preferredRegion;
    private final boolean travelsWithChildren;

    public TravelProfile(int flightsPerYear, CabinClass preferredClass, 
                        DestinationRegion preferredRegion, boolean travelsWithChildren) {
        this.flightsPerYear = flightsPerYear;
        this.preferredClass = preferredClass;
        this.preferredRegion = preferredRegion;
        this.travelsWithChildren = travelsWithChildren;
    }

    public int getFlightsPerYear() {
        return flightsPerYear;
    }

    public CabinClass getPreferredClass() {
        return preferredClass;
    }

    public DestinationRegion getPreferredRegion() {
        return preferredRegion;
    }

    public boolean travelsWithChildren() {
        return travelsWithChildren;
    }
}
