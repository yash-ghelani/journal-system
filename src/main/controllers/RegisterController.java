package main.controllers;

import javafx.fxml.FXML;
import javafx.collections.*;
import javafx.event.*;
import javafx.scene.control.*;
import main.tables.AuthorTable;
import main.tables.EditorTable;
import main.Main;
import main.tables.ReviewerTable;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.*;

public class RegisterController extends Main {

    String[] l = new String[5];

    @FXML
    private TextField firstName;

    @FXML
    private TextField lastName;

    @FXML
    private TextField university;

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passWordField;

    @FXML
    private ChoiceBox prefix;

    @FXML
    private ChoiceBox roles;

    String[] sql = {"Drop"};

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

    public void handleRegisterSuccess(ActionEvent action) throws IOException, SQLException {
        // first name
        if (firstName.getText().isEmpty()) {
            firstName.setStyle("-fx-prompt-text-fill : red;");
        } else
            l[0] = firstName.getText();
        // last name
        if (lastName.getText().isEmpty()) {
            lastName.setStyle("-fx-prompt-text-fill : red;");
        } else
            l[1] = lastName.getText();
        // university
        if (university.getText().isEmpty()) {
            university.setStyle("-fx-prompt-text-fill : red;");
        } else
            l[2] = university.getText();

        //emailField
        if (emailField.getText().isEmpty()) {
            emailField.setStyle("-fx-prompt-text-fill : red;");
        } else if (Pattern.matches("[a-zA-Z]+[@][a-zA-z]+[.][A-Za-z.]+", emailField.getText())) {
            l[3] = emailField.getText();
        }
        //else if (!Pattern.matches("[a-zA-Z]+[@][a-zA-z]+[.][A-Za-z.]+",emailField.getText())){
        else if (!Pattern.matches("[A-Za-z.]+[@][a-zA-z]+[.][A-Za-z.]+", emailField.getText())) {
            emailField.setStyle("-fx-prompt-text-fill : red;");
            emailField.clear();
            emailField.setPromptText("not valid email type");
        }
        // password
        if (passWordField.getText().isEmpty()) {
            passWordField.setStyle("-fx-prompt-text-fill : red;");
        } else if (Pattern.matches("[a-zA-Z0-9[^\\dA-Za-zA-Za-z0-9]]{6,}", passWordField.getText())) {
            l[4] = passWordField.getText();
            // System.out.println("N");
        } else if (!Pattern.matches("[a-zA-Z0-9[^\\dA-Za-zA-Za-z0-9]]{6,}", passWordField.getText())) {
            passWordField.setStyle("-fx-prompt-text-fill : red;");
            passWordField.clear();
            passWordField.setPromptText("must be 6 letters and above");
        }

        String prefixValue = (String) prefix.getValue();
        String roleValue = (String) roles.getValue();

        if (prefix != null && roles != null && firstName != null && lastName != null && university != null && emailField != null && passWordField != null){

            if (roleValue == "Author") {
                try {
                    AuthorTable.Insert(prefixValue, firstName.getText(), lastName.getText(), university.getText(), emailField.getText(), Integer.toString(passWordField.getText().hashCode()));

                    handleRegisterSuccess(action);
                } catch (SQLException e) {
                    System.err.println(e.getClass().getName() + ": " + e.getMessage());
                    System.out.println("Selection failed");
                }

            } else if (roleValue == "Editor") {
                try {
                    EditorTable.Insert(prefixValue, firstName.getText(), lastName.getText(), university.getText(), emailField.getText(), Integer.toString(passWordField.getText().hashCode()));

                    handleRegisterSuccess(action);
                } catch (SQLException e) {
                    System.err.println(e.getClass().getName() + ": " + e.getMessage());
                    System.out.println("Selection failed");
                }
            } else if (roleValue == "Reviewer") {
                ReviewerTable.Insert(prefixValue, firstName.getText(), lastName.getText(), university.getText(), emailField.getText(), Integer.toString(passWordField.getText().hashCode()));

                handleRegisterSuccess(action);

            } else {
                System.out.println("Not all fields filled in");
            }
        }
    }
}
