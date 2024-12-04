package data;

/**
 * This class stores the constants used in the project.
 */
public class Constants {
    /** Welcome message in the messagesArea */
    public static final String WELCOME_MSG = "Welcome to the PackageCalculator!\nPlease enter the dimensions of your package.";

    /** The name of the application. */
    public static final String APP_NAME = "PackageCalculator";

    /** Calculator label in the CalculatorArea */
    public static final String CALCULATOR_LABEL = "Calculator";

    /** Font size */
    public static final String LABEL_FONT = "Arial";

    /** style of Labels in CalcArea */
    public static final String LABEL_STYLE_CALCAREA = "-fx-font-size: 16px; -fx-font-weight: bold;";
    
    /** stly of Button in CalcArea */
    public static final String CALCBUTTON_STYLE = "-fx-background-color: #2596be; -fx-text-fill: white; -fx-font-size: 16px; -fx-font-weight: bold; -fx-padding: 10px 20px;";

    /** Text of the "Calculate"-Button */
    public static final String CALCULATE_BUTTON = "Calculate";

    /** Text of the "Exit"-Button */
    public static final String EXIT_BUTTON = "Exit";

    /** Text of the "Info"-Button */
    public static final String INFO_BUTTON = "Info";

    /** Text of the "About"-Button */
    public static final String ABOUT_BUTTON = "About";

    /** Text of the "Settings"-Button */
    public static final String SETTINGS_BUTTON = "Settings";

    /** Text of the "Length"-Label */
    public static final String LENGTH_LABEL = "Length: ";

    /** Text of the "Width"-Label */
    public static final String WIDTH_LABEL = "Width: ";

    /** Text of the "Height"-Label */
    public static final String HEIGHT_LABEL = "Height: ";

    /** Text of the "Weight"-Label */
    public static final String WEIGHT_LABEL = "Weight: ";

    /** Text of the "Shipping costs"-Label */
    public static final String SHIPPING_COSTS_LABEL = "Shipping costs: ";

    /** Constant for millimeter abbreviation*/
    public static final String MM = "mm";

    /** Constant for gramm abbreviation*/
    public static final String G = "g";

    /** The path to the shipping costs file */
    public static final String SHIPPING_COSTS_FILE = "src/data/ShippingCosts.csv";

    /** The path to the icon file */
    public static final String ICON_PATH = "file:src/gui/pictures/package_icon.png";

    /** The path to the darkmode toggle icon */
    public static final String DARKMODE_ICON_PATH = "file:src/gui/pictures/darkmode_icon.png";

    /** This URI is for the GitHub repo */
    public static final String PROJECT_REPO_URI = "https://github.com/Jere2k03/DHBW_ITA_5_ASWE_Project";

    /** Exception message for negative/zero packet values */
    public static final String NO_NEGATIVE_VALUES_MSG = "The packet must not consist of negative values or zeros.";

    /** Exception message for out of bounds packets */
    public static final String INVALID_DIMENSIONS_MSG = "The package dimensions are out of bounds.";

    /** Exception message if csv file isn't found */
    public static final String CSV_FILE_NOT_FOUND = "CSV-file not found";

    /** Exception message if csv file is empty */
    public static final String CSV_FILE_EMPTY = "CSV-file is empty";

    /** Exception message if csv file has the wrong format */
    public static final String CSV_FILE_WRONG_FORMAT = "CSV-file has the wrong format.";

    /** Questionmark: default symbol for calculator output */
    public static final String QUESTIONMARK = "?";

   /** Error prefix for error messages in MessagesArea */
    public static final String ERROR_PREFIX = "Error: ";

    /** Success message for a successful calculation in MessagesArea */
    public static final String CALCULATION_SUCCESSFUL_MSG = "Shipping costs calculated successfully. \nCosts: ";

    /** Euro symbol */
    public static final String EURO = " €";

    /** text for table: small package  */
    public static final String SMALL_PACKET_SIZE = "up to 300x300x150";

    /** text for table: medium package  */
    public static final String MEDIUM_PACKET_SIZE = "up to 600x300x150";

    /** text for table: big package  */
    public static final String BIG_PACKET_SIZE = "up to 1200x600x600";

    /** text for table: girth size  */
    public static final String GIRTH_SIZE = "up to 300cm";

    /** text for table: no girth needed  */
    public static final String NO_GIRTH = "-";

    /** text for table: packet weight to 1000 gramms  */
    public static final String PACKET_WEIGHT_1000 = "up to 1000g";

