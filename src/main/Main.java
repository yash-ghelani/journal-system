package main;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.net.URL;

public class Main extends Application {
    public static int vave = 0;
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        primaryStage.setTitle("Publisher");
        primaryStage.setScene(new Scene(root, 710, 526));
        primaryStage.initStyle(StageStyle.DECORATED);
        primaryStage.setResizable(false);
        primaryStage.show();

    }

    public static String currentJournalName;

    public static int IDs[] = new int[3];

    public static int ArticleIDForReview;

    public static int ArticleIDForAuthor;

    public static int AuthorCurrentReviewID;

    public static int ResponseArticleID;


    public static void main(String[] args) {
        launch(args);
    }
}
