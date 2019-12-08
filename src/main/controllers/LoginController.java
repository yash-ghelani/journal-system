package main.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import main.Main;
import main.tables.*;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.regex.Pattern;

public class LoginController {

    @FXML
    private javafx.scene.control.TextField loginID;

    @FXML
    private PasswordField Password;

    public void handleRegister(javafx.event.ActionEvent event) throws IOException {
        loadScene(event, "src/resources/Register.fxml");
    }

    public void handleReader(javafx.event.ActionEvent event) throws IOException {
        loadScene(event, "src/resources/Reader.fxml");
    }


    public void handleSignIn(ActionEvent event) throws IOException, SQLException {

        // verify user exists
        String login = loginID.getText();
        String password = Integer.toString(Password.getText().hashCode());
        int userID = UserTable.ValidateEmailAndPassword(login, password);

        // if user exists
        if(userID != -1) {

            // check what roles the user has
            Main.IDs[0] = AuthorTable.GetID(userID);
            Main.IDs[1] = EditorTable.GetID(userID);
            Main.IDs[2] = ReviewerTable.GetID(userID);

            boolean Author = false;
            boolean Editor = false;
            boolean Reviewer = false;


            if (Main.IDs[0] != -1) {
                Author = true;
            } else if (Main.IDs[1] != -1) {
                Editor = true;
            } if (Main.IDs[2] != -1) {
                Reviewer = true;
            }

            System.out.println(Main.IDs[0] +" "+ Author);
            System.out.println(Main.IDs[1] +" "+ Editor);
            System.out.println(Main.IDs[2] +" "+ Reviewer);

            // load page depending on user roles
            if (Author && Editor && Reviewer) {
                loadScene(event, "src/resources/EAR.fxml");
            } else if (Author && Editor && !Reviewer) {
                loadScene(event, "src/resources/EA.fxml");
            } else if (!Author && Editor && Reviewer) {
                loadScene(event, "src/resources/ER.fxml");
            }  else if (Author && !Editor && Reviewer) {
                loadScene(event, "src/resources/RA.fxml");
            } else if (Author && !Editor && !Reviewer) {
                loadScene(event, "src/resources/AuthorPanel.fxml");
            } else if (!Author && Editor && !Reviewer) {
                loadScene(event,"src/resources/EditorPanel.fxml");
            } else if (!Author && !Editor && Reviewer) {
                loadScene(event, "src/resources/ReviewPanel.fxml");
            } else {
                System.out.println("Not a user");
                error();
            }

        } else {
            error();
        }
    }

    public void handleChangePassword(ActionEvent event) throws IOException, SQLException {

        String login = loginID.getText();
        String password = Integer.toString(Password.getText().hashCode());
        int userID = UserTable.ValidateEmailAndPassword(login, password);

        // if user exists
        if(userID != -1) {

            // check what roles the user has
            Main.IDs[0] = AuthorTable.GetID(userID);
            Main.IDs[1] = EditorTable.GetID(userID);
            Main.IDs[2] = ReviewerTable.GetID(userID);

            if((Main.IDs[1] != -1) || (Main.IDs[1] != -1) || (Main.IDs[1] == -1)) {
                loadScene(event, "src/resources/UpdateTempUser.fxml");
            } else {
                error();
            }

        } else {
            error();
        }

    }

    public void error () {
        loginID.setStyle("-fx-border-color: red; -fx-border-width: 2px;-fx-prompt-text-fill : red;");
        Password.setStyle("-fx-border-color: red; -fx-border-width: 2px;-fx-prompt-text-fill : red;");
        loginID.clear();
        Password.clear();
    }

    public void loadScene (ActionEvent action, String pathname) throws IOException {
        URL url = new File(pathname).toURI().toURL();
        Parent view = FXMLLoader.load(url);
        Scene viewScene = new Scene(view);
        Stage window = (Stage) ((Node) action.getSource()).getScene().getWindow();
        window.setResizable(true);
        window.setScene(viewScene);
    }
}
