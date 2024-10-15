package control;

import data.Packet;

/**
 * This class calculates the shipping costs for a given pack.
*/
public class Calculator {

	/** 
	 * This method calculates the shipping costs for a given pack.
	 * 
	 * @param pack The pack with its properties for which the shipping costs should be calculated
	 * @return double The shipping costs for the given package as a decimal number
	 * @see Packet
	 */
	public double calcShippingCosts(Packet pack) {
		double shippingCosts;
		if ((pack.height <= 300) && (pack.width <= 300) && (pack.height <= 150)) {
			shippingCosts = 3.89;
		}
		if ((pack.height <= 600) && (pack.width <= 300) && (pack.height <= 150)) {
			shippingCosts = 4.39;
		}
		if ((pack.height <= 1200) && (pack.width <= 600) && (pack.height <= 600) && pack.weight <= 5000) {
			shippingCosts = 5.99;
		} else if (pack.weight <= 10000) {
			shippingCosts = 7.99;
		} else {
			shippingCosts = 14.99;
		}
		return shippingCosts;
	}
	
}
