package main.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.tables.*;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;

public class NewSubmissionController {

    @FXML
    public Label idLab;
    public TextField email;

    public void handleCancel (ActionEvent event) throws IOException {
        URL url = new File("src/resources/AuthorPanel.fxml").toURI().toURL();
        Parent view = FXMLLoader.load(url);
        Scene viewScene = new Scene(view);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(viewScene);
    }

    public void handleSubmit (ActionEvent event) throws IOException {
        URL url = new File("src/resources/AuthorPanel.fxml").toURI().toURL();
        Parent view = FXMLLoader.load(url);
        Scene viewScene = new Scene(view);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(viewScene);
    }

    public void handleRegister(ActionEvent actionEvent) throws SQLException {
        idLab.setText("Temporary Username: " + email.getText()+ "\n Temporary Password: " + Math.abs((email.getText()).hashCode()));
        AuthorTable.Insert("temp","temp","user","temp", email.getText(), String.valueOf(Math.abs((email.getText()).hashCode())), 1);
    }
}
