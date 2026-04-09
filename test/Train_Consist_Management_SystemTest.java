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
    @Test
    void testGrouping_BogiesGroupedByType() {
        var result = Train_Consist_Management_System.groupBogiesByType(createBogies());
        assertTrue(result.containsKey("Sleeper"));
    }

    @Test
    void testGrouping_MultipleBogiesInSameGroup() {
        var list = new ArrayList<Train_Consist_Management_System.Bogie>();
        list.add(new Train_Consist_Management_System.Bogie("Sleeper", 72));
        list.add(new Train_Consist_Management_System.Bogie("Sleeper", 80));

        var result = Train_Consist_Management_System.groupBogiesByType(list);
        assertEquals(2, result.get("Sleeper").size());
    }

    @Test
    void testGrouping_EmptyBogieList() {
        var result = Train_Consist_Management_System.groupBogiesByType(new ArrayList<>());
        assertTrue(result.isEmpty());
    }

    @Test
    void testGrouping_OriginalListUnchanged() {
        var original = createBogies();
        var copy = new ArrayList<>(original);

        Train_Consist_Management_System.groupBogiesByType(original);

        assertEquals(copy.size(), original.size());
    }
    @Test
    void testReduce_TotalSeatCalculation() {
        var list = createBogies();
        int total = Train_Consist_Management_System.calculateTotalCapacity(list);

        assertEquals(72 + 50 + 60 + 90, total);
    }

    @Test
    void testReduce_MultipleBogiesAggregation() {
        var list = createBogies();
        int total = Train_Consist_Management_System.calculateTotalCapacity(list);

        assertTrue(total > 0);
    }

    @Test
    void testReduce_SingleBogieCapacity() {
        var list = new ArrayList<Train_Consist_Management_System.Bogie>();
        list.add(new Train_Consist_Management_System.Bogie("Sleeper", 72));

        int total = Train_Consist_Management_System.calculateTotalCapacity(list);

        assertEquals(72, total);
    }

    @Test
    void testReduce_EmptyBogieList() {
        int total = Train_Consist_Management_System.calculateTotalCapacity(new ArrayList<>());

        assertEquals(0, total);
    }

    @Test
    void testReduce_AllBogiesIncluded() {
        var list = createBogies();
        int total = Train_Consist_Management_System.calculateTotalCapacity(list);

        assertEquals(72 + 50 + 60 + 90, total);
    }

    @Test
    void testReduce_OriginalListUnchanged() {
        var original = createBogies();
        var copy = new ArrayList<>(original);

        Train_Consist_Management_System.calculateTotalCapacity(original);

        assertEquals(copy.size(), original.size());
    }
    @Test
    void testRegex_ValidTrainID() {
        assertTrue(Train_Consist_Management_System.isValidTrainId("TRN-1234"));
    }

    @Test
    void testRegex_InvalidTrainIDFormat() {
        assertFalse(Train_Consist_Management_System.isValidTrainId("TRAIN12"));
        assertFalse(Train_Consist_Management_System.isValidTrainId("TRN12A"));
        assertFalse(Train_Consist_Management_System.isValidTrainId("1234-TRN"));
    }

    @Test
    void testRegex_ValidCargoCode() {
        assertTrue(Train_Consist_Management_System.isValidCargoCode("PET-AB"));
    }

    @Test
    void testRegex_InvalidCargoCodeFormat() {
        assertFalse(Train_Consist_Management_System.isValidCargoCode("PET-ab"));
        assertFalse(Train_Consist_Management_System.isValidCargoCode("PET123"));
        assertFalse(Train_Consist_Management_System.isValidCargoCode("AB-PET"));
    }

    @Test
    void testRegex_TrainIDDigitLengthValidation() {
        assertFalse(Train_Consist_Management_System.isValidTrainId("TRN-123"));
        assertFalse(Train_Consist_Management_System.isValidTrainId("TRN-12345"));
    }

    @Test
    void testRegex_CargoCodeUppercaseValidation() {
        assertFalse(Train_Consist_Management_System.isValidCargoCode("PET-Ab"));
    }

    @Test
    void testRegex_EmptyInputHandling() {
        assertFalse(Train_Consist_Management_System.isValidTrainId(""));
        assertFalse(Train_Consist_Management_System.isValidCargoCode(""));
    }

    @Test
    void testRegex_ExactPatternMatch() {
        assertFalse(Train_Consist_Management_System.isValidTrainId("TRN-1234XYZ"));
        assertFalse(Train_Consist_Management_System.isValidCargoCode("PET-ABCD"));
    }
    @Test
    void testLoopFilteringLogic() {
        var list = createBogies();
        var result = Train_Consist_Management_System.filterWithLoop(list);

        assertTrue(result.stream().allMatch(b -> b.capacity > 60));
    }

    @Test
    void testStreamFilteringLogic() {
        var list = createBogies();
        var result = Train_Consist_Management_System.filterWithStream(list);

        assertTrue(result.stream().allMatch(b -> b.capacity > 60));
    }

    @Test
    void testLoopAndStreamResultsMatch() {
        var list = createBogies();

        var loopResult = Train_Consist_Management_System.filterWithLoop(list);
        var streamResult = Train_Consist_Management_System.filterWithStream(list);

        assertEquals(loopResult.size(), streamResult.size());
    }

    @Test
    void testExecutionTimeMeasurement() {
        var list = createBogies();

        long start = System.nanoTime();
        Train_Consist_Management_System.filterWithLoop(list);
        long end = System.nanoTime();

        assertTrue((end - start) > 0);
    }

    @Test
    void testLargeDatasetProcessing() {
        var list = new ArrayList<Train_Consist_Management_System.Bogie>();

        for (int i = 0; i < 1000; i++) {
            list.add(new Train_Consist_Management_System.Bogie("Test", i));
        }

        var result = Train_Consist_Management_System.filterWithStream(list);

        assertNotNull(result);
    }
    @Test
    void testException_ValidCapacityCreation() throws Exception {
        var b = new Train_Consist_Management_System.Bogie("Sleeper", 72);
        assertEquals(72, b.capacity);
    }

    @Test
    void testException_NegativeCapacityThrowsException() {
        assertThrows(
                Train_Consist_Management_System.InvalidCapacityException.class,
                () -> new Train_Consist_Management_System.Bogie("Sleeper", -10)
        );
    }

    @Test
    void testException_ZeroCapacityThrowsException() {
        assertThrows(
                Train_Consist_Management_System.InvalidCapacityException.class,
                () -> new Train_Consist_Management_System.Bogie("AC", 0)
        );
    }

    @Test
    void testException_ExceptionMessageValidation() {
        Exception ex = assertThrows(
                Train_Consist_Management_System.InvalidCapacityException.class,
                () -> new Train_Consist_Management_System.Bogie("Sleeper", 0)
        );

        assertEquals("Capacity must be greater than zero", ex.getMessage());
    }

    @Test
    void testException_ObjectIntegrityAfterCreation() throws Exception {
        var b = new Train_Consist_Management_System.Bogie("AC Chair", 50);

        assertEquals("AC Chair", b.name);
        assertEquals(50, b.capacity);
    }

    @Test
    void testException_MultipleValidBogiesCreation() throws Exception {
        var b1 = new Train_Consist_Management_System.Bogie("Sleeper", 72);
        var b2 = new Train_Consist_Management_System.Bogie("AC", 50);

        assertNotNull(b1);
        assertNotNull(b2);
    }
}