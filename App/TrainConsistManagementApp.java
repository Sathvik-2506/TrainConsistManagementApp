import java.util.HashMap;
import java.util.Map;

public class TrainConsistManagementApp {

    public static void main(String[] args) {

        System.out.println("=== Train Consist Management App (UC6 - Bogie Capacity Mapping) ===");

        // Create HashMap to store bogie and capacity
        Map<String, Integer> bogieCapacityMap = new HashMap<>();

        // Add bogie-capacity pairs
        bogieCapacityMap.put("Sleeper", 72);
        bogieCapacityMap.put("AC Chair", 54);
        bogieCapacityMap.put("First Class", 24);

        // Display bogie capacity details
        System.out.println("Bogie Capacity Details:");
        for (Map.Entry<String, Integer> entry : bogieCapacityMap.entrySet()) {
            System.out.println(entry.getKey() + " -> Capacity: " + entry.getValue());
        }
    }
}