    /** text for table: packet weight to 2000 gramms  */
    public static final String PACKET_WEIGHT_2000 = "up to 2000g";

    /** text for table: packet weight to 5000 gramms  */
    public static final String PACKET_WEIGHT_5000 = "up to 5000g";

    /** text for table: packet weight to 10000 gramms  */
    public static final String PACKET_WEIGHT_10000 = "up to 10000g";

    /** text for table: packet weight to 31000 gramms  */
    public static final String PACKET_WEIGHT_31000 = "up to 31000g";

    /** Packet size header for table in info */
    public static final String PACKET_SIZE_HEADER = "Package size [mm]";

    /** Packet weight header for table in info */
    public static final String PACKET_WEIGHT_HEADER = "Package weight [g]";

    /** GIrth header for table in info */
    public static final String GIRTH_HEADER = "Belt size [cm]";

    /** Shipping costs header for table in info */
    public static final String SHIPPING_COSTS_HEADER = "Shipping costs [€]";

    /** Error dialog title for info */
    public static final String ERROR_DIALOG_TITLE = "Error";

    /** Error dialog header for info */
    public static final String INFO_ERROR_DIALOG_HEADER = "Error while loading the price list";

    /** Error dialog content for info */
    public static final String INFO_ERROR_DIALOG_CONTENT = "Was not able to load the price list. Please check the file.";

    /** Infotext: Version and Authors */
    public static final String INFO_TEXT = "Package Calculator v0.3 \n\t© 2020 I. Bogicevic \n\t© 2024 J. Matt";

    /** Title for the About dialog */
    public static final String ABOUT_TITLE = "Open Project Repository";

    /** Header for the About dialog */
    public static final String ABOUT_HEADER = "Are you sure you want to open the project repository?";

    /** Error Header in the About dialog */
    public static final String ABOUT_ERROR_HEADER = "Error while opening the project repository";

    /** Error Content in the About dialog */
    public static final String ABOUT_ERROR_CONTENT = "The project repository could not be opened.";

    /** Exit dialog title */
    public static final String EXIT_DIALOG_TITLE = "Exit Package Calculator";

    /** Exit dialog title */
    public static final String EXIT_DIALOG_HEADER = "Are you sure you want to exit the application?";

    /** Constant CSV Delimiter */
    public static final String CSV_DELIMITER = ";";

    /** Tooltip for MessagesArea */
    public static final String TOOLTIP_MSGAREA = "The error messages and the shipping cost will be displayed here.";

    /** style for MessagesArea */
    public static final String STYLE_MSGAREA = "-fx-font-size: 16px;";

    /** style for the button in the CalculatorArea */
    public static final String STYLE_BTN_CALCAREA = "-fx-background-color: #f0f0f0; -fx-text-fill: black; -fx-font-size: 14px; -fx-padding: 10px; -fx-border-color: #d3d3d3; -fx-border-width: 1px;";

    /** tooltip for the exit button */
    public static final String TOOLTIP_EXIT_BTN = "Exit the application";

    /** tooltip for the settings button */
    public static final String TOOLTIP_SETTINGS_BTN = "Open settings window";

    /** tooltip for the about button */
    public static final String TOOLTIP_ABOUT_BTN = "Open project repository";

    /** tooltip for the info button */
    public static final String TOOLTIP_INFO_BTN = "Show information about the application";

    /** error message for out of bounds p.1 */
    public static final String OUT_OF_BOUNDS_ERROR_1 = "The input for {";

    /** error message for out of bounds p.2 */
    public static final String OUT_OF_BOUNDS_ERROR_2 = "} is not numeric.";

    /** tooltip for length text field */
    public static final String TOOLTIP_LENGTH_TF = "Enter the length of the package in millimeters";

    /** tooltip for width text field */
    public static final String TOOLTIP_WIDTH_TF = "Enter the width of the package in millimeters";

    /** tooltip for height text field */
    public static final String TOOLTIP_HEIGHT_TF = "Enter the height of the package in millimeters";

    /** tooltip for weight text field */
    public static final String TOOLTIP_WEIGHT_TF = "Enter the weight of the package in grams";

    /** tooltip for shipping costs label */
    public static final String TOOLTIP_SHIPPINGCOSTS_LABEL = "The calculated shipping costs will be displayed here";

    /** tooltip for calc button */
    public static final String TOOLTIP_CALCBUTTON = "Calculate the shipping costs based on the entered package dimensions and weight";

    /** Private constructor */
    private Constants() {
        // Prevent instantiation
    }
}
