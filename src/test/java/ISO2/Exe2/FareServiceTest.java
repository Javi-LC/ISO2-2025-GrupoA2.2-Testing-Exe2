package ISO2.Exe2;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ISO2.Exe2.model.CabinClass;
import ISO2.Exe2.model.DestinationRegion;
import ISO2.Exe2.model.FareOffer;
import ISO2.Exe2.service.FareService;

/**
 * Unit tests for FareService using three coverage strategies:
 * 1. Each-Use Coverage (1-wise) - 17 test cases
 * 2. Decision Coverage - Tests for each decision branch
 * 3. MC/DC Coverage - Modified Condition/Decision Coverage
 */
public class FareServiceTest {

    private FareService fareService;

    @Before
    public void setUp() {
        fareService = new FareService();
    }

    // ========================================================================
    // SECTION 1: EACH-USE COVERAGE TESTS (17 test cases)
    // Based on Section 6 of the documentation
    // Each value from every variable is used at least once
    // ========================================================================

    // --- Each-Use Test 01 ---
    @Test
    public void testEachUse01_MinorFrequentFlyer() {
        // EU-01: Minor (age=17) with 6 flights -> Pajarillo
        FareOffer offer = fareService.determineFare(17, 6, false, false, 0, 
            CabinClass.ECONOMY, DestinationRegion.EUROPE, false);
        assertNotNull(offer);
        assertEquals("Pajarillo", offer.getName());
        assertEquals(10.0, offer.getDiscountPercentage(), 0.01);
    }

    @Test
    public void testEachUse02_YoungStudentFrequent() {
        // EU-02: Young adult student (age=18) with 12 flights in economy -> Gorrión
        FareOffer offer = fareService.determineFare(18, 12, true, false, 15000, 
            CabinClass.ECONOMY, DestinationRegion.EUROPE, false);
        assertNotNull(offer);
        assertEquals("Gorrión", offer.getName());
        assertEquals(15.0, offer.getDiscountPercentage(), 0.01);
    }

    @Test
    public void testEachUse03_YoungWorkerWithParents() {
        // EU-03: Young worker (age=19) living with parents, 3 flights economy -> Travel While You Can
        FareOffer offer = fareService.determineFare(19, 3, false, true, 18000, 
            CabinClass.ECONOMY, DestinationRegion.AMERICA, false);
        assertNotNull(offer);
        assertEquals("Travel While You Can", offer.getName());
        assertEquals(5.0, offer.getDiscountPercentage(), 0.01);
    }

    @Test
    public void testEachUse04_YoungWorkerIndependent() {
        // EU-04: Young worker (age=24) not living with parents, 4 flights economy -> Daring to Leave the Nest
        FareOffer offer = fareService.determineFare(24, 4, false, false, 22000, 
            CabinClass.ECONOMY, DestinationRegion.ASIA, false);
        assertNotNull(offer);
        assertEquals("Daring to Leave the Nest", offer.getName());
        assertEquals(25.0, offer.getDiscountPercentage(), 0.01);
    }

    @Test
    public void testEachUse05_BoundaryAge25WithParents() {
        // EU-05: Adult (age=25) boundary - young worker with parents -> Travel While You Can
        FareOffer offer = fareService.determineFare(25, 5, false, true, 19999, 
            CabinClass.ECONOMY, DestinationRegion.OTHER, false);
        assertNotNull(offer);
        assertEquals("Travel While You Can", offer.getName());
        assertEquals(5.0, offer.getDiscountPercentage(), 0.01);
    }

    @Test
    public void testEachUse06_MatureAdultEurope() {
        // EU-06: Mature adult (age=26) middle income, 6 flights economy Europe -> Discover Europe
        FareOffer offer = fareService.determineFare(26, 6, false, false, 25000, 
            CabinClass.ECONOMY, DestinationRegion.EUROPE, false);
        assertNotNull(offer);
        assertEquals("Discover Europe", offer.getName());
        assertEquals(15.0, offer.getDiscountPercentage(), 0.01);
    }

    @Test
    public void testEachUse07_MatureAdultEuropeWithChildren() {
        // EU-07: Mature adult (age=30) middle income, 7 flights Europe with children -> Discover Europe with Your Little Ones
        FareOffer offer = fareService.determineFare(30, 7, false, false, 30000, 
            CabinClass.ECONOMY, DestinationRegion.EUROPE, true);
        assertNotNull(offer);
        assertEquals("Discover Europe with Your Little Ones", offer.getName());
        assertEquals(10.0, offer.getDiscountPercentage(), 0.01);
    }

    @Test
    public void testEachUse08_MatureAdultWorldAsia() {
        // EU-08: Mature adult (age=35) high income, 11 flights business Asia -> Discover the World
        FareOffer offer = fareService.determineFare(35, 11, false, false, 40000, 
            CabinClass.BUSINESS, DestinationRegion.ASIA, false);
        assertNotNull(offer);
        assertEquals("Discover the World", offer.getName());
        assertEquals(20.0, offer.getDiscountPercentage(), 0.01);
    }

    @Test
    public void testEachUse09_MatureAdultWorldAmerica() {
        // EU-09: Mature adult (age=40) high income, 12 flights business America -> Discover the World
        FareOffer offer = fareService.determineFare(40, 12, false, false, 50000, 
            CabinClass.BUSINESS, DestinationRegion.AMERICA, false);
        assertNotNull(offer);
        assertEquals("Discover the World", offer.getName());
        assertEquals(20.0, offer.getDiscountPercentage(), 0.01);
    }

