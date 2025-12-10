package ISO2.Exe2;

import ISO2.Exe2.model.*;
import ISO2.Exe2.service.FareService;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test suite for FareService based on problem.md specifications.
 * 
 * Coverage based on:
 * - Section 6: Each-Use Coverage (17 test cases)
 * - Section 8: Decision Coverage
 * - Section 9: MC/DC Coverage
 * 
 * Target: ≥80% code coverage using JaCoCo
 */
public class FareServiceTest {
    
    private FareService fareService;
    
    @Before
    public void setUp() {
        fareService = new FareService();
    }
    
    // ========================================================================
    // SECTION 6: EACH-USE COVERAGE TESTS (17 test cases)
    // Each value from the variable partitions is used at least once
    // ========================================================================
    
    @Test
    public void testEachUse_TC1_Pajarillo() {
        FareOffer result = fareService.determineFare(17, 6, false, false, 0,
                CabinClass.ECONOMY, DestinationRegion.OTHER, false);
        assertNotNull(result);
        assertEquals("Pajarillo", result.getName());
        assertEquals(10.0, result.getDiscountPercentage(), 0.01);
    }
    
    @Test
    public void testEachUse_TC2_Gorrion() {
        FareOffer result = fareService.determineFare(18, 12, true, false, 15000,
                CabinClass.ECONOMY, DestinationRegion.OTHER, false);
        assertNotNull(result);
        assertEquals("Gorrión", result.getName());
        assertEquals(15.0, result.getDiscountPercentage(), 0.01);
    }
    
    @Test
    public void testEachUse_TC3_TravelWhileYouCan() {
        FareOffer result = fareService.determineFare(25, 3, false, true, 18000,
                CabinClass.ECONOMY, DestinationRegion.OTHER, false);
        assertNotNull(result);
        assertEquals("Travel While You Can", result.getName());
        assertEquals(5.0, result.getDiscountPercentage(), 0.01);
    }
    
    @Test
    public void testEachUse_TC4_DaringToLeaveNest() {
        FareOffer result = fareService.determineFare(19, 5, false, false, 18000,
                CabinClass.ECONOMY, DestinationRegion.OTHER, false);
        assertNotNull(result);
        assertEquals("Daring to Leave the Nest", result.getName());
        assertEquals(25.0, result.getDiscountPercentage(), 0.01);
    }
    
    @Test
    public void testEachUse_TC5_DiscoverEurope() {
        FareOffer result = fareService.determineFare(26, 6, false, false, 20001,
                CabinClass.ECONOMY, DestinationRegion.EUROPE, false);
        assertNotNull(result);
        assertEquals("Discover Europe", result.getName());
        assertEquals(15.0, result.getDiscountPercentage(), 0.01);
    }
    
    @Test
    public void testEachUse_TC6_DiscoverEuropeWithChildren() {
        FareOffer result = fareService.determineFare(30, 7, false, false, 34999,
                CabinClass.ECONOMY, DestinationRegion.EUROPE, true);
        assertNotNull(result);
        assertEquals("Discover Europe with Your Little Ones", result.getName());
        assertEquals(10.0, result.getDiscountPercentage(), 0.01);
    }
    
    @Test
    public void testEachUse_TC7_DiscoverWorldAsia() {
        FareOffer result = fareService.determineFare(40, 6, false, false, 35000,
                CabinClass.BUSINESS, DestinationRegion.ASIA, false);
        assertNotNull(result);
        assertEquals("Discover the World", result.getName());
        assertEquals(20.0, result.getDiscountPercentage(), 0.01);
    }
    
    @Test
    public void testEachUse_TC8_DiscoverWorldAmericaWithChildren() {
        FareOffer result = fareService.determineFare(35, 8, false, false, 50000,
                CabinClass.BUSINESS, DestinationRegion.AMERICA, true);
        assertNotNull(result);
        assertEquals("Discover the World with Your Little Ones", result.getName());
        assertEquals(10.0, result.getDiscountPercentage(), 0.01);
    }
    
