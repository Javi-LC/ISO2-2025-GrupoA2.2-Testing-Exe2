package ISO2.Exe2;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ISO2.Exe2.model.CabinClass;
import ISO2.Exe2.model.DestinationRegion;
import ISO2.Exe2.model.FareOffer;
import ISO2.Exe2.service.FareService;

/**
 * Unit tests for App - Integration tests with Boundary Value Analysis
 * and Helper Method Tests based on the documentation.
 * 
 * Tests App.main() by simulating console input/output.
 */
public class AppTest {

    private FareService fareService;
    private final InputStream originalIn = System.in;
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;
    private ByteArrayOutputStream outputStream;
    private ByteArrayOutputStream errorStream;

    @Before
    public void setUp() {
        fareService = new FareService();
        outputStream = new ByteArrayOutputStream();
        errorStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        System.setErr(new PrintStream(errorStream));
    }

    @After
    public void tearDown() {
        System.setIn(originalIn);
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    /**
     * Helper method to simulate console input
     */
    private void provideInput(String data) {
        System.setIn(new ByteArrayInputStream(data.getBytes()));
    }

    // ========================================================================
    // APP.MAIN() TESTS - Testing the main application flow
    // ========================================================================

    @Test
    public void testApp_Main_Pajarillo_Fare() {
        // Input: age=17, flights=6, student=false, livesWithParents=false, 
        // income=0, class=ECONOMY, region=EUROPE, children=false
        String input = "17\n6\nfalse\nfalse\n0\nECONOMY\nEUROPE\nfalse\n";
        provideInput(input);

        App.main(new String[]{});

        String output = outputStream.toString();
        assertTrue("Should contain Pajarillo fare", output.contains("Pajarillo"));
        assertTrue("Should contain 10% discount", output.contains("10"));
    }

    @Test
    public void testApp_Main_Gorrion_Fare() {
        // Input: age=20, flights=12, student=true, livesWithParents=false,
        // income=15000, class=ECONOMY, region=EUROPE, children=false
        String input = "20\n12\ntrue\nfalse\n15000\nECONOMY\nEUROPE\nfalse\n";
        provideInput(input);

        App.main(new String[]{});

        String output = outputStream.toString();
        assertTrue("Should contain Gorrión fare", output.contains("Gorrión"));
        assertTrue("Should contain 15% discount", output.contains("15"));
    }

    @Test
    public void testApp_Main_TravelWhileYouCan_Fare() {
        // Input: age=22, flights=3, student=false, livesWithParents=true,
        // income=18000, class=ECONOMY, region=EUROPE, children=false
        String input = "22\n3\nfalse\ntrue\n18000\nECONOMY\nEUROPE\nfalse\n";
        provideInput(input);

        App.main(new String[]{});

        String output = outputStream.toString();
        assertTrue("Should contain Travel While You Can fare", output.contains("Travel While You Can"));
    }

    @Test
    public void testApp_Main_DaringToLeaveNest_Fare() {
        // Input: age=22, flights=3, student=false, livesWithParents=false,
        // income=18000, class=ECONOMY, region=EUROPE, children=false
        String input = "22\n3\nfalse\nfalse\n18000\nECONOMY\nEUROPE\nfalse\n";
        provideInput(input);

        App.main(new String[]{});

        String output = outputStream.toString();
        assertTrue("Should contain Daring to Leave the Nest fare", output.contains("Daring to Leave the Nest"));
    }

    @Test
    public void testApp_Main_DiscoverEurope_Fare() {
        // Input: age=30, flights=6, student=false, livesWithParents=false,
        // income=25000, class=ECONOMY, region=EUROPE, children=false
        String input = "30\n6\nfalse\nfalse\n25000\nECONOMY\nEUROPE\nfalse\n";
        provideInput(input);

        App.main(new String[]{});

        String output = outputStream.toString();
        assertTrue("Should contain Discover Europe fare", output.contains("Discover Europe"));
    }

    @Test
    public void testApp_Main_DiscoverEuropeWithChildren_Fare() {
        // Input: age=30, flights=6, student=false, livesWithParents=false,
        // income=25000, class=ECONOMY, region=EUROPE, children=true
        String input = "30\n6\nfalse\nfalse\n25000\nECONOMY\nEUROPE\ntrue\n";
        provideInput(input);

        App.main(new String[]{});

        String output = outputStream.toString();
        assertTrue("Should contain Discover Europe with Your Little Ones fare", 
            output.contains("Discover Europe with Your Little Ones"));
    }

    @Test
    public void testApp_Main_DiscoverWorld_Fare() {
        // Input: age=35, flights=6, student=false, livesWithParents=false,
        // income=40000, class=BUSINESS, region=ASIA, children=false
        String input = "35\n6\nfalse\nfalse\n40000\nBUSINESS\nASIA\nfalse\n";
        provideInput(input);

        App.main(new String[]{});

        String output = outputStream.toString();
        assertTrue("Should contain Discover the World fare", output.contains("Discover the World"));
    }

    @Test
    public void testApp_Main_DiscoverWorldWithChildren_Fare() {
        // Input: age=35, flights=6, student=false, livesWithParents=false,
        // income=40000, class=BUSINESS, region=AMERICA, children=true
        String input = "35\n6\nfalse\nfalse\n40000\nBUSINESS\nAMERICA\ntrue\n";
        provideInput(input);

        App.main(new String[]{});

        String output = outputStream.toString();
        assertTrue("Should contain Discover the World with Your Little Ones fare", 
            output.contains("Discover the World with Your Little Ones"));
    }

    @Test
    public void testApp_Main_NoOffer() {
        // Input: age=50, flights=1, student=false, livesWithParents=false,
        // income=15000, class=ECONOMY, region=OTHER, children=false
        String input = "50\n1\nfalse\nfalse\n15000\nECONOMY\nOTHER\nfalse\n";
        provideInput(input);

        App.main(new String[]{});

        String output = outputStream.toString();
        assertTrue("Should indicate no special offer", output.contains("NO SPECIAL OFFER"));
    }

    @Test
    public void testApp_Main_LowercaseInput() {
        // Test with lowercase class and region input
        String input = "17\n6\nfalse\nfalse\n0\neconomy\neurope\nfalse\n";
        provideInput(input);

        App.main(new String[]{});

        String output = outputStream.toString();
        assertTrue("Should handle lowercase input and find Pajarillo", output.contains("Pajarillo"));
    }

    // ========================================================================
    // ERROR HANDLING TESTS - Testing exception branches in App.main()
    // ========================================================================

    @Test
    public void testApp_Main_NegativeAge_Error() {
        // Input with negative age - should trigger InputException
        String input = "-5\n6\nfalse\nfalse\n0\nECONOMY\nEUROPE\nfalse\n";
        provideInput(input);

        App.main(new String[]{});

        String error = errorStream.toString();
        assertTrue("Should show error for negative age", error.contains("Age cannot be negative"));
    }

    @Test
    public void testApp_Main_NegativeFlights_Error() {
        // Input with negative flights - should trigger InputException
        String input = "25\n-1\nfalse\nfalse\n0\nECONOMY\nEUROPE\nfalse\n";
        provideInput(input);

        App.main(new String[]{});

        String error = errorStream.toString();
        assertTrue("Should show error for negative flights", error.contains("flights cannot be negative"));
    }

    @Test
    public void testApp_Main_NegativeIncome_Error() {
        // Input with negative income - should trigger InputException
        String input = "25\n6\nfalse\nfalse\n-1000\nECONOMY\nEUROPE\nfalse\n";
        provideInput(input);

        App.main(new String[]{});

        String error = errorStream.toString();
        assertTrue("Should show error for negative income", error.contains("Income cannot be negative"));
    }

    @Test
    public void testApp_Main_InvalidCabinClass_Error() {
        // Input with invalid cabin class - should trigger IllegalArgumentException
        String input = "25\n6\nfalse\nfalse\n25000\nFIRST\nEUROPE\nfalse\n";
        provideInput(input);

        App.main(new String[]{});

        String error = errorStream.toString();
        assertTrue("Should show error for invalid cabin class", 
            error.contains("Invalid input value") || error.contains("FIRST"));
    }

    @Test
    public void testApp_Main_InvalidRegion_Error() {
        // Input with invalid region - should trigger IllegalArgumentException
        String input = "25\n6\nfalse\nfalse\n25000\nECONOMY\nANTARCTICA\nfalse\n";
        provideInput(input);

        App.main(new String[]{});

        String error = errorStream.toString();
        assertTrue("Should show error for invalid region", 
            error.contains("Invalid input value") || error.contains("ANTARCTICA"));
    }

    // ========================================================================
    // BOUNDARY VALUE TESTS (Section 7 of documentation)
    // Testing edge cases at boundaries of input domains
    // ========================================================================

    // --- BVT-01: Age Boundaries for Minor (< 18) ---

    @Test
    public void testBVT01_Age_Minor_LowerBound_0() {
        // BVT-01a: age = 0 (minimum valid age for minor)
        assertTrue(fareService.isMinor(0));
    }

    @Test
    public void testBVT01_Age_Minor_UpperBound_17() {
        // BVT-01b: age = 17 (maximum age still minor)
        assertTrue(fareService.isMinor(17));
    }

    @Test
    public void testBVT01_Age_Minor_JustAbove_18() {
        // BVT-01c: age = 18 (just above minor boundary)
        assertFalse(fareService.isMinor(18));
    }

    @Test
    public void testBVT01_Age_Negative() {
        // BVT-01d: age = -1 (invalid negative age)
        // Note: The implementation treats negative ages as minor (age < 18)
        // With 6 flights, this qualifies for Pajarillo fare
        FareOffer offer = fareService.determineFare(-1, 6, false, false, 0,
            CabinClass.ECONOMY, DestinationRegion.EUROPE, false);
        // Implementation does not validate negative ages - treats as minor
        assertNotNull(offer);
        assertEquals("Pajarillo", offer.getName());
    }

    // --- BVT-02: Age Boundaries for Young Adult (18-25) ---

    @Test
    public void testBVT02_Age_YoungAdult_LowerBound_18() {
        // BVT-02a: age = 18 (minimum young adult)
        assertTrue(fareService.isYoungAdult(18));
    }

    @Test
    public void testBVT02_Age_YoungAdult_UpperBound_25() {
        // BVT-02b: age = 25 (maximum young adult)
        assertTrue(fareService.isYoungAdult(25));
    }

    @Test
    public void testBVT02_Age_YoungAdult_JustBelow_17() {
        // BVT-02c: age = 17 (just below young adult)
        assertFalse(fareService.isYoungAdult(17));
    }

    @Test
    public void testBVT02_Age_YoungAdult_JustAbove_26() {
        // BVT-02d: age = 26 (just above young adult)
        assertFalse(fareService.isYoungAdult(26));
    }

    // --- BVT-03: Age Boundaries for Mature Adult (> 25) ---

    @Test
    public void testBVT03_Age_MatureAdult_LowerBound_26() {
        // BVT-03a: age = 26 (minimum mature adult)
        assertTrue(fareService.isMatureAdult(26));
    }

    @Test
    public void testBVT03_Age_MatureAdult_JustBelow_25() {
        // BVT-03b: age = 25 (just below mature adult)
        assertFalse(fareService.isMatureAdult(25));
    }

    @Test
    public void testBVT03_Age_MatureAdult_HighValue_100() {
        // BVT-03c: age = 100 (high value, still mature adult)
        assertTrue(fareService.isMatureAdult(100));
    }

    // --- BVT-04: Flights Boundaries for Regular Flyer (>= 3) ---

    @Test
    public void testBVT04_Flights_RegularFlyer_LowerBound_3() {
        // BVT-04a: flights = 3 (minimum regular flyer)
        assertTrue(fareService.isRegularFlyer(3));
    }

    @Test
    public void testBVT04_Flights_RegularFlyer_JustBelow_2() {
        // BVT-04b: flights = 2 (just below regular)
        assertFalse(fareService.isRegularFlyer(2));
    }

    @Test
    public void testBVT04_Flights_RegularFlyer_Zero() {
        // BVT-04c: flights = 0 (minimum possible)
        assertFalse(fareService.isRegularFlyer(0));
    }

    // --- BVT-05: Flights Boundaries for Frequent Flyer (>= 6) ---

    @Test
    public void testBVT05_Flights_FrequentFlyer_LowerBound_6() {
        // BVT-05a: flights = 6 (minimum frequent flyer)
        assertTrue(fareService.isFrequentFlyer(6));
    }

    @Test
    public void testBVT05_Flights_FrequentFlyer_JustBelow_5() {
        // BVT-05b: flights = 5 (just below frequent)
        assertFalse(fareService.isFrequentFlyer(5));
    }

    @Test
    public void testBVT05_Flights_FrequentFlyer_HighValue() {
        // BVT-05c: flights = 100 (high value)
        assertTrue(fareService.isFrequentFlyer(100));
    }

    // --- BVT-06: Flights Boundaries for Very Frequent Flyer (>= 12) ---

    @Test
    public void testBVT06_Flights_VeryFrequentFlyer_LowerBound_12() {
        // BVT-06a: flights = 12 (minimum very frequent flyer)
        assertTrue(fareService.isVeryFrequentFlyer(12));
    }

    @Test
    public void testBVT06_Flights_VeryFrequentFlyer_JustBelow_11() {
        // BVT-06b: flights = 11 (just below very frequent)
        assertFalse(fareService.isVeryFrequentFlyer(11));
    }

    // --- BVT-07: Income Boundaries for Middle Income (> 20000 && < 35000) ---

    @Test
    public void testBVT07_Income_MiddleIncome_LowerBound_20001() {
        // BVT-07a: income = 20001 (just above lower bound)
        assertTrue(fareService.hasMiddleIncome(20001));
    }

    @Test
    public void testBVT07_Income_MiddleIncome_AtLowerBound_20000() {
        // BVT-07b: income = 20000 (exactly at lower bound - NOT middle)
        assertFalse(fareService.hasMiddleIncome(20000));
    }

    @Test
    public void testBVT07_Income_MiddleIncome_UpperBound_34999() {
        // BVT-07c: income = 34999 (just below upper bound)
        assertTrue(fareService.hasMiddleIncome(34999));
    }

    @Test
    public void testBVT07_Income_MiddleIncome_AtUpperBound_35000() {
        // BVT-07d: income = 35000 (exactly at upper bound - NOT middle)
        assertFalse(fareService.hasMiddleIncome(35000));
    }

    @Test
    public void testBVT07_Income_MiddleIncome_Middle_27500() {
        // BVT-07e: income = 27500 (middle of range)
        assertTrue(fareService.hasMiddleIncome(27500));
    }

    // --- BVT-08: Income Boundaries for High Income (>= 35000) ---

    @Test
    public void testBVT08_Income_HighIncome_LowerBound_35000() {
        // BVT-08a: income = 35000 (minimum high income)
        assertTrue(fareService.hasHighIncome(35000));
    }

    @Test
    public void testBVT08_Income_HighIncome_JustBelow_34999() {
        // BVT-08b: income = 34999 (just below high income)
        assertFalse(fareService.hasHighIncome(34999));
    }

    @Test
    public void testBVT08_Income_HighIncome_HighValue_100000() {
        // BVT-08c: income = 100000 (high value)
        assertTrue(fareService.hasHighIncome(100000));
    }

    // --- BVT-09: Fare Transition at Age 17->18 (Pajarillo to Gorrión) ---

    @Test
    public void testBVT09_FareTransition_Age17_Pajarillo() {
        // BVT-09a: age=17, frequent student -> Pajarillo (minor)
        FareOffer offer = fareService.determineFare(17, 12, true, false, 0,
            CabinClass.ECONOMY, DestinationRegion.EUROPE, false);
        assertNotNull(offer);
        assertEquals("Pajarillo", offer.getName());
    }

    @Test
    public void testBVT09_FareTransition_Age18_Gorrion() {
        // BVT-09b: age=18, very frequent student -> Gorrión (young adult)
        FareOffer offer = fareService.determineFare(18, 12, true, false, 0,
            CabinClass.ECONOMY, DestinationRegion.EUROPE, false);
        assertNotNull(offer);
        assertEquals("Gorrión", offer.getName());
    }

    // --- BVT-10: Fare Transition at Age 25->26 (Young Traveler to Discover Europe) ---

    @Test
    public void testBVT10_FareTransition_Age25_YoungTraveler() {
        // BVT-10a: age=25, regular worker economy Europe -> Daring to Leave the Nest
        FareOffer offer = fareService.determineFare(25, 6, false, false, 25000,
            CabinClass.ECONOMY, DestinationRegion.EUROPE, false);
        assertNotNull(offer);
        assertEquals("Daring to Leave the Nest", offer.getName());
    }

    @Test
    public void testBVT10_FareTransition_Age26_DiscoverEurope() {
        // BVT-10b: age=26, frequent worker economy Europe middle income -> Discover Europe
        FareOffer offer = fareService.determineFare(26, 6, false, false, 25000,
            CabinClass.ECONOMY, DestinationRegion.EUROPE, false);
        assertNotNull(offer);
        assertEquals("Discover Europe", offer.getName());
    }

    // --- BVT-11: Flight Threshold Transitions ---

    @Test
    public void testBVT11_FlightThreshold_2to3_RegularFlyer() {
        // BVT-11a: flights=2 -> not regular (no Young Traveler fare)
        FareOffer offer2 = fareService.checkYoungTravelerFares(22, 2, false, true, CabinClass.ECONOMY);
        assertNull(offer2);

        // BVT-11b: flights=3 -> regular (Young Traveler fare)
        FareOffer offer3 = fareService.checkYoungTravelerFares(22, 3, false, true, CabinClass.ECONOMY);
        assertNotNull(offer3);
    }

    @Test
    public void testBVT11_FlightThreshold_5to6_FrequentFlyer() {
        // BVT-11c: flights=5 -> not frequent (no Pajarillo fare)
        FareOffer offer5 = fareService.checkPajarilloFare(17, 5);
        assertNull(offer5);

        // BVT-11d: flights=6 -> frequent (Pajarillo fare)
        FareOffer offer6 = fareService.checkPajarilloFare(17, 6);
        assertNotNull(offer6);
    }

    @Test
    public void testBVT11_FlightThreshold_11to12_VeryFrequentFlyer() {
        // BVT-11e: flights=11 -> not very frequent (no Gorrión fare)
        FareOffer offer11 = fareService.checkGorrionFare(20, 11, true, CabinClass.ECONOMY);
        assertNull(offer11);

        // BVT-11f: flights=12 -> very frequent (Gorrión fare)
        FareOffer offer12 = fareService.checkGorrionFare(20, 12, true, CabinClass.ECONOMY);
        assertNotNull(offer12);
    }

    // --- BVT-12: Income Threshold Transitions ---

    @Test
    public void testBVT12_IncomeThreshold_20000to20001_MiddleIncome() {
        // BVT-12a: income=20000 -> not middle (no Discover Europe)
        FareOffer offer20000 = fareService.checkDiscoverEuropeFares(30, 20000, 6,
            CabinClass.ECONOMY, DestinationRegion.EUROPE, false);
        assertNull(offer20000);

        // BVT-12b: income=20001 -> middle (Discover Europe)
        FareOffer offer20001 = fareService.checkDiscoverEuropeFares(30, 20001, 6,
            CabinClass.ECONOMY, DestinationRegion.EUROPE, false);
        assertNotNull(offer20001);
    }

    @Test
    public void testBVT12_IncomeThreshold_34999to35000_MiddleToHigh() {
        // BVT-12c: income=34999 -> middle income (Discover Europe)
        FareOffer offer34999 = fareService.checkDiscoverEuropeFares(30, 34999, 6,
            CabinClass.ECONOMY, DestinationRegion.EUROPE, false);
        assertNotNull(offer34999);
        assertEquals("Discover Europe", offer34999.getName());

        // BVT-12d: income=35000 -> high income (no Discover Europe, but Discover World with business)
        FareOffer offer35000Europe = fareService.checkDiscoverEuropeFares(30, 35000, 6,
            CabinClass.ECONOMY, DestinationRegion.EUROPE, false);
        assertNull(offer35000Europe);

        FareOffer offer35000World = fareService.checkDiscoverWorldFares(30, 35000, 6,
            CabinClass.BUSINESS, DestinationRegion.ASIA, false);
        assertNotNull(offer35000World);
        assertEquals("Discover the World", offer35000World.getName());
    }

    // ========================================================================
    // HELPER METHOD TESTS (Section 10 of documentation)
    // Testing all predicate helper methods individually
    // ========================================================================

    // --- HM-01: isMinor(age) - age < 18 ---

    @Test
    public void testHM01_isMinor_True_Age0() {
        assertTrue("Age 0 should be minor", fareService.isMinor(0));
    }

    @Test
    public void testHM01_isMinor_True_Age10() {
        assertTrue("Age 10 should be minor", fareService.isMinor(10));
    }

    @Test
    public void testHM01_isMinor_True_Age17() {
        assertTrue("Age 17 should be minor", fareService.isMinor(17));
    }

    @Test
    public void testHM01_isMinor_False_Age18() {
        assertFalse("Age 18 should NOT be minor", fareService.isMinor(18));
    }

    @Test
    public void testHM01_isMinor_False_Age30() {
        assertFalse("Age 30 should NOT be minor", fareService.isMinor(30));
    }

    // --- HM-02: isYoungAdult(age) - age >= 18 && age <= 25 ---

    @Test
    public void testHM02_isYoungAdult_False_Age17() {
        assertFalse("Age 17 should NOT be young adult", fareService.isYoungAdult(17));
    }

    @Test
    public void testHM02_isYoungAdult_True_Age18() {
        assertTrue("Age 18 should be young adult", fareService.isYoungAdult(18));
    }

    @Test
    public void testHM02_isYoungAdult_True_Age21() {
        assertTrue("Age 21 should be young adult", fareService.isYoungAdult(21));
    }

    @Test
    public void testHM02_isYoungAdult_True_Age25() {
        assertTrue("Age 25 should be young adult", fareService.isYoungAdult(25));
    }

    @Test
    public void testHM02_isYoungAdult_False_Age26() {
        assertFalse("Age 26 should NOT be young adult", fareService.isYoungAdult(26));
    }

    // --- HM-03: isMatureAdult(age) - age > 25 ---

    @Test
    public void testHM03_isMatureAdult_False_Age25() {
        assertFalse("Age 25 should NOT be mature adult", fareService.isMatureAdult(25));
    }

    @Test
    public void testHM03_isMatureAdult_True_Age26() {
        assertTrue("Age 26 should be mature adult", fareService.isMatureAdult(26));
    }

    @Test
    public void testHM03_isMatureAdult_True_Age50() {
        assertTrue("Age 50 should be mature adult", fareService.isMatureAdult(50));
    }

    // --- HM-04: isRegularFlyer(flightsPerYear) - flightsPerYear >= 3 ---

    @Test
    public void testHM04_isRegularFlyer_False_Flights0() {
        assertFalse("0 flights should NOT be regular flyer", fareService.isRegularFlyer(0));
    }

    @Test
    public void testHM04_isRegularFlyer_False_Flights2() {
        assertFalse("2 flights should NOT be regular flyer", fareService.isRegularFlyer(2));
    }

    @Test
    public void testHM04_isRegularFlyer_True_Flights3() {
        assertTrue("3 flights should be regular flyer", fareService.isRegularFlyer(3));
    }

    @Test
    public void testHM04_isRegularFlyer_True_Flights10() {
        assertTrue("10 flights should be regular flyer", fareService.isRegularFlyer(10));
    }

    // --- HM-05: isFrequentFlyer(flightsPerYear) - flightsPerYear >= 6 ---

    @Test
    public void testHM05_isFrequentFlyer_False_Flights5() {
        assertFalse("5 flights should NOT be frequent flyer", fareService.isFrequentFlyer(5));
    }

    @Test
    public void testHM05_isFrequentFlyer_True_Flights6() {
        assertTrue("6 flights should be frequent flyer", fareService.isFrequentFlyer(6));
    }

    @Test
    public void testHM05_isFrequentFlyer_True_Flights20() {
        assertTrue("20 flights should be frequent flyer", fareService.isFrequentFlyer(20));
    }

    // --- HM-06: isVeryFrequentFlyer(flightsPerYear) - flightsPerYear >= 12 ---

    @Test
    public void testHM06_isVeryFrequentFlyer_False_Flights11() {
        assertFalse("11 flights should NOT be very frequent flyer", fareService.isVeryFrequentFlyer(11));
    }

    @Test
    public void testHM06_isVeryFrequentFlyer_True_Flights12() {
        assertTrue("12 flights should be very frequent flyer", fareService.isVeryFrequentFlyer(12));
    }

    @Test
    public void testHM06_isVeryFrequentFlyer_True_Flights50() {
        assertTrue("50 flights should be very frequent flyer", fareService.isVeryFrequentFlyer(50));
    }

    // --- HM-07: hasMiddleIncome(income) - income > 20000 && income < 35000 ---

    @Test
    public void testHM07_hasMiddleIncome_False_Income0() {
        assertFalse("Income 0 should NOT be middle income", fareService.hasMiddleIncome(0));
    }

    @Test
    public void testHM07_hasMiddleIncome_False_Income20000() {
        assertFalse("Income 20000 should NOT be middle income (boundary)", fareService.hasMiddleIncome(20000));
    }

    @Test
    public void testHM07_hasMiddleIncome_True_Income20001() {
        assertTrue("Income 20001 should be middle income", fareService.hasMiddleIncome(20001));
    }

    @Test
    public void testHM07_hasMiddleIncome_True_Income30000() {
        assertTrue("Income 30000 should be middle income", fareService.hasMiddleIncome(30000));
    }

    @Test
    public void testHM07_hasMiddleIncome_True_Income34999() {
        assertTrue("Income 34999 should be middle income", fareService.hasMiddleIncome(34999));
    }

    @Test
    public void testHM07_hasMiddleIncome_False_Income35000() {
        assertFalse("Income 35000 should NOT be middle income (boundary)", fareService.hasMiddleIncome(35000));
    }

    @Test
    public void testHM07_hasMiddleIncome_False_Income50000() {
        assertFalse("Income 50000 should NOT be middle income", fareService.hasMiddleIncome(50000));
    }

    // --- HM-08: hasHighIncome(income) - income >= 35000 ---

    @Test
    public void testHM08_hasHighIncome_False_Income34999() {
        assertFalse("Income 34999 should NOT be high income", fareService.hasHighIncome(34999));
    }

    @Test
    public void testHM08_hasHighIncome_True_Income35000() {
        assertTrue("Income 35000 should be high income", fareService.hasHighIncome(35000));
    }

    @Test
    public void testHM08_hasHighIncome_True_Income100000() {
        assertTrue("Income 100000 should be high income", fareService.hasHighIncome(100000));
    }

    // --- HM-09: isEconomyClass(preferredClass) ---

    @Test
    public void testHM09_isEconomyClass_True() {
        assertTrue("ECONOMY should be economy class", fareService.isEconomyClass(CabinClass.ECONOMY));
    }

    @Test
    public void testHM09_isEconomyClass_False() {
        assertFalse("BUSINESS should NOT be economy class", fareService.isEconomyClass(CabinClass.BUSINESS));
    }

    // --- HM-10: isBusinessClass(preferredClass) ---

    @Test
    public void testHM10_isBusinessClass_True() {
        assertTrue("BUSINESS should be business class", fareService.isBusinessClass(CabinClass.BUSINESS));
    }

    @Test
    public void testHM10_isBusinessClass_False() {
        assertFalse("ECONOMY should NOT be business class", fareService.isBusinessClass(CabinClass.ECONOMY));
    }

    // --- HM-11: isEuropeDestination(preferredRegion) ---

    @Test
    public void testHM11_isEuropeDestination_True() {
        assertTrue("EUROPE should be Europe destination", fareService.isEuropeDestination(DestinationRegion.EUROPE));
    }

    @Test
    public void testHM11_isEuropeDestination_False_Asia() {
        assertFalse("ASIA should NOT be Europe destination", fareService.isEuropeDestination(DestinationRegion.ASIA));
    }

    @Test
    public void testHM11_isEuropeDestination_False_America() {
        assertFalse("AMERICA should NOT be Europe destination", fareService.isEuropeDestination(DestinationRegion.AMERICA));
    }

    @Test
    public void testHM11_isEuropeDestination_False_Other() {
        assertFalse("OTHER should NOT be Europe destination", fareService.isEuropeDestination(DestinationRegion.OTHER));
    }

    // --- HM-12: isLongHaulDestination(preferredRegion) - ASIA or AMERICA ---

    @Test
    public void testHM12_isLongHaulDestination_True_Asia() {
        assertTrue("ASIA should be long haul destination", fareService.isLongHaulDestination(DestinationRegion.ASIA));
    }

    @Test
    public void testHM12_isLongHaulDestination_True_America() {
        assertTrue("AMERICA should be long haul destination", fareService.isLongHaulDestination(DestinationRegion.AMERICA));
    }

    @Test
    public void testHM12_isLongHaulDestination_False_Europe() {
        assertFalse("EUROPE should NOT be long haul destination", fareService.isLongHaulDestination(DestinationRegion.EUROPE));
    }

    @Test
    public void testHM12_isLongHaulDestination_False_Other() {
        assertFalse("OTHER should NOT be long haul destination", fareService.isLongHaulDestination(DestinationRegion.OTHER));
    }

    // ========================================================================
    // INTEGRATION TESTS - Full determineFare workflow
    // ========================================================================

    @Test
    public void testIntegration_NoMatchingFare_AllConditionsFalse() {
        // No fare should match for elderly person with low flights
        FareOffer offer = fareService.determineFare(70, 1, false, false, 15000,
            CabinClass.ECONOMY, DestinationRegion.OTHER, false);
        assertNull("No fare should match for this profile", offer);
    }

    @Test
    public void testIntegration_PriorityOrder_PajarilloFirst() {
        // Minor with frequent flights should get Pajarillo (checked first)
        FareOffer offer = fareService.determineFare(17, 6, false, false, 0,
            CabinClass.ECONOMY, DestinationRegion.EUROPE, false);
        assertNotNull(offer);
        assertEquals("Pajarillo should be returned first", "Pajarillo", offer.getName());
    }

    @Test
    public void testIntegration_ChildrenVariant_DiscoverEurope() {
        // Mature adult with children should get "with Your Little Ones" variant
        FareOffer offerWithChildren = fareService.determineFare(30, 6, false, false, 25000,
            CabinClass.ECONOMY, DestinationRegion.EUROPE, true);
        assertNotNull(offerWithChildren);
        assertEquals("Discover Europe with Your Little Ones", offerWithChildren.getName());
        assertEquals(10.0, offerWithChildren.getDiscountPercentage(), 0.01);

        // Same profile without children
        FareOffer offerNoChildren = fareService.determineFare(30, 6, false, false, 25000,
            CabinClass.ECONOMY, DestinationRegion.EUROPE, false);
        assertNotNull(offerNoChildren);
        assertEquals("Discover Europe", offerNoChildren.getName());
        assertEquals(15.0, offerNoChildren.getDiscountPercentage(), 0.01);
    }

    @Test
    public void testIntegration_ChildrenVariant_DiscoverWorld() {
        // Mature adult with children should get "with Your Little Ones" variant
        FareOffer offerWithChildren = fareService.determineFare(35, 6, false, false, 40000,
            CabinClass.BUSINESS, DestinationRegion.ASIA, true);
        assertNotNull(offerWithChildren);
        assertEquals("Discover the World with Your Little Ones", offerWithChildren.getName());
        assertEquals(10.0, offerWithChildren.getDiscountPercentage(), 0.01);

        // Same profile without children
        FareOffer offerNoChildren = fareService.determineFare(35, 6, false, false, 40000,
            CabinClass.BUSINESS, DestinationRegion.ASIA, false);
        assertNotNull(offerNoChildren);
        assertEquals("Discover the World", offerNoChildren.getName());
        assertEquals(20.0, offerNoChildren.getDiscountPercentage(), 0.01);
    }

    @Test
    public void testIntegration_LivesWithParents_TravelWhileYouCan() {
        // Young worker living with parents
        FareOffer offer = fareService.determineFare(22, 3, false, true, 18000,
            CabinClass.ECONOMY, DestinationRegion.EUROPE, false);
        assertNotNull(offer);
        assertEquals("Travel While You Can", offer.getName());
        assertEquals(5.0, offer.getDiscountPercentage(), 0.01);
    }

    @Test
    public void testIntegration_Independent_DaringToLeaveNest() {
        // Young worker NOT living with parents
        FareOffer offer = fareService.determineFare(22, 3, false, false, 18000,
            CabinClass.ECONOMY, DestinationRegion.EUROPE, false);
        assertNotNull(offer);
        assertEquals("Daring to Leave the Nest", offer.getName());
        assertEquals(25.0, offer.getDiscountPercentage(), 0.01);
    }
}
