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
import main.tables.AuthorTable;
import main.tables.EditorTable;
import main.Main;
import main.tables.ReviewerTable;

import java.io.File;
import java.io.IOException;
import java.net.URL;
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
        boolean validFirstName = false;
        if (firstName.getText().isEmpty()) {
            firstName.setStyle("-fx-prompt-text-fill : red;");
        } else
            l[0] = firstName.getText();
        validFirstName = true;
        // last name
        boolean validLastName = false;
        if (lastName.getText().isEmpty()) {
            lastName.setStyle("-fx-prompt-text-fill : red;");
        } else
            l[1] = lastName.getText();
        validLastName = true;
        // university
        boolean validUniversityName = false;
        if (university.getText().isEmpty()) {
            university.setStyle("-fx-prompt-text-fill : red;");
        } else
            l[2] = university.getText();
            validUniversityName = true;
        //emailField
        boolean validEmail = false;
        if (emailField.getText().isEmpty()) {
            emailField.setStyle("-fx-prompt-text-fill : red;");
            //} else if (Pattern.matches("[a-zA-Z]+[@][a-zA-z]+[.][A-Za-z.]+", emailField.getText())) {
        } else if (Pattern.matches("[A-Za-z.]+[@][a-zA-z]+[.][A-Za-z.]+", emailField.getText())) {
            l[3] = emailField.getText();
            validEmail = true;
        }
        //else if (!Pattern.matches("[a-zA-Z]+[@][a-zA-z]+[.][A-Za-z.]+",emailField.getText())){
        else if (!Pattern.matches("[A-Za-z.]+[@][a-zA-z]+[.][A-Za-z.]+", emailField.getText())) {
            emailField.setStyle("-fx-prompt-text-fill : red;");
            emailField.clear();
            emailField.setPromptText("not valid email type");

        }
        // password
        boolean validPassword = false;
        if (passWordField.getText().isEmpty()) {
            passWordField.setStyle("-fx-prompt-text-fill : red;");
        } else if (Pattern.matches("[a-zA-Z0-9[^\\dA-Za-zA-Za-z0-9]]{6,}", passWordField.getText())) {
            l[4] = passWordField.getText();
            validPassword = true;
            // System.out.println("N");
        } else if (!Pattern.matches("[a-zA-Z0-9[^\\dA-Za-zA-Za-z0-9]]{6,}", passWordField.getText())) {
            passWordField.setStyle("-fx-prompt-text-fill : red;");
            passWordField.clear();
            passWordField.setPromptText("must be 6 letters and above");
        }

        String prefixValue = (String) prefix.getValue();
        String roleValue = (String) roles.getValue();

        if (prefix != null && roles != null && firstName != null && lastName != null && university != null && emailField != null && passWordField != null
        && validFirstName && validLastName && validUniversityName && validEmail && validPassword){

            if (roleValue == "Author") {
                try {
                    AuthorTable.Insert(prefixValue, firstName.getText(), lastName.getText(), university.getText(), emailField.getText(), Integer.toString(passWordField.getText().hashCode()),false);
                    //AuthorTable.Insert(prefixValue, l[0],l[1],l[2],l[3],l[4]);
                    URL url = new File("src/resources/login.fxml").toURI().toURL();
                    Parent view = FXMLLoader.load(url);
                    Scene viewScene = new Scene(view);

                    Stage window = (Stage) ((Node) action.getSource()).getScene().getWindow();
                    window.setResizable(true);
                    window.setScene(viewScene);

                } catch (SQLException e) {
                    System.err.println(e.getClass().getName() + ": " + e.getMessage());
                    System.out.println("Selection failed");
                }

            } else if (roleValue == "Editor") {
                try {
                    EditorTable.Insert(prefixValue, firstName.getText(), lastName.getText(), university.getText(), emailField.getText(), Integer.toString(passWordField.getText().hashCode()),false);

                    URL url = new File("src/resources/login.fxml").toURI().toURL();
                    Parent view = FXMLLoader.load(url);
                    Scene viewScene = new Scene(view);

                    Stage window = (Stage) ((Node) action.getSource()).getScene().getWindow();
                    window.setResizable(true);
                    window.setScene(viewScene);
                } catch (SQLException e) {
                    System.err.println(e.getClass().getName() + ": " + e.getMessage());
                    System.out.println("Selection failed");
                }
            } else if (roleValue == "Reviewer") {
                ReviewerTable.Insert(prefixValue, firstName.getText(), lastName.getText(), university.getText(), emailField.getText(), Integer.toString(passWordField.getText().hashCode()),false);

                URL url = new File("src/resources/login.fxml").toURI().toURL();
                Parent view = FXMLLoader.load(url);
                Scene viewScene = new Scene(view);

                Stage window = (Stage) ((Node) action.getSource()).getScene().getWindow();
                window.setResizable(true);
                window.setScene(viewScene);

            } else {
                System.out.println("Not all fields filled in");
            }
        }
    }
}
