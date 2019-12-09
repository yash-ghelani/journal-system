package main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import main.Main;
import main.tables.ReviewTable;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;

public class AuthorFinalController {

   @FXML
   private Label label;

    public void initialize() throws SQLException {
        label.setText("Final Verdict : "+ReviewTable.RSelectFinalVerdict(Main.AuthorCurrentReviewID));
    }

    public void handleCancel(ActionEvent actionEvent) throws IOException {
        Parent view = FXMLLoader.load(getClass().getResource("AuthorPanel.fxml"));
        Scene viewScene = new Scene(view);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setResizable(true);
        window.setScene(viewScene);
    }
}