    @Test
    public void testEachUse10_MatureAdultWorldWithChildren() {
        // EU-10: Mature adult (age=45) high income, 13 flights business Asia with children -> Discover the World with Your Little Ones
        FareOffer offer = fareService.determineFare(45, 13, false, false, 60000, 
            CabinClass.BUSINESS, DestinationRegion.ASIA, true);
        assertNotNull(offer);
        assertEquals("Discover the World with Your Little Ones", offer.getName());
        assertEquals(10.0, offer.getDiscountPercentage(), 0.01);
    }

    @Test
    public void testEachUse11_MinorInsufficientFlights() {
        // EU-11: Minor (age=0) with insufficient flights -> null
        FareOffer offer = fareService.determineFare(0, 5, false, false, 0, 
            CabinClass.ECONOMY, DestinationRegion.EUROPE, false);
        assertNull(offer);
    }

    @Test
    public void testEachUse12_MinorMinimalFlights() {
        // EU-12: Minor (age=1) with 1 flight -> null
        FareOffer offer = fareService.determineFare(1, 1, false, false, 1, 
            CabinClass.BUSINESS, DestinationRegion.OTHER, false);
        assertNull(offer);
    }

    @Test
    public void testEachUse13_NegativeAge() {
        // EU-13: Negative age (-1) -> null (invalid input)
        FareOffer offer = fareService.determineFare(-1, 2, true, true, 20000, 
            CabinClass.ECONOMY, DestinationRegion.AMERICA, true);
        assertNull(offer);
    }

    @Test
    public void testEachUse14_MatureAdultLowIncome() {
        // EU-14: Mature adult with low income, 0 flights -> null
        FareOffer offer = fareService.determineFare(50, 0, false, false, 15000, 
            CabinClass.ECONOMY, DestinationRegion.EUROPE, false);
        assertNull(offer);
    }

    @Test
    public void testEachUse15_StudentBusinessClass() {
        // EU-15: Young adult student in business class -> null
        FareOffer offer = fareService.determineFare(20, 12, true, false, 10000, 
            CabinClass.BUSINESS, DestinationRegion.EUROPE, false);
        assertNull(offer);
    }

    @Test
    public void testEachUse16_HighIncomeEconomyClass() {
        // EU-16: Mature adult high income but economy class -> null for Discover World
        FareOffer offer = fareService.determineFare(30, 6, false, false, 45000, 
            CabinClass.ECONOMY, DestinationRegion.ASIA, false);
        assertNull(offer);
    }

    @Test
    public void testEachUse17_MiddleIncomeBusinessClass() {
        // EU-17: Mature adult middle income but business class -> null for Discover Europe
        FareOffer offer = fareService.determineFare(30, 6, false, false, 25000, 
            CabinClass.BUSINESS, DestinationRegion.EUROPE, false);
        assertNull(offer);
    }

    // ========================================================================
    // SECTION 2: DECISION COVERAGE TESTS
    // Based on Section 8 of the documentation
    // Each decision must evaluate to both TRUE and FALSE
    // ========================================================================

    // --- Decision 1: checkPajarilloFare - isMinor(age) && isFrequentFlyer(flightsPerYear) ---

    @Test
    public void testDecision1_Pajarillo_True() {
        // DC1-T: Decision TRUE - age=17, flights=6 -> Pajarillo
        FareOffer offer = fareService.checkPajarilloFare(17, 6);
        assertNotNull(offer);
        assertEquals("Pajarillo", offer.getName());
        assertEquals(10.0, offer.getDiscountPercentage(), 0.01);
    }

    @Test
    public void testDecision1_Pajarillo_False_NotMinor() {
        // DC1-F1: Decision FALSE - age=18 (not minor), flights=6
        FareOffer offer = fareService.checkPajarilloFare(18, 6);
        assertNull(offer);
    }

    @Test
    public void testDecision1_Pajarillo_False_NotFrequent() {
        // DC1-F2: Decision FALSE - age=17 (minor), flights=5 (not frequent)
        FareOffer offer = fareService.checkPajarilloFare(17, 5);
        assertNull(offer);
    }

    // --- Decision 2: checkGorrionFare - isYoungAdult && isStudent && isEconomyClass && isVeryFrequentFlyer ---

    @Test
    public void testDecision2_Gorrion_True() {
        // DC2-T: Decision TRUE - age=20, student, economy, 12 flights -> Gorrión
        FareOffer offer = fareService.checkGorrionFare(20, 12, true, CabinClass.ECONOMY);
        assertNotNull(offer);
        assertEquals("Gorrión", offer.getName());
        assertEquals(15.0, offer.getDiscountPercentage(), 0.01);
    }

    @Test
    public void testDecision2_Gorrion_False_NotYoungAdult() {
        // DC2-F1: Decision FALSE - age=17 (not young adult)
        FareOffer offer = fareService.checkGorrionFare(17, 12, true, CabinClass.ECONOMY);
        assertNull(offer);
    }

    @Test
    public void testDecision2_Gorrion_False_NotStudent() {
        // DC2-F2: Decision FALSE - not student
        FareOffer offer = fareService.checkGorrionFare(20, 12, false, CabinClass.ECONOMY);
        assertNull(offer);
    }

    @Test
    public void testDecision2_Gorrion_False_BusinessClass() {
        // DC2-F3: Decision FALSE - business class
        FareOffer offer = fareService.checkGorrionFare(20, 12, true, CabinClass.BUSINESS);
        assertNull(offer);
    }

