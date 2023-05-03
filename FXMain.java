package Project;

import javafx.application.Application;
import javafx.stage.Stage;

public class FXMain extends Application {

    @Override
    public void start(Stage primaryStage) {

        // Create the first scene
        LoginPage scene1 = new LoginPage(primaryStage);

        // Set the first scene as the primary scene
        primaryStage.show();
        primaryStage.setTitle("BookStore App");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
