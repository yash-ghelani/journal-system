package main;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import main.tables.AuthorTable;


import java.io.File;
import java.net.URL;

public class Main<IDs> extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        URL url = new File("src/resources/Login.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        primaryStage.setTitle("Publisher");
        primaryStage.setScene(new Scene(root, 710, 526));
        primaryStage.initStyle(StageStyle.DECORATED);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static int IDs[] = new int[3];

    public static void main(String[] args) {
        launch(args);
    }
}
