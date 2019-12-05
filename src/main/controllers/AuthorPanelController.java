package main.controllers;

import com.sun.jdi.connect.Connector;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
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
import main.tables.ArticleTable;
import main.tables.SubmissionInfoTable;
import main.tables.SubmissionTable;

import java.awt.desktop.SystemSleepEvent;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;


public class AuthorPanelController{

    @FXML
    private VBox vBoxArticle;

    @FXML
    private Button toRemove;




    public void handleViewInitialVerdict (ActionEvent event) throws IOException {
        URL url = new File("src/resources/InitialViewer.fxml").toURI().toURL();
        Parent view = FXMLLoader.load(url);
        Scene viewScene = new Scene(view);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(viewScene);
    }

    public void handleViewFinalVerdict (ActionEvent event) throws IOException {
        URL url = new File("src/resources/FinalViewer.fxml").toURI().toURL();
        Parent view = FXMLLoader.load(url);
        Scene viewScene = new Scene(view);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(viewScene);
    }


    public void handleLoadArticles (ActionEvent event) throws IOException, SQLException {

        List<Integer> submissions = SubmissionInfoTable.SelectWhichSubmissionID(Main.IDs[0]);
        //System.out.println(submissions);

        for(int i =0; i<submissions.size(); i++) {

            String currentTitle = SubmissionTable.SelectTitle(submissions.get(i));
            String currentStatus = SubmissionTable.GetEditorVerdict(submissions.get(i));


            URL url = new File("src/resources/ArticleBox.fxml").toURI().toURL();

            HBox box = FXMLLoader.load(url);
            ObservableList<Node> child = box.getChildren();
            vBoxArticle.getChildren().remove(toRemove);

            VBox v = (VBox)child.get(0);
            Label title = (Label)v.getChildren().get(0);
            Label submissionID = (Label)v.getChildren().get(1);
            Label role = (Label)v.getChildren().get(2);
            Label status = (Label)v.getChildren().get(3);

            title.setText(currentTitle);
            submissionID.setText("Submission ID: "+(submissions.get(i)));
            //role.setText(String.valueOf(i));
            status.setText("Status: "+currentStatus);

            Insets padding = new Insets(10,0,0,0);
            Separator sep = new Separator();
            sep.setPadding(padding);

            vBoxArticle.getChildren().add(box);
            vBoxArticle.getChildren().add(sep);

        }

    }



    public void handleNewSubmission (ActionEvent event) throws IOException {
        URL url = new File("src/resources/NewSubmission.fxml").toURI().toURL();
        Parent view = FXMLLoader.load(url);
        Scene viewScene = new Scene(view);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(viewScene);
    }

    public void handleLogOut (ActionEvent event) throws IOException {
        URL url = new File("src/resources/Login.fxml").toURI().toURL();
        Parent view = FXMLLoader.load(url);
        Scene viewScene = new Scene(view);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(viewScene);
    }



}
