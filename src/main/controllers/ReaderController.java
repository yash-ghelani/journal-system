package main.controllers;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.stage.Stage;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

public class ReaderController {

    @FXML
    TreeView selectionTreeView;
    @FXML
    public void handleTree(javafx.event.ActionEvent event) {
        createTree();
    }

    public void createTree(String... rootItems) {
        //create root
        TreeItem<String> root = new TreeItem<>("Journal");
        root.setExpanded(true);
        //create child
        TreeItem<String> itemChild = new TreeItem<>("Volume1");
        TreeItem<String> itemChild1 = new TreeItem<>("Volume2");
        itemChild.setExpanded(false);
        //root is the parent of itemChild
        root.getChildren().add(itemChild);
        selectionTreeView.setRoot(root);
    }

    public void handleLogOut(javafx.event.ActionEvent event) throws IOException {
        URL url = new File("src/resources/Login.fxml").toURI().toURL();
        Parent view = FXMLLoader.load(url);
        Scene viewScene = new Scene(view);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setResizable(true);
        window.setScene(viewScene);
    }



}