    @Test
    public void testEachUse_TC9_NoMatch() {
        FareOffer result = fareService.determineFare(30, 2, false, false, 15000,
                CabinClass.ECONOMY, DestinationRegion.OTHER, false);
        assertNull(result);
    }
    
    @Test
    public void testEachUse_TC10_AgeZero() {
        FareOffer result = fareService.determineFare(0, 6, false, false, 0,
                CabinClass.ECONOMY, DestinationRegion.OTHER, false);
        assertNotNull(result);
        assertEquals("Pajarillo", result.getName());
    }
    
    @Test
    public void testEachUse_TC11_ZeroFlights() {
        FareOffer result = fareService.determineFare(30, 0, false, false, 25000,
                CabinClass.ECONOMY, DestinationRegion.EUROPE, false);
        assertNull(result);
    }
    
    @Test
    public void testEachUse_TC12_YoungWorker() {
        FareOffer result = fareService.determineFare(24, 4, false, true, 18000,
                CabinClass.ECONOMY, DestinationRegion.OTHER, false);
        assertNotNull(result);
        assertEquals("Travel While You Can", result.getName());
    }
    
    @Test
    public void testEachUse_TC13_Flights11() {
        FareOffer result = fareService.checkGorrionFare(20, 11, true, CabinClass.ECONOMY);
        assertNull(result);
    }
    
    @Test
    public void testEachUse_TC14_Flights13() {
        FareOffer result = fareService.checkGorrionFare(20, 13, true, CabinClass.ECONOMY);
        assertNotNull(result);
        assertEquals("Gorrión", result.getName());
    }
    
    @Test
    public void testEachUse_TC15_Income19999() {
        assertFalse(fareService.hasMiddleIncome(19999));
    }
    
    @Test
    public void testEachUse_TC16_Income35001() {
        assertTrue(fareService.hasHighIncome(35001));
    }
    
    @Test
    public void testEachUse_TC17_DestinationOther() {
        FareOffer result = fareService.checkDiscoverWorldFares(35, 50000, 6,
                CabinClass.BUSINESS, DestinationRegion.OTHER, false);
        assertNull(result);
    }
    
    // ========================================================================
    // SECTION 8: DECISION COVERAGE TESTS
    // ========================================================================
    
    // Decision 1: checkPajarilloFare
    @Test
    public void testDecision1_TT_Pajarillo() {
        FareOffer result = fareService.checkPajarilloFare(17, 6);
        assertNotNull(result);
        assertEquals("Pajarillo", result.getName());
    }
    
    @Test
    public void testDecision1_FT_Null() {
        FareOffer result = fareService.checkPajarilloFare(18, 6);
        assertNull(result);
    }
    
    @Test
    public void testDecision1_TF_Null() {
        FareOffer result = fareService.checkPajarilloFare(17, 5);
        assertNull(result);
    }
    
    @Test
    public void testDecision1_FF_Null() {
        FareOffer result = fareService.checkPajarilloFare(18, 5);
        assertNull(result);
    }
    
    // Decision 2: checkGorrionFare
    @Test
    public void testDecision2_TTTT_Gorrion() {
        FareOffer result = fareService.checkGorrionFare(20, 12, true, CabinClass.ECONOMY);
        assertNotNull(result);
        assertEquals("Gorrión", result.getName());
    }
    
    @Test
    public void testDecision2_FTTT_Null() {
        FareOffer result = fareService.checkGorrionFare(17, 12, true, CabinClass.ECONOMY);
        assertNull(result);
    }
    
    @Test
    public void testDecision2_TFTT_Null() {
        FareOffer result = fareService.checkGorrionFare(20, 12, false, CabinClass.ECONOMY);
        assertNull(result);
    }
    
    @Test
    public void testDecision2_TTFT_Null() {
        FareOffer result = fareService.checkGorrionFare(20, 12, true, CabinClass.BUSINESS);
        assertNull(result);
    }
    