    @Test
    public void testDecision2_Gorrion_False_NotVeryFrequent() {
        // DC2-F4: Decision FALSE - 11 flights (not very frequent)
        FareOffer offer = fareService.checkGorrionFare(20, 11, true, CabinClass.ECONOMY);
        assertNull(offer);
    }

    // --- Decision 3: checkYoungTravelerFares - isYoungAdult && !isStudent && isRegularFlyer && isEconomyClass ---

    @Test
    public void testDecision3_YoungTraveler_True_WithParents() {
        // DC3-T1: Decision TRUE, livesWithParents=true -> Travel While You Can
        FareOffer offer = fareService.checkYoungTravelerFares(22, 3, false, true, CabinClass.ECONOMY);
        assertNotNull(offer);
        assertEquals("Travel While You Can", offer.getName());
        assertEquals(5.0, offer.getDiscountPercentage(), 0.01);
    }

    @Test
    public void testDecision3_YoungTraveler_True_Independent() {
        // DC3-T2: Decision TRUE, livesWithParents=false -> Daring to Leave the Nest
        FareOffer offer = fareService.checkYoungTravelerFares(22, 3, false, false, CabinClass.ECONOMY);
        assertNotNull(offer);
        assertEquals("Daring to Leave the Nest", offer.getName());
        assertEquals(25.0, offer.getDiscountPercentage(), 0.01);
    }

    @Test
    public void testDecision3_YoungTraveler_False_NotYoungAdult() {
        // DC3-F1: Decision FALSE - age=17 (not young adult)
        FareOffer offer = fareService.checkYoungTravelerFares(17, 3, false, true, CabinClass.ECONOMY);
        assertNull(offer);
    }

    @Test
    public void testDecision3_YoungTraveler_False_IsStudent() {
        // DC3-F2: Decision FALSE - is student
        FareOffer offer = fareService.checkYoungTravelerFares(22, 3, true, true, CabinClass.ECONOMY);
        assertNull(offer);
    }

    @Test
    public void testDecision3_YoungTraveler_False_NotRegular() {
        // DC3-F3: Decision FALSE - 2 flights (not regular)
        FareOffer offer = fareService.checkYoungTravelerFares(22, 2, false, true, CabinClass.ECONOMY);
        assertNull(offer);
    }

    @Test
    public void testDecision3_YoungTraveler_False_BusinessClass() {
        // DC3-F4: Decision FALSE - business class
        FareOffer offer = fareService.checkYoungTravelerFares(22, 3, false, true, CabinClass.BUSINESS);
        assertNull(offer);
    }

    // --- Decision 4: checkDiscoverEuropeFares ---

    @Test
    public void testDecision4_DiscoverEurope_True_NoChildren() {
        // DC4-T1: Decision TRUE, no children -> Discover Europe
        FareOffer offer = fareService.checkDiscoverEuropeFares(30, 25000, 6, 
            CabinClass.ECONOMY, DestinationRegion.EUROPE, false);
        assertNotNull(offer);
        assertEquals("Discover Europe", offer.getName());
        assertEquals(15.0, offer.getDiscountPercentage(), 0.01);
    }

    @Test
    public void testDecision4_DiscoverEurope_True_WithChildren() {
        // DC4-T2: Decision TRUE, with children -> Discover Europe with Your Little Ones
        FareOffer offer = fareService.checkDiscoverEuropeFares(30, 25000, 6, 
            CabinClass.ECONOMY, DestinationRegion.EUROPE, true);
        assertNotNull(offer);
        assertEquals("Discover Europe with Your Little Ones", offer.getName());
        assertEquals(10.0, offer.getDiscountPercentage(), 0.01);
    }

    @Test
    public void testDecision4_DiscoverEurope_False_NotMatureAdult() {
        // DC4-F1: Decision FALSE - age=25 (not mature adult)
        FareOffer offer = fareService.checkDiscoverEuropeFares(25, 25000, 6, 
            CabinClass.ECONOMY, DestinationRegion.EUROPE, false);
        assertNull(offer);
    }

    @Test
    public void testDecision4_DiscoverEurope_False_LowIncome() {
        // DC4-F2: Decision FALSE - income=20000 (not middle income)
        FareOffer offer = fareService.checkDiscoverEuropeFares(30, 20000, 6, 
            CabinClass.ECONOMY, DestinationRegion.EUROPE, false);
        assertNull(offer);
    }

    @Test
    public void testDecision4_DiscoverEurope_False_HighIncome() {
        // DC4-F3: Decision FALSE - income=35000 (high income)
        FareOffer offer = fareService.checkDiscoverEuropeFares(30, 35000, 6, 
            CabinClass.ECONOMY, DestinationRegion.EUROPE, false);
        assertNull(offer);
    }

    @Test
    public void testDecision4_DiscoverEurope_False_NotFrequent() {
        // DC4-F4: Decision FALSE - 5 flights (not frequent)
        FareOffer offer = fareService.checkDiscoverEuropeFares(30, 25000, 5, 
            CabinClass.ECONOMY, DestinationRegion.EUROPE, false);
        assertNull(offer);
    }

    @Test
    public void testDecision4_DiscoverEurope_False_BusinessClass() {
        // DC4-F5: Decision FALSE - business class
        FareOffer offer = fareService.checkDiscoverEuropeFares(30, 25000, 6, 
            CabinClass.BUSINESS, DestinationRegion.EUROPE, false);
        assertNull(offer);
    }

