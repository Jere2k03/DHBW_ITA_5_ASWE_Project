package data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;
import java.util.ArrayList;

/**
 * This class imports the shipping costs from a csv file.
 */
public class Importer {

    /**
     * Private constructor to prevent instantiation.
     */
    private Importer() {
    }

    /**
     * This method imports the shipping costs from a csv file.
     * @return The shipping costs as a list of decimal numbers
     */
    public static List<Double> importShippingCosts() { //TODO throw exception
        String path = "src/data/shippingCosts.csv";

        try {
            BufferedReader csvReader = new BufferedReader(new FileReader(path));

            List<Double> shippingCosts = new ArrayList<>();
            String row = csvReader.readLine();

            if (row != null) {
                String[] values = row.split(";");
                for (String value : values) {
                    shippingCosts.add(Double.parseDouble(value));
                }
            }
            csvReader.close();
            return shippingCosts;
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }
}