import java.util.Arrays;

// Logic for searching bogie IDs using Optimized Binary Search
public class BogieOptimizedSearcher {

    /**
     * Searches for a bogie ID using the Binary Search algorithm.
     * Precondition: The array must be sorted. If not, it sorts it first.
     * @param bogieIds Array of bogie IDs to search.
     * @param targetId The ID to find.
     * @return true if found, false otherwise.
     */
    public boolean binarySearch(String[] bogieIds, String targetId) {
        if (bogieIds == null || bogieIds.length == 0 || targetId == null) {
            return false;
        }

        // Ensure data is sorted (Precondition for Binary Search)
        // Note: In production, we assume data is already sorted to maintain O(log n)
        Arrays.sort(bogieIds);

        int low = 0;
        int high = bogieIds.length - 1;

        while (low <= high) {
            // Divide-and-Conquer: Find middle index
            int mid = low + (high - low) / 2;
            int comparison = targetId.compareTo(bogieIds[mid]);

            if (comparison == 0) {
                return true; // Match found
            } else if (comparison < 0) {
                high = mid - 1; // Search left half
            } else {
                low = mid + 1; // Search right half
            }
        }

        return false; // Exhausted search range
    }
}

// --- UNIT TESTS ---
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TrainConsistManagementTest {

    private final BogieOptimizedSearcher searcher = new BogieOptimizedSearcher();

    @Test
    void testBinarySearch_BogieFound() {
        String[] bogieIds = {"BG101", "BG205", "BG309", "BG412", "BG550"};
        assertTrue(searcher.binarySearch(bogieIds, "BG309"), "Should find ID in the middle.");
    }

    @Test
    void testBinarySearch_BogieNotFound() {
        String[] bogieIds = {"BG101", "BG205", "BG309", "BG412", "BG550"};
        assertFalse(searcher.binarySearch(bogieIds, "BG999"), "Should return false for non-existent ID.");
    }

    @Test
    void testBinarySearch_FirstElementMatch() {
        String[] bogieIds = {"BG101", "BG205", "BG309", "BG412", "BG550"};
        assertTrue(searcher.binarySearch(bogieIds, "BG101"), "Should find ID at the first position.");
    }

    @Test
    void testBinarySearch_LastElementMatch() {
        String[] bogieIds = {"BG101", "BG205", "BG309", "BG412", "BG550"};
        assertTrue(searcher.binarySearch(bogieIds, "BG550"), "Should find ID at the last position.");
    }

    @Test
    void testBinarySearch_SingleElementArray() {
        String[] bogieIds = {"BG101"};
        assertTrue(searcher.binarySearch(bogieIds, "BG101"), "Should work with one element.");
    }

    @Test
    void testBinarySearch_EmptyArray() {
        String[] bogieIds = {};
        assertFalse(searcher.binarySearch(bogieIds, "BG101"), "Empty array should return false.");
    }

    @Test
    void testBinarySearch_UnsortedInputHandled() {
        // Input is explicitly unsorted
        String[] bogieIds = {"BG309", "BG101", "BG550", "BG205", "BG412"};
        assertTrue(searcher.binarySearch(bogieIds, "BG205"), "Should sort and then find the ID.");
    }
}