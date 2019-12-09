package main.controllers;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;

import javafx.scene.control.*;
import javafx.event.*;
import javafx.stage.Stage;
import main.tables.*;

import java.sql.SQLException;
import java.util.regex.Pattern;

public class AddAuthor {

    public TextField surname;
    public TextField affiliation;
    public ChoiceBox title;
    public TextField email;
    public PasswordField password;
    public TextField forename;
    public Label sLab;

    String [] str = {"Mr.","Ms.","Dr.","Prof."};

    int aCount = 1;

    public void initialize(){
        title.setValue("Mr.");
        title.getItems().addAll(FXCollections.observableArrayList(str));
        //System.out.println();
    }



    public void actionAdd(ActionEvent actionEvent) throws NullPointerException, SQLException {

        boolean t = validTitle();
        boolean fn = validFirstName();
        boolean ln = validLastName();
        boolean a = validAffiliation();
        boolean em = validEmail();
        boolean pw = validPassword();

        Stage stage = (Stage) title.getScene().getWindow();

        if (t && fn && ln && a && em && pw) {

            if (NewSubmissionController.authCount == 1) {
                insertCoAuthor();
                sLab.setText("Co-author: "+ aCount +" added");
                aCount += 1;
            } else if (NewSubmissionController.authCount == 2) {
                insertCoAuthor();
                sLab.setText("Co-author: "+ aCount +" added");
                aCount += 1;
            } else if (NewSubmissionController.authCount == 3) {
                insertCoAuthor();
                sLab.setText("Co-author: "+ aCount +" added");
                aCount += 1;
            } else {
                sLab.setText("You can only add 3 co-authors");
                stage.close();
            }
        }
    }

    private void insertCoAuthor() throws SQLException {
        UserTable.Insert((String) title.getValue(),forename.getText(),surname.getText(),affiliation.getText(), email.getText(), String.valueOf(password.getText().hashCode()));
        int id = UserTable.ValidateEmailAndPassword(email.getText(), String.valueOf(Math.abs((email.getText()).hashCode())));
        AuthorTable.Insert(id, 1);
        NewSubmissionController.ids.add(AuthorTable.GetID(id));
    }

    // *************************************validation methods******************************************************
    public boolean validTitle(){
        if (title.getSelectionModel().isEmpty()) {
            title.setStyle("-fx-border-color: red; -fx-border-width: 2px;-fx-prompt-text-fill : red;");
            return false;
        } else {
            return true;
        }
    }

    public boolean validFirstName(){
        // first name
        if (forename.getText().isEmpty() || !forename.getText().chars().allMatch(Character::isLetter)) {
            forename.setStyle("-fx-border-color: red; -fx-border-width: 2px;-fx-prompt-text-fill : red;");
            forename.clear();
            return false;
        } else {
            return true;
        }
    }

    public boolean validLastName(){
        // last name
        if (surname.getText().isEmpty() || !surname.getText().chars().allMatch(Character::isLetter)) {
            surname.setStyle("-fx-border-color: red; -fx-border-width: 2px;-fx-prompt-text-fill : red;");
            surname.clear();
            return false;
        } else {
            return true;
        }
    }

    public boolean validEmail(){
        //emailField
        if (Pattern.matches("[0-9A-Za-z.]+[@][a-zA-z]+[.][A-Za-z.]+", email.getText())) {
            return true;
        } else {
            email.setStyle("-fx-border-color: red; -fx-border-width: 2px;-fx-prompt-text-fill : red;");
            email.clear();
            email.setPromptText("Not valid email type");
            return false;
        }
    }

    public boolean validAffiliation(){
        if (affiliation.getText().isEmpty() || !affiliation.getText().chars().allMatch(Character::isLetter)) {
            affiliation.setStyle("-fx-border-color: red; -fx-border-width: 2px;-fx-prompt-text-fill : red;");
            affiliation.clear();
            return false;
        } else {
            return true;
        }
    }

    public boolean validPassword(){
        if (Pattern.matches("[a-zA-Z0-9[^\\dA-Za-zA-Za-z0-9]]{6,}", password.getText())) {
            return true;
        } else {
            password.setStyle("-fx-border-color: red; -fx-border-width: 2px;-fx-prompt-text-fill : red;");
            password.clear();
            password.setPromptText("Password must be over 6 letters long");
            return false;
        }
    }

}

