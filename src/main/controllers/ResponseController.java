package main.controllers;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import static main.Main.reviewIDForAuthor;

import main.Main;
import main.tables.ErrorTable;
import main.tables.QuestionTable;
import main.tables.ReviewTable;
import main.tables.SubmissionTable;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;

public class ResponseController {

    @FXML
    private Label reviewsummaryinfo;
    @FXML
    private Text text;
    @FXML
    private Label verdict;
    @FXML
    private Button loadquestions;

    @FXML
    private VBox vboxpanel;

    public void initialize() throws SQLException, IOException {
        reviewsummaryinfo.setText(ReviewTable.SelectSummary(reviewIDForAuthor));
        String errors = "";
        ArrayList<String> list = ErrorTable.SelectError(reviewIDForAuthor);
        for (int i = 0 ; i < list.size() ; i++) {
            errors += list.get(i) + "\n";
        }
        text.setText(errors);
        verdict.setText(ReviewTable.SelectSummary(reviewIDForAuthor));

        ArrayList<String> questions = QuestionTable.SelectQuestionText(reviewIDForAuthor);

        for (int i = 0; i < (questions).size(); i++) {

            URL url = new File("src/resources/RespondBox.fxml").toURI().toURL();

            HBox box = FXMLLoader.load(url);
            ObservableList<Node> child = box.getChildren();
            vboxpanel.getChildren().remove(loadquestions);

            VBox v = (VBox) child.get(0);
            Label question = (Label) v.getChildren().get(0);
            //Label submissioninfoID = (Label) v.getChildren().get(1);

            question.setText(questions.get(i));
            System.out.println(questions.get(i));

            Insets padding = new Insets(10, 0, 0, 0);
            Separator sep = new Separator();
            sep.setPadding(padding);

            vboxpanel.getChildren().add(box);
            vboxpanel.getChildren().add(sep);
        }
    }

    public void handleLogOut(ActionEvent actionEvent) throws IOException {
        URL url = new File("src/resources/Login.fxml").toURI().toURL();
        Parent view = FXMLLoader.load(url);
        Scene viewScene = new Scene(view);

        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        window.setResizable(true);
        window.setScene(viewScene);

    }
}
