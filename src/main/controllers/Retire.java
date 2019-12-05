package main.controllers;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeItem;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.File;
import java.net.*;

import javafx.event.*;
import javafx.stage.Stage;
import main.JournalTable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Retire extends ClassLoader {

    static ObservableList<EditorP> k = FXCollections.observableArrayList();
    ObservableList<EditorP> m = FXCollections.observableArrayList();

    @FXML

    private TableView<EditorP> tableT;

    TableColumn<EditorP, String> editors = new TableColumn("editors");
    TableColumn<EditorP, String> editorsTitle = new TableColumn("title");


    public void initialize() {
        editors.setCellValueFactory(new PropertyValueFactory<>("name"));
        editors.setStyle("-fx-alignment: CENTER;");
        editorsTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        editorsTitle.setStyle("-fx-alignment: CENTER;");
       // System.out.println(k.get(0).getTitle());
        tableT.getColumns().addAll(editors, editorsTitle);
        k.addAll(m);
        m.clear();
        tableT.setItems(k);
        tableT.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }


    public EditorP selectedNode() {
        return tableT.getSelectionModel().getSelectedItem();
    }

    public void retired(ActionEvent actionEvent) throws ClassNotFoundException, IOException {
        ObservableList<EditorP> p = FXCollections.observableArrayList();
        ObservableList<EditorP> d = FXCollections.observableArrayList();

        if (!tableT.getSelectionModel().getSelectedItem().getName().equals(ControlEditor.ename)){
            tableT.getSelectionModel().clearSelection();
        }
        else {

            for (EditorP g : k) {
                if (g.equals(selectedNode())) {
                    if (selectedNode().getTitle().equals("Chief Editor")) {
                        g.setTitle("Chief Editor");
                        m.add(g);
                    } else {
                        d.addAll(g);
                    }
                }
            }
        }
        k.removeAll(p);
        k.removeAll(d);
        tableT.getSelectionModel().clearSelection();
        tableT.refresh();
    }
}


