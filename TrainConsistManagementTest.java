// Custom Runtime Exception
class CargoSafetyException extends RuntimeException {
    public CargoSafetyException(String message) {
        super(message);
    }
}

// Goods Bogie Class
class GoodsBogie {
    private String shape;
    private String cargoType;

    public GoodsBogie(String shape) {
        this.shape = shape;
    }

    public void assignCargo(String cargo) {
        System.out.println("Attempting to assign " + cargo + " to " + shape + " bogie...");
        try {
            // Business Rule: Petroleum cannot be in Rectangular bogies
            if (shape.equalsIgnoreCase("Rectangular") && cargo.equalsIgnoreCase("Petroleum")) {
                throw new CargoSafetyException("Safety Violation: Petroleum cannot be transported in a Rectangular bogie!");
            }
            this.cargoType = cargo;
            System.out.println("Success: " + cargo + " assigned successfully.");
        } catch (CargoSafetyException e) {
            System.err.println("Handled Exception: " + e.getMessage());
            this.cargoType = null; // Ensure no assignment on failure
        } finally {
            System.out.println("Cargo validation process completed for this bogie.");
        }
    }

    public String getCargoType() { return cargoType; }
    public String getShape() { return shape; }
}

// --- UNIT TESTS ---
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class TrainConsistManagementTest {

    @Test
    void testCargo_SafeAssignment() {
        GoodsBogie cylindrical = new GoodsBogie("Cylindrical");
        cylindrical.assignCargo("Petroleum");
        assertEquals("Petroleum", cylindrical.getCargoType(), "Cylindrical bogies should accept Petroleum.");
    }

    @Test
    void testCargo_UnsafeAssignmentHandled() {
        GoodsBogie rectangular = new GoodsBogie("Rectangular");
        // We catch inside the method, so no exception should propagate out to crash the app
        assertDoesNotThrow(() -> rectangular.assignCargo("Petroleum"), 
            "Exception should be caught internally using try-catch.");
    }

    @Test
    void testCargo_CargoNotAssignedAfterFailure() {
        GoodsBogie rectangular = new GoodsBogie("Rectangular");
        rectangular.assignCargo("Petroleum");
        assertNull(rectangular.getCargoType(), "Cargo should remain unassigned after a safety violation.");
    }

    @Test
    void testCargo_ProgramContinuesAfterException() {
        GoodsBogie rectangular = new GoodsBogie("Rectangular");
        GoodsBogie anotherRectangular = new GoodsBogie("Rectangular");
        
        rectangular.assignCargo("Petroleum"); // Fails but caught
        anotherRectangular.assignCargo("Grains"); // Should proceed normally
        
        assertEquals("Grains", anotherRectangular.getCargoType(), "Application should continue processing subsequent assignments.");
    }

    @Test
    void testCargo_FinallyBlockExecution() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        
        GoodsBogie bogie = new GoodsBogie("Rectangular");
        bogie.assignCargo("Petroleum");
        
        assertTrue(outContent.toString().contains("Cargo validation process completed"), 
            "The finally block must execute even when an exception occurs.");
        
        System.setOut(System.out); // Reset stream
    }
}