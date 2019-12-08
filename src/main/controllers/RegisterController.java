package main.controllers;

import javafx.fxml.FXML;
import javafx.collections.*;
import javafx.event.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import main.tables.*;
import main.Main;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.*;

public class RegisterController extends Main {

    @FXML
    private TextField firstName;

    @FXML
    private TextField lastName;

    @FXML
    private TextField emailField;

    @FXML
    private TextField affiliation;

    @FXML
    private PasswordField passWordField;

    @FXML
    private ChoiceBox prefix;

    @FXML
    private ChoiceBox roles;

    public void initialize() {
        List<String> list = new ArrayList<String>();
        list.add("Mr.");
        list.add("Ms.");
        list.add("Dr.");
        list.add("Prof.");
        ObservableList obList = FXCollections.observableList(list);
        prefix.setItems(obList);

        List<String> rolesList = new ArrayList<String>();
        rolesList.add("Author");
        rolesList.add("Editor");
        rolesList.add("Reviewer");
        ObservableList rlList = FXCollections.observableList(rolesList);
        roles.setItems(rlList);
    }

    public void handleRegisterSuccess (ActionEvent action) throws IOException, SQLException {

        boolean t = validTitle();
        boolean r = validRole();
        boolean fn = validFirstName();
        boolean ln = validLastName();
        boolean a = validAffiliation();
        boolean email = validEmail();
        boolean pw = validPassword();


        if (t && r && fn && ln && a && email && pw) {

            UserTable.Insert((String) prefix.getValue(), firstName.getText(), lastName.getText(), affiliation.getText(), emailField.getText(), String.valueOf(passWordField.getText().hashCode()));
            loadLogin(action);

            if (roles.getValue() == "Author"){
                AuthorTable.Insert(UserTable.ValidateEmailAndPassword(emailField.getText(), String.valueOf(passWordField.getText().hashCode())), 0);
            } else if (roles.getValue() == "Editor"){
                EditorTable.Insert(UserTable.ValidateEmailAndPassword(emailField.getText(), String.valueOf(passWordField.getText().hashCode())), 0);
            } else {
                ReviewerTable.Insert(UserTable.ValidateEmailAndPassword(emailField.getText(), String.valueOf(passWordField.getText().hashCode())), 0);
            }

        }
    }


    // *************************************validation methods******************************************************
    public boolean validTitle(){
        if (prefix.getSelectionModel().isEmpty()) {
            prefix.setStyle("-fx-border-color: red; -fx-border-width: 2px;-fx-prompt-text-fill : red;");
            return false;
        } else {
            return true;
        }
    }

    public boolean validRole(){
        if (roles.getSelectionModel().isEmpty()) {
            roles.setStyle("-fx-border-color: red; -fx-border-width: 2px;-fx-prompt-text-fill : red;");
            return false;
        } else {
            return true;
        }
    }

    public boolean validFirstName(){
        // first name
        if (firstName.getText().isEmpty() || !firstName.getText().chars().allMatch(Character::isLetter)) {
            firstName.setStyle("-fx-prompt-text-fill : red;");
            firstName.clear();
            return false;
        } else {
            return true;
        }
    }

    public boolean validLastName(){
        // last name
        if (lastName.getText().isEmpty() || !lastName.getText().chars().allMatch(Character::isLetter)) {
            lastName.setStyle("-fx-prompt-text-fill : red;");
            firstName.clear();
            return false;
        } else {
            return true;
        }
    }

    public boolean validEmail(){
        //emailField
        if (Pattern.matches("[A-Za-z.]+[@][a-zA-z]+[.][A-Za-z.]+", emailField.getText())) {
            return true;
        } else {
            emailField.setStyle("-fx-prompt-text-fill : red;");
            emailField.clear();
            emailField.setPromptText("Not valid email type");
            return false;
        }
    }

    public boolean validAffiliation(){
        if (affiliation.getText().isEmpty() || !affiliation.getText().chars().allMatch(Character::isLetter)) {
            affiliation.setStyle("-fx-border-color: red; -fx-border-width: 2px;-fx-prompt-text-fill : red;");
            firstName.clear();
            return false;
        } else {
            return true;
        }
    }

    public boolean validPassword(){
        if (Pattern.matches("[a-zA-Z0-9[^\\dA-Za-zA-Za-z0-9]]{6,}", passWordField.getText())) {
            return true;
        } else {
            passWordField.setStyle("-fx-prompt-text-fill : red;");
            passWordField.clear();
            passWordField.setPromptText("Password must be over 6 letters long");
            return false;
        }
    }


    public void handleBack (ActionEvent action) throws IOException {
        loadLogin(action);
    }

    static void loadLogin(ActionEvent action) throws IOException {
        URL url = new File("src/resources/login.fxml").toURI().toURL();
        Parent view = FXMLLoader.load(url);
        Scene viewScene = new Scene(view);

        Stage window = (Stage) ((Node) action.getSource()).getScene().getWindow();
        window.setResizable(true);
        window.setScene(viewScene);
    }
}

