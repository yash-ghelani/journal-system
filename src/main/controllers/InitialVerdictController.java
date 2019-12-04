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
import main.Main;
import main.tables.ReviewerTable;
import main.tables.ReviewTable;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class InitialVerdictController {

    @FXML
    private TextField typoError;
    @FXML
    private TextField criticism;
    @FXML
    private VBox vBox;
    @FXML
    private VBox vBoxCriticism;

    @FXML
    private TextField reviewSummary;
    @FXML
    private ComboBox finalVerdict;

    private List<String> crits = new ArrayList<String>(); //store criticisms
    private List<String> errors = new ArrayList<String>(); // store errors

    public void initialize() {
        List<String> list = new ArrayList<String>();
        list.add("Champion");
        list.add("Detractor");
        ObservableList obList = FXCollections.observableList(list);
        finalVerdict.setItems(obList);

    }


    public void handleTypoError(ActionEvent event) throws IOException {
        Text text = new Text();
        text.setText(typoError.getText());
        vBox.getChildren().add(text);

        errors.add(typoError.getText());

        typoError.clear();
    }


    public void handleAddCriticism(ActionEvent event) throws IOException {
        Text text = new Text();
        text.setText(criticism.getText());
        vBoxCriticism.getChildren().add(text);

        crits.add(criticism.getText());

        criticism.clear();
    }

    public void handleCancel(ActionEvent event) throws IOException {
        URL url = new File("src/resources/ReviewPanel.fxml").toURI().toURL();
        Parent view = FXMLLoader.load(url);
        Scene viewScene = new Scene(view);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(viewScene);
    }

    public void handleSubmit(ActionEvent actionEvent) {
        int dummySubmissionInfo = 1234;
        boolean verdict;
        String summary = reviewSummary.getText();
//        if (finalVerdict.getValue() == null) {
//            System.out.println(finalVerdict.getValue());
//        }

        //ReviewTable.Insert(Main.IDs[2], dummySubmissionInfo, summary, verdict);

        //System.out.println(crits);
        //System.out.println(errors);
    }
}
