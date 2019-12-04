package main.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;

import javafx.scene.control.*;
import javafx.event.*;

public class AddEditor {

    static String [] editorn_p= new String[2];
    @FXML

    private TextField textName;

    @FXML

    private PasswordField passField;

    public void actionAdd(ActionEvent actionEvent){
        if (textName.getText().toString().equals(null)||passField.getText().equals(null)){

        }
        else {
            editorn_p[0] = textName.getText();
            editorn_p[1]= passField.getText();
        }
        Retire.k.add(new EditorP (editorn_p[0],"Editor"));
   }

}
