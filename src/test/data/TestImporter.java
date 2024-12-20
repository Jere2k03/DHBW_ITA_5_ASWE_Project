package test.data;

import data.Importer;
import data.Exceptions.CSVWrongFormatException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import java.nio.file.Path;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

/**
 * The {@code TestImporter} class tests the {@link Importer} class with all edge cases.
 */
class TestImporter {

    /**
     * Temporary directory for the test
     */
    @TempDir
    Path tempDir;

    /**
     * Test if the method imports the shipping costs correctly
     * @throws CSVWrongFormatException
     */
    @Test
    public void testImportShippingCostsSuccess() throws CSVWrongFormatException {
        
        Importer.setPath("src/test/data/possibleCSVs/realShippingCosts.csv");
        List<Double> shippingCosts = Importer.importShippingCosts();

        assertNotNull(shippingCosts);
        assertEquals(5, shippingCosts.size());
        assertEquals(3.89, shippingCosts.get(0));
        assertEquals(4.39, shippingCosts.get(1));
        assertEquals(5.89, shippingCosts.get(2));
        assertEquals(7.99, shippingCosts.get(3));
        assertEquals(14.99, shippingCosts.get(4));
    }

    /**
     * Test if the method throws an exception if the file has the wrong format (too much values)
     */
    @Test
    public void testImportShippingCostsInvalidFormatTooManyValues() {
        Importer.setPath("src/test/data/possibleCSVs/wrongFormatShippingCosts_tooManyValues.csv");

        assertThrows(CSVWrongFormatException.class, Importer::importShippingCosts);
    }

    /**
     * Test if the method throws an exception if the file has the wrong format (not enough values)
     */
    @Test
    public void testImportShippingCostsInvalidFormatNonNumericValues()   {
        Importer.setPath("src/test/data/possibleCSVs/wrongFormatShippingCosts_noNumbers.csv");

        assertThrows(CSVWrongFormatException.class, Importer::importShippingCosts);
    }

    /**
     * Test if the method throws an exception if the file is empty
     */
    @Test
    public void testImportShippingCostsEmptyFile()    {
        Importer.setPath("src/test/data/possibleCSVs/emptyShippingCosts.csv");

        assertThrows(CSVWrongFormatException.class, Importer::importShippingCosts);
    }
}
