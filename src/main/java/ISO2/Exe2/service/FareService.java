package ISO2.Exe2.service;

import ISO2.Exe2.model.CabinClass;
import ISO2.Exe2.model.DestinationRegion;
import ISO2.Exe2.model.FareOffer;

/**
 * Fare Service divided into small, testable methods.
 * Each rule is in its own method with only necessary parameters.
 */
public class FareService {

    /**
     * Default constructor for FareService.
     */
    public FareService() {
        // Explicit constructor for clarity
    }

    /**
     * Determines the most suitable fare offer for a customer based on their profile.
     * Delegates to specific rule methods for better testability.
     * 
     * @param age Customer age in years
     * @param flightsPerYear Number of flights taken per year
     * @param isStudent Whether customer is a university student
     * @param livesWithParents Whether customer lives with parents
     * @param income Annual income in euros
     * @param preferredClass Preferred cabin class (ECONOMY or BUSINESS)
     * @param preferredRegion Preferred travel region
     * @param travelsWithChildren Whether customer travels with children under 12
     * @return FareOffer if eligible, null otherwise
     */
    public FareOffer determineFare(final int age, final int flightsPerYear, final boolean isStudent,
                                   final boolean livesWithParents, final double income,
                                   final CabinClass preferredClass, final DestinationRegion preferredRegion,
                                   final boolean travelsWithChildren) {
        
        // Check rules in order of priority
        FareOffer offer;
        
        offer = checkPajarilloFare(age, flightsPerYear);
        if (offer != null) {
            return offer;
        }
        
        offer = checkGorrionFare(age, flightsPerYear, isStudent, preferredClass);
        if (offer != null) {
            return offer;
        }
        
        offer = checkYoungTravelerFares(age, flightsPerYear, isStudent, livesWithParents, preferredClass);
        if (offer != null) {
            return offer;
        }
        
        offer = checkDiscoverEuropeFares(age, income, flightsPerYear, preferredClass, preferredRegion, travelsWithChildren);
        if (offer != null) {
            return offer;
        }
        
        offer = checkDiscoverWorldFares(age, income, flightsPerYear, preferredClass, preferredRegion, travelsWithChildren);
        if (offer != null) {
            return offer;
        }
        
        return null;
    }

    /**
     * Rule 1: Pajarillo Fare
     * For minors who travel frequently.
     * 
     * @param age Customer age
     * @param flightsPerYear Number of flights per year
     * @return Pajarillo offer or null
     */
    public FareOffer checkPajarilloFare(final int age, final int flightsPerYear) {
        if (isMinor(age) && isFrequentFlyer(flightsPerYear)) {
            return new FareOffer("Pajarillo", 10.0);
        }
        return null;
    }

    /**
     * Rule 2: Gorrión Fare
     * For young students traveling in economy class monthly.
     * 
     * @param age Customer age
     * @param flightsPerYear Number of flights per year
     * @param isStudent Whether customer is a student
     * @param preferredClass Preferred cabin class
     * @return Gorrión offer or null
     */
    public FareOffer checkGorrionFare(final int age, final int flightsPerYear, final boolean isStudent, final CabinClass preferredClass) {
        if (isYoungAdult(age) && isStudent && isEconomyClass(preferredClass) && isVeryFrequentFlyer(flightsPerYear)) {
            return new FareOffer("Gorrión", 15.0);
        }
        return null;
    }

    /**
     * Rules 3 & 4: Young Traveler Fares
     * For young workers traveling in economy class.
     * 
     * @param age Customer age
     * @param flightsPerYear Number of flights per year
     * @param isStudent Whether customer is a student
     * @param livesWithParents Whether customer lives with parents
     * @param preferredClass Preferred cabin class
     * @return Young traveler offer or null
     */
    public FareOffer checkYoungTravelerFares(final int age, final int flightsPerYear,
            final boolean isStudent, final boolean livesWithParents,
            final CabinClass preferredClass) {
        if (isYoungAdult(age) && !isStudent && isRegularFlyer(flightsPerYear) && isEconomyClass(preferredClass)) {
            if (livesWithParents) {
                return new FareOffer("Travel While You Can", 5.0);
            } else {
                return new FareOffer("Daring to Leave the Nest", 25.0);
            }
        }
        return null;
    }

