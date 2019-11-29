package main;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import javafx.event.*;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class Controller {

    public Button button;
    @FXML
    private TextField loginID;


    public void handleRegister(ActionEvent event) throws IOException {
        URL url = new File("src/resources/Register.fxml").toURI().toURL();
        Parent view = FXMLLoader.load(url);
        Scene viewScene = new Scene(view);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(viewScene);
    }

    public void handleRegisterSuccess(ActionEvent event) throws IOException {
        URL url = new File("src/resources/RegisterSuccess.fxml").toURI().toURL();
        Parent view = FXMLLoader.load(url);
        Scene viewScene = new Scene(view);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(viewScene);
    }

    public void handleReader(ActionEvent event) throws IOException {
        URL url = new File("src/resources/Reader.fxml").toURI().toURL();
        Parent view = FXMLLoader.load(url);
        Scene viewScene = new Scene(view);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setResizable(true);
        window.setScene(viewScene);
    }

    public void handleSignIn(ActionEvent event) throws IOException {

        if (loginID.getText().equals("reviewer")){
            URL url = new File("src/resources/ReviewPanel.fxml").toURI().toURL();
            Parent view = FXMLLoader.load(url);
            Scene viewScene = new Scene(view);

            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setResizable(true);
            window.setScene(viewScene);
        }

        else if (loginID.getText().equals("author")){
            URL url = new File("src/resources/AuthorPanel.fxml").toURI().toURL();
            Parent view = FXMLLoader.load(url);
            Scene viewScene = new Scene(view);

            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setResizable(true);
            window.setScene(viewScene);
        }
    }

}
