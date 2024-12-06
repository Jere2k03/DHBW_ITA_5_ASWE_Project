package test.control;

import control.Calculator;
import control.Exceptions.PacketOutOfBoundsException;
import data.Importer;
import data.Packet;
import data.Exceptions.CSVWrongFormatException;

import static org.junit.jupiter.api.Assertions.*;
import java.util.stream.Stream;
import java.util.Random;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;



/**
 * The {@code TestCalculator} class tests the {@link Calculator} class for calculating the shipping costs of a packet and also for edge cases
 * and throws exceptions.
 */
class TestCalculator {

    /** instance of Random for Exercise 8 */
    private static final Random RANDOM = new Random();

    @BeforeAll
    static void setUpBeforeAll() {
        //no setUpBeforeAll needed
    }

    @AfterAll
    static void tearDownAfterAll() {
        //no tearDownAfterAll needed
    }

    @BeforeEach
    void setUpBeforeEach() {
        // no setUpBeforeEach needed
    }

    @AfterEach
    void tearDownAfterEach() {
        //no tearDownAfterEach needed
    }

    /**
     * Stream of different Packet objects with their expected shipping costs
     */
    private static Stream<Arguments> realPacketDataStream() {
        Double[] costs = {3.89, 4.39, 5.89, 7.99, 14.99};

        return Stream.of(
            

            // Packet with expected cost of 3.89
            Arguments.of(new Packet(1, 1, 1, 1), costs[0]),
            Arguments.of(new Packet(300, 300, 150, 1000), costs[0]),
            Arguments.of(new Packet(250, 250, 120, 750), costs[0]),
            Arguments.of(new Packet(120, 250, 250, 750), costs[0]),

            // Packet with expected cost of 4.39
            Arguments.of(new Packet(1, 1, 1, 1001), costs[1]),
            Arguments.of(new Packet(600, 300, 150, 2000), costs[1]),
            Arguments.of(new Packet(550, 250, 120, 1350), costs[1]),
            Arguments.of(new Packet(250, 120, 550, 1350), costs[1]),

            // Packet with expected cost of 5.89
            Arguments.of(new Packet(1, 1, 1, 5000), costs[2]),
            Arguments.of(new Packet(1000, 500, 500, 5000), costs[2]),
            Arguments.of(new Packet(1200, 200, 550, 3500), costs[2]),
            Arguments.of(new Packet(550, 200, 1200, 3500), costs[2]),

            // Packet with expected cost of 7.99
            Arguments.of(new Packet(1, 1, 1, 5001), costs[3]),
            Arguments.of(new Packet(1000, 500, 500, 10000), costs[3]),
            Arguments.of(new Packet(1100, 450, 450, 9009), costs[3]),
            Arguments.of(new Packet(450, 1100, 450, 9009), costs[3]),

            // Packet with expected cost of 14.99
            Arguments.of(new Packet(1100, 550, 520, 11000), costs[4]),
            Arguments.of(new Packet(1200, 600, 600, 31000), costs[4]),
            Arguments.of(new Packet(1000, 350, 220, 30000), costs[4]),
            Arguments.of(new Packet(1000, 220, 350, 30000), costs[4])
        );
    }
    @ParameterizedTest
    @MethodSource("realPacketDataStream")
    /**
     * Test for different Packet objects with their expected shipping costs
     * 
     * @param pack Packet object
     * @param expectedCosts expected shipping costs
     */
    public void testRealPackets(Packet pack, double expectedCosts) {
        Calculator calc = new Calculator();
        double delta = 1e-5; // delta for double value comparison
        try {
            double shippingCosts = calc.calcShippingCosts(pack);
            assertEquals(expectedCosts, shippingCosts, delta, "Shipping costs are not correct");
        } catch (Exception e) {
            fail("Exception thrown: " + e.getMessage());
        }
    }

    /**
     * Test for a Packet with all values set to 0
     * This test need to throw a PacketOutOfBoundsException
     */
    @Test
    public void testZeroValuePacket() {
        Calculator calc = new Calculator();
        Packet invalidPacket = new Packet(0, 0, 0, 0);
        assertThrows(PacketOutOfBoundsException.class, () -> calc.calcShippingCosts(invalidPacket));
    }

    /**
     * Test for a Packet with all values out of bounds
     * This test need to throw a PacketOutOfBoundsException
     */
    @Test
    public void testOutOfBoundsPacket() {
        Calculator calc = new Calculator();
        Packet outOfBoundsPacket = new Packet(1201, 601, 601, 31001);
        assertThrows(PacketOutOfBoundsException.class, () -> calc.calcShippingCosts(outOfBoundsPacket));
    }

