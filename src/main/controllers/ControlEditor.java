package main.controllers;

import com.sun.jdi.connect.IllegalConnectorArgumentsException;
import javafx.fxml.*;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.scene.control.cell.*;
import javafx.beans.value.*;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.*;

import java.sql.SQLException;
import java.util.Calendar;
import java.io.*;;
import java.net.MalformedURLException;
import java.net.*;
import javafx.collections.*;
import main.Main;
import main.TreeCellTextField;
import main.tables.*;


public class ControlEditor extends ClassLoader{

    ObservableList<Article> data;

    static String ename; // editors name for the retire Stage
    static String enameTitle;

    int [] store=new int[1];
    int [] storem = {-1};

    static ObservableList<Article> articles;

    ContextMenu meuProf = new ContextMenu();
    Menu retire = new Menu("retire");
    Menu board_of_editors = new Menu("board of editors");

    @FXML
    private Label editnames; // use to set the editorsname in the initialize method

    @FXML

    private Label editertitle; // use to set the editors title in the initialize method

    @FXML

    private TreeView<String> treeVolume;

    TreeItem<String> l = new TreeItem<>("Volumes"); // for volumes

    TableView tableView = new TableView();

    TableColumn<Article,String> article_name = new TableColumn<>("Name");
    TableColumn<Article,String> article_check = new TableColumn<>("Select");

    @FXML
    private Button delete;

    @FXML ChoiceBox journals;

    @FXML

    private VBox article;

    TreeItem one = new TreeItem("Edition1");

    TreeItem two = new TreeItem("Edition2");

    TreeItem three = new TreeItem("Edition3");

    TreeItem four = new TreeItem("Edition4");

    ContextMenu contextMenu = new ContextMenu();

    String [] n = {"vf","sf","gf","hf"};

   static String name_of_journal;

    public ControlEditor() throws MalformedURLException {
    }

    public void initialize() throws SQLException {
       // JournalTable.Insert(18361310,"Journal of Computer Science");
       //System.out.println(VolumeTable.SelectVolID(1));
        journals.getItems().addAll(JournalTable.SelectJournals());
        journals.setValue(JournalTable.SelectJournals().get(Main.vave));
        //tt();
        ObservableList<TreeItem> j = FXCollections.observableArrayList();
        article.getChildren().add(tableView);
        l.setExpanded(true);
        for (String r : VolumeTable.SelectVolumes(JournalTable.SelectISSN((String) journals.getValue()))){
         j.add(new TreeItem<String>(r));
        }

        for (TreeItem n : j){
            l.getChildren().add(n);
            ObservableList<TreeItem> a = FXCollections.observableArrayList();
          //  for (){

           // }
        }
        treeVolume.setRoot(l);
        treeVolume.setCellFactory((TreeView<String> p) -> new TreeCellTextField());
        one.setExpanded(false);
        two.setExpanded(true);
        three.setExpanded(true);
        four.setExpanded(true);
        article_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        article_name.setStyle("-fx-alignment: CENTER;");
        article_check.setCellValueFactory(new PropertyValueFactory<>("checkbox"));
        article_check.setStyle("-fx-alignment: CENTER;");
        tableView.getColumns().addAll(article_name,article_check);
        data = FXCollections.observableArrayList(getArticle());
        getArticle().removeAll(getArticle());
        tableView.setItems(data);
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
       // JournalTable.Insert(18361310,"Journal of Computer Science");
        System.out.println((JournalTable.SelectISSN("Journal of Computer Science")));
        treeVolume.refresh();
        name_of_journal = (String) journals.getValue();
    }

    // for adding treeitem
    public void addTable(ActionEvent action) throws SQLException {

        if (treeVolume.getSelectionModel().isEmpty()) {
            conditionToTable();
        }
        else
            if (selectedNode().getParent() == null){
                conditionToTable();
            }
            else if ((selectedNode().getParent().getParent()) != null){
                // do nothing
            }
            else if (selectedNode().getParent() != null){
                if (storem[0] == -1){
                    storem[0] = Calendar.getInstance().get(Calendar.MONTH);
                }
                else
                {
                    storem[0]+=1;
                }
                int volumeid = VolumeTable.SelectVolID(Integer.valueOf((String) selectedNode().getValue()));
                EditionTable.Insert(volumeid,storem[0]);
                selectedNode().getChildren().add(new TreeItem<String>(String.valueOf(storem[0])));
            }

    }

