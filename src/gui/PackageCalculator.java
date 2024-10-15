package gui;

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
 * a graphical user interface (GUI) with multiple areas, such as the toolbar, explorer,
 * editor, inspector, messages, and status area.
 * <p>
 * This class uses a singleton pattern to ensure that there is only one instance of the application
 * running at a time. It initializes and arranges the main components of the user interface.
 * </p>
 * 
 * <p>
 * The main layout includes a {@link SplitPane} for dividing the explorer, editor, inspector, and messages
 * areas. The GUI is set up when the application starts, and the stage is configured to maximize to the
 * primary screen's bounds.
 * </p>
 */
public class PackageCalculator extends Application {

    /**
     * The application name, used as the window title.
     */
    public final static String APPNAME = "PackageCalculator";
    
    // Singleton instance
    private static PackageCalculator instance;
    
    /**
     * Retrieves the singleton instance of the {@code PackageCalculator}.
     * 
     * @return the singleton instance of the application
     */
    public static PackageCalculator getInstance() {
        return instance;
    }
    
    // GUI areas
    public ToolbarArea toolbarArea = new ToolbarArea();
    public ExplorerArea explorerArea = new ExplorerArea();
    public CalculatorArea editorArea = new CalculatorArea();
    public InspectorArea inspectorArea = new InspectorArea();
    public MessagesArea messagesArea = new MessagesArea();
    public StatusArea statusArea = new StatusArea();

    /**
     * The root path of the currently opened project.
     */
    public String rootPath;

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
     * The entry point for JavaFX applications. This method sets up the user interface, 
     * including all panes and their layouts. It also maximizes the window size to fit 
     * the screen's available space.
     * 
     * @param primaryStage the main stage (window) for this application
     */
    @Override
    public void start(Stage primaryStage) {
        // Set the singleton instance
        PackageCalculator.instance = this;

        // Remember the primary stage for further use
        this.primaryStage = primaryStage;

        // Create a horizontal split pane for editor and inspector areas
        SplitPane lr2SplitPane = new SplitPane();
        lr2SplitPane.getItems().addAll(editorArea, inspectorArea);
        lr2SplitPane.setDividerPositions(0.8f, 0.2f);
        
        // Create a vertical split pane for the editor/inspector and messages areas
        SplitPane tdSplitPane = new SplitPane();
        tdSplitPane.setOrientation(Orientation.VERTICAL);
        tdSplitPane.getItems().addAll(lr2SplitPane, messagesArea);
        tdSplitPane.setDividerPositions(0.9f, 0.1f);

        // Create a split pane for the explorer area and the rest of the UI
        SplitPane lrSplitPane = new SplitPane();
        lrSplitPane.getItems().addAll(explorerArea, tdSplitPane);
        lrSplitPane.setDividerPositions(0.2f, 0.8f);

        // Set up the main layout (border pane) with the toolbar at the top and status area at the bottom
        BorderPane mainPane = new BorderPane();
        mainPane.setTop(toolbarArea);
        mainPane.setCenter(lrSplitPane);
        mainPane.setBottom(statusArea);

        // Show the main pane on the primary stage
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        Scene scene = new Scene(mainPane, screenBounds.getWidth(), screenBounds.getHeight(), true);
        primaryStage.setTitle(APPNAME);
        primaryStage.setScene(scene);
        primaryStage.show();

        // Optionally load a default workspace
        // ProjectHandling.openProject("/Users/...");
    }

    /**
     * The main method, which serves as the entry point for the Java application.
     * It launches the JavaFX application by calling the {@link Application#launch(String...)} method.
     * 
     * @param args the command line arguments passed to the application
     * @author Jeremias
     */
    public static void main(String[] args) {
        launch(args);
    }
}
