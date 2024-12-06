package gui;

import control.Calculator;
import control.Exceptions.PacketOutOfBoundsException;
import data.Packet;
import data.Exceptions.CSVWrongFormatException;
import data.Constants;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.control.Tooltip;
import javafx.scene.paint.Color;
import javafx.scene.effect.DropShadow;

/**
 * The CalculatorArea class represents the GUI component responsible for
 * calculating shipping costs based on user input of package dimensions and weight.
 * 
 * @version 0.3
 */
public class CalculatorArea extends GridPane {

    // Input fields for package dimensions and weight
    private TextField lengthTextField = new TextField();
    private TextField widthTextField  = new TextField();
    private TextField heightTextField = new TextField();
    private TextField weightTextField = new TextField();
    
    // Label to display the calculated shipping cost
    private Label shippingCostLabel = new Label(Constants.Symbols.QUESTIONMARK);
    
    // Button to trigger the calculation
    private Button calcButton = new Button(Constants.Buttons.CALCULATE_BUTTON);

    /**
     * Checks the user input for package dimensions and weight.
     * 
     * @return An array of integers containing the package dimensions and weight.
     * @throws PacketOutOfBoundsException if the input is not numeric or empty
     */
    private int[] checkInput() throws PacketOutOfBoundsException {
        int[] packageValues;
        try {
            // Get user input values
            int length = Integer.parseInt(lengthTextField.getText());
            int width = Integer.parseInt(widthTextField.getText());
            int height = Integer.parseInt(heightTextField.getText());
            int weight = Integer.parseInt(weightTextField.getText());

            packageValues = new int[] {length, width, height, weight};

            return packageValues;
        }
        catch (NumberFormatException e) {
            shippingCostLabel.setText(Constants.Symbols.QUESTIONMARK);

            String errorField = "";

            if (lengthTextField.getText().isEmpty() || !lengthTextField.getText().matches("\\d+")) {
                errorField = Constants.Labels.LENGTH_LABEL;
            } else if (widthTextField.getText().isEmpty() || !widthTextField.getText().matches("\\d+")) {
                errorField = Constants.Labels.WIDTH_LABEL;
            } else if (heightTextField.getText().isEmpty() || !heightTextField.getText().matches("\\d+")) {
                errorField = Constants.Labels.HEIGHT_LABEL;
            } else if (weightTextField.getText().isEmpty() || !weightTextField.getText().matches("\\d+")) {
                errorField = Constants.Labels.WEIGHT_LABEL;
            }

            throw new PacketOutOfBoundsException(Constants.Messages.ERROR_PREFIX + Constants.Messages.OUT_OF_BOUNDS_ERROR_1 + errorField + Constants.Messages.OUT_OF_BOUNDS_ERROR_2);
        }
    }

    /**
     * Calculates the shipping costs based on the package dimensions and weight entered by the user.
     * The calculation is performed by the {@link Calculator} class using the {@link Packet} object.
     *
     * @return The calculated shipping costs as a {@code double}.
     */
    private double calcShippingCosts() {
        Calculator calc = new Calculator(); // Initialize the calculator
        int length;
        int width;
        int height;
        int weight;
        Double costs; // initialize costs
        MessagesArea messagesArea = PackageCalculator.messagesArea; // initialize messagesArea
        
        try {
            //check user input
            int[] packageValues = checkInput(); // check user input
            length = packageValues[0];
            width = packageValues[1];
            height = packageValues[2];
            weight = packageValues[3];

            //set the package
            Packet packet = new Packet(length, width, height, weight);

            // Calculate the shipping costs
            costs = calc.calcShippingCosts(packet);

            // Display the result
            shippingCostLabel.setText(costs.toString() + Constants.Symbols.EURO);

            // show success message to user with the price in the messages area
            messagesArea.setMessage(Constants.Messages.CALCULATION_SUCCESSFUL_MSG + costs + Constants.Symbols.EURO);
        }
        catch (PacketOutOfBoundsException | CSVWrongFormatException e) {             
            // Label for the shipping costs
            shippingCostLabel.setText(Constants.Symbols.QUESTIONMARK); 

            messagesArea = PackageCalculator.messagesArea; // initialize messagesArea
            messagesArea.setMessage(e.getMessage()); // show error message to user in the messages area

            // return -1 to indicate an error
            return -1;
        }
        
        return costs;
    }
    
