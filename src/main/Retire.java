package main;

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
import main.tables.EditorTable;
import main.tables.JournalInfoTable;
import main.tables.JournalTable;
import main.tables.UserTable;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class Retire extends ClassLoader {

    static ObservableList<EditorP> k = FXCollections.observableArrayList();
    ObservableList<EditorP> m = FXCollections.observableArrayList();

    @FXML

    private TableView<EditorP> tableT;

    TableColumn<EditorP, String> editors = new TableColumn("editors");
    TableColumn<EditorP, String> editorsTitle = new TableColumn("title");


    public void initialize() throws SQLException {
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
        add_to_board();
    }


    public EditorP selectedNode() {
        return tableT.getSelectionModel().getSelectedItem();
    }

    public void retired(ActionEvent actionEvent) throws ClassNotFoundException, IOException, SQLException {
        ObservableList<EditorP> p = FXCollections.observableArrayList();
        ObservableList<EditorP> d = FXCollections.observableArrayList();

        if (!tableT.getSelectionModel().getSelectedItem().getName().equals(ControlEditor.ename)){
            tableT.getSelectionModel().clearSelection();
        }
        else {
            String [] nameofedit = selectedNode().getName().split(" ");
            String [] splitchiefname = ControlEditor.ename.split(" ");
            int a = EditorTable.GetID(UserTable.GetID(nameofedit[0],nameofedit[1]));
            if (ControlEditor.enameTitle == "Chief Editor"){
                int sob =JournalInfoTable.SelectEditorID(JournalTable.SelectISSN(ControlEditor.name_of_journal)).length;
                if (sob>1){
                    JournalInfoTable.UpdateType(a,"Chief Editor");
                }
                else if (ControlEditor.enameTitle == "Editor"){
                    tableT.getSelectionModel().clearSelection();
                }

            }
            JournalInfoTable.Delete(a);
            EditorTable.Delete(a);
            UserTable.DeleteUser(splitchiefname[0],splitchiefname[1]);
            k.removeAll(p);
            tableT.getSelectionModel().clearSelection();
            tableT.refresh();
            URL url = new File("src/resources/Login.fxml").toURI().toURL();
            Parent view = FXMLLoader.load(url);
            Scene viewScene = new Scene(view);
            Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            window.setResizable(true);
            window.setScene(viewScene);
        }

    }

    public void add_to_board() throws SQLException {
        tableT.getItems().clear();
       int [] u = JournalInfoTable.SelectEditorID(JournalTable.SelectISSN(ControlEditor.name_of_journal));
      //if (u)
       for (int y :u){
         String l = UserTable.SelectName(EditorTable.SelectUserID(y));
         String h = UserTable.SelectSurName(EditorTable.SelectUserID(y));
         k.add(new EditorP(l+" "+h,JournalInfoTable.SelectEditorType(y)));
      }
      tableT.refresh();
    }

}


