package main;

import com.sun.media.jfxmediaimpl.platform.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import main.tables.*;
import main.tables.JournalInfoTable;
import main.tables.JournalTable;
import main.tables.UserTable;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;

public class SelectControl {
    static int issn;
    static String name;
    static Stage closer;
    ObservableList<String> zv = FXCollections.observableArrayList();
    @FXML

    private ListView vthings;

    public void initialize() throws SQLException {
        zv.clear();
        vthings.getItems().clear();
        ArrayList<Integer> l1 = JournalInfoTable.CheckList(Main.IDs[1]);
        for (Integer v : l1){
            //int issn = JournalTable.SelectISSN(v);
            //int editorid = JournalInfoTable.CheckList(issn);
            System.out.println(JournalTable.ShowName(v));
        }
//            String [] hf = name.split(" ");
//            ArrayList<Integer> isss = JournalInfoTable.SelectISSNFromEditor
//                    (EditorTable.GetID(UserTable.GetID(hf[0],hf[1])));
//            if (isss.isEmpty()){
//                zv.add(v);
//            }
//            else {
//                for (Integer f : isss) {
//                    System.out.println(JournalTable.SelectName(f) + " " + v);
//                    }
//                }
//            }

        }
   // vthings.getItems().setAll(zv);



    public void selected(MouseEvent mouseEvent) throws SQLException,IOException {
        String journalSel= (String) vthings.getSelectionModel().getSelectedItems().get(0);
        System.out.println(journalSel);
        String [] vf = name.split(" ");
        int kl =  UserTable.GetID(vf[0],vf[1]);
        int lk = EditorTable.GetID(kl);
        issn = JournalTable.SelectISSN(journalSel);
        JournalInfoTable.Insert(issn,lk,"Chief Editor");

        URL url = null;
        url = new File("src/main/Editor.fxml").toURI().toURL();
        Parent view = null;
        view = FXMLLoader.load(url);
        Scene viewScene = new Scene(view);
        Stage window = new Stage();
        window.setResizable(true);
        window.setScene(viewScene);
        main.SelectControl.closer = window;
        window.show();
        closer.close();
    }

}