    @Test
    public void testDecision4_DiscoverEurope_False_AsiaDestination() {
        // DC4-F6: Decision FALSE - Asia destination
        FareOffer offer = fareService.checkDiscoverEuropeFares(30, 25000, 6, 
            CabinClass.ECONOMY, DestinationRegion.ASIA, false);
        assertNull(offer);
    }

    // --- Decision 5: checkDiscoverWorldFares ---

    @Test
    public void testDecision5_DiscoverWorld_True_Asia_NoChildren() {
        // DC5-T1: Decision TRUE (Asia), no children -> Discover the World
        FareOffer offer = fareService.checkDiscoverWorldFares(35, 40000, 6, 
            CabinClass.BUSINESS, DestinationRegion.ASIA, false);
        assertNotNull(offer);
        assertEquals("Discover the World", offer.getName());
        assertEquals(20.0, offer.getDiscountPercentage(), 0.01);
    }

    @Test
    public void testDecision5_DiscoverWorld_True_America_NoChildren() {
        // DC5-T2: Decision TRUE (America), no children -> Discover the World
        FareOffer offer = fareService.checkDiscoverWorldFares(35, 40000, 6, 
            CabinClass.BUSINESS, DestinationRegion.AMERICA, false);
        assertNotNull(offer);
        assertEquals("Discover the World", offer.getName());
        assertEquals(20.0, offer.getDiscountPercentage(), 0.01);
    }

    @Test
    public void testDecision5_DiscoverWorld_True_WithChildren() {
        // DC5-T3: Decision TRUE, with children -> Discover the World with Your Little Ones
        FareOffer offer = fareService.checkDiscoverWorldFares(35, 40000, 6, 
            CabinClass.BUSINESS, DestinationRegion.ASIA, true);
        assertNotNull(offer);
        assertEquals("Discover the World with Your Little Ones", offer.getName());
        assertEquals(10.0, offer.getDiscountPercentage(), 0.01);
    }

    @Test
    public void testDecision5_DiscoverWorld_False_NotMatureAdult() {
        // DC5-F1: Decision FALSE - age=25 (not mature adult)
        FareOffer offer = fareService.checkDiscoverWorldFares(25, 40000, 6, 
            CabinClass.BUSINESS, DestinationRegion.ASIA, false);
        assertNull(offer);
    }

    @Test
    public void testDecision5_DiscoverWorld_False_NotHighIncome() {
        // DC5-F2: Decision FALSE - income=34999 (not high income)
        FareOffer offer = fareService.checkDiscoverWorldFares(35, 34999, 6, 
            CabinClass.BUSINESS, DestinationRegion.ASIA, false);
        assertNull(offer);
    }

    @Test
    public void testDecision5_DiscoverWorld_False_NotFrequent() {
        // DC5-F3: Decision FALSE - 5 flights (not frequent)
        FareOffer offer = fareService.checkDiscoverWorldFares(35, 40000, 5, 
            CabinClass.BUSINESS, DestinationRegion.ASIA, false);
        assertNull(offer);
    }

    @Test
    public void testDecision5_DiscoverWorld_False_EconomyClass() {
        // DC5-F4: Decision FALSE - economy class
        FareOffer offer = fareService.checkDiscoverWorldFares(35, 40000, 6, 
            CabinClass.ECONOMY, DestinationRegion.ASIA, false);
        assertNull(offer);
    }

    @Test
    public void testDecision5_DiscoverWorld_False_EuropeDestination() {
        // DC5-F5: Decision FALSE - Europe destination (not long haul)
        FareOffer offer = fareService.checkDiscoverWorldFares(35, 40000, 6, 
            CabinClass.BUSINESS, DestinationRegion.EUROPE, false);
        assertNull(offer);
    }

    @Test
    public void testDecision5_DiscoverWorld_False_OtherDestination() {
        // DC5-F6: Decision FALSE - Other destination (not long haul)
        FareOffer offer = fareService.checkDiscoverWorldFares(35, 40000, 6, 
            CabinClass.BUSINESS, DestinationRegion.OTHER, false);
        assertNull(offer);
    }

    // ========================================================================
    // SECTION 3: MC/DC COVERAGE TESTS
    // Based on Section 9 of the documentation
    // Each condition independently affects the decision outcome
    // For N conditions, we need N+1 test cases minimum
    // ========================================================================

    // --- MC/DC Decision 1: isMinor(age) && isFrequentFlyer(flightsPerYear) ---
    // A: isMinor(age) => age < 18
    // B: isFrequentFlyer(flightsPerYear) => flightsPerYear >= 6
    // Need 3 test cases: TT, TF, FT

    @Test
    public void testMCDC1_TT_Pajarillo() {
        // MCDC1-1: A=T, B=T -> TRUE (age=17, flights=6)
        FareOffer offer = fareService.checkPajarilloFare(17, 6);
        assertNotNull(offer);
        assertEquals("Pajarillo", offer.getName());
    }

    @Test
    public void testMCDC1_TF_Pajarillo() {
        // MCDC1-2: A=T, B=F -> FALSE (age=17, flights=5) - Shows B affects outcome
        FareOffer offer = fareService.checkPajarilloFare(17, 5);
        assertNull(offer);
    }

    @Test
    public void testMCDC1_FT_Pajarillo() {
        // MCDC1-3: A=F, B=T -> FALSE (age=18, flights=6) - Shows A affects outcome
        FareOffer offer = fareService.checkPajarilloFare(18, 6);
        assertNull(offer);
    }

