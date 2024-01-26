package ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TetrisApp extends Application{

    


    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Tetris");

        FXMLLoader loader = new FXMLLoader(getClass().getResource("tetris.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);

        //gets the controller for the main screen and initializes the game
        TetrisController controller = loader.getController();
        controller.init(scene);

        primaryStage.show();
        
        
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
