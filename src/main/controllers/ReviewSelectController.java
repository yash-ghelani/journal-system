package main.controllers;

import com.sun.prism.paint.Color;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javafx.event.*;
import main.Main;
import main.tables.*;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;

import static main.Main.IDs;

public class ReviewSelectController {


    @FXML
    private Button toRemove;

    @FXML
    private VBox vBoxArticle;

    @FXML
    private Label finallabel;

    @FXML
    private HBox review;

    @FXML
    private Button select;
    @FXML
    private Label notify;


    public void handleBack(ActionEvent event) throws IOException {
        URL url = new File("src/resources/ReviewPanel.fxml").toURI().toURL();
        Parent view = FXMLLoader.load(url);
        Scene viewScene = new Scene(view);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(viewScene);
    }

    public void handleLoadArticles(ActionEvent event) throws IOException, SQLException {

        List<String> submissions = ArticleTable.SelectAllArticleTitles();
        List<Integer> ids = ArticleTable.SelectAllArticleIDs();

        if (submissions.size() == 0) {
            notify.setStyle("-fx-text-fill : red;");
            notify.setText("Currently there are no submissions");
        }

        for(int i =0; i<submissions.size(); i++) {

            URL url = new File("src/resources/ReviewSelectBox.fxml").toURI().toURL();

            HBox box = FXMLLoader.load(url);
            ObservableList<Node> child = box.getChildren();
            vBoxArticle.getChildren().remove(toRemove);

            VBox v = (VBox)child.get(0);
            Label title = (Label)v.getChildren().get(0);
            Label articleID = (Label)v.getChildren().get(1);
            int s = ids.get(i);
            //System.out.println(ArticleInfoTable.SelectAuthorID(s));

            if(ReviewTable.CheckReviewID(s) > 0){
                VBox vButton = (VBox)child.get(2);
                Button selector = (Button) vButton.getChildren().get(0);
                selector.setVisible(false);
            }

            if(IDs[0] == ArticleInfoTable.SelectAuthorID(s)){
                VBox vButton = (VBox)child.get(2);
                Button selector = (Button) vButton.getChildren().get(0);
                selector.setVisible(false);
            }

//            if(UserTable.SelectAffiliation(AuthorTable.SelectUserID(ArticleInfoTable.SelectAuthorID(s))) == UserTable.SelectAffiliation(AuthorTable.SelectUserID(IDs[0]))){
//                VBox vButton = (VBox)child.get(2);
//                Button selector = (Button) vButton.getChildren().get(0);
//                selector.setVisible(false);
//            }

            if (ReviewerTable.SelectCount(Main.IDs[2]) >= 3) {
                VBox vButton = (VBox)child.get(2);
                Button selector = (Button) vButton.getChildren().get(0);
                selector.setVisible(false);
            }

            title.setText(submissions.get(i));
            articleID.setText("ArticleID: " + s);

            Insets padding = new Insets(10,0,0,0);
            Separator sep = new Separator();
            sep.setPadding(padding);

            vBoxArticle.getChildren().add(box);
            vBoxArticle.getChildren().add(sep);

        }
    }

    public void handleSelectReview(ActionEvent actionEvent) throws SQLException {
        String text = finallabel.getText();
        String id = text.substring(11);
        int idtoint =Integer.parseInt(id);
        ReviewTable.Insert(IDs[2],idtoint,null,null, null);
        review.getChildren().remove(select);
        select.setVisible(false);
        select.setDisable(true);
        ReviewerTable.IncreaseCount(Main.IDs[2]);
    }
}