    // --- MC/DC Decision 2: isYoungAdult(age) && isStudent && isEconomyClass(preferredClass) && isVeryFrequentFlyer(flightsPerYear) ---
    // A: isYoungAdult(age) => age >= 18 && age <= 25
    // B: isStudent => true/false
    // C: isEconomyClass(preferredClass) => preferredClass == CabinClass.ECONOMY
    // D: isVeryFrequentFlyer(flightsPerYear) => flightsPerYear >= 12
    // Need 5 test cases: TTTT, FTTT, TFTT, TTFT, TTTF

    @Test
    public void testMCDC2_TTTT_Gorrion() {
        // MCDC2-1: A=T, B=T, C=T, D=T -> TRUE (age=20, student, economy, 12 flights)
        FareOffer offer = fareService.checkGorrionFare(20, 12, true, CabinClass.ECONOMY);
        assertNotNull(offer);
        assertEquals("Gorrión", offer.getName());
    }

    @Test
    public void testMCDC2_FTTT_Gorrion() {
        // MCDC2-2: A=F, B=T, C=T, D=T -> FALSE (age=17) - Shows A affects outcome
        FareOffer offer = fareService.checkGorrionFare(17, 12, true, CabinClass.ECONOMY);
        assertNull(offer);
    }

    @Test
    public void testMCDC2_TFTT_Gorrion() {
        // MCDC2-3: A=T, B=F, C=T, D=T -> FALSE (not student) - Shows B affects outcome
        FareOffer offer = fareService.checkGorrionFare(20, 12, false, CabinClass.ECONOMY);
        assertNull(offer);
    }

    @Test
    public void testMCDC2_TTFT_Gorrion() {
        // MCDC2-4: A=T, B=T, C=F, D=T -> FALSE (business class) - Shows C affects outcome
        FareOffer offer = fareService.checkGorrionFare(20, 12, true, CabinClass.BUSINESS);
        assertNull(offer);
    }

    @Test
    public void testMCDC2_TTTF_Gorrion() {
        // MCDC2-5: A=T, B=T, C=T, D=F -> FALSE (11 flights) - Shows D affects outcome
        FareOffer offer = fareService.checkGorrionFare(20, 11, true, CabinClass.ECONOMY);
        assertNull(offer);
    }

    // --- MC/DC Decision 3: isYoungAdult(age) && !isStudent && isRegularFlyer(flightsPerYear) && isEconomyClass(preferredClass) ---
    // A: isYoungAdult(age) => age >= 18 && age <= 25
    // B: !isStudent => true when isStudent=false
    // C: isRegularFlyer(flightsPerYear) => flightsPerYear >= 3
    // D: isEconomyClass(preferredClass) => preferredClass == CabinClass.ECONOMY
    // Need 5 test cases: TTTT, FTTT, TFTT, TTFT, TTTF

    @Test
    public void testMCDC3_TTTT_YoungTraveler_WithParents() {
        // MCDC3-1: A=T, B=T, C=T, D=T -> TRUE (age=22, not student, 3 flights, economy)
        FareOffer offer = fareService.checkYoungTravelerFares(22, 3, false, true, CabinClass.ECONOMY);
        assertNotNull(offer);
        assertEquals("Travel While You Can", offer.getName());
    }

    @Test
    public void testMCDC3_TTTT_YoungTraveler_Independent() {
        // MCDC3-1b: A=T, B=T, C=T, D=T -> TRUE (independent)
        FareOffer offer = fareService.checkYoungTravelerFares(22, 3, false, false, CabinClass.ECONOMY);
        assertNotNull(offer);
        assertEquals("Daring to Leave the Nest", offer.getName());
    }

    @Test
    public void testMCDC3_FTTT_YoungTraveler() {
        // MCDC3-2: A=F, B=T, C=T, D=T -> FALSE (age=17) - Shows A affects outcome
        FareOffer offer = fareService.checkYoungTravelerFares(17, 3, false, true, CabinClass.ECONOMY);
        assertNull(offer);
    }

    @Test
    public void testMCDC3_TFTT_YoungTraveler() {
        // MCDC3-3: A=T, B=F, C=T, D=T -> FALSE (is student) - Shows B affects outcome
        FareOffer offer = fareService.checkYoungTravelerFares(22, 3, true, true, CabinClass.ECONOMY);
        assertNull(offer);
    }

    @Test
    public void testMCDC3_TTFT_YoungTraveler() {
        // MCDC3-4: A=T, B=T, C=F, D=T -> FALSE (2 flights) - Shows C affects outcome
        FareOffer offer = fareService.checkYoungTravelerFares(22, 2, false, true, CabinClass.ECONOMY);
        assertNull(offer);
    }

    @Test
    public void testMCDC3_TTTF_YoungTraveler() {
        // MCDC3-5: A=T, B=T, C=T, D=F -> FALSE (business class) - Shows D affects outcome
        FareOffer offer = fareService.checkYoungTravelerFares(22, 3, false, true, CabinClass.BUSINESS);
        assertNull(offer);
    }

    // --- MC/DC Decision 4: isMatureAdult(age) && hasMiddleIncome(income) && isFrequentFlyer(flightsPerYear) && isEconomyClass(preferredClass) && isEuropeDestination(preferredRegion) ---
    // A: isMatureAdult(age) => age > 25
    // B: hasMiddleIncome(income) => income > 20000 && income < 35000
    // C: isFrequentFlyer(flightsPerYear) => flightsPerYear >= 6
    // D: isEconomyClass(preferredClass) => preferredClass == CabinClass.ECONOMY
    // E: isEuropeDestination(preferredRegion) => region == DestinationRegion.EUROPE
    // Need 6 test cases: TTTTT, FTTTT, TFTTT, TTFTT, TTTFT, TTTTF

