package main;

import com.sun.jdi.connect.IllegalConnectorArgumentsException;
import javafx.fxml.*;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.scene.control.cell.*;
import javafx.beans.value.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.*;

import java.sql.SQLException;
import java.util.ArrayList;
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

   @FXML

   private TextField addJ;

   @FXML

   private ChoiceBox checker;

   @FXML

   private Button addingEditor;

   @FXML

    private Button addingJor;

    @FXML

    private Button treepath;

    @FXML

    private Button newtree;

    @FXML

    private Button artA;

    @FXML

    private Button disart;

    @FXML

    private Button editretire;

    @FXML

    private Button selectedJ;

   static String name_of_journal;



    public void initialize() throws SQLException, MalformedURLException {
        editnames.setText(UserTable.SelectName(EditorTable.SelectUserID(Main.IDs[1])) + " " +
                UserTable.SelectSurName(EditorTable.SelectUserID(Main.IDs[1])));
        editertitle.setText(JournalInfoTable.SelectEditorType(Main.IDs[1]));
        SelectControl.name = editnames.getText();
        SelectControl.issn = JournalTable.SelectISSN((String) journals.getValue());
        kjsa();
            // JournalTable.Insert(18361310,"Journal of Computer Science");
            //System.out.println(VolumeTable.SelectVolID(1));


            //journals.getItems().addAll(JournalTable.selectJournals());
       // journals.setValue(JournalTable.SelectJournals().get(Main.vave));
        article.getChildren().add(tableView);
        l.setExpanded(true);
        treeVolume.setRoot(l);
        treeVolume.setCellFactory((TreeView<String> p) -> new TreeCellTextField());
        article_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        article_name.setStyle("-fx-alignment: CENTER;");
        article_check.setCellValueFactory(new PropertyValueFactory<>("checkbox"));
        article_check.setStyle("-fx-alignment: CENTER;");
        tableView.getColumns().addAll(article_name, article_check);
        data = FXCollections.observableArrayList();
        //getArticle().removeAll(getArticle());
        tableView.setItems(data);
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        treeVolume.refresh();
       // refresh(JournalTable.SelectJournals().get(0));
        journals.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue ov, Number old_val, Number new_val) {
                try {
                    refresh(JournalTable.SelectJournals().get((Integer) new_val));
                   // System.out.println(journals.getSelectionModel().isSelected(0));
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            });
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

    public ObservableList<Article> getArticle(String journal) throws SQLException {
        articles = FXCollections.observableArrayList();
        int issn = JournalTable.SelectISSN(journal);
        ArrayList<Integer> articleids = ArticleTable.SelectArticleIDS(issn);

        for (int i = 0 ; i < articleids.size() ; i++) {
            System.out.println(ReviewTable.CheckIfReviewed(articleids.get(i)));
            if (ReviewTable.CheckIfReviewed(articleids.get(i))) {
                int articleid = articleids.get(i);
                articles.add(new Article(ArticleTable.SelectTitle(articleid)));
            }
        }

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
    synchronized public void addtoE() throws InterruptedException, SQLException {
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
            if (selectedNode().getParent().getParent()== null)
                treeVolume.getSelectionModel().clearSelection();
            else if (selectedNode().getParent().getParent().getParent() != null){
                treeVolume.getSelectionModel().clearSelection();
            }
            else if (selectedNode().getParent().getParent() != null) {
                String title = "";
                for (TreeItem x : items){
                    title = x.getValue().toString();
                }
                ArticleTable.UpdateToPublished(title);
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

        public void conditionToTable () throws SQLException {
        if (l.getChildren().isEmpty()) {
            int i = Calendar.getInstance().get(Calendar.YEAR);
            store[0] = i;
            VolumeTable.Insert(JournalTable.SelectISSN((String) journals.getValue()), i);

            TreeItem r = new TreeItem(String.valueOf(VolumeTable.SelectPublicationYear(
                    JournalTable.SelectISSN((String) journals.getValue()), i)));//

            l.getChildren().add(r);
        } else if (!l.getChildren().isEmpty()) {
            int o= JournalTable.SelectISSN((String) journals.getValue());
            Object[] b = VolumeTable.SelectVolumes(o).toArray();
            store[0] = (Integer.valueOf((String) b[b.length - 1])) + 1;
            VolumeTable.Insert(JournalTable.SelectISSN((String) journals.getValue()), store[0]);

            TreeItem r = new TreeItem(String.valueOf(VolumeTable.SelectPublicationYear(
                    JournalTable.SelectISSN((String) journals.getValue()), store[0])));//

            l.getChildren().add(r);
            treeVolume.refresh();
        }
    }

    public void refresh(String journ) throws MalformedURLException, SQLException {
        data.clear();
        data.addAll(getArticle(journ));
        System.out.println(getArticle(journ));
        Main.currentJournalName = journ;
        l.getChildren().clear();
        name_of_journal = journ;
        ObservableList<TreeItem> j = FXCollections.observableArrayList();

        for (String r : VolumeTable.SelectVolumes(JournalTable.SelectISSN(journ))){
            j.add(new TreeItem<String>(r));
            
        }


        //System.out.println(edids);

        for (TreeItem n : j){
            ObservableList<TreeItem> a = FXCollections.observableArrayList();
            int volid = VolumeTable.SelectVolumeID(JournalTable.SelectISSN(journ), Integer.valueOf((String) n.getValue()));


            for (String e : EditionTable.selectMonth(volid)) {
                a.add(new TreeItem(e));
            }

            for (TreeItem b: a) {
                n.getChildren().add(b);
                int month = EditionTable.selectArticles(Integer.valueOf((String) b.getValue()), volid);

                ArrayList<String> dc = ArticleTable.SelectTitles(month);
                ObservableList<TreeItem> w = FXCollections.observableArrayList();


                    for (String title : dc) {
                        w.add(new TreeItem(title));
                    }

                    for (TreeItem ti : w) {
                        b.getChildren().add(ti);
                    }
            }
            l.getChildren().add(n);
        }
    }

    public void addJournal(ActionEvent actionEvent) throws SQLException {
        if (addJ.getText().isEmpty()){
            addJ.setStyle("-fx-prompt-text-fill:red");
        }
        else
        {
        String h = String.valueOf(addJ.getText().hashCode()).substring(0,8);
        JournalTable.Insert(Integer.valueOf(h),addJ.getText());
        addJ.clear();
        }
    }

    public void selector(ActionEvent actionEvent) throws IOException {
        URL url = new File("src/main/SelectJournal.fxml").toURI().toURL();
        Parent view = FXMLLoader.load(url);
        Scene viewScene = new Scene(view);
        Stage window = new Stage();
        window.setResizable(true);
        window.setScene(viewScene);
        window.initModality(Modality.APPLICATION_MODAL);
        main.SelectControl.closer = window;
        window.show();
    }

    public void kjsa() throws SQLException {
        String [] vm =  editnames.getText().split(" ");
        ArrayList<Integer> ik = JournalInfoTable.SelectISSNFromEditor(EditorTable.GetID(UserTable.GetID(vm[0],vm[1])));
        ArrayList<Integer> issns = JournalInfoTable.SelectISSNFromEditor(Main.IDs[1]);
        for (int i = 0; i < issns.size(); i++) {
            journals.getItems().add(JournalTable.SelectName(issns.get(i)));
        }

        if (JournalTable.SelectJournals().isEmpty() && ik.isEmpty() ){
            addingEditor.setDisable(true);
            journals.setDisable(true);
            //addingJor.setDisable(true);
            treepath.setDisable(true);
            newtree.setDisable(true);
            artA.setDisable(true);
            disart.setDisable(true);
            editretire.setDisable(true);
            selectedJ.setDisable(true);
        }
        else if (!JournalTable.SelectJournals().isEmpty() && ik.isEmpty()){
            addingEditor.setDisable(true);
            journals.setDisable(true);
            //addingJor.setDisable(true);
            treepath.setDisable(true);
            newtree.setDisable(true);
            artA.setDisable(true);
            disart.setDisable(true);
            editretire.setDisable(true);
           //selectedJ.setDisable(true);
        }
        else {
            addingEditor.setDisable(false);
            journals.setDisable(false);
            addingJor.setDisable(true);
            treepath.setDisable(false);
            newtree.setDisable(false);
            artA.setDisable(false);
            disart.setDisable(false);
            editretire.setDisable(false);
            selectedJ.setDisable(false);
        }
    }

}

