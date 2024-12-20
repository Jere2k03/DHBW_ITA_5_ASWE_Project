package control;

import control.Exceptions.PacketOutOfBoundsException;
import data.Packet;
import data.Exceptions.CSVWrongFormatException;
import data.Importer;
import data.Constants;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * The {@code Calculator} class provides functionality to calculate shipping costs 
 * for a given packet based on its dimensions and weight. 
 * 
 * <p>This class uses a CSV file to import shipping cost data and determines the cost 
 * by comparing the packet's properties against predefined thresholds. </p>
 */
public class Calculator {

	/** 
	 * This method calculates the shipping costs for a given pack.
	 * 
	 * @param pack The packet with its properties for which the shipping costs should be calculated (in mm and g)
	 * @return The shipping costs (in €) for the given packet as a decimal number
	 * @throws CSVWrongFormatException if the csv file with the shipping costs cannot be found or read
	 * @throws PacketOutOfBoundsException if the values of the pack are negative or out of bounds
	 * @see Packet
	 */
	public double calcShippingCosts(Packet pack) throws CSVWrongFormatException, PacketOutOfBoundsException {
		List<Double> shippingCostsList = new ArrayList<>();
		double shippingCosts = 0;
		int girth;

		try	{
			// set the path of the csv file
			Importer.setPath(Constants.FilePaths.SHIPPING_COSTS_FILE);
			shippingCostsList = Importer.importShippingCosts();
		}
		catch (CSVWrongFormatException e) {
			// throw an exception if the csv file cannot be found or read
			throw new CSVWrongFormatException(e.getMessage());
		}

		// sort the packet values in ascending order (length -> width -> height)
		sortPackageValues(pack);

		// check if the packet is in bounds
		checkBoundsAndValues(pack);

		// calculate the girth measure of the packet
		girth = calcGirth(pack);

		// calculate the shipping costs in €
		shippingCosts = calcLogic(girth, pack, shippingCostsList);

		// return the shipping costs of the packet in €
		return shippingCosts;
	}

	/**
	 * This method includes the logic for calculating the shipping costs for a given packet.
	 * @param girth the girth of the packet
	 * @param pack the packet for which the shipping costs should be calculated
	 * @param shippingCostsList the list of shipping costs for different packet sizes
	 * @return the shipping costs as double
	 */
	private double calcLogic(int girth, Packet pack, List<Double> shippingCostsList) {
		// Initialize the shipping costs as 0
		double shippingCosts = 0;

		// Check if the pack is small (300x300x150 mm and 1000 g)
		if (isSmallPacket(pack)) {
			shippingCosts = shippingCostsList.get(0);
		}
		// Check if the pack is medium (600x300x150 mm and 2000 g)
		else if (isMediumPacket(pack)) {
			shippingCosts = shippingCostsList.get(1);
		}
		// Check if the pack is large (1200x600x600 mm and 5000 g)
		else if(isBigPacket(pack)) {
			// check if the girth is less than or equal to 3000 mm and the weight is less than or equal to 5000 g
			if (girthAndWeightCheck(pack, girth, 5000)) {
				shippingCosts = shippingCostsList.get(2);
			}
			// check if the girth is less than or equal to 3000 mm and the weight is less than or equal to 10000 g
			else if(girthAndWeightCheck(pack, girth, 10000))	{
				shippingCosts = shippingCostsList.get(3);
			}
			// check if the pack is extra large (31000 g)
			else if(pack.getLength() <= 31000) {
				shippingCosts = shippingCostsList.get(4);
			}
		}

		// return the shipping costs
		return shippingCosts;
	}

	/**
	 * This method checks if a packet is small (300x300x150 [mm], 1000g).
	 * @param pack the packet to be checked
	 * @return true if the packet is small, false otherwise
	 */
	private boolean isSmallPacket(Packet pack) {
		boolean isSmallPacket = false;
		if (((pack.getLength() <= 300) && (pack.getWidth() <= 300) && (pack.getHeight() <= 150)) && (pack.getWeight() <= 1000)) {
			isSmallPacket = true;
		}
		else{
			isSmallPacket = false;
		}
		return isSmallPacket;
	}

