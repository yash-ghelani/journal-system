package main.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class FinalVerdictController {

    @FXML
    private TextField reviewSummary;

    @FXML
    private ChoiceBox verdictChoiceBox;

    public void initialize() {
        List<String> list = new ArrayList<String>();
        list.add("Champion");
        list.add("Detractor");
        ObservableList obList = FXCollections.observableList(list);
        verdictChoiceBox.setItems(obList);

    }

    public void handleSubmit(ActionEvent action) throws IOException{
        String rs = reviewSummary.getText();
    }

    public void handleLogOut(ActionEvent actionEvent) throws IOException {
        URL url = new File("src/resources/Login.fxml").toURI().toURL();
        Parent view = FXMLLoader.load(url);
        Scene viewScene = new Scene(view);
        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(viewScene);
    }

    public void handleCancel(ActionEvent actionEvent) throws IOException{
        URL url = new File("src/resources/ReviewPanel.fxml").toURI().toURL();
        Parent view = FXMLLoader.load(url);
        Scene viewScene = new Scene(view);
        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(viewScene);
    }
}
