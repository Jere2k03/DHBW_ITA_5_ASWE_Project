package gui;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import java.util.Optional;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.beans.property.SimpleStringProperty;


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
        alert.setTitle("Exit Package Calculator");
        alert.setHeaderText("Sicher, dass Sie die Anwendung beenden möchten?");
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
        Text infoText = new Text("Package Calculator v0.3 \n\t© 2020 I. Bogicevic \n\t© 2024 J. Matt");
        vbox.getChildren().add(infoText);
        setInfoTable(vbox);
        Scene dialogScene = new Scene(vbox, 433, 216); //TODO Größe anpassen (nicht kleiner möglich)
        dialog.setScene(dialogScene);
        dialog.show();
    }

    /**
     * Sets the information table for the PackageCalculator application.
     * @param vbox the VBox to which the table will be added
     */
    private void setInfoTable(VBox vbox) {
        TableView<String[]> table = new TableView<>();
        TableColumn<String[], String> columnSize = new TableColumn<>("Packetgröße [mm]");
        TableColumn<String[], String> columnWeight = new TableColumn<>("Packetgewicht [g]");
        TableColumn<String[], String> columnGirth = new TableColumn<>("Gurtmaß");
        TableColumn<String[], String> columnPrice = new TableColumn<>("Versandkosten");

        columnSize.setCellValueFactory(data -> new SimpleStringProperty(data.getValue()[0]));
        columnWeight.setCellValueFactory(data -> new SimpleStringProperty(data.getValue()[1]));
        columnGirth.setCellValueFactory(data -> new SimpleStringProperty(data.getValue()[2]));
        columnPrice.setCellValueFactory(data -> new SimpleStringProperty(data.getValue()[3]));

        table.getColumns().add(columnSize);
        table.getColumns().add(columnWeight);
        table.getColumns().add(columnGirth);
        table.getColumns().add(columnPrice);

        table.getItems().add(new String[]{"bis 300x300x150", "bis 1000g", "-", "3,89€"});
        table.getItems().add(new String[]{"bis 600x300x150", "bis 2000g", "-", "4,39€"});
        table.getItems().add(new String[]{"bis 1200x600x600", "bis 5000g", "bis 300cm", "5,89€"});
        table.getItems().add(new String[]{"bis 1200x600x600", "bis 10000g", "bis 300cm", "7,99€"});
        table.getItems().add(new String[]{"bis 1200x600x600", "bis 31000g", "-", "14,99€"});

        vbox.getChildren().add(table);
    }

    /**
     * Constructs a new {@code ToolbarArea} and initializes its buttons.
     * Each button is configured with an associated action listener to perform 
     * specific tasks such as opening a project, creating a new file, or displaying the info dialog.
     */
    public ToolbarArea() {
        // initialize buttons
        Button exitButton = new Button("Exit");
        Button settingsButton = new Button("Settings");
        Button aboutButton = new Button("About");
        Button infoButton = new Button("Info");

        // add action listeners to the buttons
        infoButton.setOnAction(_ -> showInfoDialog());
        exitButton.setOnAction(_ -> exitPackageCalculator());

        // add all buttons to the toolbar
        this.getItems().add(exitButton);
        this.getItems().add(new javafx.scene.control.Separator());
        this.getItems().add(settingsButton);
        this.getItems().add(aboutButton);
        this.getItems().add(infoButton);
    }
}
