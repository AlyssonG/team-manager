import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

public class TeamManager extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello");
        primaryStage.setScene(new Scene(root,100,100));
        primaryStage.show();
    }
}
