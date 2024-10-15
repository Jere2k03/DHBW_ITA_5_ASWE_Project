package control;

import java.io.File;

import gui.PackageCalculator;
import javafx.stage.DirectoryChooser;

public class ProjectHandling {

    /**
     * Opens a project using the given root directory path. 
     * This method updates the window title, loads the project structure into the explorer, 
     * and stores the root path for future reference.
     *
     * @param rootPath The path to the root directory of the project
     */
    static public void openProject(String rootPath) {
        // update window title
        PackageCalculator.getInstance().getPrimaryStage().setTitle(PackageCalculator.APPNAME + " – " + rootPath);
        // load tree in explorer
        PackageCalculator.getInstance().explorerArea.loadNewTree(rootPath);
        // remember open project
        PackageCalculator.getInstance().rootPath = rootPath;
    }

    /**
     * Opens a dialog window to allow the user to select a project directory.
     * Once a directory is selected, the project is opened.
     * If no directory is selected, the method does nothing.
     */
    static public void openProject() {
        final DirectoryChooser directoryChooser = new DirectoryChooser();
        File projectDirectory;
        projectDirectory = directoryChooser.showDialog(PackageCalculator.getInstance().getPrimaryStage());
        if (projectDirectory != null) {
            // Use the absolute path of the selected directory
            projectDirectory.getAbsolutePath();
        }
        // openProject(projectDirectory.getAbsolutePath()); // Uncomment to actually open the project
    }

    /**
     * Creates a new file within the project.
     * This method is currently a placeholder and needs to be implemented.
     */
    static public void newFile() {
        // Placeholder for creating a new file
    }
}