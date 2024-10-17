package gui;

import control.Calculator;
import data.Packet;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

/**
 * The CalculatorArea class represents the GUI component responsible for
 * calculating shipping costs based on user input of package dimensions and weight.
 * It extends {@link GridPane} to arrange input fields, labels, and buttons in a grid layout.
 */
public class CalculatorArea extends GridPane {

    // Input fields for package dimensions and weight
    TextField lengthTextField = new TextField();
    TextField widthTextField  = new TextField();
    TextField heightTextField = new TextField();
    TextField weightTextField = new TextField();
    
    // Label to display the calculated shipping cost
    Label shippingCostLabel = new Label("?");
    
    // Button to trigger the calculation
    Button calcButton = new Button("Calculate");
    
    /**
     * Calculates the shipping costs based on the package dimensions and weight entered by the user.
     * The calculation is performed by the {@link Calculator} class using the {@link Packet} object.
     *
     * @return The calculated shipping costs as a {@code double}.
     */
    private double calcShippingCosts() {
        // Initialize the calculator
        Calculator calc = new Calculator();
        int length, width, height, weight;

        try {
            // Get user input values
            length = Integer.parseInt(lengthTextField.getText());
            width = Integer.parseInt(widthTextField.getText());
            height = Integer.parseInt(heightTextField.getText());
            weight = Integer.parseInt(weightTextField.getText());
        }
        catch (IllegalArgumentException e)    {
            // Handle the exception
            System.out.println(e.getMessage());

            //show error message to user
            shippingCostLabel.setText("Error: " + e.getMessage());

            // return -1 to indicate an error
            return -1;
        }
        
        
        // Perform the calculation using the Packet object, only if all values are numbers
        Packet packet = new Packet(length, width, height, weight);
        Double costs = 0.0;
        try {
            costs = calc.calcShippingCosts(packet);
            // Display the result
            shippingCostLabel.setText(costs.toString());
        }
        catch (Exception e) {
            // Handle the exception
            System.out.println(e.getMessage());

            //show error message to user
            shippingCostLabel.setText("Error: " + e.getMessage());

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
        this.setPadding(new Insets(10, 10, 10, 10));
        
        // Add description labels for the input fields
        this.add(new Label("Length: "), 1, 1);
        this.add(new Label("Width:  "), 1, 2);
        this.add(new Label("Height: "), 1, 3);
        this.add(new Label("Weight: "), 1, 4);

        // Add input fields for dimensions and weight
        this.add(lengthTextField, 2, 1);
        this.add(widthTextField,  2, 2);
        this.add(heightTextField, 2, 3);
        this.add(weightTextField, 2, 4);

        // Add labels for the units (millimeters and grams)
        this.add(new Label("mm"), 3, 1);
        this.add(new Label("mm"), 3, 2);
        this.add(new Label("mm"), 3, 3);
        this.add(new Label("g"), 3, 4);
                
        // Add shipping cost calculation line with label, result, and button
        this.add(new Label("Shipping Costs: "), 1, 5);
        this.add(shippingCostLabel, 2, 5);
        this.add(calcButton, 3, 5);
        
        // Set the action listener for the calculate button
        calcButton.setOnAction(ae -> calcShippingCosts());
    }
}
