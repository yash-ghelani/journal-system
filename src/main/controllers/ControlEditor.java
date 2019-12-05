package main.controllers;

import javafx.beans.Observable;
import javafx.event.EventHandler;
import javafx.fxml.*;
import javafx.util.*;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.scene.control.cell.*;
import javafx.beans.value.*;
import javafx.scene.image.*;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.*;
import java.util.Calendar;
import java.io.*;;
import java.net.MalformedURLException;
import java.net.*;
import java.util.ResourceBundle;

import javafx.collections.*;
import main.TreeCellTextField;


public class ControlEditor {

    ObservableList<Article> data;

    static String ename; // editors anme for the retire Stage

    int [] store=new int[1];

    static ObservableList<Article> articles;

    ContextMenu meuProf = new ContextMenu();
    Menu retire = new Menu("retire");
    Menu board_of_editors = new Menu("board of editors");

    @FXML
    private Label editnames; // use to set the editorsname in the initialize method

    @FXML

    private Label editertitle; // use to set the editors title in the initialize method

    @FXML

    private Label journal; // use to set the journal name in the initialize method

    @FXML

    private TreeView<String> treeVolume;

    TreeItem<String> l = new TreeItem<>("Volumes");

    TableView tableView = new TableView();

    TableColumn<Article,String> article_name = new TableColumn<>("Name");
    TableColumn<Article,String> article_code = new TableColumn<>("ISSN");
    TableColumn<Article,String> article_check = new TableColumn<>("Select");

    @FXML
    private Button delete;

    @FXML Label changejournal;


    @FXML

    private VBox article;

    TreeItem one = new TreeItem("Edition1");

    TreeItem two = new TreeItem("Edition2");

    TreeItem three = new TreeItem("Edition3");

    TreeItem four = new TreeItem("Edition4");

    ContextMenu contextMenu = new ContextMenu();

    String [] n = {"vf","sf","gf","hf"};

    public ControlEditor() throws MalformedURLException {
    }

    public void initialize(){
        article.getChildren().add(tableView);
        l.setExpanded(true);
        treeVolume.setRoot(l);
        treeVolume.setCellFactory((TreeView<String> p) -> new TreeCellTextField());
        one.setExpanded(false);
        two.setExpanded(true);
        three.setExpanded(true);
        four.setExpanded(true);
        article_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        article_name.setStyle("-fx-alignment: CENTER;");
        article_check.setCellValueFactory(new PropertyValueFactory<>("checkbox"));
        article_code.setStyle("-fx-alignment: CENTER;");
        article_code.setCellValueFactory(new PropertyValueFactory<>("code"));
        article_check.setStyle("-fx-alignment: CENTER;");
        tableView.getColumns().addAll(article_name,article_code,article_check);
        data = FXCollections.observableArrayList(getArticle());
        getArticle().removeAll(getArticle());
        tableView.setItems(data);
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

    }

    // for adding treeitem
    public void addTable(ActionEvent action){
        if (l.getChildren().isEmpty()){
            int i=Calendar.getInstance().get(Calendar.YEAR);
            store [0] =i;
            TreeItem r = new TreeItem(String.valueOf(i));
            r.getChildren().addAll(one,two,three,four);
            l.getChildren().add(r);
       }
        else if (!l.getChildren().isEmpty()) {

            TreeItem r = new TreeItem(String.valueOf(store[0]+1));
            r.setExpanded(true);
            r.getChildren().addAll(one, two, three, four);
            l.getChildren().add(r);
            store[0] += 1;
            treeVolume.refresh();
        }

    }

    public ObservableList<Article> getArticle(){
        articles = FXCollections.observableArrayList();
        articles.add(new Article("Chicken","123"));
        articles.add(new Article("Goat",null));
        articles.add(new Article("Dog",null));
        articles.add(new Article("Pig",null));
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
                TreeItem y = new TreeItem(t.getName()+" "+t.getCode());
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

    public void contexter(ContextMenuEvent context){
        ObservableList<MenuItem>g=FXCollections.observableArrayList();

        for (String m:n){
            g.add(new MenuItem(m));
        }
        contextMenu.getItems().addAll(g);
        contextMenu.show(changejournal,context.getScreenX(),context.getScreenY());

    }

}