    /**
     * Rules 5 & 6: Discover Europe Fares
     * For mature adults with middle income traveling to Europe in economy.
     * 
     * @param age Customer age
     * @param income Annual income
     * @param flightsPerYear Number of flights per year
     * @param preferredClass Preferred cabin class
     * @param preferredRegion Preferred travel region
     * @param travelsWithChildren Whether customer travels with children
     * @return Discover Europe offer or null
     */
    public FareOffer checkDiscoverEuropeFares(final int age, final double income,
            final int flightsPerYear, final CabinClass preferredClass,
            final DestinationRegion preferredRegion, final boolean travelsWithChildren) {
        if (isMatureAdult(age) && hasMiddleIncome(income) && isFrequentFlyer(flightsPerYear) 
            && isEconomyClass(preferredClass) && isEuropeDestination(preferredRegion)) {
            
            if (travelsWithChildren) {
                return new FareOffer("Discover Europe with Your Little Ones", 10.0);
            } else {
                return new FareOffer("Discover Europe", 15.0);
            }
        }
        return null;
    }

    /**
     * Rules 7 & 8: Discover the World Fares
     * For mature adults with high income traveling to Asia/America in business.
     * 
     * @param age Customer age
     * @param income Annual income
     * @param flightsPerYear Number of flights per year
     * @param preferredClass Preferred cabin class
     * @param preferredRegion Preferred travel region
     * @param travelsWithChildren Whether customer travels with children
     * @return Discover World offer or null
     */
    public FareOffer checkDiscoverWorldFares(final int age, final double income,
            final int flightsPerYear, final CabinClass preferredClass,
            final DestinationRegion preferredRegion, final boolean travelsWithChildren) {
        if (isMatureAdult(age) && hasHighIncome(income) && isFrequentFlyer(flightsPerYear) 
            && isBusinessClass(preferredClass) && isLongHaulDestination(preferredRegion)) {
            
            if (travelsWithChildren) {
                return new FareOffer("Discover the World with Your Little Ones", 10.0);
            } else {
                return new FareOffer("Discover the World", 20.0);
            }
        }
        return null;
    }

    // ========================================================================
    // HELPER METHODS - Small, testable predicates
    // ========================================================================

    /**
     * Checks if customer is a minor (age < 18).
     */
    public boolean isMinor(final int age) {
        return age < 18;
    }

    /**
     * Checks if customer is a young adult (18-25 years old).
     */
    public boolean isYoungAdult(final int age) {
        return age >= 18 && age <= 25;
    }

    /**
     * Checks if customer is a mature adult (age > 25).
     */
    public boolean isMatureAdult(final int age) {
        return age > 25;
    }

    /**
     * Checks if customer is a regular flyer (>= 3 flights/year).
     */
    public boolean isRegularFlyer(final int flightsPerYear) {
        return flightsPerYear >= 3;
    }

    /**
     * Checks if customer is a frequent flyer (>= 6 flights/year).
     */
    public boolean isFrequentFlyer(final int flightsPerYear) {
        return flightsPerYear >= 6;
    }

    /**
     * Checks if customer is a very frequent flyer (>= 12 flights/year).
     */
    public boolean isVeryFrequentFlyer(final int flightsPerYear) {
        return flightsPerYear >= 12;
    }

    /**
     * Checks if income is in middle range (20000 < income < 35000).
     */
    public boolean hasMiddleIncome(final double income) {
        return income > 20000 && income < 35000;
    }

    /**
     * Checks if income is high (>= 35000).
     */
    public boolean hasHighIncome(final double income) {
        return income >= 35000;
    }

    /**
     * Checks if preferred class is economy.
     */
    public boolean isEconomyClass(final CabinClass preferredClass) {
        return preferredClass == CabinClass.ECONOMY;
    }

    /**
     * Checks if preferred class is business.
     */
    public boolean isBusinessClass(final CabinClass preferredClass) {
        return preferredClass == CabinClass.BUSINESS;
    }

    /**
     * Checks if destination is Europe.
     */
    public boolean isEuropeDestination(final DestinationRegion region) {
        return region == DestinationRegion.EUROPE;
    }

    /**
     * Checks if destination is long-haul (Asia or America).
     */
    public boolean isLongHaulDestination(final DestinationRegion region) {
        return region == DestinationRegion.ASIA || region == DestinationRegion.AMERICA;
    }
}
