package main;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import main.tables.ArticleTable;
import main.tables.EditionTable;
import main.tables.JournalTable;
import main.tables.VolumeTable;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;

public class ReaderController {

    @FXML
    public TextArea abstractArea;
    public Label editorLab;
    public Label authorLab;
    public Text titleLab;
    public TextField pdfLink;

    @FXML
    TreeView selectionTreeView;

    public void initialize() throws SQLException {
        createTree();
    }

    public void createTree(String... rootItems) throws SQLException {
        //create root
        TreeItem<String> root = new TreeItem<>("Journals");
        root.setExpanded(true);
        //create child
        //get list of journals
        ArrayList<String> journalList = JournalTable.SelectJournals();

        // add journals to tree
        for (int i = 0; i < journalList.size(); i++){
            TreeItem<String> journals = new TreeItem<>(journalList.get(i));
            journals.setExpanded(true);
            root.getChildren().add(journals);
            //get volumes (years) of that journal using journal name to get issn, then using issn to get publication year
            ArrayList<String> volumeList = VolumeTable.SelectVolumes(JournalTable.SelectISSN((journalList.get(i))));

            //add volumes to tree
            for (int j = 0; j < volumeList.size(); j++) {
                TreeItem<String> volumes = new TreeItem<>(volumeList.get(j));
                volumes.setExpanded(true);
                journals.getChildren().add(volumes);
                //**************************************need to update column in editions table to store year instead of vol id**************************
                //get editions of that volume using volume year
                int volID = VolumeTable.SelectVolID(Integer.valueOf(volumeList.get(j)));
                ArrayList<String> editionsList = EditionTable.selectEditions(volID);

                System.out.println("Vol list "+volumeList);
                System.out.println("ed list "+editionsList);

                //add volumes to tree
                for (int l = 0; l < editionsList.size(); l++) {
                    TreeItem<String> editions = new TreeItem<>(editionsList.get(l));
                    editions.setExpanded(true);
                    volumes.getChildren().add(editions);
                    //get articles of that edition using month
                    //ArrayList<String> articleList = ArticleTable.SelectTitles(EditionTable.SelectID(volID, editionsList.get(l)));
                    System.out.println(editionsList);

                }

            }

        }

        selectionTreeView.setRoot(root);
    }

    public void handleReadArticle (javafx.event.ActionEvent event) throws IOException, SQLException {
        int articleid = 1;
        titleLab.setText(ArticleTable.SelectTitle(articleid));
        abstractArea.setText(ArticleTable.SelectAbstract(articleid));
        pdfLink.setText(ArticleTable.SelectPDF(articleid));
    }

    public void handleLogOut(javafx.event.ActionEvent event) throws IOException {
        Parent view = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Scene viewScene = new Scene(view);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setResizable(true);
        window.setScene(viewScene);
    }
}
