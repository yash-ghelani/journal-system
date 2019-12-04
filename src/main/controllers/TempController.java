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

public class TempController extends Main {

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

    String[] sql = {"Drop"};

    public void initialize() {
        List<String> list = new ArrayList<String>();
        list.add("Mr.");
        list.add("Ms.");
        list.add("Dr.");
        list.add("Prof.");
        ObservableList obList = FXCollections.observableList(list);
        prefix.setItems(obList);

    }

    public void handleUpdate(ActionEvent action) throws IOException, SQLException {
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

        String [] roleValue = getRole(Main.IDs);

        if (prefix != null && firstName != null && lastName != null && university != null && emailField != null && passWordField != null
                && validFirstName && validLastName && validUniversityName && validEmail && validPassword){

            if (roleValue[0] == "Author") {
                try {

                    AuthorTable.UpdateTitle(Integer.valueOf(roleValue[1]),prefixValue);
                    AuthorTable.UpdateName(Integer.valueOf(roleValue[1]), firstName.getText());
                    AuthorTable.UpdateSurname(Integer.valueOf(roleValue[1]),lastName.getText());
                    AuthorTable.UpdateAffiliation(Integer.valueOf(roleValue[1]),university.getText());
                    AuthorTable.UpdateEmail(Integer.valueOf(roleValue[1]),emailField.getText());
                    AuthorTable.UpdatePassword(Integer.valueOf(roleValue[1]),Integer.toString(passWordField.getText().hashCode()));
                    AuthorTable.UpdateTemp(Integer.valueOf(roleValue[1]),false);

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

            } else if (roleValue[0] == "Editor") {
                try {

                    EditorTable.UpdateTitle(Integer.valueOf(roleValue[1]),prefixValue);
                    EditorTable.UpdateName(Integer.valueOf(roleValue[1]), firstName.getText());
                    EditorTable.UpdateSurname(Integer.valueOf(roleValue[1]),lastName.getText());
                    EditorTable.UpdateAffiliation(Integer.valueOf(roleValue[1]),university.getText());
                    EditorTable.UpdateEmail(Integer.valueOf(roleValue[1]),emailField.getText());
                    EditorTable.UpdatePassword(Integer.valueOf(roleValue[1]),Integer.toString(passWordField.getText().hashCode()));
                    EditorTable.UpdateTemp(Integer.valueOf(roleValue[1]),false);

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
            } else if (roleValue[0] == "Reviewer") {

                try {
                    ReviewerTable.UpdateTitle(Integer.valueOf(roleValue[1]),prefixValue);
                    ReviewerTable.UpdateName(Integer.valueOf(roleValue[1]), firstName.getText());
                    ReviewerTable.UpdateSurname(Integer.valueOf(roleValue[1]),lastName.getText());
                    ReviewerTable.UpdateAffiliation(Integer.valueOf(roleValue[1]),university.getText());
                    ReviewerTable.UpdateEmail(Integer.valueOf(roleValue[1]),emailField.getText());
                    ReviewerTable.UpdatePassword(Integer.valueOf(roleValue[1]),Integer.toString(passWordField.getText().hashCode()));
                    ReviewerTable.UpdateTemp(Integer.valueOf(roleValue[1]),false);

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

            } else {
                System.out.println("Not all fields filled in");
            }
        }
    }

    public String[] getRole(int [] IDs){

        if (IDs[0] == 0 && IDs[1] == 0 && IDs[2] != 0){
            return new String[] {"Reviewer", String.valueOf(IDs[2])};
        } else if (IDs[0] == 0 && IDs[1] != 0 && IDs[2] == 0) {
            return new String[] {"Editor", String.valueOf(IDs[1])};
        } else {
            return new String[] {"Author", String.valueOf(IDs[0])};
        }
    }
}
