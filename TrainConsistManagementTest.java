import java.util.Arrays;

// Logic for Defensive Search with State Validation
public class DefensiveBogieSearcher {

    /**
     * Performs an optimized search for a bogie ID, but validates system state first.
     * This follows the 'Fail-Fast' principle to prevent operations on invalid data.
     * * @param bogieIds Array of bogie IDs in the train consist.
     * @param targetId The ID to locate.
     * @return true if the ID exists, false otherwise.
     * @throws IllegalStateException if the train consist is empty.
     */
    public boolean searchWithValidation(String[] bogieIds, String targetId) {
        // 1. State Validation (Defensive Programming)
        if (bogieIds == null || bogieIds.length == 0) {
            throw new IllegalStateException("Search failed: No bogies available in the train consist.");
        }

        // 2. Proceed with Search logic only if state is valid
        // Sorting is required for Binary Search
        Arrays.sort(bogieIds);
        
        int index = Arrays.binarySearch(bogieIds, targetId);
        return index >= 0;
    }
}

// --- UNIT TESTS ---
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TrainConsistManagementTest {

    private final DefensiveBogieSearcher searcher = new DefensiveBogieSearcher();

    @Test
    void testSearch_ThrowsExceptionWhenEmpty() {
        String[] emptyConsist = {};
        
        // Verifies Fail-Fast behavior using IllegalStateException
        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            searcher.searchWithValidation(emptyConsist, "BG101");
        });
        
        assertEquals("Search failed: No bogies available in the train consist.", exception.getMessage());
    }

    @Test
    void testSearch_AllowsSearchWhenDataExists() {
        String[] consist = {"BG101", "BG205"};
        
        // Should not throw any exception
        assertDoesNotThrow(() -> {
            searcher.searchWithValidation(consist, "BG101");
        });
    }

    @Test
    void testSearch_BogieFoundAfterValidation() {
        String[] consist = {"BG101", "BG205", "BG309"};
        boolean found = searcher.searchWithValidation(consist, "BG205");
        
        assertTrue(found, "Bogie should be found when it exists in the collection.");
    }

    @Test
    void testSearch_BogieNotFoundAfterValidation() {
        String[] consist = {"BG101", "BG205", "BG309"};
        boolean found = searcher.searchWithValidation(consist, "BG999");
        
        assertFalse(found, "Should return false if the ID is missing after validation passes.");
    }

    @Test
    void testSearch_SingleElementValidCase() {
        String[] consist = {"BG101"};
        assertTrue(searcher.searchWithValidation(consist, "BG101"), "Search should work for a single valid element.");
    }
}