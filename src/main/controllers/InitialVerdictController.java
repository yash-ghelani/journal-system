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

public class InitialVerdictController {

    @FXML
    private TextField typoError;
    @FXML
    private TextField criticism;
    @FXML
    private VBox vBox;
    @FXML
    private VBox vBoxCriticism;



    public void handleTypoError(ActionEvent event) throws IOException {
        Text text = new Text();
        text.setText(typoError.getText());
        vBox.getChildren().add(text);
    }
    public void handleAddCriticism(ActionEvent event) throws IOException {
        Text text = new Text();
        text.setText(criticism.getText());
        vBoxCriticism.getChildren().add(text);
    }

    public void handleCancel(ActionEvent event) throws IOException {
        URL url = new File("src/resources/ReviewPanel.fxml").toURI().toURL();
        Parent view = FXMLLoader.load(url);
        Scene viewScene = new Scene(view);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(viewScene);
    }

}