    @Test
    public void testMCDC4_TTTTT_DiscoverEurope_NoChildren() {
        // MCDC4-1: A=T, B=T, C=T, D=T, E=T -> TRUE (no children)
        FareOffer offer = fareService.checkDiscoverEuropeFares(30, 25000, 6, 
            CabinClass.ECONOMY, DestinationRegion.EUROPE, false);
        assertNotNull(offer);
        assertEquals("Discover Europe", offer.getName());
    }

    @Test
    public void testMCDC4_TTTTT_DiscoverEurope_WithChildren() {
        // MCDC4-1b: A=T, B=T, C=T, D=T, E=T -> TRUE (with children)
        FareOffer offer = fareService.checkDiscoverEuropeFares(30, 25000, 6, 
            CabinClass.ECONOMY, DestinationRegion.EUROPE, true);
        assertNotNull(offer);
        assertEquals("Discover Europe with Your Little Ones", offer.getName());
    }

    @Test
    public void testMCDC4_FTTTT_DiscoverEurope() {
        // MCDC4-2: A=F, B=T, C=T, D=T, E=T -> FALSE (age=25) - Shows A affects outcome
        FareOffer offer = fareService.checkDiscoverEuropeFares(25, 25000, 6, 
            CabinClass.ECONOMY, DestinationRegion.EUROPE, false);
        assertNull(offer);
    }

    @Test
    public void testMCDC4_TFTTT_DiscoverEurope() {
        // MCDC4-3: A=T, B=F, C=T, D=T, E=T -> FALSE (income=20000) - Shows B affects outcome
        FareOffer offer = fareService.checkDiscoverEuropeFares(30, 20000, 6, 
            CabinClass.ECONOMY, DestinationRegion.EUROPE, false);
        assertNull(offer);
    }

    @Test
    public void testMCDC4_TTFTT_DiscoverEurope() {
        // MCDC4-4: A=T, B=T, C=F, D=T, E=T -> FALSE (5 flights) - Shows C affects outcome
        FareOffer offer = fareService.checkDiscoverEuropeFares(30, 25000, 5, 
            CabinClass.ECONOMY, DestinationRegion.EUROPE, false);
        assertNull(offer);
    }

    @Test
    public void testMCDC4_TTTFT_DiscoverEurope() {
        // MCDC4-5: A=T, B=T, C=T, D=F, E=T -> FALSE (business class) - Shows D affects outcome
        FareOffer offer = fareService.checkDiscoverEuropeFares(30, 25000, 6, 
            CabinClass.BUSINESS, DestinationRegion.EUROPE, false);
        assertNull(offer);
    }

    @Test
    public void testMCDC4_TTTTF_DiscoverEurope() {
        // MCDC4-6: A=T, B=T, C=T, D=T, E=F -> FALSE (Asia) - Shows E affects outcome
        FareOffer offer = fareService.checkDiscoverEuropeFares(30, 25000, 6, 
            CabinClass.ECONOMY, DestinationRegion.ASIA, false);
        assertNull(offer);
    }

    // --- MC/DC Decision 5: isMatureAdult(age) && hasHighIncome(income) && isFrequentFlyer(flightsPerYear) && isBusinessClass(preferredClass) && isLongHaulDestination(preferredRegion) ---
    // A: isMatureAdult(age) => age > 25
    // B: hasHighIncome(income) => income >= 35000
    // C: isFrequentFlyer(flightsPerYear) => flightsPerYear >= 6
    // D: isBusinessClass(preferredClass) => preferredClass == CabinClass.BUSINESS
    // E: isLongHaulDestination(preferredRegion) => region == ASIA || region == AMERICA
    // Need 6 test cases: TTTTT, FTTTT, TFTTT, TTFTT, TTTFT, TTTTF

    @Test
    public void testMCDC5_TTTTT_DiscoverWorld_Asia_NoChildren() {
        // MCDC5-1: A=T, B=T, C=T, D=T, E=T -> TRUE (Asia, no children)
        FareOffer offer = fareService.checkDiscoverWorldFares(35, 40000, 6, 
            CabinClass.BUSINESS, DestinationRegion.ASIA, false);
        assertNotNull(offer);
        assertEquals("Discover the World", offer.getName());
    }

    @Test
    public void testMCDC5_TTTTT_DiscoverWorld_America_NoChildren() {
        // MCDC5-1b: A=T, B=T, C=T, D=T, E=T -> TRUE (America, no children)
        FareOffer offer = fareService.checkDiscoverWorldFares(35, 40000, 6, 
            CabinClass.BUSINESS, DestinationRegion.AMERICA, false);
        assertNotNull(offer);
        assertEquals("Discover the World", offer.getName());
    }

    @Test
    public void testMCDC5_TTTTT_DiscoverWorld_WithChildren() {
        // MCDC5-1c: A=T, B=T, C=T, D=T, E=T -> TRUE (with children)
        FareOffer offer = fareService.checkDiscoverWorldFares(35, 40000, 6, 
            CabinClass.BUSINESS, DestinationRegion.ASIA, true);
        assertNotNull(offer);
        assertEquals("Discover the World with Your Little Ones", offer.getName());
    }

