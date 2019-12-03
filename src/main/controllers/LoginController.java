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

<<<<<<< HEAD
    public void signedin(ActionEvent action) throws IOException {

        kin[0]= "king";

        kin[1] = "Good12";

        if (!login.getText().equals(kin[0])) {
            login.clear();
            login.setStyle("-fx-prompt-text-fill :red");
            login.setPromptText("invalid username");
        }


        if (!password.getText().equals(kin[1])) {
            password.clear();
            password.setStyle("-fx-prompt-text-fill :red");
            password.setPromptText("invalid password");
        }

        if (login.getText().equals(kin[0]) && password.getText().equals(kin[1])){
            URL url = new File("src/resources/sample.fxml").toURI().toURL();
            Parent view = FXMLLoader.load(url);
            Scene viewScene = new Scene(view);

            Stage window = (Stage)((Node)action.getSource()).getScene().getWindow();
            window.setResizable(true);
            window.setScene(viewScene);
        }


    }

    public void handleSignIn(ActionEvent action) throws IOException{
        URL url = new File("src/resources/EAR.fxml").toURI().toURL();
        Parent view = FXMLLoader.load(url);
        Scene viewScene = new Scene(view);

        Stage window = (Stage)((Node)action.getSource()).getScene().getWindow();
        window.setResizable(true);
        window.setScene(viewScene);
=======
//    public void signedin(ActionEvent action) throws IOException {
//
//        kin[0]= "king";
//
//        kin[1] = "Good12";
//
//        if (!login.getText().equals(kin[0])) {
//            login.clear();
//            login.setStyle("-fx-prompt-text-fill :red");
//            login.setPromptText("invalid username");
//        }
//
//
//        if (!password.getText().equals(kin[1])) {
//            password.clear();
//            password.setStyle("-fx-prompt-text-fill :red");
//            password.setPromptText("invalid password");
//        }
//
//        if (login.getText().equals(kin[0]) && password.getText().equals(kin[1])){
//            URL url = new File("src/resources/sample.fxml").toURI().toURL();
//            Parent view = FXMLLoader.load(url);
//            Scene viewScene = new Scene(view);
//
//            Stage window = (Stage)((Node)action.getSource()).getScene().getWindow();
//            window.setResizable(true);
//            window.setScene(viewScene);
//        }
//
//
//    }

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
>>>>>>> 162811e19c3a00dc41ec0a042cfea500bc16af4a
    }
}