	/**
	 * This method checks if a packet is medium (600x300x150 [mm], 2000g).
	 * @param pack the packet to be checked
	 * @return true if the packet is medium, false otherwise
	 */
	private boolean isMediumPacket(Packet pack) {
		boolean isMediumPackage = false;
		if (((pack.getLength() <= 600) && (pack.getWidth() <= 300) && (pack.getHeight() <= 150)) && (pack.getWeight() <= 2000)) {
			isMediumPackage = true;
		}
		else{
			isMediumPackage = false;
		}
		return isMediumPackage;
	}

	/**
	 * This method checks if a packet is big (1200x600x600 [mm]).
	 * @param pack the packet to be checked
	 * @return true if the pack is big, false otherwise
	 */
	private boolean isBigPacket(Packet pack)	{
		boolean isBigPackage = false;
		if ((pack.getLength() <= 1200) && (pack.getWidth() <= 600) && (pack.getHeight() <= 600)) {
			isBigPackage = true;
		}
		else{
			isBigPackage = false;
		}
		return isBigPackage;
	}

	/**
	 * This method checks if the girth and weight of a pack are within the allowed range (girth <= 3000mm).
	 * 
	 * @param pack the pack to be checked
	 * @param girth the girth of the pack
	 * @param maxWeight the maximum allowed weight of the pack
	 * @return true if the girth and weight are within the allowed range, false otherwise
	 */
	private boolean girthAndWeightCheck(Packet pack, int girth, int maxWeight) {
		boolean isInBounds = false;
		if ((girth <= 3000) && (pack.getWeight() <= maxWeight)) {
			isInBounds = true;
		}
		else {
			isInBounds = false;
		}
		return isInBounds;
	}

	/**
	 * This method checks if the values of a pack are positive and within the allowed range (<1200mm, <600mm, 600mm, <31000g).
	 * 
	 * @param pack the pack to be checked
	 * @throws PacketOutOfBoundsException if the values are negative or out of bounds
	 */
	private void checkBoundsAndValues(Packet pack) throws PacketOutOfBoundsException {
		// Check if the values are positive and within the allowed range
		if (((pack.getLength() <= 0) || (pack.getWidth() <= 0) || (pack.getHeight() <= 0)) || (pack.getWeight() <= 0)) {
			throw new PacketOutOfBoundsException(Constants.Exceptions.NO_NEGATIVE_VALUES_MSG);
		}
		if (((pack.getLength() > 1200) || (pack.getWidth() > 600) || (pack.getHeight() > 600)) || (pack.getWeight() > 31000))	{
			throw new PacketOutOfBoundsException(Constants.Exceptions.INVALID_DIMENSIONS_MSG);
		}
	}

	/**
	 * This method calculates the girth measurement of a package.
	 * 
	 * @param height height of the packet
	 * @param width width of the packet
	 * @param length length of the packet
	 * @return the girth measure of the packet
	 */
	private int calcGirth(Packet pack) {

		@SuppressWarnings({"java:51488"})
		// calculate the girth measure of the package
		int girth = (pack.getLength() + 2 * pack.getWidth() + 2 * pack.getHeight());

		// returns the girth measure of the package
		return girth;
	}

	/**
	 * This method sorts the values of a pack in ascending order (length -> width -> height).
	 * 
	 * @param pack the packet to be sorted
	 * @return the sorted packet
	 * @see Packet
	 */
	private Packet sortPackageValues(Packet pack)	{
		// Create an array with the dimensions
        int[] dimensions = {pack.getLength(), pack.getWidth(), pack.getHeight()};
        
        // Sort the array in ascending order
        Arrays.sort(dimensions);
        
        // Update the pack with the sorted dimensions
        pack.setLength(dimensions[2]); // Largest value
        pack.setWidth(dimensions[1]);  // Middle value
        pack.setHeight(dimensions[0]); // Smallest value
        
        // Return the sorted pack
        return pack;
	}
}