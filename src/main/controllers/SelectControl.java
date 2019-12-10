package main.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import main.tables.EditorTable;
import main.tables.JournalInfoTable;
import main.tables.JournalTable;
import main.tables.UserTable;

import java.sql.SQLException;

public class SelectControl {
    static int issn;
    static String name;
    static Stage closer;
    ObservableList<String> zv = FXCollections.observableArrayList();
    @FXML

    private ListView vthings;

    public void initialize() throws SQLException {

        for (String v : JournalTable.selectJournals()){
            zv.add(v);
        }
        vthings.getItems().setAll(zv);

    }


    public void selected(MouseEvent mouseEvent) throws SQLException {
     String journalSel= (String) vthings.getSelectionModel().getSelectedItems().get(0);
     System.out.println(journalSel);
     String [] vf = name.split(" ");
      int kl =  UserTable.GetID(vf[0],vf[1]);
      int lk = EditorTable.GetID(kl);
     //JournalInfoTable.Insert(issn,lk,"Chief Editor");
     //closer.close();
    }
}
