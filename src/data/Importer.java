package data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import data.Exceptions.CSVWrongFormatException;

import java.util.ArrayList;
import java.io.File;


/**
 * This class imports the shipping costs from a csv file.
 */
public class Importer {

    private static String path;

    /**
     * Constructor for the Importer class.
     */
    private Importer() {
        // empty to prevent instantiation
    }

    /**
     * Setter for the path of the csv file
     * @param path
     */
    public static void setPath(String path) {
        Importer.path = path;
    }

    /**
     * This method imports the shipping costs from a csv file.
     * @return The shipping costs as a list of decimal numbers
     * @throws CSVWrongFormatException if the csv file cannot be found or read or has the wrong format
     */
    public static List<Double> importShippingCosts() throws CSVWrongFormatException {
        if (!new File(path).exists()) {
            throw new CSVWrongFormatException(Constants.Exceptions.CSV_FILE_NOT_FOUND);
        }

        try (BufferedReader csvReader = new BufferedReader(new FileReader(path))) {
            List<Double> shippingCosts = new ArrayList<>();
            String row = csvReader.readLine();

            if (row != null) {
                String[] values = row.split(Constants.Symbols.CSV_DELIMITER);
                // Check if the first row has the correct format
                if (values.length != 5 || !checkArrayForNumeric(values)) {
                    throw new IOException(Constants.Exceptions.CSV_FILE_WRONG_FORMAT);
                }
                for (String value : values) {
                    shippingCosts.add(Double.parseDouble(value));
                }
            } else {
                throw new IOException(Constants.Exceptions.CSV_FILE_EMPTY);
            }
            return shippingCosts;
        } catch (IOException e) {
            throw new CSVWrongFormatException(e.getMessage());
        }
    }

    /**
     * This method checks if all elements of a string array are numeric.
     * @param stringArray
     * @return true if all elements are numeric, false otherwise
     */
    private static boolean checkArrayForNumeric(String[] stringArray) {
        boolean isNumeric = true;
        for (String string : stringArray) {
            try {
                Double.parseDouble(string);
            } catch (NumberFormatException e) {
                isNumeric = false;
                break;
            }
        }
        return isNumeric;
    }
}