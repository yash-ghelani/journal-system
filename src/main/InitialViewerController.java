package main;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javafx.event.*;
import main.Main;
import main.tables.ReviewTable;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;

public class InitialViewerController {

    @FXML
    private Text title;
    @FXML
    private Label review1;

    @FXML
    private Label review2;

    @FXML
    private Label review3;

    public void initialize() {
        title.setText("Initial Verdict- ArticleID: " + Main.ArticleIDForAuthor);
    }

    public void handleInitialCancel(ActionEvent action) throws IOException{
        Parent view = FXMLLoader.load(getClass().getResource("AuthorPanel.fxml"));
        Scene viewScene = new Scene(view);
        Stage window = (Stage) ((Node) action.getSource()).getScene().getWindow();
        window.setResizable(true);
        window.setScene(viewScene);
    }

    public void handleInitialViewer1(ActionEvent actionEvent) throws IOException, SQLException {

        ArrayList<Integer> timesReviewed = ReviewTable.SelectReviewsCompleted(Main.ArticleIDForAuthor);
        System.out.println(timesReviewed);

        if (timesReviewed.size() == 0) {
            review1.setText("Not completed yet");
        } else {
            Main.AuthorCurrentReviewID = timesReviewed.get(0);
            quickLink("Respond", actionEvent);
        }

    }

    public void handleInitialViewer2(ActionEvent actionEvent) throws IOException, SQLException {
        ArrayList<Integer> timesReviewed = ReviewTable.SelectReviewsCompleted(Main.ArticleIDForAuthor);

        if (timesReviewed.size() <= 2 ) {
            review2.setText("Not completed yet");
        } else {
            Main.AuthorCurrentReviewID = timesReviewed.get(1);
            quickLink("Respond", actionEvent);
        }
    }

    public void handleInitialViewer3(ActionEvent actionEvent) throws IOException, SQLException {
        ArrayList<Integer> timesReviewed = ReviewTable.SelectReviewsCompleted(Main.ArticleIDForAuthor);

        if (timesReviewed.size() <= 3) {
            review3.setText("Not completed yet");
        } else {
            Main.AuthorCurrentReviewID = timesReviewed.get(2);
            quickLink("Respond", actionEvent);
        }
    }

    public void quickLink(String link, ActionEvent actionEvent) throws IOException {
        Parent view = FXMLLoader.load(getClass().getResource(link + ".fxml"));
        Scene viewScene = new Scene(view);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setResizable(true);
        window.setScene(viewScene);
    }
}
