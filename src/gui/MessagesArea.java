package gui;

import javafx.scene.control.ListView;

/**
 * This class represents the messages area of the application.
 * 
 * @author Jeremias Matt
 * @version 0.1
 */
public class MessagesArea extends ListView<String> {

    /**
     * Constructs a new messages area.
     * Sets a welcome message.
     */
    public MessagesArea() {
        this.getItems().add("Herzlich Willkommen zum PackageCalculator! \nBitte geben Sie die Maße Ihres Pakets ein.");
    }

    /**
     * Sets a message to the messages area.
     * 
     * @param text the message to set
     */
    public void setMessage(String text) {
        clearMessage();
        this.getItems().add(text);
    }

    /**
     * Retrieves the last message from the messages area.
     * 
     * @return the last message from the messages area
     */
    public String getMessage() {
        return this.getItems().get(0);
    }

    /**
     * Clears the messages area.
     */
    public void clearMessage() {
        this.getItems().clear();
    }

}