    @Test
    public void testDecision2_TTTF_Null() {
        FareOffer result = fareService.checkGorrionFare(20, 11, true, CabinClass.ECONOMY);
        assertNull(result);
    }
    
    // Decision 3: checkYoungTravelerFares
    @Test
    public void testDecision3_TTTT_WithParents() {
        FareOffer result = fareService.checkYoungTravelerFares(20, 3, false, true, CabinClass.ECONOMY);
        assertNotNull(result);
        assertEquals("Travel While You Can", result.getName());
    }
    
    @Test
    public void testDecision3_TTTT_WithoutParents() {
        FareOffer result = fareService.checkYoungTravelerFares(20, 3, false, false, CabinClass.ECONOMY);
        assertNotNull(result);
        assertEquals("Daring to Leave the Nest", result.getName());
    }
    
    @Test
    public void testDecision3_FTTT_Null() {
        FareOffer result = fareService.checkYoungTravelerFares(26, 3, false, true, CabinClass.ECONOMY);
        assertNull(result);
    }
    
    @Test
    public void testDecision3_TFTT_Null() {
        FareOffer result = fareService.checkYoungTravelerFares(20, 3, true, true, CabinClass.ECONOMY);
        assertNull(result);
    }
    
    @Test
    public void testDecision3_TTFT_Null() {
        FareOffer result = fareService.checkYoungTravelerFares(20, 2, false, true, CabinClass.ECONOMY);
        assertNull(result);
    }
    
    @Test
    public void testDecision3_TTTF_Null() {
        FareOffer result = fareService.checkYoungTravelerFares(20, 3, false, true, CabinClass.BUSINESS);
        assertNull(result);
    }
    
    // Decision 4: checkDiscoverEuropeFares
    @Test
    public void testDecision4_TTTTT_WithChildren() {
        FareOffer result = fareService.checkDiscoverEuropeFares(30, 25000, 6,
                CabinClass.ECONOMY, DestinationRegion.EUROPE, true);
        assertNotNull(result);
        assertEquals("Discover Europe with Your Little Ones", result.getName());
    }
    
    @Test
    public void testDecision4_TTTTT_WithoutChildren() {
        FareOffer result = fareService.checkDiscoverEuropeFares(30, 25000, 6,
                CabinClass.ECONOMY, DestinationRegion.EUROPE, false);
        assertNotNull(result);
        assertEquals("Discover Europe", result.getName());
    }
    
    @Test
    public void testDecision4_FTTTT_Null() {
        FareOffer result = fareService.checkDiscoverEuropeFares(25, 25000, 6,
                CabinClass.ECONOMY, DestinationRegion.EUROPE, false);
        assertNull(result);
    }
    
    @Test
    public void testDecision4_TFTTT_Null() {
        FareOffer result = fareService.checkDiscoverEuropeFares(30, 20000, 6,
                CabinClass.ECONOMY, DestinationRegion.EUROPE, false);
        assertNull(result);
    }
    
    @Test
    public void testDecision4_TTFTT_Null() {
        FareOffer result = fareService.checkDiscoverEuropeFares(30, 25000, 5,
                CabinClass.ECONOMY, DestinationRegion.EUROPE, false);
        assertNull(result);
    }
    
    @Test
    public void testDecision4_TTTFT_Null() {
        FareOffer result = fareService.checkDiscoverEuropeFares(30, 25000, 6,
                CabinClass.BUSINESS, DestinationRegion.EUROPE, false);
        assertNull(result);
    }
    
    @Test
    public void testDecision4_TTTTF_Null() {
        FareOffer result = fareService.checkDiscoverEuropeFares(30, 25000, 6,
                CabinClass.ECONOMY, DestinationRegion.ASIA, false);
        assertNull(result);
    }
    
    // Decision 5: checkDiscoverWorldFares
    @Test
    public void testDecision5_TTTTT_Asia() {
        FareOffer result = fareService.checkDiscoverWorldFares(30, 40000, 6,
                CabinClass.BUSINESS, DestinationRegion.ASIA, false);
        assertNotNull(result);
        assertEquals("Discover the World", result.getName());
    }
    