    @Test
    public void testMCDC5_FTTTT_DiscoverWorld() {
        // MCDC5-2: A=F, B=T, C=T, D=T, E=T -> FALSE (age=25) - Shows A affects outcome
        FareOffer offer = fareService.checkDiscoverWorldFares(25, 40000, 6, 
            CabinClass.BUSINESS, DestinationRegion.ASIA, false);
        assertNull(offer);
    }

    @Test
    public void testMCDC5_TFTTT_DiscoverWorld() {
        // MCDC5-3: A=T, B=F, C=T, D=T, E=T -> FALSE (income=34999) - Shows B affects outcome
        FareOffer offer = fareService.checkDiscoverWorldFares(35, 34999, 6, 
            CabinClass.BUSINESS, DestinationRegion.ASIA, false);
        assertNull(offer);
    }

    @Test
    public void testMCDC5_TTFTT_DiscoverWorld() {
        // MCDC5-4: A=T, B=T, C=F, D=T, E=T -> FALSE (5 flights) - Shows C affects outcome
        FareOffer offer = fareService.checkDiscoverWorldFares(35, 40000, 5, 
            CabinClass.BUSINESS, DestinationRegion.ASIA, false);
        assertNull(offer);
    }

    @Test
    public void testMCDC5_TTTFT_DiscoverWorld() {
        // MCDC5-5: A=T, B=T, C=T, D=F, E=T -> FALSE (economy class) - Shows D affects outcome
        FareOffer offer = fareService.checkDiscoverWorldFares(35, 40000, 6, 
            CabinClass.ECONOMY, DestinationRegion.ASIA, false);
        assertNull(offer);
    }

    @Test
    public void testMCDC5_TTTTF_DiscoverWorld() {
        // MCDC5-6: A=T, B=T, C=T, D=T, E=F -> FALSE (Europe) - Shows E affects outcome
        FareOffer offer = fareService.checkDiscoverWorldFares(35, 40000, 6, 
            CabinClass.BUSINESS, DestinationRegion.EUROPE, false);
        assertNull(offer);
    }

    // ========================================================================
    // SECTION 4: HELPER METHODS UNIT TESTS
    // ========================================================================

    // --- Age Classification Methods ---

    @Test
    public void testIsMinor_True() {
        assertTrue(fareService.isMinor(17));
    }

    @Test
    public void testIsMinor_False_Boundary() {
        assertFalse(fareService.isMinor(18));
    }

    @Test
    public void testIsYoungAdult_True_Lower() {
        assertTrue(fareService.isYoungAdult(18));
    }

    @Test
    public void testIsYoungAdult_True_Upper() {
        assertTrue(fareService.isYoungAdult(25));
    }

    @Test
    public void testIsYoungAdult_False_Below() {
        assertFalse(fareService.isYoungAdult(17));
    }

    @Test
    public void testIsYoungAdult_False_Above() {
        assertFalse(fareService.isYoungAdult(26));
    }

    @Test
    public void testIsMatureAdult_True() {
        assertTrue(fareService.isMatureAdult(26));
    }

    @Test
    public void testIsMatureAdult_False() {
        assertFalse(fareService.isMatureAdult(25));
    }

    // --- Flight Frequency Methods ---

    @Test
    public void testIsRegularFlyer_True() {
        assertTrue(fareService.isRegularFlyer(3));
    }

    @Test
    public void testIsRegularFlyer_False() {
        assertFalse(fareService.isRegularFlyer(2));
    }

    @Test
    public void testIsFrequentFlyer_True() {
        assertTrue(fareService.isFrequentFlyer(6));
    }

    @Test
    public void testIsFrequentFlyer_False() {
        assertFalse(fareService.isFrequentFlyer(5));
    }

    @Test
    public void testIsVeryFrequentFlyer_True() {
        assertTrue(fareService.isVeryFrequentFlyer(12));
    }

    @Test
    public void testIsVeryFrequentFlyer_False() {
        assertFalse(fareService.isVeryFrequentFlyer(11));
    }

    // --- Income Classification Methods ---

    @Test
    public void testHasMiddleIncome_True_Lower() {
        assertTrue(fareService.hasMiddleIncome(20001));
    }

    @Test
    public void testHasMiddleIncome_True_Upper() {
        assertTrue(fareService.hasMiddleIncome(34999));
    }

    @Test
    public void testHasMiddleIncome_False_Lower() {
        assertFalse(fareService.hasMiddleIncome(20000));
    }

    @Test
    public void testHasMiddleIncome_False_Upper() {
        assertFalse(fareService.hasMiddleIncome(35000));
    }

    @Test
    public void testHasHighIncome_True() {
        assertTrue(fareService.hasHighIncome(35000));
    }

    @Test
    public void testHasHighIncome_False() {
        assertFalse(fareService.hasHighIncome(34999));
    }

    // --- Class and Destination Methods ---

    @Test
    public void testIsEconomyClass_True() {
        assertTrue(fareService.isEconomyClass(CabinClass.ECONOMY));
    }

    @Test
    public void testIsEconomyClass_False() {
        assertFalse(fareService.isEconomyClass(CabinClass.BUSINESS));
    }

    @Test
    public void testIsBusinessClass_True() {
        assertTrue(fareService.isBusinessClass(CabinClass.BUSINESS));
    }

    @Test
    public void testIsBusinessClass_False() {
        assertFalse(fareService.isBusinessClass(CabinClass.ECONOMY));
    }