    /**
     * Constructs a new CalculatorArea object that initializes and arranges
     * the user interface components (input fields, labels, and buttons) for shipping cost calculation.
     */
    public CalculatorArea() {
        // Set standard padding between elements
        this.setPadding(new Insets(20, 20, 20, 20));
        this.setHgap(15);
        this.setVgap(15);
        
        // Center the grid in the window
        this.setAlignment(Pos.CENTER);
        
        // header label
        Label headerLabel = new Label(Constants.Labels.CALCULATOR_LABEL);
        headerLabel.setFont(Font.font(Constants.Styles.LABEL_FONT, FontWeight.BOLD, 28));  // Increased font size
        this.add(headerLabel, 1, 0, 3, 1);

        // labels for the input fields
        Label lengthLabel = new Label(Constants.Labels.LENGTH_LABEL);
        Label widthLabel = new Label(Constants.Labels.WIDTH_LABEL);
        Label heightLabel = new Label(Constants.Labels.HEIGHT_LABEL);
        Label weightLabel = new Label(Constants.Labels.WEIGHT_LABEL);
        
        // label style
        lengthLabel.setStyle(Constants.Styles.LABEL_STYLE_CALCAREA);
        widthLabel.setStyle(Constants.Styles.LABEL_STYLE_CALCAREA);
        heightLabel.setStyle(Constants.Styles.LABEL_STYLE_CALCAREA);
        weightLabel.setStyle(Constants.Styles.LABEL_STYLE_CALCAREA);

        this.add(lengthLabel, 1, 1);
        this.add(widthLabel, 1, 2);
        this.add(heightLabel, 1, 3);
        this.add(weightLabel, 1, 4);

        // Add input fields for dimensions and weight with larger font size
        this.add(lengthTextField, 2, 1);
        this.add(widthTextField,  2, 2);
        this.add(heightTextField, 2, 3);
        this.add(weightTextField, 2, 4);

        // Add unit labels (mm and g)
        Label mmLabel1 = new Label(Constants.Measurements.MM);
        Label mmLabel2 = new Label(Constants.Measurements.MM);
        Label mmLabel3 = new Label(Constants.Measurements.MM);
        Label gLabel = new Label(Constants.Measurements.G);

        // Apply minimal style to the unit labels with increased font size
        mmLabel1.setStyle(Constants.Styles.LABEL_STYLE_CALCAREA);
        mmLabel2.setStyle(Constants.Styles.LABEL_STYLE_CALCAREA);
        mmLabel3.setStyle(Constants.Styles.LABEL_STYLE_CALCAREA);
        gLabel.setStyle(Constants.Styles.LABEL_STYLE_CALCAREA);

        this.add(mmLabel1, 3, 1);
        this.add(mmLabel2, 3, 2);
        this.add(mmLabel3, 3, 3);
        this.add(gLabel, 3, 4);
            
        // Add shipping cost calculation line with label, result, and button
        Label shippingCostsLabel = new Label(Constants.Labels.SHIPPING_COSTS_LABEL);
        shippingCostsLabel.setStyle(Constants.Styles.LABEL_STYLE_CALCAREA);

        this.add(shippingCostsLabel, 1, 5);
        this.add(shippingCostLabel, 2, 5);

        // Style the calculate button with a smooth transition effect
        calcButton.setStyle(Constants.Styles.STYLE_CALCBTN);
        calcButton.setEffect(new DropShadow(5, Color.GRAY));
        this.add(calcButton, 3, 5);

        // Add hover effect for the calculate button
        calcButton.setOnMouseEntered(_ -> calcButton.setStyle(Constants.Styles.STYLE_CALCBTN_HOVER));
        calcButton.setOnMouseExited(_ -> calcButton.setStyle(Constants.Styles.STYLE_CALCBTN));

        // Set the action listener for the calculate button
        calcButton.setOnAction(_ -> calcShippingCosts());

        // Add tooltips to input fields, labels & Button
        lengthTextField.setTooltip(new Tooltip(Constants.Tooltips.TOOLTIP_LENGTH_TF));
        widthTextField.setTooltip(new Tooltip(Constants.Tooltips.TOOLTIP_WIDTH_TF));
        heightTextField.setTooltip(new Tooltip(Constants.Tooltips.TOOLTIP_HEIGHT_TF));
        weightTextField.setTooltip(new Tooltip(Constants.Tooltips.TOOLTIP_WEIGHT_TF));
        shippingCostsLabel.setTooltip(new Tooltip(Constants.Tooltips.TOOLTIP_SHIPPINGCOSTS_LABEL));
        calcButton.setTooltip(new Tooltip(Constants.Tooltips.TOOLTIP_CALCBUTTON));
    }
}
