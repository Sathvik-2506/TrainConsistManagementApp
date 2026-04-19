// Logic for searching bogie IDs using Linear Search
public class BogieSearcher {

    /**
     * Searches for a specific bogie ID in an array using Linear Search.
     * This technique checks each element sequentially and is suitable for unsorted data.
     * @param bogieIds Array of bogie IDs to search through.
     * @param targetId The specific ID to locate.
     * @return true if the ID exists in the array, false otherwise.
     */
    public boolean linearSearch(String[] bogieIds, String targetId) {
        if (bogieIds == null || targetId == null) {
            return false;
        }

        // Sequential Traversal: O(n) Time Complexity
        for (String id : bogieIds) {
            // Equality Comparison using .equals() for String safety
            if (id.equals(targetId)) {
                // Early Termination: Stop searching once found
                return true; 
            }
        }

        // Search Match Not Found after traversing the entire list
        return false;
    }
}

// --- UNIT TESTS ---
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TrainConsistManagementTest {

    private final BogieSearcher searcher = new BogieSearcher();

    @Test
    void testSearch_BogieFound() {
        String[] bogieIds = {"BG101", "BG205", "BG309", "BG412", "BG550"};
        assertTrue(searcher.linearSearch(bogieIds, "BG309"), "System should identify an existing bogie ID.");
    }

    @Test
    void testSearch_BogieNotFound() {
        String[] bogieIds = {"BG101", "BG205", "BG309", "BG412", "BG550"};
        assertFalse(searcher.linearSearch(bogieIds, "BG999"), "System should return false for non-existent ID.");
    }

    @Test
    void testSearch_FirstElementMatch() {
        String[] bogieIds = {"BG101", "BG205", "BG309", "BG412", "BG550"};
        assertTrue(searcher.linearSearch(bogieIds, "BG101"), "Search should terminate successfully at the first position.");
    }

    @Test
    void testSearch_LastElementMatch() {
        String[] bogieIds = {"BG101", "BG205", "BG309", "BG412", "BG550"};
        assertTrue(searcher.linearSearch(bogieIds, "BG550"), "Search should traverse to the final element and return success.");
    }

    @Test
    void testSearch_SingleElementArray() {
        String[] bogieIds = {"BG101"};
        assertTrue(searcher.linearSearch(bogieIds, "BG101"), "Search should work correctly for a single element array.");
        assertFalse(searcher.linearSearch(bogieIds, "BG202"), "Search should return false if single element does not match.");
    }
}