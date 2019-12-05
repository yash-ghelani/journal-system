package main.controllers;

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
import main.tables.ReviewTable;
import main.tables.SubmissionTable;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ReviewPanelController {

    @FXML
    private Button clickToAdd;

    @FXML
    private VBox vboxpanel;

    public void handleNewReview(ActionEvent event) throws IOException {
        URL url = new File("src/resources/ReviewSelect.fxml").toURI().toURL();
        Parent view = FXMLLoader.load(url);
        Scene viewScene = new Scene(view);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(viewScene);
    }

    public void handleInitialVerdict(ActionEvent event) throws IOException {
        URL url = new File("src/resources/InitialVerdict.fxml").toURI().toURL();
        Parent view = FXMLLoader.load(url);
        Scene viewScene = new Scene(view);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(viewScene);
    }

    public void handleViewArticle(ActionEvent event) throws IOException {
        URL url = new File("src/resources/ReviewPanel.fxml").toURI().toURL();
        Parent view = FXMLLoader.load(url);
        Scene viewScene = new Scene(view);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(viewScene);
    }

    public void handleViewResponse(ActionEvent event) throws IOException {
        URL url = new File("src/resources/FinalVerdict.fxml").toURI().toURL();
        Parent view = FXMLLoader.load(url);
        Scene viewScene = new Scene(view);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(viewScene);
    }

    public void handleLogOut(ActionEvent event) throws IOException {
        URL url = new File("src/resources/Login.fxml").toURI().toURL();
        Parent view = FXMLLoader.load(url);
        Scene viewScene = new Scene(view);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(viewScene);
    }


    public void handleClick(ActionEvent actionEvent) throws IOException, SQLException {

        ArrayList<Integer> submissionids = ReviewTable.selectListOfSubmissionID(Main.IDs[2]);

        for (int i = 0; i < (submissionids).size(); i++) {


            URL url = new File("src/resources/ReviewPanelBox.fxml").toURI().toURL();

            HBox box = FXMLLoader.load(url);
            ObservableList<Node> child = box.getChildren();
            vboxpanel.getChildren().remove(clickToAdd);

            VBox v = (VBox) child.get(0);
            Label title = (Label) v.getChildren().get(0);
            Label submissioninfoID = (Label) v.getChildren().get(1);



            title.setText(SubmissionTable.selectInitialTitle(submissionids.get(i)));
            System.out.println(submissionids.get(i));

            submissioninfoID.setText("Submission Info ID: " + submissionids.get(i));
            //role.setText(String.valueOf(i));




            Insets padding = new Insets(10, 0, 0, 0);
            Separator sep = new Separator();
            sep.setPadding(padding);

            vboxpanel.getChildren().add(box);
            vboxpanel.getChildren().add(sep);
        }
    }
}
