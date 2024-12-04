package data;

/**
 * Represents a package with dimensions (length, width, height) in millimeters
 * and weight in grams. This class stores the basic properties of a package
 * and provides a constructor to initialize these properties.
 */
public class Packet {

    /**
     * The dimensions and weight of the package.
     */
    private int length;
    private int width;
    private int height;
    private int weight;

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
    

    /**
     * Returns the length of the package in millimeters.
     *
     * @return The length of the package in millimeters
     */
    public int getLength() {
        return length;
    }

    /**
     * Returns the width of the package in millimeters.
     *
     * @return The width of the package in millimeters
     */
    public int getWidth() {
        return width;
    }

    /**
     * Returns the height of the package in millimeters.
     *
     * @return The height of the package in millimeters
     */
    public int getHeight() {
        return height;
    }

    /**
     * Returns the weight of the package in grams.
     *
     * @return The weight of the package in grams
     */
    public int getWeight() {
        return weight;
    }

    /**
     * Sets the length of the package in millimeters.
     *
     * @param length The new length of the package in millimeters
     */
    public void setLength(int length) {
        this.length = length;
    }

    /**
     * Sets the width of the package in millimeters.
     *
     * @param width The new width of the package in millimeters
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * Sets the height of the package in millimeters.
     *
     * @param height The new height of the package in millimeters
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * Sets the weight of the package in grams.
     *
     * @param weight The new weight of the package in grams
     */
    public void setWeight(int weight) {
        this.weight = weight;
    }
}