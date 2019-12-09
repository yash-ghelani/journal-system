package main.controllers;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;

import javafx.scene.control.*;
import javafx.event.*;
import main.tables.*;

import java.sql.SQLException;
import java.util.regex.Pattern;

public class AddEditor {

    String[] editorn_p = {null,null,null,null};

    @FXML

    private TextField textName;

    @FXML

    private PasswordField passField;

    @FXML

    private TextField emailField;

    @FXML

    private ChoiceBox choice;

    @FXML

    private TextField affiliate;

    String [] str = {"Mr.","Ms.","Dr.","Prof."};

   public void initialize(){
       choice.setValue("Mr.");
       choice.getItems().addAll(FXCollections.observableArrayList(str));
      //System.out.println();
   }



    public void actionAdd(ActionEvent actionEvent) throws NullPointerException, SQLException {

        if (textName.getText().isEmpty()) {
            textName.setStyle("-fx-prompt-text-fill:red;");

        } else if (!textName.getText().isEmpty()) {
            editorn_p[0] = textName.getText();
            textName.clear();
        }

        if (passField.getText().isEmpty()) {
            passField.clear();
            passField.setStyle("-fx-prompt-text-fill:red;");
        } else {
            editorn_p[1] = passField.getText();
            passField.clear();
        }

        if (emailField.getText().isEmpty()){
            emailField.setStyle("-fx-prompt-text-fill:red;");
        }
        else if (!Pattern.matches("[A-za-z]+[@]{1}[a-zA-Z]+[.]{1}[A-za-z.]+",emailField.getText())){
            emailField.clear();
            emailField.setPromptText("invalid email structure");
            emailField.setStyle("-fx-prompt-text-fill:red;");
        }
        else if (Pattern.matches("[A-za-z]+[@]{1}[a-zA-Z]+[.]{1}[A-za-z.]+",emailField.getText())){
            editorn_p[2] = emailField.getText();
            emailField.clear();
        }

        if (affiliate.getText().isEmpty()){
            affiliate.setStyle("-fx-prompt-text-fill:red;");
        }
        else {
            editorn_p[3] = affiliate.getText();
            affiliate.clear();
        }

        if (editorn_p[0] != null && editorn_p[1] != null && editorn_p[2] != null ){
            String [] namespilt = editorn_p[0].split(" ");
            System.out.println(namespilt[0]);
            String l[] = new String[8];
            l[0] =(String)choice.getValue();
            l[1] = namespilt[0];
            l[2] = namespilt[1];
            l[3] = affiliate.getText();
            l[4] = emailField.getText();
            l[5] = passField.getText();

            if (JournalInfoTable.SelectEditorID(JournalTable.SelectISSN(ControlEditor.name_of_journal)).length < 5) {

                UserTable.Insert(l[0], l[1], l[2], l[3], l[4], l[5]);

                EditorTable.Insert(UserTable.GetID(l[1], l[2], l[4]), 1);

                JournalInfoTable.Insert(JournalTable.SelectISSN(
                        ControlEditor.name_of_journal), EditorTable.GetID(UserTable.GetID(l[1], l[2], l[4])), "Editor");
            }
            else {
                //nothing
            }

        }
        else {
        }


   }

}
