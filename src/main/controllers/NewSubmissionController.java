package main.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.Main;
import main.tables.*;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;


public class NewSubmissionController {

    @FXML
    public Label idLab;
    public TextField email;
    public Label coAuth1;
    public Label coAuth2;
    public Label coAuth3;
    public TextField title;
    public TextArea abstractTA;
    public TextField pdf;
    public ChoiceBox journals;


    ArrayList<Integer> ids = new  ArrayList<>();
    int authCount = 1;

    public void initialize () throws SQLException {
        idLab.setText("Author ID: "+Main.IDs[0]);
        // getting list of journals
        List<String> journalList = JournalTable.SelectJournals();
        ObservableList jList = FXCollections.observableList(journalList);
        journals.setItems(jList);
    }

    public void handleCancel (ActionEvent event) throws IOException {
        loadScene(event, "src/resources/AuthorPanel.fxml");
    }

    public void handleSubmit (ActionEvent event) throws IOException, SQLException {

        if (!title.getText().isEmpty() && !abstractTA.getText().isEmpty() && (!pdf.getText().isEmpty() || !pdf.getText().endsWith(".pdf")) && !journals.getSelectionModel().isEmpty()) {

            System.out.println("Passed basic validation");
            //creating article
            ArticleTable.Insert(JournalTable.SelectISSN((String) journals.getValue()), title.getText(), abstractTA.getText(), pdf.getText(), null, 0);
            System.out.println("Inserted article");
            int articleID = ArticleTable.GetID();
            System.out.println(articleID);

            //adding main author
            ArticleInfoTable.Insert(articleID, Main.IDs[0], 0);
            System.out.println("added author");
            //adding co authors
            for (int i = 0; i < ids.size(); i++){
                ArticleInfoTable.Insert(articleID, ids.get(i), 1);
                System.out.println("added co author "+ ids.get(i));
            }

            loadScene(event, "src/resources/AuthorPanel.fxml");

        } else {

            if (journals.getSelectionModel().isEmpty()) {
                journals.setStyle("-fx-border-color: red; -fx-border-width: 2px;-fx-prompt-text-fill : red;");
            }
            if (title.getText().isEmpty()){
                title.setStyle("-fx-border-color: red; -fx-border-width: 2px;-fx-prompt-text-fill : red;");
            }
            if (abstractTA.getText().isEmpty()) {
                abstractTA.setStyle("-fx-border-color: red; -fx-border-width: 2px;-fx-prompt-text-fill : red;");
            }
            if (pdf.getText().isEmpty() || !pdf.getText().endsWith(".pdf")) {
                pdf.setStyle("-fx-border-color: red; -fx-border-width: 2px;-fx-prompt-text-fill : red;");
            }

        }
    }

    public void handleRegister(ActionEvent actionEvent) throws SQLException {

        if (validEmail()) {
            if (authCount == 1) {
                coAuth1.setText("Co-Author 1 Temporary Login: " + email.getText()+ ", " + Math.abs((email.getText()).hashCode()));
                insertCoAuthor();
                authCount += 1;
            } else if (authCount == 2) {
                coAuth2.setText("Co-Author 2 Temporary Login: " + email.getText()+ ", " + Math.abs((email.getText()).hashCode()));
                insertCoAuthor();
                authCount += 1;
            } else if (authCount == 3) {
                coAuth3.setText("Co-Author 3 Temporary Login: " + email.getText()+ ", " + Math.abs((email.getText()).hashCode()));
                insertCoAuthor();
                authCount += 1;
            } else {
                email.clear();
                email.setStyle("-fx-border-color: red; -fx-border-width: 2px;-fx-prompt-text-fill : red;");
                email.setPromptText("You can only register a maximum of 3 authors");
            }
        }
    }

    private void insertCoAuthor() throws SQLException {
        UserTable.Insert("temp","temp","user","temp", email.getText(), String.valueOf(Math.abs((email.getText()).hashCode())));
        int id = UserTable.ValidateEmailAndPassword(email.getText(), String.valueOf(Math.abs((email.getText()).hashCode())));
        AuthorTable.Insert(id, 1);
        ids.add(AuthorTable.GetID(id));
        email.clear();
    }

    public boolean validEmail(){
        //emailField
        if (Pattern.matches("[0-9A-Za-z.]+[@][a-zA-z]+[.][A-Za-z.]+", email.getText())) {
            return true;
        } else {
            email.setStyle("-fx-prompt-text-fill : red;");
            email.clear();
            email.setPromptText("Not valid email type");
            return false;
        }
    }

    public void loadScene (ActionEvent action, String pathname) throws IOException {
        URL url = new File(pathname).toURI().toURL();
        Parent view = FXMLLoader.load(url);
        Scene viewScene = new Scene(view);
        Stage window = (Stage) ((Node) action.getSource()).getScene().getWindow();
        window.setResizable(true);
        window.setScene(viewScene);
    }

    public void addExistingAuthor(ActionEvent actionEvent) {

    }

    public void addNewAuthor(ActionEvent actionEvent) {
    }
}
