import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;
import java.util.Set;
import java.util.LinkedList;

public class Train_Consist_Management_System {

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
    }
}