import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;
import java.util.Set;
import java.util.LinkedList;
import java.util.LinkedHashSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Comparator;
import java.util.regex.Pattern;

public class Train_Consist_Management_System {

    static class Bogie {
        String name;
        int capacity;

        Bogie(String name, int capacity) throws InvalidCapacityException {
            if (capacity <= 0) {
                throw new InvalidCapacityException("Capacity must be greater than zero");
            }
            this.name = name;
            this.capacity = capacity;
        }
    }
    static class GoodsBogie {
        String type;
        String cargo;

        GoodsBogie(String type, String cargo) {
            this.type = type;
            this.cargo = cargo;
        }
    }
    public static List<Bogie> filterBogiesByCapacity(List<Bogie> bogies, int threshold) {
        return bogies.stream()
                .filter(b -> b.capacity > threshold)
                .toList();
    }
    public static Map<String, List<Bogie>> groupBogiesByType(List<Bogie> bogies) {
        return bogies.stream()
                .collect(java.util.stream.Collectors.groupingBy(b -> b.name));
    }
    public static int calculateTotalCapacity(List<Bogie> bogies) {
        return bogies.stream()
                .map(b -> b.capacity)
                .reduce(0, Integer::sum);
    }

    public static boolean isValidTrainId(String trainId) {
        String regex = "TRN-\\d{4}";
        return Pattern.matches(regex, trainId);
    }

