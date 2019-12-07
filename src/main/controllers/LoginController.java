package main.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import main.Main;
import main.tables.*;


import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.regex.Pattern;

public class LoginController {

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

        String login = loginID.getText();
        String password = Integer.toString(Password.getText().hashCode());



        boolean isAuthor = AuthorTable.ValidateEmailAndPassword(login, password);
        boolean isEditor = EditorTable.ValidateEmailAndPassword(login, password);
        boolean isReviewer = ReviewerTable.ValidateEmailAndPassword(login, password);

        Main.IDs[0] = AuthorTable.getID(login, password);
        Main.IDs[1] = EditorTable.getID(login, password);
        Main.IDs[2] = ReviewerTable.getID(login, password);

        if(Pattern.matches("[A-Za-z.]+[@][a-zA-z]+[.][A-Za-z.]+", loginID.getText()) && Pattern.matches("[a-zA-Z0-9[^\\dA-Za-zA-Za-z0-9]]{6,}", Password.getText())) {
            if (isEditor && isAuthor && isReviewer) {
                URL url = new File("src/resources/EAR.fxml").toURI().toURL();
                Parent view = FXMLLoader.load(url);
                Scene viewScene = new Scene(view);

                Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
                window.setResizable(true);
                window.setScene(viewScene);
            } else if (isEditor && isAuthor && !isReviewer) {
                URL url = new File("src/resources/EA.fxml").toURI().toURL();
                Parent view = FXMLLoader.load(url);
                Scene viewScene = new Scene(view);

                Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
                window.setResizable(true);
                window.setScene(viewScene);
            } else if (isEditor && !isAuthor && isReviewer) {
                URL url = new File("src/resources/ER.fxml").toURI().toURL();
                Parent view = FXMLLoader.load(url);
                Scene viewScene = new Scene(view);

                Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
                window.setResizable(true);
                window.setScene(viewScene);
            }  else if (!isEditor && isReviewer && isAuthor) {
                URL url = new File("src/resources/RA.fxml").toURI().toURL();
                Parent view = FXMLLoader.load(url);
                Scene viewScene = new Scene(view);

                Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
                window.setResizable(true);
                window.setScene(viewScene);
            } else if (isEditor && !isAuthor && !isReviewer) {
                URL url = new File("src/resources/EditorPanel.fxml").toURI().toURL();
                Parent view = FXMLLoader.load(url);
                Scene viewScene = new Scene(view);

                Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
                window.setResizable(true);
                window.setScene(viewScene);
            } else if (!isEditor && isAuthor && !isReviewer) {
                URL url = new File("src/resources/AuthorPanel.fxml").toURI().toURL();
                Parent view = FXMLLoader.load(url);
                Scene viewScene = new Scene(view);

                Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
                window.setResizable(true);
                window.setScene(viewScene);
            } else if (!isEditor && !isAuthor && isReviewer) {
                URL url = new File("src/resources/ReviewPanel.fxml").toURI().toURL();
                Parent view = FXMLLoader.load(url);
                Scene viewScene = new Scene(view);

                Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
                window.setResizable(true);
                window.setScene(viewScene);
            }
        } else if (!isAuthor || !isEditor || !isReviewer) {

            loginID.setStyle("-fx-border-color: red; -fx-border-width: 2px;-fx-prompt-text-fill : red;");
            Password.setStyle("-fx-border-color: red; -fx-border-width: 2px;-fx-prompt-text-fill : red;");
            loginID.clear();
            Password.clear();

        } else {

            loginID.setStyle("-fx-border-color: red; -fx-border-width: 2px;-fx-prompt-text-fill : red;");
            Password.setStyle("-fx-border-color: red; -fx-border-width: 2px;-fx-prompt-text-fill : red;");
            loginID.clear();
            Password.clear();
        }
    }

    public void handleChangePassword(ActionEvent event) throws IOException, SQLException {

        String login = loginID.getText();
        String password = Integer.toString(Password.getText().hashCode());

        Main.IDs[0] = AuthorTable.getID(login, password);
        Main.IDs[1] = EditorTable.getID(login, password);
        Main.IDs[2] = ReviewerTable.getID(login, password);

        boolean isAuthor = AuthorTable.ValidateEmailAndPassword(login, password);
        boolean isEditor = EditorTable.ValidateEmailAndPassword(login, password);
        boolean isReviewer = ReviewerTable.ValidateEmailAndPassword(login, password);

        if(!loginID.getText().isEmpty() && !Password.getText().isEmpty() && (isAuthor || isEditor || isReviewer)) {
            URL url = new File("src/resources/UpdateTempUser.fxml").toURI().toURL();
            Parent view = FXMLLoader.load(url);
            Scene viewScene = new Scene(view);
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(viewScene);
        } else {
            loginID.setStyle("-fx-prompt-text-fill :red");
            Password.setStyle("-fx-prompt-text-fill :red");
            loginID.clear();
            Password.clear();
        }
    }
}