    public ObservableList<Article> getArticle(){
        articles = FXCollections.observableArrayList();
        articles.add(new Article("Chicken"));
        articles.add(new Article("Goat"));
        articles.add(new Article("Dog"));
        articles.add(new Article("Pig"));
        return articles;
    }
    // for discarding article
    public void removeArticles(ActionEvent actionEvent){
        ObservableList<Article> art = FXCollections.observableArrayList();
        for (Article t : data){
           if (t.getCheckbox().isSelected()){
               art.add(t);
           }
        }
        data.removeAll(art);
    }
    // selecting the one treeitem
    public TreeItem selectedNode(){
       return treeVolume.getSelectionModel().getSelectedItem();
    }

    //adding to the treeitem
    synchronized public void addtoE() throws InterruptedException {
        ObservableList<Article> art = FXCollections.observableArrayList();
        ObservableList<TreeItem> items = FXCollections.observableArrayList();
        for (Article t : data) {
            if (t.getCheckbox().isSelected()) {
                art.add(t);
                TreeItem y = new TreeItem(t.getName());
               // System.out.println(y.getValue());
                items.add(y);
            }
        }
        if (treeVolume.getSelectionModel().isEmpty()){

        }
        else {
            if (selectedNode().getValue().toString().contains(" "))
                treeVolume.getSelectionModel().clearSelection();
            else if (!selectedNode().getValue().toString().contains(" ")) {
                selectedNode().getChildren().addAll(items);
                data.removeAll(art);
            }
            treeVolume.getSelectionModel().clearSelection();
        }
    }

    public void addEditors(ActionEvent action) throws IOException {
        URL url = new File("src/resources/ADDEditor.fxml").toURI().toURL();
        Parent view = FXMLLoader.load(url);
        Scene viewScene = new Scene(view);
        Stage window = new Stage();
        window.setResizable(true);
        window.setScene(viewScene);
        window.initModality(Modality.APPLICATION_MODAL);
        window.show();
    }

    public void retire(ActionEvent action) throws IOException {
        ename = editnames.getText();
        enameTitle = editertitle.getText();
        URL url = new File("src/resources/RetireEditor.fxml").toURI().toURL();
        Parent view = FXMLLoader.load(url);
        Scene viewScene = new Scene(view);
        Stage window = new Stage();
        window.setResizable(true);
        window.setScene(viewScene);
        window.initModality(Modality.APPLICATION_MODAL);
        window.show();
    }

    public void deletetreepaths(ActionEvent actionEvent){
        if (treeVolume.getSelectionModel().isEmpty()){

        }
        else {
           if (selectedNode().getParent() == null){

           }
           else {
               selectedNode().getParent().getChildren().remove(selectedNode());
           }

        }
        treeVolume.refresh();
    }

    public void conditionToTable() throws SQLException {
        if (l.getChildren().isEmpty()) {
            int i = Calendar.getInstance().get(Calendar.YEAR);
            store[0] = i;
            VolumeTable.Insert(JournalTable.SelectISSN((String) journals.getValue()), i);

            TreeItem r = new TreeItem(String.valueOf(VolumeTable.SelectPublicationYear(
                    JournalTable.SelectISSN((String) journals.getValue()), i)));//

            l.getChildren().add(r);
        } else if (!l.getChildren().isEmpty()) {
            Object[] b = VolumeTable.SelectVolumes(18361310).toArray();
            store[0] = (Integer.valueOf((String) b[b.length - 1])) + 1;
            VolumeTable.Insert(JournalTable.SelectISSN((String) journals.getValue()), store[0]);

            TreeItem r = new TreeItem(String.valueOf(VolumeTable.SelectPublicationYear(
                    JournalTable.SelectISSN((String) journals.getValue()), store[0])));//

            l.getChildren().add(r);
            treeVolume.refresh();
        }
    }



}