    //Aufgabe 8
    /**
     * Recheck if the calculated costs are correct, with new logic
     * @param pack Packet object
     * @param costs expected shipping costs
     * @return true if the calculated costs are correct, false otherwise
     */
    private boolean checkRandomPacketForCosts(Packet pack, double costs) {
        final List<Double> shippingCostsList;
        try {
            shippingCostsList = Importer.importShippingCosts();
        } catch (Exception e) {
            return false;
        }
    
        if (costs == shippingCostsList.get(0)) {
            return isSmallPacketInBounds(pack);
        }
    
        if (costs == shippingCostsList.get(1)) {
            return isMediumPacketInBounds(pack);
        }
    
        if (costs == shippingCostsList.get(2)) {
            return isLargePacketInBounds(pack, 5000);
        }
    
        if (costs == shippingCostsList.get(3)) {
            return isLargePacketInBounds(pack, 10000);
        }
    
        if (costs == shippingCostsList.get(4)) {
            return isExtraLargePacketInBounds(pack);
        }
    
        return false;
    }
    
    /**
     * Check if the small packet is in bounds
     * 
     * @param pack Packet object
     * @return true if the small packet is in bounds, false otherwise
     */
    private boolean isSmallPacketInBounds(Packet pack) {
        return pack.getLength() <= 300 && pack.getWidth() <= 300 
            && pack.getHeight() <= 150 && pack.getWeight() <= 1000;
    }
    
    /**
     * Check if the medium packet is in bounds
     * 
     * @param pack Packet object
     * @return true if the medium packet is in bounds, false otherwise
     */
    private boolean isMediumPacketInBounds(Packet pack) {
        return pack.getLength() <= 600 && pack.getWidth() <= 300 
            && pack.getHeight() <= 150 && pack.getWeight() <= 2000;
    }
    
    /**
     * Check if the large packet is in bounds
     * 
     * @param pack Packet object
     * @param maxWeight maximum weight for the large packet
     * @return true if the large packet is in bounds, false otherwise
     */
    private boolean isLargePacketInBounds(Packet pack, int maxWeight) {
        if (pack.getLength() > 1200 || pack.getWidth() > 600 || pack.getHeight() > 600) {
            return false;
        }

        int girth = pack.getLength() + 2 * pack.getWidth() + 2 * pack.getHeight();

        return girth <= 3000 && pack.getWeight() <= maxWeight;
    }
    
    /**
     * Check if the extra large packet is in bounds
     * 
     * @param pack Packet object
     * @return true if the extra large packet is in bounds, false otherwise
     */
    private boolean isExtraLargePacketInBounds(Packet pack) {
        return pack.getLength() <= 1200 && pack.getWidth() <= 600 
            && pack.getHeight() <= 600 && pack.getWeight() <= 31000;
    }
    
    /**
     * Stream of different Packet objects with Random values
     */
    private static Stream<Arguments> randomPacketsStream()  {
        
        Stream<Arguments> randomTestData = Stream.empty();

        // Create 1000 packets with Random values
        for (int i = 0; i < 1000; i++) {
            Packet pack = new Packet(
                RANDOM.nextInt(2 * 1200) + 1,
                RANDOM.nextInt(2 * 600) + 1,
                RANDOM.nextInt(2 * 600) + 1,
                RANDOM.nextInt(2 * 31000) + 1 // add 1 to avoid 0 as package dimensions
            );

            randomTestData = Stream.concat(randomTestData, Stream.of(Arguments.of(pack)));
        }

        // Return the stream with all the Random packets
        return randomTestData;
    }
    @ParameterizedTest
    @MethodSource("randomPacketsStream")
    /**
     * Test for 1000 Random Packet objects
     * If the calculated costs are correct, the test will pass
     * If the packet is out of bounds, the test will check if the exception is thrown
     * @param pack Packet object
     */
    public void testRandomPackets(Packet pack) {
        Calculator calc = new Calculator();
        try {
            double actualShippingCosts;
            actualShippingCosts = calc.calcShippingCosts(pack);

            //check if the calculated costs are correct
            assertTrue(checkRandomPacketForCosts(pack, actualShippingCosts));
        } catch (CSVWrongFormatException e) {
            //check if exception is thrown if packet is out of bounds
            assertThrows(CSVWrongFormatException.class, () -> calc.calcShippingCosts(pack));
        } catch (PacketOutOfBoundsException e) {
            //check if exception is thrown if packet is out of bounds
            assertThrows(PacketOutOfBoundsException.class, () -> calc.calcShippingCosts(pack));
        }
    }
}