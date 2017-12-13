#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package};
/**
 * Created by mbarralo on 19-08-2016.
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import ${package}.controller.MainController;

import java.io.IOException;

public class MainApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApp.class.getResource("/views/main.fxml"));
        Parent root = loader.load();

        MainController controller = loader.getController();
        stage.setOnCloseRequest(event -> controller.quitApplication(null));

        Scene scene = new Scene(root);
        //tage.initStyle(StageStyle.UNDECORATED);
        stage.setTitle("Members App");
        stage.setScene(scene);
        stage.show();
    }
}
