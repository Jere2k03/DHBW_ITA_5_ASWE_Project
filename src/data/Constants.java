package data;

/**
 * This class stores the constants used in the project.
 */
@SuppressWarnings("java:S1118")
public class Constants {

    /** Prevent instantiation */
    private Constants() {}

    /**
     * all possible Exception messages
     */
    public static class Exceptions {
        /** Error message when negative values or zeros are encountered in a packet. */
        public static final String NO_NEGATIVE_VALUES_MSG = "The packet must not consist of negative values or zeros.";
        
        /** Error message when package dimensions are out of bounds. */
        public static final String INVALID_DIMENSIONS_MSG = "The package dimensions are out of bounds.";
        
        /** Error message when the CSV file is not found. */
        public static final String CSV_FILE_NOT_FOUND = "CSV-file not found";
        
        /** Error message when the CSV file is empty. */
        public static final String CSV_FILE_EMPTY = "CSV-file is empty";
        
        /** Error message when the CSV file format is incorrect. */
        public static final String CSV_FILE_WRONG_FORMAT = "CSV-file has the wrong format.";
    }

    /**
     * Buttons in the application
     */
    public static class Buttons {
        /** Text for the calculate button. */
        public static final String CALCULATE_BUTTON = "Calculate";
        
        /** Text for the exit button. */
        public static final String EXIT_BUTTON = "Exit";
        
        /** Text for the info button. */
        public static final String INFO_BUTTON = "Info";
        
        /** Text for the about button. */
        public static final String ABOUT_BUTTON = "About";
        
        /** Text for the settings button. */
        public static final String SETTINGS_BUTTON = "Settings";
    }

    /**
     * Messages used throughout the application.
     */
    public static class Messages {
        /** Welcome message shown when the application starts. */
        public static final String WELCOME_MSG = "Welcome to the PackageCalculator!\nPlease enter the dimensions of your package.";
        
        /** Prefix for error messages. */
        public static final String ERROR_PREFIX = "Error: ";
        
        /** Message shown when the calculation of shipping costs is successful. */
        public static final String CALCULATION_SUCCESSFUL_MSG = "Shipping costs calculated successfully. \nCosts: ";
        
        /**  Error message when an input value is out of bounds. */
        public static final String OUT_OF_BOUNDS_ERROR_1 = "The input for {";
        
        /** Error message when an input value is not numeric. */
        public static final String OUT_OF_BOUNDS_ERROR_2 = "} is not numeric.";
    }

    /**
     * Labels, Titles, and Texts for UI elements.
     */
    public static class Labels {
        /** The name of the application shown in the UI. */
        public static final String APP_NAME = "PackageCalculator";
        
        /** Label for the calculator section in the UI. */
        public static final String CALCULATOR_LABEL = "Calculator";
        
        /** Label for the length input field. */
        public static final String LENGTH_LABEL = "Length: ";
        
        /** Label for the width input field. */
        public static final String WIDTH_LABEL = "Width: ";
        
        /** Label for the height input field. */
        public static final String HEIGHT_LABEL = "Height: ";
        
        /** Label for the weight input field. */
        public static final String WEIGHT_LABEL = "Weight: ";
        
        /** Label for the shipping costs output. */
        public static final String SHIPPING_COSTS_LABEL = "Shipping costs: ";
    }

    /**
     * Style constants for UI elements.
     */
    public static class Styles {
        /** Font used for labels. */
        public static final String LABEL_FONT = "Arial";
        
        /** Style for calculator labels. */
        public static final String LABEL_STYLE_CALCAREA = "-fx-font-size: 16px; -fx-text-fill: #555555;";
        
        /** Style for the message area. */
        public static final String STYLE_MSGAREA = "-fx-font-size: 16px;";
        
        /** Style for the calculate button. */
        public static final String STYLE_CALCBTN = "-fx-background-color: #a8d8e7; -fx-font-size: 16px; -fx-border-radius: 5px; -fx-text-fill: #fff;";
        
        /** Hover style for the calculate button. */
        public static final String STYLE_CALCBTN_HOVER = "-fx-background-color: #85c6d7; -fx-font-size: 16px; -fx-border-radius: 5px; -fx-text-fill: #fff;";
        
        /** Style for other buttons in the application. */
        public static final String STYLE_BTN_TBAREA = "-fx-background-color: #f0f0f0; -fx-text-fill: black; -fx-font-size: 16px; -fx-padding: 10px; -fx-border-color: #d3d3d3; -fx-border-width: 1px;";
        
        /** Hover style for other buttons in the application. */
        public static final String STYLE_BTN_HOVER_TBAREA = "-fx-background-color: #b2b2b2; -fx-font-size: 16px; -fx-padding: 10px;";
    }

    /**
     * Tooltips for UI elements.
     */
    public static class Tooltips {
        /** Tooltip for the message area. */
        public static final String TOOLTIP_MSGAREA = "The error messages and the shipping cost will be displayed here.";
        
        /** Tooltip for the exit button. */
        public static final String TOOLTIP_EXIT_BTN = "Exit the application";
        
        /** Tooltip for the settings button. */
        public static final String TOOLTIP_SETTINGS_BTN = "Open settings window";
        
        /** Tooltip for the about button. */
        public static final String TOOLTIP_ABOUT_BTN = "Open project repository";
        
        /** Tooltip for the info button. */
        public static final String TOOLTIP_INFO_BTN = "Show information about the application";
        
        /** Tooltip for the length input field. */
        public static final String TOOLTIP_LENGTH_TF = "Enter the length of the package in millimeters";
        
