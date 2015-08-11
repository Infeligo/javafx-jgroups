package sample;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class MainController {


    private Messenger messenger;

    @FXML
    private Button sendButton;

    @FXML
    private TextField inputField;

    @FXML
    private TextArea chatArea;

    @FXML
    private void initialize() {
        sendButton.setOnAction((event) -> {
            String text = inputField.getText();
            messenger.send(text);
        });

    }

    public void setMessenger(Messenger messenger) {
        this.messenger = messenger;

        this.messenger.setListener(text -> {
            Platform.runLater(() -> {
                chatArea.appendText(text + "\n");
            });
        });
    }
}
