package ISO2.Exe2;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

/**
 * Test suite for App class (main application).
 * 
 * Tests cover:
 * - Valid input scenarios for each fare type
 * - No match scenarios
 * - Input validation errors (negative values)
 * - Invalid enum values
 * 
 * Uses System.in/out redirection to simulate user input and capture output.
 */
public class AppTest {
    
    private final InputStream originalIn = System.in;
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;
    private ByteArrayOutputStream outContent;
    private ByteArrayOutputStream errContent;
    
    @Before
    public void setUp() {
        outContent = new ByteArrayOutputStream();
        errContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }
    
    @After
    public void tearDown() {
        System.setIn(originalIn);
        System.setOut(originalOut);
        System.setErr(originalErr);
    }
    
    /**
     * Simulates user input by setting System.in to a ByteArrayInputStream.
     * Input format: age, flightsPerYear, isStudent, livesWithParents, income, preferredClass, preferredRegion, travelsWithChildren
     */
    private void provideInput(String data) {
        ByteArrayInputStream testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
    }
    
    // ========================================================================
    // VALID INPUT SCENARIOS - Each Fare Type
    // ========================================================================
    
    /**
     * TC_APP_01: Pajarillo fare - minor with frequent flights
     * age=17, flights=6, not student, not with parents, income=0, ECONOMY, OTHER, no children
     */
    @Test
    public void testApp_Pajarillo() {
        String input = "17\n6\nfalse\nfalse\n0\nECONOMY\nOTHER\nfalse\n";
        provideInput(input);
        
        App.main(new String[]{});
        
        String output = outContent.toString();
        assertTrue("Should display Pajarillo fare", output.contains("Pajarillo"));
        assertTrue("Should display 10% discount", output.contains("10.0%"));
    }
    
    /**
     * TC_APP_02: Gorrion fare - young adult student with very frequent flights
     * age=20, flights=12, student=true, not with parents, income=0, ECONOMY, OTHER, no children
     */
    @Test
    public void testApp_Gorrion() {
        String input = "20\n12\ntrue\nfalse\n0\nECONOMY\nOTHER\nfalse\n";
        provideInput(input);
        
        App.main(new String[]{});
        
        String output = outContent.toString();
        assertTrue("Should display Gorrion fare", output.contains("Gorrión"));
        assertTrue("Should display 15% discount", output.contains("15.0%"));
    }
    
    /**
     * TC_APP_03: Travel While You Can fare - young adult living with parents
     * age=22, flights=4, not student, lives with parents, income=15000, ECONOMY, OTHER, no children
     */
    @Test
    public void testApp_TravelWhileYouCan() {
        String input = "22\n4\nfalse\ntrue\n15000\nECONOMY\nOTHER\nfalse\n";
        provideInput(input);
        
        App.main(new String[]{});
        
        String output = outContent.toString();
        assertTrue("Should display Travel While You Can fare", output.contains("Travel While You Can"));
        assertTrue("Should display 5% discount", output.contains("5.0%"));
    }
    
    /**
     * TC_APP_04: Daring to Leave the Nest fare - young adult not living with parents
     * age=22, flights=4, not student, not with parents, income=15000, ECONOMY, OTHER, no children
     */
    @Test
    public void testApp_DaringToLeaveTheNest() {
        String input = "22\n4\nfalse\nfalse\n15000\nECONOMY\nOTHER\nfalse\n";
        provideInput(input);
        
        App.main(new String[]{});
        
        String output = outContent.toString();
        assertTrue("Should display Daring to Leave the Nest fare", output.contains("Daring to Leave the Nest"));
        assertTrue("Should display 25% discount", output.contains("25.0%"));
    }
    
    /**
     * TC_APP_05: Discover Europe fare - mature adult with middle income
     * age=30, flights=6, not student, not with parents, income=25000, ECONOMY, EUROPE, no children
     */
    @Test
    public void testApp_DiscoverEurope() {
        String input = "30\n6\nfalse\nfalse\n25000\nECONOMY\nEUROPE\nfalse\n";
        provideInput(input);
        
        App.main(new String[]{});
        
        String output = outContent.toString();
        assertTrue("Should display Discover Europe fare", output.contains("Discover Europe"));
        assertTrue("Should display 15% discount", output.contains("15.0%"));
    }
    
    /**
     * TC_APP_06: Discover Europe with Your Little Ones fare - mature adult with children
     * age=30, flights=6, not student, not with parents, income=25000, ECONOMY, EUROPE, with children
     */
    @Test
    public void testApp_DiscoverEuropeWithChildren() {
        String input = "30\n6\nfalse\nfalse\n25000\nECONOMY\nEUROPE\ntrue\n";
        provideInput(input);
        
        App.main(new String[]{});
        
        String output = outContent.toString();
        assertTrue("Should display Discover Europe with Your Little Ones fare", 
                output.contains("Discover Europe with Your Little Ones"));
        assertTrue("Should display 10% discount", output.contains("10.0%"));
    }
    