    @Test
    public void testDecision5_TTTTT_America() {
        FareOffer result = fareService.checkDiscoverWorldFares(30, 40000, 6,
                CabinClass.BUSINESS, DestinationRegion.AMERICA, false);
        assertNotNull(result);
        assertEquals("Discover the World", result.getName());
    }
    
    @Test
    public void testDecision5_TTTTT_WithChildren() {
        FareOffer result = fareService.checkDiscoverWorldFares(30, 40000, 6,
                CabinClass.BUSINESS, DestinationRegion.ASIA, true);
        assertNotNull(result);
        assertEquals("Discover the World with Your Little Ones", result.getName());
    }
    
    @Test
    public void testDecision5_FTTTT_Null() {
        FareOffer result = fareService.checkDiscoverWorldFares(25, 40000, 6,
                CabinClass.BUSINESS, DestinationRegion.ASIA, false);
        assertNull(result);
    }
    
    @Test
    public void testDecision5_TFTTT_Null() {
        FareOffer result = fareService.checkDiscoverWorldFares(30, 34999, 6,
                CabinClass.BUSINESS, DestinationRegion.ASIA, false);
        assertNull(result);
    }
    
    @Test
    public void testDecision5_TTFTT_Null() {
        FareOffer result = fareService.checkDiscoverWorldFares(30, 40000, 5,
                CabinClass.BUSINESS, DestinationRegion.ASIA, false);
        assertNull(result);
    }
    
    @Test
    public void testDecision5_TTTFT_Null() {
        FareOffer result = fareService.checkDiscoverWorldFares(30, 40000, 6,
                CabinClass.ECONOMY, DestinationRegion.ASIA, false);
        assertNull(result);
    }
    
    @Test
    public void testDecision5_TTTTF_Null() {
        FareOffer result = fareService.checkDiscoverWorldFares(30, 40000, 6,
                CabinClass.BUSINESS, DestinationRegion.EUROPE, false);
        assertNull(result);
    }
    
    // ========================================================================
    // SECTION 9: MC/DC COVERAGE TESTS
    // ========================================================================
    
    // MC/DC Decision 1
    @Test
    public void testMCDC1_BaseCase() {
        FareOffer result = fareService.checkPajarilloFare(17, 6);
        assertNotNull(result);
    }
    
    @Test
    public void testMCDC1_A_Independent() {
        FareOffer result = fareService.checkPajarilloFare(18, 6);
        assertNull(result);
    }
    
    @Test
    public void testMCDC1_B_Independent() {
        FareOffer result = fareService.checkPajarilloFare(17, 5);
        assertNull(result);
    }
    
    // MC/DC Decision 2
    @Test
    public void testMCDC2_BaseCase() {
        FareOffer result = fareService.checkGorrionFare(20, 12, true, CabinClass.ECONOMY);
        assertNotNull(result);
    }
    
    @Test
    public void testMCDC2_A_Independent() {
        FareOffer result = fareService.checkGorrionFare(26, 12, true, CabinClass.ECONOMY);
        assertNull(result);
    }
    
    @Test
    public void testMCDC2_B_Independent() {
        FareOffer result = fareService.checkGorrionFare(20, 12, false, CabinClass.ECONOMY);
        assertNull(result);
    }
    
    @Test
    public void testMCDC2_C_Independent() {
        FareOffer result = fareService.checkGorrionFare(20, 12, true, CabinClass.BUSINESS);
        assertNull(result);
    }
    
    @Test
    public void testMCDC2_D_Independent() {
        FareOffer result = fareService.checkGorrionFare(20, 11, true, CabinClass.ECONOMY);
        assertNull(result);
    }
    
    // MC/DC Decision 3
    @Test
    public void testMCDC3_BaseCase() {
        FareOffer result = fareService.checkYoungTravelerFares(20, 3, false, true, CabinClass.ECONOMY);
        assertNotNull(result);
    }
    
