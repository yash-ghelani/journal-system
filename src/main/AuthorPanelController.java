package main;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javafx.event.*;
import main.Main;
import main.tables.*;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;



public class AuthorPanelController{

    @FXML
    private VBox vBoxArticle;

    @FXML
    private Button toRemove;

    @FXML
    private Label submissionID;



    public void handleViewInitialVerdict (ActionEvent event) throws IOException {

        String text = submissionID.getText();
        String subid = text.substring(11);
        int id = Integer.parseInt(subid);
        Main.ArticleIDForAuthor = id;
        //System.out.println(Main.ArticleIDForAuthor);

        Parent view = FXMLLoader.load(getClass().getResource("InitialViewer.fxml"));
        Scene viewScene = new Scene(view);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setResizable(true);
        window.setScene(viewScene);
    }

    public void handleViewFinalVerdict (ActionEvent event) throws IOException {
        String text = submissionID.getText();
        String subid = text.substring(11);
        int id = Integer.parseInt(subid);
        Main.ArticleIDForAuthor = id;

        Parent view = FXMLLoader.load(getClass().getResource("FinalViewer.fxml"));
        Scene viewScene = new Scene(view);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setResizable(true);
        window.setScene(viewScene);
    }

    public void handleLoadArticles (ActionEvent event) throws IOException, SQLException {

        List<Integer> submissions = ArticleInfoTable.SelectArticleIDs(Main.IDs[0]);
        //System.out.println(submissions);

        if (submissions.isEmpty()) {
            toRemove.setStyle("-fx-text-fill : red;");
            toRemove.setText("You do not have any submitted articles");
        }

        for(int i =0; i<submissions.size(); i++) {

            String currentTitle = ArticleTable.SelectTitle(submissions.get(i));
            //String currentStatus = SubmissionTable.GetEditorVerdict(submissions.get(i));
            String currentRole = ArticleInfoTable.SelectAuthorType(Main.IDs[0], submissions.get(i));

            HBox box = FXMLLoader.load(getClass().getResource("ArticleBox.fxml"));
            ObservableList<Node> child = box.getChildren();
            vBoxArticle.getChildren().remove(toRemove);

            VBox v = (VBox)child.get(0);
            VBox v1 = (VBox)child.get(2);
            VBox v2 = (VBox)child.get(3);
            Label title = (Label)v.getChildren().get(0);
            Label submissionID = (Label)v.getChildren().get(1);
            Label role = (Label)v.getChildren().get(2);
            Button initialbutton = (Button)v2.getChildren().get(0);
            Label status = (Label)v1.getChildren().get(0);

            if(ResponseTable.SelectResponseID(QuestionTable.GetQuestionID(ReviewTable.SelectReviewID(submissions.get(i))))>0){
                initialbutton.setVisible(false);
            }

            title.setText(currentTitle);
            submissionID.setText("ArticleID: " + (submissions.get(i)));
            role.setText("Role: " + currentRole);
            status.setText("PDF Link: "+ArticleTable.SelectPDF(submissions.get(i)));

            Insets padding = new Insets(10,0,0,0);
            Separator sep = new Separator();
            sep.setPadding(padding);

            vBoxArticle.getChildren().add(box);
            vBoxArticle.getChildren().add(sep);

        }

    }

    public void handleNewSubmission (ActionEvent event) throws IOException {
        Parent view = FXMLLoader.load(getClass().getResource("NewSubmission.fxml"));
        Scene viewScene = new Scene(view);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setResizable(true);
        window.setScene(viewScene);
    }

    public void handleLogOut (ActionEvent event) throws IOException {
        Parent view = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Scene viewScene = new Scene(view);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setResizable(true);
        window.setScene(viewScene);
    }



}
