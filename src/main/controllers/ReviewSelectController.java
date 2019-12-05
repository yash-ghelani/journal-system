package main.controllers;

import com.mysql.cj.log.NullLogger;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javafx.event.*;
import main.Main;
import main.tables.ReviewTable;
import main.tables.SubmissionInfoTable;
import main.tables.SubmissionTable;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;

public class ReviewSelectController {


    @FXML
    private Button toRemove;

    @FXML
    private VBox vBoxArticle;

    @FXML
    private Label submissionID;

    @FXML
    private Label title;

    @FXML
    private HBox review;

    @FXML
    private Button select;



    public void handleBack(ActionEvent event) throws IOException {
        URL url = new File("src/resources/ReviewPanel.fxml").toURI().toURL();
        Parent view = FXMLLoader.load(url);
        Scene viewScene = new Scene(view);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(viewScene);
    }

    public void handleLoadArticles(ActionEvent event) throws IOException, SQLException {
        List<String> submissions = SubmissionTable.SelectAllSubmissionTitles();
        System.out.println(submissions);

        for(int i =0; i<submissions.size(); i++) {


            URL url = new File("src/resources/ReviewSelectBox.fxml").toURI().toURL();

            HBox box = FXMLLoader.load(url);
            ObservableList<Node> child = box.getChildren();
            vBoxArticle.getChildren().remove(toRemove);

            VBox v = (VBox)child.get(0);
            Label title = (Label)v.getChildren().get(0);
            Label submissionID = (Label)v.getChildren().get(1);


            title.setText(submissions.get(i));
            submissionID.setText("Submission ID: "+SubmissionTable.selectSubmissionID(submissions.get(i)));
            //role.setText(String.valueOf(i));

            //ReviewTable.SelectReviewID(SubmissionInfoTable.getSubmissionInfoID(i));
            System.out.println(ReviewTable.CheckReviewID(SubmissionInfoTable.getSubmissionInfoID(i)));

            Insets padding = new Insets(10,0,0,0);
            Separator sep = new Separator();
            sep.setPadding(padding);

            vBoxArticle.getChildren().add(box);
            vBoxArticle.getChildren().add(sep);



        }
    }

    public void handleSelectReview(ActionEvent actionEvent) throws SQLException {
        String strap = submissionID.getText();
        int iD = Character.getNumericValue(strap.charAt(strap.length()-1));
        System.out.println(ReviewTable.CheckReviewID(SubmissionInfoTable.getSubmissionInfoID(iD)));

        //ReviewTable.Insert(Main.IDs[2],iD,null,null);
        review.getChildren().remove(select);
        select.setVisible(false);
        select.setDisable(true);



    }


}