    @Test
    public void testMCDC3_A_Independent() {
        FareOffer result = fareService.checkYoungTravelerFares(17, 3, false, true, CabinClass.ECONOMY);
        assertNull(result);
    }
    
    @Test
    public void testMCDC3_B_Independent() {
        FareOffer result = fareService.checkYoungTravelerFares(20, 3, true, true, CabinClass.ECONOMY);
        assertNull(result);
    }
    
    @Test
    public void testMCDC3_C_Independent() {
        FareOffer result = fareService.checkYoungTravelerFares(20, 2, false, true, CabinClass.ECONOMY);
        assertNull(result);
    }
    
    @Test
    public void testMCDC3_D_Independent() {
        FareOffer result = fareService.checkYoungTravelerFares(20, 3, false, true, CabinClass.BUSINESS);
        assertNull(result);
    }
    
    // MC/DC Decision 4
    @Test
    public void testMCDC4_BaseCase() {
        FareOffer result = fareService.checkDiscoverEuropeFares(30, 25000, 6,
                CabinClass.ECONOMY, DestinationRegion.EUROPE, false);
        assertNotNull(result);
    }
    
    @Test
    public void testMCDC4_A_Independent() {
        FareOffer result = fareService.checkDiscoverEuropeFares(25, 25000, 6,
                CabinClass.ECONOMY, DestinationRegion.EUROPE, false);
        assertNull(result);
    }
    
    @Test
    public void testMCDC4_B_Independent() {
        FareOffer result = fareService.checkDiscoverEuropeFares(30, 20000, 6,
                CabinClass.ECONOMY, DestinationRegion.EUROPE, false);
        assertNull(result);
    }
    
    @Test
    public void testMCDC4_C_Independent() {
        FareOffer result = fareService.checkDiscoverEuropeFares(30, 25000, 5,
                CabinClass.ECONOMY, DestinationRegion.EUROPE, false);
        assertNull(result);
    }
    
    @Test
    public void testMCDC4_D_Independent() {
        FareOffer result = fareService.checkDiscoverEuropeFares(30, 25000, 6,
                CabinClass.BUSINESS, DestinationRegion.EUROPE, false);
        assertNull(result);
    }
    
    @Test
    public void testMCDC4_E_Independent() {
        FareOffer result = fareService.checkDiscoverEuropeFares(30, 25000, 6,
                CabinClass.ECONOMY, DestinationRegion.ASIA, false);
        assertNull(result);
    }
    
    // MC/DC Decision 5
    @Test
    public void testMCDC5_BaseCase() {
        FareOffer result = fareService.checkDiscoverWorldFares(30, 40000, 6,
                CabinClass.BUSINESS, DestinationRegion.ASIA, false);
        assertNotNull(result);
    }
    
    @Test
    public void testMCDC5_A_Independent() {
        FareOffer result = fareService.checkDiscoverWorldFares(25, 40000, 6,
                CabinClass.BUSINESS, DestinationRegion.ASIA, false);
        assertNull(result);
    }
    
    @Test
    public void testMCDC5_B_Independent() {
        FareOffer result = fareService.checkDiscoverWorldFares(30, 34999, 6,
                CabinClass.BUSINESS, DestinationRegion.ASIA, false);
        assertNull(result);
    }
    
    @Test
    public void testMCDC5_C_Independent() {
        FareOffer result = fareService.checkDiscoverWorldFares(30, 40000, 5,
                CabinClass.BUSINESS, DestinationRegion.ASIA, false);
        assertNull(result);
    }
    
    @Test
    public void testMCDC5_D_Independent() {
        FareOffer result = fareService.checkDiscoverWorldFares(30, 40000, 6,
                CabinClass.ECONOMY, DestinationRegion.ASIA, false);
        assertNull(result);
    }
    
    @Test
    public void testMCDC5_E_Independent() {
        FareOffer result = fareService.checkDiscoverWorldFares(30, 40000, 6,
                CabinClass.BUSINESS, DestinationRegion.EUROPE, false);
        assertNull(result);
    }
    
