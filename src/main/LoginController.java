package main;

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
import java.util.ArrayList;

public class LoginController {

    @FXML
    private javafx.scene.control.TextField loginID;

    @FXML
    private PasswordField Password;

    public void handleRegister(javafx.event.ActionEvent event) throws IOException {
        Parent view = FXMLLoader.load(getClass().getResource("Register.fxml"));
        Scene viewScene = new Scene(view);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setResizable(true);
        window.setScene(viewScene);
    }

    public void handleReader(javafx.event.ActionEvent event) throws IOException {
        Parent view = FXMLLoader.load(getClass().getResource("Reader.fxml"));
        Scene viewScene = new Scene(view);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setResizable(true);
        window.setScene(viewScene);
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


//            if (Main.IDs[0] != -1) {
//                Author = true;
//            } else if (Main.IDs[1] != -1) {
//                Editor = true;
//            } if (Main.IDs[2] != -1) {
//                Reviewer = true;
//            }

            if (Main.IDs[0] != -1) {
                Author = true;
            }
            if (Main.IDs[1] != -1) {
                Editor = true;
            }
            if (Main.IDs[2] != -1) {
                Reviewer = true;
            }

            if (checkReviewer(Main.IDs[2])) {
                Reviewer = false;
            }

            System.out.println("check: " + checkReviewer(Main.IDs[2]));

            System.out.println(Main.IDs[0] +" "+ Author);
            System.out.println(Main.IDs[1] +" "+ Editor);
            System.out.println(Main.IDs[2] +" "+ Reviewer);

            // load page depending on user roles
            if (Author && Editor && Reviewer) {
                Parent view = FXMLLoader.load(getClass().getResource("EAR.fxml"));
                Scene viewScene = new Scene(view);
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setResizable(true);
                window.setScene(viewScene);
            } else if (Author && Editor && !Reviewer) {
                Parent view = FXMLLoader.load(getClass().getResource("EA.fxml"));
                Scene viewScene = new Scene(view);
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setResizable(true);
                window.setScene(viewScene);
            } else if (!Author && Editor && Reviewer) {
                Parent view = FXMLLoader.load(getClass().getResource("ER.fxml"));
                Scene viewScene = new Scene(view);
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setResizable(true);
                window.setScene(viewScene);
            }  else if (Author && !Editor && Reviewer) {
                Parent view = FXMLLoader.load(getClass().getResource("RA.fxml"));
                Scene viewScene = new Scene(view);
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setResizable(true);
                window.setScene(viewScene);
            } else if (Author && !Editor && !Reviewer) {
                Parent view = FXMLLoader.load(getClass().getResource("AuthorPanel.fxml"));
                Scene viewScene = new Scene(view);
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setResizable(true);
                window.setScene(viewScene);
            } else if (!Author && Editor && !Reviewer) {
                Parent view = FXMLLoader.load(getClass().getResource("Editor.fxml"));
                Scene viewScene = new Scene(view);
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setResizable(true);
                window.setScene(viewScene);
            } else if (!Author && !Editor && Reviewer) {
                Parent view = FXMLLoader.load(getClass().getResource("ReviewPanel.fxml"));
                Scene viewScene = new Scene(view);
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setResizable(true);
                window.setScene(viewScene);
            } else {
                System.out.println("No role");
                error();
            }

        } else {
            System.out.println("Not a user");
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
                Parent view = FXMLLoader.load(getClass().getResource("UpdateTempUser.fxml"));
                Scene viewScene = new Scene(view);
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setResizable(true);
                window.setScene(viewScene);
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

    public boolean checkReviewer(int reviewerid) throws SQLException {
        int count = 0;
        ArrayList<Integer> articleid = ReviewTable.SelectArticleIDFromReviewerID(reviewerid);
        int istemp = ReviewerTable.SelectTemp(Main.IDs[2]);

        for (int i = 0 ; i < articleid.size() ; i++) {
            boolean ispublished = ArticleTable.isPublished(articleid.get(i));
            System.out.println(ispublished);
            if (ispublished) {
                count++;
            }
        }

        if (count > 3 && articleid.size() == 3 && istemp == 0) {
            return true;
        } else {
            return false;
        }
    }
}
