package gui;

import java.io.File;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.util.Callback;

/**
 * The ExplorerArea class represents the file explorer area of the application.
 * It extends {@link TabPane} and provides a tab for viewing and interacting
 * with the file system, displaying the directory structure and text files.
 */
public class ExplorerArea extends TabPane {

    private Tab explorerTab;
    private TreeView<FileItem> treeView;

    /**
     * Wrapper class for representing files in the TreeView. It stores the file
     * and provides a custom string representation.
     */
    class FileItem {
        public File file;

        /**
         * Default constructor (unused).
         */
        @SuppressWarnings("unused")
        private FileItem() {
        }

        /**
         * Constructs a new FileItem object from the given File.
         *
         * @param file The file to be wrapped in the FileItem.
         */
        public FileItem(File file) {
            this.file = file;
        }

        /**
         * Constructs a new FileItem object from the given file path.
         *
         * @param str The path of the file as a String.
         */
        public FileItem(String str) {
            this.file = new File(str);
        }

        /**
         * Returns the name of the file, hiding the root folder name.
         * The root folder name is replaced by "..." to avoid redundancy in the GUI.
         *
         * @return The file name or the formatted root name if it is the root directory.
         */
        @Override
        public String toString() {
            if (file.getAbsolutePath().equals(PackageCalculator.getInstance().rootPath)) {
                return "..." + File.separator + file.getName();
            }
            return file.getName();
        }

        /**
         * Returns a context menu for the file, currently for test purposes.
         *
         * @return A {@link ContextMenu} containing a test menu item.
         */
        //@Override
        public ContextMenu getMenu() {
            return new ContextMenu(new MenuItem("test"));
        }
    }

    /**
     * Builds a TreeView to display the file system starting from the given root path.
     *
     * @param rootPath The root directory to display in the TreeView.
     * @return A TreeView populated with the file structure from the root path.
     */
    private TreeView<FileItem> buildFileSystemBrowser(String rootPath) {
        TreeItem<FileItem> root = createNode(new FileItem(new File(rootPath)));
        return new TreeView<FileItem>(root);
    }

    /**
     * Creates a TreeItem node representing the given FileItem, recursively adding children.
     * It overrides the {@link TreeItem#getChildren()} and {@link TreeItem#isLeaf()} methods
     * to optimize performance by caching whether a file is a leaf or has children.
     *
     * @param fileItem The FileItem representing the file or directory.
     * @return A TreeItem representing the file or directory.
     */
    private TreeItem<FileItem> createNode(final FileItem fileItem) {
        return new TreeItem<FileItem>(fileItem) {
            private boolean isLeaf;
            private boolean isFirstTimeChildren = true;
            private boolean isFirstTimeLeaf = true;

            @Override
            public ObservableList<TreeItem<FileItem>> getChildren() {
                if (isFirstTimeChildren) {
                    isFirstTimeChildren = false;
                    super.getChildren().setAll(buildChildren(this));
                }
                return super.getChildren();
            }

            @Override
            public boolean isLeaf() {
                if (isFirstTimeLeaf) {
                    isFirstTimeLeaf = false;
                    FileItem f = getValue();
                    isLeaf = f.file.isFile();
                }
                return isLeaf;
            }

            private ObservableList<TreeItem<FileItem>> buildChildren(TreeItem<FileItem> treeItem) {
                FileItem f = treeItem.getValue();
                if (f != null && f.file.isDirectory()) {
                    File[] files = f.file.listFiles();
                    if (files != null) {
                        ObservableList<TreeItem<FileItem>> children = FXCollections.observableArrayList();
                        for (File childFile : files) {
                            if (childFile.isDirectory() || childFile.getName().toLowerCase().endsWith(".txt")) {
                                children.add(createNode(new FileItem(childFile)));
                            }
                        }
                        return children;
                    }
                }
                return FXCollections.emptyObservableList();
            }
        };
    }

    /**
     * Custom TreeCell implementation to display file items in the TreeView.
     * It also provides a context menu based on whether the item is a directory or a file.
     */
    private final class TreeCellImpl extends TreeCell<FileItem> {

        @Override
        public void updateItem(FileItem item, boolean empty) {
            super.updateItem(item, empty);

            if (empty) {
                setText(null);
                setGraphic(null);
                return;
            }
            setText(getItem() == null ? "" : getItem().toString());
            setGraphic(getTreeItem().getGraphic());
            ContextMenu contextMenu = new ContextMenu();

            if (getItem().file.isDirectory()) {
                // Folder-specific context menu
                MenuItem newFileMenu = new MenuItem("New File");
                MenuItem newSubfolderMenu = new MenuItem("New Subfolder");
                contextMenu.getItems().addAll(newFileMenu, newSubfolderMenu);
            } else {
                // File-specific context menu
                MenuItem openMenu = new MenuItem("Open");
                MenuItem closeMenu = new MenuItem("Close");
                contextMenu.getItems().addAll(openMenu, closeMenu);
            }

            // Common context menu items
            MenuItem copyMenu = new MenuItem("Copy");
            MenuItem pasteMenu = new MenuItem("Paste");
            MenuItem deleteMenu = new MenuItem("Delete");
            contextMenu.getItems().addAll(new SeparatorMenuItem(), copyMenu, pasteMenu, deleteMenu);

            setContextMenu(contextMenu);
        }
    }

    /**
     * Loads a new tree structure based on the given project path and populates
     * the TreeView with files and folders.
     *
     * @param projectPath The root path of the project to load into the file explorer.
     */
    @SuppressWarnings("unchecked")
    public void loadNewTree(String projectPath) {
        TreeItem<FileItem> root = new TreeItem<FileItem>();
        root.setExpanded(true);
        root.getChildren().addAll(
                new TreeItem<FileItem>(),
                new TreeItem<FileItem>(),
                new TreeItem<FileItem>()
        );
        treeView = new TreeView<FileItem>(root);

        this.treeView = buildFileSystemBrowser(projectPath);

        treeView.setCellFactory(new Callback<TreeView<FileItem>, TreeCell<FileItem>>() {
            @Override
            public TreeCell<FileItem> call(TreeView<FileItem> p) {
                return new TreeCellImpl();
            }
        });

        treeView.getRoot().setExpanded(true);
        explorerTab.setContent(treeView);
    }

    /**
     * Constructs a new ExplorerArea object. Initializes the explorer tab and adds it to the TabPane.
     */
    public ExplorerArea() {
        // Add the fixed explorer tab
        explorerTab = new Tab("Explorer");
        explorerTab.setClosable(false);
        this.getTabs().add(explorerTab);
    }
}