    // ========================================================================
    // HELPER METHOD BOUNDARY TESTS (Section 4 partitions)
    // ========================================================================
    
    @Test
    public void testBoundary_isMinor_Age17() {
        assertTrue(fareService.isMinor(17));
    }
    
    @Test
    public void testBoundary_isMinor_Age18() {
        assertFalse(fareService.isMinor(18));
    }
    
    @Test
    public void testBoundary_isYoungAdult_Age18() {
        assertTrue(fareService.isYoungAdult(18));
    }
    
    @Test
    public void testBoundary_isYoungAdult_Age25() {
        assertTrue(fareService.isYoungAdult(25));
    }
    
    @Test
    public void testBoundary_isYoungAdult_Age26() {
        assertFalse(fareService.isYoungAdult(26));
    }
    
    @Test
    public void testBoundary_isMatureAdult_Age25() {
        assertFalse(fareService.isMatureAdult(25));
    }
    
    @Test
    public void testBoundary_isMatureAdult_Age26() {
        assertTrue(fareService.isMatureAdult(26));
    }
    
    @Test
    public void testBoundary_isRegularFlyer_2() {
        assertFalse(fareService.isRegularFlyer(2));
    }
    
    @Test
    public void testBoundary_isRegularFlyer_3() {
        assertTrue(fareService.isRegularFlyer(3));
    }
    
    @Test
    public void testBoundary_isFrequentFlyer_5() {
        assertFalse(fareService.isFrequentFlyer(5));
    }
    
    @Test
    public void testBoundary_isFrequentFlyer_6() {
        assertTrue(fareService.isFrequentFlyer(6));
    }
    
    @Test
    public void testBoundary_isVeryFrequentFlyer_11() {
        assertFalse(fareService.isVeryFrequentFlyer(11));
    }
    
    @Test
    public void testBoundary_isVeryFrequentFlyer_12() {
        assertTrue(fareService.isVeryFrequentFlyer(12));
    }
    
    @Test
    public void testBoundary_hasMiddleIncome_20000() {
        assertFalse(fareService.hasMiddleIncome(20000));
    }
    
    @Test
    public void testBoundary_hasMiddleIncome_20001() {
        assertTrue(fareService.hasMiddleIncome(20001));
    }
    
    @Test
    public void testBoundary_hasMiddleIncome_34999() {
        assertTrue(fareService.hasMiddleIncome(34999));
    }
    
    @Test
    public void testBoundary_hasMiddleIncome_35000() {
        assertFalse(fareService.hasMiddleIncome(35000));
    }
    
    @Test
    public void testBoundary_hasHighIncome_34999() {
        assertFalse(fareService.hasHighIncome(34999));
    }
    
    @Test
    public void testBoundary_hasHighIncome_35000() {
        assertTrue(fareService.hasHighIncome(35000));
    }
    
    @Test
    public void testPartition_isEconomyClass() {
        assertTrue(fareService.isEconomyClass(CabinClass.ECONOMY));
        assertFalse(fareService.isEconomyClass(CabinClass.BUSINESS));
    }
    
    @Test
    public void testPartition_isBusinessClass() {
        assertTrue(fareService.isBusinessClass(CabinClass.BUSINESS));
        assertFalse(fareService.isBusinessClass(CabinClass.ECONOMY));
    }
    
    @Test
    public void testPartition_isEuropeDestination() {
        assertTrue(fareService.isEuropeDestination(DestinationRegion.EUROPE));
        assertFalse(fareService.isEuropeDestination(DestinationRegion.ASIA));
    }
    
    @Test
    public void testPartition_isLongHaulDestination() {
        assertTrue(fareService.isLongHaulDestination(DestinationRegion.ASIA));
        assertTrue(fareService.isLongHaulDestination(DestinationRegion.AMERICA));
        assertFalse(fareService.isLongHaulDestination(DestinationRegion.EUROPE));
        assertFalse(fareService.isLongHaulDestination(DestinationRegion.OTHER));
    }
}
