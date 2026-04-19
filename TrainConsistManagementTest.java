// Bubble Sort implementation for Train Consist Management
public class ConsistSorter {

    /**
     * Sorts passenger bogie capacities using the Bubble Sort algorithm.
     * This demonstrates manual sorting logic without using library methods.
     * * @param capacities An array of passenger bogie seat capacities.
     */
    public void bubbleSort(int[] capacities) {
        if (capacities == null || capacities.length <= 1) {
            return;
        }

        int n = capacities.length;
        // Nested loops for bubble sort logic
        for (int i = 0; i < n - 1; i++) {
            // Last i elements are already in place
            for (int j = 0; j < n - i - 1; j++) {
                // Compare adjacent elements
                if (capacities[j] > capacities[j + 1]) {
                    // Swapping logic using a temporary variable
                    int temp = capacities[j];
                    capacities[j] = capacities[j + 1];
                    capacities[j + 1] = temp;
                }
            }
        }
    }
}

// --- UNIT TESTS ---
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TrainConsistManagementTest {

    private final ConsistSorter sorter = new ConsistSorter();

    @Test
    void testSort_BasicSorting() {
        int[] capacities = {72, 56, 24, 70, 60};
        int[] expected = {24, 56, 60, 70, 72};
        
        sorter.bubbleSort(capacities);
        
        assertArrayEquals(expected, capacities, "The array should be sorted in ascending order.");
    }

    @Test
    void testSort_AlreadySortedArray() {
        int[] capacities = {24, 56, 60, 70, 72};
        int[] expected = {24, 56, 60, 70, 72};
        
        sorter.bubbleSort(capacities);
        
        assertArrayEquals(expected, capacities, "An already sorted array should remain unchanged.");
    }

    @Test
    void testSort_DuplicateValues() {
        int[] capacities = {72, 56, 56, 24};
        int[] expected = {24, 56, 56, 72};
        
        sorter.bubbleSort(capacities);
        
        assertArrayEquals(expected, capacities, "Duplicate values should be handled and sorted correctly.");
    }

    @Test
    void testSort_SingleElementArray() {
        int[] capacities = {50};
        int[] expected = {50};
        
        sorter.bubbleSort(capacities);
        
        assertArrayEquals(expected, capacities, "A single element array should remain unchanged.");
    }

    @Test
    void testSort_AllEqualValues() {
        int[] capacities = {40, 40, 40};
        int[] expected = {40, 40, 40};
        
        sorter.bubbleSort(capacities);
        
        assertArrayEquals(expected, capacities, "An array of identical values should remain unchanged.");
    }
}