        /** Tooltip for the width input field. */
        public static final String TOOLTIP_WIDTH_TF = "Enter the width of the package in millimeters";
        
        /** Tooltip for the height input field. */
        public static final String TOOLTIP_HEIGHT_TF = "Enter the height of the package in millimeters";
        
        /** Tooltip for the weight input field. */
        public static final String TOOLTIP_WEIGHT_TF = "Enter the weight of the package in grams";
        
        /** Tooltip for the shipping costs label. */
        public static final String TOOLTIP_SHIPPINGCOSTS_LABEL = "The calculated shipping costs will be displayed here";
        
        /** Tooltip for the calculate button. */
        public static final String TOOLTIP_CALCBUTTON = "Calculate the shipping costs based on the entered package dimensions and weight";
    }

    /**
     * File paths used in the application.
     */
    public static class FilePaths {
        /** Path to the CSV file containing the shipping costs. */
        public static final String SHIPPING_COSTS_FILE = "src/data/ShippingCosts.csv";
        
        /** Path to the icon image file. */
        public static final String ICON_PATH = "file:src/gui/pictures/package_icon.png";
        
        /** Path to the dark mode icon image file. */
        public static final String DARKMODE_ICON_PATH = "file:src/gui/pictures/darkmode_icon.png";
    }

    /**
     * Dialog constants for UI interactions.
     */
    public static class Dialogs {
        /** Title for the information dialog. */
        public static final String INFO_TITLE = "Info";
        
        /** Title for the settings dialog. */
        public static final String SETTINGS_TITLE = "Settings";
        
        /** Title for the exit confirmation dialog. */
        public static final String EXIT_DIALOG_TITLE = "Exit Package Calculator";
        
        /** Header for the exit confirmation dialog. */
        public static final String EXIT_DIALOG_HEADER = "Are you sure you want to exit the application?";
        
        /** Title for the about dialog. */
        public static final String ABOUT_TITLE = "Open Project Repository";
        
        /** Header for the about dialog. */
        public static final String ABOUT_HEADER = "Are you sure you want to open the project repository?";
        
        /** Header for the error dialog when the project repository cannot be opened. */
        public static final String ABOUT_ERROR_HEADER = "Error while opening the project repository";
        
        /** Content for the error dialog when the project repository cannot be opened. */
        public static final String ABOUT_ERROR_CONTENT = "The project repository could not be opened.";
        
        /** Title for the error dialog.  */
        public static final String ERROR_DIALOG_TITLE = "Error";
        
        /** Header for the error dialog when loading the price list fails. */
        public static final String INFO_ERROR_DIALOG_HEADER = "Error while loading the price list";
        
        /** Content for the error dialog when loading the price list fails. */
        public static final String INFO_ERROR_DIALOG_CONTENT = "Was not able to load the price list. Please check the file.";
    }

    /**
     * Measurements, units, and other dimension-related constants.
     */
    public static class Measurements {
        /** Unit for millimeters.  */
        public static final String MM = "mm";
        
        /** Unit for grams. */
        public static final String G = "g";
        
        /** Size for a small packet.  */
        public static final String SMALL_PACKET_SIZE = "up to 300x300x150";
        
        /** Size for a medium packet.  */
        public static final String MEDIUM_PACKET_SIZE = "up to 600x300x150";
        
        /** Size for a big packet. */
        public static final String BIG_PACKET_SIZE = "up to 1200x600x600";
        
        /** Maximum girth size. */
        public static final String GIRTH_SIZE = "up to 300cm";
        
        /** No girth size. */
        public static final String NO_GIRTH = "-";
        
        /** Maximum weight for a 1000g packet. */
        public static final String PACKET_WEIGHT_1000 = "up to 1000g";
        
        /** Maximum weight for a 2000g packet. */
        public static final String PACKET_WEIGHT_2000 = "up to 2000g";
        
        /** Maximum weight for a 5000g packet. */
        public static final String PACKET_WEIGHT_5000 = "up to 5000g";
        
        /** Maximum weight for a 10000g packet. */
        public static final String PACKET_WEIGHT_10000 = "up to 10000g";
        
        /** Maximum weight for a 31000g packet. */
        public static final String PACKET_WEIGHT_31000 = "up to 31000g";
    }

    /**
     * Table headers for info display.
     */
    public static class TableHeaders {
        /** Header for the package size column. */
        public static final String PACKET_SIZE_HEADER = "Package size [mm]";
        
        /** Header for the package weight column. */
        public static final String PACKET_WEIGHT_HEADER = "Package weight [g]";
        
        /** Header for the girth size column. */
        public static final String GIRTH_HEADER = "Belt size [cm]";
        
        /** Header for the shipping costs column. */
        public static final String SHIPPING_COSTS_HEADER = "Shipping costs [€]";
    }

    /**
     * Symbols and other constants.
     */
    public static class Symbols {
        /** Question mark symbol. */
        public static final String QUESTIONMARK = "?";
        
        /** Euro symbol. */
        public static final String EURO = " €";
        
        /** Delimiter used in CSV files. */
        public static final String CSV_DELIMITER = ";";
    }

    /**
     * Application metadata and repository information.
     */
    public static class Metadata {
        /** Information text about the application version and author. */
        public static final String INFO_TEXT = "Package Calculator v0.3 \n\t© 2020 I. Bogicevic \n\t© 2024 J. Matt";
        
        /** URL of the project's repository. */
        public static final String PROJECT_REPO_URI = "https://github.com/Jere2k03/DHBW_ITA_5_ASWE_Project";
    }
}