    /**
     * TC_APP_07: Discover the World fare - mature adult with high income, business class
     * age=35, flights=6, not student, not with parents, income=40000, BUSINESS, ASIA, no children
     */
    @Test
    public void testApp_DiscoverWorld() {
        String input = "35\n6\nfalse\nfalse\n40000\nBUSINESS\nASIA\nfalse\n";
        provideInput(input);
        
        App.main(new String[]{});
        
        String output = outContent.toString();
        assertTrue("Should display Discover the World fare", output.contains("Discover the World"));
        assertTrue("Should display 20% discount", output.contains("20.0%"));
    }
    
    /**
     * TC_APP_08: Discover the World with Your Little Ones fare - business class with children
     * age=35, flights=6, not student, not with parents, income=40000, BUSINESS, AMERICA, with children
     */
    @Test
    public void testApp_DiscoverWorldWithChildren() {
        String input = "35\n6\nfalse\nfalse\n40000\nBUSINESS\nAMERICA\ntrue\n";
        provideInput(input);
        
        App.main(new String[]{});
        
        String output = outContent.toString();
        assertTrue("Should display Discover the World with Your Little Ones fare", 
                output.contains("Discover the World with Your Little Ones"));
        assertTrue("Should display 10% discount", output.contains("10.0%"));
    }
    
    // ========================================================================
    // NO MATCH SCENARIOS
    // ========================================================================
    
    /**
     * TC_APP_09: No special offer - customer doesn't match any fare criteria
     * age=30, flights=2, not student, not with parents, income=15000, ECONOMY, OTHER, no children
     */
    @Test
    public void testApp_NoSpecialOffer() {
        String input = "30\n2\nfalse\nfalse\n15000\nECONOMY\nOTHER\nfalse\n";
        provideInput(input);
        
        App.main(new String[]{});
        
        String output = outContent.toString();
        assertTrue("Should display no special offer message", 
                output.contains("NO SPECIAL OFFER AVAILABLE"));
    }
    
    /**
     * TC_APP_10: No match - adult with low flights in OTHER region
     * age=40, flights=5, not student, not with parents, income=50000, BUSINESS, OTHER, no children
     */
    @Test
    public void testApp_NoMatchHighIncomeOtherRegion() {
        String input = "40\n5\nfalse\nfalse\n50000\nBUSINESS\nOTHER\nfalse\n";
        provideInput(input);
        
        App.main(new String[]{});
        
        String output = outContent.toString();
        assertTrue("Should display no special offer message", 
                output.contains("NO SPECIAL OFFER AVAILABLE"));
    }
    
    // ========================================================================
    // INPUT VALIDATION ERROR SCENARIOS
    // ========================================================================
    
    /**
     * TC_APP_11: Negative age validation error
     * age=-1 should trigger InputException
     */
    @Test
    public void testApp_NegativeAgeError() {
        String input = "-1\n";
        provideInput(input);
        
        App.main(new String[]{});
        
        String errorOutput = errContent.toString();
        assertTrue("Should display error for negative age", 
                errorOutput.contains("Error") || errorOutput.contains("negative"));
    }
    
    /**
     * TC_APP_12: Negative flights validation error
     * age=30, flights=-1 should trigger InputException
     */
    @Test
    public void testApp_NegativeFlightsError() {
        String input = "30\n-1\n";
        provideInput(input);
        
        App.main(new String[]{});
        
        String errorOutput = errContent.toString();
        assertTrue("Should display error for negative flights", 
                errorOutput.contains("Error") || errorOutput.contains("negative"));
    }
    
    /**
     * TC_APP_13: Negative income validation error
     * All valid until income=-1000
     */
    @Test
    public void testApp_NegativeIncomeError() {
        String input = "30\n6\nfalse\nfalse\n-1000\n";
        provideInput(input);
        
        App.main(new String[]{});
        
        String errorOutput = errContent.toString();
        assertTrue("Should display error for negative income", 
                errorOutput.contains("Error") || errorOutput.contains("negative"));
    }
    
    // ========================================================================
    // ENUM PARSING SCENARIOS
    // ========================================================================
    
    /**
     * TC_APP_14: Invalid CabinClass enum value
     * Should trigger IllegalArgumentException
     */
    @Test
    public void testApp_InvalidCabinClass() {
        String input = "30\n6\nfalse\nfalse\n25000\nFIRST\n";
        provideInput(input);
        
        App.main(new String[]{});
        
        String errorOutput = errContent.toString();
        assertTrue("Should display error for invalid class", 
                errorOutput.contains("Error") || errorOutput.contains("Invalid"));
    }
    
    /**
     * TC_APP_15: Invalid DestinationRegion enum value
     * Should trigger IllegalArgumentException
     */
    @Test
    public void testApp_InvalidDestinationRegion() {
        String input = "30\n6\nfalse\nfalse\n25000\nECONOMY\nAFRICA\n";
        provideInput(input);
        
        App.main(new String[]{});
        
        String errorOutput = errContent.toString();
        assertTrue("Should display error for invalid region", 
                errorOutput.contains("Error") || errorOutput.contains("Invalid"));
    }
    
    // ========================================================================
    // CASE SENSITIVITY AND INPUT FORMAT
    // ========================================================================
    
