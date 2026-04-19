import java.util.Arrays;

// Logic for sorting bogie names using built-in Java utilities
public class BogieNameSorter {

    /**
     * Sorts bogie type names alphabetically using the optimized Arrays.sort() method.
     * This replaces manual algorithms like Bubble Sort with production-ready library methods.
     * @param names An array of bogie type names to be sorted.
     */
    public void sortBogieNames(String[] names) {
        if (names == null || names.length <= 1) {
            return;
        }

        // Using Java's built-in, highly optimized sorting utility
        // Time Complexity: O(n log n)
        Arrays.sort(names);
    }

    public String formatNames(String[] names) {
        return Arrays.toString(names);
    }
}

// --- UNIT TESTS ---
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TrainConsistManagementTest {

    private final BogieNameSorter sorter = new BogieNameSorter();

    @Test
    void testSort_BasicAlphabeticalSorting() {
        String[] names = {"Sleeper", "AC Chair", "First Class", "General", "Luxury"};
        String[] expected = {"AC Chair", "First Class", "General", "Luxury", "Sleeper"};
        
        sorter.sortBogieNames(names);
        
        assertArrayEquals(expected, names, "Bogie names should be sorted in alphabetical order.");
    }

    @Test
    void testSort_UnsortedInput() {
        String[] names = {"Luxury", "General", "Sleeper", "AC Chair"};
        String[] expected = {"AC Chair", "General", "Luxury", "Sleeper"};
        
        sorter.sortBogieNames(names);
        
        assertArrayEquals(expected, names, "Unsorted input should be rearranged correctly.");
    }

    @Test
    void testSort_AlreadySortedArray() {
        String[] names = {"AC Chair", "First Class", "General"};
        String[] expected = {"AC Chair", "First Class", "General"};
        
        sorter.sortBogieNames(names);
        
        assertArrayEquals(expected, names, "An already sorted array should remain unchanged.");
    }

    @Test
    void testSort_DuplicateBogieNames() {
        String[] names = {"Sleeper", "AC Chair", "Sleeper", "General"};
        String[] expected = {"AC Chair", "General", "Sleeper", "Sleeper"};
        
        sorter.sortBogieNames(names);
        
        assertArrayEquals(expected, names, "Duplicate names should be retained and ordered correctly.");
    }

    @Test
    void testSort_SingleElementArray() {
        String[] names = {"Sleeper"};
        String[] expected = {"Sleeper"};
        
        sorter.sortBogieNames(names);
        
        assertArrayEquals(expected, names, "A single element array should not change.");
    }
}