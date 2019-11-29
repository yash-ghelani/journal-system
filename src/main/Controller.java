package main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class Controller {

   // public Button button;

    String [] kin = new String [2];

    @FXML

    private javafx.scene.control.TextField login;

    @FXML
    private PasswordField password;


    public void handleRegister(javafx.event.ActionEvent event) throws IOException {
        URL url = new File("src/resources/Register.fxml").toURI().toURL();
        Parent view = FXMLLoader.load(url);
        Scene viewScene = new Scene(view);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(viewScene);
    }

    public void handleRegisterSuccess(javafx.event.ActionEvent event) throws IOException {
        URL url = new File("src/resources/RegisterSuccess.fxml").toURI().toURL();
        Parent view = FXMLLoader.load(url);
        Scene viewScene = new Scene(view);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(viewScene);
    }

    public void handleReader(javafx.event.ActionEvent event) throws IOException {
        URL url = new File("src/resources/Reader.fxml").toURI().toURL();
        Parent view = FXMLLoader.load(url);
        Scene viewScene = new Scene(view);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setResizable(true);
        window.setScene(viewScene);
    }

    public void signedin(ActionEvent action) throws IOException {

        kin[0]= "king";

        kin[1] = "Good12";

        if (!login.getText().equals(kin[0])) {
            login.clear();
            login.setStyle("-fx-prompt-text-fill :red");
            login.setPromptText("invalid username");
        }


        if (!password.getText().equals(kin[1])) {
            password.clear();
            password.setStyle("-fx-prompt-text-fill :red");
            password.setPromptText("invalid password");
        }

        if (login.getText().equals(kin[0]) && password.getText().equals(kin[1])){
            URL url = new File("src/resources/sample.fxml").toURI().toURL();
            Parent view = FXMLLoader.load(url);
            Scene viewScene = new Scene(view);

            Stage window = (Stage)((Node)action.getSource()).getScene().getWindow();
            window.setResizable(true);
            window.setScene(viewScene);
        }


    }
}
