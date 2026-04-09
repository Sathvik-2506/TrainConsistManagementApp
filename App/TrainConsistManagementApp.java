import java.util.ArrayList;
import java.util.List;

public class TrainConsistManagementApp {

    public static void main(String[] args) {

        System.out.println("=== Train Consist Management App (UC2 - Passenger Bogies) ===");

        // Create ArrayList for passenger bogies
        List<String> passengerBogies = new ArrayList<>();

        // Add bogies
        passengerBogies.add("Sleeper");
        passengerBogies.add("AC Chair");
        passengerBogies.add("First Class");

        // Display after addition
        System.out.println("Passenger Bogies after addition: " + passengerBogies);

        // Remove a bogie
        passengerBogies.remove("AC Chair");
        System.out.println("After removing AC Chair: " + passengerBogies);

        // Check existence
        boolean exists = passengerBogies.contains("Sleeper");
        System.out.println("Does Sleeper exist? " + exists);

        // Final list state
        System.out.println("Final Passenger Bogies: " + passengerBogies);
    }
}