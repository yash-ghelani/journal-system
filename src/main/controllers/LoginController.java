package main.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import main.tables.AuthorTable;
import main.tables.EditorTable;
import main.tables.ReviewerTable;


import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;

public class LoginController {

   // public Button button;

    String [] kin = new String [2];

    @FXML
    private javafx.scene.control.TextField loginID;

    @FXML
    private PasswordField Password;



    public void handleRegister(javafx.event.ActionEvent event) throws IOException {
        URL url = new File("src/resources/Register.fxml").toURI().toURL();
        Parent view = FXMLLoader.load(url);
        Scene viewScene = new Scene(view);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(viewScene);
    }

    public void handleRegisterSuccess(javafx.event.ActionEvent event) throws IOException {
        URL url = new File("src/resources/RegisterSuccess.fxml").toURI().toURL();
        Parent view = FXMLLoader.load(url);
        Scene viewScene = new Scene(view);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(viewScene);
    }

    public void handleReader(javafx.event.ActionEvent event) throws IOException {
        URL url = new File("src/resources/Reader.fxml").toURI().toURL();
        Parent view = FXMLLoader.load(url);
        Scene viewScene = new Scene(view);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setResizable(true);
        window.setScene(viewScene);
    }


    public void handleSignIn(ActionEvent event) throws IOException, SQLException {

            if(!loginID.getText().isEmpty() && !Password.getText().isEmpty()) {
                if (AuthorTable.ValidateEmailAndPassword(loginID.getText(), Integer.toString(Password.getText().hashCode()))) {
                    URL url = new File("src/resources/AuthorPanel.fxml").toURI().toURL();
                    Parent view = FXMLLoader.load(url);
                    Scene viewScene = new Scene(view);

                    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    window.setResizable(true);
                    window.setScene(viewScene);
                } else if (EditorTable.ValidateEmailAndPassword(loginID.getText(), Integer.toString(Password.getText().hashCode()))) {
                    URL url = new File("src/resources/EditorPanel.fxml").toURI().toURL();
                    Parent view = FXMLLoader.load(url);
                    Scene viewScene = new Scene(view);

                    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    window.setResizable(true);
                    window.setScene(viewScene);
                } else if (ReviewerTable.ValidateEmailAndPassword(loginID.getText(), Integer.toString(Password.getText().hashCode()))) {
                    URL url = new File("src/resources/ReviewPanel.fxml").toURI().toURL();
                    Parent view = FXMLLoader.load(url);
                    Scene viewScene = new Scene(view);

                    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    window.setResizable(true);
                    window.setScene(viewScene);
                }
            }else {
                loginID.setStyle("-fx-prompt-text-fill :red");
                Password.setStyle("-fx-prompt-text-fill :red");
            }
    }
}
