package main.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javafx.event.*;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class InitialFinalViewerController {

    public void handleInitialViewer(ActionEvent action) throws IOException{
        URL url = new File("src/resources/Respond.fxml").toURI().toURL();
        Parent view = FXMLLoader.load(url);
        Scene viewScene = new Scene(view);

        Stage window = (Stage)((Node)action.getSource()).getScene().getWindow();
        window.setResizable(true);
        window.setScene(viewScene);
    }

    public void handleFinalViewer(ActionEvent action) throws IOException{
        URL url = new File("src/resources/Respond.fxml").toURI().toURL();
        Parent view = FXMLLoader.load(url);
        Scene viewScene = new Scene(view);

        Stage window = (Stage)((Node)action.getSource()).getScene().getWindow();
        window.setResizable(true);
        window.setScene(viewScene);
    }

    public void handleInitialFinalCancel(ActionEvent action) throws IOException{
        URL url = new File("src/resources/AuthorPanel.fxml").toURI().toURL();
        Parent view = FXMLLoader.load(url);
        Scene viewScene = new Scene(view);

        Stage window = (Stage)((Node)action.getSource()).getScene().getWindow();
        window.setResizable(true);
        window.setScene(viewScene);
    }
}