    /**
     * TC_APP_16: Lowercase class input - should work due to toUpperCase()
     * age=17, flights=6, ECONOMY lowercase
     */
    @Test
    public void testApp_LowercaseClassInput() {
        String input = "17\n6\nfalse\nfalse\n0\neconomy\nother\nfalse\n";
        provideInput(input);
        
        App.main(new String[]{});
        
        String output = outContent.toString();
        assertTrue("Should handle lowercase input", output.contains("Pajarillo"));
    }
    
    /**
     * TC_APP_17: Mixed case input
     * Testing EcOnOmY and EuRoPe
     */
    @Test
    public void testApp_MixedCaseInput() {
        String input = "30\n6\nfalse\nfalse\n25000\nEcOnOmY\nEuRoPe\nfalse\n";
        provideInput(input);
        
        App.main(new String[]{});
        
        String output = outContent.toString();
        assertTrue("Should handle mixed case input", output.contains("Discover Europe"));
    }
    
    // ========================================================================
    // BOUNDARY VALUE TESTS FOR APP
    // ========================================================================
    
    /**
     * TC_APP_18: Age boundary - exactly 18 (young adult boundary)
     * age=18, student, very frequent flyer for Gorrion
     */
    @Test
    public void testApp_AgeBoundary18() {
        String input = "18\n12\ntrue\nfalse\n0\nECONOMY\nOTHER\nfalse\n";
        provideInput(input);
        
        App.main(new String[]{});
        
        String output = outContent.toString();
        assertTrue("Age 18 should qualify for Gorrion", output.contains("Gorrión"));
    }
    
    /**
     * TC_APP_19: Age boundary - exactly 25 (young adult upper boundary)
     * age=25, not student, regular flyer, lives with parents
     */
    @Test
    public void testApp_AgeBoundary25() {
        String input = "25\n4\nfalse\ntrue\n15000\nECONOMY\nOTHER\nfalse\n";
        provideInput(input);
        
        App.main(new String[]{});
        
        String output = outContent.toString();
        assertTrue("Age 25 should qualify for Travel While You Can", 
                output.contains("Travel While You Can"));
    }
    
    /**
     * TC_APP_20: Age boundary - exactly 26 (mature adult boundary)
     * age=26, middle income, frequent flyer, economy, Europe
     */
    @Test
    public void testApp_AgeBoundary26() {
        String input = "26\n6\nfalse\nfalse\n25000\nECONOMY\nEUROPE\nfalse\n";
        provideInput(input);
        
        App.main(new String[]{});
        
        String output = outContent.toString();
        assertTrue("Age 26 should qualify for Discover Europe", 
                output.contains("Discover Europe"));
    }
    
    /**
     * TC_APP_21: Income boundary - exactly 20001 (middle income lower boundary)
     */
    @Test
    public void testApp_IncomeBoundary20001() {
        String input = "30\n6\nfalse\nfalse\n20001\nECONOMY\nEUROPE\nfalse\n";
        provideInput(input);
        
        App.main(new String[]{});
        
        String output = outContent.toString();
        assertTrue("Income 20001 should qualify for Discover Europe", 
                output.contains("Discover Europe"));
    }
    
    /**
     * TC_APP_22: Income boundary - exactly 35000 (high income boundary)
     */
    @Test
    public void testApp_IncomeBoundary35000() {
        String input = "30\n6\nfalse\nfalse\n35000\nBUSINESS\nASIA\nfalse\n";
        provideInput(input);
        
        App.main(new String[]{});
        
        String output = outContent.toString();
        assertTrue("Income 35000 should qualify for Discover the World", 
                output.contains("Discover the World"));
    }
    
    /**
     * TC_APP_23: Flights boundary - exactly 3 (regular flyer)
     */
    @Test
    public void testApp_FlightsBoundary3() {
        String input = "22\n3\nfalse\nfalse\n15000\nECONOMY\nOTHER\nfalse\n";
        provideInput(input);
        
        App.main(new String[]{});
        
        String output = outContent.toString();
        assertTrue("3 flights should qualify for Daring to Leave the Nest", 
                output.contains("Daring to Leave the Nest"));
    }
    
    /**
     * TC_APP_24: Flights boundary - exactly 6 (frequent flyer)
     */
    @Test
    public void testApp_FlightsBoundary6() {
        String input = "17\n6\nfalse\nfalse\n0\nECONOMY\nOTHER\nfalse\n";
        provideInput(input);
        
        App.main(new String[]{});
        
        String output = outContent.toString();
        assertTrue("6 flights should qualify for Pajarillo", 
                output.contains("Pajarillo"));
    }
    
    /**
     * TC_APP_25: Flights boundary - exactly 12 (very frequent flyer)
     */
    @Test
    public void testApp_FlightsBoundary12() {
        String input = "20\n12\ntrue\nfalse\n0\nECONOMY\nOTHER\nfalse\n";
        provideInput(input);
        
        App.main(new String[]{});
        
        String output = outContent.toString();
        assertTrue("12 flights should qualify for Gorrion", 
                output.contains("Gorrión"));
    }
}
