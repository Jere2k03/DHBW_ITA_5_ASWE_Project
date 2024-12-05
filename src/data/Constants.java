package data;

/**
 * This class stores the constants used in the project.
 */
@SuppressWarnings("java:S1118")
public class Constants {

    /** Prevent instantiation */
    private Constants() {}

    /**
     * Exception messages
     */

    public static class Exceptions {
        public static final String NO_NEGATIVE_VALUES_MSG = "The packet must not consist of negative values or zeros.";
        public static final String INVALID_DIMENSIONS_MSG = "The package dimensions are out of bounds.";
        public static final String CSV_FILE_NOT_FOUND = "CSV-file not found";
        public static final String CSV_FILE_EMPTY = "CSV-file is empty";
        public static final String CSV_FILE_WRONG_FORMAT = "CSV-file has the wrong format.";
    }

    /**
     * Buttons in the application
     */
    public static class Buttons {
        public static final String CALCULATE_BUTTON = "Calculate";
        public static final String EXIT_BUTTON = "Exit";
        public static final String INFO_BUTTON = "Info";
        public static final String ABOUT_BUTTON = "About";
        public static final String SETTINGS_BUTTON = "Settings";
    }

    /**
     * Messages used throughout the application.
     */
    public static class Messages {
        public static final String WELCOME_MSG = "Welcome to the PackageCalculator!\nPlease enter the dimensions of your package.";
        public static final String ERROR_PREFIX = "Error: ";
        public static final String CALCULATION_SUCCESSFUL_MSG = "Shipping costs calculated successfully. \nCosts: ";
        public static final String OUT_OF_BOUNDS_ERROR_1 = "The input for {";
        public static final String OUT_OF_BOUNDS_ERROR_2 = "} is not numeric.";
    }

    /**
     * Labels, Titles, and Texts for UI elements.
     */
    public static class Labels {
        public static final String APP_NAME = "PackageCalculator";
        public static final String CALCULATOR_LABEL = "Calculator";
        public static final String LENGTH_LABEL = "Length: ";
        public static final String WIDTH_LABEL = "Width: ";
        public static final String HEIGHT_LABEL = "Height: ";
        public static final String WEIGHT_LABEL = "Weight: ";
        public static final String SHIPPING_COSTS_LABEL = "Shipping costs: ";
    }

    /**
     * Style constants for UI elements.
     */
    public static class Styles {
        public static final String LABEL_FONT = "Arial";
        public static final String LABEL_STYLE_CALCAREA = "-fx-font-size: 16px; -fx-font-weight: bold;";
        public static final String CALCBUTTON_STYLE = "-fx-background-color: #2596be; -fx-text-fill: white; -fx-font-size: 16px; -fx-font-weight: bold; -fx-padding: 10px 20px;";
        public static final String STYLE_MSGAREA = "-fx-font-size: 16px;";
        public static final String STYLE_BTN_CALCAREA = "-fx-background-color: #f0f0f0; -fx-text-fill: black; -fx-font-size: 14px; -fx-padding: 10px; -fx-border-color: #d3d3d3; -fx-border-width: 1px;";
    }

    /**
     * Tooltips for UI elements.
     */
    public static class Tooltips {
        public static final String TOOLTIP_MSGAREA = "The error messages and the shipping cost will be displayed here.";
        public static final String TOOLTIP_EXIT_BTN = "Exit the application";
        public static final String TOOLTIP_SETTINGS_BTN = "Open settings window";
        public static final String TOOLTIP_ABOUT_BTN = "Open project repository";
        public static final String TOOLTIP_INFO_BTN = "Show information about the application";
        public static final String TOOLTIP_LENGTH_TF = "Enter the length of the package in millimeters";
        public static final String TOOLTIP_WIDTH_TF = "Enter the width of the package in millimeters";
        public static final String TOOLTIP_HEIGHT_TF = "Enter the height of the package in millimeters";
        public static final String TOOLTIP_WEIGHT_TF = "Enter the weight of the package in grams";
        public static final String TOOLTIP_SHIPPINGCOSTS_LABEL = "The calculated shipping costs will be displayed here";
        public static final String TOOLTIP_CALCBUTTON = "Calculate the shipping costs based on the entered package dimensions and weight";
    }

    /**
     * File paths used in the application.
     */
    public static class FilePaths {
        public static final String SHIPPING_COSTS_FILE = "src/data/ShippingCosts.csv";
        public static final String ICON_PATH = "file:src/gui/pictures/package_icon.png";
        public static final String DARKMODE_ICON_PATH = "file:src/gui/pictures/darkmode_icon.png";
    }

    /**
     * Dialog constants for UI interactfions.
     */
    public static class Dialogs {
        public static final String INFO_TITLE = "Info";
        public static final String SETTINGS_TITLE = "Settings";
        public static final String EXIT_DIALOG_TITLE = "Exit Package Calculator";
        public static final String EXIT_DIALOG_HEADER = "Are you sure you want to exit the application?";
        public static final String ABOUT_TITLE = "Open Project Repository";
        public static final String ABOUT_HEADER = "Are you sure you want to open the project repository?";
        public static final String ABOUT_ERROR_HEADER = "Error while opening the project repository";
        public static final String ABOUT_ERROR_CONTENT = "The project repository could not be opened.";
        public static final String ERROR_DIALOG_TITLE = "Error";
        public static final String INFO_ERROR_DIALOG_HEADER = "Error while loading the price list";
        public static final String INFO_ERROR_DIALOG_CONTENT = "Was not able to load the price list. Please check the file.";
    }

    /**
     * Measurements, units, and other dimension-related constants.
     */
    public static class Measurements {
        public static final String MM = "mm";
        public static final String G = "g";
        public static final String SMALL_PACKET_SIZE = "up to 300x300x150";
        public static final String MEDIUM_PACKET_SIZE = "up to 600x300x150";
        public static final String BIG_PACKET_SIZE = "up to 1200x600x600";
        public static final String GIRTH_SIZE = "up to 300cm";
        public static final String NO_GIRTH = "-";
        public static final String PACKET_WEIGHT_1000 = "up to 1000g";
        public static final String PACKET_WEIGHT_2000 = "up to 2000g";
        public static final String PACKET_WEIGHT_5000 = "up to 5000g";
        public static final String PACKET_WEIGHT_10000 = "up to 10000g";
        public static final String PACKET_WEIGHT_31000 = "up to 31000g";
    }

    /**
     * Table headers for info display.
     */
    public static class TableHeaders {
        public static final String PACKET_SIZE_HEADER = "Package size [mm]";
        public static final String PACKET_WEIGHT_HEADER = "Package weight [g]";
        public static final String GIRTH_HEADER = "Belt size [cm]";
        public static final String SHIPPING_COSTS_HEADER = "Shipping costs [€]";
    }

    /**
     * Symbols and other constants.
     */
    public static class Symbols {
        public static final String QUESTIONMARK = "?";
        public static final String EURO = " €";
        public static final String CSV_DELIMITER = ";";
    }

    /**
     * Application metadata and repository information.
     */
    public static class Metadata {
        public static final String INFO_TEXT = "Package Calculator v0.3 \n\t© 2020 I. Bogicevic \n\t© 2024 J. Matt";
        public static final String PROJECT_REPO_URI = "https://github.com/Jere2k03/DHBW_ITA_5_ASWE_Project";
    }
}
