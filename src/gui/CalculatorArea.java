package gui;

import java.io.IOException;

import control.Calculator;
import control.Exceptions.PacketOutOfBoundsException;
import data.Packet;
import data.Exceptions.CSVWrongFormatException;
import data.Constants;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.control.Tooltip;

/**
 * The CalculatorArea class represents the GUI component responsible for
 * calculating shipping costs based on user input of package dimensions and weight.
 * It extends {@link GridPane} to arrange input fields, labels, and buttons in a grid layout.
 * 
 * @version 0.1
 */
public class CalculatorArea extends GridPane {

    // Input fields for package dimensions and weight
    private TextField lengthTextField = new TextField();
    private TextField widthTextField  = new TextField();
    private TextField heightTextField = new TextField();
    private TextField weightTextField = new TextField();
    
    // Label to display the calculated shipping cost
    private Label shippingCostLabel = new Label(Constants.QUESTIONMARK);
    
    // Button to trigger the calculation
    private Button calcButton = new Button(Constants.CALCULATE_BUTTON);

    /**
     * Checks the user input for package dimensions and weight.
     * @return An array of integers containing the package dimensions and weight.
     * @throws IOException if the input is not numeric or empty
     */
    private int[] checkInput() throws PacketOutOfBoundsException {
        int[] packageValues;
        //check user input
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
            // reset Label
            shippingCostLabel.setText(Constants.QUESTIONMARK);

            // determine which TextField caused the error
            String errorField = "";
            if (lengthTextField.getText().isEmpty() || !lengthTextField.getText().matches("\\d+")) {
            errorField = Constants.LENGTH_LABEL;
            } else if (widthTextField.getText().isEmpty() || !widthTextField.getText().matches("\\d+")) {
            errorField = Constants.WIDTH_LABEL;
            } else if (heightTextField.getText().isEmpty() || !heightTextField.getText().matches("\\d+")) {
            errorField = Constants.HEIGHT_LABEL;
            } else if (weightTextField.getText().isEmpty() || !weightTextField.getText().matches("\\d+")) {
            errorField = Constants.WEIGHT_LABEL;
            }

            throw new PacketOutOfBoundsException(Constants.ERROR_PREFIX + Constants.OUT_OF_BOUNDS_ERROR_1 + errorField + Constants.OUT_OF_BOUNDS_ERROR_2);
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
        // initialize Package dimensions and weight
        int length;
        int width;
        int height;
        int weight; 
        Double costs; // initialize costs
        MessagesArea messagesArea = PackageCalculator.messagesArea; // initialize messagesArea
        
        // checks the user input and calculates the shipping costs
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
            shippingCostLabel.setText(costs.toString() + Constants.EURO);

            // show success message to user with the price in the messages area
            messagesArea.setMessage(Constants.CALCULATION_SUCCESSFUL_MSG + costs + Constants.EURO);
        }
        catch (PacketOutOfBoundsException | CSVWrongFormatException e) {             
            // reset Label
            shippingCostLabel.setText(Constants.QUESTIONMARK); 

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
     * Also sets up the action listener for the calculate button.
     */
    public CalculatorArea() {
                    
        // Set standard padding between elements
        this.setPadding(new Insets(20, 20, 20, 20));
        this.setHgap(15);
        this.setVgap(15);

        // Center the grid in the window
        this.setAlignment(javafx.geometry.Pos.CENTER);

        // Add a header label "Calculator" in larger font and bold
        Label headerLabel = new Label(Constants.CALCULATOR_LABEL);
        headerLabel.setFont(Font.font(Constants.LABEL_FONT, FontWeight.BOLD, 24));
        this.add(headerLabel, 1, 0, 3, 1);

        // Add description labels for the input fields
        Label lengthLabel = new Label(Constants.LENGTH_LABEL);
        Label widthLabel = new Label(Constants.WIDTH_LABEL);
        Label heightLabel = new Label(Constants.HEIGHT_LABEL);
        Label weightLabel = new Label(Constants.WEIGHT_LABEL);
        
        // Apply style to the labels
        String labelStyle = Constants.LABEL_STYLE_CALCAREA;
        lengthLabel.setStyle(labelStyle);
        widthLabel.setStyle(labelStyle);
        heightLabel.setStyle(labelStyle);
        weightLabel.setStyle(labelStyle);

        this.add(lengthLabel, 1, 1);
        this.add(widthLabel, 1, 2);
        this.add(heightLabel, 1, 3);
        this.add(weightLabel, 1, 4);

        // Add input fields for dimensions and weight
        this.add(lengthTextField, 2, 1);
        this.add(widthTextField,  2, 2);
        this.add(heightTextField, 2, 3);
        this.add(weightTextField, 2, 4);

        // Add labels for the units (millimeters and grams)
        Label mmLabel1 = new Label(Constants.MM);
        Label mmLabel2 = new Label(Constants.MM);
        Label mmLabel3 = new Label(Constants.MM);
        Label gLabel = new Label(Constants.G);

        // Apply style to the unit labels
        mmLabel1.setStyle(labelStyle);
        mmLabel2.setStyle(labelStyle);
        mmLabel3.setStyle(labelStyle);
        gLabel.setStyle(labelStyle);

        this.add(mmLabel1, 3, 1);
        this.add(mmLabel2, 3, 2);
        this.add(mmLabel3, 3, 3);
        this.add(gLabel, 3, 4);
            
        // Add shipping cost calculation line with label, result, and button
        Label shippingCostsLabel = new Label(Constants.SHIPPING_COSTS_LABEL);
        shippingCostsLabel.setStyle(labelStyle);
        shippingCostLabel.setStyle(labelStyle);

        this.add(shippingCostsLabel, 1, 5);
        this.add(shippingCostLabel, 2, 5);
        // Style the calculate button
        calcButton.setStyle(Constants.CALCBUTTON_STYLE);
        this.add(calcButton, 3, 5);
        
        // Set the action listener for the calculate button
        calcButton.setOnAction(_ -> calcShippingCosts());

        // Add tooltips to input fields, labels & Button
        lengthTextField.setTooltip(new Tooltip(Constants.TOOLTIP_LENGTH_TF));
        widthTextField.setTooltip(new Tooltip(Constants.TOOLTIP_WIDTH_TF));
        heightTextField.setTooltip(new Tooltip(Constants.TOOLTIP_HEIGHT_TF));
        weightTextField.setTooltip(new Tooltip(Constants.TOOLTIP_WEIGHT_TF));
        shippingCostsLabel.setTooltip(new Tooltip(Constants.TOOLTIP_SHIPPINGCOSTS_LABEL));
        calcButton.setTooltip(new Tooltip(Constants.TOOLTIP_CALCBUTTON));
    }
}
