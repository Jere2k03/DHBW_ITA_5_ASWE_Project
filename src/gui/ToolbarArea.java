package gui;

import data.Importer;
import data.Exceptions.CSVWrongFormatException;
import data.Constants;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import java.util.Optional;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Tooltip;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;


/**
 * The {@code ToolbarArea} class represents the toolbar of the PackageCalculator application.
 * It contains buttons for various actions like opening the settings, showing information, opening the project repo, and exiting the
 * application.
 * 
 * @author Jeremias Matt
 * @version 0.1
 */
public class ToolbarArea extends ToolBar {
    
    /**
     * Exits the PackageCalculator application after displaying a confirmation (YES, NO) dialog.
     */
    private void exitPackageCalculator() {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle(Constants.Dialogs.EXIT_DIALOG_TITLE);
        alert.setHeaderText(Constants.Dialogs.EXIT_DIALOG_HEADER);
        alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.YES) {
            System.exit(0);
        }
    }

    /**
     * Displays an information dialog with details about the PackageCalculator application.
     * This dialog is modal and contains information such as the version and authors.
     */
    private void showInfoDialog() {
        Stage infoStage = new Stage();
        VBox vbox = new VBox(20);

        infoStage.setTitle(Constants.Dialogs.INFO_TITLE);
        infoStage.initModality(Modality.APPLICATION_MODAL);
        
        try {
            setInfoTable(vbox);
        } catch (CSVWrongFormatException e) {
            // show error messsage as alert
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle(Constants.Dialogs.ERROR_DIALOG_TITLE);
            alert.setHeaderText(Constants.Dialogs.INFO_ERROR_DIALOG_HEADER);
            alert.setContentText(Constants.Dialogs.INFO_ERROR_DIALOG_CONTENT);
            alert.showAndWait();
        }

        Text infoText = new Text(Constants.Metadata.INFO_TEXT);
        vbox.getChildren().add(infoText);
        Scene infoScene = new Scene(vbox, 495, 216);
        infoStage.setResizable(false); // make the dialog non-resizable
        infoStage.setScene(infoScene);
        infoStage.show();
    }

    /**
     * Sets the information table for the PackageCalculator application.
     * 
     * @param vbox the VBox to which the table will be added
     * @throws CSVWrongFormatException if the csv file cannot be found or read or has the wrong format
     */
    private void setInfoTable(VBox vbox) throws CSVWrongFormatException {
        List<Double> shippingCostsList = new ArrayList<>();

        // uses the Importer class to import the shipping costs
        try	{
            Importer.setPath(Constants.FilePaths.SHIPPING_COSTS_FILE);
			shippingCostsList = Importer.importShippingCosts();
		}
		catch (CSVWrongFormatException e) {
            throw new CSVWrongFormatException(e.getMessage());
		}

        // create table
        TableView<String[]> table = new TableView<>();
        TableColumn<String[], String> columnSize = new TableColumn<>(Constants.TableHeaders.PACKET_SIZE_HEADER);
        TableColumn<String[], String> columnWeight = new TableColumn<>(Constants.TableHeaders.PACKET_WEIGHT_HEADER);
        TableColumn<String[], String> columnGirth = new TableColumn<>(Constants.TableHeaders.GIRTH_HEADER);
        TableColumn<String[], String> columnPrice = new TableColumn<>(Constants.TableHeaders.SHIPPING_COSTS_HEADER);

        // set cell value factories
        columnSize.setCellValueFactory(data -> new SimpleStringProperty(data.getValue()[0]));
        columnWeight.setCellValueFactory(data -> new SimpleStringProperty(data.getValue()[1]));
        columnGirth.setCellValueFactory(data -> new SimpleStringProperty(data.getValue()[2]));
        columnPrice.setCellValueFactory(data -> new SimpleStringProperty(data.getValue()[3]));

        // add columns to table
        table.getColumns().add(columnSize);
        table.getColumns().add(columnWeight);
        table.getColumns().add(columnGirth);
        table.getColumns().add(columnPrice);

        // add data to table
        table.getItems().add(new String[]{
            Constants.Measurements.SMALL_PACKET_SIZE,
            Constants.Measurements.PACKET_WEIGHT_1000,
            Constants.Measurements.NO_GIRTH,
            shippingCostsList.get(0).toString() + Constants.Symbols.EURO
        });
        table.getItems().add(new String[]{
            Constants.Measurements.MEDIUM_PACKET_SIZE,
            Constants.Measurements.PACKET_WEIGHT_2000,
            Constants.Measurements.NO_GIRTH,
            shippingCostsList.get(1).toString() + Constants.Symbols.EURO
        });
        table.getItems().add(new String[]{
            Constants.Measurements.BIG_PACKET_SIZE, 
            Constants.Measurements.PACKET_WEIGHT_5000, 
            Constants.Measurements.GIRTH_SIZE, 
            shippingCostsList.get(2).toString() + Constants.Symbols.EURO
        });
        table.getItems().add(new String[]{
            Constants.Measurements.BIG_PACKET_SIZE, 
            Constants.Measurements.PACKET_WEIGHT_10000, 
            Constants.Measurements.GIRTH_SIZE,
            shippingCostsList.get(3).toString() + Constants.Symbols.EURO
        });
        table.getItems().add(new String[]{
            Constants.Measurements.BIG_PACKET_SIZE, 
            Constants.Measurements.PACKET_WEIGHT_31000,
            Constants.Measurements.NO_GIRTH,
            shippingCostsList.get(4).toString() + Constants.Symbols.EURO
        });

        vbox.getChildren().add(table);
    }

    /**
     * Opens the project repo after an Opt-In dialog
     */
    private void openProjectRepo() {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle(Constants.Dialogs.ABOUT_TITLE);
        alert.setHeaderText(Constants.Dialogs.ABOUT_HEADER);
        alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);

        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.YES) {
            try {
                java.awt.Desktop.getDesktop().browse(new java.net.URI(Constants.Metadata.PROJECT_REPO_URI));
            }
            catch (IOException | URISyntaxException e) {
                // show error messsage as alert
                Alert errorAlert = new Alert(AlertType.ERROR);
                errorAlert.setTitle(Constants.Dialogs.ERROR_DIALOG_TITLE);
                errorAlert.setHeaderText(Constants.Dialogs.ABOUT_ERROR_HEADER);
                errorAlert.setContentText(Constants.Dialogs.ABOUT_ERROR_CONTENT);
                errorAlert.showAndWait();
            }
        }
    }

    /**
     * Opens the settings window.
     */
    @SuppressWarnings("java:S1135")
    private void openSettingsWindow() {
        final int minWidth = 433;
        final int minHeight = 216;
        Stage settingsStage = new Stage();
        settingsStage.setTitle(Constants.Dialogs.SETTINGS_TITLE);
        settingsStage.initModality(Modality.APPLICATION_MODAL);
        Pane pane = new Pane();
        Scene settingsScene = new Scene(pane, minWidth, minHeight);
        settingsStage.setMinWidth(minWidth); // set min width
        settingsStage.setMinHeight(minHeight); // set min height
        settingsStage.setScene(settingsScene);

        // different Buttons for settings
        // TODO: the idea is to have several buttons for different settings, i.e dark mode, language, etc. 
        // Right now, only the dark mode button is implemented as a mock object for demonstration purposes.
        darkModeToggleButton(pane);

        settingsStage.show();
    }

    /**
     * This button can toggle between dark and white mode of the system.
     * It's only for demonstration purposes and doesn't work yet.
     * 
     * @param pane the pane to which the button will be added
     */
    private void darkModeToggleButton(Pane pane) {
        Button newButton = new Button();

        // Set icon for the button
        ImageView icon = new ImageView(new Image(Constants.FilePaths.DARKMODE_ICON_PATH));
        icon.setFitHeight(30); // set height of the icon
        icon.setFitWidth(30); // set width of the icon
        newButton.setGraphic(icon);

        // Set the position of the button using coordinates
        newButton.setLayoutX(15); // Set X coordinate
        newButton.setLayoutY(15); // Set Y coordinate

        // Add button to the VBox
        pane.getChildren().add(newButton);
    }

    /**
     * Constructs a new {@code ToolbarArea} and initializes its buttons.
     * Each button is configured with an associated action listener to perform 
     * specific tasks such as opening a project, creating a new file, or displaying the info dialog.
     */
    public ToolbarArea() {
        // initialize all buttons for ToolbarArea
        Button exitButton = new Button(Constants.Buttons.EXIT_BUTTON);
        Button settingsButton = new Button(Constants.Buttons.SETTINGS_BUTTON);
        Button aboutButton = new Button(Constants.Buttons.ABOUT_BUTTON);
        Button infoButton = new Button(Constants.Buttons.INFO_BUTTON);

        // Set button size and styling directly
        double buttonWidth = 120;
        double buttonHeight = 40;

        // button style for all buttons
        exitButton.setStyle(Constants.Styles.STYLE_BTN_TBAREA);
        settingsButton.setStyle(Constants.Styles.STYLE_BTN_TBAREA);
        aboutButton.setStyle(Constants.Styles.STYLE_BTN_TBAREA);
        infoButton.setStyle(Constants.Styles.STYLE_BTN_TBAREA);

        // Set button sizes
        exitButton.setPrefWidth(buttonWidth);
        exitButton.setPrefHeight(buttonHeight);
        settingsButton.setPrefWidth(buttonWidth);
        settingsButton.setPrefHeight(buttonHeight);
        aboutButton.setPrefWidth(buttonWidth);
        aboutButton.setPrefHeight(buttonHeight);
        infoButton.setPrefWidth(buttonWidth);
        infoButton.setPrefHeight(buttonHeight);

        // button hover effect
        applyHoverEffect(exitButton);
        applyHoverEffect(settingsButton);
        applyHoverEffect(aboutButton);
        applyHoverEffect(infoButton);

        // action listeners for the buttons
        exitButton.setOnAction(_ -> exitPackageCalculator());
        settingsButton.setOnAction(_ -> openSettingsWindow());
        aboutButton.setOnAction(_ -> openProjectRepo());
        infoButton.setOnAction(_ -> showInfoDialog());

        // add buttons in the toolbar
        ImageView packageIcon = new ImageView();
        Image image = new Image(Constants.FilePaths.ICON_PATH);
        packageIcon.setImage(image);
        packageIcon.setFitHeight(30);
        packageIcon.setFitWidth(30);
        this.getItems().add(packageIcon);
        this.getItems().add(exitButton);
        this.getItems().add(new javafx.scene.control.Separator());
        this.getItems().add(settingsButton);
        this.getItems().add(aboutButton);
        this.getItems().add(infoButton);

        // Add tooltips to the buttons
        exitButton.setTooltip(new Tooltip(Constants.Tooltips.TOOLTIP_EXIT_BTN));
        settingsButton.setTooltip(new Tooltip(Constants.Tooltips.TOOLTIP_SETTINGS_BTN));
        aboutButton.setTooltip(new Tooltip(Constants.Tooltips.TOOLTIP_ABOUT_BTN));
        infoButton.setTooltip(new Tooltip(Constants.Tooltips.TOOLTIP_INFO_BTN));
    }

    /**
     * This method applies a hover effect to the button, changing the background color.
     */
    private void applyHoverEffect(Button button) {
        button.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent _) -> 
            button.setStyle(button.getStyle() + Constants.Styles.STYLE_BTN_HOVER_TBAREA)
        );

        button.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent _) -> 
            button.setStyle(button.getStyle().replace(Constants.Styles.STYLE_BTN_HOVER_TBAREA, Constants.Styles.STYLE_BTN_TBAREA))
        );
    }
}
