import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import java.util.*;


class Train_Consist_Management_SystemTest {

    private List<Train_Consist_Management_System.Bogie> createBogies() {
        return Arrays.asList(
                new Train_Consist_Management_System.Bogie("Sleeper", 72),
                new Train_Consist_Management_System.Bogie("AC Chair", 50),
                new Train_Consist_Management_System.Bogie("First Class", 60),
                new Train_Consist_Management_System.Bogie("General", 90)
        );
    }

    @Test
    void testFilter_CapacityGreaterThanThreshold() {
        var result = Train_Consist_Management_System
                .filterBogiesByCapacity(createBogies(), 60);

        assertEquals(2, result.size());
    }

    @Test
    void testFilter_NoBogiesMatching() {
        var result = Train_Consist_Management_System
                .filterBogiesByCapacity(createBogies(), 100);

        assertTrue(result.isEmpty());
    }

    @Test
    void testFilter_AllBogiesMatching() {
        var result = Train_Consist_Management_System
                .filterBogiesByCapacity(createBogies(), 10);

        assertEquals(4, result.size());
    }

    @Test
    void testFilter_EmptyList() {
        var result = Train_Consist_Management_System
                .filterBogiesByCapacity(new ArrayList<>(), 50);

        assertTrue(result.isEmpty());
    }

    @Test
    void testOriginalListUnchanged() {
        List<Train_Consist_Management_System.Bogie> original = createBogies();
        List<Train_Consist_Management_System.Bogie> copy = new ArrayList<>(original);

        Train_Consist_Management_System
                .filterBogiesByCapacity(original, 60);

        assertEquals(copy.size(), original.size());
    }
}