    @Test
    public void testIsEuropeDestination_True() {
        assertTrue(fareService.isEuropeDestination(DestinationRegion.EUROPE));
    }

    @Test
    public void testIsEuropeDestination_False() {
        assertFalse(fareService.isEuropeDestination(DestinationRegion.ASIA));
    }

    @Test
    public void testIsLongHaulDestination_Asia_True() {
        assertTrue(fareService.isLongHaulDestination(DestinationRegion.ASIA));
    }

    @Test
    public void testIsLongHaulDestination_America_True() {
        assertTrue(fareService.isLongHaulDestination(DestinationRegion.AMERICA));
    }

    @Test
    public void testIsLongHaulDestination_Europe_False() {
        assertFalse(fareService.isLongHaulDestination(DestinationRegion.EUROPE));
    }

    @Test
    public void testIsLongHaulDestination_Other_False() {
        assertFalse(fareService.isLongHaulDestination(DestinationRegion.OTHER));
    }

    // ========================================================================
    // SECTION 5: BOUNDARY VALUE TESTS
    // ========================================================================

    @Test
    public void testBoundary_AgePajarilloToGorrion() {
        // BV-01: Age boundary 17->18 transition for Pajarillo
        // age=17 with frequent flights should get Pajarillo
        FareOffer offer17 = fareService.determineFare(17, 12, true, false, 0, 
            CabinClass.ECONOMY, DestinationRegion.EUROPE, false);
        assertNotNull(offer17);
        assertEquals("Pajarillo", offer17.getName());

        // age=18 with same conditions should get Gorrión (if student)
        FareOffer offer18 = fareService.determineFare(18, 12, true, false, 0, 
            CabinClass.ECONOMY, DestinationRegion.EUROPE, false);
        assertNotNull(offer18);
        assertEquals("Gorrión", offer18.getName());
    }

    @Test
    public void testBoundary_AgeYoungToMature() {
        // BV-02: Age boundary 25->26 transition for Young Traveler to Discover Europe
        // age=25 with worker conditions should get Young Traveler fare
        FareOffer offer25 = fareService.determineFare(25, 6, false, false, 25000, 
            CabinClass.ECONOMY, DestinationRegion.EUROPE, false);
        assertNotNull(offer25);
        assertEquals("Daring to Leave the Nest", offer25.getName());

        // age=26 with same income should get Discover Europe
        FareOffer offer26 = fareService.determineFare(26, 6, false, false, 25000, 
            CabinClass.ECONOMY, DestinationRegion.EUROPE, false);
        assertNotNull(offer26);
        assertEquals("Discover Europe", offer26.getName());
    }

    @Test
    public void testBoundary_FlightsFrequentFlyer() {
        // BV-03: Flights boundary 5->6 for frequent flyer threshold
        // 5 flights should not qualify for Pajarillo
        FareOffer offer5 = fareService.checkPajarilloFare(17, 5);
        assertNull(offer5);

        // 6 flights should qualify for Pajarillo
        FareOffer offer6 = fareService.checkPajarilloFare(17, 6);
        assertNotNull(offer6);
        assertEquals("Pajarillo", offer6.getName());
    }

    @Test
    public void testBoundary_FlightsVeryFrequentFlyer() {
        // BV-04: Flights boundary 11->12 for very frequent flyer threshold
        // 11 flights should not qualify for Gorrión
        FareOffer offer11 = fareService.checkGorrionFare(20, 11, true, CabinClass.ECONOMY);
        assertNull(offer11);

        // 12 flights should qualify for Gorrión
        FareOffer offer12 = fareService.checkGorrionFare(20, 12, true, CabinClass.ECONOMY);
        assertNotNull(offer12);
        assertEquals("Gorrión", offer12.getName());
    }

    @Test
    public void testBoundary_IncomeMiddleLower() {
        // BV-05: Income boundary 20000->20001 for middle income
        // income=20000 should not qualify
        FareOffer offer20000 = fareService.checkDiscoverEuropeFares(30, 20000, 6, 
            CabinClass.ECONOMY, DestinationRegion.EUROPE, false);
        assertNull(offer20000);

        // income=20001 should qualify
        FareOffer offer20001 = fareService.checkDiscoverEuropeFares(30, 20001, 6, 
            CabinClass.ECONOMY, DestinationRegion.EUROPE, false);
        assertNotNull(offer20001);
        assertEquals("Discover Europe", offer20001.getName());
    }

    @Test
    public void testBoundary_IncomeMiddleToHigh() {
        // BV-06: Income boundary 34999->35000 for middle/high income transition
        // income=34999 should qualify for Discover Europe (middle income)
        FareOffer offer34999 = fareService.checkDiscoverEuropeFares(30, 34999, 6, 
            CabinClass.ECONOMY, DestinationRegion.EUROPE, false);
        assertNotNull(offer34999);
        assertEquals("Discover Europe", offer34999.getName());

        // income=35000 should not qualify for Discover Europe (becomes high income)
        FareOffer offer35000Europe = fareService.checkDiscoverEuropeFares(30, 35000, 6, 
            CabinClass.ECONOMY, DestinationRegion.EUROPE, false);
        assertNull(offer35000Europe);

        // income=35000 should qualify for Discover World with business class
        FareOffer offer35000World = fareService.checkDiscoverWorldFares(30, 35000, 6, 
            CabinClass.BUSINESS, DestinationRegion.ASIA, false);
        assertNotNull(offer35000World);
        assertEquals("Discover the World", offer35000World.getName());
    }
}
