package studentenrollmentsystem;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class StudentEnrollmentSystem extends Application {

@Override
public void start(Stage stage) throws Exception {
    Parent root = FXMLLoader.load(getClass().getResource("/views/MainUI.fxml"));
    Scene scene = new Scene(root);

 
    scene.getStylesheets().add(getClass().getResource("/styles/style.css").toExternalForm());

    stage.setScene(scene);
    stage.setTitle("Student Enrollment System");
    stage.show();
}
    public static void main(String[] args) {
        launch(args);
    }
}