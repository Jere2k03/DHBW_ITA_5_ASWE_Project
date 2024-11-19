package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;

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
            //TODO: Add more test cases (unterer edge, oberer edge, normal cases)

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

    @Test
    void testOutOfRangePacket() {
        Calculator calc = new Calculator();
        Packet pack = new Packet(1300, 700, 700, 100000);
        assertThrows(Calculator.PacketValueExceptions.class, () -> calc.calcShippingCosts(pack), "Exception not thrown for out of range packet");
    }

    static Stream<Arguments> negativePacketArgStream() {
        return Stream.of(
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
}