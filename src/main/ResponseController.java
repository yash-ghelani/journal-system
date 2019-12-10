package main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
//import static main.Main.reviewIDForAuthor;

import main.Main;
import main.tables.*;

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
    private Button loadreview;
    @FXML
    private Button response;
    @FXML
    private Label question;

    @FXML
    private TextField updatedLink;
    @FXML
    private TextField responsetext;


    @FXML
    private VBox vboxpanel1;

    public void init() throws SQLException, IOException {
        reviewsummaryinfo.setText(ReviewTable.SelectSummary(Main.AuthorCurrentReviewID));
        String errors = "";
        ArrayList<String> list = ErrorTable.SelectError(Main.AuthorCurrentReviewID);
        for (int i = 0 ; i < list.size() ; i++) {
            errors += list.get(i) + "\n";
        }
        text.setText(errors);
        verdict.setText(ReviewTable.SelectInitialVerdict(Main.AuthorCurrentReviewID));
    }

    public void handleLogOut(ActionEvent actionEvent) throws IOException {
        Parent view = FXMLLoader.load(getClass().getResource("InitialViewer.fxml"));
        Scene viewScene = new Scene(view);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setResizable(true);
        window.setScene(viewScene);

    }


    public void handleSubmit(ActionEvent actionEvent) throws SQLException, IOException {
        ArticleTable.UpdatePDF(Main.ArticleIDForAuthor, updatedLink.getText());

        Parent view = FXMLLoader.load(getClass().getResource("AuthorPanel.fxml"));
        Scene viewScene = new Scene(view);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setResizable(true);
        window.setScene(viewScene);

    }

    public void handleLoadReview(ActionEvent actionEvent) throws IOException, SQLException {

        URL url = new File("src/resources/ResponseBox.fxml").toURI().toURL();

        VBox box = FXMLLoader.load(getClass().getResource("ResponseBox.fxml"));
        vboxpanel1.getChildren().remove(loadreview);
        vboxpanel1.getChildren().add(box);

        HBox reviewsummary = (HBox) box.getChildren().get(3);
        Label summary = (Label) reviewsummary.getChildren().get(1);
        summary.setText(ReviewTable.SelectSummary(Main.AuthorCurrentReviewID));

        String errorsstring = "";
        ArrayList<String> list = ErrorTable.SelectError(Main.AuthorCurrentReviewID);
        for (int i = 0 ; i < list.size() ; i++) {
            errorsstring += list.get(i) + "\n";
        }

        HBox errors = (HBox) box.getChildren().get(6);
        ScrollPane errorscroll = (ScrollPane) errors.getChildren().get(1);
        VBox errorvbox = (VBox) errorscroll.getContent();
        Text errortext = (Text) errorvbox.getChildren().get(0);
        errortext.setText(errorsstring);

        HBox verdict = (HBox) box.getChildren().get(10);
        Label verdictlabel = (Label) verdict.getChildren().get(3);
        verdictlabel.setText(ReviewTable.SelectInitialVerdict(Main.AuthorCurrentReviewID));

        HBox hbox2 = (HBox) box.getChildren().get(9);
        ScrollPane scroll = (ScrollPane) hbox2.getChildren().get(1);
        VBox s = (VBox) scroll.getContent();

        ArrayList<String> questions = QuestionTable.SelectQuestionText(Main.AuthorCurrentReviewID);

        for (int i = 0; i < (questions).size(); i++) {


            VBox box1 = FXMLLoader.load(getClass().getResource("RespondBox.fxml"));
            HBox box2 = (HBox) box1.getChildren().get(0);
            VBox box3 = (VBox) box2.getChildren().get(0);

            s.getChildren().add(box1);

            Label question = (Label) box3.getChildren().get(0);
            question.setText(questions.get(i));

        }
    }

    public void handleResponse(ActionEvent actionEvent) throws SQLException {
        int questionid = QuestionTable.SelectQuestionID(Main.AuthorCurrentReviewID, question.getText());
        //System.out.println(Main.AuthorCurrentReviewID);
        ResponseTable.Insert(questionid, responsetext.getText());
        response.setVisible(false);
    }
}
