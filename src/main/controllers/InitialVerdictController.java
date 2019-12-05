package main.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import main.tables.*;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static main.Main.SubmissionIDForReview;

public class InitialVerdictController {

    @FXML
    private TextField typoError;
    @FXML
    private TextField criticism;
    @FXML
    private VBox vBox;
    @FXML
    private VBox vBoxCriticism;

    @FXML
    private TextField reviewSummary;
    @FXML
    private ComboBox finalVerdict;
    @FXML
    private Label submissionid;
    @FXML
    private Label reviewid;

    private List<String> crits = new ArrayList<String>(); //store criticisms
    private List<String> errors = new ArrayList<String>(); // store errors

    public void initialize() throws SQLException {
        List<String> list = new ArrayList<String>();
        list.add("Champion");
        list.add("Detractor");
        ObservableList obList = FXCollections.observableList(list);
        finalVerdict.setItems(obList);
        submissionid.setText("Submission ID: "+ SubmissionIDForReview);
        reviewid.setText("Review ID: " + ReviewTable.selectReviewID(SubmissionIDForReview));

    }


    public void handleTypoError(ActionEvent event) throws IOException {
        Text text = new Text();
        text.setText(typoError.getText());
        vBox.getChildren().add(text);

        errors.add(typoError.getText());

        typoError.clear();
    }


    public void handleAddCriticism(ActionEvent event) throws IOException {
        Text text = new Text();
        text.setText(criticism.getText());
        vBoxCriticism.getChildren().add(text);

        crits.add(criticism.getText());

        criticism.clear();
    }

    public void handleCancel(ActionEvent event) throws IOException {
        URL url = new File("src/resources/ReviewPanel.fxml").toURI().toURL();
        Parent view = FXMLLoader.load(url);
        Scene viewScene = new Scene(view);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(viewScene);
    }

    public void handleSubmit(ActionEvent actionEvent) throws SQLException, IOException {

        String summary = reviewSummary.getText();

        ReviewTable.UpdateSummary(SubmissionIDForReview, summary);
        ReviewTable.UpdateVerdict(SubmissionIDForReview, (String) finalVerdict.getValue());

        int reviewid = ReviewTable.SelectReviewID(Main.IDs[2], SubmissionIDForReview, summary, (String) finalVerdict.getValue());

        for (int i = 0 ; i < errors.size() ; i++) {
            ErrorTable.Insert(reviewid, errors.get(i));
        }

        for (int i = 0 ; i < crits.size(); i++) {
            CriticismsTable.Insert(reviewid, crits.get(i));
        }

        URL url = new File("src/resources/ReviewPanel.fxml").toURI().toURL();
        Parent view = FXMLLoader.load(url);
        Scene viewScene = new Scene(view);
        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(viewScene);

    }
}
