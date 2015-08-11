package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.jgroups.JChannel;

public class Main extends Application {

    JChannel channel;
    JGroupsMessenger messenger = new JGroupsMessenger();

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("main.fxml"));
        Parent root = fxmlLoader.load();

        MainController mainController = fxmlLoader.getController();
        mainController.setMessenger(messenger);

        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();

        initializeChannel();
        primaryStage.setTitle("Chat " + getParameters().getNamed().get("instance"));
    }


    public static void main(String[] args) {
        launch(args);
    }

    public void initializeChannel() throws Exception {
        channel = new JChannel();
        messenger.setChannel(channel);
    }

    @Override
    public void stop() throws Exception {
        channel.close();
    }
}
