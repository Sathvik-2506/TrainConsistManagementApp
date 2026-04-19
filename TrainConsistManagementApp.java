import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class Bogie {
    private String id;
    private int capacity;

    public Bogie(String id, int capacity) {
        this.id = id;
        this.capacity = capacity;
    }

    public int getCapacity() { return capacity; }
}

public class ConsistPerformanceBenchmarker {

    public static void main(String[] args) {
        // 1. Prepare a large collection of bogies (e.g., 100,000)
        List<Bogie> consist = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            consist.add(new Bogie("B" + i, (i % 100))); // Capacities ranging 0-99
        }

        System.out.println("--- UC13: Performance Benchmarking (Loop vs Streams) ---");
        System.out.println("Dataset Size: " + consist.size() + " bogies\n");

        // 2. Loop-Based Filtering
        long startLoop = System.nanoTime();
        List<Bogie> filteredLoop = new ArrayList<>();
        for (Bogie b : consist) {
            if (b.getCapacity() > 60) {
                filteredLoop.add(b);
            }
        }
        long endLoop = System.nanoTime();
        long loopDuration = endLoop - startLoop;

        // 3. Stream-Based Filtering
        long startStream = System.nanoTime();
        List<Bogie> filteredStream = consist.stream()
                .filter(b -> b.getCapacity() > 60)
                .collect(Collectors.toList());
        long endStream = System.nanoTime();
        long streamDuration = endStream - startStream;

        // 4. Display Results
        System.out.println("Loop-Based Filtering Time   : " + loopDuration + " ns");
        System.out.println("Stream-Based Filtering Time : " + streamDuration + " ns");
        System.out.println("Results Match: " + (filteredLoop.size() == filteredStream.size()));
        
        double difference = (double) streamDuration / loopDuration;
        System.out.printf("Stream is %.2fx slower/faster than Loop\n", difference);
    }
}