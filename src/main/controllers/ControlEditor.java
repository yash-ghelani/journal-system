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



public class ControlEditor {

    ObservableList<Article> data;

    int [] store=new int[1];

    ContextMenu meuProf = new ContextMenu();
    Menu retire = new Menu("retire");
    Menu board_of_editors = new Menu("board of editors");


    @FXML

    private TreeView<String> treeVolume;

    @FXML

    private Button addingEditor;

    TreeItem l = new TreeItem("Volumes");

    TableView tableView = new TableView();

    TableColumn<Article,String> article_name = new TableColumn<>("Name");
    TableColumn<Article,String> article_code = new TableColumn<>("ISSN");
    TableColumn<Article,String> article_check = new TableColumn<>("Select");
   // TableColumn<Article,String> article_accept = new TableColumn<>("Accept");
   // TableColumn<Article,String> article_reject = new TableColumn<>("reject");





    @FXML

    private VBox article;

    TreeItem one = new TreeItem("Edition1");

    TreeItem two = new TreeItem("Edition2");

    TreeItem three = new TreeItem("Edition3");

    TreeItem four = new TreeItem("Edition4");

    public ControlEditor() throws MalformedURLException {
    }


    public void initialize(){
        article.getChildren().add(tableView);
        l.setExpanded(true);
        treeVolume.setRoot(l);
        one.setExpanded(false);
        two.setExpanded(true);
        three.setExpanded(true);
        four.setExpanded(true);
        article_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        article_check.setCellValueFactory(new PropertyValueFactory<>("checkbox"));
        article_code.setCellValueFactory(new PropertyValueFactory<>("code"));
        article_check.setStyle("-fx-alignment: CENTER;");
        tableView.getColumns().addAll(article_name,article_code,article_check);
        data = FXCollections.observableArrayList(getArticle());
        System.out.println(getArticle().removeAll(getArticle()));
        tableView.setItems(data);
    }

    // for adding treeitem
    public void addTable(ActionEvent action){

        if (l.getChildren().isEmpty()){
            int i=Calendar.getInstance().get(Calendar.YEAR);
            store [0] =i;
            TreeItem r = new TreeItem(i);
            r.getChildren().addAll(one,two,three,four);
            l.getChildren().add(r);
       }
        else if (!l.getChildren().isEmpty()) {

            TreeItem r = new TreeItem(store[0] + 1);
            r.setExpanded(true);
            r.getChildren().addAll(one, two, three, four);
            l.getChildren().add(r);
            store[0] += 1;
        }

    }

    public ObservableList<Article> getArticle(){
        ObservableList<Article> articles = FXCollections.observableArrayList();
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

    //adding to teh treeitem
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
        if (selectedNode().getValue().toString().contains(" "))
            treeVolume.getSelectionModel().clearSelection();
        else if (!selectedNode().getValue().toString().contains(" ")){
            selectedNode().getChildren().addAll(items);
            data.removeAll(art);
        }
        treeVolume.getSelectionModel().clearSelection();
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
        URL url = new File("src/resources/RetireEditor.fxml").toURI().toURL();
        Parent view = FXMLLoader.load(url);
        Scene viewScene = new Scene(view);
        Stage window = new Stage();
        window.setResizable(true);
        window.setScene(viewScene);
        window.initModality(Modality.APPLICATION_MODAL);
        window.show();
    }

}