    public static boolean isValidCargoCode(String cargoCode) {
        String regex = "PET-[A-Z]{2}";
        return Pattern.matches(regex, cargoCode);
    }
    public static boolean isTrainSafe(List<GoodsBogie> goodsBogies) {
        return goodsBogies.stream()
                .allMatch(b -> {
                    if (b.type.equalsIgnoreCase("Cylindrical")) {
                        return b.cargo.equalsIgnoreCase("Petroleum");
                    }
                    return true; // other bogies allowed anything
                });
    }
    public static List<Bogie> filterWithLoop(List<Bogie> bogies) {
        List<Bogie> result = new ArrayList<>();

        for (Bogie b : bogies) {
            if (b.capacity > 60) {
                result.add(b);
            }
        }
        return result;
    }
    public static List<Bogie> filterWithStream(List<Bogie> bogies) {
        return bogies.stream()
                .filter(b -> b.capacity > 60)
                .toList();
    }
    static class InvalidCapacityException extends Exception {
        public InvalidCapacityException(String message) {
            super(message);
        }
    }
    public static void main(String[] args) {

        // Welcome Message
        System.out.println("=== Train Consist Management App ===");

        // Initialize Train Consist (Empty List)
        List<String> trainConsist = new ArrayList<>();

        // Display Initial Bogie Count
        System.out.println("Train consist initialized.");
        System.out.println("Initial bogie count: " + trainConsist.size());

        // Create Passenger Bogie List
        List<String> passengerBogies = new ArrayList<>();

        // Add bogies
        passengerBogies.add("Sleeper");
        passengerBogies.add("AC Chair");
        passengerBogies.add("First Class");

        // Display after adding
        System.out.println("\nPassenger Bogies after addition:");
        System.out.println(passengerBogies);

        // Remove one bogie
        passengerBogies.remove("AC Chair");

        // Display after removal
        System.out.println("\nAfter removing AC Chair:");
        System.out.println(passengerBogies);

        // Check existence
        boolean exists = passengerBogies.contains("Sleeper");

        System.out.println("\nDoes Sleeper exist? " + exists);

        // Final state
        System.out.println("\nFinal Passenger Bogies:");
        System.out.println(passengerBogies);
        // =========================
// UC3 — Unique Bogie IDs
// =========================



// Create Set for Bogie IDs
        Set<String> bogieIds = new HashSet<>();

// Add IDs (with duplicates intentionally)
        bogieIds.add("BG101");
        bogieIds.add("BG102");
        bogieIds.add("BG103");
        bogieIds.add("BG101"); // duplicate
        bogieIds.add("BG102"); // duplicate

// Display unique bogie IDs
        System.out.println("\nUnique Bogie IDs:");
        System.out.println(bogieIds);


// Create LinkedList for Train Consist
        LinkedList<String> orderedConsist = new LinkedList<>();

// Add bogies (in order)
        orderedConsist.add("Engine");
        orderedConsist.add("Sleeper");
        orderedConsist.add("AC");
        orderedConsist.add("Cargo");
        orderedConsist.add("Guard");

// Insert Pantry Car at position 2 (index starts from 0)
        orderedConsist.add(2, "Pantry");

// Display after insertion
        System.out.println("\nTrain Consist after adding Pantry:");
        System.out.println(orderedConsist);

// Remove first and last bogie
        orderedConsist.removeFirst();
        orderedConsist.removeLast();

// Final consist
        System.out.println("\nFinal Ordered Train Consist:");
        System.out.println(orderedConsist);

        // Create LinkedHashSet for train formation
        LinkedHashSet<String> formation = new LinkedHashSet<>();

// Add bogies
        formation.add("Engine");
        formation.add("Sleeper");
        formation.add("Cargo");
        formation.add("Guard");

// Add duplicate intentionally
        formation.add("Sleeper"); // duplicate

// Display final formation
        System.out.println("\nTrain Formation (Ordered & Unique):");
        System.out.println(formation);

        // =========================
// UC6 — Bogie Capacity Mapping using HashMap
// =========================

// Create HashMap for bogie → capacity
        Map<String, Integer> bogieCapacityMap = new HashMap<>();

// Add bogie capacities
        bogieCapacityMap.put("Sleeper", 72);
        bogieCapacityMap.put("AC Chair", 50);
        bogieCapacityMap.put("First Class", 24);

// Display capacity details
        System.out.println("\nBogie Capacity Details:");

        for (Map.Entry<String, Integer> entry : bogieCapacityMap.entrySet()) {
            System.out.println(entry.getKey() + " → Capacity: " + entry.getValue());
        }
// Create list of Bogie objects
        List<Bogie> bogieList = new ArrayList<>();

        bogieList.add(new Bogie("Sleeper", 72));
        bogieList.add(new Bogie("AC Chair", 50));
        bogieList.add(new Bogie("First Class", 24));
        bogieList.add(new Bogie("General", 90));
        bogieList.add(new Bogie("Sleeper", 80));
        bogieList.add(new Bogie("AC Chair", 55));

// Sort using Comparator (ascending order)
        bogieList.sort(Comparator.comparingInt(b -> b.capacity));

// Display sorted bogies
        System.out.println("\nBogies sorted by capacity:");

        for (Bogie b : bogieList) {
            System.out.println(b.name + " → Capacity: " + b.capacity);
        }
        // =========================
// UC8 — Stream Filtering
// =========================

        int threshold = 60;

        List<Bogie> filteredBogies = filterBogiesByCapacity(bogieList, threshold);

        System.out.println("\nFiltered Bogies (Capacity > " + threshold + "):");

        for (Bogie b : filteredBogies) {
            System.out.println(b.name + " → Capacity: " + b.capacity);
        }
        // =========================
// UC9 — Group Bogies by Type
// =========================

        Map<String, List<Bogie>> groupedBogies = groupBogiesByType(bogieList);

        System.out.println("\nGrouped Bogies by Type:");

        for (Map.Entry<String, List<Bogie>> entry : groupedBogies.entrySet()) {
            System.out.println(entry.getKey() + ":");

            for (Bogie b : entry.getValue()) {
                System.out.println("  Capacity: " + b.capacity);
            }
        }
        // =========================
// UC10 — Total Seat Calculation
// =========================

        int totalCapacity = calculateTotalCapacity(bogieList);

        System.out.println("\nTotal Seating Capacity of Train: " + totalCapacity);
        // =========================
// UC11 — Regex Validation
// =========================

        String trainId = "TRN-1234";
        String cargoCode = "PET-AB";

        System.out.println("\nTrain ID Validation: " +
                (isValidTrainId(trainId) ? "Valid" : "Invalid"));

        System.out.println("Cargo Code Validation: " +
                (isValidCargoCode(cargoCode) ? "Valid" : "Invalid"));


        // =========================
// UC12 — Safety Compliance Check
// =========================

        List<GoodsBogie> goodsBogies = new ArrayList<>();

        goodsBogies.add(new GoodsBogie("Cylindrical", "Petroleum"));
        goodsBogies.add(new GoodsBogie("Open", "Coal"));
        goodsBogies.add(new GoodsBogie("Box", "Grain"));

        boolean isSafe = isTrainSafe(goodsBogies);

        System.out.println("\nTrain Safety Status: " + (isSafe ? "SAFE" : "UNSAFE"));
        // =========================
// UC13 — Performance Comparison
// =========================

// Create large dataset
        List<Bogie> largeList = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            largeList.add(new Bogie("Sleeper", i % 100));
        }

// Loop timing
        long startLoop = System.nanoTime();
        List<Bogie> loopResult = filterWithLoop(largeList);
        long endLoop = System.nanoTime();

// Stream timing
        long startStream = System.nanoTime();
        List<Bogie> streamResult = filterWithStream(largeList);
        long endStream = System.nanoTime();

// Results
        System.out.println("\nLoop Time: " + (endLoop - startLoop) + " ns");
        System.out.println("Stream Time: " + (endStream - startStream) + " ns");
        System.out.println("Loop Result Size: " + loopResult.size());
        System.out.println("Stream Result Size: " + streamResult.size());
        // =========================
// UC14 — Exception Handling
// =========================

        try {
            Bogie b1 = new Bogie("Sleeper", 72);
            Bogie b2 = new Bogie("AC Chair", 0); // ❌ will throw exception

            System.out.println("Bogies created successfully");

        } catch (InvalidCapacityException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}