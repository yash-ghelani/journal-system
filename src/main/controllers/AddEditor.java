package main.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;

import javafx.scene.control.*;
import javafx.event.*;

public class AddEditor {

    String [] editorn_p = {"@£$%N","@£$%N"};
    @FXML

    private TextField textName;

    @FXML

    private PasswordField passField;

    public void actionAdd(ActionEvent actionEvent) throws NullPointerException{

        if (textName.getText().isEmpty()){
            textName.setStyle("-fx-prompt-text-fill:red;");

        }
        else if(!textName.getText().isEmpty()) {
            editorn_p[0] = textName.getText();

        }

        if (passField.getText().isEmpty()){
            passField.setStyle("-fx-prompt-text-fill:red;");
        }
        else {
           editorn_p[1] = passField.getText();
        }

        if (!editorn_p[0].equals("@£$%N") && !editorn_p[1].equals("@£$%N")) {
            if (Retire.k.size()<4)
            Retire.k.add(new EditorP(editorn_p[0], "Editor"));
            else {}

        }
        else   {
           //nothing
        }
   }

}
