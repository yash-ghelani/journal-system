package main.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import main.Main;
import main.tables.ErrorTable;
import main.tables.QuestionTable;
import main.tables.ResponseTable;
import main.tables.ReviewTable;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;

public class CoAuthorFinalVerdictController {
    @FXML
    private Text questions;
    @FXML
    private Label summary;
    @FXML
    private Text errors;
    @FXML
    private Label verdict;
    @FXML
    private Label articleid;
    @FXML
    private Label reviewid;
    @FXML
    private Text title;

    public void initialize() throws SQLException {
        title.setText(Main.CurrentTitleBeingLookedAt);
        articleid.setText("ArticleID: " + Main.ArticleIDForAuthor);
        reviewid.setText("ReviewID: " + Main.AuthorCurrentReviewID);

        summary.setText(ReviewTable.SelectSummary(Main.AuthorCurrentReviewID));
        String errorsstring = "";
        ArrayList<String> list = ErrorTable.SelectError(Main.AuthorCurrentReviewID);
        for (int i = 0 ; i < list.size() ; i++) {
            errorsstring += list.get(i) + "\n";
        }
        errors.setText(errorsstring);

        String questionstring = "";
        ArrayList<String> list1 = QuestionTable.SelectQuestionText(Main.AuthorCurrentReviewID);

        for (int i = 0 ; i < list1.size() ; i++) {
            int questionid = QuestionTable.SelectQuestionID(Main.AuthorCurrentReviewID, list1.get(i));
            String response = ResponseTable.SelectResponseText(questionid);
            questionstring += list1.get(i) + " : " + response + "\n";
        }
        questions.setText(questionstring);

        verdict.setText("Verdict: "+ReviewTable.SelectInitialVerdict(Main.AuthorCurrentReviewID));

    }

    public void handleBack(ActionEvent actionEvent) throws IOException {
        URL url = new File("src/resources/FinalViewer.fxml").toURI().toURL();
        Parent view = FXMLLoader.load(url);
        Scene viewScene = new Scene(view);

        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        window.setResizable(true);
        window.setScene(viewScene);
    }
}
