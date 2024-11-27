package test.test_control;

import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;
import java.util.Random;
import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import control.Calculator;
import data.Packet;

/**
 * The {@code TestCalculator} class tests the {@link Calculator} class for calculating
 */
class TestCalculator {

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
        //no setUpBeforeEach needed
    }

    @AfterEach
    void tearDownAfterEach() {
        //no tearDownAfterEach needed
    }

    /**
     * Stream of different Packet objects with their expected shipping costs
     */
    static Stream<Arguments> realPacketDataStream() {
        Double[] costs = {3.89, 4.39, 5.89, 7.99, 14.99};

        return Stream.of(
            

            // Packet with expected cost of 3.89
            Arguments.of(new Packet(1, 1, 1, 1), costs[0]),
            Arguments.of(new Packet(300, 300, 150, 1000), costs[0]),
            Arguments.of(new Packet(250, 250, 120, 750), costs[0]),

            // Packet with expected cost of 4.39
            Arguments.of(new Packet(1, 1, 1, 1001), costs[1]),
            Arguments.of(new Packet(600, 300, 150, 2000), costs[1]),
            Arguments.of(new Packet(550, 250, 120, 1350), costs[1]),

            // Packet with expected cost of 5.89
            Arguments.of(new Packet(1, 1, 1, 5000), costs[2]),
            Arguments.of(new Packet(1000, 500, 500, 5000), costs[2]),
            Arguments.of(new Packet(1200, 200, 550, 3500), costs[2]),

            // Packet with expected cost of 7.99
            Arguments.of(new Packet(1, 1, 1, 5001), costs[3]),
            Arguments.of(new Packet(1000, 500, 500, 10000), costs[3]),
            Arguments.of(new Packet(1100, 450, 450, 9009), costs[3]),

            // Packet with expected cost of 14.99
            Arguments.of(new Packet(1100, 550, 520, 11000), costs[4]),
            Arguments.of(new Packet(1200, 600, 600, 31000), costs[4]),
            Arguments.of(new Packet(1000, 350, 220, 30000), costs[4])
        );
    }
    @ParameterizedTest
    @MethodSource("realPacketDataStream")
    void testRealPackets(Packet pack, double expectedCosts) {
        Calculator calc = new Calculator();
        double delta = 1e-5;
        try {
            double shippingCosts = calc.calcShippingCosts(pack);
            assertEquals(expectedCosts, shippingCosts, delta, "Shipping costs are not correct");
        } catch (Exception e) {
            fail("Exception thrown: " + e.getMessage());
        }
    }

    static Stream<Arguments> outOfRangeArgStream() {
        return Stream.of(
            // Test of all possible combinations of negative values
            Arguments.of(new Packet(100000, 1, 1, 1), Calculator.PacketValueExceptions.class),
            Arguments.of(new Packet(1, 100000, 1, 1), Calculator.PacketValueExceptions.class),
            Arguments.of(new Packet(1, 1, 100000, 1), Calculator.PacketValueExceptions.class),
            Arguments.of(new Packet(1, 1, 1, 100000), Calculator.PacketValueExceptions.class)
        );
    }
    @ParameterizedTest
    @MethodSource("outOfRangeArgStream")
    void testOutOfRangePackets(Packet pack) {
        Calculator calc = new Calculator();
        assertThrows(Calculator.PacketValueExceptions.class, () -> calc.calcShippingCosts(pack), "Exception not thrown for out of allowed range values");
    }

    static Stream<Arguments> negativePacketArgStream() {
        return Stream.of(
            // Test of all possible combinations of negative values
            Arguments.of(new Packet(-1, 1, 1, 1), Calculator.PacketValueExceptions.class),
            Arguments.of(new Packet(1, -1, 1, 1), Calculator.PacketValueExceptions.class),
            Arguments.of(new Packet(1, 1, -1, 1), Calculator.PacketValueExceptions.class),
            Arguments.of(new Packet(1, 1, 1, -1), Calculator.PacketValueExceptions.class)
        );
    }
    @ParameterizedTest
    @MethodSource("negativePacketArgStream")
    void testNegativeValuePacket(Packet pack) {
        Calculator calc = new Calculator();
        assertThrows(Calculator.PacketValueExceptions.class, () -> calc.calcShippingCosts(pack), "Exception not thrown for negative values");
    }

    @Test
    void testZeroValuePacket() {
        Calculator calc = new Calculator();
        Packet pack = new Packet(0, 0, 0, 0);
        assertThrows(Calculator.PacketValueExceptions.class, () -> calc.calcShippingCosts(pack), "Exception not thrown for negative values");
    }

    //TODO Aufgabe 8
    @Test
    public void testRandomPackagesShippingCosts() {
        Calculator calc = new Calculator();
        for (int i = 0; i < 1000; i++) {
            Packet packet = generateRandomPacket();

            try {
                double calculatedCost = calc.calcShippingCosts(packet);
                double expectedCost = calculateExpectedCost(packet);

                // Verify that the calculated cost matches the expected cost
                assertEquals(expectedCost, calculatedCost, 1e-5, "Shipping cost mismatch");

                // Additional conditions: Cost should be between 0 and 100
                assertTrue(calculatedCost >= 0 && calculatedCost <= 100, "Shipping cost out of bounds");

            } catch (Exception e) {
                // If the dimensions are out of bounds, an exception should be thrown
                fail("Test failure with Exception" + e);
            }
            }
        }
    
    /**
     * Generates a packet with random dimensions and weight
     * within the valid range.
     *
     * @return A randomly generated packet.
     */
    private Packet generateRandomPacket() {
        Random random = new Random();
        int length = random.nextInt(1200) + 1; // Length up to 1200 mm
        int width = random.nextInt(600) + 1;   // Width up to 600 mm
        int height = random.nextInt(600) + 1;  // Height up to 600 mm
        int weight = random.nextInt(31000) + 1; // Weight up to 31 kg

        return new Packet(length, width, height, weight);
    }
    /**
     * Calculates the expected shipping cost based on the package rules.
     *
     * @param packet The packet whose expected cost is to be calculated.
     * @return The expected shipping cost as a double.
     */
    private double calculateExpectedCost(Packet packet) {
        double shippingCosts = -1;

		Packet sortedPack = sortDimensions(packet);
		boolean girth = calcGirth(sortedPack.length, sortedPack.width, sortedPack.height);

		if ((sortedPack.width <= 300) && (sortedPack.height <= 150)) {
			if ((sortedPack.length <= 300) && (sortedPack.weight <= 1000)) {
				shippingCosts = 3.89;
			}
			else if ((sortedPack.length <= 600) && (sortedPack.weight <= 2000)) {
				shippingCosts = 4.39;
			}
		}
		if ((shippingCosts == -1) && (sortedPack.length <= 1200) && (sortedPack.width <= 600) && (sortedPack.height <= 600)) {
			if ((girth)) {
				if ((sortedPack.weight <= 5000)) {
					shippingCosts = 5.89;
				}
				else if ((sortedPack.weight <= 10000)) {
					shippingCosts = 7.99;
				}
			}
			if ((sortedPack.weight <= 31000) && (shippingCosts == -1)) {
				shippingCosts = 14.99;
			}
		}
		if ((shippingCosts == -1)) {
			//throw new PacketMeassurementsOutOfBounds("PacketMeassurements out of bounds");
		}
		return shippingCosts;
    }
    private Packet sortDimensions (Packet pack) {
		Integer[] dimensions = {pack.length, pack.width, pack.height};
		Arrays.sort(dimensions, Collections.reverseOrder());

		Packet newPacket = new Packet(dimensions[0], dimensions[1], dimensions[2], pack.weight);
		return newPacket;
	}
    private Boolean calcGirth (int length, int width, int height) {
		boolean girth = (length + 2 * width + 2 * height) <= 3000;
		return girth;
	}
}