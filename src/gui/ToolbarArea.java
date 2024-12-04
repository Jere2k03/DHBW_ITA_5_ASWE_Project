package gui;

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

import data.Importer;
import data.Exceptions.CSVWrongFormatException;
import data.Constants;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


/**
 * The {@code ToolbarArea} class represents the toolbar of the PackageCalculator application.
 * It contains buttons for various actions like opening a project, creating a new file, saving files,
 * and displaying information about the application.
 * <p>
 * This class extends JavaFX's {@link ToolBar} and provides buttons with associated action listeners 
 * to interact with the rest of the application.
 * </p>
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
        alert.setTitle(Constants.EXIT_DIALOG_TITLE);
        alert.setHeaderText(Constants.EXIT_DIALOG_HEADER);
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
        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        VBox vbox = new VBox(20);
        try {
            setInfoTable(vbox);
        } catch (CSVWrongFormatException e) {
            // show error messsage as alert
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle(Constants.ERROR_DIALOG_TITLE);
            alert.setHeaderText(Constants.INFO_ERROR_DIALOG_HEADER);
            alert.setContentText(Constants.INFO_ERROR_DIALOG_CONTENT);
            alert.showAndWait();
        }
        Text infoText = new Text(Constants.INFO_TEXT);
        vbox.getChildren().add(infoText);
        Scene dialogScene = new Scene(vbox, 495, 216);
        dialog.setResizable(false); // make the dialog non-resizable
        dialog.setScene(dialogScene);
        dialog.show();
    }

    /**
     * Sets the information table for the PackageCalculator application.
     * @param vbox the VBox to which the table will be added
     */
    private void setInfoTable(VBox vbox) throws CSVWrongFormatException {
        List<Double> shippingCostsList = new ArrayList<>();
        try	{
            Importer.setPath(Constants.SHIPPING_COSTS_FILE);
			shippingCostsList = Importer.importShippingCosts();
		}
		catch (CSVWrongFormatException e) {
            throw new CSVWrongFormatException(e.getMessage());
		}

        // create table
        TableView<String[]> table = new TableView<>();
        TableColumn<String[], String> columnSize = new TableColumn<>(Constants.PACKET_SIZE_HEADER);
        TableColumn<String[], String> columnWeight = new TableColumn<>(Constants.PACKET_WEIGHT_HEADER);
        TableColumn<String[], String> columnGirth = new TableColumn<>(Constants.GIRTH_HEADER);
        TableColumn<String[], String> columnPrice = new TableColumn<>(Constants.SHIPPING_COSTS_HEADER);

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
            Constants.SMALL_PACKET_SIZE,
            Constants.PACKET_WEIGHT_1000,
            Constants.NO_GIRTH,
            shippingCostsList.get(0).toString() + Constants.EURO
        });
        table.getItems().add(new String[]{
            Constants.MEDIUM_PACKET_SIZE,
            Constants.PACKET_WEIGHT_2000,
            Constants.NO_GIRTH,
            shippingCostsList.get(1).toString() + Constants.EURO
        });
        table.getItems().add(new String[]{
            Constants.BIG_PACKET_SIZE, 
            Constants.PACKET_WEIGHT_5000, 
            Constants.GIRTH_SIZE, 
            shippingCostsList.get(2).toString() + Constants.EURO
        });
        table.getItems().add(new String[]{
            Constants.BIG_PACKET_SIZE, 
            Constants.PACKET_WEIGHT_10000, 
            Constants.GIRTH_SIZE,
            shippingCostsList.get(3).toString() + Constants.EURO
        });
        table.getItems().add(new String[]{
            Constants.BIG_PACKET_SIZE, 
            Constants.PACKET_WEIGHT_31000,
            Constants.NO_GIRTH,
            shippingCostsList.get(4).toString() + Constants.EURO
        });

        vbox.getChildren().add(table);
    }

    /**
     * Opens the project repo after an Opt-In dialog
     */
    private void openProjectRepo() {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle(Constants.ABOUT_TITLE);
        alert.setHeaderText(Constants.ABOUT_HEADER);
        alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.YES) {
            try {
                java.awt.Desktop.getDesktop().browse(new java.net.URI(Constants.PROJECT_REPO_URI));
            }
            catch (IOException | URISyntaxException e) {
                // show error messsage as alert
                Alert errorAlert = new Alert(AlertType.ERROR);
                errorAlert.setTitle(Constants.ERROR_DIALOG_TITLE);
                errorAlert.setHeaderText(Constants.ABOUT_ERROR_HEADER);
                errorAlert.setContentText(Constants.ABOUT_ERROR_CONTENT);
                errorAlert.showAndWait();
            }
        }
    }

    /**
     * Opens the settings window.
     */
    private void openSettingsWindow() {
        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        Pane pane = new Pane();
        Scene dialogScene = new Scene(pane, 433, 216);
        dialog.setMinWidth(433); // set min width
        dialog.setMinHeight(216); // set min height
        dialog.setScene(dialogScene);

        // different Buttons for settings
        // TODO: the idea is to have several buttons for different settings, i.e dark mode, language, etc.
        darkModeToggleButton(pane);

        dialog.show();
    }

    /**
     * This button can toggle between dark and white mode of the system.
     * It's only for demonstration purposes and doesn't work yet.
     * @param pane the pane to which the button will be added
     */
    private void darkModeToggleButton(Pane pane) {
        Button newButton = new Button();

        // Set icon for the button
        ImageView icon = new ImageView(new Image(Constants.DARKMODE_ICON_PATH));
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
        // initialize buttons with clean UI
        Button exitButton = new Button(Constants.EXIT_BUTTON);
        Button settingsButton = new Button(Constants.SETTINGS_BUTTON);
        Button aboutButton = new Button(Constants.ABOUT_BUTTON);
        Button infoButton = new Button(Constants.INFO_BUTTON);

        // apply CSS styles for a cleaner UI
        String buttonStyle = Constants.STYLE_BTN_CALCAREA;
        exitButton.setStyle(buttonStyle);
        settingsButton.setStyle(buttonStyle);
        aboutButton.setStyle(buttonStyle);
        infoButton.setStyle(buttonStyle);

        // set button sizes for uniformity
        exitButton.setPrefWidth(130);
        settingsButton.setPrefWidth(130);
        aboutButton.setPrefWidth(130);
        infoButton.setPrefWidth(130);
        // add action listeners to the buttons
        exitButton.setOnAction(_ -> exitPackageCalculator());
        settingsButton.setOnAction(_ -> openSettingsWindow());
        aboutButton.setOnAction(_ -> openProjectRepo());
        infoButton.setOnAction(_ -> showInfoDialog());

        // add all buttons to the toolbar
        ImageView packageIcon = new ImageView();
        Image image = new Image(Constants.ICON_PATH);
        packageIcon.setImage(image);
        packageIcon.setFitHeight(30);
        packageIcon.setFitWidth(30);
        this.getItems().add(packageIcon);
        this.getItems().add(exitButton);
        this.getItems().add(new javafx.scene.control.Separator());
        this.getItems().add(settingsButton);
        this.getItems().add(aboutButton);
        this.getItems().add(infoButton);

        // add tooltips to the buttons
        exitButton.setTooltip(new Tooltip(Constants.TOOLTIP_EXIT_BTN));
        settingsButton.setTooltip(new Tooltip(Constants.TOOLTIP_SETTINGS_BTN));
        aboutButton.setTooltip(new Tooltip(Constants.TOOLTIP_ABOUT_BTN));
        infoButton.setTooltip(new Tooltip(Constants.TOOLTIP_INFO_BTN));
    }
}
