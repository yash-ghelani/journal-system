package main;

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
import main.tables.ArticleTable;
import main.tables.ReviewTable;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;

import static main.Main.ArticleIDForReview;


public class ReviewPanelController {
    @FXML
    private Button viewresponse;

    @FXML
    private Button clickToAdd;

    @FXML
    private VBox vboxpanel;

    @FXML
    private Label submissionid;

    @FXML
    private Label pleaseselect;

    public void handleNewReview(ActionEvent event) throws IOException {
        Parent view = FXMLLoader.load(getClass().getResource("ReviewSelect.fxml"));
        Scene viewScene = new Scene(view);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(viewScene);
    }


    public void handleViewArticle(ActionEvent event) throws IOException {
        Parent view = FXMLLoader.load(getClass().getResource("ReviewPanel.fxml"));
        Scene viewScene = new Scene(view);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(viewScene);
    }

    public void handleViewResponse(ActionEvent event) throws IOException, SQLException {
        String text = submissionid.getText();
        String id = text.substring(11);
        int idtoint = Integer.parseInt(id);
        Main.ArticleIDForReview = idtoint;
        if ((ReviewTable.SelectReviewsCompleted(Main.ArticleIDForReview).size() > 0)) {

            Parent view = FXMLLoader.load(getClass().getResource("FinalVerdict.fxml"));
            Scene viewScene = new Scene(view);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(viewScene);

        } else {
            viewresponse.setStyle("-fx-text-fill : red;");
            viewresponse.setText("No response yet");

        }


    }

    public void handleLogOut(ActionEvent event) throws IOException {
        Parent view = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Scene viewScene = new Scene(view);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(viewScene);
    }


    public void handleClick(ActionEvent actionEvent) throws IOException, SQLException {

        ArrayList<Integer> submissionids = ReviewTable.SelectListOfArticleIDs(Main.IDs[2]);

        if (submissionids.isEmpty()) {
            pleaseselect.setText("Please select reviews");
        }

        for (int i = 0; i < (submissionids).size(); i++) {

            int reviewid = ReviewTable.SelectReviewID(Main.IDs[2], submissionids.get(i));

            pleaseselect.setText("");
            HBox box = FXMLLoader.load(getClass().getResource("ReviewPanelBox.fxml"));
            ObservableList<Node> child = box.getChildren();
            vboxpanel.getChildren().remove(clickToAdd);
            vboxpanel.getChildren().add(box);

            VBox v = (VBox) child.get(0);
            VBox pdfBox = (VBox) child.get(2);
            VBox initialButtonBox = (VBox) child.get(3);
            Button initialButton = (Button) initialButtonBox.getChildren().get(0);
            Button finalButton = (Button) initialButtonBox.getChildren().get(1);
            Label title = (Label) v.getChildren().get(0);
            Label articleID = (Label) v.getChildren().get(1);
            Label pdf = (Label)pdfBox.getChildren().get(0);

            pdf.setText("PDF Link: "+ArticleTable.SelectPDF(submissionids.get(i)));
            title.setText(ArticleTable.SelectTitle(submissionids.get(i)));
            articleID.setText("ArticleID: " + submissionids.get(i));

            if(ReviewTable.CheckReviewIDsSum(reviewid)>0){
                initialButton.setVisible(false);
            }
            //System.out.println(ReviewTable.SelectInitialVerdict(reviewid).equals("null"));

            if(ReviewTable.SelectInitialVerdict(reviewid) == "null"){
                finalButton.setVisible(false);
            }

            Insets padding = new Insets(10, 0, 0, 0);
            Separator sep = new Separator();
            sep.setPadding(padding);


            if(ReviewTable.SelectFinalVerdict(submissionids.get(i)) == ("null")){
                vboxpanel.getChildren().add(box);
                vboxpanel.getChildren().add(sep);
                //System.out.
            }
        }
    }

    public void handleSubmitInitialVerdict(ActionEvent actionEvent) throws IOException {
        String text = submissionid.getText();
        String id = text.substring(11);
        int idtoint = Integer.parseInt(id);
        ArticleIDForReview = idtoint;
        //System.out.println(ArticleIDForReview);
        Parent view = FXMLLoader.load(getClass().getResource("InitialVerdict.fxml"));
        Scene viewScene = new Scene(view);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(viewScene);
    }

    public void handleSubmitFinalVerdict(ActionEvent actionEvent) throws IOException {
        String text = submissionid.getText();
        String id = text.substring(11);
        int idtoint = Integer.parseInt(id);
        ArticleIDForReview = idtoint;
        //System.out.println(ArticleIDForReview);
        Parent view = FXMLLoader.load(getClass().getResource("FinalVerdict.fxml"));
        Scene viewScene = new Scene(view);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(viewScene);
    }
}
