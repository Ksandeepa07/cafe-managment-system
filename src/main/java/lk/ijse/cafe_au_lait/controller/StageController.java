package lk.ijse.cafe_au_lait.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class StageController {
     static void changeStage(String fxml,String title) throws IOException {
         FXMLLoader fxmlLoader = new FXMLLoader(StageController.class.getResource(String.valueOf(fxml)));
         Parent root1 = (Parent) fxmlLoader.load();
         Stage stage = new Stage();
         stage.setTitle(title);
         stage.setScene(new Scene(root1));
         stage.setResizable(false);
         stage.show();
     }

     static void changeScene(String fxml, AnchorPane pane) throws IOException {
         Parent load = FXMLLoader.load(StageController.class.getResource(String.valueOf(fxml)));
         pane.getChildren().clear();
         pane.getChildren().add(load);


     }
}
