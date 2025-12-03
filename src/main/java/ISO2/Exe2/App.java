package ISO2.Exe2;

import ISO2.Exe2.model.CabinClass;
import ISO2.Exe2.model.DestinationRegion;
import ISO2.Exe2.model.FareOffer;
import ISO2.Exe2.service.FareService;

import service.InputService;
import exception.InputException;
import parser.IntegerParser;
import parser.DoubleParser;
import parser.BooleanParser;

/**
 * Main application for Airline Fare Calculator.
 * Simplified version that works directly with primitive types.
 */
public class App {
    public static void main(String[] args) {
        
        try {
            // Read customer data
            CustomerData data = readCustomerData();
            
            // Create fare service
            FareService fareService = new FareService();
            
            // Determine the best fare
            FareOffer offer = fareService.determineFare(
                data.age,
                data.flightsPerYear,
                data.isStudent,
                data.livesWithParents,
                data.income,
                data.preferredClass,
                data.preferredRegion,
                data.travelsWithChildren
            );
            
            // Display result
            System.out.println("\n" + "═".repeat(45));
            if (offer != null) {
                System.out.println("✅ FARE OFFER FOUND!");
                System.out.println("═".repeat(45));
                System.out.println("Fare Name: " + offer.getName());
                System.out.println("Discount:  " + offer.getDiscountPercentage() + "%");
                System.out.println("═".repeat(45));
            } else {
                System.out.println("ℹ️  NO SPECIAL OFFER AVAILABLE");
                System.out.println("═".repeat(45));
                System.out.println("This customer profile does not match");
                System.out.println("any of our promotional fares.");
                System.out.println("═".repeat(45));
            }
            
        } catch (InputException e) {
            System.err.println("\n❌ Error reading input: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println("\n❌ Invalid input value: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("\n❌ Unexpected error: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * Reads customer data from user input.
     */
    private static CustomerData readCustomerData() throws InputException {
        InputService input = new InputService();
        CustomerData data = new CustomerData();
        
        System.out.println("Please enter the following details:");
        System.out.println();
        
        // Read age
        data.age = input.readWithParser("Age (years): ", new IntegerParser());
        
        // Read flights per year
        data.flightsPerYear = input.readWithParser("Flights per year: ", new IntegerParser());
        
        // Read student status
        data.isStudent = input.readWithParser("Is student? (true/false): ", new BooleanParser());
        
        // Read living situation
        data.livesWithParents = input.readWithParser("Lives with parents? (true/false): ", new BooleanParser());
        
        // Read income
        data.income = input.readWithParser("Annual income (€): ", new DoubleParser());
        
        // Read preferred class
        String classStr = input.readString("Preferred Class (ECONOMY/BUSINESS): ").toUpperCase().trim();
        data.preferredClass = CabinClass.valueOf(classStr);
        
        // Read preferred region
        String regionStr = input.readString("Preferred Region (EUROPE/ASIA/AMERICA/OTHER): ").toUpperCase().trim();
        data.preferredRegion = DestinationRegion.valueOf(regionStr);
        
        // Read children status
        data.travelsWithChildren = input.readWithParser("Travels with children? (true/false): ", new BooleanParser());
        
        return data;
    }
    
    /**
     * Simple data holder for customer information.
     */
    private static class CustomerData {
        int age;
        int flightsPerYear;
        boolean isStudent;
        boolean livesWithParents;
        double income;
        CabinClass preferredClass;
        DestinationRegion preferredRegion;
        boolean travelsWithChildren;
    }
}
