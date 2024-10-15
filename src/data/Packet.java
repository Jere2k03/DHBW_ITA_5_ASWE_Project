package data;

/**
 * Represents a package with dimensions (length, width, height) in millimeters
 * and weight in grams. This class stores the basic properties of a package
 * and provides a constructor to initialize these properties.
 */
public class Packet {

    /**
     * The length of the package in millimeters.
     */
    public int length;

    /**
     * The width of the package in millimeters.
     */
    public int width;
    
    /**
     * The height of the package in millimeters.
     */
    public int height;
    
    /**
     * The weight of the package in grams.
     */
    public int weight;

    /**
     * Constructs a new Packet object with the specified dimensions and weight.
     *
     * @param length The length of the package in millimeters
     * @param width The width of the package in millimeters
     * @param height The height of the package in millimeters
     * @param weight The weight of the package in grams
     */
    public Packet(int length, int width, int height, int weight) {
        this.length = length;
        this.width = width;
        this.height = height;
        this.weight = weight;
    }
    
}
