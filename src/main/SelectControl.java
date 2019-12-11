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
import java.util.HashSet;
import java.util.Set;

public class SelectControl {
    static int issn;
    static String name;
    static Stage closer;
    ObservableList<String> zv = FXCollections.observableArrayList();
    ArrayList<Integer>av=new ArrayList<>();
    ArrayList<Integer>jv=new ArrayList<>();
    @FXML

    private ListView vthings;

    public void initialize() throws SQLException {
        zv.clear();
        for (Integer k :JournalInfoTable.SelectAllE()){
            av.addAll(JournalInfoTable.SelectISSNFromEditor(k));
        }
        jv.addAll(JournalTable.SelectISSNs());
        Set<Integer> set =new HashSet<>(av);

        Set<Integer> setall = new HashSet<>(jv);
        setall.removeAll(set);
        av.clear();
        av.addAll(setall);
        for (Integer n : av){
            zv.add(JournalTable.SelectName(n));
        }
        vthings.getItems().setAll(zv);
    }




    public void selected(MouseEvent mouseEvent) throws SQLException,IOException {
        URL url = null;
        url = new File("src/main/Editor.fxml").toURI().toURL();
        Parent view = null;
        view = FXMLLoader.load(url);
        if (vthings.getSelectionModel().isEmpty()){
            closer.close();
            Scene viewScene = new Scene(view);
            Stage window = new Stage();
            window.setResizable(true);
            window.setScene(viewScene);
            main.SelectControl.closer = window;
            window.show();
        }
        else {
            closer.close();
            String journalSel = (String) vthings.getSelectionModel().getSelectedItems().get(0);
            System.out.println(journalSel);
            String[] vf = name.split(" ");
            int kl = UserTable.GetID(vf[0], vf[1]);
            int lk = EditorTable.GetID(kl);
            issn = JournalTable.SelectISSN(journalSel);
            JournalInfoTable.Insert(issn, lk, "Chief Editor");
            Scene viewScene = new Scene(view);
            Stage window = new Stage();
            window.setResizable(true);
            window.setScene(viewScene);
            main.SelectControl.closer = window;
            window.show();
        }
    }

}
