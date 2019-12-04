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
import main.Main;
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
        URL url = new File("src/resources/InitialVerdict.fxml").toURI().toURL();
        Parent view = FXMLLoader.load(url);
        Scene viewScene = new Scene(view);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setResizable(true);
        window.setScene(viewScene);

//        String login = loginID.getText();
//        String password = Integer.toString(Password.getText().hashCode());
//
//        boolean isAuthor = AuthorTable.ValidateEmailAndPassword(login, password);
//        boolean isEditor = EditorTable.ValidateEmailAndPassword(login, password);
//        boolean isReviewer = ReviewerTable.ValidateEmailAndPassword(login, password);
//
//        Main.IDs[0] = AuthorTable.getID(login, password);
//        Main.IDs[1] = EditorTable.getID(login, password);
//        Main.IDs[2] = ReviewerTable.getID(login, password);
//
//        if(!loginID.getText().isEmpty() && !Password.getText().isEmpty()) {
//            if (isEditor && isAuthor && isReviewer) {
//                URL url = new File("src/resources/EAR.fxml").toURI().toURL();
//                Parent view = FXMLLoader.load(url);
//                Scene viewScene = new Scene(view);
//
//                Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
//                window.setResizable(true);
//                window.setScene(viewScene);
//            } else if (isEditor && isAuthor && !isReviewer) {
//                URL url = new File("src/resources/EA.fxml").toURI().toURL();
//                Parent view = FXMLLoader.load(url);
//                Scene viewScene = new Scene(view);
//
//                Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
//                window.setResizable(true);
//                window.setScene(viewScene);
//            } else if (isEditor && !isAuthor && isReviewer) {
//                URL url = new File("src/resources/ER.fxml").toURI().toURL();
//                Parent view = FXMLLoader.load(url);
//                Scene viewScene = new Scene(view);
//
//                Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
//                window.setResizable(true);
//                window.setScene(viewScene);
//            }  else if (!isEditor && isReviewer && isAuthor) {
//                URL url = new File("src/resources/RA.fxml").toURI().toURL();
//                Parent view = FXMLLoader.load(url);
//                Scene viewScene = new Scene(view);
//
//                Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
//                window.setResizable(true);
//                window.setScene(viewScene);
//            } else if (isEditor && !isAuthor && !isReviewer) {
//                URL url = new File("src/resources/EditorPanel.fxml").toURI().toURL();
//                Parent view = FXMLLoader.load(url);
//                Scene viewScene = new Scene(view);
//
//                Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
//                window.setResizable(true);
//                window.setScene(viewScene);
//            } else if (!isEditor && isAuthor && !isReviewer) {
//                URL url = new File("src/resources/AuthorPanel.fxml").toURI().toURL();
//                Parent view = FXMLLoader.load(url);
//                Scene viewScene = new Scene(view);
//
//                Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
//                window.setResizable(true);
//                window.setScene(viewScene);
//            } else if (!isEditor && !isAuthor && isReviewer) {
//                URL url = new File("src/resources/ReviewPanel.fxml").toURI().toURL();
//                Parent view = FXMLLoader.load(url);
//                Scene viewScene = new Scene(view);
//
//                Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
//                window.setResizable(true);
//                window.setScene(viewScene);
//            }
//        }else {
//            loginID.setStyle("-fx-prompt-text-fill :red");
//            Password.setStyle("-fx-prompt-text-fill :red");
//        }
    }
}
