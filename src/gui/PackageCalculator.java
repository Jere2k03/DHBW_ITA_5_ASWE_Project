package gui;

import data.Constants;

import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * The {@code PackageCalculator} class represents the main application for calculating
 * shipping costs based on package dimensions. It is a JavaFX application that provides
 * a graphical user interface (GUI) with multiple areas, such as the toolbar, messages, and calculator area.
 */
public class PackageCalculator extends Application {

    /** The application name */
    public static final String APPNAME = Constants.Labels.APP_NAME;
    
    /** init Toolbar GUI Area */
    public static final ToolbarArea toolbarArea = new ToolbarArea();
    /** init Calculator GUI Area */
    public static final CalculatorArea calculatorArea = new CalculatorArea();
    /** init Messages GUI Area */
    public static final MessagesArea messagesArea = new MessagesArea();

    // Primary stage reference
    private Stage primaryStage;
    
    /**
     * Returns the primary stage of the application.
     * 
     * @return the primary stage of the application
     */
    public Stage getPrimaryStage() {
        return this.primaryStage;
    }

    /**
     * This method sets up the user interface, 
     * including all panes and their layouts. It also maximizes the window size to fit 
     * the screen's available space.
     * 
     * @param primaryStage the main stage (window) for this application
     */
    @Override
    public void start(Stage primaryStage) {
        // Remember the primary stage for further use
        this.primaryStage = primaryStage;

        // Create a horizontal split pane for calculator and inspector areas
        SplitPane lr2SplitPane = new SplitPane();
        lr2SplitPane.getItems().addAll(calculatorArea);
        lr2SplitPane.setDividerPositions(0.8f, 0.2f);
        
        // Create a vertical split pane for the editor/inspector and messages areas
        SplitPane tdSplitPane = new SplitPane();
        tdSplitPane.setOrientation(Orientation.VERTICAL);
        tdSplitPane.getItems().addAll(lr2SplitPane, messagesArea);
        tdSplitPane.setDividerPositions(0.9f, 0.1f);

        // Set up the main layout (border pane) with the toolbar at the top and status area at the bottom
        BorderPane mainPane = new BorderPane();
        mainPane.setTop(toolbarArea);
        mainPane.setCenter(tdSplitPane);

        // Show the main pane on the primary stage
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        Scene scene = new Scene(mainPane, screenBounds.getWidth(), screenBounds.getHeight(), true);
        primaryStage.setTitle(APPNAME);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * The main method, which serves as the entry point for the Java application.
     * It launches the application.
     * 
     * @param args the command line arguments passed to the application
     * @author Jeremias Matt
     */
    public static void main(String[] args) {
        launch(args);
    }
}
