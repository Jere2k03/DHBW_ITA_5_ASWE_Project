package control;

import data.Packet;
import data.Importer;

import java.util.List;
import java.util.ArrayList;

/**
 * This class calculates the shipping costs for a given pack.
*/
public class Calculator {

	public class PacketValueExceptions extends Exception {
		public PacketValueExceptions(String message) {
			super(message);
		}
	}

	/** 
	 * This method calculates the shipping costs for a given pack.
	 * 
	 * @param pack The pack with its properties for which the shipping costs should be calculated in mm and g
	 * @return The shipping costs (in €) for the given package as a decimal number
	 * @throws PacketValueExceptions
	 * @see Packet
	 */
	public double calcShippingCosts(Packet pack) throws PacketValueExceptions {
		double shippingCosts = 0;
		int girth;
		List<Double> shippingCostsList = Importer.importShippingCosts();

		// sort the pack values in ascending order (length -> width -> height)
		pack = sortPackageValues(pack);

		// exception handling of the pack values //TODO exception auslagern
		if (((pack.length <= 0) || (pack.width <= 0) || (pack.height <= 0)) || (pack.weight <= 0)) {
			throw new PacketValueExceptions("The package needs positive values.");
		}
		if (((pack.length > 1200) || (pack.width > 600) || (pack.height > 600)) || (pack.weight > 31000))	{
			throw new PacketValueExceptions("The package is out of the allowed range.");
		}

		// calculate the girth measure of the package
		girth = calcGirth(pack.length, pack.width, pack.height);

		// calculate the shipping costs in euro
		if (((pack.length <= 300) && (pack.width <= 300) && (pack.height <= 150)) && (pack.weight <= 1000)) {
			shippingCosts = shippingCostsList.get(0);
		}
		else if (((pack.length <= 600) && (pack.width <= 300) && (pack.height <= 150)) && (pack.weight <= 2000)) {
			shippingCosts = shippingCostsList.get(1);
		}
		else if((pack.length <= 1200) && (pack.width <= 600) && (pack.height <= 600)) {
			if ((girth <= 3000) && (pack.weight <= 5000)) {
				shippingCosts = shippingCostsList.get(2);
			}
			else if((girth <= 3000) && (pack.weight <= 10000))	{
				shippingCosts = shippingCostsList.get(3);
			}
			else if(pack.weight <= 31000) {
				shippingCosts = shippingCostsList.get(4);
			}
		}

		// return the shipping costs of the packet in euro
		return shippingCosts;
	}

	/**
	 * This method calculates the girth measurement of a package.
	 * 
	 * @param height
	 * @param width
	 * @param length
	 * @return the girth measure of the package
	 */
	public int calcGirth(int length, int width, int height) {

		@SuppressWarnings({"java:51488"})
		// calculate the girth measure of the package
		int girth = (length + 2 * width + 2 * height);

		// returns the girth measure of the package
		return girth;
	}

	/**
	 * This method sorts the values of a pack in ascending order (length -> width -> height).
	 * 
	 * @param pack the pack to be sorted
	 * @return the sorted pack
	 * @see Packet
	 */
	public Packet sortPackageValues(Packet pack)	{

		// sort the pack values in ascending order (length -> width -> height)
		if (pack.length < pack.width) {
			int temp = pack.length;
			pack.length = pack.width;
			pack.width = temp;
		}
		if (pack.length < pack.height) {
			int temp = pack.length;
			pack.length = pack.height;
			pack.height = temp;
		}
		if (pack.width < pack.height) {
			int temp = pack.width;
			pack.width = pack.height;
			pack.height = temp;
		}

		// return the sorted pack
		return pack;
	}
}
