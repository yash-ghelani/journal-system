package main;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

import javafx.event.*;
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
import java.util.List;


public class FinalVerdictController {

    @FXML
    private TextField reviewSummary;

    @FXML
    private ChoiceBox verdictChoiceBox;

    @FXML
    private Label question;

    @FXML
    private Label response;

    @FXML
    private Button loadresponse;

    @FXML
    private VBox vbox;




    public void handleSubmit(ActionEvent action) throws IOException, SQLException {
        int reviewID = ReviewTable.SelectReviewID(Main.IDs[2],Main.ArticleIDForReview);
        System.out.println(verdictChoiceBox.getValue());
        ReviewTable.UpdateFinallVerdict(reviewID,(String)verdictChoiceBox.getValue());

        Parent view = FXMLLoader.load(getClass().getResource("ReviewPanel.fxml"));
        Scene viewScene = new Scene(view);
        Stage window = (Stage)((Node)action.getSource()).getScene().getWindow();
        window.setScene(viewScene);

    }

    public void handleLogOut(ActionEvent actionEvent) throws IOException {
        Parent view = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Scene viewScene = new Scene(view);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setResizable(true);
        window.setScene(viewScene);
    }

    public void handleCancel(ActionEvent actionEvent) throws IOException{
        Parent view = FXMLLoader.load(getClass().getResource("ReviewPanel.fxml"));
        Scene viewScene = new Scene(view);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setResizable(true);
        window.setScene(viewScene);
    }

    public void handleLoadResponse(ActionEvent actionEvent) throws IOException, SQLException {

        VBox box = FXMLLoader.load(getClass().getResource("../../../../Desktop/d/FinalVerdictMainBox.fxml"));
        vbox.getChildren().remove(loadresponse);
        vbox.getChildren().add(box);

        int reviewID = ReviewTable.SelectReviewID(Main.IDs[2],Main.ArticleIDForReview);

        HBox reviewsummary = (HBox) box.getChildren().get(3);
        Label summary = (Label) reviewsummary.getChildren().get(1);
        summary.setText(ReviewTable.SelectSummary(reviewID));

        HBox reviewsummary2 = (HBox) box.getChildren().get(10);
        ChoiceBox choiceBox = (ChoiceBox) reviewsummary2.getChildren().get(3);

        List<String> list = new ArrayList<String>();
        list.add("Champion");
        list.add("Detractor");

        ObservableList obList = FXCollections.observableList(list);
        choiceBox.setItems(obList);

        ArrayList<Integer> questions = QuestionTable.SelectQuestionID(reviewID);

        HBox h = (HBox) box.getChildren().get(9);
        ScrollPane scroll =(ScrollPane) h.getChildren().get(1);
        VBox addv = (VBox) scroll.getContent();


        for (int i = 0; i < (questions).size(); i++) {

            HBox box1 = FXMLLoader.load(getClass().getResource("FinalVerdictBox.fxml"));
            Label question = (Label)box1.getChildren().get(0);
            question.setText(QuestionTable.GetQuestionText(questions.get(i))+": "+ ResponseTable.SelectResponseText(questions.get(i)));
            addv.getChildren().add(box1);
        }

        String errorsstring = "";
        ArrayList<String> lister = ErrorTable.SelectError(reviewID);
        for (int i = 0 ; i < lister.size() ; i++) {
            errorsstring += lister.get(i) + "\n";
        }

        HBox errors = (HBox) box.getChildren().get(6);
        ScrollPane errorscroll = (ScrollPane) errors.getChildren().get(1);
        VBox errorvbox = (VBox) errorscroll.getContent();
        Text errortext = (Text) errorvbox.getChildren().get(0);
        errortext.setText(errorsstring);
    }
}
